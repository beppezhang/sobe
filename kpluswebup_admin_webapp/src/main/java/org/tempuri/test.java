package org.tempuri;

import java.rmi.RemoteException;

import com._9niuw.www.services.salesOrderTrans.TransWSProxy;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*IService1Proxy s = new IService1Proxy();
		try {
			System.out.println(s.getExpressNo("主单号"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		TransWSProxy ts = new TransWSProxy();
		String xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
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
		String str = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ " <SalesOrderTransVOs>"
				+ "  <SalesOrderTransVO>"
				+ "   <tmsServiceCode>571001</tmsServiceCode>"
				+ "  <operator>张三</operator>"
				+ "  <operatorDate>2015/5/10 0:00:00</operatorDate>"
				+ "  <orderCode>100001</orderCode>"
				+ " <orderNo>100001</orderNo>"
				+ "  <status>收件</status>"
				+ " <scanSite>杭州点</scanSite>"
				+ "  <remark>123</remark>"
				+ " </SalesOrderTransVO>"
				+ "  <SalesOrderTransVO>"
				+ "  <tmsServiceCode>571001</tmsServiceCode>"
				+ "  <operator>122</operator>"
				+ " <operatorDate>2015/5/10 0:00:00</operatorDate>"
				+ "  <orderCode>100002</orderCode>"
				+ "  <orderNo>100002</orderNo>"
				+ "  <status>签收</status>"
				+ "  <scanSite>111</scanSite>"
				+ "  <ctrName>111</ctrName>"
				+ "  <content>11</content>"
				+ "  <remark>11</remark>"
				+ " </SalesOrderTransVO>"
				+ " </SalesOrderTransVOs>";
		try {
			String i = ts.salesOrderTransSave(str);
			System.out.println("数量="+i);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
