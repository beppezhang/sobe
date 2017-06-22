package com.kpluswebup.mall.web.order.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.admin.system.service.AreaService;
import com.kpluswebup.web.admin.system.service.FreightTemplatePriceService;
import com.kpluswebup.web.admin.system.service.FreightTemplateService;
import com.kpluswebup.web.admin.system.service.ShippingAddressService;
import com.kpluswebup.web.admin.system.service.SystemLogService;
import com.kpluswebup.web.customer.dao.CustomerDeliveryAddressDAO;
import com.kpluswebup.web.domain.CustomerDeliveryAddressDTO;
import com.kpluswebup.web.domain.FavoriteDTO;
import com.kpluswebup.web.domain.PromotionDTO;
import com.kpluswebup.web.domain.ShoppingCartDTO;
import com.kpluswebup.web.domain.SystemLogDTO;
import com.kpluswebup.web.member.service.CustomerAddressService;
import com.kpluswebup.web.member.service.CustomerGradeSerivce;
import com.kpluswebup.web.member.service.FavoriteService;
import com.kpluswebup.web.member.service.MemberSerivce;
import com.kpluswebup.web.member.service.ShoppingCartSerivce;
import com.kpluswebup.web.service.FlashSaleService;
import com.kpluswebup.web.service.ItemScoreService;
import com.kpluswebup.web.service.ItemService;
import com.kpluswebup.web.service.PreSaleService;
import com.kpluswebup.web.service.ProductService;
import com.kpluswebup.web.service.PromotionService;
import com.kpluswebup.web.service.SalesOrderDeliveryAddressService;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.supplier.service.SupplierService;
import com.kpluswebup.web.vo.AreaVO;
import com.kpluswebup.web.vo.CustomerDeliveryAddressVO;
import com.kpluswebup.web.vo.CustomerGradeVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.FavoriteVO;
import com.kpluswebup.web.vo.FlashSaleVO;
import com.kpluswebup.web.vo.FreightTemplatePriceVO;
import com.kpluswebup.web.vo.FreightTemplateVO;
import com.kpluswebup.web.vo.ItemDetailVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.PreSaleVO;
import com.kpluswebup.web.vo.PromotionSetVO;
import com.kpluswebup.web.vo.PromotionVO;
import com.kpluswebup.web.vo.SalesOrderDeliveryAddressVO;
import com.kpluswebup.web.vo.SalesOrderVO;
import com.kpluswebup.web.vo.ShippingAddressVO;
import com.kpluswebup.web.vo.ShoppingCartVO;
import com.kpluswebup.web.vo.SupplierVO;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.IPRequest;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping({ "/" })
public class ShoppingCartControl extends BaseController {

	@Autowired
	private ShoppingCartSerivce shoppingCartSerivce;

	@Autowired
	private ShippingAddressService shippingAddressService;

	@Autowired
	private FavoriteService favoriteService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private CustomerAddressService customerAddressService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private SalesOrderService salesOrderService;

	@Autowired
	private SalesOrderDeliveryAddressService salesOrderDeliveryAddressService;

	@Autowired
	private MemberSerivce memberSerivce;

	@Autowired
	private ItemScoreService itemScoreService;

	@Autowired
	private CachedClient cachedClient;

	@Autowired
	private PromotionService promotionService;

	@Autowired
	private FreightTemplatePriceService freightTemplatePriceService;

	@Autowired
	private PreSaleService preSaleService;

	@Autowired
	private FlashSaleService flashSaleService;

	@Autowired
	private CustomerGradeSerivce customerGradeSerivce;

	@Autowired
	private ProductService productService;

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private SystemLogService systemLogService;

	@Autowired
	private FreightTemplateService freightTemplateService;

	@Autowired
	private CustomerDeliveryAddressDAO customerDeliveryAddressDAO;

	@RequestMapping({ "shoppingCart1" })
	public ModelAndView shoppingCart1(HttpServletRequest request,
			HttpServletResponse response) {
		CustomerVO customerVO = findUserInfo();
		if (customerVO == null) {
			String uuid = getCookieUUID(request, response);
			this.cachedClient.set("before_login_url_" + uuid,
					Constant.EXP.intValue(), "/shoppingCart1.htm");
			return new ModelAndView("redirect:/mall/member/toLogin.htm");
		}
		ModelAndView modelAndView = newModelAndView();
		modelAndView.setViewName("/screen/order/shoppingcart_1");
		List<ShoppingCartVO> list = this.shoppingCartSerivce
				.findShoppingCart(customerVO.getMainID());
		Map<String, List<ShoppingCartVO>>	shopCartMap;
		if ((list != null) && (list.size() > 0)) {
			shopCartMap = new LinkedHashMap<String, List<ShoppingCartVO>>();
			for (ShoppingCartVO shoppingCartVO : list) {
				ItemVO itemVO = this.itemService.findItemById(shoppingCartVO
						.getItemID());
				shoppingCartVO.setItemPicUrl(itemVO.getPicURL());
				if (shoppingCartVO.getType().intValue() == 1) {
					shoppingCartVO.setAllamount(Double.valueOf(shoppingCartVO
							.getItemCount().intValue()
							* shoppingCartVO.getSalesPrice().doubleValue()));
				} else if (shoppingCartVO.getType().intValue() == 2) {
					shoppingCartVO.setAllscore(Integer.valueOf(shoppingCartVO
							.getItemCount().intValue()
							* itemVO.getScore().intValue()));
				} else if (shoppingCartVO.getType().intValue() == 3) {
					shoppingCartVO.setAllamount(Double.valueOf(shoppingCartVO
							.getItemCount().intValue()
							* itemVO.getScorePrice().doubleValue()));
					shoppingCartVO.setAllscore(Integer.valueOf(shoppingCartVO
							.getItemCount().intValue()
							* shoppingCartVO.getSalesScore().intValue()));
				}
				List temp = new ArrayList();
				String sid1 = shoppingCartVO.getSupplierID();
				String siname1 = shoppingCartVO.getSupplierName();
				for (ShoppingCartVO shoppingCartVO1 : list) {
					String sid2 = shoppingCartVO1.getSupplierID();
					if (sid1.equals(sid2)) {
						temp.add(shoppingCartVO1);
					}
				}
				shopCartMap.put(sid1 + "_" + siname1, temp);
			}
			modelAndView.addObject("shoppingCartList", shopCartMap);
		}

		ShippingAddressVO shippingAddressVO = this.shippingAddressService
				.findDefaultShippingAddressOrder();
		modelAndView.addObject("shippingAddressVO", shippingAddressVO);
		List guessLikeProductList = this.productService.findProductYourLove();
		modelAndView.addObject("guessLikeProductList", guessLikeProductList);
		return modelAndView;
	}

