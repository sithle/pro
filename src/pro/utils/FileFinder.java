package pro.utils;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 文件搜索器
 * 
 * @author lc
 * 
 */
public class FileFinder {

	/**
	 * 查找图片
	 * 
	 * @param baseDirName
	 *            待查找的目录
	 * @param currentTime
	 *            当前系统时间
	 * @param lane
	 *            车道
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public static String findPhoto(String baseDirName, Timestamp currentTime) {
		System.out.println("正在 " + baseDirName + " 路径下找图片...");
		// 照片含通配符的名称
		String photoName = currentTime.toString().substring(0, 19)
				.replace("-", "").replace(" ", "-").replace(":", "")
				+ "*.jpg";
		System.out.println(photoName);
		String photoName_1 = (new Timestamp(currentTime.getTime() - 1000))
				.toString().substring(0, 19).replace("-", "").replace(" ", "-")
				.replace(":", "")
				+ "*.jpg";
		String photoName_2 = (new Timestamp(currentTime.getTime() - 2000))
				.toString().substring(0, 19).replace("-", "").replace(" ", "-")
				.replace(":", "")
				+ "*.jpg";
		String photoName_3 = (new Timestamp(currentTime.getTime() - 3000))
				.toString().substring(0, 19).replace("-", "").replace(" ", "-")
				.replace(":", "")
				+ "*.jpg";
		String photoName_4 = (new Timestamp(currentTime.getTime() - 4000))
				.toString().substring(0, 19).replace("-", "").replace(" ", "-")
				.replace(":", "")
				+ "*.jpg";
		String photoName_5 = (new Timestamp(currentTime.getTime() - 5000))
				.toString().substring(0, 19).replace("-", "").replace(" ", "-")
				.replace(":", "")
				+ "*.jpg";
		String photoName_6 = (new Timestamp(currentTime.getTime() - 6000))
				.toString().substring(0, 19).replace("-", "").replace(" ", "-")
				.replace(":", "")
				+ "*.jpg";
		String photoName1 = (new Timestamp(currentTime.getTime() + 1000))
				.toString().substring(0, 19).replace("-", "").replace(" ", "-")
				.replace(":", "")
				+ "*.jpg";
		String photoName2 = (new Timestamp(currentTime.getTime() + 2000))
				.toString().substring(0, 19).replace("-", "").replace(" ", "-")
				.replace(":", "")
				+ "*.jpg";
		String photoName3 = (new Timestamp(currentTime.getTime() + 3000))
				.toString().substring(0, 19).replace("-", "").replace(" ", "-")
				.replace(":", "")
				+ "*.jpg";
		String photoName4 = (new Timestamp(currentTime.getTime() + 4000))
				.toString().substring(0, 19).replace("-", "").replace(" ", "-")
				.replace(":", "")
				+ "*.jpg";
		String photoName5 = (new Timestamp(currentTime.getTime() + 5000))
				.toString().substring(0, 19).replace("-", "").replace(" ", "-")
				.replace(":", "")
				+ "*.jpg";
		String photoName6 = (new Timestamp(currentTime.getTime() + 6000))
				.toString().substring(0, 19).replace("-", "").replace(" ", "-")
				.replace(":", "")
				+ "*.jpg";

		List photoList = new ArrayList();
		if ((photoList = FileFinder.findFiles(baseDirName, photoName, 0))
				.size() == 1) {
			String photo = photoList.get(0).toString();
			if (!isBlueCarNum(photo)) {
				return photo;
			}
		}
		if ((photoList = FileFinder.findFiles(baseDirName, photoName_1, 0))
				.size() == 1) {
			String photo = photoList.get(0).toString();
			if (!isBlueCarNum(photo)) {
				return photo;
			}
		}
		if ((photoList = FileFinder.findFiles(baseDirName, photoName1, 0))
				.size() == 1) {
			String photo = photoList.get(0).toString();
			if (!isBlueCarNum(photo)) {
				return photo;
			}
		}
		if ((photoList = FileFinder.findFiles(baseDirName, photoName_2, 0))
				.size() == 1) {
			String photo = photoList.get(0).toString();
			if (!isBlueCarNum(photo)) {
				return photo;
			}
		}
		if ((photoList = FileFinder.findFiles(baseDirName, photoName2, 0))
				.size() == 1) {
			String photo = photoList.get(0).toString();
			if (!isBlueCarNum(photo)) {
				return photo;
			}
		}
		if ((photoList = FileFinder.findFiles(baseDirName, photoName_3, 0))
				.size() == 1) {
			String photo = photoList.get(0).toString();
			if (!isBlueCarNum(photo)) {
				return photo;
			}
		}
		if ((photoList = FileFinder.findFiles(baseDirName, photoName3, 0))
				.size() == 1) {
			String photo = photoList.get(0).toString();
			if (!isBlueCarNum(photo)) {
				return photo;
			}
		}
		if ((photoList = FileFinder.findFiles(baseDirName, photoName_4, 0))
				.size() == 1) {
			String photo = photoList.get(0).toString();
			if (!isBlueCarNum(photo)) {
				return photo;
			}
		}
		if ((photoList = FileFinder.findFiles(baseDirName, photoName4, 0))
				.size() == 1) {
			String photo = photoList.get(0).toString();
			if (!isBlueCarNum(photo)) {
				return photo;
			}
		}
		if ((photoList = FileFinder.findFiles(baseDirName, photoName_5, 0))
				.size() == 1) {
			String photo = photoList.get(0).toString();
			if (!isBlueCarNum(photo)) {
				return photo;
			}
		}
		if ((photoList = FileFinder.findFiles(baseDirName, photoName5, 0))
				.size() == 1) {
			String photo = photoList.get(0).toString();
			if (!isBlueCarNum(photo)) {
				return photo;
			}
		}
		if ((photoList = FileFinder.findFiles(baseDirName, photoName_6, 0))
				.size() == 1) {
			String photo = photoList.get(0).toString();
			if (!isBlueCarNum(photo)) {
				return photo;
			}
		}
		if ((photoList = FileFinder.findFiles(baseDirName, photoName6, 0))
				.size() == 1) {
			String photo = photoList.get(0).toString();
			if (!isBlueCarNum(photo)) {
				return photo;
			}
		}
		return null;
	}

	/**
	 * 判断找到的图片是否是非蓝车牌,是蓝返回true
	 * 
	 * @param path
	 *            图片的路径
	 * @return
	 */
	public static boolean isBlueCarNum(String path) {
		int index = path.indexOf("号牌颜色");
		char n = path.charAt(index + 5);
		if (n == '3') {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 查找文件
	 * 
	 * @param baseDirName
	 *            待查找的目录
	 * @param targetFileName
	 *            目标文件名，支持通配符形式
	 * @param count
	 *            期待结果数目，为0表示查找全部
	 * @return 满足查询条件的文件名列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List findFiles(String baseDirName, String targetFileName,
			int count) {
		/**
		 * 算法简述: 从某个给定的待查找的文件夹出发，搜索该文件夹下的所有文件和子文件夹
		 * 如果是文件，则进行匹配，匹配成功则加入结果集，如果是文件夹，则进入队列 队列不空，继续上述操作，队列为空，程序结束，返回结果
		 */

		List fileList = new ArrayList();
		// 判断目录是否存在
		File baseDir = new File(baseDirName);
		if (!baseDir.exists() || !baseDir.isDirectory()) {
			System.out.println("查找文件失败：" + baseDirName + "不是一个目录!");
			return fileList;
		}
		String tempName = null;
		Queue queue = new LinkedList();
		queue.offer(baseDir);
		File tempFile = null;
		while (!queue.isEmpty()) {
			tempFile = (File) queue.poll();
			if (tempFile.exists() && tempFile.isDirectory()) {
				File[] files = tempFile.listFiles();
				for (int i = 0; i < files.length; i++) {
					// 如果是目录则放进队列
					if (files[i].isDirectory())
						queue.offer(files[i]);
					else {
						// 如果是文件则根据文件名与目标文件名进行匹配
						tempName = files[i].getName();
						if (wildcardMatch(targetFileName, tempName))
							fileList.add(files[i].getAbsoluteFile());
						// 如果已经到达指定的数目，则退出循环
						if (count != 0 && fileList.size() > count)
							return fileList;
					}
				}
			}

		}
		return fileList;
	}

	/**
	 * 通配符匹配
	 * 
	 * @param pattern
	 *            通配符模式
	 * @param str
	 *            待匹配的字符串
	 * @return 匹配成功返回true，否则返回false
	 */
	private static boolean wildcardMatch(String pattern, String str) {
		int patternLength = pattern.length();
		int strLength = str.length();
		int strIndex = 0;
		char ch;
		for (int patternIndex = 0; patternIndex < patternLength; patternIndex++) {
			ch = pattern.charAt(patternIndex);
			if (ch == '*') {
				// 通配符"*"，表示可以匹配任意多个字符
				while (strIndex < strLength) {
					if (wildcardMatch(pattern.substring(patternIndex + 1),
							str.substring(strIndex))) {
						return true;
					}
					strIndex++;
				}
			} else if (ch == '?') {
				// 通配符"?"表示匹配任意一个字符
				strIndex++;
				if (strIndex > strLength) {
					// 表示str中已经没有字符匹配"?"了
					return false;
				}
			} else {
				if (strIndex >= strLength || ch != str.charAt(strIndex))
					return false;
				strIndex++;
			}
		}
		return (strIndex == strLength);
	}

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
       System.out.println(123);
	   //String baseDir = "F:\\picture1\\" + "天兴洲大桥_青山主线" + "\\车道1";
       //String baseDir = "F:\\picture1\\天兴洲大桥_汉口主线\\车道1";
       String baseDir = "F:\\picture1";
		String fileName = "201607*";
		int count = 0;
		List<File> result = FileFinder.findFiles(baseDir, fileName, count);
		System.out.println(result);
		for (File file : result) {
			file.delete();
			System.out.println(file.getPath());
		}
	}
}
