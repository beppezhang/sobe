package org.tempuri;
import java.rmi.RemoteException;
import localhost.services.salesOrderTrans.TransWSProxy;

/**
 * 使用serviceClient进行xml定制发送 User: Francis.Hu Date: 13-12-4 Time: 下午1:41
 */
public class BaseUtil {
	public static void main(String[] args) {
		String xmlStr= "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
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
			+ "</SalesOrderTransVOs> ";
		String str="<?xml version=\"1.0\" encoding=\"utf-8\"?>"
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
				+ "<content>22</content>"
		        + "<remark>123</remark>"
		    + "</SalesOrderTransVO>"
		+ "</SalesOrderTransVOs>";
		TransWSProxy ts = new TransWSProxy();
		try {
			System.out.println(ts.salesOrderTransSave(str));
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}
	}

}