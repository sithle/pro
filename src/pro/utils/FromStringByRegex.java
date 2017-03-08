package pro.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通过正则表达式筛选出一个字符串中的字符串并返回
 * 
 * @author lc
 * 
 */
public class FromStringByRegex {

	/**
	 * 通过正则表达式筛选出一个字符串中的车牌号码并返回
	 * 
	 * @param str
	 *            被筛选的字符串
	 * @return
	 */
	public static String findCarNumber(String str) {
//		// String input = "鄂AA2262";
//		Pattern pattern;
//		Matcher matcher;
//		// pattern = Pattern.compile("^[\\u4E00-\\u9FA5][\\da-zA-Z]{6}$");
//		pattern = Pattern.compile("[\\u4e00-\\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}");
//		matcher = pattern.matcher(str);
//		while (matcher.find()) {
//			return matcher.group();
//		}
		return str.substring(49, 56);
	}

	public static void main(String[] args) {
		System.out.println(findCarNumber("F:\\picture\\天兴洲大桥_青山主线\\车道1\\20160427-110235-249#车牌(皖KBB55#)号牌颜色(2)车身颜色(0)车型(2)车道(01)速度(000)违法类型(116)全景(1)"));
	}

}
