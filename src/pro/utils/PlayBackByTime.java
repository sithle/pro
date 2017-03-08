package pro.utils;

import java.awt.BorderLayout;
/*import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;*/
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//import java.io.File;
import java.sql.Timestamp;

import javax.swing.BorderFactory;
/*import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;*/
import javax.swing.JComponent;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/*import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;*/
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
/*import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;*/

import pro.utils.NetSDKLib;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.examples.win32.W32API.HWND;
import com.sun.jna.ptr.IntByReference;


/**
 * 回放Demo
 */
public class PlayBackByTime{
	static NetSDKLib NetSdk        = NetSDKLib.COMMON_INSTANCE;
    static NetSDKLib ConfigSdk     = NetSDKLib.CONFIG_INSTANCE;
    
	private SDKEnvironment sdkEnv;
	
	// 登录参数	
	private String m_strIp             = "10.0.0.123";
	private Integer m_nPort            = new Integer("37777");
	private String m_strUser           = "admin";
	private String m_strPassword       = "admin";
	// 设备信息
    private NetSDKLib.NET_DEVICEINFO m_stDeviceInfo = new NetSDKLib.NET_DEVICEINFO();
    // 回放参数
    private Timestamp StartTime = new Timestamp(0);
    private Timestamp StopTime  = new Timestamp(0);
    // streamId=1表示上游，2表示下游
    private int streamId = 1;
	
/*    private boolean m_playFlag = false; // 播放标志位
    private boolean m_pauseFlag = true; // 暂停标志位
*/	private NativeLong m_hLoginHandle = new NativeLong(0); // 登录句柄
	private NativeLong m_hPlayHandle = new NativeLong(0);  // 回放句柄
	
	private int m_streamType; // 码流类型
	private int m_recordType; // 录像类型
	private Integer m_channel = new Integer(2); // 通道号
	private NetSDKLib.NET_TIME m_startTime = new NetSDKLib.NET_TIME(); // 开始时间
	private NetSDKLib.NET_TIME m_stopTime = new NetSDKLib.NET_TIME(); // 结束时间
	private HWND m_hwnd; // 播放窗口句柄
    
    private DownLoadPosCallBack m_PlayBackDownLoadPos = new DownLoadPosCallBack(); // 回放数据下载进度
    private DataCallBack m_dataCallBack = new DataCallBack(); // 回放数据回调
    
