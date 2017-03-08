package pro.utils;

import java.util.List;

/**
 * 与pro有关的工具类
 * 
 * @author lc
 *
 */
public class ProUtils {
	/**
	 * 判断值是不是等于255. 如果是，返回true; 反之，返回false.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isStartWithFF(Integer integer) {
		return integer == 255;
	}

	/**
	 * 判断CRC校正对与错. 如果对，返回true; 反之，返回false.
	 * 
	 * @param list
	 * @return
	 */
	public static boolean isCRCCorrect(List<Integer> list) {
		int c, treat, bcrc;
		int wcrc = 0;
		for (int i = 0; i < list.size() - 3; i++) {
			c = list.get(i) & 0xff; // 把list.get(i)的值转成对应的ASCII码的字符
			for (int j = 0; j < 8; j++) {
				treat = c & 0x80;
				c <<= 1;
				c = c & 0xff;
				bcrc = ((wcrc >> 8) & 0x80) & 0xff;
				wcrc <<= 1;
				wcrc = wcrc & 0xffff;
				if ((treat ^ bcrc) != 0)
					wcrc ^= 0x1021;
			}
		}
		int generate_crc = wcrc;
		// System.out.println("generate_crc：" + generate_crc);
		int size = list.size();
		int recv_crc = ((list.get(size - 3) / 16 * 10 + list.get(size - 3) % 16)
				* 10000
				+ (list.get(size - 2) / 16 * 10 + list.get(size - 2) % 16)
				* 100 + list.get(size - 1) / 16 * 10 + list.get(size - 1) % 16);
		// System.out.println("recv_crc：" + recv_crc);

		if (generate_crc == recv_crc) {
			// CRC校验成功
			return true;
		} else {
			// CRC校验失败
			return false;
		}
	}

	/**
	 * 从listData中获得车道
	 * 
	 * @param list
	 * @return
	 */
	public static int getCarLane(List<Integer> list) {
		int lane = list.get(1) / 16 + 1;
		return lane;
	}

	/**
	 * 从listData中获得总轴重
	 * 
	 * @param list
	 * @return
	 */
	public static float getCarWeight(List<Integer> list) {
		float weight = 0;
		int index = 18;
		for (int i = 0; i < getCarAxis(list); i++) {
			weight += list.get(index) * 16 * 16 * 16 * 16 + list.get(index + 1)
					* 16 * 16 + list.get(index + 2);
			index += 3;
		}
		
		// 使得显示的车重小数点后只有一位
		weight /= 1000;
		String temp = String.format("%.1f", weight);
		weight = Float.valueOf(temp);
		
		return weight;
	}

	/**
	 * 从listData中获得总轴数
	 * 
	 * @param list
	 * @return
	 */
	public static int getCarAxis(List<Integer> list) {
		return (list.size() - 18) / 9;
	}

	/**
	 * 把"4/21/2016 11:35:00"格式的时间字符串改成"2016-4-21 11:35:00"
	 * 
	 * @param str
	 * @return
	 */
	public static String changeDateTimeStyle(String str) {
		String datetime = "";
		if (!str.isEmpty()) {
			String s[] = str.split(" ");
			String ss[] = s[0].split("/");
			datetime = ss[2] + "-" + ss[0] + "-" + ss[1] + " " + s[1];
		}
		return datetime;
	}

}
