package com.kpluswebup.wap.web.order.control;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.kpluswebup.mall.XMLUtil;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.service.impl.SalesOrderServiceImpl;
public class TransWS implements ApplicationContextAware{
	private static ApplicationContext context = null;
	public TransWS(){
		
	}
	public String salesOrderTransSave(String xmlStr) {
		 String xmlStrDemo= "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<SalesOrderTransVOs>"
					+ "<SalesOrderTransVO>"
						+ "<tmsServiceCode>571001</tmsServiceCode>"
						+ "<operator>张三</operator>"
						+ "<operatorDate>2015/5/10 0:00:00</operatorDate>"
						+ "<orderCode>100001</orderCode>" 
						+ "<orderNo>100001</orderNo>"
						+ "<status>收件</status>" 
						+ "<scanSite>杭州点</scanSite>"
						+ "<station>33</station>"
						+ "<ctrName>441</ctrName>"
						+ " <content>22</content>"
						+ "<remark>123</remark>" 
					+ "</SalesOrderTransVO>"
					+ "<SalesOrderTransVO>"
						+ " <tmsServiceCode>571001</tmsServiceCode>"
						+ " <operator>222</operator>"
						+ " <operatorDate>2015/5/10 0:00:00</operatorDate>"
						+ " <orderCode>100004</orderCode>"
						+ " <orderNo>100004</orderNo>"
						+ " <status>到件卸车</status>"
						+ "<scanSite>222</scanSite>"
						+ " <station>22</station>"
						+ " <ctrName>111</ctrName>"
						+ " <content>22</content>"
						+ " <remark>22</remark>" 
					+ "</SalesOrderTransVO> "
				+ "</SalesOrderTransVOs> ";
		 SalesOrderService ss = context.getBean(SalesOrderServiceImpl.class);
		return ss.salesOrderTransSave(XMLUtil.xmlElements(xmlStr));
	}
	 public static void main(String[] args){
		 TransWS ws = new TransWS();
		 ws.salesOrderTransSave("");
	 }
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;		
	}
}
