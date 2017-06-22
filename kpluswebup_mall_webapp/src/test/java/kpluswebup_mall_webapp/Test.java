package kpluswebup_mall_webapp;

public class Test {

	public static void main(String[] args) {
		String str = "{\"menu\":[{\"itemID\":\"0003444319617\",\"itemCount\":\"1\","
				+ "\"customerID\":\"15c17e37d7a64fb4a6aac37d9e6d1015\","
				+ "\"productID\":\"0002133891884\",\"stock\":\"44\","
				+ "\"supplierID\":\"mks111111111\"},{\"itemID\":\"0003713924036\","
				+ "\"itemCount\":\"1\",\"customerID\":\"15c17e37d7a64fb4a6aac37d9e6d1015\","
				+ "\"productID\":\"0002346006014\",\"stock\":\"95\",\"supplierID\":"
				+ "\"mks212122123\"},{\"itemID\":\"0003758637031\",\"itemCount\":\"1\","
				+ "\"customerID\":\"15c17e37d7a64fb4a6aac37d9e6d1015\",\"productID\":"
				+ "\"0002465224016\",\"stock\":\"993\",\"supplierID\":"
				+ "\"d05bd6b2a8e84bd68e5b17e4687ab928\"}]}";
		str = str.replace("{", "(");
		str = str.replace("}", ")");
		System.out.println(str);

	}

}
