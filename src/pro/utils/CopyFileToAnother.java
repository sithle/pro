package pro.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

/**
 * 把一个文件复制到另一个文件夹中
 * 
 * @author lc
 * 
 */
public class CopyFileToAnother {

	/**
	 * 把一个文件复制到另一个文件夹中，如下例子，把E盘中的文件复制到F盘中.
	 * 
	 * @param oldPath
	 *            类似于"E:\\myphoto.png"
	 * @param newPath
	 *            类似于"F:\\myphoto.png"
	 */
	@SuppressWarnings("resource")
	public static void Copy(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) {
				InputStream inStream = new FileInputStream(oldPath);
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1024];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制文件失败！");
			e.printStackTrace();
		}
	}

	/**
	 * 用文件通道的方式来进行文件复制，比流的方式要快很多很多4倍左右哦
	 * 
	 * @param s
	 *            源文件
	 * @param t
	 *            复制到的新文件
	 */
	public static void fileChannelCopy(File s, File t) {

		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		try {
			fi = new FileInputStream(s);
			fo = new FileOutputStream(t);
			in = fi.getChannel();// 得到对应的文件通道
			out = fo.getChannel();// 得到对应的文件通道
			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fi.close();
				in.close();
				fo.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
//		double x = System.currentTimeMillis();
//		Copy("E:\\myphoto.png", "F:\\myphoto.png");
//		double y = System.currentTimeMillis();
//		System.out.println("流方式复制文件用时：" + (y - x));
//
		double x1 = System.currentTimeMillis();
		fileChannelCopy(new File("E:\\myphoto.png"),
				new File("F:\\myphoto.png"));
		double y1 = System.currentTimeMillis();
		System.out.println("文件通道方式复制文件用时：" + (y1 - x1));
		
//		double x2 = System.currentTimeMillis();
//		File f = new File("F:\\myphoto.png"); 
//		System.out.println(f.delete());
//		double y2 = System.currentTimeMillis();
//		System.out.println("删除文件用时：" + (y2 - x2));

		// String
		// classPath=Thread.currentThread().getContextClassLoader().getResource("").toString();
		// String path = classPath.replace("file:/",
		// "").replace("WEB-INF/classes/", "").replace("/", "\\")+"photo\\";
		// System.out.println(path);
	}
}
