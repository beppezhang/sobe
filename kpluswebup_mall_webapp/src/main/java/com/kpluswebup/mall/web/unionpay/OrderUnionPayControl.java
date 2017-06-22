package com.kpluswebup.mall.web.unionpay;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.account.service.AccountDetailService;
import com.kpluswebup.web.domain.AccountDetailDTO;
import com.kpluswebup.web.service.SalesOrderLineService;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.vo.SalesOrderLineVO;
import com.kpluswebup.web.vo.SalesOrderVO;
import com.unionpay.acp.sdk.LogUtil;
import com.unionpay.acp.sdk.SDKConfig;
import com.unionpay.acp.sdk.SDKConstants;
import com.unionpay.acp.sdk.SDKUtil;

@Controller
@RequestMapping("/")
public class OrderUnionPayControl extends BaseController {

	@Autowired
	private SalesOrderService salesOrderService;

	@Autowired
	private SalesOrderLineService salesOrderLineService;

	@Autowired
	private AccountDetailService accountDetailService;

	@RequestMapping("orderUnionPay")
	public ModelAndView unionPay(String orderID) {
		/**
		 * 初始化证书
		 */
		SDKConfig.getConfig().loadPropertiesFromSrc();// 从classpath加载acp_sdk.properties文件

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date currentTime = new Date();// 得到当前系统时间
		String pDate = formatter.format(currentTime);

		HttpServletRequest requests = this.getRequest();
		String baseDiskPath = requests.getSession().getServletContext()
				.getRealPath("/");
		System.out.println(baseDiskPath);
		SalesOrderVO order = salesOrderService.getSalesOrderLine(orderID);
		List<SalesOrderLineVO> list = salesOrderLineService
				.findSalesOrderLine(orderID);
		domain="http://124.160.25.226:8888";
		String backUrl = domain + "/backUrlPage.htm?orderId="
				+ order.getMainID();
		String frontUrl = domain + "/frontUrlPage.htm?orderId="
				+ order.getMainID();
		Integer amount = Double.valueOf((order.getTotalAmount()) * 100)
				.intValue();
		StringBuffer orderBody = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			if (i == 0) {
				orderBody.append((i + 1) + ":" + list.get(i).getItemName());
			} else {
				orderBody.append(";" + (i + 1) + ":"
						+ list.get(i).getItemName());
			}
		}
		/**
		 * 组装请求报文
		 */
		Map<String, String> data = new HashMap<String, String>();
		// 版本号
		data.put("version", "5.0.0");
		// 字符集编码 默认"UTF-8"
		String encoding = "UTF-8";
		data.put("encoding", encoding);
		// 签名方法 01 RSA
		data.put("signMethod", "01");
		// 交易类型 01-消费
		data.put("txnType", "01");
		// 交易子类型 01:自助消费 02:订购 03:分期付款
		data.put("txnSubType", "01");
		// 业务类型 000201 B2C网关支付
		data.put("bizType", "000201");
		// 渠道类型 07-互联网渠道
		data.put("channelType", "07");
		// 商户/收单前台接收地址 选送
		// 后台服务对应的写法参照 com.unionpay.acp.sdksample.notice.FrontRcvResponse.java
		data.put("frontUrl", frontUrl);
		// 商户/收单后台接收地址 必送
		// 后台服务对应的写法参照 com.unionpay.acp.sdksample.notice.BackRcvResponse.java
		data.put("backUrl", backUrl);
		// 接入类型:商户接入填0 0- 商户 ， 1： 收单， 2：平台商户
		data.put("accessType", "0");
		// 商户号码
		data.put("merId", "777290058111645");
		// 订单号 商户根据自己规则定义生成，每订单日期内不重复
		data.put("orderId", orderID);
		// 订单发送时间 格式： YYYYMMDDhhmmss 商户发送交易时间，根据自己系统或平台生成
		data.put("txnTime", pDate);
		// 交易金额 分
		data.put("txnAmt", amount.toString());
		// 交易币种
		data.put("currencyCode", "156");
		// 发卡机构代码 根据需求选送 参考接口规范 当需要网银标志前移时，上送银行简称代码 如 工行 ICBC
		data.put("issInsCode", "ICBC");
		// 持卡人ip 根据需求选送 参考接口规范 防钓鱼用
		data.put("customerIp", "127.0.0.1");

