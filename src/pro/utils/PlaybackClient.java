package pro.utils;

import java.net.InetSocketAddress;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pro.dao.entity.PlaybackInfo;


/**
 * Playback的socket客户端，向安装在客户机器上的回放程序发送所需参数
 * 
 * @author zq
 *
 */
public class PlaybackClient {

	// 和本线程相关的Socket
	private Socket socket = null;
	private PlaybackInfo info;

	public PlaybackClient(PlaybackInfo info) {
		//this.socket = socket;
		this.info = info;
	}
	
	public void setSocket() {
		try {
			socket = new Socket();
			// 与服务器建立socket连接永远不会超时
			socket.connect(new InetSocketAddress(info.ip, 10050), 0);
		} catch (IOException e) {
			// e.printStackTrace();
		}
	}

	public void closeSocket() {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {

			}
		}
	}

	// 向服务器传递回放参数
	public void dataTransmit() {
		OutputStream os = null;
		
		if (socket != null) {
			System.out.println("PlaybackClient has started!");
			try {
				os = socket.getOutputStream();
				info.PackData();
				os.write(info.getByteArrayData());
				os.flush();
			}  catch (Exception e) {
				System.err.println("PlaybackClient使用出现问题！");
				e.printStackTrace();
			} finally {
				try {
					if (os != null) {
						os.close();
					}
					if (socket != null) {
						socket.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.err.println("关闭出现问题！");
					e.printStackTrace();
				}
			}
		}
		else {
			System.err.println("Socket in PlaybackClient is null!");
		}
	}

	public static void main(String[] args) throws Exception {/*
		// 创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(10050);
			Socket socket = null;
			System.out.println("***PlaybackClient程序已经启动，向远端发送数据***");

			while (true) {
				// 调用accept()方法开始监听，等待客户端的连接
				socket = serverSocket.accept();
				// 创建一个新的线程
				PlaybackServer PlaybackServer = new PlaybackServer(socket);
				// 启动线程
				PlaybackServer.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null) {
				serverSocket.close();
			}
		}
	*/}
}
