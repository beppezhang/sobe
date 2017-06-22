package com.kpluswebup.mall;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.kpluswebup.web.vo.SalesOrderTransVO;

import java.io.IOException;
import java.io.StringReader;

import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

public class XMLUtil {
	public static List<SalesOrderTransVO> xmlToList(File file) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			// 通过构造器的parse()方法，将一个File对象生成相应的Document文档
			Document document = db.parse(file);
			// 下面拿到根
			Element root = document.getDocumentElement();
			NodeList nodeList = root.getChildNodes();
			List<SalesOrderTransVO> listVo = new ArrayList<SalesOrderTransVO>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if ("SalesOrderTransVO".equals(node.getNodeName())) {
					NodeList childNodeList = node.getChildNodes();
					SalesOrderTransVO vo = new SalesOrderTransVO();
					for (int j = 0; j < childNodeList.getLength(); j++) {
						Node childNode = childNodeList.item(j);
						if (!"#text".equals(childNode.getNodeName())) {
							if ("operator".equals(childNode.getNodeName())) {
								vo.setOperators(childNode.getTextContent());
							} else if ("operatorDate".equals(childNode
									.getNodeName())) {
								vo.setOperatorDate(childNode.getTextContent());
							} else if ("orderCode".equals(childNode
									.getNodeName())) {
								vo.setOrderCode(childNode.getTextContent());
							} else if ("orderNo"
									.equals(childNode.getNodeName())) {
								vo.setOrderNo(childNode.getTextContent());
							} else if ("status".equals(childNode.getNodeName())) {
								vo.setStatus(childNode.getTextContent());
							} else if ("scanSite".equals(childNode
									.getNodeName())) {
								vo.setScanSite(childNode.getTextContent());
							} else if ("station"
									.equals(childNode.getNodeName())) {
								vo.setStation(childNode.getTextContent());
							} else if ("ctrName"
									.equals(childNode.getNodeName())) {
								vo.setCtrName(childNode.getTextContent());
							} else if ("content"
									.equals(childNode.getNodeName())) {
								vo.setContent(childNode.getTextContent());
							} else if ("remark".equals(childNode.getNodeName())) {
								vo.setRemark(childNode.getTextContent());
							} else if ("tmsServiceCode".equals(childNode
									.getNodeName())) {
								vo.setTmsServiceCode(childNode.getTextContent());
							}
						}
					}
					listVo.add(vo);
				}
			}
			return listVo;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static List<SalesOrderTransVO> xmlElements(String xmlDoc) {
		// 创建一个新的字符串
		StringReader read = new StringReader(xmlDoc);
		// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		InputSource source = new InputSource(read);
		// 创建一个新的SAXBuilder
		SAXBuilder sb = new SAXBuilder();
		try {
			// 通过输入源构造一个Document
			org.jdom.Document doc = sb.build(source);
			// 取的根元素
			org.jdom.Element root = doc.getRootElement();
			// 得到根元素所有子元素的集合
			List jiedian = root.getChildren();
			// 获得XML中的命名空间（XML中未定义可不写）
			Namespace ns = root.getNamespace();
			org.jdom.Element et = null;
			List<SalesOrderTransVO> listVo = new ArrayList<SalesOrderTransVO>();
			for (int i = 0; i < jiedian.size(); i++) {
				SalesOrderTransVO vo = new SalesOrderTransVO();
				et = (org.jdom.Element) jiedian.get(i);// 循环依次得到子元素
				vo.setTmsServiceCode(et.getChild("tmsServiceCode", ns)
						.getText());
				vo.setOperators(et.getChild("operator", ns).getText());
				vo.setOperatorDate(et.getChild("operatorDate", ns).getText());
				vo.setOrderCode(et.getChild("orderCode", ns).getText());
				vo.setOrderNo(et.getChild("orderNo", ns).getText());
				vo.setStatus(et.getChild("status", ns).getText());
				vo.setScanSite(et.getChild("scanSite", ns).getText());
				vo.setStation(et.getChild("station", ns).getText());
				vo.setCtrName(et.getChild("ctrName", ns).getText());
				vo.setContent(et.getChild("content", ns).getText());
				vo.setRemark(et.getChild("remark", ns).getText());
				listVo.add(vo);
			}

			System.out.println(listVo.size());
			return listVo;
		} catch (JDOMException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		XMLUtil doc = new XMLUtil();
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
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
		doc.xmlElements(xml);
	}
}