		Map<String, String> request = new HashMap<String, String>();
		request.putAll(data);
		Set<String> set = data.keySet();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			if (null == data.get(key) || "".equals(data.get(key))) {
				request.remove(key);
			}
		}

		/**
		 * 签名
		 */
		SDKUtil.sign(request, encoding);

		// 交易请求url 从配置文件读取
		String requestFrontUrl = SDKConfig.getConfig().getFrontRequestUrl();
		/**
		 * 创建表单
		 */
		String html = RequestData.createHtml(requestFrontUrl, request);

		System.out.println(html);
		getResponse().setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		try {
			out = getResponse().getWriter();
			out.println(html);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("backUrlPage")
	public ModelAndView backUrlPage(String orderId) {
		try {
			SDKConfig.getConfig().loadPropertiesFromSrc();// 从classpath加载acp_sdk.properties文件
			LogUtil.writeLog("BackRcvResponse接收后台通知开始");

			getRequest().setCharacterEncoding("UTF-8");
			String encoding = getRequest().getParameter(
					SDKConstants.param_encoding);
			// 获取请求参数中所有的信息
			Map<String, String> reqParam = getAllRequestParam(getRequest());
			// 打印请求报文
			LogUtil.printRequestLog(reqParam);

			Map<String, String> valideData = null;
			if (null != reqParam && !reqParam.isEmpty()) {
				Iterator<Entry<String, String>> it = reqParam.entrySet()
						.iterator();
				valideData = new HashMap<String, String>(reqParam.size());
				while (it.hasNext()) {
					Entry<String, String> e = it.next();
					String key = (String) e.getKey();
					String value = (String) e.getValue();

					value = new String(value.getBytes("UTF-8"), encoding);

					valideData.put(key, value);
				}
			}
			String orderID = valideData.get("orderId");
			String txnAmt = valideData.get("txnAmt");
			String queryId = valideData.get("queryId");
			// 验证签名
			if (!SDKUtil.validate(valideData, encoding)) {
				LogUtil.writeLog("验证签名结果[失败].");
			} else {
				System.out.println(valideData.get("orderId")); // 其他字段也可用类似方式获取
				LogUtil.writeLog("验证签名结果[成功].");
				changeOrder(orderID, txnAmt, queryId);
			}

			LogUtil.writeLog("BackRcvResponse接收后台通知结束");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取请求参数中所有的信息
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getAllRequestParam(
			final HttpServletRequest request) {
		Map<String, String> res = new HashMap<String, String>();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				String value = request.getParameter(en);
				res.put(en, value);
				// 在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
				// System.out.println("ServletUtil类247行  temp数据的键=="+en+"     值==="+value);
				if (null == res.get(en) || "".equals(res.get(en))) {
					res.remove(en);
				}
			}
		}
		return res;
	}

	/**
	 * 支付成功
	 * 
	 * @date 2015年1月15日
	 * @author liudanqi
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("frontUrlPage")
	public ModelAndView frontUrlPage(String orderId) {
		SDKConfig.getConfig().loadPropertiesFromSrc();// 从classpath加载acp_sdk.properties文件
		LogUtil.writeLog("FrontRcvResponse前台接收报文返回开始");
		try {
			getRequest().setCharacterEncoding("UTF-8");
			String encoding = getRequest().getParameter(
					SDKConstants.param_encoding);
			LogUtil.writeLog("返回报文中encoding=[" + encoding + "]");
			Map<String, String> respParam = getAllRequestParam(getRequest());

			// 打印请求报文
			LogUtil.printRequestLog(respParam);

			Map<String, String> valideData = null;
			StringBuffer page = new StringBuffer();
			if (null != respParam && !respParam.isEmpty()) {
				Iterator<Entry<String, String>> it = respParam.entrySet()
						.iterator();
				valideData = new HashMap<String, String>(respParam.size());
				while (it.hasNext()) {
					Entry<String, String> e = it.next();
					String key = (String) e.getKey();
					String value = (String) e.getValue();
					value = new String(value.getBytes("UTF-8"), encoding);
					page.append("<tr><td width=\"30%\" align=\"right\">" + key
							+ "(" + key + ")</td><td>" + value + "</td></tr>");
					valideData.put(key, value);
				}
			}
			if (!SDKUtil.validate(valideData, encoding)) {
				page.append("<tr><td width=\"30%\" align=\"right\">验证签名结果</td><td>失败</td></tr>");
				LogUtil.writeLog("验证签名结果[失败].");
			} else {
				page.append("<tr><td width=\"30%\" align=\"right\">验证签名结果</td><td>成功</td></tr>");
				LogUtil.writeLog("验证签名结果[成功].");
				System.out.println(valideData.get("orderId")); // 其他字段也可用类似方式获取
				String orderID = valideData.get("orderId");
				String txnAmt = valideData.get("txnAmt");
				String queryId = valideData.get("queryId");
				changeOrder(orderID, txnAmt, queryId);
			}
			LogUtil.writeLog("FrontRcvResponse前台接收报文返回结束");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return new ModelAndView("redirect:mall/buyer/userCenter.htm");
	}

	public void changeOrder(String orderId, String amount, String cupsQid) {
		SalesOrderVO salesOrderVO = salesOrderService
				.findSalesOrderByMainID(orderId);
		if (salesOrderVO.getOrderStatus() < 2) {
			AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
			accountDetailDTO.setSerialNumber(cupsQid);
			accountDetailDTO.setDetailType(2);
			accountDetailDTO.setAmount(Double.parseDouble(amount));
			accountDetailDTO.setAccountType(1);
			accountDetailDTO.setPaymentType(2);
			accountDetailDTO.setDescription(cupsQid);
			accountDetailDTO.setObjID(salesOrderVO.getMainID());
			accountDetailDTO.setCustomerID(salesOrderVO.getCustomerID());
			accountDetailDTO.setStatus(1);
			accountDetailService.addMemberScore(accountDetailDTO);
		}

	}

}
