package com.kpuswebup.comom.util;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author zhuhuipei 2011-9-19
 */

public class StringUtil {

	private final static String REGEX = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

	public static String StringFilter(String str) throws PatternSyntaxException {
		Pattern pattern = Pattern.compile(REGEX);
		Matcher m = pattern.matcher(str);
		return m.replaceAll("").trim();
	}

	public static double toFormatDouble(String str) {
		try {
			return Double.parseDouble(df.format(Double.parseDouble(str)));
		} catch (Exception e) {
			e.printStackTrace();
			return 0.0;
		}
	}

	public static String delZero(String str) {
		if (StringUtil.isEmpty(str))
			return "";
		char[] ch = str.toCharArray();
		int size = ch.length;
		if (size <= 1)
			return str;
		for (int i = 0; i < size; i++) {
			String s1 = Character.toString(str.charAt(i));
			if ("0".equals(s1)) {
				continue;
			} else {
				return str.substring(i);
			}
		}
		return "";
	}

	public static String toNullString(String str) {
		if (str == null)
			return "";
		return str;
	}

	public static boolean idDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isEq(String str1, String str2) {
		if (str1 == null || str2 == null) {
			return false;
		}
		return str1.equals(str2);
	}

	public static boolean isInteger(String str) {
		try {
			Integer.valueOf(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 将Object类型转化成String类型
	 * 
	 * @author wangxm 2013年11月8日
	 * @param obj
	 * @return
	 */
	public static String objectToString(Object obj) {
		if (obj == null) {
			return null;
		}
		return obj.toString();
	}

	/**
	 * 检验一个String是否为空。
	 * 
	 * @param String
	 *            要校验的字符串。
	 * @return 如果是空，将返回true，否则返回false。
	 */
	public static boolean isEmpty(String str) {
		if ((str == null)
				|| (str.trim().length() <= 0 || "null".equalsIgnoreCase(str))) {
			return true;
		}

		return false;
	}

	/**
	 * 检验一个String是否为空。
	 * 
	 * @param String
	 *            要校验的字符串。
	 * @return 如果是空，将返回false，否则返回true。
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 检验一个String是否数字, 如 .123, 123, -123, -.434, +430. 都是合法的数字.
	 * 
	 * @param 要校验的字符串
	 *            。
	 * @return 如果是一个标准的实数，将返回true，否则返回false。
	 */
	public static boolean isNumberic(String str) {
		if (str == null) {
			return false;
		}

		str = str.trim();

		if (str.length() <= 0) {
			return false;
		}

		char[] ch = str.toCharArray();
		int count = 0;

		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(ch[i])) {
				if ((i == 0) && (ch[i] == '-' || ch[i] == '+')) {
					continue;
				}

				if ((ch[i] == '.') && ((++count) <= 1)) {
					continue;
				}

				return false;
			}
		}

		return true;
	}

	/**
	 * 检验一个String是否是金额, 如 1,200 , .123, 123, -123, -.434, +430. 都是合法的金额.
	 * 
	 * @author chun.fengch
	 * @param 要校验的字符串
	 * @return 如果是一个标准的实数，将返回true，否则返回false。
	 */
	public static boolean isMoney(String money) {
		if (money == null || money.length() <= 0) {
			return false;
		}
		money = money.trim().replaceAll(",", "");

		boolean is = true;
		try {
			new Double(money);
		} catch (NumberFormatException e) {
			is = false;
		}

		return is;
	}

	/**
	 * 将String类型转成Long型
	 * 
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static Long toLong(String str, Long defaultValue) {
		if (isEmpty(str)) {
			return defaultValue;
		}
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	private static final DecimalFormat df = new DecimalFormat("#.##");
	private static final DecimalFormat df2 = new DecimalFormat("#.####");

	/**
	 * 获取文件大小，KB为单位，精确到小数点后两位
	 * 
	 * @param length
	 * @return
	 */
	public static String getFileLength(long length) {
		double d = length / (1024.0);
		String s = df.format(d);
		if ("0".equals(s)) {
			return df2.format(d);
		}
		return s;
	}

	public static String getDoubleFormat(Double d) {
		return df.format(d);
	}

	public static double toDouble(String str) {
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
			e.printStackTrace();
			return 0.0;
		}
	}

	public static boolean isLargerVersion(String version, String comparedVersion) {
		try {
			String[] s1 = version.split("\\.");
			String[] s2 = comparedVersion.split("\\.");
			int length1 = s1.length;
			int length2 = s2.length;
			for (int i = 0; i < length2 && i < length1; i++) {
				if (Integer.parseInt(s1[i]) > Integer.parseInt(s2[i])) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Integer toInteger(String str, Integer def) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			e.printStackTrace();
			return def;
		}
	}

	/**
	 * 生成六位随机数
	 * @date 2014年11月21日
	 * @author wanghehua
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public static String randomSex() {
		String val = ""; // 密码
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) // 字符串
			{
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) // 数字
			{
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}
	
	public  boolean isNull(String str){
		if(str != null && !"".equals(str))
			return false;
		else
			return true;
	}

}