	/**
	 * PlayDemo 构造
	 */
	public PlayBackByTime(Timestamp Start, Timestamp Stop, int stream) {
		StartTime = Start;
		StopTime  = Stop;
		streamId  = stream;
		
		sdkEnv = new SDKEnvironment();
		sdkEnv.init();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				InitPlayBackFrame("PlayBackByTime");
				StartPlayBack();
			}
		});		
	}
    
	public void InitPlayBackFrame(String title) {
		playFrame = new JFrame(title);
		playFrame.setSize(820, 520);
		playFrame.setLayout(new BorderLayout());
		playFrame.setLocationRelativeTo(null);
		playFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		playFrame.setVisible(true);
		
/*		loginJPanel = new LoginPanel(); // 登录面板
		controlPanel = new ControlPanel(); // 主控制面板		
*/		playWindow = new PlayWindow(); // 播放面板
		
/*		playFrame.add(controlPanel, BorderLayout.EAST);
		playFrame.add(loginJPanel, BorderLayout.NORTH);*/
		playFrame.add(playWindow, BorderLayout.CENTER);	
		
		WindowAdapter closeWindowAdapter =  new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.out.println("PlayBack Window Closing");			
				LoginOut();
				sdkEnv.cleanup();
				playFrame.dispose();
			}
		};
		playFrame.addWindowListener(closeWindowAdapter);
	}
	
	/**
	 * 播放面板
	 */
	private class PlayWindow extends JPanel {		
		private HWND m_win32;
		public PlayWindow() {		
			setLayout(new BorderLayout());
			// setBorderEx(this, "播放窗口", 2);
			setBorderEx(this, "", 2);
			
			panelPlayBack = new java.awt.Panel();	
			//playSlider = new JSlider(0, 100, 0);
			
			// 播放容器设置
			panelPlayBack.setBackground(new java.awt.Color(153, 240, 255));
		    panelPlayBack.setForeground(new java.awt.Color(0, 0, 0));
			//panelPlayBack.setBounds(10, 20, 390, 300);
			panelPlayBack.setSize(800, 500);
			
			add(panelPlayBack, BorderLayout.CENTER);
		}
	
		// 返回 windows 句柄 
	    public HWND getHWNDofFrame() {
	        m_win32 = new HWND(Native.getComponentPointer(panelPlayBack));
	        return m_win32;
	    }
	}	

	/**
	 * NetSDK 库初始化
	 */
	private class SDKEnvironment {
    
	    private boolean bInit = false;
	    private boolean bLogopen = false;
	    
	    private DisConnect disConnect = new DisConnect();  // 设备断线通知回调
	    private HaveReConnect haveReConnect = new HaveReConnect(); // 网络连接恢复
	    
	    // 初始化
	    public boolean init() {
	    	
			// SDK 库初始化, 并设置断线回调
			bInit = NetSdk.CLIENT_Init(disConnect, new NativeLong(0));
			if (!bInit) {
				System.err.println("Initialize SDK failed");
				return false;
			}
			
			// 打开日志，可选
/*			NetSDKLib.LOG_SET_PRINT_INFO setLog = new NetSDKLib.LOG_SET_PRINT_INFO();
			
			File path = new File(".");			
			String logPath = path.getAbsoluteFile().getParent() + "\\sdk_log\\PlayBackByTime_" + System.currentTimeMillis() + ".log";
			
			setLog.bSetFilePath = 1;
			System.arraycopy(logPath.getBytes(), 0, setLog.szLogFilePath, 0, logPath.getBytes().length);
			
			setLog.bSetPrintStrategy = 1;
			setLog.nPrintStrategy = 0;
			bLogopen = NetSdk.CLIENT_LogOpen(setLog);
			if (!bLogopen) {
				System.err.println("Failed to open NetSDK log !!!");
			}*/
			
			// 获取版本, 可选操作
			System.out.printf("NetSDK Version [%d]\n", NetSdk.CLIENT_GetSDKVersion());		
			
			// 设置断线重连回调接口，设置过断线重连成功回调函数后，当设备出现断线情况，SDK内部会自动进行重连操作
			// 此操作为可选操作，但建议用户进行设置
			NetSdk.CLIENT_SetAutoReconnect(haveReConnect, new NativeLong(0));
			
			// 设置登录超时时间和尝试次数 , 此操作为可选操作	   
			int waitTime = 5000;   // 登录请求响应超时时间设置为 5s
			int tryTimes = 3;      // 登录时尝试建立链接3次
			NetSdk.CLIENT_SetConnectTime(waitTime, tryTimes);
			
			// 设置更多网络参数，NET_PARAM的nWaittime，nConnectTryNum成员与CLIENT_SetConnectTime 
			// 接口设置的登录设备超时时间和尝试次数意义相同
			// 此操作为可选操作
			NetSDKLib.NET_PARAM netParam = new NetSDKLib.NET_PARAM();
			netParam.nConnectTime = 10000; // 登录时尝试建立链接的超时时间
			NetSdk.CLIENT_SetNetworkParam(netParam);			
	    	
	    	return true;
	    }
	    
	    // 清除环境
	    public void cleanup() {
	    	if (bLogopen) {
	    		NetSdk.CLIENT_LogClose();
	    	}
	    	
	    	if (bInit) {
	    		NetSdk.CLIENT_Cleanup();
	    	}
	    }
	    
	    // 设备断线回调: 通过 CLIENT_Init 设置该回调函数，当设备出现断线时，SDK会调用该函数
	    public class DisConnect implements NetSDKLib.fDisConnect  {
	        public int invoke(NativeLong lLoginID, String pchDVRIP, int nDVRPort, NativeLong dwUser) {
	        	System.out.printf("Device[%s] Port[%d] Disconnect!\n" , pchDVRIP , nDVRPort);
	        	return 0;
	        }
	    }
		
		// 网络连接恢复，设备重连成功回调
		// 通过 CLIENT_SetAutoReconnect 设置该回调函数，当已断线的设备重连成功时，SDK会调用该函数
	    public class HaveReConnect implements NetSDKLib.fHaveReConnect {
	        public int invoke(NativeLong lLoginID, String pchDVRIP, int nDVRPort, NativeLong dwUser) {
	        	System.out.printf("ReConnect Device[%s] Port[%d]\n", pchDVRIP, nDVRPort);
	        	return 0;
	        }
	    }
	}

	// 回放进度回调
    public class DownLoadPosCallBack implements NetSDKLib.fDownLoadPosCallBack {
    	public void invoke(NativeLong lPlayHandle, int dwTotalSize, int dwDownLoadSize, NativeLong dwUser) {
    		if (playPos != dwDownLoadSize) {
    			playPos = dwDownLoadSize;
    			System.out.println("PlayBack DownLoadCallback: [ " + dwTotalSize + " ]" + " [ " + dwDownLoadSize +" ]");
        		System.out.println("Pos " + dwDownLoadSize*100/dwTotalSize);
//        		playSlider.setValue(dwDownLoadSize*100/dwTotalSize);
        		if (-1 == dwDownLoadSize) {
        			JOptionPane.showMessageDialog(playFrame, "回放结束");
//        			playSlider.setValue(100);
        		}
    		}    		
    	}
    }
    
    // 回放数据回调
    public class DataCallBack implements NetSDKLib.fDataCallBack {
    	public int invoke(NativeLong lRealHandle, int dwDataType, Pointer pBuffer, int dwBufSize, NativeLong dwUser) {
//    		System.out.println("PlayBack DataCallBack [ " + dwUser +" ]");
    		return 0;
    	}
    }

    //////////////////////// Border Extends //////////////////////
	/**
	 * 设置边框
	 */
	private void setBorderEx(JComponent object, String title, int width) {
		Border innerBorder = BorderFactory.createTitledBorder(title);
		Border outerBorder = BorderFactory.createEmptyBorder(width, width, width, width);	
		object.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
	}
	
	//////////////////////// Operations of PlayBackFrame ////////////////
	/**
	 * 更新回放参数
	 */
	private void updatePlayBackParams() {
		// 通道号 、码流 、录像类型
		// streamId=1表示上游，2表示下游
		if(streamId==1) {
			m_channel = 2;
			m_strIp = "10.0.0.123";
		}
		else if(streamId==2) {
			m_channel = 6;
			m_strIp = "10.0.0.123";
		}
		
/*		m_streamType = streamTypeComboBox.getSelectedIndex();
		m_recordType = recordTypeComboBox.getSelectedIndex();*/
				
		// 开始时间
		m_startTime.dwYear = Integer.parseInt(StartTime.toString().substring(0, 4));
		m_startTime.dwMonth = Integer.parseInt(StartTime.toString().substring(5, 7));
		m_startTime.dwDay = Integer.parseInt(StartTime.toString().substring(8, 10));
		m_startTime.dwHour = Integer.parseInt(StartTime.toString().substring(11, 13));
		m_startTime.dwMinute = Integer.parseInt(StartTime.toString().substring(14, 16));
		m_startTime.dwSecond = Integer.parseInt(StartTime.toString().substring(17, 19));
		// 结束时间
		m_stopTime.dwYear = Integer.parseInt(StopTime.toString().substring(0, 4));
		m_stopTime.dwMonth = Integer.parseInt(StopTime.toString().substring(5, 7));
		m_stopTime.dwDay = Integer.parseInt(StopTime.toString().substring(8, 10));
		m_stopTime.dwHour = Integer.parseInt(StopTime.toString().substring(11, 13));
		m_stopTime.dwMinute = Integer.parseInt(StopTime.toString().substring(14, 16));
		m_stopTime.dwSecond = Integer.parseInt(StopTime.toString().substring(17, 19));
		
		System.out.println("Param m_channel: " + m_channel);
/*		System.out.println("Param SteamType: " + m_streamType);
		System.out.println("Param RecordType: " + m_recordType);*/
		System.out.println("Param StartTime: " + m_startTime);
		System.out.println("Param StopTime: " + m_stopTime);
	}
	
	/**
	 * 登录设备
	 */
	private void Login() {		
		System.out.println("设备地址：" + m_strIp
						+  "\n端口号：" + m_nPort
						+  "\n用户名：" + m_strUser
						+  "\n密码：" + m_strPassword);
		
		// 登录设备
        int nError[] = {0};
        m_hLoginHandle = NetSdk.CLIENT_LoginEx(m_strIp, (short)m_nPort.intValue(), m_strUser , m_strPassword , 0, null, m_stDeviceInfo, nError);
        if(m_hLoginHandle.longValue() == 0)
        {
            System.err.printf("Login Device[%s] Port[%d]Failed. Last Error[%x]\n" , m_strIp , m_nPort , NetSdk.CLIENT_GetLastError());
            JOptionPane.showMessageDialog(playWindow, "登录失败");
        }
        else
        {
        	System.out.println("Login Success [ " + m_strIp +" ]");
        	JOptionPane.showMessageDialog(playWindow, "登录成功");
/*        	logoutBtn.setEnabled(true);
        	loginBtn.setEnabled(false);*/
        }
	}
	
	/**
	 * 登出设备
	 */
	private void LoginOut() {
		if (m_hLoginHandle.longValue() != 0) {
			System.out.println("LogOut device");
    		// 确保关闭播放
			StopPlayBack();
    		
    		if (NetSdk.CLIENT_Logout(m_hLoginHandle)) {
	    		System.out.println("Logout Success [ " + m_strIp +" ]");
    			m_hLoginHandle.setValue(0);
/*    			logoutBtn.setEnabled(false);
	        	loginBtn.setEnabled(true);*/
    		}    		
    	}
	}
	
	/**
	 * 开启回放
	 */
	private void StartPlayBack() {
		Login();		
		if (m_hLoginHandle.longValue() == 0) {
			System.err.printf("Please Login First");
        	JOptionPane.showMessageDialog(playFrame, "设备登录失败，请检查设置参数");		
        	return;
		}
		
		updatePlayBackParams(); // 更新参数
		m_hwnd = playWindow.getHWNDofFrame();
		
        // 设置回放时的码流类型
        IntByReference steamType = new IntByReference(m_streamType);// 0-主辅码流,1-主码流,2-辅码流
        int emType = NetSDKLib.EM_USEDEV_MODE.NET_RECORD_STREAM_TYPE;       

        boolean bret = NetSdk.CLIENT_SetDeviceMode(m_hLoginHandle, emType, steamType.getPointer());
        if (!bret) {
        	System.err.printf("Set Stream Type Failed, Get last error [0x%x]\n", NetSdk.CLIENT_GetLastError());
        }
        
        // 设置回放时的录像文件类型
        IntByReference emFileType = new IntByReference(m_recordType); // 所有录像 NET_RECORD_TYPE
        emType = NetSDKLib.EM_USEDEV_MODE.NET_RECORD_TYPE;      
        bret = NetSdk.CLIENT_SetDeviceMode(m_hLoginHandle, emType, emFileType.getPointer());
        if (!bret) {
        	System.err.printf("Set Record Type Failed, Get last error [0x%x]\n", NetSdk.CLIENT_GetLastError());
        }
		
		m_hPlayHandle = NetSdk.CLIENT_PlayBackByTimeEx(m_hLoginHandle, m_channel, m_startTime, m_stopTime, 
        		m_hwnd, m_PlayBackDownLoadPos, m_hLoginHandle, m_dataCallBack, m_hLoginHandle);
        if (m_hPlayHandle.longValue() == 0) {
        	int error = NetSdk.CLIENT_GetLastError();
        	System.err.printf("PlayBackByTimeEx Failed, Get last error [0x%x]\n", error);
        	switch(error) {
        	case NetSDKLib.NET_NO_RECORD_FOUND:
        		JOptionPane.showMessageDialog(playFrame, "查找不到录像");				
        		break;
        	default:
        		JOptionPane.showMessageDialog(playFrame, "开启失败, 错误码：" + String.format("0x%x", error));	
        		break;
        	}	
        }
        else {	
        	System.out.println("PlayBackByTimeEx Successed");
//       	m_playFlag = true; // 打开播放标志位
//        	playButton.setText("停止回放");  	
        }
	}
	
	/**
	 * 停止回放
	 */
	private void StopPlayBack() {
		if (m_hPlayHandle.longValue() == 0) { 
			System.err.println("Please make sure the PlayBack Handle is valid");
			return;
		}
		
		if (!NetSdk.CLIENT_StopPlayBack(m_hPlayHandle)) {
			System.err.println("StopPlayBack Failed");
			return;
		}
		
		m_hPlayHandle.setValue(0);
/*		m_playFlag = false;
		m_pauseFlag = true;*/
		playPos = 0;
/*		playButton.setText("开启回放");
		pauseButton.setText("暂停");*/
		panelPlayBack.repaint();
	}
	
	//////////////////////// Components of PlayBackFrame ///////////////
	/**
	 * 主界面
	 */
	private JFrame playFrame;
	
	/**
	 * 播放界面
	 */
	private java.awt.Panel panelPlayBack;
//	private JSlider playSlider; // 播放进度条
	private int playPos = 0;
	private PlayWindow playWindow;

/*	public static void main(String[] args) {
		PlayBackByTime demo = new PlayBackByTime();
	}*/
}