	@RequestMapping(value = { "checkedShoppingCartAmount" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public void checkedShoppingCartAmount(String checkedCartIds,
			String checkedCartNums, HttpServletResponse response)
			throws IOException {
		StringBuffer result = new StringBuffer();
		CustomerVO customerVO = findUserInfo();
		if (customerVO == null) {
			result.append("{\"info\": \"1000\"}");
		} else {
			String[] cartIds = checkedCartIds.split(",");
			String[] cartNums = checkedCartNums.split(",");
			Double totalAmount = Double.valueOf(0.0D);
			String unCartID = null;
			StringBuffer unSaleCartIds = new StringBuffer();
			result.append("{\"info\": \"0\", \"amountArr\":[");
			for (int i = 0; i < cartIds.length; i++) {
				ShoppingCartVO shoppingCartVO = this.shoppingCartSerivce
						.findShoppingCartByID(Long.valueOf(Long
								.parseLong(cartIds[i])));
				if (shoppingCartVO == null) {
					unCartID = cartIds[i];
					break;
				}
				Integer num = Integer.valueOf(Integer.parseInt(cartNums[i]));
				if ((shoppingCartVO.getStock() != null)
						&& (shoppingCartVO.getStock().intValue() >= num
								.intValue())) {
					if (!shoppingCartVO.getItemCount().equals(num)) {
						ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
						shoppingCartDTO.setItemCount(num);
						shoppingCartDTO.setId(shoppingCartVO.getId());
						this.shoppingCartSerivce
								.updateShoppingCart(shoppingCartDTO);
					}
					Double amount = Double.valueOf(shoppingCartVO
							.getSalesPrice().doubleValue() * num.intValue());
					if (i == 0)
						result.append(amount);
					else
						result.append("," + amount);
					totalAmount = Double.valueOf(totalAmount.doubleValue()
							+ amount.doubleValue());
				}
			}
			if (unCartID != null) {
				result = new StringBuffer();
				result.append("{\"info\": \"2000\", \"cartId\":\"" + unCartID
						+ "\"}");
			} else {
				result.append("], \"totalAmount\":" + totalAmount + "}");
			}
			PrintWriter out = response.getWriter();
			out.print(result.toString());
			out.close();
		}
	}

	@RequestMapping({ "addFavorite" })
	@ResponseBody
	public JsonResult addFavorite(String itemID, String productID,
			HttpServletRequest request, HttpServletResponse response) {
		CustomerVO customerVO = findUserInfo();
		if (customerVO == null) {
			String uuid = getCookieUUID(request, response);
			this.cachedClient.set("before_login_url_" + uuid,
					Constant.EXP.intValue(), "/shoppingCart1.htm");
			return new JsonResult(ResultCode.ERROR_LOGIN);
		}
		FavoriteDTO favoriteDTO = new FavoriteDTO();
		favoriteDTO.setCustomerID(customerVO.getMainID());
		favoriteDTO.setProductID(productID);
		FavoriteVO favoriteVO = this.favoriteService
				.findFavoriteByCustomerItem(favoriteDTO);
		if (favoriteVO != null) {
			return new JsonResult(ResultCode.ERROR_FAVORITE);
		}
		favoriteDTO.setProductID(productID);
		try {
			this.favoriteService.insertFavorite(favoriteDTO);
			return JsonResult.create();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}

	@RequestMapping({ "delShoppingCart" })
	@ResponseBody
	public JsonResult delShoppingCart(Long id, HttpServletRequest request,
			HttpServletResponse response) {
		CustomerVO customerVO = findUserInfo();
		if (customerVO == null) {
			String uuid = getCookieUUID(request, response);
			this.cachedClient.set("before_login_url_" + uuid,
					Constant.EXP.intValue(), "/shoppingCart1.htm");
			return new JsonResult(ResultCode.ERROR_LOGIN);
		}
		Boolean isSuccess = this.shoppingCartSerivce.delShoppingCartByID(id);

		List list = this.shoppingCartSerivce.findShoppingCart(customerVO
				.getMainID());
		this.cachedClient.set("shopping_cart_count_" + customerVO.getMainID(),
				Constant.EXP.intValue(), Integer.valueOf(list.size()));
		if (isSuccess.booleanValue()) {
			return JsonResult.create();
		}
		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}

	@RequestMapping({ "delShoppingCartAll" })
	@ResponseBody
	public JsonResult delShoppingCartAll(HttpServletRequest request,
			HttpServletResponse response) {
		CustomerVO customerVO = findUserInfo();
		if (customerVO == null) {
			String uuid = getCookieUUID(request, response);
			this.cachedClient.set("before_login_url_" + uuid,
					Constant.EXP.intValue(), "/shoppingCart1.htm");
			return new JsonResult(ResultCode.ERROR_LOGIN);
		}
		Boolean isSuccess = this.shoppingCartSerivce
				.delShoppingCartAll(customerVO.getMainID());
		if (isSuccess.booleanValue()) {
			return JsonResult.create();
		}
		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}

	@RequestMapping({ "updateShoppingCart" })
	@ResponseBody
	public JsonResult updateShoppingCart(String id, String number) {
		try {
			ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
			shoppingCartDTO.setItemCount(Integer.valueOf(number));
			shoppingCartDTO.setId(Long.valueOf(id));
			this.shoppingCartSerivce.updateShoppingCart(shoppingCartDTO);
			return JsonResult.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}

	@RequestMapping({ "shoppingCart2" })
	public ModelAndView shoppingCart2(String cartID,
			HttpServletRequest request, HttpServletResponse response) {
		CustomerVO customerVO = findUserInfo();
		if (customerVO == null) {
			String uuid = getCookieUUID(request, response);
			this.cachedClient.set("before_login_url_" + uuid,
					Constant.EXP.intValue(), "/shoppingCart2.htm?cartID="
							+ cartID);
			return new ModelAndView("redirect:mall/member/toLogin.htm");
		}
		ModelAndView modelAndView = newModelAndView();
		modelAndView.setViewName("/screen/order/shoppingcart_2");
		List addressList = this.customerAddressService
				.findPassAddressByCustomerID(customerVO.getMainID(), 0);
		modelAndView.addObject("addressList", addressList);

		CustomerDeliveryAddressVO customerDeliveryAddressVO = null;
		for (Iterator iterator = addressList.iterator(); iterator.hasNext();) {
			CustomerDeliveryAddressVO address = (CustomerDeliveryAddressVO) iterator
					.next();
			if (address.getIsDefault().intValue() == 1) {
				customerDeliveryAddressVO = address;
			}

		}

		List<ShoppingCartVO> list = new ArrayList();
		String[] ids = cartID.split(",");
		double allFreight = 0.0D;
		for (String id : ids) {
			ShoppingCartVO shoppingCartVO = this.shoppingCartSerivce
					.findShoppingCartByID(Long.valueOf(id));
			if (customerDeliveryAddressVO != null) {
				Double itemFreight = freightCanculate(shoppingCartVO,
						customerVO);
				shoppingCartVO.setFreight(itemFreight.doubleValue());
				allFreight += itemFreight.doubleValue();
			}
			list.add(shoppingCartVO);
		}
		Integer count = Integer.valueOf(0);
		Double amount = Double.valueOf(0.0D);
		Integer score = Integer.valueOf(0);
		Double weight = Double.valueOf(0.0D);

		Map freightMap = new HashMap();

		if ((list != null) && (list.size() > 0)) {
			String supplierFreightStr = "";
			Map shopCartMap = new HashMap();
			for (ShoppingCartVO shoppingCartVO : list) {
				ItemVO itemVO = this.itemService.findItemById(shoppingCartVO
						.getItemID());
				count = Integer.valueOf(count.intValue()
						+ shoppingCartVO.getItemCount().intValue());

				if (shoppingCartVO.getType().intValue() == 1) {
					amount = Double.valueOf(amount.doubleValue()
							+ shoppingCartVO.getItemCount().intValue()
							* shoppingCartVO.getSalesPrice().doubleValue());
					shoppingCartVO.setAllamount(Double.valueOf(shoppingCartVO
							.getItemCount().intValue()
							* shoppingCartVO.getSalesPrice().doubleValue()));
				} else if (shoppingCartVO.getType().intValue() == 2) {
					score = Integer.valueOf(score.intValue()
							+ shoppingCartVO.getItemCount().intValue()
							* itemVO.getScore().intValue());
					shoppingCartVO.setAllscore(Integer.valueOf(shoppingCartVO
							.getItemCount().intValue()
							* itemVO.getScore().intValue()));
				} else if (shoppingCartVO.getType().intValue() == 3) {
					amount = Double.valueOf(amount.doubleValue()
							+ shoppingCartVO.getItemCount().intValue()
							* itemVO.getScorePrice().doubleValue());
					score = Integer.valueOf(score.intValue()
							+ shoppingCartVO.getItemCount().intValue()
							* shoppingCartVO.getSalesScore().intValue());
					shoppingCartVO.setAllamount(Double.valueOf(shoppingCartVO
							.getItemCount().intValue()
							* itemVO.getScorePrice().doubleValue()));
					shoppingCartVO.setAllscore(Integer.valueOf(shoppingCartVO
							.getItemCount().intValue()
							* itemVO.getSalesScore().intValue()));
				}
				List temp = new ArrayList();
				String sid1 = shoppingCartVO.getSupplierID();
				String siname1 = shoppingCartVO.getSupplierName();
				String linkQQ = shoppingCartVO.getLinkQQ();
				String linkWangWang = shoppingCartVO.getLinkWangWang();

				double supplierFreight = 0.0D;
				for (ShoppingCartVO shoppingCartVO1 : list) {
					String sid2 = shoppingCartVO1.getSupplierID();
					if (sid1.equals(sid2)) {
						temp.add(shoppingCartVO1);
						supplierFreight += shoppingCartVO1.getFreight();
					}
				}
				shopCartMap.put(sid1 + "_" + siname1 + "_" + linkQQ + "_"
						+ linkWangWang, temp);
				freightMap.put(sid1 + "_" + siname1,
						Double.valueOf(supplierFreight));
			}

			Iterator iterator = freightMap.keySet().iterator();
			while (iterator.hasNext()) {
				String supplierKey = (String) iterator.next();
				supplierFreightStr = supplierFreightStr + supplierKey + ":"
						+ freightMap.get(supplierKey) + "|";
			}

			modelAndView.addObject("shoppingCartList", shopCartMap);
			modelAndView.addObject("count", count);
			modelAndView.addObject("weight", weight);
			modelAndView.addObject("amount", amount);
			modelAndView.addObject("score", score);
			modelAndView.addObject("generateType", "shoppingcart");
			customerVO = this.memberSerivce.findMemberByMainID(customerVO
					.getMainID());
			modelAndView.addObject("userScore", customerVO.getScore());
			if ((customerVO.getScore() != null)
					&& (!"0".equals(customerVO.getScore()))
					&& (customerVO.getScore().doubleValue() >= score.intValue())) {
				modelAndView.addObject("flag", Integer.valueOf(0));
			}

			modelAndView.addObject("freight", Double.valueOf(allFreight));
			modelAndView.addObject("supplierFreightStr", supplierFreightStr);
			modelAndView.addObject("amountAll",
					Double.valueOf(amount.doubleValue() + allFreight));
			SalesOrderVO tempOrder = new SalesOrderVO();
			tempOrder.setMainID(getTimeString());
			tempOrder.setExpressFee(Double.valueOf(allFreight));
			tempOrder.setTotalAmount(Double.valueOf(amount.doubleValue()
					+ allFreight));
			modelAndView.addObject("customerVO", customerVO);

			modelAndView.addObject("orderID", tempOrder.getMainID());
		}

		ShippingAddressVO shippingAddressVO = this.shippingAddressService
				.findDefaultShippingAddressOrder();
		modelAndView.addObject("shippingAddressVO", shippingAddressVO);
		modelAndView.addObject("cartID", cartID);
		List provinceList = this.areaService.getAllProvince();
		modelAndView.addObject("provinceList", provinceList);
		if ((provinceList != null) && (provinceList.size() > 0)) {
			List cityList = this.areaService
					.getAreaByParentID(((AreaVO) provinceList.get(0))
							.getMainID());
			modelAndView.addObject("cityList", cityList);
			if ((cityList != null) && (cityList.size() > 0)) {
				List districtList = this.areaService
						.getAreaByParentID(((AreaVO) cityList.get(0))
								.getMainID());
				modelAndView.addObject("districtList", districtList);
			}
		}
		modelAndView.addObject("orderType", Integer.valueOf(0));
		if (StringUtil.isNotEmpty(customerVO.getGradeID())) {
			CustomerGradeVO customerGradeVO = this.customerGradeSerivce
					.findCustomerGradeByMainID(customerVO.getGradeID());
			if ((customerGradeVO != null)
					&& (customerGradeVO.getGradeSet().intValue() == 4)
					&& (amount != null)) {
				Integer shoppingIntegral = Integer.valueOf(Double.valueOf(
						amount.doubleValue()
								* (customerGradeVO.getShoppingIntegral()
										.doubleValue() / 100.0D)).intValue());
				if ((shoppingIntegral != null)
						&& (shoppingIntegral.intValue() > 0)) {
					modelAndView
							.addObject("shoppingIntegral", shoppingIntegral);
				}
			}
		}

		return modelAndView;
	}

	@RequestMapping(value = { "freightCanculateAgainOBJ" }, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ShoppingCartVO freightCanculateAgainOBJ() {
		return new ShoppingCartVO();
	}

	@RequestMapping(value = { "freightCanculateAgain" }, produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public String freightCanculateAgain(String cartID, String productAmount,
			String customerDeliveryAddressId, HttpServletRequest request,
			HttpServletResponse response) {
		List<ShoppingCartVO> list = new ArrayList();
		String[] ids = cartID.split(",");
		double allFreight = 0.0D;
		Double itemFreight;
		for (String id : ids) {
			ShoppingCartVO shoppingCartVO = this.shoppingCartSerivce
					.findShoppingCartByID(Long.valueOf(id));
			itemFreight = freightCanculateAgain(shoppingCartVO,
					customerDeliveryAddressId);
			shoppingCartVO.setFreight(itemFreight.doubleValue());
			allFreight += itemFreight.doubleValue();
			list.add(shoppingCartVO);
		}
		double amountAll = Double.parseDouble(productAmount) + allFreight;

		Object freightMap = new HashMap();

		String supplierFreightStr = "";
		if ((list != null) && (list.size() > 0)) {
			for (ShoppingCartVO shoppingCartVO : list) {
				String sid1 = shoppingCartVO.getSupplierID();
				String siname1 = shoppingCartVO.getSupplierName();

				double supplierFreight = 0.0D;
				for (ShoppingCartVO shoppingCartVO1 : list) {
					String sid2 = shoppingCartVO1.getSupplierID();
					if (sid1.equals(sid2)) {
						supplierFreight += shoppingCartVO1.getFreight();
					}
				}
				((Map) freightMap).put(sid1 + "_" + siname1,
						Double.valueOf(supplierFreight));
			}

			Iterator iterator = ((Map) freightMap).keySet().iterator();
			while (iterator.hasNext()) {
				String supplierKey = (String) iterator.next();
				supplierFreightStr = supplierFreightStr + supplierKey + ":"
						+ ((Map) freightMap).get(supplierKey) + "|";
			}
		}

		return "{\"freight\":" + allFreight + ", \"amountAll\":" + amountAll
				+ ", \"supplierFreightStr\":\"" + supplierFreightStr + "\"}";
	}

	private Double freightCanculate(ShoppingCartVO shoppingCartVO,
			CustomerVO userInfo) {
		double freight = 0.0D;

		FreightTemplateVO freightTemplateVO = this.freightTemplateService
				.findFreightTemplateByProduct(shoppingCartVO.getProductID());
		int PriceType = freightTemplateVO.getPriceType().intValue();
		ItemVO itemVO = this.itemService.findItemById(shoppingCartVO
				.getItemID());

		FreightTemplatePriceVO freightTemplatePriceVO = this.freightTemplatePriceService
				.findFreightTemplatePriceByUserAndProduct(
						shoppingCartVO.getProductID(), userInfo);
		int firstCondition = 0;
		double FirstPrice = 0.0D;
		int addUnit = 0;
		double addPrice = 0.0D;
		if (freightTemplatePriceVO == null) {
			firstCondition = freightTemplateVO.getFirstCondition().intValue();
			FirstPrice = freightTemplateVO.getFirstPrice().doubleValue();
			addUnit = freightTemplateVO.getAddUnit().intValue();
			addPrice = freightTemplateVO.getAddPrice().doubleValue();
		} else {
			firstCondition = freightTemplatePriceVO.getFirstCondition()
					.intValue();
			FirstPrice = freightTemplatePriceVO.getFirstPrice().doubleValue();
			addUnit = freightTemplatePriceVO.getAddUnit().intValue();
			addPrice = freightTemplatePriceVO.getAddPrice().doubleValue();
		}

		if (PriceType == 1) {
			double itemWeight = itemVO.getWeight().doubleValue();
			if (itemWeight <= firstCondition)
				freight = FirstPrice;
			else
				freight = (itemWeight - firstCondition) / addUnit * addPrice;
		} else if (PriceType == 2) {
			int itemCount = shoppingCartVO.getItemCount().intValue();

			freight = FirstPrice + (itemCount - firstCondition) / addUnit
					* addPrice;
		} else if (PriceType == 3) {
			int itemCubage = itemVO.getCubage().intValue();
			if (itemCubage <= firstCondition)
				freight = FirstPrice;
			else {
				freight = (itemCubage - firstCondition) / addUnit * addPrice;
			}
		}
		return Double.valueOf(freight);
	}

	private Double freightCanculateAgain(ShoppingCartVO shoppingCartVO,
			String customerDeliveryAddressId) {
		double freight = 0.0D;

		FreightTemplateVO freightTemplateVO = this.freightTemplateService
				.findFreightTemplateByProduct(shoppingCartVO.getProductID());
		int PriceType = freightTemplateVO.getPriceType().intValue();
		ItemVO itemVO = this.itemService.findItemById(shoppingCartVO
				.getItemID());

		FreightTemplatePriceVO freightTemplatePriceVO = this.freightTemplatePriceService
				.findFreightTemplatePriceByCustomerDeliveryAddressAndProduct(
						shoppingCartVO.getProductID(),
						customerDeliveryAddressId);
		int firstCondition = 0;
		double FirstPrice = 0.0D;
		int addUnit = 0;
		double addPrice = 0.0D;
		if (freightTemplatePriceVO == null) {
			firstCondition = freightTemplateVO.getFirstCondition().intValue();
			FirstPrice = freightTemplateVO.getFirstPrice().doubleValue();
			addUnit = freightTemplateVO.getAddUnit().intValue();
			addPrice = freightTemplateVO.getAddPrice().doubleValue();
		} else {
			firstCondition = freightTemplatePriceVO.getFirstCondition()
					.intValue();
			FirstPrice = freightTemplatePriceVO.getFirstPrice().doubleValue();
			addUnit = freightTemplatePriceVO.getAddUnit().intValue();
			addPrice = freightTemplatePriceVO.getAddPrice().doubleValue();
		}

		if (PriceType == 1) {
			double itemWeight = itemVO.getWeight().doubleValue();
			if (itemWeight <= firstCondition)
				freight = FirstPrice;
			else
				freight = (itemWeight - firstCondition) / addUnit * addPrice;
		} else if (PriceType == 2) {
			int itemCount = shoppingCartVO.getItemCount().intValue();

			freight = FirstPrice + (itemCount - firstCondition) / addUnit
					* addPrice;
		} else if (PriceType == 3) {
			int itemCubage = itemVO.getCubage().intValue();
			if (itemCubage <= firstCondition)
				freight = FirstPrice;
			else {
				freight = (itemCubage - firstCondition) / addUnit * addPrice;
			}
		}
		return Double.valueOf(freight);
	}

	public List<PromotionVO> getPromotionList() {
		PromotionDTO promotionDTO = new PromotionDTO();

		List<PromotionVO> pList = this.promotionService
				.findPromotionList(promotionDTO);
		for (PromotionVO promotionVO : pList) {
			List promotionSetVOList = this.promotionService
					.findPromotionSetByPromotionID(promotionVO.getMainID());
			promotionVO.setPromotionSetVOList(promotionSetVOList);
		}
		return pList;
	}

	@RequestMapping({ "updateDefaultAddress" })
	@ResponseBody
	public JsonResult updateDefaultAddress(Long addressId, String customerId,
			String type) {
		try {
			CustomerDeliveryAddressVO customerDeliveryAddressVO = this.customerAddressService
					.findDefaultAddressByCustomerID(customerId,
							Integer.valueOf(type));
			if (customerDeliveryAddressVO != null) {
				this.customerAddressService
						.editDefaultAddressByID(customerDeliveryAddressVO
								.getId());
			}
			this.customerAddressService.editIsDefaultAddressByID(addressId);
			return JsonResult.create();
		} catch (Exception e) {
		}
		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}

	@RequestMapping({ "getCityByParentID" })
	@ResponseBody
	public JsonResult getCityByParentID(String parentID) {
		try {
			List cityList = this.areaService.getCityByParentID(parentID);
			if ((cityList != null) && (cityList.size() > 0))
				return new JsonResult(cityList);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(ResultCode.ERROR_SYSTEM);
		}
		return JsonResult.create();
	}

	@RequestMapping({ "saveAddress" })
	public ModelAndView saveAddress(String addressID, String name,
			String mobile, String telephone, String provinceId, String cityId,
			String districtId, String address, String zip, String isDefault,
			String type, String cartID, String itemID, String itemCount,
			String itemType, String orderType, String productID, String objID,
			String supplierID, String itemsJson) {
		CustomerVO customerVO = findUserInfo();
		CustomerDeliveryAddressDTO customerDeliveryAddressDTO = new CustomerDeliveryAddressDTO();
		if (StringUtil.isNotEmpty(name)) {
			customerDeliveryAddressDTO.setName(name);
		}
		if (StringUtil.isNotEmpty(mobile)) {
			customerDeliveryAddressDTO.setMobile(mobile);
		}
		if (StringUtil.isNotEmpty(telephone)) {
			customerDeliveryAddressDTO.setTelephone(telephone);
		}
		if (StringUtil.isNotEmpty(provinceId)) {
			customerDeliveryAddressDTO.setProvinceID(provinceId);
		}
		if (StringUtil.isNotEmpty(cityId)) {
			customerDeliveryAddressDTO.setCityID(cityId);
		}
		if (StringUtil.isNotEmpty(districtId)) {
			customerDeliveryAddressDTO.setDisctrictID(districtId);
		}
		if (StringUtil.isNotEmpty(address)) {
			customerDeliveryAddressDTO.setAddress(address);
		}
		if (StringUtil.isNotEmpty(zip)) {
			customerDeliveryAddressDTO.setZip(zip);
		}
		if (StringUtil.isNotEmpty(type)) {
			customerDeliveryAddressDTO.setType(Integer.valueOf(type));
		}
		if (StringUtil.isNotEmpty(isDefault)) {
			customerDeliveryAddressDTO.setIsDefault(Integer.valueOf(isDefault));
			if (isDefault.equals("1")) {
				this.customerAddressService
						.updateAddressNotDefaultByCustomerMainID(customerVO
								.getMainID());
			}

		} else {
			customerDeliveryAddressDTO.setIsDefault(Integer.valueOf(0));
		}
		customerDeliveryAddressDTO.setCustomerID(customerVO.getMainID());
		if (StringUtil.isNotEmpty(addressID)) {
			customerDeliveryAddressDTO.setId(Long.valueOf(addressID));
			this.customerAddressService.editAddress(customerDeliveryAddressDTO);
		} else {
			this.customerAddressService.addAddress(customerDeliveryAddressDTO);
		}
		String url = "";
		if (StringUtil.isNotEmpty(itemID)) {
			url = "redirect:goBuy.htm?cartID=" + itemID + "&number="
					+ itemCount + "&type=" + itemType + "&orderType="
					+ orderType + "&productID=" + productID + "&objID=" + objID
					+ "&supplierID=" + supplierID;
		} else if (StringUtil.isNotEmpty(itemsJson)) {
			itemsJson = itemsJson.replace("{", "(");
			itemsJson = itemsJson.replace("}", ")");
			url = "redirect:goBuys.htm?itemsJson=" + itemsJson;
		} else {
			url = "redirect:shoppingCart2.htm?cartID=" + cartID;
		}
		return new ModelAndView(url);
	}

	@RequestMapping({ "editAddressPage" })
	public ModelAndView editAddressPage(String addressID) {
		ModelAndView modelAndView = newModelAndView();
		modelAndView.setViewName("/screen/order/shoppingcart_address_data");
		CustomerDeliveryAddressVO addressVO = this.customerAddressService
				.findAddressByID(Long.valueOf(addressID));
		modelAndView.addObject("addressVO", addressVO);
		List provinceList = this.areaService.getAllProvince();
		modelAndView.addObject("provinceList", provinceList);
		List cityList = null;
		List districtList = null;
		if (StringUtil.isNotEmpty(addressVO.getProvinceID())) {
			cityList = this.areaService.getAreaByParentID(addressVO
					.getProvinceID());
		} else if ((provinceList != null) && (provinceList.size() > 0)) {
			cityList = this.areaService
					.getAreaByParentID(((AreaVO) provinceList.get(0))
							.getMainID());
		}

		modelAndView.addObject("cityList", cityList);
		if (StringUtil.isNotEmpty(addressVO.getCityID())) {
			districtList = this.areaService.getAreaByParentID(addressVO
					.getCityID());
		} else if ((cityList != null) && (cityList.size() > 0)) {
			districtList = this.areaService
					.getAreaByParentID(((AreaVO) cityList.get(0)).getMainID());
		}

		modelAndView.addObject("districtList", districtList);
		return modelAndView;
	}

	@RequestMapping({ "editStorePage" })
	public ModelAndView editStorePage(String storeID) {
		ModelAndView modelAndView = newModelAndView();
		modelAndView.setViewName("/screen/order/shoppingcart_store_data");
		CustomerDeliveryAddressVO addressVO = this.customerAddressService
				.findAddressByID(Long.valueOf(storeID));
		modelAndView.addObject("addressVO", addressVO);
		List provinceList = this.areaService.getAllProvince();
		modelAndView.addObject("provinceList", provinceList);
		List cityList = null;
		List districtList = null;
		if (StringUtil.isNotEmpty(addressVO.getProvinceID())) {
			cityList = this.areaService.getAreaByParentID(addressVO
					.getProvinceID());
		} else if ((provinceList != null) && (provinceList.size() > 0)) {
			cityList = this.areaService
					.getAreaByParentID(((AreaVO) provinceList.get(0))
							.getMainID());
		}

		modelAndView.addObject("cityList", cityList);
		if (StringUtil.isNotEmpty(addressVO.getCityID())) {
			districtList = this.areaService.getAreaByParentID(addressVO
					.getCityID());
		} else if ((cityList != null) && (cityList.size() > 0)) {
			districtList = this.areaService
					.getAreaByParentID(((AreaVO) cityList.get(0)).getMainID());
		}

		modelAndView.addObject("districtList", districtList);
		return modelAndView;
	}

	@RequestMapping({ "deleteAddress" })
	@ResponseBody
	public JsonResult deleteAddress(Long id) {
		Boolean isSuccess = this.customerAddressService
				.deleteAddressByPrimaryKey(id);
		if (isSuccess.booleanValue()) {
			return JsonResult.create();
		}
		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}

	@RequestMapping({ "goBuy" })
	public ModelAndView goBuy(String cartID, Integer number, String orderType,
			String objID, String productID, String supplierID,
			HttpServletRequest request, HttpServletResponse response) {
		CustomerVO customerVO = findUserInfo();
		PreSaleVO preSaleVO = null;
		FlashSaleVO flashSaleVO = null;
		if (StringUtil.isNotEmpty(objID)) {
			preSaleVO = this.preSaleService.findPreSale(objID);
			flashSaleVO = this.flashSaleService.findFlashSaleByMainID(objID);
		}
		if (customerVO == null) {
			String url = null;
			if (StringUtil.isNotEmpty(objID)) {
				if ((StringUtil.isNotEmpty(cartID))
						&& (StringUtil.isNotEmpty(objID))) {
					if (preSaleVO != null) {
						url = "/mall/item/itemDetail.htm?itemID=" + cartID
								+ "&preID=" + objID;
					}
					if (flashSaleVO != null)
						url = "/mall/item/itemDetail.htm?itemID=" + cartID
								+ "&flashID=" + objID;
				}
			} else if (StringUtil.isNotEmpty(productID))
				url = "/mall/product/productDetail.htm?productID=" + productID
						+ "&itemID=" + cartID;
			else {
				url = "/mall/item/itemDetail.htm?itemID=" + cartID;
			}
			String uuid = getCookieUUID(request, response);
			this.cachedClient.set("before_login_url_" + uuid,
					Constant.EXP.intValue(), url);
			return new ModelAndView("redirect:mall/member/toLogin.htm");
		}
		ModelAndView modelAndView = newModelAndView();
		modelAndView.setViewName("/screen/order/shoppingcart_2");
		List addressList = this.customerAddressService
				.findPassAddressByCustomerID(customerVO.getMainID(), 0);
		modelAndView.addObject("addressList", addressList);

		List<ShoppingCartVO> list = new ArrayList();

		ShoppingCartVO shoppingCartVO = new ShoppingCartVO();
		ItemVO itemVO = this.itemService.findItemById(cartID);

		ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
		shoppingCartDTO.setProductID(itemVO.getProductID());
		shoppingCartDTO.setItemID(cartID);
		shoppingCartDTO.setCustomerID(customerVO.getMainID());
		shoppingCartDTO.setType(Integer.valueOf(1));
		shoppingCartDTO.setIsPromotion(Integer.valueOf(0));
		shoppingCartDTO.setSupplierID(supplierID);

		ShoppingCartVO shoppingCart1 = this.shoppingCartSerivce
				.findShoppingCartByCustomerItem(shoppingCartDTO);
		if (shoppingCart1 != null) {
			shoppingCartDTO.setItemCount(number);
			shoppingCartDTO.setId(shoppingCartVO.getId());
			this.shoppingCartSerivce.updateShoppingCart(shoppingCartDTO);
			shoppingCartVO.setId(shoppingCart1.getId());
		} else {
			System.out.println(shoppingCartDTO.getSupplierID());
			shoppingCartDTO.setItemCount(number);

			this.shoppingCartSerivce.addShoppingCart(shoppingCartDTO);
			shoppingCartVO.setId(shoppingCartDTO.getId());
		}

		List<ItemDetailVO> props = this.itemService
				.findItemDetailByItemID(itemVO.getMainID());
		if ((props != null) && (props.size() > 0)) {
			String itemProp = "";
			for (ItemDetailVO prop : props) {
				itemProp = itemProp + prop.getItemPropName() + ":"
						+ prop.getItemPropValue() + " ";
			}
			shoppingCartVO.setItemProp(itemProp);
		}
		shoppingCartVO.setSupplierID(supplierID);
		shoppingCartVO.setItemID(cartID);
		shoppingCartVO.setTempCartID(cartID);
		shoppingCartVO.setProductID(itemVO.getProductID());
		shoppingCartVO.setItemName(itemVO.getName());
		shoppingCartVO.setItemPicUrl(itemVO.getPicURL());
		shoppingCartVO.setStandrardPrice(itemVO.getStandrardPrice());
		Double salesPrice = Double.valueOf(0.0D);
		if (preSaleVO != null)
			salesPrice = preSaleVO.getSalesPrice();
		else if (flashSaleVO != null)
			salesPrice = flashSaleVO.getSalesPrice();
		else {
			salesPrice = itemVO.getSalesPrice();
		}
		shoppingCartVO.setSalesPrice(salesPrice);
		shoppingCartVO.setItemCount(number);
		shoppingCartVO.setAllamount(Double.valueOf(salesPrice.doubleValue()
				* number.intValue()));
		list.add(shoppingCartVO);

		if ((list != null) && (list.size() > 0)) {
			Map shopCartMap = new HashMap();

			shopCartMap.put("gobuy", list);
			Integer count = Integer.valueOf(0);
			Double amount = Double.valueOf(0.0D);
			Double weight = Double.valueOf(0.0D);
			count = Integer.valueOf(count.intValue()
					+ shoppingCartVO.getItemCount().intValue());

			amount = Double.valueOf(amount.doubleValue()
					+ shoppingCartVO.getItemCount().intValue()
					* shoppingCartVO.getSalesPrice().doubleValue());
			shoppingCartVO.setAllamount(Double.valueOf(shoppingCartVO
					.getItemCount().intValue()
					* shoppingCartVO.getSalesPrice().doubleValue()));
			modelAndView.addObject("shoppingCartList", shopCartMap);
			modelAndView.addObject("count", count);
			modelAndView.addObject("weight", weight);
			modelAndView.addObject("amount", amount);
			modelAndView.addObject("generateType", "quickBuy");
			customerVO = this.memberSerivce.findMemberByMainID(customerVO
					.getMainID());
			modelAndView.addObject("userScore", customerVO.getScore());
			Double freight = freightCanculate(list);
			modelAndView.addObject("freight", freight);

			List<PromotionVO> pList = getPromotionList();
			if ((pList != null) && (pList.size() > 0))
				for (PromotionVO promotionVO : pList) {
					Date endDate = promotionVO.getEndDate();
					if (endDate.getTime() >= new Date().getTime()) {
						SalesOrderVO tempOrder;
						ShippingAddressVO shippingAddressVO;
						List provinceList;
						List cityList;
						List districtList;
						SystemLogDTO systemLogDTO;
						if (promotionVO.getType().intValue() == 1) {
							List<PromotionSetVO> promotionSetVOList = promotionVO
									.getPromotionSetVOList();
							List<ShoppingCartVO> onCatCartList = new ArrayList();
							if ((promotionSetVOList != null)
									&& (promotionSetVOList.size() > 0)) {
								for (ShoppingCartVO shoppingCartVO1 : list) {
									for (PromotionSetVO promotionSetVO : promotionSetVOList) {
										ItemVO item = this.promotionService
												.findItemByItemIDAndCategoryID(
														promotionSetVO
																.getObjID(),
														shoppingCartVO1
																.getItemID());
										if (item != null) {
											onCatCartList.add(shoppingCartVO1);
											break;
										}
									}
								}
							}
							double totalAmount = 0.0D;
							for (ShoppingCartVO shoppingCartVO1 : onCatCartList) {
								totalAmount += shoppingCartVO1.getAllamount()
										.doubleValue();
							}
							if (totalAmount > promotionVO
									.getPromotionCondition().doubleValue()) {
								amount = Double.valueOf(amount.doubleValue()
										- Double.parseDouble(promotionVO
												.getPromotionValue()));
								modelAndView.addObject("freeFee", Double
										.valueOf(Double.parseDouble(promotionVO
												.getPromotionValue())));
								break;
							}
						}
					}
				}
			if (amount != null) {
				modelAndView.addObject(
						"amountAll",
						Double.valueOf(amount.doubleValue()
								+ freight.doubleValue()));
				SalesOrderVO tempOrder = new SalesOrderVO();
				tempOrder.setMainID(getTimeString());
				tempOrder.setExpressFee(freight);
				tempOrder.setTotalAmount(Double.valueOf(amount.doubleValue()
						+ freight.doubleValue()));
				this.cachedClient.add(
						customerVO.getMainID() + tempOrder.getMainID(),
						Constant.EXP.intValue(), tempOrder);
				modelAndView.addObject("orderID", tempOrder.getMainID());
			}
		}
		ShippingAddressVO shippingAddressVO = this.shippingAddressService
				.findDefaultShippingAddressOrder();
		modelAndView.addObject("shippingAddressVO", shippingAddressVO);
		modelAndView.addObject("tempCartID", cartID);
		modelAndView.addObject("itemCount", number);
		modelAndView.addObject("supplierID", supplierID);
		modelAndView.addObject("orderType", orderType);
		modelAndView.addObject("objID", objID);
		List<AreaVO> provinceList = this.areaService.getAllProvince();
		modelAndView.addObject("provinceList", provinceList);
		if ((provinceList != null) && (provinceList.size() > 0)) {
			List<AreaVO> cityList = this.areaService
					.getAreaByParentID(((AreaVO) provinceList.get(0))
							.getMainID());
			modelAndView.addObject("cityList", cityList);
			if ((cityList != null) && (cityList.size() > 0)) {
				List<AreaVO> districtList = this.areaService
						.getAreaByParentID(((AreaVO) cityList.get(0))
								.getMainID());
				modelAndView.addObject("districtList", districtList);
			}
		}
		if (StringUtil.isNotEmpty(productID)) {
			modelAndView.addObject("productID", productID);
		}
		modelAndView.addObject("customerVO", customerVO);
		SystemLogDTO systemLogDTO = new SystemLogDTO();
		systemLogDTO.setOperation(request.getRequestURI());
		systemLogDTO.setIP(IPRequest.getIpAddress(request));
		systemLogDTO.setStatus("1");
		systemLogDTO.setUser(customerVO.getName());
		systemLogDTO.setMemo("下单操作");
		this.systemLogService.insertSystemLog(systemLogDTO);
		return modelAndView;
	}

	@RequestMapping({ "goBuys" })
	public ModelAndView goBuys(HttpServletRequest request,
			HttpServletResponse response) {
		String str = null;
		PreSaleVO preSaleVO = null;
		FlashSaleVO flashSaleVO = null;
		try {
			str = URLDecoder.decode(request.getParameter("itemsJson"), "UTF-8");
			str = str.replace("(", "{");
			str = str.replace(")", "}");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		JSONObject jb = new JSONObject();

		JSONArray array = (JSONArray) JSONObject.fromObject(str).get("menu");
		CustomerVO customerVO = findUserInfo();
		if (customerVO == null) {
			String url = "/mall/product/productList.htm";
			String uuid = getCookieUUID(request, response);
			this.cachedClient.set("before_login_url_" + uuid,
					Constant.EXP.intValue(), url);
			return new ModelAndView("redirect:mall/member/toLogin.htm");
		}
		ModelAndView modelAndView = newModelAndView();
		try {
			modelAndView.setViewName("/screen/order/shoppingcart_2");
			List addressList = this.customerAddressService
					.findPassAddressByCustomerID(customerVO.getMainID(), 0);
			modelAndView.addObject("addressList", addressList);
			List<ShoppingCartVO> list = new ArrayList();
			String objID;
			Double salesPrice;
			for (int i = 0; i < array.size(); i++) {
				JSONObject o = (JSONObject) array.get(i);
				String cartID = o.get("itemID").toString();
				String supplierID = o.get("supplierID").toString();
				Integer number = Integer.valueOf(Integer.parseInt(o.get(
						"itemCount").toString()));
				String orderType = null;
				objID = null;
				String productID = o.get("productID").toString();
				ShoppingCartVO shoppingCartVO = new ShoppingCartVO();
				ItemVO itemVO = this.itemService
						.findItemSupplieredByItemMainIDAndSupplierID(cartID,
								supplierID);
				List<ItemDetailVO> props = this.itemService
						.findItemDetailByItemID(itemVO.getMainID());
				if ((props != null) && (props.size() > 0)) {
					String itemProp = "";
					for (ItemDetailVO prop : props) {
						itemProp = itemProp + prop.getItemPropName() + ":"
								+ prop.getItemPropValue() + " ";
					}
					shoppingCartVO.setItemProp(itemProp);
				}
				shoppingCartVO.setTempCartID(cartID);
				shoppingCartVO.setItemID(cartID);
				shoppingCartVO.setProductID(itemVO.getProductID());
				shoppingCartVO.setItemName(itemVO.getName());
				shoppingCartVO.setItemPicUrl(itemVO.getPicURL());
				shoppingCartVO.setStandrardPrice(itemVO.getStandrardPrice());
				shoppingCartVO.setAllamount(Double.valueOf(itemVO
						.getSalesPrice().doubleValue() * number.intValue()));
				shoppingCartVO.setSupplierID(supplierID);
				shoppingCartVO.setType(Integer.valueOf(1));
				SupplierVO supplier = this.supplierService
						.findSupplierByID(supplierID);
				shoppingCartVO.setSupplierName(supplier.getCompanyName());
				salesPrice = Double.valueOf(0.0D);
				if (preSaleVO != null)
					salesPrice = preSaleVO.getSalesPrice();
				else if (flashSaleVO != null)
					salesPrice = flashSaleVO.getSalesPrice();
				else {
					salesPrice = itemVO.getSalesPrice();
				}
				shoppingCartVO.setSalesPrice(salesPrice);
				shoppingCartVO.setItemCount(number);
				list.add(shoppingCartVO);
			}

			Integer count = Integer.valueOf(0);
			Double amount = Double.valueOf(0.0D);
			Integer score = Integer.valueOf(0);
			Double weight = Double.valueOf(0.0D);

			if ((list != null) && (list.size() > 0)) {
				Map shopCartMap = new HashMap();
				for (ShoppingCartVO shoppingCartVO : list) {
					ItemVO itemVO = this.itemService
							.findItemById(shoppingCartVO.getItemID());
					count = Integer.valueOf(count.intValue()
							+ shoppingCartVO.getItemCount().intValue());
					amount = Double.valueOf(amount.doubleValue()
							+ shoppingCartVO.getSalesPrice().doubleValue()
							* shoppingCartVO.getItemCount().intValue());

					List temp = new ArrayList();
					String sid1 = shoppingCartVO.getSupplierID();
					String siname1 = shoppingCartVO.getSupplierName();
					for (ShoppingCartVO shoppingCartVO2 : list) {
						String sid2 = shoppingCartVO2.getSupplierID();
						if (sid1.equals(sid2)) {
							temp.add(shoppingCartVO2);
						}
					}
					shopCartMap.put(sid1 + "_" + siname1, temp);
				}
				this.cachedClient.add(findUserInfo().getMainID(),
						Constant.EXP.intValue(), list);
				modelAndView.addObject("shoppingCartList", shopCartMap);
				modelAndView.addObject("generateType", "oneKey");
				modelAndView.addObject("count", count);
				modelAndView.addObject("weight", weight);
				modelAndView.addObject("amount", amount);
				modelAndView.addObject("score", score);
				customerVO = this.memberSerivce.findMemberByMainID(customerVO
						.getMainID());
				modelAndView.addObject("userScore", customerVO.getScore());
				if ((customerVO.getScore() != null)
						&& (!"0".equals(customerVO.getScore()))
						&& (customerVO.getScore().doubleValue() >= score
								.intValue())) {
					modelAndView.addObject("flag", Integer.valueOf(0));
				}

				Double freight = freightCanculate(list);
				modelAndView.addObject("freight", freight);
				modelAndView.addObject(
						"amountAll",
						Double.valueOf(amount.doubleValue()
								+ freight.doubleValue()));
				SalesOrderVO tempOrder = new SalesOrderVO();
				tempOrder.setMainID(getTimeString());
				tempOrder.setExpressFee(freight);
				tempOrder.setTotalAmount(Double.valueOf(amount.doubleValue()
						+ freight.doubleValue()));
				this.cachedClient.add(
						customerVO.getMainID() + tempOrder.getMainID(),
						Constant.EXP.intValue(), tempOrder);
				modelAndView.addObject("orderID", tempOrder.getMainID());
			}
			ShippingAddressVO shippingAddressVO = this.shippingAddressService
					.findDefaultShippingAddressOrder();
			modelAndView.addObject("shippingAddressVO", shippingAddressVO);
			modelAndView.addObject("itemsJson", str);

			List provinceList = this.areaService.getAllProvince();
			modelAndView.addObject("provinceList", provinceList);
			if ((provinceList != null) && (provinceList.size() > 0)) {
				List cityList = this.areaService
						.getAreaByParentID(((AreaVO) provinceList.get(0))
								.getMainID());
				modelAndView.addObject("cityList", cityList);
				if ((cityList != null) && (cityList.size() > 0)) {
					List districtList = this.areaService
							.getAreaByParentID(((AreaVO) cityList.get(0))
									.getMainID());
					modelAndView.addObject("districtList", districtList);
				}
			}
			modelAndView.addObject("orderType", Integer.valueOf(0));
			if (StringUtil.isNotEmpty(customerVO.getGradeID())) {
				CustomerGradeVO customerGradeVO = this.customerGradeSerivce
						.findCustomerGradeByMainID(customerVO.getGradeID());
				if ((customerGradeVO != null)
						&& (customerGradeVO.getGradeSet().intValue() == 4)
						&& (amount != null)) {
					Integer shoppingIntegral = Integer.valueOf(Double.valueOf(
							amount.doubleValue()
									* (customerGradeVO.getShoppingIntegral()
											.doubleValue() / 100.0D))
							.intValue());
					if ((shoppingIntegral != null)
							&& (shoppingIntegral.intValue() > 0))
						modelAndView.addObject("shoppingIntegral",
								shoppingIntegral);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			return modelAndView;
		}
	}

	private Double freightCanculate(List<ShoppingCartVO> list) {
		List<PromotionVO> pList = getPromotionList();
		Double freight = Double.valueOf(10.0D);
		if ((pList != null) && (pList.size() > 0))
			for (PromotionVO promotionVO : pList) {
				Date endDate = promotionVO.getEndDate();
				if (endDate.getTime() >= new Date().getTime()) {
					if (promotionVO.getType().intValue() == 3) {
						List<PromotionSetVO> promotionSetVOList = promotionVO
								.getPromotionSetVOList();
						List<ShoppingCartVO> onCatCartList = new ArrayList();
						if ((promotionSetVOList != null)
								&& (promotionSetVOList.size() > 0)) {
							for (ShoppingCartVO shoppingCartVO : list) {
								for (PromotionSetVO promotionSetVO : promotionSetVOList) {
									ItemVO item = this.promotionService
											.findItemByItemIDAndCategoryID(
													promotionSetVO.getObjID(),
													shoppingCartVO.getItemID());
									if (item != null) {
										onCatCartList.add(shoppingCartVO);
										break;
									}
								}
							}
						}
						double totalAmount = 0.0D;
						for (ShoppingCartVO shoppingCartVO : onCatCartList) {
							totalAmount += shoppingCartVO.getAllamount()
									.doubleValue();
						}
						if (totalAmount > promotionVO.getPromotionCondition()
								.intValue()) {
							freight = Double.valueOf(0.0D);
							break;
						}
					}
				}
			}
		return freight;
	}

	@RequestMapping({ "saveOrder" })
	public ModelAndView saveOrder(String cartIDs, String addressID,
			String paymentType, String orderAmount, String scoreAll,
			String memo, String itemCount, String itemID, String supplierID,
			String itemType, String orderType, String objID,
			String invoiceType, String invoiceTitle, String generateType,
			String tempOrderID, String allFreight, String supplierFreightStr,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if ((StringUtil.isNotEmpty(memo))
				&& (memo.equals(new String(memo.getBytes("iso-8859-1"),
						"iso-8859-1")))) {
			memo = new String(memo.getBytes("iso-8859-1"), "utf-8");
		}
		if ((StringUtil.isNotEmpty(invoiceTitle))
				&& (invoiceTitle.equals(new String(invoiceTitle
						.getBytes("iso-8859-1"), "iso-8859-1")))) {
			invoiceTitle = new String(invoiceTitle.getBytes("iso-8859-1"),
					"utf-8");
		}
		CustomerVO customerVO = findUserInfo();
		if (customerVO == null) {
			String uuid = getCookieUUID(request, response);
			this.cachedClient.set("before_login_url_" + uuid,
					Constant.EXP.intValue(), "/index.htm");
			return new ModelAndView("redirect:mall/member/toLogin.htm");
		}
		ModelAndView modelAndView = newModelAndView();
		modelAndView.setViewName("/screen/order/shoppingcart_3");
		StringBuffer ordersList = this.salesOrderService.addSalesOrder(
				customerVO.getMainID(), cartIDs, addressID, paymentType,
				orderAmount, scoreAll, memo, itemCount, itemID, supplierID,
				itemType, orderType, objID, invoiceType, invoiceTitle,
				generateType, tempOrderID, allFreight, supplierFreightStr);
		List list = this.shoppingCartSerivce.findShoppingCart(customerVO
				.getMainID());
		this.cachedClient.set("shopping_cart_count_" + customerVO.getMainID(),
				Constant.EXP.intValue(), Integer.valueOf(list.size()));
		return new ModelAndView("redirect:shoppingCart3.htm?ordersList="
				+ ordersList);
	}

	@RequestMapping({ "shoppingCart3" })
	public ModelAndView shoppingCart3(StringBuffer ordersList,
			HttpServletRequest request, HttpServletResponse response) {
		CustomerVO customerVO = findUserInfo();
		if (customerVO == null) {
			String uuid = getCookieUUID(request, response);
			this.cachedClient.set("before_login_url_" + uuid,
					Constant.EXP.intValue(), "/index.htm");
			return new ModelAndView("redirect:mall/member/toLogin.htm");
		}
		ModelAndView modelAndView = newModelAndView();
		modelAndView.setViewName("/screen/order/shoppingcart_3");
		List salesorderList = new ArrayList();
		List salesorderDList = new ArrayList();
		String[] ordersLists = ordersList.toString().split(",");
		for (int i = 0; i < ordersLists.length; i++) {
			SalesOrderDeliveryAddressVO salesOrderDeliveryAddressVO = this.salesOrderDeliveryAddressService
					.findSalesOrderDeliveryAddressByID(ordersLists[i]);
			SalesOrderVO salesOrderVO = this.salesOrderService
					.findSalesOrderByMainID(ordersLists[i]);
			salesorderList.add(salesOrderVO);
			salesorderDList.add(salesOrderDeliveryAddressVO);
		}
		modelAndView.addObject("salesorderDList", salesorderDList);
		modelAndView.addObject("salesorderList", salesorderList);
		return modelAndView;
	}

	@RequestMapping({ "shoppingCart4" })
	public ModelAndView shoppingCart4(String orderID,
			HttpServletRequest request, HttpServletResponse response) {
		CustomerVO customerVO = findUserInfo();
		if (customerVO == null) {
			String uuid = getCookieUUID(request, response);
			this.cachedClient.set("before_login_url_" + uuid,
					Constant.EXP.intValue(), "/index.htm");
			return new ModelAndView("redirect:mall/member/toLogin.htm");
		}
		ModelAndView modelAndView = newModelAndView();
		modelAndView.setViewName("/screen/order/shoppingcart_4");
		SalesOrderDeliveryAddressVO salesOrderDeliveryAddressVO = this.salesOrderDeliveryAddressService
				.findSalesOrderDeliveryAddressByID(orderID);
		modelAndView.addObject("salesOrderDeliveryAddressVO",
				salesOrderDeliveryAddressVO);
		SalesOrderVO salesOrderVO = this.salesOrderService
				.findSalesOrderByMainID(orderID);
		modelAndView.addObject("salesOrderVO", salesOrderVO);
		return modelAndView;
	}
}