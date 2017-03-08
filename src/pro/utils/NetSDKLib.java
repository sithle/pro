package pro.utils;

import pro.utils.NetSDKTools;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Union;
import com.sun.jna.Memory;
import com.sun.jna.examples.win32.W32API.HWND;

/**
 * SDK JNA接口封装
 */

public interface NetSDKLib extends StdCallLibrary{

    /*NetSDKLib COMMON_INSTANCE = (NetSDKLib) Native.loadLibrary("./libs/dhnetsdk" , NetSDKLib.class);
    NetSDKLib CONFIG_INSTANCE = (NetSDKLib) Native.loadLibrary("./libs/dhconfigsdk" , NetSDKLib.class);*/
	/*NetSDKLib COMMON_INSTANCE = (NetSDKLib) Native.loadLibrary("dhnetsdk" , NetSDKLib.class);
    NetSDKLib CONFIG_INSTANCE = (NetSDKLib) Native.loadLibrary("dhconfigsdk" , NetSDKLib.class);*/
	NetSDKLib COMMON_INSTANCE = (NetSDKLib) Native.loadLibrary("E:/work/MyEclipse 2016/pro/libs/dhnetsdk" , NetSDKLib.class);
    NetSDKLib CONFIG_INSTANCE = (NetSDKLib) Native.loadLibrary("E:/work/MyEclipse 2016/pro/libs/dhconfigsdk" , NetSDKLib.class);

    /************************************************************************
     ** 常量定义
     ***********************************************************************/
    public static final int NET_SERIALNO_LEN                      = 48;             // 设备序列号字符长度
    public static final int NET_CFG_Max_VideoColor                = 24;             // 每个通道最大视频输入颜色配置数量
    public static final int NET_CFG_Custom_Title_Len              = 1024;           // 自定义标题名称长度(扩充到1024)
    public static final int NET_CFG_Custom_TitleType_Len          = 32;             // 自定义标题类型长度
    public static final int NET_CFG_Max_Video_Widget_Cover        = 16;             // 编码区域覆盖最大数量
    public static final int NET_CFG_Max_Video_Widget_Custom_Title = 8;              // 编码物件自定义标题最大数量
    public static final int NET_CFG_Max_Video_Widget_Sensor_Info  = 2;              // 编码物件叠加传感器信息的最大数目
    public static final int NET_CFG_Max_Description_Num           = 4;              // 叠加区域描述信息的最大个数

    // 错误类型代号，对应CLIENT_GetLastError接口的返回值
    public static final int NET_NOERROR                         =  0;               // 没有错误
    public static final int NET_ERROR                           = -1;               // 未知错误
    public static final int NET_SYSTEM_ERROR                    = (0x80000000|1);   // Windows系统出错
    public static final int NET_NETWORK_ERROR                   = (0x80000000|2);   // 网络错误，可能是因为网络超时
    public static final int NET_DEV_VER_NOMATCH                 = (0x80000000|3);   // 设备协议不匹配
    public static final int NET_INVALID_HANDLE                  = (0x80000000|4);   // 句柄无效
    public static final int NET_OPEN_CHANNEL_ERROR              = (0x80000000|5);   // 打开通道失败
    public static final int NET_CLOSE_CHANNEL_ERROR             = (0x80000000|6);   // 关闭通道失败
    public static final int NET_ILLEGAL_PARAM                   = (0x80000000|7);   // 用户参数不合法
    public static final int NET_SDK_INIT_ERROR                  = (0x80000000|8);   // SDK初始化出错
    public static final int NET_SDK_UNINIT_ERROR                = (0x80000000|9);   // SDK清理出错
    public static final int NET_RENDER_OPEN_ERROR               = (0x80000000|10);  // 申请render资源出错
    public static final int NET_DEC_OPEN_ERROR                  = (0x80000000|11);  // 打开解码库出错
    public static final int NET_DEC_CLOSE_ERROR                 = (0x80000000|12);  // 关闭解码库出错
    public static final int NET_MULTIPLAY_NOCHANNEL             = (0x80000000|13);  // 多画面预览中检测到通道数为0
    public static final int NET_TALK_INIT_ERROR                 = (0x80000000|14);  // 录音库初始化失败
    public static final int NET_TALK_NOT_INIT                   = (0x80000000|15);  // 录音库未经初始化
    public static final int NET_TALK_SENDDATA_ERROR             = (0x80000000|16);  // 发送音频数据出错
    public static final int NET_REAL_ALREADY_SAVING             = (0x80000000|17);  // 实时数据已经处于保存状态
    public static final int NET_NOT_SAVING                      = (0x80000000|18);  // 未保存实时数据
    public static final int NET_OPEN_FILE_ERROR                 = (0x80000000|19);  // 打开文件出错
    public static final int NET_PTZ_SET_TIMER_ERROR             = (0x80000000|20);  // 启动云台控制定时器失败
    public static final int NET_RETURN_DATA_ERROR               = (0x80000000|21);  // 对返回数据的校验出错
    public static final int NET_INSUFFICIENT_BUFFER             = (0x80000000|22);  // 没有足够的缓存
    public static final int NET_NOT_SUPPORTED                   = (0x80000000|23);  // 当前SDK未支持该功能
    public static final int NET_NO_RECORD_FOUND                 = (0x80000000|24);  // 查询不到录象
    public static final int NET_NOT_AUTHORIZED                  = (0x80000000|25);  // 无操作权限
    public static final int NET_NOT_NOW                         = (0x80000000|26);  // 暂时无法执行
    public static final int NET_NO_TALK_CHANNEL                 = (0x80000000|27);  // 未发现对讲通道
    public static final int NET_NO_AUDIO                        = (0x80000000|28);  // 未发现音频
    public static final int NET_NO_INIT                         = (0x80000000|29);  // 网络SDK未经初始化
    public static final int NET_DOWNLOAD_END                    = (0x80000000|30);  // 下载已结束
    public static final int NET_EMPTY_LIST                      = (0x80000000|31);  // 查询结果为空
    public static final int NET_ERROR_GETCFG_SYSATTR            = (0x80000000|32);  // 获取系统属性配置失败
    public static final int NET_ERROR_GETCFG_SERIAL             = (0x80000000|33);  // 获取序列号失败
    public static final int NET_ERROR_GETCFG_GENERAL            = (0x80000000|34);  // 获取常规属性失败
    public static final int NET_ERROR_GETCFG_DSPCAP             = (0x80000000|35);  // 获取DSP能力描述失败
    public static final int NET_ERROR_GETCFG_NETCFG             = (0x80000000|36);  // 获取网络配置失败
    public static final int NET_ERROR_GETCFG_CHANNAME           = (0x80000000|37);  // 获取通道名称失败
    public static final int NET_ERROR_GETCFG_VIDEO              = (0x80000000|38);  // 获取视频属性失败
    public static final int NET_ERROR_GETCFG_RECORD             = (0x80000000|39);  // 获取录象配置失败
    public static final int NET_ERROR_GETCFG_PRONAME            = (0x80000000|40);  // 获取解码器协议名称失败
    public static final int NET_ERROR_GETCFG_FUNCNAME           = (0x80000000|41);  // 获取232串口功能名称失败
    public static final int NET_ERROR_GETCFG_485DECODER         = (0x80000000|42);  // 获取解码器属性失败
    public static final int NET_ERROR_GETCFG_232COM             = (0x80000000|43);  // 获取232串口配置失败
    public static final int NET_ERROR_GETCFG_ALARMIN            = (0x80000000|44);  // 获取外部报警输入配置失败
    public static final int NET_ERROR_GETCFG_ALARMDET           = (0x80000000|45);  // 获取动态检测报警失败
    public static final int NET_ERROR_GETCFG_SYSTIME            = (0x80000000|46);  // 获取设备时间失败
    public static final int NET_ERROR_GETCFG_PREVIEW            = (0x80000000|47);  // 获取预览参数失败
    public static final int NET_ERROR_GETCFG_AUTOMT             = (0x80000000|48);  // 获取自动维护配置失败
    public static final int NET_ERROR_GETCFG_VIDEOMTRX          = (0x80000000|49);  // 获取视频矩阵配置失败
    public static final int NET_ERROR_GETCFG_COVER              = (0x80000000|50);  // 获取区域遮挡配置失败
    public static final int NET_ERROR_GETCFG_WATERMAKE          = (0x80000000|51);  // 获取图象水印配置失败
    public static final int NET_ERROR_SETCFG_GENERAL            = (0x80000000|55);  // 修改常规属性失败
    public static final int NET_ERROR_SETCFG_NETCFG             = (0x80000000|56);  // 修改网络配置失败
    public static final int NET_ERROR_SETCFG_CHANNAME           = (0x80000000|57);  // 修改通道名称失败
    public static final int NET_ERROR_SETCFG_VIDEO              = (0x80000000|58);  // 修改视频属性失败
    public static final int NET_ERROR_SETCFG_RECORD             = (0x80000000|59);  // 修改录象配置失败
    public static final int NET_ERROR_SETCFG_485DECODER         = (0x80000000|60);  // 修改解码器属性失败
    public static final int NET_ERROR_SETCFG_232COM             = (0x80000000|61);  // 修改232串口配置失败
    public static final int NET_ERROR_SETCFG_ALARMIN            = (0x80000000|62);  // 修改外部输入报警配置失败
    public static final int NET_ERROR_SETCFG_ALARMDET           = (0x80000000|63);  // 修改动态检测报警配置失败
    public static final int NET_ERROR_SETCFG_SYSTIME            = (0x80000000|64);  // 修改设备时间失败
    public static final int NET_ERROR_SETCFG_PREVIEW            = (0x80000000|65);  // 修改预览参数失败
    public static final int NET_ERROR_SETCFG_AUTOMT             = (0x80000000|66);  // 修改自动维护配置失败
    public static final int NET_ERROR_SETCFG_VIDEOMTRX          = (0x80000000|67);  // 修改视频矩阵配置失败
    public static final int NET_ERROR_SETCFG_COVER              = (0x80000000|68);  // 修改区域遮挡配置失败
    public static final int NET_ERROR_SETCFG_WATERMAKE          = (0x80000000|69);  // 修改图象水印配置失败
    public static final int NET_ERROR_SETCFG_WLAN               = (0x80000000|70);  // 修改无线网络信息失败
    public static final int NET_ERROR_SETCFG_WLANDEV            = (0x80000000|71);  // 选择无线网络设备失败
    public static final int NET_ERROR_SETCFG_REGISTER           = (0x80000000|72);  // 修改主动注册参数配置失败
    public static final int NET_ERROR_SETCFG_CAMERA             = (0x80000000|73);  // 修改摄像头属性配置失败
    public static final int NET_ERROR_SETCFG_INFRARED           = (0x80000000|74);  // 修改红外报警配置失败
    public static final int NET_ERROR_SETCFG_SOUNDALARM         = (0x80000000|75);  // 修改音频报警配置失败
    public static final int NET_ERROR_SETCFG_STORAGE            = (0x80000000|76);  // 修改存储位置配置失败
    public static final int NET_AUDIOENCODE_NOTINIT             = (0x80000000|77);  // 音频编码接口没有成功初始化
    public static final int NET_DATA_TOOLONGH                   = (0x80000000|78);  // 数据过长
    public static final int NET_UNSUPPORTED                     = (0x80000000|79);  // 设备不支持该操作
    public static final int NET_DEVICE_BUSY                     = (0x80000000|80);  // 设备资源不足
    public static final int NET_SERVER_STARTED                  = (0x80000000|81);  // 服务器已经启动
    public static final int NET_SERVER_STOPPED                  = (0x80000000|82);  // 服务器尚未成功启动
    public static final int NET_LISTER_INCORRECT_SERIAL         = (0x80000000|83);  // 输入序列号有误
    public static final int NET_QUERY_DISKINFO_FAILED           = (0x80000000|84);  // 获取硬盘信息失败
    public static final int NET_ERROR_GETCFG_SESSION            = (0x80000000|85);  // 获取连接Session信息
    public static final int NET_USER_FLASEPWD_TRYTIME           = (0x80000000|86);  // 输入密码错误超过限制次数
    public static final int NET_LOGIN_ERROR_PASSWORD            = (0x80000000|100); // 密码不正确
    public static final int NET_LOGIN_ERROR_USER                = (0x80000000|101); // 帐户不存在
    public static final int NET_LOGIN_ERROR_TIMEOUT             = (0x80000000|102); // 等待登录返回超时
    public static final int NET_LOGIN_ERROR_RELOGGIN            = (0x80000000|103); // 帐号已登录
    public static final int NET_LOGIN_ERROR_LOCKED              = (0x80000000|104); // 帐号已被锁定
    public static final int NET_LOGIN_ERROR_BLACKLIST           = (0x80000000|105); // 帐号已被列为黑名单
    public static final int NET_LOGIN_ERROR_BUSY                = (0x80000000|106); // 资源不足，系统忙
    public static final int NET_LOGIN_ERROR_CONNECT             = (0x80000000|107); // 登录设备超时，请检查网络并重试
    public static final int NET_LOGIN_ERROR_NETWORK             = (0x80000000|108); // 网络连接失败
    public static final int NET_LOGIN_ERROR_SUBCONNECT          = (0x80000000|109); // 登录设备成功，但无法创建视频通道，请检查网络状况
    public static final int NET_LOGIN_ERROR_MAXCONNECT          = (0x80000000|110); // 超过最大连接数
    public static final int NET_LOGIN_ERROR_PROTOCOL3_ONLY      = (0x80000000|111); // 只支持3代协议
    public static final int NET_LOGIN_ERROR_UKEY_LOST           = (0x80000000|112); // 未插入U盾或U盾信息错误
    public static final int NET_LOGIN_ERROR_NO_AUTHORIZED       = (0x80000000|113); // 客户端IP地址没有登录权限
    public static final int NET_RENDER_SOUND_ON_ERROR           = (0x80000000|120); // Render库打开音频出错
    public static final int NET_RENDER_SOUND_OFF_ERROR          = (0x80000000|121); // Render库关闭音频出错
    public static final int NET_RENDER_SET_VOLUME_ERROR         = (0x80000000|122); // Render库控制音量出错
    public static final int NET_RENDER_ADJUST_ERROR             = (0x80000000|123); // Render库设置画面参数出错
    public static final int NET_RENDER_PAUSE_ERROR              = (0x80000000|124); // Render库暂停播放出错
    public static final int NET_RENDER_SNAP_ERROR               = (0x80000000|125); // Render库抓图出错
    public static final int NET_RENDER_STEP_ERROR               = (0x80000000|126); // Render库步进出错
    public static final int NET_RENDER_FRAMERATE_ERROR          = (0x80000000|127); // Render库设置帧率出错
    public static final int NET_RENDER_DISPLAYREGION_ERROR      = (0x80000000|128); // Render库设置显示区域出错
    public static final int NET_GROUP_EXIST                     = (0x80000000|140); // 组名已存在
    public static final int NET_GROUP_NOEXIST                   = (0x80000000|141); // 组名不存在
    public static final int NET_GROUP_RIGHTOVER                 = (0x80000000|142); // 组的权限超出权限列表范围
    public static final int NET_GROUP_HAVEUSER                  = (0x80000000|143); // 组下有用户，不能删除
    public static final int NET_GROUP_RIGHTUSE                  = (0x80000000|144); // 组的某个权限被用户使用，不能出除
    public static final int NET_GROUP_SAMENAME                  = (0x80000000|145); // 新组名同已有组名重复
    public static final int NET_USER_EXIST                      = (0x80000000|146); // 用户已存在
    public static final int NET_USER_NOEXIST                    = (0x80000000|147); // 用户不存在
    public static final int NET_USER_RIGHTOVER                  = (0x80000000|148); // 用户权限超出组权限
    public static final int NET_USER_PWD                        = (0x80000000|149); // 保留帐号，不容许修改密码
    public static final int NET_USER_FLASEPWD                   = (0x80000000|150); // 密码不正确
    public static final int NET_USER_NOMATCHING                 = (0x80000000|151); // 密码不匹配
    public static final int NET_USER_INUSE                      = (0x80000000|152); // 账号正在使用中
    public static final int NET_ERROR_GETCFG_ETHERNET           = (0x80000000|300); // 获取网卡配置失败
    public static final int NET_ERROR_GETCFG_WLAN               = (0x80000000|301); // 获取无线网络信息失败
    public static final int NET_ERROR_GETCFG_WLANDEV            = (0x80000000|302); // 获取无线网络设备失败
    public static final int NET_ERROR_GETCFG_REGISTER           = (0x80000000|303); // 获取主动注册参数失败
    public static final int NET_ERROR_GETCFG_CAMERA             = (0x80000000|304); // 获取摄像头属性失败
    public static final int NET_ERROR_GETCFG_INFRARED           = (0x80000000|305); // 获取红外报警配置失败
    public static final int NET_ERROR_GETCFG_SOUNDALARM         = (0x80000000|306); // 获取音频报警配置失败
    public static final int NET_ERROR_GETCFG_STORAGE            = (0x80000000|307); // 获取存储位置配置失败
    public static final int NET_ERROR_GETCFG_MAIL               = (0x80000000|308); // 获取邮件配置失败
    public static final int NET_CONFIG_DEVBUSY                  = (0x80000000|309); // 暂时无法设置
    public static final int NET_CONFIG_DATAILLEGAL              = (0x80000000|310); // 配置数据不合法
    public static final int NET_ERROR_GETCFG_DST                = (0x80000000|311); // 获取夏令时配置失败
    public static final int NET_ERROR_SETCFG_DST                = (0x80000000|312); // 设置夏令时配置失败
    public static final int NET_ERROR_GETCFG_VIDEO_OSD          = (0x80000000|313); // 获取视频OSD叠加配置失败
    public static final int NET_ERROR_SETCFG_VIDEO_OSD          = (0x80000000|314); // 设置视频OSD叠加配置失败
    public static final int NET_ERROR_GETCFG_GPRSCDMA           = (0x80000000|315); // 获取CDMA\GPRS网络配置失败
    public static final int NET_ERROR_SETCFG_GPRSCDMA           = (0x80000000|316); // 设置CDMA\GPRS网络配置失败
    public static final int NET_ERROR_GETCFG_IPFILTER           = (0x80000000|317); // 获取IP过滤配置失败
    public static final int NET_ERROR_SETCFG_IPFILTER           = (0x80000000|318); // 设置IP过滤配置失败
    public static final int NET_ERROR_GETCFG_TALKENCODE         = (0x80000000|319); // 获取语音对讲编码配置失败
    public static final int NET_ERROR_SETCFG_TALKENCODE         = (0x80000000|320); // 设置语音对讲编码配置失败
    public static final int NET_ERROR_GETCFG_RECORDLEN          = (0x80000000|321); // 获取录像打包长度配置失败
    public static final int NET_ERROR_SETCFG_RECORDLEN          = (0x80000000|322); // 设置录像打包长度配置失败
    public static final int NET_DONT_SUPPORT_SUBAREA            = (0x80000000|323); // 不支持网络硬盘分区
    public static final int NET_ERROR_GET_AUTOREGSERVER         = (0x80000000|324); // 获取设备上主动注册服务器信息失败
    public static final int NET_ERROR_CONTROL_AUTOREGISTER      = (0x80000000|325); // 主动注册重定向注册错误
    public static final int NET_ERROR_DISCONNECT_AUTOREGISTER   = (0x80000000|326); // 断开主动注册服务器错误
    public static final int NET_ERROR_GETCFG_MMS                = (0x80000000|327); // 获取mms配置失败
    public static final int NET_ERROR_SETCFG_MMS                = (0x80000000|328); // 设置mms配置失败
    public static final int NET_ERROR_GETCFG_SMSACTIVATION      = (0x80000000|329); // 获取短信激活无线连接配置失败
    public static final int NET_ERROR_SETCFG_SMSACTIVATION      = (0x80000000|330); // 设置短信激活无线连接配置失败
    public static final int NET_ERROR_GETCFG_DIALINACTIVATION   = (0x80000000|331); // 获取拨号激活无线连接配置失败
    public static final int NET_ERROR_SETCFG_DIALINACTIVATION   = (0x80000000|332); // 设置拨号激活无线连接配置失败
    public static final int NET_ERROR_GETCFG_VIDEOOUT           = (0x80000000|333); // 查询视频输出参数配置失败
    public static final int NET_ERROR_SETCFG_VIDEOOUT           = (0x80000000|334); // 设置视频输出参数配置失败
    public static final int NET_ERROR_GETCFG_OSDENABLE          = (0x80000000|335); // 获取osd叠加使能配置失败
    public static final int NET_ERROR_SETCFG_OSDENABLE          = (0x80000000|336); // 设置osd叠加使能配置失败
    public static final int NET_ERROR_SETCFG_ENCODERINFO        = (0x80000000|337); // 设置数字通道前端编码接入配置失败
    public static final int NET_ERROR_GETCFG_TVADJUST           = (0x80000000|338); // 获取TV调节配置失败
    public static final int NET_ERROR_SETCFG_TVADJUST           = (0x80000000|339); // 设置TV调节配置失败
    public static final int NET_ERROR_CONNECT_FAILED            = (0x80000000|340); // 请求建立连接失败
    public static final int NET_ERROR_SETCFG_BURNFILE           = (0x80000000|341); // 请求刻录文件上传失败
    public static final int NET_ERROR_SNIFFER_GETCFG            = (0x80000000|342); // 获取抓包配置信息失败
    public static final int NET_ERROR_SNIFFER_SETCFG            = (0x80000000|343); // 设置抓包配置信息失败
    public static final int NET_ERROR_DOWNLOADRATE_GETCFG       = (0x80000000|344); // 查询下载限制信息失败
    public static final int NET_ERROR_DOWNLOADRATE_SETCFG       = (0x80000000|345); // 设置下载限制信息失败
    public static final int NET_ERROR_SEARCH_TRANSCOM           = (0x80000000|346); // 查询串口参数失败
    public static final int NET_ERROR_GETCFG_POINT              = (0x80000000|347); // 获取预制点信息错误
    public static final int NET_ERROR_SETCFG_POINT              = (0x80000000|348); // 设置预制点信息错误
    public static final int NET_SDK_LOGOUT_ERROR                = (0x80000000|349); // SDK没有正常登出设备
    public static final int NET_ERROR_GET_VEHICLE_CFG           = (0x80000000|350); // 获取车载配置失败
    public static final int NET_ERROR_SET_VEHICLE_CFG           = (0x80000000|351); // 设置车载配置失败
    public static final int NET_ERROR_GET_ATM_OVERLAY_CFG       = (0x80000000|352); // 获取atm叠加配置失败
    public static final int NET_ERROR_SET_ATM_OVERLAY_CFG       = (0x80000000|353); // 设置atm叠加配置失败
    public static final int NET_ERROR_GET_ATM_OVERLAY_ABILITY   = (0x80000000|354); // 获取atm叠加能力失败
    public static final int NET_ERROR_GET_DECODER_TOUR_CFG      = (0x80000000|355); // 获取解码器解码轮巡配置失败
    public static final int NET_ERROR_SET_DECODER_TOUR_CFG      = (0x80000000|356); // 设置解码器解码轮巡配置失败
    public static final int NET_ERROR_CTRL_DECODER_TOUR         = (0x80000000|357); // 控制解码器解码轮巡失败
    public static final int NET_GROUP_OVERSUPPORTNUM            = (0x80000000|358); // 超出设备支持最大用户组数目
    public static final int NET_USER_OVERSUPPORTNUM             = (0x80000000|359); // 超出设备支持最大用户数目
    public static final int NET_ERROR_GET_SIP_CFG               = (0x80000000|368); // 获取SIP配置失败
    public static final int NET_ERROR_SET_SIP_CFG               = (0x80000000|369); // 设置SIP配置失败
    public static final int NET_ERROR_GET_SIP_ABILITY           = (0x80000000|370); // 获取SIP能力失败
    public static final int NET_ERROR_GET_WIFI_AP_CFG           = (0x80000000|371); // 获取WIFI ap配置失败
    public static final int NET_ERROR_SET_WIFI_AP_CFG           = (0x80000000|372); // 设置WIFI ap配置失败
    public static final int NET_ERROR_GET_DECODE_POLICY         = (0x80000000|373); // 获取解码策略配置失败
    public static final int NET_ERROR_SET_DECODE_POLICY         = (0x80000000|374); // 设置解码策略配置失败
    public static final int NET_ERROR_TALK_REJECT               = (0x80000000|375); // 拒绝对讲
    public static final int NET_ERROR_TALK_OPENED               = (0x80000000|376); // 对讲被其他客户端打开
    public static final int NET_ERROR_TALK_RESOURCE_CONFLICIT   = (0x80000000|377); // 资源冲突
    public static final int NET_ERROR_TALK_UNSUPPORTED_ENCODE   = (0x80000000|378); // 不支持的语音编码格式
    public static final int NET_ERROR_TALK_RIGHTLESS            = (0x80000000|379); // 无权限
    public static final int NET_ERROR_TALK_FAILED               = (0x80000000|380); // 请求对讲失败
    public static final int NET_ERROR_GET_MACHINE_CFG           = (0x80000000|381); // 获取机器相关配置失败
    public static final int NET_ERROR_SET_MACHINE_CFG           = (0x80000000|382); // 设置机器相关配置失败
    public static final int NET_ERROR_GET_DATA_FAILED           = (0x80000000|383); // 设备无法获取当前请求数据
    public static final int NET_ERROR_MAC_VALIDATE_FAILED       = (0x80000000|384); // MAC地址验证失败
    public static final int NET_ERROR_GET_INSTANCE              = (0x80000000|385); // 获取服务器实例失败
    public static final int NET_ERROR_JSON_REQUEST              = (0x80000000|386); // 生成的jason字符串错误
    public static final int NET_ERROR_JSON_RESPONSE             = (0x80000000|387); // 响应的jason字符串错误
    public static final int NET_ERROR_VERSION_HIGHER            = (0x80000000|388); // 协议版本低于当前使用的版本
    public static final int NET_SPARE_NO_CAPACITY               = (0x80000000|389); // 热备操作失败, 容量不足
    public static final int NET_ERROR_SOURCE_IN_USE             = (0x80000000|390); // 显示源被其他输出占用
    public static final int NET_ERROR_REAVE                     = (0x80000000|391); // 高级用户抢占低级用户资源
    public static final int NET_ERROR_NETFORBID                 = (0x80000000|392); // 禁止入网
    public static final int NET_ERROR_GETCFG_MACFILTER          = (0x80000000|393); // 获取MAC过滤配置失败
    public static final int NET_ERROR_SETCFG_MACFILTER          = (0x80000000|394); // 设置MAC过滤配置失败
    public static final int NET_ERROR_GETCFG_IPMACFILTER        = (0x80000000|395); // 获取IP/MAC过滤配置失败
    public static final int NET_ERROR_SETCFG_IPMACFILTER        = (0x80000000|396); // 设置IP/MAC过滤配置失败
    public static final int NET_ERROR_OPERATION_OVERTIME        = (0x80000000|397); // 当前操作超时
    public static final int NET_ERROR_SENIOR_VALIDATE_FAILED    = (0x80000000|398); // 高级校验失败
    public static final int NET_ERROR_DEVICE_ID_NOT_EXIST       = (0x80000000|399); // 设备ID不存在
    public static final int NET_ERROR_UNSUPPORTED               = (0x80000000|400); // 不支持当前操作
    public static final int NET_ERROR_PROXY_DLLLOAD             = (0x80000000|401); // 代理库加载失败
    public static final int NET_ERROR_PROXY_ILLEGAL_PARAM       = (0x80000000|402); // 代理用户参数不合法
    public static final int NET_ERROR_PROXY_INVALID_HANDLE      = (0x80000000|403); // 代理句柄无效
    public static final int NET_ERROR_PROXY_LOGIN_DEVICE_ERROR  = (0x80000000|404); // 代理登入前端设备失败
    public static final int NET_ERROR_PROXY_START_SERVER_ERROR  = (0x80000000|405); // 启动代理服务失败

    // CLIENT_StartListenEx报警事件
    public static final int NET_ALARM_ALARM_EX 					= 0x2101;     		//外部报警，数据字节数与设备报警通道个数相同，每个字节表示一个报警通道的报警状态，1为有报警，0为无报警。
    public static final int NET_VIDEOLOST_ALARM_EX 				= 0x2103; 			// 视频丢失报警，数据字节数与设备视频通道个数相同，每个字节表示一个视频通道的视频丢失报警状态，1为有报警，0为无报警。
    public static final int NET_SHELTER_ALARM_EX 				= 0x2104;   		// 视频遮挡报警，数据字节数与设备视频通道个数相同，每个字节表示一个视频通道的遮挡(黑屏)报警状态，1为有报警，0为无报警。
    public static final int NET_DISKFULL_ALARM_EX 				= 0x2106;  			// 硬盘满报警，数据为1个字节，1为有硬盘满报警，0为无报警。
    public static final int NET_DISKERROR_ALARM_EX 				= 0x2107; 			// 坏硬盘报警，数据为32个字节，每个字节表示一个硬盘的故障报警状态，1为有报警，0为无报警。
    public static final int NET_ALARM_BATTERYLOWPOWER 			= 0x2134;      		// 电池电量低报警(对应结构体ALARM_BATTERYLOWPOWER_INFO)
    public static final int NET_ALARM_TEMPERATURE 				= 0x2135;  			// 温度过高报警(对应结构体ALARM_TEMPERATURE_INFO)
    public static final int NET_ALARM_ALARM_EX2 				= 0x2175;    		// 本地报警事件(对应结构体ALARM_ALARM_INFO_EX2,对NET_ALARM_ALARM_EX升级)
    public static final int NET_EVENT_VIDEOABNORMALDETECTION    = 0x218e;           // 视频异常事件(对应ALARM_VIDEOABNORMAL_DETECTION_INFO)
    public static final int NET_ALARM_NEW_FILE                  = 0x31b3;           // 新文件事件(对应ALARM_NEW_FILE_INFO)
    public static final int NET_ALARM_HUMAM_NUMBER_STATISTIC    = 0x31cc;           // 人数量/客流量统计事件 (对应结构体 ALARM_HUMAN_NUMBER_STATISTIC_INFO)
    
    
    // CLIENT_RealLoadPictureEx 智能抓图事件
    public static final int EVENT_IVS_ALL                       = 0x00000001;       // 订阅所有事件
    public static final int EVENT_IVS_CROSSLINEDETECTION        = 0x00000002;       // 警戒线事件(对应 DEV_EVENT_CROSSLINE_INFO)
    public static final int EVENT_IVS_CROSSREGIONDETECTION      = 0x00000003;       // 警戒区事件(对应 DEV_EVENT_CROSSREGION_INFO)
    public static final int EVENT_IVS_WANDERDETECTION           = 0x00000007;       // 徘徊事件(对应  DEV_EVENT_WANDER_INFO)
    public static final int EVENT_IVS_FIGHTDETECTION            = 0x0000000E;       // 斗殴事件(对应 DEV_EVENT_FIGHT_INFO)  
    public static final int EVENT_IVS_TRAFFICJUNCTION           = 0x00000017;       // 交通路口事件----老规则(对应 DEV_EVENT_TRAFFICJUNCTION_INFO)
    public static final int EVENT_IVS_TRAFFICGATE               = 0x00000018;       // 交通卡口事件----老规则(对应 DEV_EVENT_TRAFFICGATE_INFO)
    public static final int EVENT_IVS_FACEDETECT                = 0x0000001A;       // 人脸检测事件 (对应 DEV_EVENT_FACEDETECT_INFO)
    public static final int EVENT_IVS_TRAFFIC_RUNREDLIGHT       = 0x00000100;       // 交通违章-闯红灯事件(对应 DEV_EVENT_TRAFFIC_RUNREDLIGHT_INFO)
    public static final int EVENT_IVS_TRAFFIC_OVERLINE          = 0x00000101;       // 交通违章-压车道线事件(对应 DEV_EVENT_TRAFFIC_OVERLINE_INFO)
    public static final int EVENT_IVS_TRAFFIC_OVERSPEED         = 0x00000106;       // 交通违章-超速(对应 DEV_EVENT_TRAFFIC_OVERSPEED_INFO)
    public static final int EVENT_IVS_TRAFFIC_PARKING           = 0x00000108;       // 交通违章-违章停车(对应 DEV_EVENT_TRAFFIC_PARKING_INFO)
    public static final int EVENT_IVS_TRAFFIC_WRONGROUTE        = 0x00000109;       // 交通违章-不按车道行驶(对应 DEV_EVENT_TRAFFIC_WRONGROUTE_INFO)
    public static final int EVENT_IVS_TRAFFIC_OVERYELLOWLINE    = 0x0000010B;       // 交通违章-压黄线 (对应 DEV_EVENT_TRAFFIC_OVERYELLOWLINE_INFO)
    public static final int EVENT_IVS_TRAFFIC_YELLOWPLATEINLANE = 0x0000010E;       // 交通违章-黄牌车占道事件(对应 DEV_EVENT_TRAFFIC_YELLOWPLATEINLANE_INFO)
    public static final int EVENT_IVS_FACERECOGNITION           = 0x00000117;       // 人脸识别事件(对应 DEV_EVENT_FACERECOGNITION_INFO
    public static final int EVENT_IVS_TRAFFIC_MANUALSNAP        = 0x00000118;       // 交通手动抓拍事件(对应  DEV_EENT_TRAFFIC_MANUALSNAP_INFO)
    public static final int EVENT_IVS_TRAFFIC_FLOWSTATE         = 0x00000119;       // 交通流量统计事件(对应 DEV_EVENT_TRAFFIC_FLOW_STATE)
    public static final int EVENT_IVS_TRAFFIC_TOLLGATE          = 0x00000120;       // 交通违章-卡口事件----新规则(对应 DEV_EVENT_TRAFFICJUNCTION_INFO)
    public static final int EVENT_IVS_AUDIO_ABNORMALDETECTION   = 0x00000126;       // 声音异常检测(对应 DEV_EVENT_IVS_AUDIO_ABNORMALDETECTION_INFO)
    public static final int EVENT_IVS_CLIMBDETECTION            = 0x00000128;       // 攀高检测事件(对应 DEV_EVENT_IVS_CLIMB_INFO)
    public static final int EVENT_IVS_LEAVEDETECTION            = 0x00000129;       // 离岗检测事件(对应 DEV_EVENT_IVS_LEAVE_INFO)
    public static final int EVENT_IVS_TRAFFIC_VEHICLEINROUTE    = 0x0000011B;       // 有车占道事件(对应 DEV_EVENT_TRAFFIC_VEHICLEINROUTE_INFO)
    public static final int EVENT_IVS_TRAFFIC_PARKINGSPACEPARKING = 0x0000012B;     // 车位有车事件(对应 DEV_EVENT_TRAFFIC_PARKINGSPACEPARKING_INFO )
    public static final int EVENT_IVS_TRAFFIC_PARKINGSPACENOPARKING = 0x0000012C;   // 车位无车事件(对应  DEV_EVENT_TRAFFIC_PARKINGSPACENOPARKING_INFO )
  
    public static final int EVENT_IVS_TRAFFIC_FCC               = 0x0000016B;       // 加油站提枪、挂枪事件(对应  DEV_EVENT_TRAFFIC_FCC_INFO)
    
    // CLIENT_GetNewDevConfig/CLIENT_SetNewDevConfig 配置项
    public static final String CFG_CMD_VIDEOWIDGET              = "VideoWidget";         // 视频编码物件配置(对应 NET_CFG_VideoWidget )
    public static final String CFG_CMD_ANALYSEMODULE            = "VideoAnalyseModule";  // 物体的检测模块配置(对应CFG_ANALYSEMODULES_INFO)
    public static final String CFG_CMD_ANALYSERULE              = "VideoAnalyseRule";    // 视频分析规则配置(对应 CFG_ANALYSERULES_INFO)
    public static final String CFG_CMD_VIDEOINOPTIONS           = "VideoInOptions";      // 视频输入前端选项(对应CFG_VIDEO_IN_OPTIONS)
    public static final String CFG_CMD_RAINBRUSHMODE            = "RainBrushMode";       // 雨刷模式相关配置(对应CFG_RAINBRUSHMODE_INFO数组)
    public static final String CFG_CMD_RAINBRUSH                = "RainBrush";           // 雨刷配置(对应CFG_RAINBRUSH_INFO)
    public static final String CFG_CMD_ENCODE                   = "Encode";              // 图像通道属性配置(对应CFG_ENCODE_INFO)
    public static final String CFG_CMD_VIDEO_IN_ZOOM            = "VideoInZoom";         // 云台通道变倍配置(对应CFG_VIDEO_IN_ZOOM)
    public static final String CFG_CMD_REMOTEDEVICE				= "RemoteDevice";		 // 远程设备信息(对应  AV_CFG_RemoteDevice 数组, 通道无关)
    public static final String CFG_CMD_ANALYSESOURCE			= "VideoAnalyseSource";  // 视频分析资源配置(对应 CFG_ANALYSESOURCE_INFO)
    
    // CLIENT_FileTransmit接口传输文件类型
    public static final int NET_DEV_BLACKWHITETRANS_START      = 0x0003;           // 开始发送黑白名单(对应结构体 DHDEV_BLACKWHITE_LIST_INFO)
    public static final int NET_DEV_BLACKWHITETRANS_SEND       = 0x0004;           // 发送黑白名单
    public static final int NET_DEV_BLACKWHITETRANS_STOP       = 0x0005;           // 停止发送黑白名单
    
    // 配置类型,对应CLIENT_GetDevConfig和CLIENT_SetDevConfig接口
    public static final int NET_DEV_DEVICECFG                   = 0x0001;                // 设备属性配置

    // 命令类型, 对应 CLIENT_QueryNewSystemInfo 接口
    public static final String CFG_CAP_CMD_DEVICE_STATE         = "trafficSnap.getDeviceStatus"; // 获取设备状态信息 (对应 CFG_CAP_TRAFFIC_DEVICE_STATUS)
    
    // 查询类型,对应CLIENT_QueryDevState接口
	public static final int NET_DEVSTATE_TALK_ECTYPE            = 0x0009;                // 查询设备支持的语音对讲格式列表(对应NET_DEV_TALKFORMAT_LIST)///////////////////////////////
    public static final int NET_DEVSTATE_DSP                    = 0x0011;                // 查询DSP能力描述(对应结构体NET_DEV_DSP_ENCODECAP)
    public static final int NET_DEVSTATE_ONLINE                 = 0x0035;                // 查询设备的在线状态(返回一个DWORD,1表示在线, 0表示断线)
    public static final int NET_DEVSTATE_VIRTUALCAMERA          = 0x003a;           	 // 查询虚拟摄像头状态(对应NET_DEV_VIRTUALCAMERA_STATE_INFO)
    public static final int NET_DEVSTATE_VIDEOLOST 				= 0x003f; 				 // 获取视频丢失报警状态(对应NET_CLIENT_VIDEOLOST_STATE)
    public static final int NET_DEVSTATE_VIDEOBLIND 			= 0x0043;        		 // 获取视频遮挡报警状态(对应NET_CLIENT_VIDEOBLIND_STATE)
 
    // 查询设备信息类型, 对应接口 CLIENT_QueryDevInfo
 // 设备信息类型,对应CLIENT_QueryDevInfo接口
    public static final int NET_QUERY_DEV_STORAGE_NAMES                 = 0x01;                // 查询设备的存储模块名列表 , pInBuf=NET_IN_STORAGE_DEV_NAMES *, pOutBuf=NET_OUT_STORAGE_DEV_NAMES *
    public static final int NET_QUERY_DEV_STORAGE_INFOS                 = 0x02;                // 查询设备的存储模块信息列表, pInBuf=NET_IN_STORAGE_DEV_INFOS*, pOutBuf= NET_OUT_STORAGE_DEV_INFOS *
    public static final int NET_QUERY_RECENCY_JNNCTION_CAR_INFO         = 0x03;                // 查询最近的卡口车辆信息接口, pInBuf=NET_IN_GET_RECENCY_JUNCTION_CAR_INFO*, pOutBuf=NET_OUT_GET_RECENCY_JUNCTION_CAR_INFO*
    public static final int NET_QUERY_LANES_STATE                       = 0x04;                // 查询车道信息,pInBuf = NET_IN_GET_LANES_STATE , pOutBuf = NET_OUT_GET_LANES_STATE
    public static final int NET_QUERY_DEV_FISHEYE_WININFO               = 0x05;                // 查询鱼眼窗口信息 , pInBuf= NET_IN_FISHEYE_WININFO*, pOutBuf=NET_OUT_FISHEYE_WININFO *
    public static final int NET_QUERY_DEV_REMOTE_DEVICE_INFO            = 0x06;;               // 查询远程设备信息 , pInBuf= NET_IN_GET_DEVICE_INFO*, pOutBuf= NET_OUT_GET_DEVICE_INFO *
    public static final int NET_QUERY_SYSTEM_INFO                       = 0x07;                // 查询设备系统信息 , pInBuf= NET_IN_SYSTEM_INFO*, pOutBuf= NET_OUT_SYSTEM_INFO*
    public static final int NET_QUERY_REG_DEVICE_NET_INFO               = 0x08;                // 查询主动注册设备的网络连接 , pInBuf=NET_IN_REGDEV_NET_INFO * , pOutBuf=NET_OUT_REGDEV_NET_INFO *
    public static final int NET_QUERY_DEV_THERMO_GRAPHY_PRESET          = 0x09;                // 查询热成像预设信息 , pInBuf= NET_IN_THERMO_GET_PRESETINFO*, pOutBuf= NET_OUT_THERMO_GET_PRESETINFO *
    public static final int NET_QUERY_DEV_THERMO_GRAPHY_OPTREGION       = 0x0a;                // 查询热成像感兴趣区域信息,pInBuf= NET_IN_THERMO_GET_OPTREGION*, pOutBuf= NET_OUT_THERMO_GET_OPTREGION *
    public static final int NET_QUERY_DEV_THERMO_GRAPHY_EXTSYSINFO      = 0x0b;                // 查询热成像外部系统信息, pInBuf= NET_IN_THERMO_GET_EXTSYSINFO*, pOutBuf= NET_OUT_THERMO_GET_EXTSYSINFO *
    public static final int NET_QUERY_DEV_RADIOMETRY_POINT_TEMPER       = 0x0c;                // 查询测温点的参数值, pInBuf= NET_IN_RADIOMETRY_GETPOINTTEMPER*, pOutBuf= NET_OUT_RADIOMETRY_GETPOINTTEMPER *
    public static final int NET_QUERY_DEV_RADIOMETRY_TEMPER             = 0x0d;                // 查询测温项的参数值, pInBuf= NET_IN_RADIOMETRY_GETTEMPER*, pOutBuf= NET_OUT_RADIOMETRY_GETTEMPER *
    public static final int NET_QUERY_GET_CAMERA_STATE                  = 0x0e;                // 获取摄像机状态, pInBuf= NET_IN_GET_CAMERA_STATEINFO*, pOutBuf= NET_OUT_GET_CAMERA_STATEINFO *
    public static final int NET_QUERY_GET_REMOTE_CHANNEL_AUDIO_ENCODE   = 0x0f;                // 获取远程通道音频编码方式, pInBuf= NET_IN_GET_REMOTE_CHANNEL_AUDIO_ENCODEINFO*, pOutBuf= NET_OUT_GET_REMOTE_CHANNEL_AUDIO_ENCODEINFO *
    public static final int NET_QUERY_GET_COMM_PORT_INFO                = 0x10;                // 获取设备串口信息, pInBuf=NET_IN_GET_COMM_PORT_INFO* , pOutBuf=NET_OUT_GET_COMM_PORT_INFO* 
    public static final int NET_QUERY_GET_LINKCHANNELS                  = 0x11;                // 查询某视频通道的关联通道列表,pInBuf=NET_IN_GET_LINKCHANNELS* , pOutBuf=NET_OUT_GET_LINKCHANNELS*
    public static final int NET_QUERY_GET_VIDEOOUTPUTCHANNELS           = 0x12;                // 获取解码通道数量统计信息, pInBuf=NET_IN_GET_VIDEOOUTPUTCHANNELS*, pOutBuf=NET_OUT_GET_VIDEOOUTPUTCHANNELS*
    public static final int NET_QUERY_GET_VIDEOINFO                     = 0x13;                // 获取解码通道信息, pInBuf=NET_IN_GET_VIDEOINFO*, pOutBuf=NET_OUT_GET_VIDEOINFO*
    public static final int NET_QUERY_GET_ALLLINKCHANNELS               = 0x14;                // 查询全部视频关联通道列表,pInBuf=NET_IN_GET_ALLLINKCHANNELS* , pOutBuf=NET_OUT_GET_ALLLINKCHANNELS*
    public static final int NET_QUERY_VIDEOCHANNELSINFO                 = 0x15;                // 查询视频通道信息,pInBuf=NET_IN_GET_VIDEOCHANNELSINFO* , pOutBuf=NET_OUT_GET_VIDEOCHANNELSINFO*
    public static final int NET_QUERY_TRAFFICRADAR_VERSION              = 0x16;                // 查询雷达设备版本,pInBuf=NET_IN_TRAFFICRADAR_VERSION* , pOutBuf=NET_OUT_TRAFFICRADAR_VERSION*
    public static final int NET_QUERY_WORKGROUP_NAMES                   = 0x17;                // 查询所有的工作目录组名,pInBuf=NET_IN_WORKGROUP_NAMES* , pOutBuf=NET_OUT_WORKGROUP_NAMES*
    public static final int NET_QUERY_WORKGROUP_INFO                    = 0x18;                // 查询工作组信息,pInBuf=NET_IN_WORKGROUP_INFO* , pOutBuf=NET_OUT_WORKGROUP_INFO*
    public static final int NET_QUERY_WLAN_ACCESSPOINT                  = 0x19;                // 查询无线网络接入点信息,pInBuf=NET_IN_WLAN_ACCESSPOINT* , pOutBuf=NET_OUT_WLAN_ACCESSPOINT*
    public static final int NET_QUERY_GPS_INFO							= 0x1a;				   // 查询设备GPS信息,pInBuf=NET_IN_DEV_GPS_INFO* , pOutBuf=NET_OUT_DEV_GPS_INFO*
    public static final int NET_QUERY_IVS_REMOTE_DEVICE_INFO            = 0x1b;                // 查询IVS的前端设备所关联的远程设备信息, pInBuf = NET_IN_IVS_REMOTE_DEV_INFO*, pOutBuf = NET_OUT_IVS_REMOTE_DEV_INFO*
   
    //其他定义
    public static final int NET_MAX_NAME_LEN                    = 16;   // 通用名字字符串长度
    public static final int NET_MAX_PERSON_ID_LEN               = 32;   // 人员id最大长度
    public static final int NET_MAX_PERSON_IMAGE_NUM            = 48;   // 每个人员对应的最大人脸图片数
    public static final int NET_MAX_PROVINCE_NAME_LEN           = 64;   // 省份名称最大长度
    public static final int NET_MAX_CITY_NAME_LEN               = 64;   // 城市名称最大长度
    public static final int NET_MAX_PERSON_NAME_LEN             = 64;   // 人员名字最大长度
    public static final int MAX_FACE_AREA_NUM                   = 8;    // 最大人脸区域个数
    public static final int MAX_PATH                            = 260;
    public static final int MAX_FACE_DB_NUM                     = 8;    // 最大人脸数据库个数
    public static final int MAX_GOURP_NUM                       = 128;  // 人脸库最大个数
    public static final int NET_COMMON_STRING_64                = 64;   // 通用字符串长度64
    public static final int MAX_FIND_COUNT                      = 20;
    public static final int NET_MAX_POLYGON_NUM                 = 16;   // 多边形最大顶点个数
    public static final int NET_MAX_CANDIDATE_NUM               = 50;   // 人脸识别最大匹配数
    public static final int MAX_POLYGON_NUM                     = 20;   // 视频分析设备区域顶点个数上限
    public static final int MAX_CALIBRATEBOX_NUM                = 10;   // 智能分析校准框个数上限
    public static final int MAX_NAME_LEN                        = 128;  // 通用名字字符串长度
    public static final int MAX_EXCLUDEREGION_NUM               = 10;   // 智能分析检测区域中需要排除的区域个数上限
    public static final int MAX_OBJECT_LIST_SIZE                = 16;   // 视频分析设备支持的检测物体类型列表个数上限
    public static final int MAX_SPECIALDETECT_NUM               = 10;   // 智能分析特殊检测区域上限
    public static final int MAX_OBJECT_ATTRIBUTES_SIZE          = 16;   // 视频分析设备支持的检测物体属性类型列表个数上限
    public static final int MAX_CATEGORY_TYPE_NUMBER            = 128;  // 子类别类型数
    public static final int MAX_ANALYSE_MODULE_NUM              = 16;   // 视频分析设备最大检测模块个数
    public static final int NET_COMMON_STRING_128               = 128;  // 通用字符串长度128
    public static final int NET_COMMON_STRING_256               = 256;  // 通用字符串长度256
    public static final int MAX_LOG_PATH_LEN                    = 260;  // 日志路径名最大长度
    public static final int MAX_CHANNELNAME_LEN                 = 64;   // 最大通道名称长度
    public static final int MAX_VIDEO_CHANNEL_NUM               = 256;  // 最大通道数256
    public static final int MAX_PSTN_SERVER_NUM                 = 8;    // 最大报警电话服务器数
    public static final int MAX_TIME_SCHEDULE_NUM               = 8;    // 时间表元素个数
    public static final int MAX_REC_TSECT                       = 6;    // 录像时间段个数
    public static final int MAX_REC_TSECT_EX                    = 10;   // 录像时间段扩展个数
    public static final int MAX_CHANNEL_COUNT                   = 16;
    public static final int MAX_ACCESSCONTROL_NUM               = 8;    // 最大门禁操作的组合数
    public static final int MAX_DBKEY_NUM                       = 64;   // 数据库关键字最大值
    public static final int MAX_SUMMARY_LEN                     = 1024; // 叠加到JPEG图片的摘要信息最大长度
    public static final int WEEK_DAY_NUM                        = 7;    // 一周的天数
    public static final int NET_MAX_FACEDETECT_FEATURE_NUM      = 32;   // 人脸特征最大个数
    public static final int NET_MAX_OBJECT_LIST                 = 16;   // 智能分析设备检测到的物体ID个数上限    
    public static final int NET_MAX_RULE_LIST                   = 16;   // 智能分析设备规则个数上限
    public static final int NET_MAX_DETECT_REGION_NUM           = 20;   // 规则检测区域最大顶点数
    public static final int NET_MAX_DETECT_LINE_NUM             = 20;   // 规则检测线最大顶点数
    public static final int NET_MAX_TRACK_LINE_NUM              = 20;   // 物体运动轨迹最大顶点数
    public static final int NET_MACADDR_LEN                     = 40;   // MAC地址字符串长度
    public static final int NET_DEV_TYPE_LEN                    = 32;   // 设备型号字符串（如"IPC-F725"）长度
    public static final int NET_DEV_SERIALNO_LEN                = 48;   // 序列号字符串长度
    public static final int NET_MAX_URL_LEN                     = 128;  // URL字符串长度
    public static final int NET_MAX_STRING_LEN                  = 128;
    public static final int NET_MACHINE_NAME_NUM                = 64;   // 机器名称长度
    public static final int NET_USER_NAME_LENGTH_EX             = 16;   // 用户名长度
    public static final int NET_EVENT_NAME_LEN                  = 128;  // 事件名称长度
    public static final int NET_MAX_LANE_NUM                    = 8;    // 视频分析设备每个通道对应车道数上限
    public static final int MAX_DRIVING_DIR_NUM                 = 16;   // 车道行驶方向最大个数
    public static final int FLOWSTAT_ADDR_NAME                  = 16;   // 上下行地点名长
    public static final int NET_MAX_DRIVINGDIRECTION            = 256;  // 行驶方向字符串长度
    public static final int COMMON_SEAT_MAX_NUMBER              = 8;    // 默认检测最大座驾个数
    public static final int NET_MAX_ATTACHMENT_NUM              = 8;    // 最大车辆物件数量
    public static final int NET_MAX_ANNUUALINSPECTION_NUM       = 8;    // 最大年检标识位置
    public static final int NET_COMMON_STRING_32                = 32;   // 通用字符串长度32
    public static final int NET_COMMON_STRING_16                = 16;   // 通用字符串长度16
    public static final int MAX_VIDEOSTREAM_NUM                 = 3;    // 最大码流个数
    public static final int MAX_VIDEO_COVER_NUM                 = 16;   // 最大遮挡区域个数
    public static final int MAX_VIDEO_IN_ZOOM                   = 32;   // 单通道最大变速配置个数
    public static final int NET_EVENT_CARD_LEN                  = 36;   // 卡片名称长度
    public static final int NET_EVENT_MAX_CARD_NUM              = 16;   // 事件上报信息包含最大卡片个数
    public static final int MAX_STATUS_NUM                      = 16;   // 交通设备状态最大个数
    public static final int NET_MAX_CHANMASK 					= 64;   // 通道掩码最大值
    public static final int NET_CHAN_NAME_LEN                   = 32;   // 通道名长度,DVR DSP能力限制,最多32字节 

    public static final int NET_NEW_MAX_RIGHT_NUM               = 1024; // 用户权限个数上限/////////////
    public static final int NET_MAX_GROUP_NUM                   = 20;   // 用户组个数上限///////////////
    public static final int NET_MAX_USER_NUM                    = 200;  // 用户个数上限////////////////
    public static final int NET_RIGHT_NAME_LENGTH               = 32;   // 权限名长度//////////////////
    public static final int NET_MEMO_LENGTH                     = 32;   // 备注长度///////////////////
    public static final int NET_NEW_USER_NAME_LENGTH            = 128;  // 用户名长度/////////////////
    public static final int NET_NEW_USER_PSW_LENGTH             = 128;  // 密码   ////////////////////

    public static final int AV_CFG_Device_ID_Len				= 64;   // 设备ID长度
    public static final int AV_CFG_IP_Address_Len				= 32;   // IP 长度
    public static final int AV_CFG_Protocol_Len 				= 32;   // 协议名长度
    public static final int AV_CFG_User_Name_Len 				= 64;   // 用户名长度
    public static final int	AV_CFG_Password_Len 				= 64;   // 密码长度
    public static final int AV_CFG_Serial_Len					= 32;	// 序列号长度
    public static final int AV_CFG_Device_Class_Len				= 16;   // 设备类型长度
    public static final int AV_CFG_Device_Type_Len				= 32;	// 设备具体型号长度
    public static final int AV_CFG_Device_Name_Len				= 128;	// 机器名称
    public static final int AV_CFG_Address_Len					= 128;	// 机器部署地点
    public static final int AV_CFG_Max_Path						= 260;	// 路径长度
    public static final int AV_CFG_Group_Name_Len               = 64;   // 分组名称长度
    public static final int AV_CFG_DeviceNo_Len                 = 32;   // 设备编号长度
    public static final int AV_CFG_Group_Memo_Len               = 128;  // 分组说明长度
    public static final int AV_CFG_Max_Channel_Num              = 1024; // 最大通道数量
    public static final int MAX_DEVICE_NAME_LEN					= 64;   // 机器名称
    public static final int MAX_DEV_ID_LEN_EX					= 128;  // 设备ID最大长度
    public static final int MAX_PATH_STOR                       = 240;  // 远程目录的长度
    public static final int	MAX_REMOTE_DEV_NUM       			= 256;  // 最大远程设备数量
    public static final int NET_MAX_ALARMOUT_NUM_EX 			= 32;   //报警输出口个数上限扩展
    public static final int NET_MAX_PLATE_NUMBER_LEN            = 32;   // 车牌字符长度    
    public static final int NET_MAX_VIDEO_IN_NUM_EX 			= 32;   //视频输入口个数上限扩展
    public static final int NET_MAX_AUTHORITY_LIST_NUM          = 16;   // 权限列表最大个数    
    
    /************************************************************************
     ** 结构体
     ***********************************************************************/
    // 设置登入时的相关参数
    public static class NET_PARAM  extends Structure
    {
        public int                    nWaittime;                // 等待超时时间(毫秒为单位)，为0默认5000ms
        public int                    nConnectTime;            // 连接超时时间(毫秒为单位)，为0默认1500ms
        public int                    nConnectTryNum;            // 连接尝试次数，为0默认1次
        public int                    nSubConnectSpaceTime;    // 子连接之间的等待时间(毫秒为单位)，为0默认10ms
        public int                    nGetDevInfoTime;        // 获取设备信息超时时间，为0默认1000ms
        public int                    nConnectBufSize;        // 每个连接接收数据缓冲大小(字节为单位)，为0默认250*1024
        public int                    nGetConnInfoTime;        // 获取子连接信息超时时间(毫秒为单位)，为0默认1000ms
        public int                     nSearchRecordTime;      // 按时间查询录像文件的超时时间(毫秒为单位),为0默认为3000ms
        public int                     nsubDisconnetTime;      // 检测子链接断线等待时间(毫秒为单位)，为0默认为60000ms
        public byte                    byNetType;                // 网络类型, 0-LAN, 1-WAN
        public byte                    byPlaybackBufSize;      // 回放数据接收缓冲大小（M为单位），为0默认为4M
        public byte[]               byReserved1 = new byte[2];         // 对齐
        public int                     nPicBufSize;            // 实时图片接收缓冲大小（字节为单位），为0默认为2*1024*1024
        public byte[]                bReserved = new byte[4];            // 保留字段字段
    }
    
    // 设备信息
    public static class NET_DEVICEINFO extends Structure {
        public byte[]              sSerialNumber = new byte[NET_SERIALNO_LEN];    // 序列号
        public byte                byAlarmInPortNum;         // DVR报警输入个数
        public byte                byAlarmOutPortNum;        // DVR报警输出个数
        public byte                byDiskNum;                // DVR硬盘个数
        public byte                byDVRType;                // DVR类型, 见枚举NET_DEV_DEVICE_TYPE
        public byte                byChanNum;                // DVR通道个数
    }
    
    // 设备信息扩展，对应CLIENT_LoginEx2
    public static class NET_DEVICEINFO_Ex extends Structure {
    	 public byte[]     sSerialNumber = new byte[NET_SERIALNO_LEN];    // 序列号
    	 public int        byAlarmInPortNum;                              // DVR报警输入个数
    	 public int        byAlarmOutPortNum;                             // DVR报警输出个数
    	 public int        byDiskNum;                                     // DVR硬盘个数
    	 public int        byDVRType;                                     // DVR类型,见枚举NET_DEVICE_TYPE
    	 public int        byChanNum;                                     // DVR通道个数
    	 public byte       byLimitLoginTime;                              // 在线超时时间,为0表示不限制登陆,非0表示限制的分钟数
    	 public byte       byLeftLogTimes;                                // 当登陆失败原因为密码错误时,通过此参数通知用户,剩余登陆次数,为0时表示此参数无效
    	 public byte[]     bReserved = new byte[2];                       // 保留字节,字节对齐
    	 public int        byLockLeftTime;                                // 当登陆失败,用户解锁剩余时间（秒数）, -1表示设备未设置该参数
    	 public byte[]     Reserved = new byte[24];                       // 保留
    }
    
    // 对应接口 CLIENT_LoginEx2/////////////////////////////////////////////////////////
    public static class EM_LOGIN_SPAC_CAP_TYPE extends Structure {
    	public static final int EM_LOGIN_SPEC_CAP_TCP               = 0;    // TCP登陆, 默认方式
    	public static final int EM_LOGIN_SPEC_CAP_ANY               = 1;    // 无条件登陆
    	public static final int EM_LOGIN_SPEC_CAP_SERVER_CONN       = 2;    // 主动注册的登入
    	public static final int	EM_LOGIN_SPEC_CAP_MULTICAST         = 3;    // 组播登陆
    	public static final int	EM_LOGIN_SPEC_CAP_UDP               = 4;    // UDP方式下的登入
    	public static final int	EM_LOGIN_SPEC_CAP_MAIN_CONN_ONLY    = 6;    // 只建主连接下的登入
    	public static final int	EM_LOGIN_SPEC_CAP_SSL               = 7;    // SSL加密方式登陆

    	public static final int	EM_LOGIN_SPEC_CAP_INTELLIGENT_BOX   = 9;    // 登录智能盒远程设备
    	public static final int	EM_LOGIN_SPEC_CAP_NO_CONFIG         = 10;   // 登录设备后不做取配置操作
    	public static final int EM_LOGIN_SPEC_CAP_U_LOGIN           = 11;   // 用U盾设备的登入
    	public static final int	EM_LOGIN_SPEC_CAP_LDAP              = 12;   // LDAP方式登录
    	public static final int EM_LOGIN_SPEC_CAP_AD                = 13;   // AD（ActiveDirectory）登录方式
    	public static final int EM_LOGIN_SPEC_CAP_RADIUS            = 14;   // Radius 登录方式 
    	public static final int EM_LOGIN_SPEC_CAP_SOCKET_5          = 15;   // Socks5登陆方式
    	public static final int EM_LOGIN_SPEC_CAP_CLOUD             = 16;   // 云登陆方式
    	public static final int EM_LOGIN_SPEC_CAP_AUTH_TWICE        = 17;   // 二次鉴权登陆方式
    	public static final int EM_LOGIN_SPEC_CAP_TS                = 18;   // TS码流客户端登陆方式
    	public static final int	EM_LOGIN_SPEC_CAP_P2P               = 19;   // 为P2P登陆方式
    	public static final int	EM_LOGIN_SPEC_CAP_MOBILE            = 20;   // 手机客户端登陆
    	public int EM_LOGIN_SPEC_CAP_INVALID;                               // 无效的登陆方式
    }

    // 时间
    public static class NET_TIME extends Structure 
    {
        public int                dwYear;                   // 年
        public int                dwMonth;                  // 月
        public int                dwDay;                    // 日
        public int                dwHour;                   // 时
        public int                dwMinute;                 // 分
        public int                dwSecond;                 // 秒
        
        public NET_TIME()
        {
            this.dwYear = 0;
            this.dwMonth = 0;
            this.dwDay = 0;
            this.dwHour = 0;
            this.dwMinute = 0;
            this.dwSecond = 0;
        }
        
        public NET_TIME(NET_TIME other)
        {
            this.dwYear = other.dwYear;
            this.dwMonth = other.dwMonth;
            this.dwDay = other.dwDay;
            this.dwHour = other.dwHour;
            this.dwMinute = other.dwMinute;
            this.dwSecond = other.dwSecond;
        }
        
        //用于列表中显示
        public String toStringTime()
        {
            return  String.format("%02d/%02d/%02d %02d:%02d:%02d", dwYear, dwMonth, dwDay, dwHour, dwMinute, dwSecond);
        }
    }

    public static class NET_TIME_EX extends Structure 
    {
        public int                dwYear;                    // 年
        public int                dwMonth;                   // 月
        public int                dwDay;                     // 日
        public int                dwHour;                    // 时
        public int                dwMinute;                  // 分
        public int                dwSecond;                  // 秒
        public int              dwMillisecond;               // 毫秒
        public int[]            dwReserved = new int[2];     // 保留字段
        
        public String toString() {
            return "NET_TIME_EX.dwYear: " + dwYear + "\n" + "NET_TIME_EX.dwMonth: " + dwMonth + "\n" + "NET_TIME_EX.dwDay: " + dwDay + "\n" + "NET_TIME_EX.dwHour: " + dwHour + "\n" + "NET_TIME_EX.dwMinute: " + dwMinute + "\n" + "NET_TIME_EX.dwSecond: " + dwSecond;
        }

        //用于列表中显示
        public String toStringTime()
        {
            return  String.format("%02d/%02d/%02d %02d:%02d:%02d", dwYear, dwMonth, dwDay, dwHour, dwMinute, dwSecond);
        }

        //存储文件名使用
         public String toStringTitle()
        {
            return  String.format("Time%02d%02d%02d_%02d%02d%02d%03d", dwYear, dwMonth, dwDay, dwHour, dwMinute, dwSecond,dwMillisecond);
        }
    }
    
    // 区域
    public static class  NET_CFG_Rect extends Structure
    {
        public int            nStructSize;
        public int            nLeft;
        public int            nTop;
        public int            nRight;
        public int            nBottom;    
        
        public NET_CFG_Rect()
        {
            this.nStructSize = this.size();
        }
    }
    
    // 颜色
    public static class  NET_CFG_Color extends Structure
    {
        public int            nStructSize;
        public int            nRed;                            // 红
        public int            nGreen;                            // 绿
        public int            nBlue;                            // 蓝
        public int            nAlpha;                            // 透明
        
        public NET_CFG_Color()
        {
            this.nStructSize = this.size();
        }
    }

    // 编码物件-通道标题
    public static class  NET_CFG_VideoWidgetChannelTitle extends Structure
    {
        public int                nStructSize;
        public int            	  bEncodeBlend;                    // 叠加到主码流, 类型为BOOL, 取值0或者1
        public int            	  bEncodeBlendExtra1;              // 叠加到辅码流1, 类型为BOOL, 取值0或者1
        public int                bEncodeBlendExtra2;              // 叠加到辅码流2, 类型为BOOL, 取值0或者1
        public int            	  bEncodeBlendExtra3;              // 叠加到辅码流3, 类型为BOOL, 取值0或者1
        public int            	  bEncodeBlendSnapshot;            // 叠加到抓图, 类型为BOOL, 取值0或者1
        public NET_CFG_Color      stuFrontColor = new NET_CFG_Color();    // 前景色
        public NET_CFG_Color      stuBackColor = new NET_CFG_Color();    // 背景色
        public NET_CFG_Rect       stuRect = new NET_CFG_Rect();        // 区域, 坐标取值0~8191, 仅使用left和top值, 点(left,top)应和(right,bottom)设置成同样的点
        public int            bPreviewBlend;                    // 叠加到预览视频, 类型为BOOL， 取值0或者1
        
        public NET_CFG_VideoWidgetChannelTitle()
        {
            this.nStructSize = this.size();
        }
    }

    // 编码物件-时间标题
    public static class  NET_CFG_VideoWidgetTimeTitle extends Structure
    {
        public int                nStructSize;
        public int            bEncodeBlend;                        // 叠加到主码流, 类型为BOOL, 取值0或者1
        public int            bEncodeBlendExtra1;                    // 叠加到辅码流1, 类型为BOOL, 取值0或者1
        public int            bEncodeBlendExtra2;                    // 叠加到辅码流2, 类型为BOOL, 取值0或者1
        public int            bEncodeBlendExtra3;                    // 叠加到辅码流3, 类型为BOOL, 取值0或者1
        public int            bEncodeBlendSnapshot;                // 叠加到抓图, 类型为BOOL, 取值0或者1
        public NET_CFG_Color        stuFrontColor = new NET_CFG_Color();    // 前景色
        public NET_CFG_Color        stuBackColor = new NET_CFG_Color();    // 背景色
        public NET_CFG_Rect        stuRect = new NET_CFG_Rect();        // 区域, 坐标取值0~8191, 仅使用left和top值, 点(left,top)应和(right,bottom)设置成同样的点
        public int            bShowWeek;                            // 是否显示星期, 类型为BOOL, 取值0或者1
        public int            bPreviewBlend;                        // 叠加到预览视频, 类型为BOOL, 取值0或者1
        
        public NET_CFG_VideoWidgetTimeTitle()
        {
            this.nStructSize = this.size();
        }
    }
    
    // 编码物件-区域覆盖配置
    public static class  NET_CFG_VideoWidgetCover extends Structure
    {
        public int                nStructSize;    
        public int            bEncodeBlend;                    // 叠加到主码流, 类型为BOOL, 取值0或者1
        public int            bEncodeBlendExtra1;                // 叠加到辅码流1, 类型为BOOL, 取值0或者1
        public int            bEncodeBlendExtra2;                // 叠加到辅码流2, 类型为BOOL, 取值0或者1
        public int            bEncodeBlendExtra3;                // 叠加到辅码流3, 类型为BOOL, 取值0或者1
        public int            bEncodeBlendSnapshot;            // 叠加到抓图, 类型为BOOL, 取值0或者1
        public NET_CFG_Color        stuFrontColor = new NET_CFG_Color();        // 前景色
        public NET_CFG_Color        stuBackColor = new NET_CFG_Color();        // 背景色
        public NET_CFG_Rect        stuRect = new NET_CFG_Rect();            // 区域, 坐标取值0~8191
        public int            bPreviewBlend;                    // 叠加到预览视频, 类型为BOOL, 取值0或者1
        
        public NET_CFG_VideoWidgetCover()
        {
            this.nStructSize = this.size();
        }
    }
    
    public class EM_TITLE_TEXT_ALIGN
    {
        public static final int EM_TEXT_ALIGN_INVALID         = 0;     // 无效的对齐方式
        public static final int EM_TEXT_ALIGN_LEFT            = 1;       // 左对齐
        public static final int EM_TEXT_ALIGN_XCENTER        = 2;    // X坐标中对齐
        public static final int EM_TEXT_ALIGN_YCENTER        = 3;    // Y坐标中对齐
        public static final int EM_TEXT_ALIGN_CENTER        = 4;      // 居中
        public static final int EM_TEXT_ALIGN_RIGHT            = 5;       // 右对齐
        public static final int EM_TEXT_ALIGN_TOP            = 6;       // 按照顶部对齐
        public static final int EM_TEXT_ALIGN_BOTTOM        = 7;     // 按照底部对齐
        public static final int EM_TEXT_ALIGN_LEFTTOP        = 8;    // 按照左上角对齐
        public static final int EM_TEXT_ALIGN_CHANGELINE    = 9;      // 换行对齐
    }

    // 编码物件-自定义标题
    public static class  NET_CFG_VideoWidgetCustomTitle extends Structure
    {
        public int                nStructSize;
        public int            bEncodeBlend;                        // 叠加到主码流, 类型为BOOL, 取值0或者1 
        public int            bEncodeBlendExtra1;                    // 叠加到辅码流1, 类型为BOOL, 取值0或者1
        public int            bEncodeBlendExtra2;                    // 叠加到辅码流2, 类型为BOOL, 取值0或者1
        public int            bEncodeBlendExtra3;                    // 叠加到辅码流3, 类型为BOOL, 取值0或者1
        public int            bEncodeBlendSnapshot;                // 叠加到抓图, 类型为BOOL, 取值0或者1
        public NET_CFG_Color        stuFrontColor = new NET_CFG_Color();    // 前景色
        public NET_CFG_Color        stuBackColor = new NET_CFG_Color();    // 背景色
        public NET_CFG_Rect        stuRect = new NET_CFG_Rect();        // 区域, 坐标取值0~8191, 仅使用left和top值, 点(left,top)应和(right,bottom)设置成同样的点
        public byte[]            szText = new byte[NET_CFG_Custom_Title_Len];// 标题内容
        public int            bPreviewBlend;                    // 叠加到预览视频, 类型为BOOL, 取值0或者1
        public byte[]           szType = new byte[NET_CFG_Custom_TitleType_Len];// 标题类型 "Rtinfo" 实时刻录信息 "Custom" 自定义叠加、温湿度叠加 "Title" :片头信息 "Check"  校验码
                                                                // 地理信息 "Geography"  ATM卡号信息 "ATMCardInfo" 摄像机编号 "CameraID" 
        public int                  emTextAlign;                    // 标题对齐方式 (参见EM_TITLE_TEXT_ALIGN)
        
        public NET_CFG_VideoWidgetCustomTitle()
        {
            this.nStructSize = this.size();
        }
    }
    
    //  编码物件-叠加传感器信息-叠加内容描述
    public static class  NET_CFG_VideoWidgetSensorInfo_Description extends Structure
    {
        public int            nStructSize;
        public int            nSensorID;                        // 需要描述的传感器的ID(即模拟量报警通道号)
        
        public NET_CFG_VideoWidgetSensorInfo_Description()
        {
            this.nStructSize = this.size();
        }
    }

    // 编码物件-叠加传感器信息
    public static class  NET_CFG_VideoWidgetSensorInfo extends Structure
    {
        public int            nStructSize;
        public int        bPreviewBlend;                    // 叠加到预览视频, 类型为BOOL, 取值0或者1
        public int        bEncodeBlend;                    // 叠加到主码流视频编码, 类型为BOOL, 取值0或者1
        public NET_CFG_Rect    stuRect = new NET_CFG_Rect();                        // 区域, 坐标取值0~8191
        public int            nDescriptionNum;                // 叠加区域描述数目
        public NET_CFG_VideoWidgetSensorInfo_Description[]  stuDescription = (NET_CFG_VideoWidgetSensorInfo_Description[])new NET_CFG_VideoWidgetSensorInfo_Description().toArray(NET_CFG_Max_Description_Num);// 叠加区域描述信息
        
        public NET_CFG_VideoWidgetSensorInfo()
        {
            this.nStructSize = this.size();
        }
    }

    // 视频编码物件配置
    public static class NET_CFG_VideoWidget extends Structure
    {
        public int                                nStructSize;
        public NET_CFG_VideoWidgetChannelTitle    stuChannelTitle = new NET_CFG_VideoWidgetChannelTitle();        // 通道标题
        public NET_CFG_VideoWidgetTimeTitle        stuTimeTitle = new NET_CFG_VideoWidgetTimeTitle();            // 时间标题
        public int                                nConverNum;                // 区域覆盖数量
        public NET_CFG_VideoWidgetCover[]        stuCovers = (NET_CFG_VideoWidgetCover[])new NET_CFG_VideoWidgetCover().toArray(NET_CFG_Max_Video_Widget_Cover);                        // 覆盖区域
        public int                                nCustomTitleNum;        // 自定义标题数量
        public NET_CFG_VideoWidgetCustomTitle[]    stuCustomTitle = (NET_CFG_VideoWidgetCustomTitle[])new NET_CFG_VideoWidgetCustomTitle().toArray(NET_CFG_Max_Video_Widget_Custom_Title);    // 自定义标题
        public int                                nSensorInfo;            // 传感器信息叠加区域数目
        public NET_CFG_VideoWidgetSensorInfo[]    stuSensorInfo = (NET_CFG_VideoWidgetSensorInfo[])new NET_CFG_VideoWidgetSensorInfo().toArray(NET_CFG_Max_Video_Widget_Sensor_Info);        // 传感器信息叠加区域信息
        public double                           fFontSizeScale;         //叠加字体大小放大比例
                                                                        //当fFontSizeScale≠0时,nFontSize不起作用
                                                                           //当fFontSizeScale=0时,nFontSize起作用
                                                                           //设备默认fFontSizeScale=1.0
                                                                           //如果需要修改倍数，修改该值
                                                                           //如果需要按照像素设置，则置该值为0，nFontSize的值生效
        public int                               nFontSize;              //叠加到主码流上的全局字体大小,单位 px.
                                                                           //和fFontSizeScale共同作用
        public int                               nFontSizeExtra1;        //叠加到辅码流1上的全局字体大小,单位 px
        public int                               nFontSizeExtra2;        //叠加到辅码流2上的全局字体大小,单位 px
        public int                               nFontSizeExtra3;        //叠加到辅码流3上的全局字体大小,单位 px
        public int                               nFontSizeSnapshot;      //叠加到抓图流上的全局字体大小, 单位 px
        public int                               nFontSizeMergeSnapshot; //叠加到抓图流上合成图片的字体大小,单位 px
        
        public NET_CFG_VideoWidget()
        {
            this.nStructSize = this.size();
        }
    }
    
    // 报警事件类型 NET_EVENT_VIDEOABNORMALDETECTION 对应的数据描述信息
    public static class ALARM_VIDEOABNORMAL_DETECTION_INFO extends Structure
    {
        public int          dwSize;    
        public int          nChannelID;                     // 通道号
        public double       PTS;                            // 时间戳(单位是毫秒)
        public NET_TIME_EX  UTC;                            // 事件发生的时间
        public int             nEventID;                       // 事件ID
        public int          nEventAction;                   // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public int          nType;                          // 检测类型,0-视频丢失, 1-视频遮挡, 2-画面冻结, 3-过亮, 4-过暗, 5-场景变化
                                                            // 6-条纹检测 , 7-噪声检测 , 8-偏色检测 , 9-视频模糊检测 , 10-对比度异常检测
                                                            // 11-视频运动, 12-视频闪烁, 13-视频颜色, 14-虚焦检测, 15-过曝检测
        public int          nValue;                         // 检测值,值越高表示视频质量越差, GB30147定义
        public int          nOccurrenceCount;               // 规则被触发生次数
        
        public ALARM_VIDEOABNORMAL_DETECTION_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 报警事件类型 NET_ALARM_NEW_FILE 对应的数据描述信息
    public static class ALARM_NEW_FILE_INFO extends Structure
    {
        public int      dwSize;
        public int      nChannel;                           // 抓图通道号
        public int      nEventID;                           // 事件ID
        public int      dwEvent;                            // 事件类型
        public int      FileSize;                           // 文件大小,单位是字节
        public int      nIndex;                             // 事件源通道
        public int      dwStorPoint;                        // 存储点
        public byte[]   szFileName = new byte[128];           // 文件名
        
        public ALARM_NEW_FILE_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 人数越上限类型
    public static class EM_UPPER_LIMIT_TYPE extends Structure
    {
        public static final int EM_UPPER_LIMIT_TYPE_UNKNOWN     = 0;  
        public static final int EM_UPPER_LIMIT_TYPE_ENTER_OVER  = 1; // 进入越上限
        public static final int EM_UPPER_LIMIT_TYPE_EXIT_OVER   = 2; // 出来越上限
        public static final int EM_UPPER_LIMIT_TYPE_INSIDE_OVER = 3; // 内部越上限    
    }
    

    // 事件类型 NET_ALARM_HUMAM_NUMBER_STATISTIC (人数量/客流量统计事件NumberStat对应的数据描述信息)
    public static class  ALARM_HUMAN_NUMBER_STATISTIC_INFO extends Structure
    {
        public double              PTS;                            // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                            // 事件发生的时间
        public int                 nEventAction;                   // 事件动作,0-事件持续, 1-表示事件开始, 2-表示事件结束;
        public int                 nNumber;                        // 区域内物体的个数
        public int                 nEnteredNumber;                 // 进入区域或者出入口内的物体个数
        public int                 nExitedNumber;                  // 出来区域或者出入口内的物体个数
        public int                 emUpperLimitType;               // 人数越上限类型,参见EM_UPPER_LIMIT_TYPE定义
        public byte[]              reserved = new byte[512];       // 预留       
    }
    
    /////////////////////////////////智能支持/////////////////////////////////
    //物体对应图片文件信息
    public static class NET_PIC_INFO extends Structure
    {
        public int dwOffSet;//文件在二进制数据块中的偏移位置,单位:字节
        public int dwFileLenth;//文件大小,单位:字节
        public short wWidth;//图片宽度,单位:像素
        public short wHeight;//图片高度,单位:像素
        public Pointer pszFilePath;//鉴于历史原因,该成员只在事件上报时有效， char *
                                   // 文件路径
                                   // 用户使用该字段时需要自行申请空间进行拷贝保存
        public byte bIsDetected;//图片是否算法检测出来的检测过的提交识别服务器时,
                                            //则不需要再时检测定位抠图,1:检测过的,0:没有检测过
        public byte[] bReserved = new byte[11];//12<--16
    }

    // 人员类型
    public static class EM_PERSON_TYPE extends Structure
    {
        public static final int PERSON_TYPE_UNKNOWN = 0;  
        public static final int PERSON_TYPE_NORMAL = 1; //普通人员
        public static final int PERSON_TYPE_SUSPICION = 2; //嫌疑人员
    }

    // 证件类型
    public static class EM_CERTIFICATE_TYPE extends Structure
    {
        public static final int CERTIFICATE_TYPE_UNKNOWN = 0;  
        public static final int CERTIFICATE_TYPE_IC = 1; //身份证
        public static final int CERTIFICATE_TYPE_PASSPORT = 2; //护照
    }
    
    //人员信息
    public static class FACERECOGNITION_PERSON_INFO extends Structure
    {
        public byte[] szPersonName = new byte[NET_MAX_NAME_LEN];//姓名,此参数作废
        public short wYear;//出生年,作为查询条件时,此参数填0,则表示此参数无效
        public byte byMonth;//出生月,作为查询条件时,此参数填0,则表示此参数无效
        public byte byDay;//出生日,作为查询条件时,此参数填0,则表示此参数无效
        public byte[] szID = new byte[NET_MAX_PERSON_ID_LEN];//人员唯一标示(身份证号码,工号,或其他编号)
        public byte bImportantRank;//人员重要等级,1~10,数值越高越重要,作为查询条件时,此参数填0,则表示此参数无效
        public byte bySex;//性别,1-男,2-女,作为查询条件时,此参数填0,则表示此参数无效
        public short wFacePicNum;//图片张数
        public NET_PIC_INFO[] szFacePicInfo =  (NET_PIC_INFO[])new NET_PIC_INFO().toArray(NET_MAX_PERSON_IMAGE_NUM);//当前人员对应的图片信息
        public byte byType;//人员类型,详见EM_PERSON_TYPE
        public byte byIDType;//证件类型,详见EM_CERTIFICATE_TYPE
        public byte[] bReserved1 = new byte[2];//字节对齐
        public byte[] szProvince = new byte[NET_MAX_PROVINCE_NAME_LEN];//省份
        public byte[] szCity = new byte[NET_MAX_CITY_NAME_LEN];//城市
        public byte[] szPersonNameEx = new byte[NET_MAX_PERSON_NAME_LEN];//姓名,因存在姓名过长,16字节无法存放问题,故增加此参数,
        public byte[] szUID = new byte[NET_MAX_PERSON_ID_LEN];//人员唯一标识符,首次由服务端生成,区别于ID字段
                                                              // 修改,删除操作时必填
        public byte[] bReserved = new byte[28];
    }

    ///////////////////////////////////人脸识别模块相关结构体///////////////////////////////////////
    public static class NET_UID_CHAR extends Structure
    {
        public byte[] szUID = new byte[NET_MAX_PERSON_ID_LEN];//UID内容
    }
    
    //人脸识别数据库操作
    public static class EM_OPERATE_FACERECONGNITIONDB_TYPE
    {
        public static final int NET_FACERECONGNITIONDB_UNKOWN = 0; 
        public static final int NET_FACERECONGNITIONDB_ADD = 1;             //添加人员信息和人脸样本，如果人员已经存在，图片数据和原来的数据合并
        public static final int NET_FACERECONGNITIONDB_DELETE = 2;             //删除人员信息和人脸样本
        public static final int NET_FACERECONGNITIONDB_MODIFY = 3;             //修改人员信息和人脸样本,人员的UID标识必填
        public static final int NET_FACERECONGNITIONDB_DELETE_BY_UID = 4;     //通过UID删除人员信息和人脸样本
    }
    
    //CLIENT_OperateFaceRecognitionDB接口输入参数
    public static class NET_IN_OPERATE_FACERECONGNITIONDB extends Structure
    {
        public int dwSize;
        public int emOperateType;//操作类型, 取EM_OPERATE_FACERECONGNITIONDB_TYPE中的值
        public FACERECOGNITION_PERSON_INFO stPersonInfo;//人员信息
                                                        //emOperateType操作类型为ET_FACERECONGNITIONDB_DELETE_BY_UID时使用,stPeronInfo字段无效
        public int nUIDNum;//UID个数
        public Pointer stuUIDs;//人员唯一标识符,首次由服务端生成,区别于ID字段, NET_UID_CHAR *
        // 图片二进制数据
        public Pointer pBuffer;//缓冲地址, char *
        public int nBufferLen;//缓冲数据长度
        
        public NET_IN_OPERATE_FACERECONGNITIONDB()
        {
            this.dwSize = this.size();
        }
    }
    
    //CLIENT_OperateFaceRecognitionDB接口输出参数
    public static class NET_OUT_OPERATE_FACERECONGNITIONDB extends Structure
    {
        public int dwSize;
        
        public NET_OUT_OPERATE_FACERECONGNITIONDB()
        {
            this.dwSize = this.size();
        }
    }
    
    //人脸对比模式
    public static class EM_FACE_COMPARE_MODE extends Structure
    {
        public static final int NET_FACE_COMPARE_MODE_UNKOWN = 0;
        public static final int NET_FACE_COMPARE_MODE_NORMAL = 1; //正常
        public static final int NET_FACE_COMPARE_MODE_AREA = 2; //指定人脸区域组合区域
        public static final int  NET_FACE_COMPARE_MODE_AUTO = 3; //智能模式,算法根据人脸各个区域情况自动选取组合
    }
    
    //人脸区域
    public static class EM_FACE_AREA_TYPE extends Structure
    {
        public static final int NET_FACE_AREA_TYPE_UNKOWN = 0; 
        public static final int NET_FACE_AREA_TYPE_EYEBROW = 1; //眉毛
        public static final int NET_FACE_AREA_TYPE_EYE = 2; //眼睛
        public static final int NET_FACE_AREA_TYPE_NOSE= 3; //鼻子
        public static final int NET_FACE_AREA_TYPE_MOUTH = 4; //嘴巴
        public static final int NET_FACE_AREA_TYPE_CHEEK =5; //脸颊
    }
    
    public static class NET_FACE_MATCH_OPTIONS extends Structure
    {
        public int dwSize;
        public int nMatchImportant;//人员重要等级1~10,数值越高越重要,(查询重要等级大于等于此等级的人员)， 类型为unsigned int
        public int emMode;//人脸比对模式,详见EM_FACE_COMPARE_MODE, 取EM_FACE_COMPARE_MODE中的值
        public int nAreaNum;//人脸区域个数
        public int[] szAreas= new int[MAX_FACE_AREA_NUM];//人脸区域组合,emMode为NET_FACE_COMPARE_MODE_AREA时有效, 数组元素取EM_FACE_AREA_TYPE中的值
        public int nAccuracy;//识别精度(取值1~10,随着值增大,检测精度提高,检测速度下降。最小值为1表示检测速度优先,最大值为10表示检测精度优先。暂时只对人脸检测有效)
        public int nSimilarity;//相似度(必须大于该相识度才报告;百分比表示,1~100)
        public int nMaxCandidate;//报告的最大候选个数(根据相似度进行排序,取相似度最大的候选人数报告)
        
        public NET_FACE_MATCH_OPTIONS()
        {
            this.dwSize = this.size();
        }
    }
    
    //人脸识别人脸类型
    public static class EM_FACERECOGNITION_FACE_TYPE extends Structure
    {
        public static final int  EM_FACERECOGNITION_FACE_TYPE_UNKOWN = 0;
        public static final int  EM_FACERECOGNITION_FACE_TYPE_ALL = 1; //所有人脸
        public static final int  EM_FACERECOGNITION_FACE_TYPE_REC_SUCCESS=  2;//识别成功
        public static final int  EM_FACERECOGNITION_FACE_TYPE_REC_FAIL = 3;//识别失败
    }
    
    public static class NET_FACE_FILTER_CONDTION extends Structure
    {
        public int dwSize;
        public NET_TIME stStartTime;//开始时间
        public NET_TIME stEndTime;//结束时间
        public byte[] szMachineAddress = new byte[MAX_PATH];//地点,支持模糊匹配
        public int nRangeNum;//实际数据库个数
        public byte[] szRange = new byte[MAX_FACE_DB_NUM];//待查询数据库类型,详见EM_FACE_DB_TYPE
        public int emFaceType;//待查询人脸类型,详见EM_FACERECOGNITION_FACE_TYPE， 取EM_FACERECOGNITION_FACE_TYPE中的值
        public int nGroupIdNum;//人员组数
        public byte[] szGroupId = new byte[MAX_GOURP_NUM*NET_COMMON_STRING_64];//人员组ID
        
        public NET_FACE_FILTER_CONDTION()
        {
            this.dwSize = this.size();
        }
    }
    
    //CLIENT_StartFindFaceRecognition接口输入参数
    public static class NET_IN_STARTFIND_FACERECONGNITION extends Structure
    {
        public int dwSize;
        public int bPersonEnable;//人员信息查询条件是否有效, BOOL类型，取值0或1
        public FACERECOGNITION_PERSON_INFO stPerson;//人员信息查询条件
        public NET_FACE_MATCH_OPTIONS stMatchOptions;//人脸匹配选项
        public NET_FACE_FILTER_CONDTION stFilterInfo;//查询过滤条件
        // 图片二进制数据
        public Pointer pBuffer;//缓冲地址, char *
        public int nBufferLen;//缓冲数据长度
        public int nChannelID;//通道号
        
        public NET_IN_STARTFIND_FACERECONGNITION()
        {
            this.dwSize = this.size();
        }
    }
    
    //CLIENT_StartFindFaceRecognition接口输出参数
    public static class NET_OUT_STARTFIND_FACERECONGNITION extends Structure
    {
        public int dwSize;
        public int nTotalCount;//返回的符合查询条件的记录个数
                               // -1表示总条数未生成,要推迟获取
                               // 使用CLIENT_AttachFaceFindState接口状态
        public NativeLong lFindHandle;//查询句柄
        public int nToken;//获取到的查询令牌
        
        public NET_OUT_STARTFIND_FACERECONGNITION()
        {
            this.dwSize = this.size();
        }
    }
    
    //CLIENT_DoFindFaceRecognition 接口输入参数
    public static class NET_IN_DOFIND_FACERECONGNITION extends Structure
    {
        public int dwSize;
        public NativeLong lFindHandle;//查询句柄
        public int nBeginNum;//查询起始序号
        public int nCount;//当前想查询的记录条数
        
        public NET_IN_DOFIND_FACERECONGNITION()
        {
            this.dwSize = this.size();
        }
    }
    
    //候选人员信息
    public static class CANDIDATE_INFO extends Structure
    {
        public FACERECOGNITION_PERSON_INFO stPersonInfo;//人员信息
                                                        // 布控（黑名单）库,指布控库中人员信息；
                                                        // 历史库,指历史库中人员信息
                                                        // 报警库,指布控库的人员信息
        public byte bySimilarity;//和查询图片的相似度,百分比表示,1~100
        public byte byRange;//人员所属数据库范围,详见EM_FACE_DB_TYPE
        public byte[] byReserved1 = new byte[2];
        public NET_TIME stTime;//当byRange为历史数据库时有效,表示查询人员出现的时间
        public byte[] szAddress = new byte[MAX_PATH];//当byRange为历史数据库时有效,表示查询人员出现的地点
        public byte[] byReserved = new byte[128];//保留字节
    }
    
    //CLIENT_DoFindFaceRecognition接口输出参数
    public static class NET_OUT_DOFIND_FACERECONGNITION extends Structure
    {
        public int dwSize;
        public int nCadidateNum;//实际返回的候选信息结构体个数
        public CANDIDATE_INFO[] stCadidateInfo = (CANDIDATE_INFO[])new CANDIDATE_INFO().toArray(MAX_FIND_COUNT);//候选信息数组
        // 图片二进制数据
        public Pointer pBuffer;//缓冲地址, char *
        public int nBufferLen;//缓冲数据长度
        
        public NET_OUT_DOFIND_FACERECONGNITION()
        {
            this.dwSize = this.size();
        }
    }
    
    /////////////////////////////////智能支持/////////////////////////////////
    //CLIENT_DetectFace接口输入参数
    public static class NET_IN_DETECT_FACE extends Structure
    {
        public int dwSize;
        public NET_PIC_INFO stPicInfo;//大图信息
        // 图片二进制数据
        public Pointer pBuffer;//缓冲地址, char *
        public int nBufferLen;//缓冲数据长度
        
        public NET_IN_DETECT_FACE()
        {
            this.dwSize = this.size();
        }
    }
    
    //CLIENT_DetectFace接口输出参数
    public static class NET_OUT_DETECT_FACE extends Structure
    {
        public int dwSize;
        public Pointer pPicInfo;//检测出的人脸图片信息,由用户申请空间, NET_PIC_INFO*
        public int nMaxPicNum;//最大人脸图片信息个数
        public int nRetPicNum;//实际返回的人脸图片个数
        // 图片二进制数据
        public Pointer pBuffer;//缓冲地址,由用户申请空间,存放检测出的人脸图片数据, char *
        public int nBufferLen;//缓冲数据长度
        
        public NET_OUT_DETECT_FACE()
        {
            this.dwSize = this.size();
        }
    }
    
    // 人脸识别事件类型
    public static class EM_FACERECOGNITION_ALARM_TYPE extends Structure
    {
        public static final int NET_FACERECOGNITION_ALARM_TYPE_UNKOWN = 0;
        public static final int NET_FACERECOGNITION_ALARM_TYPE_ALL = 1; //黑白名单
        public static final int NET_FACERECOGNITION_ALARM_TYPE_BLACKLIST = 2; //黑名单
        public static final int NET_FACERECOGNITION_ALARM_TYPE_WHITELIST = 3; //白名单
    }
    
    //NET_FILE_QUERY_FACE对应的人脸识别服务查询参数
    public static class MEDIAFILE_FACERECOGNITION_PARAM extends Structure
    {
        public int dwSize;//结构体大小
        // 查询过滤条件
        public NET_TIME stStartTime;//开始时间
        public NET_TIME stEndTime;//结束时间
        public byte[] szMachineAddress = new byte[MAX_PATH];//地点,支持模糊匹配
        public int nAlarmType;//待查询报警类型,详见EM_FACERECOGNITION_ALARM_TYPE
        public int abPersonInfo;//人员信息是否有效, BOOL类型，取值0或1
        public FACERECOGNITION_PERSON_INFO stPersonInfo;//人员信息
        public int nChannelId;//通道号
        public int nGroupIdNum;//人员组数
        public byte[] szGroupId = new byte[MAX_GOURP_NUM*NET_COMMON_STRING_64];//人员组ID
        
        public MEDIAFILE_FACERECOGNITION_PARAM()
        {
            this.dwSize = this.size();
        }
    }
    
    public static class NET_PIC_INFO_EX extends Structure
    {
        public int dwSize;//结构体大小
        public int dwFileLenth;//文件大小,单位:字节
        public byte[] szFilePath = new byte[MAX_PATH];//文件路径
        
        public NET_PIC_INFO_EX()
        {
            this.dwSize = this.size();
        }
    }
    
    //区域；各边距按整长8192的比例
    public static class NET_RECT extends Structure
    {
        public NativeLong left;
        public NativeLong top;
        public NativeLong right;
        public NativeLong bottom;
    }
    
    //二维空间点
    public static class NET_POINT extends Structure
    {
        public short nx;
        public short ny;
    }
    
    public static class NET_CANDIDAT_PIC_PATHS extends Structure
    {
        public int dwSize;//结构体大小
        public int nFileCount;//实际文件个数
        public NET_PIC_INFO_EX[] stFiles = (NET_PIC_INFO_EX[])new NET_PIC_INFO_EX().toArray(NET_MAX_PERSON_IMAGE_NUM);//文件信息
        
        public NET_CANDIDAT_PIC_PATHS()
        {
            this.dwSize = this.size();
        }
    }
    
    //颜色类型
    public static class EM_COLOR_TYPE extends Structure
    {   
        public static final int NET_COLOR_TYPE_RED = 0;//红色
        public static final int NET_COLOR_TYPE_YELLOW = 1;//黄色
        public static final int NET_COLOR_TYPE_GREEN = 2; //绿色
        public static final int NET_COLOR_TYPE_CYAN = 3; //青色
        public static final int NET_COLOR_TYPE_BLUE = 4; //蓝色
        public static final int NET_COLOR_TYPE_PURPLE = 5; //紫色
        public static final int NET_COLOR_TYPE_BLACK = 6; //黑色
        public static final int NET_COLOR_TYPE_WHITE = 7; //白色
        public static final int NET_COLOR_TYPE_MAX = 8; 
    }
    
    //视频分析物体信息结构体
    public static class NET_MSG_OBJECT extends Structure
    {
        public int nObjectID;//物体ID,每个ID表示一个唯一的物体
        public byte[] szObjectType = new byte[128];//物体类型
        public int nConfidence;//置信度(0~255),值越大表示置信度越高
        public int nAction;//物体动作:1:Appear2:Move3:Stay
        public NET_RECT BoundingBox;//包围盒
        public NET_POINT Center;//物体型心
        public int nPolygonNum;//多边形顶点个数
        public NET_POINT[] Contour = (NET_POINT[])new NET_POINT().toArray(NET_MAX_POLYGON_NUM);//较精确的轮廓多边形
        public int rgbaMainColor;//表示车牌、车身等物体主要颜色；按字节表示,分别为红、绿、蓝和透明度,例如:RGB值为(0,255,0),透明度为0时,其值为0x00ff0000.
        public byte[] szText = new byte[128];//物体上相关的带0结束符文本,比如车牌,集装箱号等等
                                                                // "ObjectType"为"Vehicle"或者"Logo"时（尽量使用Logo。Vehicle是为了兼容老产品）表示车标,支持：
                                                                // "Unknown"未知 
                                                                // "Audi" 奥迪
                                                                // "Honda" 本田
                                                                // "Buick" 别克
                                                                // "Volkswagen" 大众
                                                                // "Toyota" 丰田
                                                                // "BMW" 宝马
                                                                // "Peugeot" 标致
                                                                // "Ford" 福特
                                                                // "Mazda" 马自达
                                                                // "Nissan" 尼桑
                                                                // "Hyundai" 现代
                                                                // "Suzuki" 铃木
                                                                // "Citroen" 雪铁龙
                                                                // "Benz" 奔驰
                                                                // "BYD" 比亚迪
                                                                // "Geely" 吉利
                                                                // "Lexus" 雷克萨斯
                                                                // "Chevrolet" 雪佛兰
                                                                // "Chery" 奇瑞
                                                                // "Kia" 起亚
                                                                // "Charade" 夏利
                                                                // "DF" 东风
                                                                // "Naveco" 依维柯
                                                                // "SGMW" 五菱
                                                                // "Jinbei" 金杯
                                                                // "JAC" 江淮
                                                                // "Emgrand" 帝豪
                                                                // "ChangAn" 长安
                                                                // "Great Wall" 长城
                                                                // "Skoda" 斯柯达
                                                                // "BaoJun" 宝骏
                                                                // "Subaru" 斯巴鲁
                                                                // "LandWind" 陆风
                                                                // "Luxgen" 纳智捷
                                                                // "Renault" 雷诺
                                                                // "Mitsubishi" 三菱
                                                                // "Roewe" 荣威
                                                                // "Cadillac" 凯迪拉克
                                                                // "MG" 名爵
                                                                // "Zotye" 众泰
                                                                // "ZhongHua" 中华
                                                                // "Foton" 福田
                                                                // "SongHuaJiang" 松花江
                                                                // "Opel" 欧宝
                                                                // "HongQi" 一汽红旗
                                                                // "Fiat" 菲亚特
                                                                // "Jaguar" 捷豹
                                                                // "Volvo" 沃尔沃
                                                                // "Acura" 讴歌
                                                                // "Porsche" 保时捷
                                                                // "Jeep" 吉普
                                                                // "Bentley" 宾利
                                                                // "Bugatti" 布加迪
                                                                // "ChuanQi" 传祺
                                                                // "Daewoo" 大宇
                                                                // "DongNan" 东南
                                                                // "Ferrari" 法拉利
                                                                // "Fudi" 福迪
                                                                // "Huapu" 华普
                                                                // "HawTai" 华泰
                                                                // "JMC" 江铃
                                                                // "JingLong" 金龙客车
                                                                // "JoyLong" 九龙
                                                                // "Karry" 开瑞
                                                                // "Chrysler" 克莱斯勒
                                                                // "Lamborghini" 兰博基尼
                                                                // "RollsRoyce" 劳斯莱斯
                                                                // "Linian" 理念
                                                                // "LiFan" 力帆
                                                                // "LieBao" 猎豹
                                                                // "Lincoln" 林肯
                                                                // "LandRover" 路虎
                                                                // "Lotus" 路特斯
                                                                // "Maserati" 玛莎拉蒂
                                                                // "Maybach" 迈巴赫
                                                                // "Mclaren" 迈凯轮
                                                                // "Youngman" 青年客车
                                                                // "Tesla" 特斯拉
                                                                // "Rely" 威麟
                                                                // "Lsuzu" 五十铃
                                                                // "Yiqi" 一汽
                                                                // "Infiniti" 英菲尼迪
                                                                // "YuTong" 宇通客车
                                                                // "AnKai" 安凯客车
                                                                // "Canghe" 昌河
                                                                // "HaiMa" 海马
                                                                // "Crown" 丰田皇冠
                                                                // "HuangHai" 黄海
                                                                // "JinLv" 金旅客车
                                                                // "JinNing" 精灵
                                                                // "KuBo" 酷博
                                                                // "Europestar" 莲花
                                                                // "MINI" 迷你
                                                                // "Gleagle" 全球鹰
                                                                // "ShiDai" 时代
                                                                // "ShuangHuan" 双环
                                                                // "TianYe" 田野
                                                                // "WeiZi" 威姿
                                                                // "Englon" 英伦
                                                                // "ZhongTong" 中通客车
                                                                // "Changan" 长安轿车
                                                                // "Yuejin" 跃进
                                                                // "Taurus" 金牛星
                                                                // "Alto" 奥拓
                                                                // "Weiwang" 威旺
                                                                // "Chenglong" 乘龙
                                                                // "Haige" 海格
                                                                // "Shaolin" 少林客车
                                                                // "Beifang" 北方客车
                                                                // "Beijing" 北京汽车
                                                                // "Hafu" 哈弗
        public byte[] szObjectSubType = new byte[64];//物体子类别,根据不同的物体类型,可以取以下子类型：
                                                                             // Vehicle Category:"Unknown"  未知,"Motor" 机动车,"Non-Motor":非机动车,"Bus": 公交车,"Bicycle" 自行车,"Motorcycle":摩托车,"PassengerCar":客车,
                                                                             // "LargeTruck":大货车,    "MidTruck":中货车,"SaloonCar":轿车,"Microbus":面包车,"MicroTruck":小货车,"Tricycle":三轮车,    "Passerby":行人                                                    
                                                                             // Plate Category："Unknown" 未知,"Normal" 蓝牌黑牌,"Yellow" 黄牌,"DoubleYellow" 双层黄尾牌,"Police" 警牌"Armed" 武警牌,
                                                                             // "Military" 部队号牌,"DoubleMilitary" 部队双层,"SAR" 港澳特区号牌,"Trainning" 教练车号牌
                                                                             // "Personal" 个性号牌,"Agri" 农用牌,"Embassy" 使馆号牌,"Moto" 摩托车号牌,"Tractor" 拖拉机号牌,"Other" 其他号牌
                                                                             // HumanFace Category:"Normal" 普通人脸,"HideEye" 眼部遮挡,"HideNose" 鼻子遮挡,"HideMouth" 嘴部遮挡
        public byte[] byReserved1 = new byte[3];
        public byte bPicEnble;//是否有物体对应图片文件信息, 类型小bool, 取值0或者1
        public NET_PIC_INFO stPicInfo;//物体对应图片信息
        public byte bShotFrame;//是否是抓拍张的识别结果,  类型小bool, 取值0或者1
        public byte bColor;//物体颜色(rgbaMainColor)是否可用, 类型小bool, 取值0或者1
        public byte byReserved2;
        public byte byTimeType;//时间表示类型,详见EM_TIME_TYPE说明
        public NET_TIME_EX stuCurrentTime;//针对视频浓缩,当前时间戳（物体抓拍或识别时,会将此识别智能帧附在一个视频帧或jpeg图片中,此帧所在原始视频中的出现时间）
        public NET_TIME_EX stuStartTime;//开始时间戳（物体开始出现时）
        public NET_TIME_EX stuEndTime;//结束时间戳（物体最后出现时）
        public NET_RECT stuOriginalBoundingBox;//包围盒(绝对坐标)
        public NET_RECT stuSignBoundingBox;//车标坐标包围盒
        public int dwCurrentSequence;//当前帧序号（抓下这个物体时的帧）
        public int dwBeginSequence;//开始帧序号（物体开始出现时的帧序号）
        public int dwEndSequence;//结束帧序号（物体消逝时的帧序号）
        public long nBeginFileOffse;//开始时文件偏移,单位:字（物体开始出现时,视频帧在原始视频文件中相对于文件起始处的偏移）
        public long nEndFileOffset;//结束时文件偏移,单位:字节（物体消逝时,视频帧在原始视频文件中相对于文件起始处的偏移）
        public byte[] byColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX];//物体颜色相似度,取值范围：0-100,数组下标值代表某种颜色,详见 EM_COLOR_TYPE
        public byte[] byUpperBodyColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX];//上半身物体颜色相似度(物体类型为人时有效)
        public byte[] byLowerBodyColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX];//下半身物体颜色相似度(物体类型为人时有效)
        public int nRelativeID;//相关物体ID
        public byte[] szSubText = new byte[20];//"ObjectType"为"Vehicle"或者"Logo"时,表示车标下的某一车系,比如奥迪A6L,由于车系较多,SDK实现时透传此字段,设备如实填写。
        public byte[] byReserved = new byte[2];
        
        public NET_MSG_OBJECT()
        {
            // 强制采用最大四字节对其
            setAlignType(ALIGN_GNUC);
        }
    }
    
    //NET_FILE_QUERY_FACE对应的人脸识别服务FINDNEXT查询返回参数
    public static class MEDIAFILE_FACERECOGNITION_INFO extends Structure
    {
        public int dwSize;//结构体大小
        public int bGlobalScenePic;//全景图是否存在, BOOL类型，取值0或1
        public NET_PIC_INFO_EX stGlobalScenePic;//全景图片文件路径
        public NET_MSG_OBJECT stuObject;//目标人脸物体信息
        public NET_PIC_INFO_EX stObjectPic;//目标人脸文件路径
        public int nCandidateNum;//当前人脸匹配到的候选对象数量
        public CANDIDATE_INFO[] stuCandidates = (CANDIDATE_INFO[])new CANDIDATE_INFO().toArray(NET_MAX_CANDIDATE_NUM);//当前人脸匹配到的候选对象信息
        public NET_CANDIDAT_PIC_PATHS[] stuCandidatesPic = (NET_CANDIDAT_PIC_PATHS[])new NET_CANDIDAT_PIC_PATHS().toArray(NET_MAX_CANDIDATE_NUM);//当前人脸匹配到的候选对象图片文件路径
        public NET_TIME stTime;//报警发生时间
        public byte[] szAddress = new byte[MAX_PATH];//报警发生地点
        public int nChannelId;//通道号
        
        public MEDIAFILE_FACERECOGNITION_INFO()
        {
            this.dwSize = this.size();
        }
    }
    
    //每个视频输入通道对应的所有事件规则：缓冲区pRuleBuf填充多个事件规则信息，每个事件规则信息内容为CFG_RULE_INFO+"事件类型对应的规则配置结构体"。
    public static class CFG_ANALYSERULES_INFO extends Structure
    {
        public int nRuleCount;//事件规则个数
        public Pointer pRuleBuf;//每个视频输入通道对应的视频分析事件规则配置缓冲, char *
        public int nRuleLen;//缓冲大小
    }
    
    public static class CFG_RULE_INFO extends Structure
    {
        public int dwRuleType;//事件类型，详见dhnetsdk.h中"智能分析事件类型"
        public int nRuleSize;//该事件类型规则配置结构体大小
    }
    
    //区域顶点信息
    public static class CFG_POLYGON extends Structure
    {
        public int nX;//0~8191
        public int nY;
    }
    
    //区域信息
    public static class CFG_REGION extends Structure
    {
        public int nPointNum;
        public CFG_POLYGON[] stuPolygon = (CFG_POLYGON[])new CFG_POLYGON().toArray(MAX_POLYGON_NUM);
    }
    
    public static class CFG_SIZE_Attribute extends Union
    {
        public float nWidth;//宽
        public float nArea;//面积
    }
    
    //Size
    public static class CFG_SIZE extends Structure
    {
        public CFG_SIZE_Attribute attr;
        public float nHeight;//高
    }
    
    //校准框信息
    public static class CFG_CALIBRATEBOX_INFO extends Structure
    {
        public CFG_POLYGON stuCenterPoint;//校准框中心点坐标(点的坐标归一化到[0,8191]区间)
        public float fRatio;//相对基准校准框的比率(比如1表示基准框大小，0.5表示基准框大小的一半)
    }
    
    //尺寸过滤器
    public static class CFG_SIZEFILTER_INFO extends Structure
    {
        public int nCalibrateBoxNum;//校准框个数
        public CFG_CALIBRATEBOX_INFO[] stuCalibrateBoxs = (CFG_CALIBRATEBOX_INFO[])new CFG_CALIBRATEBOX_INFO().toArray(MAX_CALIBRATEBOX_NUM);//校准框(远端近端标定模式下有效)
        public byte bMeasureModeEnable;//计量方式参数是否有效， 类型bool, 取值0或1
        public byte bMeasureMode;//计量方式,0-像素，不需要远端、近端标定,1-实际长度，单位：米,2-远端近端标定后的像素
        public byte bFilterTypeEnable;//过滤类型参数是否有效， 类型bool, 取值0或1
        // ByArea,ByRatio仅作兼容，使用独立的ByArea和ByRatio选项代替 2012/03/06
        public byte bFilterType;//过滤类型:0:"ByLength",1:"ByArea",2"ByWidthHeight"
        public byte[] bReserved = new byte[2];//保留字段
        public byte bFilterMinSizeEnable;//物体最小尺寸参数是否有效， 类型bool, 取值0或1
        public byte bFilterMaxSizeEnable;//物体最大尺寸参数是否有效， 类型bool, 取值0或1
        public CFG_SIZE stuFilterMinSize;//物体最小尺寸"ByLength"模式下表示宽高的尺寸，"ByArea"模式下宽表示面积，高无效(远端近端标定模式下表示基准框的宽高尺寸)。
        public CFG_SIZE stuFilterMaxSize;//物体最大尺寸"ByLength"模式下表示宽高的尺寸，"ByArea"模式下宽表示面积，高无效(远端近端标定模式下表示基准框的宽高尺寸)。
        public byte abByArea;//类型bool, 取值0或1
        public byte abMinArea;//类型bool, 取值0或1
        public byte abMaxArea;//类型bool, 取值0或1
        public byte abMinAreaSize;//类型bool, 取值0或1
        public byte abMaxAreaSize;//类型bool, 取值0或1
        public byte bByArea;//是否按面积过滤通过能力ComplexSizeFilter判断是否可用， 类型bool, 取值0或1
        public byte[] bReserved1 = new byte[2];
        public float nMinArea;//最小面积
        public float nMaxArea;//最大面积
        public CFG_SIZE stuMinAreaSize;//最小面积矩形框尺寸"计量方式"为"像素"时，表示最小面积矩形框的宽高尺寸；"计量方式"为"远端近端标定模式"时，表示基准框的最小宽高尺寸；
        public CFG_SIZE stuMaxAreaSize;//最大面积矩形框尺寸,同上
        public byte abByRatio;//类型bool, 取值0或1
        public byte abMinRatio;//类型bool, 取值0或1
        public byte abMaxRatio;//类型bool, 取值0或1
        public byte abMinRatioSize;//类型bool, 取值0或1
        public byte abMaxRatioSize;//类型bool, 取值0或1
        public byte bByRatio;//是否按宽高比过滤通过能力ComplexSizeFilter判断是否可用， 类型bool, 取值0或1
        public byte[] bReserved2 = new byte[2];
        public double dMinRatio;//最小宽高比
        public double dMaxRatio;//最大宽高比
        public CFG_SIZE stuMinRatioSize;//最小宽高比矩形框尺寸，最小宽高比对应矩形框的宽高尺寸。
        public CFG_SIZE stuMaxRatioSize;//最大宽高比矩形框尺寸，同上
        public int nAreaCalibrateBoxNum;//面积校准框个数
        public CFG_CALIBRATEBOX_INFO[] stuAreaCalibrateBoxs = (CFG_CALIBRATEBOX_INFO[])new CFG_CALIBRATEBOX_INFO().toArray(MAX_CALIBRATEBOX_NUM);//面积校准框
        public int nRatioCalibrateBoxs;//宽高校准框个数
        public CFG_CALIBRATEBOX_INFO[] stuRatioCalibrateBoxs = (CFG_CALIBRATEBOX_INFO[])new CFG_CALIBRATEBOX_INFO().toArray(MAX_CALIBRATEBOX_NUM);//宽高校准框
        public byte abBySize;//长宽过滤使能参数是否有效， 类型bool, 取值0或1
        public byte bBySize;//长宽过滤使能， 类型bool, 取值0或1
    }
    
    //各种物体特定的过滤器
    public static class CFG_OBJECT_SIZEFILTER_INFO extends Structure
    {
        public byte[] szObjectType = new byte[MAX_NAME_LEN];//物体类型
        public CFG_SIZEFILTER_INFO stSizeFilter;//对应的尺寸过滤器
    }
    
    //特殊区域的属性类型
    public static class EM_SEPCIALREGION_PROPERTY_TYPE extends Structure
    {
         public static final int EM_SEPCIALREGION_PROPERTY_TYPE_HIGHLIGHT = 1;//高亮，键盘检测区域具有此特性
         public static final int EM_SEPCIALREGION_PROPERTY_TYPE_REGULARBLINK = 2; //规律的闪烁，插卡区域具有此特性
         public static final int EM_SEPCIALREGION_PROPERTY_TYPE_IREGULARBLINK = 3; //不规律的闪烁，屏幕区域具有此特性
         public static final int EM_SEPCIALREGION_PROPERTY_TYPE_NUM = 4; 
    }
    
    //特殊检测区，是指从检测区中区分出来，有特殊检测属性的区域
    public static class CFG_SPECIALDETECT_INFO extends Structure
    {
        public int nDetectNum;//检测区域顶点数
        public CFG_POLYGON[] stDetectRegion = (CFG_POLYGON[])new CFG_POLYGON().toArray(MAX_POLYGON_NUM);//检测区域
        public int nPropertyNum;//特殊检测区属性个数
        public int[] nPropertys = new int[EM_SEPCIALREGION_PROPERTY_TYPE.EM_SEPCIALREGION_PROPERTY_TYPE_NUM];//特殊检测区属性
    }
    
    //各类物体的子类型
    public static class CFG_CATEGORY_TYPE extends Structure
    {
        public static final int CFG_CATEGORY_TYPE_UNKNOW = 0; //未知类型
         //车型相关子类别
         public static final int CFG_CATEGORY_VEHICLE_TYPE_MOTOR = 1; //"Motor"机动车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_NON_MOTOR = 2; //"Non-Motor"非机动车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_BUS = 3; //"Bus"公交车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_BICYCLE = 4; //"Bicycle"自行车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_MOTORCYCLE = 5; //"Motorcycle"摩托车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_UNLICENSEDMOTOR = 6; //"UnlicensedMotor":无牌机动车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_LARGECAR = 7; //"LargeCar"大型汽车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_MICROCAR = 8; //"MicroCar"小型汽车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_EMBASSYCAR = 9; //"EmbassyCar"使馆汽车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_MARGINALCAR = 10; //"MarginalCar"领馆汽车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_AREAOUTCAR = 11; //"AreaoutCar"境外汽车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_FOREIGNCAR = 12; //"ForeignCar"外籍汽车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_DUALTRIWHEELMOTORCYCLE = 13; //"DualTriWheelMotorcycle"两、三轮摩托车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_LIGHTMOTORCYCLE = 14; //"LightMotorcycle"轻便摩托车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_EMBASSYMOTORCYCLE = 15 ; //"EmbassyMotorcycle"使馆摩托车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_MARGINALMOTORCYCLE = 16; //"MarginalMotorcycle"领馆摩托车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_AREAOUTMOTORCYCLE = 17; //"AreaoutMotorcycle"境外摩托车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_FOREIGNMOTORCYCLE = 18; //"ForeignMotorcycle"外籍摩托车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_FARMTRANSMITCAR = 19; //"FarmTransmitCar"农用运输车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_TRACTOR = 20; //"Tractor"拖拉机
         public static final int CFG_CATEGORY_VEHICLE_TYPE_TRAILER = 21; //"Trailer"挂车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_COACHCAR = 22; //"CoachCar"教练汽车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_COACHMOTORCYCLE = 23; //"CoachMotorcycle"教练摩托车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_TRIALCAR = 24; //"TrialCar"试验汽车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_TRIALMOTORCYCLE = 25; //"TrialMotorcycle"试验摩托车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_TEMPORARYENTRYCAR = 26; //"TemporaryEntryCar"临时入境汽车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_TEMPORARYENTRYMOTORCYCLE = 27; //"TemporaryEntryMotorcycle"临时入境摩托车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_TEMPORARYSTEERCAR = 28; //"TemporarySteerCar"临时行驶车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_PASSENGERCAR = 29; //"PassengerCar"客车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_LARGETRUCK = 30; //"LargeTruck"大货车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_MIDTRUCK =31 ; //"MidTruck"中货车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_SALOONCAR = 32; //"SaloonCar"轿车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_MICROBUS = 33; //"Microbus"面包车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_MICROTRUCK = 34; //"MicroTruck"小货车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_TRICYCLE = 35; //"Tricycle"三轮车
         public static final int CFG_CATEGORY_VEHICLE_TYPE_PASSERBY = 36; //"Passerby"行人
         //车牌相关子类别
         public static final int CFG_CATEGORY_PLATE_TYPE_NORMAL = 37; //"Normal"蓝牌黑字
         public static final int CFG_CATEGORY_PLATE_TYPE_YELLOW = 38; //"Yellow"黄牌
         public static final int CFG_CATEGORY_PLATE_TYPE_DOUBLEYELLOW = 39; //"DoubleYellow"双层黄尾牌
         public static final int CFG_CATEGORY_PLATE_TYPE_POLICE = 40; //"Police"警牌
         public static final int CFG_CATEGORY_PLATE_TYPE_ARMED = 41; //"Armed"武警牌
         public static final int CFG_CATEGORY_PLATE_TYPE_MILITARY = 42; //"Military"部队号牌
         public static final int CFG_CATEGORY_PLATE_TYPE_DOUBLEMILITARY = 43; //"DoubleMilitary"部队双层
         public static final int CFG_CATEGORY_PLATE_TYPE_SAR = 44; //"SAR"港澳特区号牌
         public static final int CFG_CATEGORY_PLATE_TYPE_TRAINNING = 45; //"Trainning"教练车号牌
         public static final int CFG_CATEGORY_PLATE_TYPE_PERSONAL = 46; //"Personal"个性号牌
         public static final int CFG_CATEGORY_PLATE_TYPE_AGRI = 47; //"Agri"农用牌
         public static final int CFG_CATEGORY_PLATE_TYPE_EMBASSY = 48; //"Embassy"使馆号牌
         public static final int CFG_CATEGORY_PLATE_TYPE_MOTO = 49; //"Moto"摩托车号牌
         public static final int CFG_CATEGORY_PLATE_TYPE_TRACTOR = 50; //"Tractor"拖拉机号牌
         public static final int CFG_CATEGORY_PLATE_TYPE_OFFICIALCAR = 51; //"OfficialCar"公务车
         public static final int CFG_CATEGORY_PLATE_TYPE_PERSONALCAR = 52; //"PersonalCar"私家车
         public static final int CFG_CATEGORY_PLATE_TYPE_WARCAR = 53; //"WarCar"军用
         public static final int CFG_CATEGORY_PLATE_TYPE_OTHER = 54; //"Other"其他号牌
         public static final int CFG_CATEGORY_PLATE_TYPE_CIVILAVIATION = 55; //"Civilaviation"民航号牌
         public static final int CFG_CATEGORY_PLATE_TYPE_BLACK = 56; //"Black"黑牌
    }
    
    //不同区域各种类型物体的检测模块配置
    public static class CFG_MODULE_INFO extends Structure
    {
        // 信息
        public byte[] szObjectType = new byte[MAX_NAME_LEN];//默认物体类型,详见"支持的检测物体类型列表"
        public byte bSnapShot;//是否对识别物体抓图，类型bool，取值0或1
        public byte bSensitivity;//灵敏度,取值1-10，值越小灵敏度越低
        public byte bMeasureModeEnable;//计量方式参数是否有效，类型bool，取值0或1
        public byte bMeasureMode;//计量方式,0-像素，不需要远端、近端标定,1-实际长度，单位：米,2-远端近端标定后的像素
        public int nDetectRegionPoint;//检测区域顶点数
        public CFG_POLYGON[] stuDetectRegion = (CFG_POLYGON[])new CFG_POLYGON().toArray(MAX_POLYGON_NUM);//检测区域
        public int nTrackRegionPoint;//跟踪区域顶点数
        public CFG_POLYGON[] stuTrackRegion = (CFG_POLYGON[])new CFG_POLYGON().toArray(MAX_POLYGON_NUM);//跟踪区域
        public byte bFilterTypeEnable;//过滤类型参数是否有效，类型bool，取值0或1
        // ByArea,ByRatio仅作兼容使枚懒⒌腂yArea和ByRatio选项代替 2012/03/06
        public byte nFilterType;//过滤类型:0:"ByLength",1:"ByArea",2:"ByWidthHeight",3:"ByRatio":
        public byte bBackgroudEnable;//区域的背景类型参数是否有效，类型bool，取值0或1
        public byte bBackgroud;//区域的背景类型,0-普通类型,1-高光类型
        public byte abBySize;//长宽过滤使能参数是否有效，类型bool，取值0或1
        public byte bBySize;//长宽过滤使能，类型bool，取值0或1
        public byte bFilterMinSizeEnable;//物体最小尺寸参数是否有效，类型bool，取值0或1
        public byte bFilterMaxSizeEnable;//物体最大尺寸参数是否有效，类型bool，取值0或1
        public CFG_SIZE stuFilterMinSize;//物体最小尺寸"ByLength"模式下表示宽高的尺寸，"ByArea"模式下宽表示面积，高无效。
        public CFG_SIZE stuFilterMaxSize;//物体最大尺寸"ByLength"模式下表示宽高的尺寸，"ByArea"模式下宽表示面积，高无效。
        public int nExcludeRegionNum;//排除区域数
        public CFG_REGION[] stuExcludeRegion = (CFG_REGION[])new CFG_REGION().toArray(MAX_EXCLUDEREGION_NUM);//排除区域
        public int nCalibrateBoxNum;//校准框个数
        public CFG_CALIBRATEBOX_INFO[] stuCalibrateBoxs = (CFG_CALIBRATEBOX_INFO[])new CFG_CALIBRATEBOX_INFO().toArray(MAX_CALIBRATEBOX_NUM);//校准框(远端近端标定模式下有效)
        public byte bAccuracy;//检测精度是否有效，类型bool，取值0或1
        public byte byAccuracy;//检测精度
        public byte bMovingStep;//算法移动步长是否有效，类型bool，取值0或1
        public byte byMovingStep;//算法移动步长
        public byte bScalingFactor;//算法缩放因子是否有效，类型bool，取值0或1
        public byte byScalingFactor;//算法缩放因子
        public byte[] bReserved2 = new byte[1];//保留字段
        public byte abDetectBalance;//漏检和误检平衡参数是否有效，类型bool，取值0或1
        public int nDetectBalance;//漏检和误检平衡0-折中模式(默认)1-漏检更少2-误检更少
        public byte abByRatio;//类型bool，取值0或1
        public byte abMinRatio;;//类型bool，取值0或1
        public byte abMaxRatio;;//类型bool，取值0或1
        public byte abMinAreaSize;;//类型bool，取值0或1
        public byte abMaxAreaSize;;//类型bool，取值0或1
        public byte bByRatio;//是否按宽高比过滤通过能力ComplexSizeFilter判断是否可用可以和nFilterType复用，类型bool，取值0或1
        public byte[] bReserved1 = new byte[2];
        public double dMinRatio;//最小宽高比
        public double dMaxRatio;//最大宽高比
        public CFG_SIZE stuMinAreaSize;//最小面积矩形框尺寸"计量方式"为"像素"时，表示最小面积矩形框的宽高尺寸；"计量方式"为"远端近端标定模式"时，表示基准框的最小宽高尺寸；
        public CFG_SIZE stuMaxAreaSize;//最大面积矩形框尺寸,同上
        public byte abByArea;//类型bool，取值0或1
        public byte abMinArea;//类型bool，取值0或1
        public byte abMaxArea;//类型bool，取值0或1
        public byte abMinRatioSize;//类型bool，取值0或1
        public byte abMaxRatioSize;//类型bool，取值0或1
        public byte bByArea;//是否按面积过滤通过能力ComplexSizeFilter判断是否可用可以和nFilterType复用，类型bool，取值0或1
        public byte[] bReserved3 = new byte[2];
        public float nMinArea;//最小面积
        public float nMaxArea;//最大面积
        public CFG_SIZE stuMinRatioSize;//最小宽高比矩形框尺寸，最小宽高比对应矩形框的宽高尺寸。
        public CFG_SIZE stuMaxRatioSize;//最大宽高比矩形框尺寸，同上
        public int nAreaCalibrateBoxNum;//面积校准框个数
        public CFG_CALIBRATEBOX_INFO[] stuAreaCalibrateBoxs = (CFG_CALIBRATEBOX_INFO[])new CFG_CALIBRATEBOX_INFO().toArray(MAX_CALIBRATEBOX_NUM);//面积校准框
        public int nRatioCalibrateBoxs;//比例校准框个数
        public CFG_CALIBRATEBOX_INFO[] stuRatioCalibrateBoxs = (CFG_CALIBRATEBOX_INFO[])new CFG_CALIBRATEBOX_INFO().toArray(MAX_CALIBRATEBOX_NUM);//比例校准框个数
        public byte bAntiDisturbance;//是否开启去扰动模块，类型bool，取值0或1
        public byte bBacklight;//是否有逆光，类型bool，取值0或1
        public byte bShadow;//是否有阴影，类型bool，取值0或1
        public byte bContourAssistantTrack;//是否开启轮廓辅助跟踪，例：在人脸识别时可以通过跟踪人体来辅助识别脸，类型bool，取值0或1
        public int nPtzPresetId;//云台预置点，0~255，0表示固定场景，忽略预置点。大于0表示在此预置点时模块有效
        public int nObjectFilterNum;//物体特定的过滤器个数
        public CFG_OBJECT_SIZEFILTER_INFO[] stObjectFilter= (CFG_OBJECT_SIZEFILTER_INFO[])new CFG_OBJECT_SIZEFILTER_INFO().toArray(MAX_OBJECT_LIST_SIZE);//物体特定的过滤器信息
        public int abObjectImageSize; //BOOL类型，取值0或1
        public CFG_SIZE stObjectImageSize;//保证物体图像尺寸相同,单位是像素,不支持小数，取值：>=0,0表示自动调整大小
        public int nSpecailDetectNum;//特殊检测区域个数
        public CFG_SPECIALDETECT_INFO[] stSpecialDetectRegions= (CFG_SPECIALDETECT_INFO[])new CFG_SPECIALDETECT_INFO().toArray(MAX_SPECIALDETECT_NUM);//特殊检测区信息
        public int nAttribute;//需要识别物体的属性个数， 类型为unsigned int
        public byte[] szAttributes = new byte[MAX_OBJECT_ATTRIBUTES_SIZE*MAX_NAME_LEN];//需要识别物体的属性列表，“Category”
        public int abPlateAnalyseMode;//nPlateAnalyseMode是否有效, BOOL类型，取值0或1
        public int nPlateAnalyseMode;//车牌识别模式，0-只识别车头牌照1-只识别车尾牌照2-车头牌照优先（场景中大部分车均是车头牌照）3-车尾牌照优先（场景中大部分车均是车尾牌照）
        //szAttributes属性存在"Category"时生效
        public int nCategoryNum;//需要识别物体的子类型总数
        public int[] emCategoryType= new int[MAX_CATEGORY_TYPE_NUMBER];//子类型信息, 元素取CFG_CATEGORY_TYPE中的值
    }
    
    public static class CFG_ANALYSEMODULES_INFO extends Structure
    {
        public int nMoudlesNum;//检测模块数
        public CFG_MODULE_INFO[] stuModuleInfo= (CFG_MODULE_INFO[])new CFG_MODULE_INFO().toArray(MAX_ANALYSE_MODULE_NUM);//每个视频输入通道对应的各种类型物体的检测模块配置
    }
    
    // CLIENT_FindGroupInfo接口输入参数
    public static class NET_IN_FIND_GROUP_INFO extends Structure
    {
        public int dwSize;
        public byte[] szGroupId = new byte[NET_COMMON_STRING_64];//人员组ID,唯一标识一组人员,为空表示查询全部人员组信息
        
        public NET_IN_FIND_GROUP_INFO()
        {
            this.dwSize = this.size();
        }
    }
    
    // 人脸数据类型
    public static class EM_FACE_DB_TYPE extends Structure
    {
        public static final int NET_FACE_DB_TYPE_UNKOWN = 0; 
        public static final int NET_FACE_DB_TYPE_HISTORY = 1; //历史数据库,存放的是检测出的人脸信息,一般没有包含人脸对应人员信息
        public static final int NET_FACE_DB_TYPE_BLACKLIST = 2;//黑名单数据库
        public static final int NET_FACE_DB_TYPE_WHITELIST = 3; //白名单数据库,废弃
        public static final int NET_FACE_DB_TYPE_ALARM = 4;//报警库
    }
    
    // 人员组信息
    public static class NET_FACERECONGNITION_GROUP_INFO extends Structure
    {
        public int dwSize;
        public int emFaceDBType;//人员组类型,详见EM_FACE_DB_TYPE, 取值为EM_FACE_DB_TYPE中的值
        public byte[] szGroupId = new byte[NET_COMMON_STRING_64];//人员组ID,唯一标识一组人员(不可修改,添加操作时无效)
        public byte[] szGroupName = new byte[NET_COMMON_STRING_128];//人员组名称
        public byte[] szGroupRemarks = new byte[NET_COMMON_STRING_256];//人员组备注信息
        public int nGroupSize;//当前组内人员数
        
        public NET_FACERECONGNITION_GROUP_INFO()
        {
            this.dwSize = this.size();
        }
    }
    
    // CLIENT_FindGroupInfo接口输出参数
    public static class NET_OUT_FIND_GROUP_INFO extends Structure
    {
        public int dwSize;
        public Pointer pGroupInfos;//人员组信息,由用户申请空间， 指向NET_FACERECONGNITION_GROUP_INFO的指针
        public int nMaxGroupNum;//当前申请的数组大小
        public int nRetGroupNum;//设备返回的人员组个数
        
        public NET_OUT_FIND_GROUP_INFO()
        {
            this.dwSize = this.size();
        }
    }

    // 人员组操作枚举
    public static class EM_OPERATE_FACERECONGNITION_GROUP_TYPE extends Structure
    {
        public static final int NET_FACERECONGNITION_GROUP_UNKOWN = 0;
        public static final int NET_FACERECONGNITION_GROUP_ADD = 1; //添加人员组信息
        public static final int NET_FACERECONGNITION_GROUP_MODIFY = 2; //修改人员组信息
        public static final int NET_FACERECONGNITION_GROUP_DELETE = 3; //删除人员组信息
    }
    
    // CLIENT_OperateFaceRecognitionGroup接口输入参数
    public static class NET_IN_OPERATE_FACERECONGNITION_GROUP extends Structure
    {
        public int dwSize;
        public int emOperateType;//操作类型, 取值为EM_OPERATE_FACERECONGNITION_GROUP_TYPE中的值
        public Pointer pOPerateInfo;//相关操作信息，指向void *
        
        public NET_IN_OPERATE_FACERECONGNITION_GROUP()
        {
            this.dwSize = this.size();
        }
    }
    
    // CLIENT_OperateFaceRecognitionGroup接口输出参数
    public static class NET_OUT_OPERATE_FACERECONGNITION_GROUP extends Structure
    {
        public int dwSize;
        public byte[] szGroupId = new byte[NET_COMMON_STRING_64];//新增记录的人员组ID,唯一标识一组人员
        
        public NET_OUT_OPERATE_FACERECONGNITION_GROUP()
        {
            this.dwSize = this.size();
        }
    }
    
    // CLIENT_SetGroupInfoForChannel接口输入参数
    public static class NET_IN_SET_GROUPINFO_FOR_CHANNEL extends Structure
    {
        public int dwSize;
        public int nChannelID;//通道号
        public int nGroupIdNum;//人员组数
        public byte[] szGroupId = new byte[MAX_GOURP_NUM*NET_COMMON_STRING_64];//人员组ID
        
        public NET_IN_SET_GROUPINFO_FOR_CHANNEL()
        {
            this.dwSize = this.size();
        }
    }
    
    // CLIENT_SetGroupInfoForChannel接口输出参数
    public static class NET_OUT_SET_GROUPINFO_FOR_CHANNEL extends Structure
    {
        public int dwSize;
        
        public NET_OUT_SET_GROUPINFO_FOR_CHANNEL()
        {
            this.dwSize = this.size();
        }
    }
    
    // 人脸查询状态信息回调函数, lAttachHandle是CLIENT_AttachFaceFindState的返回值
    public static class NET_CB_FACE_FIND_STATE extends Structure
    {
        public int dwSize;
        public int nToken;//视频浓缩任务数据库主键ID
        public int nProgress;//正常取值范围：0-100,-1,表示查询token不存在(当订阅一个不存在或结束的查询时)
        public int nCurrentCount;//目前符合查询条件的人脸数量
        
        public NET_CB_FACE_FIND_STATE()
        {
            this.dwSize = this.size();
        }
    }
    
    //CLIENT_AttachFaceFindState接口输入参数
    public static class NET_IN_FACE_FIND_STATE extends Structure
    {
        public int dwSize;//结构体大小,必须填写
        public int nTokenNum;//查询令牌数,为0时,表示订阅所有的查询任务
        public Pointer nTokens;//查询令牌, 指向int的指针
        public fFaceFindState cbFaceFindState;//回调函数
        public NativeLong dwUser;//用户数据
        
        public NET_IN_FACE_FIND_STATE()
        {
            this.dwSize = this.size();
        }
    }
    
    //CLIENT_AttachFaceFindState接口输入参数
    public static class NET_OUT_FACE_FIND_STATE extends Structure
    {
        public int dwSize;
        
        public NET_OUT_FACE_FIND_STATE()
        {
            this.dwSize = this.size();
        }
    }
    
    // SDK全局日志打印信息
    public static class LOG_SET_PRINT_INFO extends Structure
    {
        public int dwSize;
        public int bSetFilePath;//是否重设日志路径, BOOL类型，取值0或1
        public byte[] szLogFilePath = new byte[MAX_LOG_PATH_LEN];//日志路径(默认"./sdk_log/sdk_log.log")
        public int bSetFileSize;//是否重设日志文件大小, BOOL类型，取值0或1
        public int nFileSize;//每个日志文件的大小(默认大小10240),单位:比特, 类型为unsigned int
        public int bSetFileNum;//是否重设日志文件个数, BOOL类型，取值0或1
        public int nFileNum;//绕接日志文件个数(默认大小10), 类型为unsigned int
        public int bSetPrintStrategy;//是否重设日志打印输出策略, BOOL类型，取值0或1
        public int nPrintStrategy;//日志输出策略,0:输出到文件(默认); 1:输出到窗口, 类型为unsigned int
        
        public LOG_SET_PRINT_INFO()
        {
            this.dwSize = this.size();
        }
    }
    
    // media文件查询条件
    public static class EM_FILE_QUERY_TYPE extends Structure
    {
        public static final int NET_FILE_QUERY_TRAFFICCAR = 0; //交通车辆信息
        public static final int NET_FILE_QUERY_ATM = 1; //ATM信息
        public static final int NET_FILE_QUERY_ATMTXN = 2; //ATM交易信息
        public static final int NET_FILE_QUERY_FACE = 3; //人脸信息MEDIAFILE_FACERECOGNITION_PARAM和MEDIAFILE_FACERECOGNITION_INFO
        public static final int NET_FILE_QUERY_FILE = 4; //文件信息对应NET_IN_MEDIA_QUERY_FILE和NET_OUT_MEDIA_QUERY_FILE
        public static final int NET_FILE_QUERY_TRAFFICCAR_EX = 5; //交通车辆信息,扩展NET_FILE_QUERY_TRAFFICCAR,支持更多的字段
        public static final int NET_FILE_QUERY_FACE_DETECTION = 6; //人脸检测事件信息MEDIAFILE_FACE_DETECTION_PARAM和MEDIAFILE_FACE_DETECTION_INFO
    }
    
    // 查询跳转条件
    public static class NET_FINDING_JUMP_OPTION_INFO extends Structure
    {
        public int dwSize;
        public int nOffset;//查询结果偏移量,是相对于当前查询的第一条查询结果的位置偏移
        
        public NET_FINDING_JUMP_OPTION_INFO()
        {
            this.dwSize = this.size();
        }
    }
    
    // 云台联动类型
    public static class CFG_LINK_TYPE extends Structure
    {
        public static final int LINK_TYPE_NONE = 0; //无联动
        public static final int LINK_TYPE_PRESET = 1; //联动预置点
        public static final int LINK_TYPE_TOUR = 2; //联动巡航
        public static final int LINK_TYPE_PATTERN = 3; //联动轨迹
    }

    // 联动云台信息
    public static class CFG_PTZ_LINK extends Structure
    {
        public int emType;//联动类型, 取值为CFG_LINK_TYPE中的值
        public int nValue;//联动取值分别对应预置点号，巡航号等等
    }

    // 联动云台信息扩展
    public static class CFG_PTZ_LINK_EX extends Structure
    {
        public int emType;//联动类型, 取值为CFG_LINK_TYPE中的值
        public int nParam1;//联动参数1
        public int nParam2;//联动参数2
        public int nParam3;//联动参数3
        public int nChannelID;//所联动云台通道
    }

    // RGBA信息
    public static class CFG_RGBA extends Structure
    {
        public int nRed;
        public int nGreen;
        public int nBlue;
        public int nAlpha;
    }

    // 事件标题内容结构体
    public static class CFG_EVENT_TITLE extends Structure
    {
        public byte[] szText = new byte[MAX_CHANNELNAME_LEN];
        public CFG_POLYGON stuPoint;//标题左上角坐标,采用0-8191相对坐标系
        public CFG_SIZE stuSize;//标题的宽度和高度,采用0-8191相对坐标系，某项或者两项为0表示按照字体自适应宽高
        public CFG_RGBA stuFrontColor;//前景颜色
        public CFG_RGBA stuBackColor;//背景颜色
    }

    // 邮件附件类型
    public static class CFG_ATTACHMENT_TYPE extends Structure
    {
        public static final int ATTACHMENT_TYPE_PIC = 0; //图片附件
        public static final int ATTACHMENT_TYPE_VIDEO = 1; //视频附件
        public static final int ATTACHMENT_TYPE_NUM = 2; //附件类型总数
    }

    // 分割模式
    public static class CFG_SPLITMODE extends Structure
    {
        public static final int SPLITMODE_1 =1;//1画面
        public static final int SPLITMODE_2 =2;//2画面
        public static final int SPLITMODE_4 =4;//4画面
        public static final int SPLITMODE_6 =6;//6画面
        public static final int SPLITMODE_8 =8;//8画面
        public static final int SPLITMODE_9 =9;//9画面
        public static final int SPLITMODE_12 =12;//12画面
        public static final int SPLITMODE_16 =16;//16画面
        public static final int SPLITMODE_20 =20;//20画面
        public static final int SPLITMODE_25 =25;//25画面
        public static final int SPLITMODE_36 =36;//36画面
        public static final int SPLITMODE_64 =64;//64画面
        public static final int SPLITMODE_144 =144;//144画面
        public static final int SPLITMODE_PIP =1000;//画中画分割模式基础值
        public static final int SPLITMODE_PIP1 =SPLITMODE_PIP+1;//画中画模式, 1个全屏大画面+1个小画面窗口
        public static final int SPLITMODE_PIP3 =SPLITMODE_PIP+3;//画中画模式, 1个全屏大画面+3个小画面窗口
        public static final int SPLITMODE_FREE =SPLITMODE_PIP*2;//自由开窗模式，可以自由创建、关闭窗口，自由设置窗口位置和Z轴次序
        public static final int SPLITMODE_COMPOSITE_1 = SPLITMODE_PIP * 3 + 1;  // 融合屏成员1分割
        public static final int SPLITMODE_COMPOSITE_4 = SPLITMODE_PIP * 3 + 4;  // 融合屏成员4分割
        public static final int SPLITMODE_EOF = SPLITMODE_COMPOSITE_4+1; //结束标识
    }

    // 轮巡联动配置
    public static class CFG_TOURLINK extends Structure
    {
        public int bEnable;//轮巡使能, BOOL类型，取值0或1
        public int emSplitMode;//轮巡时的分割模式,取值范围为CFG_SPLITMODE中的值
        public int[] nChannels = new int[MAX_VIDEO_CHANNEL_NUM];//轮巡通道号列表
        public int nChannelCount;//轮巡通道数量
    }

    // 门禁操作类型
    public static class EM_CFG_ACCESSCONTROLTYPE extends Structure
    {
        public static final int EM_CFG_ACCESSCONTROLTYPE_NULL = 0;//不做操作
        public static final int EM_CFG_ACCESSCONTROLTYPE_AUTO = 1; //自动
        public static final int EM_CFG_ACCESSCONTROLTYPE_OPEN = 2; //开门
        public static final int EM_CFG_ACCESSCONTROLTYPE_CLOSE = 3; //关门
        public static final int EM_CFG_ACCESSCONTROLTYPE_OPENALWAYS = 4; //永远开启
        public static final int EM_CFG_ACCESSCONTROLTYPE_CLOSEALWAYS = 5; //永远关闭
    }

    // 邮件详细内容
    public static class CFG_MAIL_DETAIL extends Structure
    {
        public int emAttachType;//附件类型, 取值范围为CFG_ATTACHMENT_TYPE中的值
        public int nMaxSize;//文件大小上限，单位kB
        public int nMaxTimeLength;//最大录像时间长度，单位秒，对video有效
    }

    // 语音呼叫发起方
    public static class EM_CALLER_TYPE extends Structure
    {
        public static final int EM_CALLER_DEVICE = 0;//设备发起
    }

    // 呼叫协议
    public static class EM_CALLER_PROTOCOL_TYPE extends Structure
    {
        public static final int EM_CALLER_PROTOCOL_CELLULAR = 0;//手机方式
    }

    // 语音呼叫联动信息
    public static class CFG_TALKBACK_INFO extends Structure
    {
        public int bCallEnable;//语音呼叫使能, BOOL类型，取值0或1
        public int emCallerType;//语音呼叫发起方, 取值范围为EM_CALLER_TYPE中的值
        public int emCallerProtocol;//语音呼叫协议, 取值范围为EM_CALLER_PROTOCOL_TYPE中的值
    }

    // 电话报警中心联动信息
    public static class CFG_PSTN_ALARM_SERVER extends Structure
    {
        public int bNeedReport;//是否上报至电话报警中心, BOOL类型，取值0或1
        public int nServerCount;//电话报警服务器个数
        public byte[] byDestination = new byte[MAX_PSTN_SERVER_NUM];//上报的报警中心下标,详见配置CFG_PSTN_ALARM_CENTER_INFO
    }

    // 时间表信息
    public static class CFG_TIME_SCHEDULE extends Structure
    {
        public int bEnableHoliday;//是否支持节假日配置，默认为不支持，除非获取配置后返回为TRUE，不要使能假日配置, BOOL类型，取值0或1
        public CFG_TIME_SECTION[] stuTimeSection = (CFG_TIME_SECTION[])new CFG_TIME_SECTION().toArray(MAX_TIME_SCHEDULE_NUM*MAX_REC_TSECT);//第一维前7个元素对应每周7天，第8个元素对应节假日，每天最多6个时间段
    }

    // 报警联动信息
    public static class CFG_ALARM_MSG_HANDLE extends Structure
    {
        //能力
        public byte abRecordMask;//类型bool, 取值0或者1
        public byte abRecordEnable;//类型bool, 取值0或者1
        public byte abRecordLatch;//类型bool, 取值0或者1
        public byte abAlarmOutMask;//类型bool, 取值0或者1
        public byte abAlarmOutEn;//类型bool, 取值0或者1
        public byte abAlarmOutLatch;//类型bool, 取值0或者1
        public byte abExAlarmOutMask;//类型bool, 取值0或者1
        public byte abExAlarmOutEn;//类型bool, 取值0或者1
        public byte abPtzLinkEn;//类型bool, 取值0或者1
        public byte abTourMask;//类型bool, 取值0或者1
        public byte abTourEnable;//类型bool, 取值0或者1
        public byte abSnapshot;//类型bool, 取值0或者1
        public byte abSnapshotEn;//类型bool, 取值0或者1
        public byte abSnapshotPeriod;//类型bool, 取值0或者1
        public byte abSnapshotTimes;//类型bool, 取值0或者1
        public byte abTipEnable;//类型bool, 取值0或者1
        public byte abMailEnable;//类型bool, 取值0或者1
        public byte abMessageEnable;//类型bool, 取值0或者1
        public byte abBeepEnable;//类型bool, 取值0或者1
        public byte abVoiceEnable;//类型bool, 取值0或者1
        public byte abMatrixMask;//类型bool, 取值0或者1
        public byte abMatrixEnable;//类型bool, 取值0或者1
        public byte abEventLatch;//类型bool, 取值0或者1
        public byte abLogEnable;//类型bool, 取值0或者1
        public byte abDelay;//类型bool, 取值0或者1
        public byte abVideoMessageEn;//类型bool, 取值0或者1
        public byte abMMSEnable;//类型bool, 取值0或者1
        public byte abMessageToNetEn;//类型bool, 取值0或者1
        public byte abTourSplit;//类型bool, 取值0或者1
        public byte abSnapshotTitleEn;//类型bool, 取值0或者1
        public byte abChannelCount;//类型bool, 取值0或者1
        public byte abAlarmOutCount;//类型bool, 取值0或者1
        public byte abPtzLinkEx;//类型bool, 取值0或者1
        public byte abSnapshotTitle;//类型bool, 取值0或者1
        public byte abMailDetail;//类型bool, 取值0或者1
        public byte abVideoTitleEn;//类型bool, 取值0或者1
        public byte abVideoTitle;//类型bool, 取值0或者1
        public byte abTour;//类型bool, 取值0或者1
        public byte abDBKeys;//类型bool, 取值0或者1
        public byte abJpegSummary;//类型bool, 取值0或者1
        public byte abFlashEn;//类型bool, 取值0或者1
        public byte abFlashLatch;//类型bool, 取值0或者1
        //信息
        public int nChannelCount;//设备的视频通道数
        public int nAlarmOutCount;//设备的报警输出个数
        public int[] dwRecordMask = new int[MAX_CHANNEL_COUNT];//录像通道掩码(按位)
        public int bRecordEnable;//录像使能, BOOL类型，取值0或1
        public int nRecordLatch;//录像延时时间(秒)
        public int[] dwAlarmOutMask = new int[MAX_CHANNEL_COUNT];//报警输出通道掩码
        public int bAlarmOutEn;//报警输出使能, BOOL类型，取值0或1
        public int nAlarmOutLatch;//报警输出延时时间(秒)
        public int[] dwExAlarmOutMask = new int[MAX_CHANNEL_COUNT];//扩展报警输出通道掩码
        public int bExAlarmOutEn;//扩展报警输出使能, BOOL类型，取值0或1
        public CFG_PTZ_LINK [] stuPtzLink = (CFG_PTZ_LINK [])new CFG_PTZ_LINK().toArray(MAX_VIDEO_CHANNEL_NUM);//云台联动项
        public int bPtzLinkEn;//云台联动使能, BOOL类型，取值0或1
        public int[] dwTourMask = new int[MAX_CHANNEL_COUNT];//轮询通道掩码
        public int bTourEnable;//轮询使能, BOOL类型，取值0或1
        public int[] dwSnapshot = new int[MAX_CHANNEL_COUNT];//快照通道号掩码
        public int bSnapshotEn;//快照使能, BOOL类型，取值0或1
        public int nSnapshotPeriod;//连拍周期(秒)
        public int nSnapshotTimes;//连拍次数
        public int bTipEnable;//本地消息框提示, BOOL类型，取值0或1
        public int bMailEnable;//发送邮件，如果有图片，作为附件, BOOL类型，取值0或1
        public int bMessageEnable;//上传到报警服务器, BOOL类型，取值0或1
        public int bBeepEnable;//蜂鸣, BOOL类型，取值0或1
        public int bVoiceEnable;//语音提示, BOOL类型，取值0或1
        public int[] dwMatrixMask = new int[MAX_CHANNEL_COUNT];//联动视频矩阵通道掩码
        public int bMatrixEnable;//联动视频矩阵, BOOL类型，取值0或1
        public int nEventLatch;//联动开始延时时间(秒)，0－15
        public int bLogEnable;//是否记录日志, BOOL类型，取值0或1
        public int nDelay;//设置时先延时再生效，单位为秒
        public int bVideoMessageEn;//叠加提示字幕到视频。叠加的字幕包括事件类型，通道号，秒计时。BOOL类型，取值0或1
        public int bMMSEnable;//发送彩信使能, BOOL类型，取值0或1
        public int bMessageToNetEn;//消息上传给网络使能, BOOL类型，取值0或1
        public int nTourSplit;//轮巡时的分割模式0:1画面;
        public int bSnapshotTitleEn;//是否叠加图片标题, BOOL类型，取值0或1
        public int nPtzLinkExNum;//云台配置数
        public CFG_PTZ_LINK_EX[] stuPtzLinkEx = (CFG_PTZ_LINK_EX[])new CFG_PTZ_LINK_EX().toArray(MAX_VIDEO_CHANNEL_NUM);//扩展云台信息
        public int nSnapTitleNum;//图片标题内容数
        public CFG_EVENT_TITLE[] stuSnapshotTitle = (CFG_EVENT_TITLE[])new CFG_EVENT_TITLE().toArray(MAX_VIDEO_CHANNEL_NUM);//图片标题内容
        public CFG_MAIL_DETAIL stuMailDetail;//邮件详细内容
        public int bVideoTitleEn;//是否叠加视频标题，主要指主码流, BOOL类型，取值0或1
        public int nVideoTitleNum;//视频标题内容数目
        public CFG_EVENT_TITLE[] stuVideoTitle = (CFG_EVENT_TITLE[])new CFG_EVENT_TITLE().toArray(MAX_VIDEO_CHANNEL_NUM);//视频标题内容
        public int nTourNum;//轮询联动数目
        public CFG_TOURLINK[] stuTour = (CFG_TOURLINK[])new CFG_TOURLINK().toArray(MAX_VIDEO_CHANNEL_NUM);//轮询联动配置
        public int nDBKeysNum;//指定数据库关键字的有效数
        public byte[] szDBKeys = new byte[MAX_DBKEY_NUM*MAX_CHANNELNAME_LEN];//指定事件详细信息里需要写到数据库的关键字
        public byte[] byJpegSummary = new byte[MAX_SUMMARY_LEN];//叠加到JPEG图片的摘要信息
        public int bFlashEnable;//是否使能补光灯, BOOL类型，取值0或1
        public int nFlashLatch;//补光灯延时时间(秒),延时时间范围：10,300]
        public byte abAudioFileName;//bool类型，取值0或1
        public byte abAlarmBellEn;//bool类型，取值0或1
        public byte abAccessControlEn;//bool类型，取值0或1
        public byte abAccessControl;//bool类型，取值0或1
        public byte[] szAudioFileName = new byte[MAX_PATH];//联动语音文件绝对路径
        public int bAlarmBellEn;//警号使能, BOOL类型，取值0或1
        public int bAccessControlEn;//门禁使能, BOOL类型，取值0或1
        public int dwAccessControl;//门禁组数
        public int[] emAccessControlType = new int[MAX_ACCESSCONTROL_NUM];//门禁联动操作信息, 元素取值范围为EM_CFG_ACCESSCONTROLTYPE中的值
        public byte abTalkBack;//bool类型，取值0或1
        public CFG_TALKBACK_INFO stuTalkback;//语音呼叫联动信息
        public byte abPSTNAlarmServer;//bool类型，取值0或1
        public CFG_PSTN_ALARM_SERVER stuPSTNAlarmServer;//电话报警中心联动信息
        public CFG_TIME_SCHEDULE stuTimeSection;//事件响应时间表
        public byte abAlarmBellLatch;//bool类型，取值0或1
        public int nAlarmBellLatch;//警号输出延时时间(10-300秒)
    }

    // 时间段信息
    public static class CFG_TIME_SECTION extends Structure
    {
        public int dwRecordMask;//录像掩码，按位分别为动态检测录像、报警录像、定时录像、Bit3~Bit15保留、Bit16动态检测抓图、Bit17报警抓图、Bit18定时抓图
        public int nBeginHour;
        public int nBeginMin;
        public int nBeginSec;
        public int nEndHour;
        public int nEndMin;
        public int nEndSec;
    }

    // 事件类型EVENT_IVS_FACERECOGNITION(人脸识别)对应的规则配置
    public static class CFG_FACERECOGNITION_INFO extends Structure
    {
    // 信息
        public byte[] szRuleName = new byte[MAX_NAME_LEN];//规则名称,不同规则不能重名
        public byte bRuleEnable;//规则使能,bool类型，取值0或1
        public byte[] bReserved = new byte[2];//保留字段
        public int nObjectTypeNum;//相应物体类型个数
        public byte[] szObjectTypes = new byte[MAX_OBJECT_LIST_SIZE*MAX_NAME_LEN];//相应物体类型列表
        public int nPtzPresetId;//云台预置点编号0~65535
        public byte bySimilarity;//相似度，必须大于该相识度才报告(1~100)
        public byte byAccuracy;//识别精度(取值1~10，随着值增大，检测精度提高，检测速度下降。最小值为1表示检测速度优先，最大值为10表示检测精度优先)
        public byte byMode;//对比模式,0-正常,1-指定人脸区域组合,
        public byte byImportantRank;//查询重要等级大于等于此等级的人员(1~10,数值越高越重要)
        public int nAreaNum;//区域数
        public byte[] byAreas = new byte[8];//人脸区域组合,0-眉毛，1-眼睛，2-鼻子，3-嘴巴，4-脸颊(此参数在对比模式为1时有效)
        public int nMaxCandidate;//报告的最大匹配图片个数
        public CFG_ALARM_MSG_HANDLE stuEventHandler;//报警联动
        public CFG_TIME_SECTION[] stuTimeSection = (CFG_TIME_SECTION[])new CFG_TIME_SECTION().toArray(WEEK_DAY_NUM*MAX_REC_TSECT_EX);//事件响应时间段
    }
    
    // 大类业务方案    
    public static class EM_CLASS_TYPE extends Structure
    {
        public static final int EM_CLASS_UNKNOWN =0;//未知业务
        public static final int EM_CLASS_VIDEO_SYNOPSIS =1;//视频浓缩
        public static final int EM_CLASS_TRAFFIV_GATE =2;//卡口
        public static final int EM_CLASS_ELECTRONIC_POLICE =3;//电警
        public static final int EM_CLASS_SINGLE_PTZ_PARKING =4;//单球违停
        public static final int EM_CLASS_PTZ_PARKINBG =5;//主从违停
        public static final int EM_CLASS_TRAFFIC =6;//交通事件"Traffic"
        public static final int EM_CLASS_NORMAL =7;//通用行为分析"Normal"
        public static final int EM_CLASS_PRISON =8;//监所行为分析"Prison"
        public static final int EM_CLASS_ATM =9;//金融行为分析"ATM"
        public static final int EM_CLASS_METRO =10;//地铁行为分析
        public static final int EM_CLASS_FACE_DETECTION =11;//人脸检测"FaceDetection"
        public static final int EM_CLASS_FACE_RECOGNITION =12;//人脸识别"FaceRecognition"
        public static final int EM_CLASS_NUMBER_STAT =13;//人数统计"NumberStat"
        public static final int EM_CLASS_HEAT_MAP =14;//热度图"HeatMap"
        public static final int EM_CLASS_VIDEO_DIAGNOSIS =15;//视频诊断"VideoDiagnosis"
        public static final int EM_CLASS_VIDEO_ENHANCE =16;//视频增强
        public static final int EM_CLASS_SMOKEFIRE_DETECT =17;//烟火检测
        public static final int EM_CLASS_VEHICLE_ANALYSE =18;//车辆特征识别"VehicleAnalyse"
        public static final int EM_CLASS_PERSON_FEATURE =19;//人员特征识别
    }
    
    // 智能报警事件公共信息
    public static class EVENT_INTELLI_COMM_INFO extends Structure
    {
        public int emClassType;//智能事件所属大类， 取值为  EM_CLASS_TYPE 中的值
    }
    
    // 事件类型EVENT_IVS_FACERECOGNITION(人脸识别)对应的数据块描述信息
    public static class DEV_EVENT_FACERECOGNITION_INFO extends Structure
    {
        public int nChannelID;//通道号
        public byte[] szName = new byte[128];//事件名称
        public int nEventID;//事件ID
        public NET_TIME_EX UTC;//事件发生的时间
        public NET_MSG_OBJECT stuObject;//检测到的物体
        public int nCandidateNum;//当前人脸匹配到的候选对象数量
        public CANDIDATE_INFO[] stuCandidates = (CANDIDATE_INFO[])new CANDIDATE_INFO().toArray(NET_MAX_CANDIDATE_NUM);//当前人脸匹配到的候选对象信息
        public byte bEventAction;//事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束; 
        public byte byImageIndex;//图片的序号,同一时间内(精确到秒)可能有多张图片,从0开始
        public byte[] byReserved1 = new byte[2];//对齐
        public int bGlobalScenePic;//全景图是否存在, 类型为BOOL, 取值为0或者1
        public NET_PIC_INFO stuGlobalScenePicInfo;//全景图片信息
        public byte[] szSnapDevAddress = new byte[MAX_PATH];//抓拍当前人脸的设备地址,如：滨康路37号
        public int nOccurrenceCount;//事件触发累计次数， 类型为unsigned int
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;//智能事件公共信息
        public byte[] bReserved = new byte[720];//保留字节,留待扩展. 
    }
    
    //人脸检测对应性别类型
    public static class EM_DEV_EVENT_FACEDETECT_SEX_TYPE extends Structure
    {
        public static final int EM_DEV_EVENT_FACEDETECT_SEX_TYPE_UNKNOWN = 0; //未知
        public static final int EM_DEV_EVENT_FACEDETECT_SEX_TYPE_MAN = 1; //男性
        public static final int EM_DEV_EVENT_FACEDETECT_SEX_TYPE_WOMAN = 2; //女性
    }

    //人脸检测对应人脸特征类型
    public static class EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE extends Structure
    {
        public static final int EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_UNKNOWN = 0; //未知
        public static final int EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_WEAR_GLASSES = 1; //戴眼镜
        public static final int EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_SMILE = 2; //微笑
        public static final int EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_ANGER = 3; //愤怒
        public static final int EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_SADNESS = 4; //悲伤
        public static final int EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_DISGUST = 5; //厌恶
        public static final int EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_FEAR = 6; //害怕
        public static final int EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_SURPRISE = 7; //惊讶
        public static final int EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_NEUTRAL = 8; //正常
        public static final int EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE_LAUGH = 9; //大笑
    }

    // 事件文件的文件标签类型
    public static class EM_EVENT_FILETAG extends Structure
    {
        public static final int NET_ATMBEFOREPASTE = 1; //ATM贴条前
        public static final int NET_ATMAFTERPASTE = 2;  //ATM贴条后
    }

    // 事件对应文件信息
    public static class NET_EVENT_FILE_INFO extends Structure
    {
        public byte bCount;//当前文件所在文件组中的文件总数
        public byte bIndex;//当前文件在文件组中的文件编号(编号1开始)
        public byte bFileTag;//文件标签,具体说明见枚举类型 EM_EVENT_FILETAG
        public byte bFileType;//文件类型,0-普通1-合成2-抠图
        public NET_TIME_EX stuFileTime;//文件时间
        public int nGroupId;//同一组抓拍文件的唯一标识
    }

    // 多人脸检测信息
    public static class NET_FACE_INFO extends Structure
    {
        public int nObjectID;//物体ID,每个ID表示一个唯一的物体
        public byte[] szObjectType = new byte[128];//物体类型
        public int nRelativeID;//Relative与另一张图片ID相同,表示这张人脸抠图是从大图中取出的
        public NET_RECT BoundingBox;//包围盒
        public NET_POINT Center;//物体型心
    }

    //事件类型EVENT_IVS_FACEDETECT(人脸检测事件)对应的数据块描述信息
    public static class DEV_EVENT_FACEDETECT_INFO extends Structure
    {
        public int nChannelID;//通道号
        public byte[] szName = new byte[128];//事件名称
        public byte[] bReserved1 = new byte[4];//字节对齐
        public double PTS;//时间戳(单位是毫秒)
        public NET_TIME_EX UTC;//事件发生的时间
        public int nEventID;//事件ID
        public NET_MSG_OBJECT stuObject;//检测到的物体
        public NET_EVENT_FILE_INFO stuFileInfo;//事件对应文件信息
        public byte bEventAction;//事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[] reserved = new byte[2];//保留字节
        public byte byImageIndex;//图片的序号,同一时间内(精确到秒)可能有多张图片,从0开始
        public int nDetectRegionNum;//规则检测区域顶点数
        public NET_POINT[] DetectRegion = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM);//规则检测区域
        public int dwSnapFlagMask;//抓图标志(按位),具体见NET_RESERVED_COMMON
        public byte[] szSnapDevAddress = new byte[MAX_PATH];//抓拍当前人脸的设备地址,如：滨康路37号
        public int nOccurrenceCount;//事件触发累计次数, 类型为unsigned int
        public int emSex;//性别, 取值为EM_DEV_EVENT_FACEDETECT_SEX_TYPE中的值
        public int nAge;//年龄,-1表示该字段数据无效
        public int nFeatureValidNum;//人脸特征数组有效个数,与emFeature结合使用, 类型为unsigned int
        public int[] emFeature = new int[NET_MAX_FACEDETECT_FEATURE_NUM];//人脸特征数组,与nFeatureValidNum, 取值为EM_DEV_EVENT_FACEDETECT_FEATURE_TYPE中的值
        public int nFacesNum;//指示stuFaces有效数量
        public NET_FACE_INFO[] stuFaces = (NET_FACE_INFO[])new NET_FACE_INFO().toArray(10);//多张人脸时使用,此时没有Object
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;//智能事件公共信息
        public byte[] bReserved = new byte[1020];//保留字节,留待扩展
    }
    
    // 车辆行驶方向
    public static class NET_FLOWSTAT_DIRECTION extends Structure
    {
        public static final int DRIVING_DIR_UNKNOW   = 0 ;     //兼容之前
        public static final int DRIVING_DIR_APPROACH = 1 ;     //上行,即车辆离设备部署点越来越近
        public static final int DRIVING_DIR_LEAVE    = 2 ;     //下行,即车辆离设备部署点越来越远
    }
    
    //车辆流量统计车辆行驶方向信息 
    public static class NET_TRAFFIC_FLOWSTAT_INFO_DIR extends Structure
    {
        public int                         emDrivingDir;      //行驶方向 (参见NET_FLOWSTAT_DIRECTION)
        public byte[]                      szUpGoing = new byte[FLOWSTAT_ADDR_NAME];      //上行地点 
        public byte[]                      szDownGoing = new byte[FLOWSTAT_ADDR_NAME];    //下行地点 
        public byte[]                      reserved= new byte[32];                       //保留字节
        
    }
    
    public static class NET_TRAFFIC_JAM_STATUS extends Structure
    {
         public static final int JAM_STATUS_UNKNOW = 0; //未知
         public static final int JAM_STATUS_CLEAR  = 1; //通畅
         public static final int JAM_STATUS_JAMMED = 2; //拥堵
    }


    public static class  NET_TRAFFIC_FLOW_STATE  extends Structure
    {
        public int                             nLane;                          // 车道号
        public int                             dwState;                        // 状态值
                                                                               // 1- 流量过大
                                                                               // 2- 流量过大恢复
                                                                               // 3- 正常
                                                                               // 4- 流量过小
                                                                               // 5- 流量过小恢复
        public int                             dwFlow;                         // 流量值, 单位: 辆
        public int                             dwPeriod;                       // 流量值对应的统计时间
        public NET_TRAFFIC_FLOWSTAT_INFO_DIR   stTrafficFlowDir = new NET_TRAFFIC_FLOWSTAT_INFO_DIR();               // 车道方向信息
        public int                             nVehicles;                      // 通过车辆总数
        public float                           fAverageSpeed;                  // 平均车速,单位km/h
        public float                           fAverageLength;                 // 平均车长,单位米
        public float                           fTimeOccupyRatio;               // 时间占有率,即单位时间内通过断面的车辆所用时间的总和占单位时间的比例
        public float                           fSpaceOccupyRatio;              // 空间占有率,即按百分率计量的车辆长度总和除以时间间隔内车辆平均行驶距离
        public float                           fSpaceHeadway;                  // 车头间距,相邻车辆之间的距离,单位米/辆
        public float                           fTimeHeadway;                   // 车头时距,单位秒/辆
        public float                           fDensity;                       // 车辆密度,每公里的车辆数,单位辆/km
        public int                             nOverSpeedVehicles;             // 超速车辆数
        public int                             nUnderSpeedVehicles;            // 低速车辆数
        public int                             nLargeVehicles;                 // 大车交通量(9米<车长<12米),辆/单位时间
        public int                             nMediumVehicles;                // 中型车交通量(6米<车长<9米),辆/单位时间
        public int                             nSmallVehicles;                 // 小车交通量(4米<车长<6米),辆/单位时间,
        public int                             nMotoVehicles;                  // 摩托交通量(微型车,车长<4米),辆/单位时间,
        public int                             nLongVehicles;                  // 超长交通量(车长>=12米),辆/单位时间,
        public int                             nVolume;                        // 交通量, 辆/单位时间, 某时间间隔通过车道、道路或其他通道上一点的车辆数,常以1小时计, 
        public int                             nFlowRate;                      // 流率小车当量,辆/小时, 车辆通过车道、道路某一断面或某一路段的当量小时流量
        public int                             nBackOfQueue;                   // 排队长度,单位：米, 从信号交叉口停车线到上游排队车辆末端之间的距离
        public int                             nTravelTime;                    // 旅行时间,单位：秒, 车辆通过某一条道路所用时间。包括所有停车延误
        public int                             nDelay;                         // 延误,单位：秒,驾驶员、乘客或行人花费的额外的行程时间
        public byte[]                          byDirection = new byte[MAX_DRIVING_DIR_NUM]; // 车道方向,详见NET_ROAD_DIRECTION
        public byte                            byDirectionNum;                 // 车道行驶方向个数
        public byte[]                          reserved1 = new byte[3];        // 字节对齐
        public int          emJamState;                                        // 道路拥挤状况 (参见NET_TRAFFIC_JAM_STATUS)
        //  按车辆类型统计交通量
        public int                             nPassengerCarVehicles;                      // 客车交通量(辆/单位时间)
        public int                             nLargeTruckVehicles;                        // 大货车交通量(辆/单位时间)
        public int                             nMidTruckVehicles;                          // 中货车交通量(辆/单位时间)
        public int                             nSaloonCarVehicles;                         // 轿车交通量(辆/单位时间)    
        public int                             nMicrobusVehicles;                          // 面包车交通量(辆/单位时间)
        public int                             nMicroTruckVehicles;                        // 小货车交通量(辆/单位时间)
        public int                             nTricycleVehicles;                          // 三轮车交通量(辆/单位时间)
        public int                             nMotorcycleVehicles;                        // 摩托车交通量(辆/单位时间)
        public int                             nPasserbyVehicles;                          // 行人交通量(辆/单位时间)
        public byte[]                          reserved = new byte[816];                   // 保留字节
    }

    //事件类型 EVENT_IVS_TRAFFIC_FLOWSTATE(交通流量事件)对应数据块描述信息
    public static class DEV_EVENT_TRAFFIC_FLOW_STATE extends Structure
    {
        public int                       nChannelID;                             // 通道号
        public byte[]                    szName = new byte[NET_EVENT_NAME_LEN];   // 事件名称
        public byte[]                    bReserved1 = new byte[8];                          // 字节对齐
        public int                       PTS;                                    // 时间戳(单位是毫秒)
        public NET_TIME_EX               UTC;                                    // 事件发生的时间
        public int                       nEventID;                               // 事件ID
        public int                       nSequence;                              // 序号
        public int                       nStateNum;                              // 流量状态数量
        public NET_TRAFFIC_FLOW_STATE[]  stuStates = (NET_TRAFFIC_FLOW_STATE[])new NET_TRAFFIC_FLOW_STATE().toArray(NET_MAX_LANE_NUM);             // 流量状态, 每个车道对应数组中一个元素
        public byte[]                    bReserved = new byte[1024];               // 保留字节
    }
    
    // 图片分辨率
    public static class NET_RESOLUTION_INFO extends Structure
    {
        public short snWidth;//宽
        public short snHight;//高
    }

    public static class EM_COMMON_SEAT_TYPE extends Structure
    {
        public static final int COMMON_SEAT_TYPE_UNKNOWN = 0;//未识别
        public static final int COMMON_SEAT_TYPE_MAIN = 1;//主驾驶
        public static final int COMMON_SEAT_TYPE_SLAVE = 2;//副驾驶
    }

    // 违规状态
    public static class EVENT_COMM_STATUS extends Structure
    {
        public byte bySmoking;//是否抽烟
        public byte byCalling;//是否打电话
        public byte[] szReserved = new byte[14];//预留字段
    }

    public static class NET_SAFEBELT_STATE extends Structure
    {
        public static final int SS_NUKNOW = 0;//未知
        public static final int SS_WITH_SAFE_BELT = 1;//已系安全带
        public static final int SS_WITHOUT_SAFE_BELT = 2;//未系安全带
    }

    //遮阳板状态
    public static class NET_SUNSHADE_STATE extends Structure
    {
        public static final int SS_NUKNOW_SUN_SHADE = 0;//未知
        public static final int SS_WITH_SUN_SHADE = 1;//有遮阳板
        public static final int SS_WITHOUT_SUN_SHADE = 2;//无遮阳板
    }

    // 驾驶位违规信息
    public static class EVENT_COMM_SEAT extends Structure
    {
        public int bEnable;//是否检测到座驾信息, 类型BOOL, 取值0或者1
        public int emSeatType;//座驾类型,0:未识别;1:主驾驶; 取值为EM_COMMON_SEAT_TYPE中的值
        public EVENT_COMM_STATUS stStatus;//违规状态
        public int emSafeBeltStatus;//安全带状态, 取值为NET_SAFEBELT_STATE中的值
        public int emSunShadeStatus;//遮阳板状态, 取值为NET_SUNSHADE_STATE中的值
        public byte[] szReserved = new byte[24];//预留字节
    }

    public static class EM_COMM_ATTACHMENT_TYPE extends Structure
    {       
        public static final int COMM_ATTACHMENT_TYPE_UNKNOWN = 0;// 未知类型
        public static final int COMM_ATTACHMENT_TYPE_FURNITURE = 1;// 摆件   
        public static final int COMM_ATTACHMENT_TYPE_PENDANT = 2;// 挂件    
        public static final int COMM_ATTACHMENT_TYPE_TISSUEBOX = 3;// 纸巾盒
        public static final int COMM_ATTACHMENT_TYPE_DANGER = 4;// 危险品
    }

    // 车辆物件
    public static class EVENT_COMM_ATTACHMENT extends Structure
    {       
        public int emAttachmentType;//物件类型, 取值为EM_COMM_ATTACHMENT_TYPE中的值
        public NET_RECT stuRect;//坐标
        public byte[] bReserved = new byte[20];//预留字节
    }
    
    //NTP校时状态
    public static class EM_NTP_STATUS extends Structure
    {
        public static final int NET_NTP_STATUS_UNKNOWN = 0;
        public static final int NET_NTP_STATUS_DISABLE = 1;
        public static final int NET_NTP_STATUS_SUCCESSFUL = 2;
        public static final int NET_NTP_STATUS_FAILED = 3;
    }

    public static class EVENT_COMM_INFO extends Structure
    {
        public int emNTPStatus;//NTP校时状态, 取值为EM_NTP_STATUS中的值
        public int nDriversNum;//驾驶员信息数
        public Pointer pstDriversInfo;//驾驶员信息数据，类型为NET_MSG_OBJECT_EX*
        public Pointer pszFilePath;//本地硬盘或者sd卡成功写入路径,为NULL时,路径不存在， 类型为char *
        public Pointer pszFTPPath;//设备成功写到ftp服务器的路径， 类型为char *
        public Pointer pszVideoPath;//当前接入需要获取当前违章的关联视频的FTP上传路径， 类型为char *
        public EVENT_COMM_SEAT[] stCommSeat = (EVENT_COMM_SEAT[])new EVENT_COMM_SEAT().toArray(COMMON_SEAT_MAX_NUMBER);//驾驶位信息
        public int nAttachmentNum;//车辆物件个数
        public EVENT_COMM_ATTACHMENT[] stuAttachment = (EVENT_COMM_ATTACHMENT[])new EVENT_COMM_ATTACHMENT().toArray(NET_MAX_ATTACHMENT_NUM);//车辆物件信息
        public int nAnnualInspectionNum;//年检标志个数
        public NET_RECT[] stuAnnualInspection = (NET_RECT[])new NET_RECT().toArray(NET_MAX_ANNUUALINSPECTION_NUM);//年检标志
        public byte[] bReserved = new byte[1100];//预留字节
        public byte[] szCountry = new byte[20];//国家
    }
    
    // 车检器冗余信息
    public static class NET_SIG_CARWAY_INFO_EX extends Structure
    {
        public byte[] byRedundance = new byte[8];//由车检器产生抓拍信号冗余信息
        public byte[] bReserved = new byte[120];//保留字段
    }
    
    // 颜色RGBA
    public static class NET_COLOR_RGBA extends Structure
    {
        public int nRed;//红
        public int nGreen;//绿
        public int nBlue;//蓝
        public int nAlpha;//透明
    }

    // TrafficCar 交通车辆信息
    public static class DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO extends Structure
    {
        public byte[] szPlateNumber = new byte[32];//车牌号码
        public byte[] szPlateType = new byte[32];//号牌类型参见VideoAnalyseRule中车牌类型定义
        public byte[] szPlateColor = new byte[32];//车牌颜色"Blue","Yellow",
        public byte[] szVehicleColor = new byte[32];//车身颜色"White",
        public int nSpeed;//速度单位Km/H
        public byte[] szEvent = new byte[64];//触发的相关事件参见事件列表Event
        public byte[] szViolationCode = new byte[32];//违章代码详见TrafficGlobal.ViolationCode
        public byte[] szViolationDesc = new byte[64];//违章描述
        public int nLowerSpeedLimit;//速度下限
        public int nUpperSpeedLimit;//速度上限
        public int nOverSpeedMargin;//限高速宽限值单位：km/h
        public int nUnderSpeedMargin;//限低速宽限值单位：km/h
        public int nLane;//车道参见事件列表EventList中卡口和路口事件。
        public int nVehicleSize;//车辆大小,-1表示未知,否则按位
        // 第0位:"Light-duty", 小型车
        // 第1位:"Medium", 中型车
        // 第2位:"Oversize", 大型车
        // 第3位:"Minisize", 微型车
        // 第4位:"Largesize", 长车
        public float fVehicleLength;//车辆长度单位米
        public int nSnapshotMode;//抓拍方式0-未分类,1-全景,2-近景,4-同向抓拍,8-反向抓拍,16-号牌图像
        public byte[] szChannelName = new byte[32];//本地或远程的通道名称,可以是地点信息来源于通道标题配置ChannelTitle.Name
        public byte[] szMachineName = new byte[256];//本地或远程设备名称来源于普通配置General.MachineName
        public byte[] szMachineGroup = new byte[256];//机器分组或叫设备所属单位默认为空,用户可以将不同的设备编为一组,便于管理,可重复。
        public byte[] szRoadwayNo = new byte[64];//道路编号
        public byte[] szDrivingDirection = new byte[3*NET_MAX_DRIVINGDIRECTION];//
                                                                                // 行驶方向 , "DrivingDirection" : ["Approach", "上海", "杭州"],
                                                                                // "Approach"-上行,即车辆离设备部署点越来越近；"Leave"-下行,
                                                                                // 即车辆离设备部署点越来越远,第二和第三个参数分别代表上行和
                                                                                // 下行的两个地点
        public Pointer szDeviceAddress;//设备地址,OSD叠加到图片上的,来源于配置TrafficSnapshot.DeviceAddress,'\0'结束
        public byte[] szVehicleSign = new byte[32];//车辆标识,例如
        public NET_SIG_CARWAY_INFO_EX stuSigInfo;//由车检器产生抓拍信号冗余信息
        public Pointer szMachineAddr;//设备部署地点
        public float fActualShutter;//当前图片曝光时间,单位为毫秒
        public byte byActualGain;//当前图片增益,范围为0~100
        public byte byDirection;//车道方向,0-南向北1-西南向东北2-西向东
        public byte[] byReserved = new byte[2];
        public Pointer szDetailedAddress;//详细地址,作为szDeviceAddress的补充
        public byte[] szDefendCode = new byte[NET_COMMON_STRING_64];//图片防伪码
        public int nTrafficBlackListID;//关联黑名单数据库记录默认主键ID,0,无效；>0,黑名单数据记录
        public NET_COLOR_RGBA stuRGBA;//车身颜色RGBA
        public NET_TIME stSnapTime;//抓拍时间
        public int nRecNo;//记录编号
        public byte[] szCustomParkNo= new byte[NET_COMMON_STRING_32+1];// 自定义车位号（停车场用）
        public byte[] byReserved1 = new byte[3];
        public int nDeckNo;//车板位号
        public int nFreeDeckCount;//空闲车板数量
        public int nFullDeckCount;//占用车板数量
        public int nTotalDeckCount;//总共车板数量
        public byte[] szViolationName = new byte[64];//违章名称
        public int nWeight;//车重(单位Kg), 类型为unsigned int
        public byte[] bReserved = new byte[616];//保留字节,留待扩展.
    }

    // 事件类型EVENT_IVS_TRAFFIC_PARKING(交通违章停车事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_PARKING_INFO extends Structure
    {
        public int nChannelID;//通道号
        public byte[] szName = new byte[128];//事件名称
        public byte[] bReserved1 = new byte[4];//字节对齐
        public double PTS;//时间戳(单位是毫秒)
        public NET_TIME_EX UTC;//事件发生的时间
        public int nEventID;//事件ID
        public NET_MSG_OBJECT stuObject;//检测到的物体
        public NET_MSG_OBJECT stuVehicle;//车身信息
        public int nLane;//对应车道号
        public NET_EVENT_FILE_INFO stuFileInfo;//事件对应文件信息
        public byte bEventAction;//事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[] reserved = new byte[2];//保留字节
        public byte byImageIndex;//图片的序号,同一时间内(精确到秒)可能有多张图片,从0开始
        public NET_TIME_EX stuStartParkingTime;//开始停车时间
        public int nSequence;//表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束(bEventAction=2时此参数有效)
        public int nAlarmIntervalTime;//报警时间间隔,单位:秒。(此事件为连续性事件,在收到第一个此事件之后,若在超过间隔时间后未收到此事件的后续事件,则认为此事件异常结束了)
        public int nParkingAllowedTime;//允许停车时长,单位：秒。
        public int nDetectRegionNum;//规则检测区域顶点数
        public NET_POINT[] DetectRegion = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM);//规则检测区域
        public int dwSnapFlagMask;//抓图标志(按位),具体见NET_RESERVED_COMMON
        public NET_RESOLUTION_INFO stuResolution;//对应图片的分辨率
        public int bIsExistAlarmRecord;//true:有对应的报警录像;false:无对应的报警录像, 类型为BOOL, 取值为0或1
        public int dwAlarmRecordSize;//录像大小
        public byte[] szAlarmRecordPath = new byte[NET_COMMON_STRING_256];//录像路径
        public byte[] szFTPPath = new byte[NET_COMMON_STRING_256];//FTP路径
        public EVENT_INTELLI_COMM_INFO stuIntelliCommInfo;//智能事件公共信息
        public byte[] bReserved = new byte[400];//保留字节,留待扩展.
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;//交通车辆信息
        public EVENT_COMM_INFO stCommInfo;//公共信息
    }
    
    //停车场信息
    public static class DEV_TRAFFIC_PARKING_INFO extends Structure
    {
        public int           nFeaturePicAreaPointNum;                                                        // 特征图片区点个数
        public NET_POINT[]   stFeaturePicArea = (NET_POINT[])new NET_POINT().toArray(NET_MAX_POLYGON_NUM);   // 特征图片区信息
        public byte[]        bReserved = new byte[572];                                                      // 保留字节
    }
    
    //事件类型 EVENT_IVS_TRAFFIC_PARKINGSPACEPARKING(车位有车事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_PARKINGSPACEPARKING_INFO extends Structure
    {
        public int                 nChannelID;                                 // 通道号
        public byte[]              szName = new byte[NET_EVENT_NAME_LEN];      // 事件名称
        public byte[]              bReserved1 = new byte[8];                   // 字节对齐
        public int                 PTS;                                        // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                                        // 事件发生的时间
        public int                 nEventID;                                   // 事件ID
        public int                 nLane;                                      // 对应车道号
        public NET_MSG_OBJECT      stuObject;                                  // 检测到的物体
        public NET_MSG_OBJECT      stuVehicle;                                 // 车身信息
        public NET_EVENT_FILE_INFO stuFileInfo;                                // 事件对应文件信息
        
        public int                 nSequence;                                  // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束    
        public byte                bEventAction;                               // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]              byReserved = new byte[2];
        public byte                byImageIndex;                               // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int                 dwSnapFlagMask;                             // 抓图标志(按位),具体见NET_RESERVED_COMMON    
        public NET_RESOLUTION_INFO  stuResolution;                             // 对应图片的分辨率
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;                 // 交通车辆信息
        public int                 nParkingSpaceStatus;                        // 车位状态,0-占用,1-空闲,2-压线
        public DEV_TRAFFIC_PARKING_INFO stTrafficParingInfo;                   // 停车场信息
        public byte[]              bReserved = new byte[380];                  // 保留字节 
        public EVENT_COMM_INFO     stCommInfo;                                 // 公共信息

    }

    // 事件类型 EVENT_IVS_TRAFFIC_PARKINGSPACENOPARKING(车位无车事件)对应的数据块描述信息
    // 由于历史原因,如果要处理卡口事件,DEV_EVENT_TRAFFICJUNCTION_INFO和EVENT_IVS_TRAFFICGATE要一起处理,以防止有视频电警和线圈电警同时接入平台的情况发生
    // 另外EVENT_IVS_TRAFFIC_TOLLGATE只支持新卡口事件的配置
    public static class DEV_EVENT_TRAFFIC_PARKINGSPACENOPARKING_INFO extends Structure
    {
        public int                 nChannelID;                                 // 通道号
        public byte[]              szName = new byte[NET_EVENT_NAME_LEN];      // 事件名称
        public byte[]              bReserved1 = new byte[8];                   // 字节对齐
        public int                 PTS;                                        // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                                        // 事件发生的时间
        public int                 nEventID;                                   // 事件ID
        public int                 nLane;                                      // 对应车道号
        public NET_MSG_OBJECT       stuObject;                                 // 检测到的物体
        public NET_MSG_OBJECT       stuVehicle;                                // 车身信息
        public NET_EVENT_FILE_INFO  stuFileInfo;                               // 事件对应文件信息
        
        public int                 nSequence;                                  // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public byte                bEventAction;                               // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]              byReserved = new byte[2];
        public byte                byImageIndex;                               // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int                 dwSnapFlagMask;                             // 抓图标志(按位),具体见 NET_RESERVED_COMMON    
        public NET_RESOLUTION_INFO  stuResolution;                             // 对应图片的分辨率
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;                 // 交通车辆信息
        public DEV_TRAFFIC_PARKING_INFO stTrafficParingInfo;                   // 停车场信息
        public byte[]              bReserved = new byte[384];                  // 保留字节
        public EVENT_COMM_INFO     stCommInfo;                                 // 公共信息
    }
    
    // 事件上报携带卡片信息
    public static class EVENT_CARD_INFO extends Structure
    {
        public byte[]              szCardNumber = new byte[NET_EVENT_CARD_LEN];// 卡片序号字符串
        public byte[]              bReserved = new byte[32];                   // 保留字节,留待扩展.
    }
    
    // 车辆方向信息
    public static class EM_VEHICLE_DIRECTION extends Structure
    {
        public static final int    NET_VEHICLE_DIRECTION_UNKOWN = 0;           // 未知
        public static final int    NET_VEHICLE_DIRECTION_HEAD   = 1;           // 车头    
        public static final int    NET_VEHICLE_DIRECTION_TAIL   = 2;           // 车尾  
    }
    
    // 开闸状态
    public static class EM_OPEN_STROBE_STATE extends Structure
    {
        public static final int    NET_OPEN_STROBE_STATE_UNKOWN = 0;           // 未知状态
        public static final int    NET_OPEN_STROBE_STATE_CLOSE  = 1;           // 关闸
        public static final int    NET_OPEN_STROBE_STATE_AUTO   = 2;           // 自动开闸    
        public static final int    NET_OPEN_STROBE_STATE_MANUAL = 3;           // 手动开闸
    }
    
    // 事件类型 EVENT_IVS_TRAFFICJUNCTION 交通路口老规则事件/视频电警上的交通卡口老规则事件对应的数据块描述信息
    // 由于历史原因,如果要处理卡口事件,DEV_EVENT_TRAFFICJUNCTION_INFO和EVENT_IVS_TRAFFICGATE要一起处理
    // 以防止有视频电警和线圈电警同时接入平台的情况发生, 另外EVENT_IVS_TRAFFIC_TOLLGATE只支持新卡口事件的配置
    public static class DEV_EVENT_TRAFFICJUNCTION_INFO extends Structure
    {
        public int                 nChannelID;                                 // 通道号
        public byte[]              szName = new byte[NET_EVENT_NAME_LEN];      // 事件名称
        public byte                byMainSeatBelt;                             // 主驾驶座,系安全带状态,1-系安全带,2-未系安全带
        public byte                bySlaveSeatBelt;                            // 副驾驶座,系安全带状态,1-系安全带,2-未系安全带
        public byte                byVehicleDirection;                         // 当前被抓拍到的车辆是车头还是车尾,具体请见 EM_VEHICLE_DIRECTION
        public byte                byOpenStrobeState;                          // 开闸状态,具体请见 EM_OPEN_STROBE_STATE 
        public double              PTS;                                        // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                                        // 事件发生的时间
        public int                 nEventID;                                   // 事件ID
        public NET_MSG_OBJECT      stuObject;                                  // 检测到的物体
        public int                 nLane;                                      // 对应车道号
        public int                 dwBreakingRule;                             // 违反规则掩码,第一位:闯红灯; 
                                                                               // 第二位:不按规定车道行驶;
                                                                               // 第三位:逆行; 第四位：违章掉头;
                                                                               // 第五位:交通堵塞; 第六位:交通异常空闲
                                                                               // 第七位:压线行驶; 否则默认为:交通路口事件
        public NET_TIME_EX         RedLightUTC;                                // 红灯开始UTC时间
        public NET_EVENT_FILE_INFO stuFileInfo;                                // 事件对应文件信息
        public int                 nSequence;                                  // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public int                 nSpeed;                                     // 车辆实际速度Km/h                 
        public byte                bEventAction;                               // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte                byDirection;                                // 路口方向,1-表示正向,2-表示反向
        public byte                byLightState;                               // LightState表示红绿灯状态:0 未知,1 绿灯,2 红灯,3 黄灯
        public byte                byReserved;                                 // 保留字节
        public byte                byImageIndex;                               // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public NET_MSG_OBJECT      stuVehicle;                                 // 车身信息
        public int                 dwSnapFlagMask;                             // 抓图标志(按位),具体见 NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"   
        public NET_RESOLUTION_INFO stuResolution;                              // 对应图片的分辨率
        public byte[]              szRecordFile = new byte[NET_COMMON_STRING_128];// 报警对应的原始录像文件信息
        public byte[]              bReserved = new byte[340];                  // 保留字节,留待扩展.
        public int                 nTriggerType;                               // TriggerType:触发类型,0车检器,1雷达,2视频
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;                 // 交通车辆信息
        public int                 dwRetCardNumber;                            // 卡片个数
        public EVENT_CARD_INFO[]   stuCardInfo = (EVENT_CARD_INFO[])new EVENT_CARD_INFO().toArray(NET_EVENT_MAX_CARD_NUM);// 卡片信息   
        public EVENT_COMM_INFO     stCommInfo;                                 // 公共信息
    }    
    
    // 事件类型 EVENT_IVS_TRAFFICGATE(交通卡口老规则事件/线圈电警上的交通卡口老规则事件)对应的数据块描述信息
    // 由于历史原因,如果要处理卡口事件,DEV_EVENT_TRAFFICJUNCTION_INFO和EVENT_IVS_TRAFFICGATE要一起处理,以防止有视频电警和线圈电警同时接入平台的情况发生
    // 另外 EVENT_IVS_TRAFFIC_TOLLGATE 只支持新卡口事件的配置
    public static class DEV_EVENT_TRAFFICGATE_INFO extends Structure
    {
        public int                 nChannelID;                                 // 通道号
        public byte[]              szName = new byte[NET_EVENT_NAME_LEN];      // 事件名称
        public byte                byOpenStrobeState;                          // 开闸状态,具体请见EM_OPEN_STROBE_STATE
        public byte                bReserved1[] = new byte[3];                 // 字节对齐
        public double              PTS;                                        // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                                        // 事件发生的时间
        public int                 nEventID;                                   // 事件ID
        public NET_MSG_OBJECT      stuObject;                                  // 检测到的物体
        public int                 nLane;                                      // 对应车道号
        public int                 nSpeed;                                     // 车辆实际速度Km/h
        public int                 nSpeedUpperLimit;                           // 速度上限 单位：km/h
        public int                 nSpeedLowerLimit;                           // 速度下限 单位：km/h 
        public int                 dwBreakingRule;                             // 违反规则掩码,第一位:逆行; 
                                                                               // 第二位:压线行驶; 第三位:超速行驶; 
                                                                               // 第四位：欠速行驶; 第五位:闯红灯;第六位:穿过路口(卡口事件)
                                                                               // 第七位: 压黄线; 第八位: 有车占道; 第九位: 黄牌占道;否则默认为:交通卡口事件
        public NET_EVENT_FILE_INFO stuFileInfo;                                // 事件对应文件信息
        public NET_MSG_OBJECT      stuVehicle;                                 // 车身信息
        public byte                szManualSnapNo[] = new byte[64];            // 手动抓拍序号
        public int                 nSequence;                                  // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public byte                bEventAction;                               // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束; 
        public byte[]              byReserved = new byte[3];                   // 保留字节
        public byte[]              szSnapFlag = new byte[16];                  // 设备产生的抓拍标识
        public byte                bySnapMode;                                 // 抓拍方式,0-未分类 1-全景 2-近景 4-同向抓拍 8-反向抓拍 16-号牌图像
        public byte                byOverSpeedPercentage;                      // 超速百分比
        public byte                byUnderSpeedingPercentage;                  // 欠速百分比
        public byte                byRedLightMargin;                           // 红灯容许间隔时间,单位：秒
        public byte                byDriveDirection;                           // 行驶方向,0-上行(即车辆离设备部署点越来越近),1-下行(即车辆离设备部署点越来越远)
        public byte[]              szRoadwayNo = new byte[32];                 // 道路编号
        public byte[]              szViolationCode = new byte[16];             // 违章代码
        public byte[]              szViolationDesc = new byte[128];            // 违章描述
        public NET_RESOLUTION_INFO stuResolution;                              // 对应图片的分辨率
        public byte[]              szVehicleType= new byte[32];                // 车辆大小类型 Minisize"微型车,"Light-duty"小型车,"Medium"中型车,
                                                                               // "Oversize"大型车,"Huge"超大车,"Largesize"长车 "Unknown"未知
        public byte                byVehicleLenth;                             // 车辆长度, 单位米
        public byte                byLightState;                               // LightState表示红绿灯状态:0 未知,1 绿灯,2 红灯,3 黄灯
        public byte                byReserved1;                                // 保留字节,留待扩展
        public byte                byImageIndex;                               // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int                 nOverSpeedMargin;                           // 限高速宽限值    单位：km/h 
        public int                 nUnderSpeedMargin;                          // 限低速宽限值    单位：km/h 
        public byte[]              szDrivingDirection = new byte[3*NET_MAX_DRIVINGDIRECTION]; //
                                                                               // "DrivingDirection" : ["Approach", "上海", "杭州"],行驶方向
                                                                               // "Approach"-上行,即车辆离设备部署点越来越近；"Leave"-下行,
                                                                               // 即车辆离设备部署点越来越远,第二和第三个参数分别代表上行和
                                                                               // 下行的两个地点,UTF-8编码
        public byte[]              szMachineName = new byte[256];              // 本地或远程设备名称
        public byte[]              szMachineAddress = new byte[256];           // 机器部署地点、道路编码
        public byte[]              szMachineGroup = new byte[256];             // 机器分组、设备所属单位
        public int                 dwSnapFlagMask;                             // 抓图标志(按位),具体见NET_RESERVED_COMMON    
        public NET_SIG_CARWAY_INFO_EX stuSigInfo;                              // 由车检器产生抓拍信号冗余信息
        public byte[]              szFilePath = new byte[MAX_PATH];            // 文件路径
        public NET_TIME_EX         RedLightUTC;                                // 红灯开始UTC时间
        public Pointer             szDeviceAddress;                            // 设备地址,OSD叠加到图片上的,来源于配置TrafficSnapshot.DeviceAddress,'\0'结束
        public float               fActualShutter;                             // 当前图片曝光时间,单位为毫秒
        public byte                byActualGain;                               // 当前图片增益,范围为0~100
        public byte                byDirection;                                // 0-南向北 1-西南向东北 2-西向东 3-西北向东南 4-北向南 5-东北向西南 6-东向西 7-东南向西北 8-未知
        public byte                bReserve;                                   // 保留字节, 字节对齐
        public byte                bRetCardNumber;                             // 卡片个数
        public EVENT_CARD_INFO[]   stuCardInfo = (EVENT_CARD_INFO[])new EVENT_CARD_INFO().toArray(NET_EVENT_MAX_CARD_NUM);// 卡片信息
        public byte[]              szDefendCode = new byte[NET_COMMON_STRING_64];           // 图片防伪码
        public int                 nTrafficBlackListID;                         // 关联黑名单数据库记录默认主键ID, 0,无效；> 0,黑名单数据记录
        public EVENT_COMM_INFO     stCommInfo;                                 // 公共信息
        public byte[]              bReserved = new byte[452];                  // 保留字节,留待扩展.
    }
    
    //事件类型EVENT_IVS_TRAFFIC_RUNREDLIGHT(交通-闯红灯事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_RUNREDLIGHT_INFO extends Structure
    {
        public int                 nChannelID;                                 // 通道号
        public byte[]              szName = new byte[128];                     // 事件名称
        public byte[]              bReserved1 = new byte[4];                   // 字节对齐
        public double              PTS;                                        // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                                        // 事件发生的时间
        public int                 nEventID;                                   // 事件ID
        public int                 nLane;                                      // 对应车道号
        public NET_MSG_OBJECT      stuObject;                                  // 车牌信息
        public NET_MSG_OBJECT      stuVehicle;                                 // 车身信息
        public NET_EVENT_FILE_INFO stuFileInfo;                                // 事件对应文件信息 
        public int                 nLightState;                                // 红绿灯状态 0:未知 1：绿灯 2:红灯 3:黄灯
        public int                 nSpeed;                                     // 车速,km/h
        public int                 nSequence;                                  // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public byte                bEventAction;                               // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]              byReserved = new byte[2];
        public byte                byImageIndex;                               // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int                 dwSnapFlagMask;                             // 抓图标志(按位),具体见NET_RESERVED_COMMON    
        public NET_TIME_EX         stRedLightUTC;                              // 红灯开始时间
        public NET_RESOLUTION_INFO stuResolution;                              // 对应图片的分辨率
        public byte                byRedLightMargin;                           // 红灯容许间隔时间,单位：秒
        public byte[]              byAlignment = new byte[3];                  // 字节对齐
        public int                 nRedLightPeriod;                            // 表示红灯周期时间,单位毫秒
        public byte[]              bReserved = new byte[968];                  // 保留字节
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;                 // 交通车辆信息
        public EVENT_COMM_INFO     stCommInfo;                                 // 公共信息
    }
    
    //事件类型EVENT_IVS_TRAFFIC_OVERLINE(交通-压线事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_OVERLINE_INFO extends Structure
    {
        public int                 nChannelID;                                 // 通道号
        public byte[]              szName = new byte[128];                     // 事件名称
        public byte[]              bReserved1 = new byte[4];                   // 字节对齐
        public double              PTS;                                        // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                                        // 事件发生的时间
        public int                 nEventID;                                   // 事件ID
        public int                 nLane;                                      // 对应车道号
        public NET_MSG_OBJECT      stuObject;                                  // 车牌信息
        public NET_MSG_OBJECT      stuVehicle;                                 // 车身信息
        public NET_EVENT_FILE_INFO stuFileInfo;                                // 事件对应文件信息
        public int                 nSequence;                                  // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public int                 nSpeed;                                     // 车辆实际速度,Km/h
        public byte                bEventAction;                               // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]              byReserved = new byte[2];
        public byte                byImageIndex;                               // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int                 dwSnapFlagMask;                             // 抓图标志(按位),具体见NET_RESERVED_COMMON    
        public NET_RESOLUTION_INFO stuResolution;                              // 对应图片的分辨率
        public byte[]              bReserved = new byte[1008];                 // 保留字节
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;                 // 交通车辆信息
        public EVENT_COMM_INFO     stCommInfo;                                 // 公共信息
    } 
    
    //事件类型EVENT_IVS_TRAFFIC_OVERSPEED(交通超速事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_OVERSPEED_INFO extends Structure
    {
        public int                 nChannelID;                                 // 通道号
        public byte[]              szName = new byte[128];                     // 事件名称
        public byte[]              bReserved1 = new byte[4];                   // 字节对齐
        public double              PTS;                                        // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                                        // 事件发生的时间
        public int                 nEventID;                                   // 事件ID
        public int                 nLane;                                      // 对应车道号
        public NET_MSG_OBJECT      stuObject;                                  // 检测到的物体
        public NET_MSG_OBJECT      stuVehicle;                                 // 车身信息
        public NET_EVENT_FILE_INFO stuFileInfo;                                // 事件对应文件信息
        public int                 nSpeed;                                     // 车辆实际速度Km/h
        public int                 nSpeedUpperLimit;                           // 速度上限 单位：km/h
        public int                 nSpeedLowerLimit;                           // 速度下限 单位：km/h 
        public int                 nSequence;                                  // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public byte                bEventAction;                               // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;    
        public byte[]              byReserved = new byte[2];
        public byte                byImageIndex;                               // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int                 dwSnapFlagMask;                             // 抓图标志(按位),具体见NET_RESERVED_COMMON    
        public NET_RESOLUTION_INFO stuResolution;                              // 对应图片的分辨率
        public byte[]              szFilePath = new byte[MAX_PATH];            // 文件路径
        public EVENT_INTELLI_COMM_INFO     stuIntelliCommInfo;                 // 智能事件公共信息
        public byte[]                bReserved = new byte[744];                // 保留字节
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;                 // 交通车辆信息
        public EVENT_COMM_INFO     stCommInfo;                                 // 公共信息
    }
    
    //事件类型EVENT_IVS_TRAFFIC_WRONGROUTE(交通违章-不按车道行驶)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_WRONGROUTE_INFO extends Structure
    {
        public int                 nChannelID;                                 // 通道号
        public byte[]              szName = new byte[128];                     // 事件名称
        public byte[]              bReserved1 = new byte[4];                   // 字节对齐
        public double              PTS;                                        // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                                        // 事件发生的时间
        public int                 nEventID;                                   // 事件ID
        public NET_MSG_OBJECT      stuObject;                                  // 检测到的物体
        public NET_MSG_OBJECT      stuVehicle;                                 // 车身信息
        public int                 nLane;                                      // 对应车道号
        public NET_EVENT_FILE_INFO stuFileInfo;                                // 事件对应文件信息               
        public byte                bEventAction;                               // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]              byReserved = new byte[2];
        public byte                byImageIndex;                               // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int                 nSpeed;                                     // 车辆实际速度,km/h
        public int                 dwSnapFlagMask;                             // 抓图标志(按位),具体见NET_RESERVED_COMMON    
        public NET_RESOLUTION_INFO stuResolution;                              // 对应图片的分辨率
        public byte[]              bReserved = new byte[1012];                 // 保留字节,留待扩展.
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;                 // 交通车辆信息
        public EVENT_COMM_INFO     stCommInfo;                                 // 公共信息
    }
    
    //事件类型EVENT_IVS_TRAFFIC_OVERYELLOWLINE(交通违章-压黄线)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_OVERYELLOWLINE_INFO extends Structure
    {
        public int                 nChannelID;                                 // 通道号
        public byte[]              szName = new byte[128];                     // 事件名称
        public byte[]              bReserved1 = new byte[4];                   // 字节对齐
        public double              PTS;                                        // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                                        // 事件发生的时间
        public int                 nEventID;                                   // 事件ID
        public NET_MSG_OBJECT      stuObject;                                  // 检测到的物体
        public NET_MSG_OBJECT      stuVehicle;                                 // 车身信息
        public int                 nLane;                                      // 对应车道号
        public NET_EVENT_FILE_INFO stuFileInfo;                                // 事件对应文件信息 
        public byte                bEventAction;                               // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]              byReserved = new byte[2];
        public byte                byImageIndex;                               // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int                 nSpeed;                                     // 车辆实际速度,km/h
        public int                 dwSnapFlagMask;                             // 抓图标志(按位),具体见NET_RESERVED_COMMON    
        public NET_RESOLUTION_INFO stuResolution;                              // 对应图片的分辨率
        public int                 bIsExistAlarmRecord;                        // bool 类型： 1:有对应的报警录像; 0:无对应的报警录像
        public int                 dwAlarmRecordSize;                          // 录像大小
        public byte[]              szAlarmRecordPath = new byte[NET_COMMON_STRING_256]; // 录像路径
        public EVENT_INTELLI_COMM_INFO     stuIntelliCommInfo;                 // 智能事件公共信息
        public byte[]              bReserved = new byte[660];                  // 保留字节,留待扩展.
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;                 // 交通车辆信息

        public int                 nDetectNum;                                 // 规则检测区域顶点数
        public NET_POINT[]         DetectRegion = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM); // 规则检测区域    
        public EVENT_COMM_INFO     stCommInfo;                                 // 公共信息
    }
    
    //事件类型EVENT_IVS_TRAFFIC_YELLOWPLATEINLANE(交通违章-黄牌车占道事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_YELLOWPLATEINLANE_INFO extends Structure
    {
        public int                 nChannelID;                                 // 通道号
        public byte[]              szName = new byte[128];                     // 事件名称
        public byte[]              bReserved1 = new byte[4];                   // 字节对齐
        public double              PTS;                                        // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                                        // 事件发生的时间
        public int                 nEventID;                                   // 事件ID
        public NET_MSG_OBJECT      stuObject;                                  // 检测到的物体
        public NET_MSG_OBJECT      stuVehicle;                                 // 车身信息
        public int                 nLane;                                      // 对应车道号
        public NET_EVENT_FILE_INFO stuFileInfo;                                // 事件对应文件信息               
        public byte                bEventAction;                               // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]              byReserved = new byte[2];     
        public byte                byImageIndex;                               // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int                 nSpeed;                                     // 车辆实际速度,km/h
        public int                 dwSnapFlagMask;                             // 抓图标志(按位),具体见NET_RESERVED_COMMON    
        public NET_RESOLUTION_INFO stuResolution;                              // 对应图片的分辨率
        public byte[]              bReserved = new byte[1016];                 // 保留字节,留待扩展.
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;                 // 交通车辆信息
        public EVENT_COMM_INFO     stCommInfo;                                 // 公共信息
    }

    //事件类型 EVENT_IVS_TRAFFIC_VEHICLEINROUTE(有车占道事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_VEHICLEINROUTE_INFO extends Structure
    {
        public int                 nChannelID;                                 // 通道号
        public byte[]              szName = new byte[128];                     // 事件名称
        public byte[]              bReserved1 = new byte[4];                   // 字节对齐
        public double              PTS;                                        // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                                        // 事件发生的时间
        public int                 nEventID;                                   // 事件ID
        public NET_MSG_OBJECT      stuObject;                                  // 检测到的物体
        public NET_MSG_OBJECT      stuVehicle;                                 // 车身信息
        public int                 nLane;                                      // 对应车道号
        public int                 nSequence;                                  // 抓拍序号,如3-2-1/0,1表示抓拍正常结束,0表示抓拍异常结束
        public int                 nSpeed;                                     // 车速
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;                 // 表示交通车辆的数据库记录
        public NET_EVENT_FILE_INFO  stuFileInfo;                               // 事件对应文件信息               
        public byte                bEventAction;                               // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]              byReserved0 = new byte[2];
        public byte                byImageIndex;                               // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int                 dwSnapFlagMask;                             // 抓图标志(按位),具体见NET_RESERVED_COMMON    
        public NET_RESOLUTION_INFO  stuResolution;                             // 对应图片的分辨率
        public EVENT_INTELLI_COMM_INFO     stuIntelliCommInfo;                 // 智能事件公共信息
        public byte[]              byReserved = new byte[1012];           
        public EVENT_COMM_INFO     stCommInfo;                                 // 公共信息
    }
    
    //事件类型EVENT_IVS_TRAFFIC_MANUALSNAP(交通手动抓拍事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_MANUALSNAP_INFO extends Structure
    {
        public int                 nChannelID;                                 // 通道号
        public byte[]              szName = new byte[128];                     // 事件名称
        public byte[]              bReserved1 = new byte[4];                   // 字节对齐
        public double              PTS;                                        // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                                        // 事件发生的时间
        public int                 nEventID;                                   // 事件ID
        public int                 nLane;                                      // 对应车道号
        public byte[]              szManualSnapNo = new byte[64];              // 手动抓拍序号 
        public NET_MSG_OBJECT      stuObject;                                  // 检测到的物体
        public NET_MSG_OBJECT      stuVehicle;                                 // 检测到的车身信息
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;                 // 表示交通车辆的数据库记录
        public NET_EVENT_FILE_INFO stuFileInfo;                                // 事件对应文件信息
        public byte                bEventAction;                               // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]              byReserved = new byte[2];
        public byte                byImageIndex;                               // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int                 dwSnapFlagMask;                             // 抓图标志(按位),具体见NET_RESERVED_COMMON    
        public NET_RESOLUTION_INFO stuResolution;                              // 对应图片的分辨率
        public byte[]              bReserved = new byte[1016];                 // 保留字节,留待扩展.
        public EVENT_COMM_INFO     stCommInfo;                                 // 公共信息
    }
    
    // 事件类型 EVENT_IVS_CROSSLINEDETECTION(警戒线事件)对应的数据块描述信息
    public static class DEV_EVENT_CROSSLINE_INFO extends Structure {
        public int                 nChannelID;                         // 通道号
        public byte[]              szName = new byte[128];             // 事件名称
        public byte[]              bReserved1 = new byte[4];           // 字节对齐
        public double              PTS;                                // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                                // 事件发生的时间
        public int                 nEventID;                           // 事件ID
        public NET_MSG_OBJECT      stuObject;                          // 检测到的物体
        public NET_EVENT_FILE_INFO stuFileInfo;                        // 事件对应文件信息
        public NET_POINT[]         DetectLine = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_LINE_NUM);// 规则检测线
        public int                 nDetectLineNum;                     // 规则检测线顶点数
        public NET_POINT[]         TrackLine = (NET_POINT[])new NET_POINT().toArray(NET_MAX_TRACK_LINE_NUM);   // 物体运动轨迹
        public int                 nTrackLineNum;                      // 物体运动轨迹顶点数
        public byte                bEventAction;                       // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte                bDirection;                         // 表示入侵方向, 0-由左至右, 1-由右至左
        public byte                byReserved;
        public byte                byImageIndex;                       // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int                 dwSnapFlagMask;                     // 抓图标志(按位),具体见  NET_RESERVED_COMMON
        public int                 nSourceIndex;                       // 事件源设备上的index,-1表示数据无效,-1表示数据无效
        public byte[]              szSourceDevice = new byte[MAX_PATH];           // 事件源设备唯一标识,字段不存在或者为空表示本地设备
        public int        nOccurrenceCount;                   		   // 事件触发累计次数, 类型为unsigned int
        public EVENT_INTELLI_COMM_INFO     stuIntelliCommInfo;         // 智能事件公共信息
        public byte[]              bReserved = new byte[604];          // 保留字节,留待扩展.
    }
    
    // 事件类型 EVENT_IVS_CROSSREGIONDETECTION(警戒区事件)对应的数据块描述信息
    public static class DEV_EVENT_CROSSREGION_INFO extends Structure {
        public int                 nChannelID;                         // 通道号
        public byte[]              szName = new byte[128];             // 事件名称
        public byte[]              bReserved1 = new byte[4];           // 字节对齐
        public double              PTS;                                // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                                // 事件发生的时间
        public int                 nEventID;                           // 事件ID
        public NET_MSG_OBJECT      stuObject;                          // 检测到的物体
        public NET_EVENT_FILE_INFO stuFileInfo;                        // 事件对应文件信息
        public NET_POINT[]         DetectRegion = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM); // 规则检测区域
        public int                 nDetectRegionNum;                   // 规则检测区域顶点数
        public NET_POINT[]         TrackLine = (NET_POINT[])new NET_POINT().toArray(NET_MAX_TRACK_LINE_NUM);   // 物体运动轨迹
        public int                 nTrackLineNum;                      // 物体运动轨迹顶点数
        public byte                bEventAction;                       // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte                bDirection;                         // 表示入侵方向, 0-进入, 1-离开,2-出现,3-消失
        public byte                bActionType;                        // 表示检测动作类型,0-出现 1-消失 2-在区域内 3-穿越区域
        public byte                byImageIndex;                       // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int                 dwSnapFlagMask;                     // 抓图标志(按位),具体见NET_RESERVED_COMMON    
        public int                 nSourceIndex;                       // 事件源设备上的index,-1表示数据无效
        public byte[]              szSourceDevice = new byte[MAX_PATH];// 事件源设备唯一标识,字段不存在或者为空表示本地设备
        public int        		   nOccurrenceCount;                   // 事件触发累计次数, unsigned int 类型
        public byte[]              bReserved = new byte[536];          // 保留字节,留待扩展.
        public int                 nObjectNum;                         // 检测到的物体个数
        public NET_MSG_OBJECT[]    stuObjectIDs = (NET_MSG_OBJECT[]) new NET_MSG_OBJECT().toArray(NET_MAX_OBJECT_LIST);   // 检测到的物体
        public int                 nTrackNum;                          // 轨迹数(与检测到的物体个数  nObjectNum 对应)
        public NET_POLY_POINTS[]   stuTrackInfo = (NET_POLY_POINTS[]) new NET_POLY_POINTS().toArray(NET_MAX_OBJECT_LIST);   // 轨迹信息(与检测到的物体对应)
    	public EVENT_INTELLI_COMM_INFO     stuIntelliCommInfo;         // 智能事件公共信息
    }
    
    // 事件类型 EVENT_IVS_WANDERDETECTION(徘徊事件)对应的数据块描述信息
    public static class DEV_EVENT_WANDER_INFO extends Structure {
        public int                 nChannelID;                         // 通道号
        public byte[]              szName = new byte[128];             // 事件名称
        public byte[]              bReserved1 = new byte[4];           // 字节对齐
        public double              PTS;                                // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                                // 事件发生的时间
        public int                 nEventID;                           // 事件ID
        public NET_EVENT_FILE_INFO  stuFileInfo;                       // 事件对应文件信息
        public byte                bEventAction;                       // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]              byReserved = new byte[2];           // 保留字节
        public byte                byImageIndex;                       // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int                 nObjectNum;                         // 检测到的物体个数
        public NET_MSG_OBJECT[]    stuObjectIDs = (NET_MSG_OBJECT[]) new NET_MSG_OBJECT().toArray(NET_MAX_OBJECT_LIST);   // 检测到的物体
        public int                 nTrackNum;                          // 轨迹数(与检测到的物体个数对应)
        public NET_POLY_POINTS[]   stuTrackInfo = (NET_POLY_POINTS[]) new NET_POLY_POINTS().toArray(NET_MAX_OBJECT_LIST);   // 轨迹信息(与检测到的物体对应)
        public int                 nDetectRegionNum;                   // 规则检测区域顶点数
        public NET_POINT[]         DetectRegion = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM);    // 规则检测区域
        public int                 dwSnapFlagMask;                     // 抓图标志(按位),具体见NET_RESERVED_COMMON    
        public int                 nSourceIndex;                       // 事件源设备上的index,-1表示数据无效
        public byte[]              szSourceDevice = new byte[MAX_PATH]; // 事件源设备唯一标识,字段不存在或者为空表示本地设备
        public int        		   nOccurrenceCount;                   // 事件触发累计次数, unsigned int 类型
        public EVENT_INTELLI_COMM_INFO     stuIntelliCommInfo;         // 智能事件公共信息
        public byte[]              bReserved =  new byte[752];         // 保留字节,留待扩展.
    }
    
    //事件类型 EVENT_IVS_LEAVEDETECTION(离岗检测事件)对应数据块描述信息
    public static class DEV_EVENT_IVS_LEAVE_INFO extends Structure {
        public int                 nChannelID;                         // 通道号
        public byte[]              szName = new byte[128];             // 事件名称
        public byte[]              bReserved1 = new byte[4];           // 字节对齐
        public double              PTS;                                // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                                // 事件发生的时间
        public int                 nEventID;                           // 事件ID
        public NET_MSG_OBJECT      stuObject;                          // 检测到的物体
        public NET_EVENT_FILE_INFO stuFileInfo;                        // 事件对应文件信息
        public NET_RESOLUTION_INFO stuResolution;                      // 对应图片的分辨率
        public int                 nDetectRegionNum;                   // 规则检测区域顶点数
        public NET_POINT[]         DetectRegion = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM);// 规则检测区域
        public byte                bEventAction;                       // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;    
        public byte                byImageIndex;                       // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
    	public EVENT_INTELLI_COMM_INFO     stuIntelliCommInfo;         // 智能事件公共信息
    	public byte[]              bReserved = new byte[1022];         // 保留字节
    }

    //事件类型 EVENT_IVS_AUDIO_ABNORMALDETECTION(声音异常检测)对应数据块描述信息
    public static class DEV_EVENT_IVS_AUDIO_ABNORMALDETECTION_INFO extends Structure {
        public int                 nChannelID;                         // 通道号
        public byte[]              szName = new byte[128];             // 事件名称
        public byte[]              bReserved1 = new byte[4];           // 字节对齐
        public double              PTS;                                // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                                // 事件发生的时间
        public int                 nEventID;                           // 事件ID
        public NET_EVENT_FILE_INFO  stuFileInfo;                       // 事件对应文件信息
        public int                 nDecibel;                           // 声音强度
        public int                 nFrequency;                         // 声音频率
        public byte                bEventAction;                       // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]              byReserved = new byte[2];
        public byte                byImageIndex;                       // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int                 dwSnapFlagMask;                     // 抓图标志(按位),具体见NET_RESERVED_COMMON
        public NET_RESOLUTION_INFO stuResolution;                      // 对应图片的分辨率
        public byte[]              bReserved = new byte[1024];         // 保留字节,留待扩展.
    }
    
    //事件类型 EVENT_IVS_CLIMBDETECTION(攀高检测事件)对应数据块描述信息
    public static class DEV_EVENT_IVS_CLIMB_INFO extends Structure {
        public int                 nChannelID;                         // 通道号
        public byte[]              szName = new byte[128];             // 事件名称
        public byte[]              bReserved1 = new byte[4];           // 字节对齐
        public double              PTS;                                // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                                // 事件发生的时间
        public int                 nEventID;                           // 事件ID
        public NET_MSG_OBJECT      stuObject;                          // 检测到的物体
        public NET_EVENT_FILE_INFO stuFileInfo;                        // 事件对应文件信息
        public NET_RESOLUTION_INFO stuResolution;                      // 对应图片的分辨率
        public int                 nDetectLineNum;                     // 规则检测线顶点数
        public NET_POINT[]         DetectLine = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_LINE_NUM);         // 规则检测线
        public byte                bEventAction;                       // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte                byImageIndex;                       // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int        		   nOccurrenceCount;                   // 事件触发累计次数, unsigned int
        public EVENT_INTELLI_COMM_INFO     stuIntelliCommInfo;         // 智能事件公共信息
        public byte[]              bReserved = new byte[1018];         // 保留字节
    }
    
    // 事件类型 EVENT_IVS_FIGHTDETECTION(斗殴事件)对应的数据块描述信息
    public static class DEV_EVENT_FIGHT_INFO extends Structure {
        public int                 nChannelID;                         // 通道号
        public byte[]              szName = new byte[128];             // 事件名称
        public byte[]              bReserved1 = new byte[4];           // 字节对齐
        public double              PTS;                                // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                                // 事件发生的时间
        public int                 nEventID;                           // 事件ID
        public int                 nObjectNum;                         // 检测到的物体个数
        public NET_MSG_OBJECT[]    stuObjectIDs = (NET_MSG_OBJECT[])new NET_MSG_OBJECT().toArray(NET_MAX_OBJECT_LIST);   // 检测到的物体列表
        public NET_EVENT_FILE_INFO  stuFileInfo;                       // 事件对应文件信息
        public byte                bEventAction;                       // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte[]              byReserved = new byte[2];           // 保留字节
        public byte                byImageIndex;                       // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int                 nDetectRegionNum;                   // 规则检测区域顶点数
        public NET_POINT[]         DetectRegion = (NET_POINT[]) new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM);    // 规则检测区域
        
        public int                 dwSnapFlagMask;                     // 抓图标志(按位),具体见NET_RESERVED_COMMON    
        public int                 nSourceIndex;                       // 事件源设备上的index,-1表示数据无效
        public byte[]              szSourceDevice = new byte[MAX_PATH]; // 事件源设备唯一标识,字段不存在或者为空表示本地设备
        public int                 nOccurrenceCount;                   // 事件触发累计次数, unsigned int 类型
        public EVENT_INTELLI_COMM_INFO     stuIntelliCommInfo;         // 智能事件公共信息
        public byte[]              bReserved = new byte[620];          // 保留字节,留待扩展.
    }
    
    // 加油类型
    public static class EM_REFUEL_TYPE extends Structure {
        public static final int 	EM_REFUEL_TYPE_UNKNOWN = 0;								// unknown
        public static final int		EM_REFUEL_TYPE_NINETY_EIGHT = 1;						// "98#"
        public static final int		EM_REFUEL_TYPE_NINETY_SEVEN = 2;						// "97#"
        public static final int		EM_REFUEL_TYPE_NINETY_FIVE  = 3;						// "95#"
        public static final int		EM_REFUEL_TYPE_NINETY_THREE = 4;                        // "93#"
        public static final int		EM_REFUEL_TYPE_NINETY = 5;								// "90#"
        public static final int		EM_REFUEL_TYPE_TEN 	= 6;								// "10#"
        public static final int		EM_REFUEL_TYPE_FIVE = 7;								// "5#"
        public static final int		EM_REFUEL_TYPE_ZERO = 8; 								// "0#"
        public static final int		EM_REFUEL_TYPE_NEGATIVE_TEN = 9;						// "-10#"
        public static final int		EM_REFUEL_TYPE_NEGATIVE_TWENTY = 10;					// "-20#"
        public static final int		EM_REFUEL_TYPE_NEGATIVE_THIRTY_FIVE = 11;				// "-35#"
        public static final int		EM_REFUEL_TYPE_NEGATIVE_FIFTY = 12;						// "-50#"   	
    }

    // 车辆抓拍图片信息
    public static class DEV_EVENT_TRAFFIC_FCC_IMAGE extends Structure {
        public int              dwOffSet; // 图片文件在二进制数据块中的偏移位置, 单位:字节
        public int              dwLength; // 图片大小, 单位:字节
        public int              wWidth;   // 图片宽度, 单位:像素
        public int              wHeight;  // 图片高度, 单位:像素
    }

    // 车辆抓图信息
    public static class DEV_EVENT_TRAFFIC_FCC_OBJECT extends Structure {
    	public DEV_EVENT_TRAFFIC_FCC_IMAGE	stuImage; // 车辆抓拍图片信息
    }

    // 事件类型  EVENT_IVS_TRAFFIC_FCC 加油站提枪、挂枪事件
    public static class DEV_EVENT_TRAFFIC_FCC_INFO extends Structure {
    	public int              nChannelID;                                 // 通道号
        public byte[]           szName = new byte[NET_EVENT_NAME_LEN];      // 事件名称
        public int				nTriggerID;									// 触发类型: 1表示提枪, 2表示挂枪
        public double           PTS;                                        // 时间戳(单位是毫秒)
        public NET_TIME_EX      UTC;                                        // 事件发生的时间
        public int              nEventID;                                   // 事件ID
        ///////////////////////////////以上为公共字段//////////////////////////////

    	public int				nNum;										// 加油枪号
    	public int				nLitre;										// 加油升数,单位 0.01升
    	public int   			emType;										// 加油类型: 取值范围{"90#","93#","10#","-20#"}, 具体参考 EM_REFUEL_TYPE
    	public int				dwMoney;									// 加油金额,单位 0.01元
    	public byte[]		    szText = new byte[NET_COMMON_STRING_16];	// 车牌号
    	public byte[]			szTime = new byte[NET_COMMON_STRING_32];	// 事件发生时间: "2016-05-23 10:31:17"
    	public DEV_EVENT_TRAFFIC_FCC_OBJECT	stuObject;						// 车辆抓图信息
    	public byte[]			bReserved = new byte[1024];					// 保留字节,留待扩展
    }
    
    // 区域或曲线顶点信息
    public static class NET_POLY_POINTS extends Structure
    {
        public int         nPointNum;                               	// 顶点数
        public NET_POINT[] stuPoints = (NET_POINT[])new NET_POINT().toArray(NET_MAX_DETECT_REGION_NUM);     // 顶点信息
    }
    
    
    // 抓图参数结构体
    public static class SNAP_PARAMS extends Structure
    {
        public int     Channel;                       // 抓图的通道
        public int     Quality;                       // 画质；1~6
        public int     ImageSize;                     // 画面大小；0：QCIF,1：CIF,2：D1
        public int     mode;                          // 抓图模式；-1:表示停止抓图, 0：表示请求一帧, 1：表示定时发送请求, 2：表示连续请求
        public int     InterSnap;                     // 时间单位秒；若mode=1表示定时发送请求时
                                                      // 只有部分特殊设备(如：车载设备)支持通过该字段实现定时抓图时间间隔的配置
                                                      // 建议通过 CFG_CMD_ENCODE 配置的stuSnapFormat[nSnapMode].stuVideoFormat.nFrameRate字段实现相关功能
        public int     CmdSerial;                     // 请求序列号，有效值范围 0~65535，超过范围会被截断为 unsigned short
        public int[]   Reserved = new int[4];
    }
    
    // 对应CLIENT_StartSearchDevices接口
    public static class DEVICE_NET_INFO_EX extends Structure
    {
        public int iIPVersion;//4forIPV4,
        public byte[] szIP = new byte[64];//IPIPV4形如"192.168.0.1"
        public int nPort;//tcp端口
        public byte[] szSubmask = new byte[64];//子网掩码IPV6无子网掩码
        public byte[] szGateway = new byte[64];//网关
        public byte[] szMac = new byte[NET_MACADDR_LEN];//MAC地址
        public byte[] szDeviceType = new byte[NET_DEV_TYPE_LEN];//设备类型
        public byte byManuFactory;//目标设备的生产厂商,具体参考EM_IPC_TYPE类
        public byte byDefinition;//1-标清2-高清
        public byte bDhcpEn;//Dhcp使能状态,true-开,false-关, 类型为bool, 取值0或者1
        public byte byReserved1;//字节对齐
        public byte[] verifyData = new byte[88];//校验数据通过异步搜索回调获取(在修改设备IP时会用此信息进行校验)
        public byte[] szSerialNo = new byte[NET_DEV_SERIALNO_LEN];//序列号
        public byte[] szDevSoftVersion = new byte[NET_MAX_URL_LEN];//设备软件版本号
        public byte[] szDetailType = new byte[NET_DEV_TYPE_LEN];//设备型号
        public byte[] szVendor = new byte[NET_MAX_STRING_LEN];//OEM客户类型
        public byte[] szDevName = new byte[NET_MACHINE_NAME_NUM];//设备名称
        public byte[] szUserName = new byte[NET_USER_NAME_LENGTH_EX];//登陆设备用户名（在修改设备IP时需要填写）
        public byte[] szPassWord = new byte[NET_USER_NAME_LENGTH_EX];//登陆设备密码（在修改设备IP时需要填写）
        public short nHttpPort;//HTTP服务端口号, unsigned short类型
        public short wVideoInputCh;//视频输入通道数
        public short wRemoteVideoInputCh;//远程视频输入通道数
        public short wVideoOutputCh;//视频输出通道数
        public short wAlarmInputCh;//报警输入通道数
        public short wAlarmOutputCh;//报警输出通道数
        public byte[] cReserved = new byte[244];
    }
    
    // 视频输入通道信息
    public static class NET_VIDEO_INPUTS extends Structure {
        public int                       dwSize;
        public byte[]                    szChnName = new byte[64];                  // 通道名称
        public int                        bEnable;                                // 使能
        public byte[]                   szControlID = new byte[128];            // 控制ID
        public byte[]                   szMainStreamUrl = new byte[MAX_PATH];   // 主码流url地址 
        public byte[]                   szExtraStreamUrl = new byte[MAX_PATH];  // 辅码流url地址
        public int                      nOptionalMainUrlCount;                  // 备用主码流地址数量
        public byte[]                   szOptionalMainUrls = new byte[8*MAX_PATH];  // 备用主码流地址列表
        public int                      nOptionalExtraUrlCount;                 // 备用辅码流地址数量
        public byte[]                   szOptionalExtraUrls = new byte[8*MAX_PATH]; // 备用辅码流地址列表
        
        public NET_VIDEO_INPUTS()
        {
            this.dwSize = this.size();
        }
    }
    
    // 远程设备信息
    public static class NET_REMOTE_DEVICE extends Structure {
        public int                       dwSize;
        public int                       bEnable;                          // 使能
        public byte[]                    szIp     =  new byte[16];         // IP
        public byte[]                    szUser = new byte[8];             // 用户名, 建议使用szUserEx
        public byte[]                    szPwd     = new byte[8];          // 密码, 建议使用szPwdEx
        public int                       nPort;                            // 端口
        public int                       nDefinition;                      // 清晰度, 0-标清, 1-高清
        public int                       emProtocol;                       // 协议类型  NET_DEVICE_PROTOCOL
        public byte[]                    szDevName = new byte[64];         // 设备名称
        public int                       nVideoInputChannels;              // 视频输入通道数
        public int                       nAudioInputChannels;              // 音频输入通道数
        public byte[]                    szDevClass = new byte[32];        // 设备类型, 如IPC, DVR, NVR等
        public byte[]                    szDevType = new byte[32];         // 设备具体型号, 如IPC-HF3300
        public int                       nHttpPort;                        // Http端口
        public int                       nMaxVideoInputCount;              // 视频输入通道最大数
        public int                       nRetVideoInputCount;              // 返回实际通道个数
        public Pointer                   pstuVideoInputs;                  // 视频输入通道信息 NET_VIDEO_INPUTS*
        public byte[]                    szMachineAddress = new byte[256]; // 设备部署地
        public byte[]                    szSerialNo = new byte[48];        // 设备序列号
        public int                       nRtspPort;                        // Rtsp端口

        /*以下用于新平台扩展*/
        public byte[]                    szUserEx = new byte[32];          // 用户名
        public byte[]                    szPwdEx = new byte[32];           // 密码
        
        public NET_REMOTE_DEVICE() 
        {
            this.dwSize = this.size();
        }
    }
    
    // 可用的显示源信息
    public static class NET_MATRIX_CAMERA_INFO extends Structure 
    {
        public int                      dwSize;
        public byte                     szName[] = new byte[128];          // 名称
        public byte                     szDevID[] = new byte[128];         // 设备ID
        public byte                     szszControlID[] = new byte[128];   // 控制ID
        public int                      nChannelID;                        // 通道号, DeviceID设备内唯一
        public int                      nUniqueChannel;                    // 设备内统一编号的唯一通道号
        public int                      bRemoteDevice;                     // 是否远程设备
        public NET_REMOTE_DEVICE        stuRemoteDevice;                   // 远程设备信息
        public int                      emStreamType;                      // 视频码流类型  NET_STREAM_TYPE
        public int                      emChannelType;                     // 通道类型应 NET_LOGIC_CHN_TYPE
               
        public NET_MATRIX_CAMERA_INFO()
        {
            this.dwSize = this.size();
            stuRemoteDevice = new NET_REMOTE_DEVICE();
        }
    }
    
    // CLIENT_MatrixGetCameras接口的输入参数
    public static class NET_IN_MATRIX_GET_CAMERAS extends Structure {
        public int dwSize; 
        
        public NET_IN_MATRIX_GET_CAMERAS() {
            this.dwSize = this.size();
        }
    }
        
    // CLIENT_MatrixGetCameras接口的输出参数
    public static class NET_OUT_MATRIX_GET_CAMERAS extends Structure {
        public int                        dwSize;                    
        public Pointer                    pstuCameras;            // 显示源信息数组, 用户分配内存 NET_MATRIX_CAMERA_INFO
        public int                        nMaxCameraCount;        // 显示源数组大小
        public int                        nRetCameraCount;        // 返回的显示源数量
        
        public NET_OUT_MATRIX_GET_CAMERAS() {
            this.dwSize = this.size();
        }
       
        public NET_OUT_MATRIX_GET_CAMERAS(int nMaxCameraCount) {
            this.dwSize = this.size();
            this.nMaxCameraCount = nMaxCameraCount;
        
            NET_MATRIX_CAMERA_INFO CameraInfo = new NET_MATRIX_CAMERA_INFO();            
            Memory mem = new Memory(this.nMaxCameraCount * CameraInfo.size());
            mem.clear();
            pstuCameras = mem;
            
            long offset = 0;
            for (int i = 0; i < this.nMaxCameraCount; ++i) {
                NetSDKTools.SetStructDataToPointer(CameraInfo, this.pstuCameras, offset);
                offset += CameraInfo.size();
            }
        }
    }
    
    // CLIENT_SnapPictureToFile 接口输入参数
    public static class NET_IN_SNAP_PIC_TO_FILE_PARAM extends Structure {
        public int                         dwSize;                    // 结构体大小
        public SNAP_PARAMS                 stuParam;                  // 抓图参数, 其中mode字段仅一次性抓图, 不支持定时或持续抓图; 除了车载DVR, 其他设备仅支持每秒一张的抓图频率

        public byte[]                      szFilePath = new byte[MAX_PATH];// 写入文件的地址
        
        public NET_IN_SNAP_PIC_TO_FILE_PARAM() {
            this.dwSize = this.size();
            this.stuParam = new SNAP_PARAMS();
        }
    }
    
    //  CLIENT_SnapPictureToFile 接口输出参数
    public static class NET_OUT_SNAP_PIC_TO_FILE_PARAM extends Structure {
        public int                        dwSize;                    
        public Pointer                    szPicBuf;               // 图片内容,用户分配内存
        public int                        dwPicBufLen;            // 图片内容内存大小, 单位:字节
        public int                        dwPicBufRetLen;         // 返回的图片大小, 单位:字节
        
        public NET_OUT_SNAP_PIC_TO_FILE_PARAM() {
            this.dwSize = this.size();
        }
        
        public NET_OUT_SNAP_PIC_TO_FILE_PARAM(int nMaxBuf) {
            this.dwSize = this.size();
            this.dwPicBufLen = nMaxBuf;
            Memory mem = new Memory(nMaxBuf);
            mem.clear();
            this.szPicBuf = mem;
        }
    }
    
    // 录像文件信息
    public static class NET_RECORDFILE_INFO extends Structure {
        public int                        ch;                         // 通道号
        public byte[]                     filename = new byte[124];   // 文件名
        public int                        framenum;                   // 文件总帧数
        public int                        size;                       // 文件长度
        public NET_TIME                   starttime = new NET_TIME(); // 开始时间
        public NET_TIME                   endtime = new NET_TIME();   // 结束时间
        public int                        driveno;                    // 磁盘号(区分网络录像和本地录像的类型,0－127表示本地录像,其中64表示光盘1,128表示网络录像)
        public int                        startcluster;               // 起始簇号
        public byte                       nRecordFileType;            // 录象文件类型  0：普通录象；1：报警录象；2：移动检测；3：卡号录象；4：图片, 5: 智能录像,255:所有录像
        public byte                       bImportantRecID;            // 0:普通录像 1:重要录像
        public byte                       bHint;                      // 文件定位索引(nRecordFileType==4<图片>时,bImportantRecID<<8 +bHint ,组成图片定位索引 )
        public byte                       bRecType;                  // 0-主码流录像 1-辅码1流录像 2-辅码流2 3-辅码流3录像
        
        public static class ByValue extends NET_RECORDFILE_INFO implements Structure.ByValue { }
    }
    
    // 录像查询类型
    public static class EM_QUERY_RECORD_TYPE extends Structure {
        public static final int            EM_RECORD_TYPE_ALL              = 0;            // 所有录像
        public static final int            EM_RECORD_TYPE_ALARM            = 1;            // 外部报警录像
        public static final int            EM_RECORD_TYPE_MOTION_DETECT    = 2;            // 动态检测报警录像
        public static final int            EM_RECORD_TYPE_ALARM_ALL        = 3;            // 所有报警录像
        public static final int            EM_RECORD_TYPE_CARD             = 4;            // 卡号查询
        public static final int            EM_RECORD_TYPE_CONDITION        = 5;            // 按条件查询
        public static final int            EM_RECORD_TYPE_JOIN             = 6;            // 组合查询
        public static final int            EM_RECORD_TYPE_CARD_PICTURE     = 8;            // 按卡号查询图片,HB-U、NVS等使用
        public static final int            EM_RECORD_TYPE_PICTURE          = 9;            // 查询图片,HB-U、NVS等使用
        public static final int            EM_RECORD_TYPE_FIELD            = 10;           // 按字段查询
        public static final int            EM_RECORD_TYPE_INTELLI_VIDEO    = 11;           // 智能录像查询
        public static final int            EM_RECORD_TYPE_NET_DATA         = 15;           // 查询网络数据,金桥网吧等使用
        public static final int            EM_RECORD_TYPE_TRANS_DATA       = 16;           // 查询透明串口数据录像
        public static final int            EM_RECORD_TYPE_IMPORTANT        = 17;           // 查询重要录像
        public static final int            EM_RECORD_TYPE_TALK_DATA        = 18;           // 查询录音文件
        
        public static final int            EM_RECORD_TYPE_INVALID          = 256;          // 无效的查询类型
    }
    
    // 语言种类
    public static class NET_LANGUAGE_TYPE extends Structure
    {
        public static final int NET_LANGUAGE_ENGLISH = 0; //英文
        public static final int NET_LANGUAGE_CHINESE_SIMPLIFIED = NET_LANGUAGE_ENGLISH+1; //简体中文
        public static final int NET_LANGUAGE_CHINESE_TRADITIONAL = NET_LANGUAGE_CHINESE_SIMPLIFIED+1; //繁体中文
        public static final int NET_LANGUAGE_ITALIAN = NET_LANGUAGE_CHINESE_TRADITIONAL+1; //意大利文
        public static final int NET_LANGUAGE_SPANISH = NET_LANGUAGE_ITALIAN+1; //西班牙文
        public static final int NET_LANGUAGE_JAPANESE = NET_LANGUAGE_SPANISH+1; //日文版
        public static final int NET_LANGUAGE_RUSSIAN = NET_LANGUAGE_JAPANESE+1; //俄文版
        public static final int NET_LANGUAGE_FRENCH = NET_LANGUAGE_RUSSIAN+1; //法文版
        public static final int NET_LANGUAGE_GERMAN = NET_LANGUAGE_FRENCH+1; //德文版
        public static final int NET_LANGUAGE_PORTUGUESE = NET_LANGUAGE_GERMAN+1; //葡萄牙语
        public static final int NET_LANGUAGE_TURKEY = NET_LANGUAGE_PORTUGUESE+1; //土尔其语
        public static final int NET_LANGUAGE_POLISH = NET_LANGUAGE_TURKEY+1; //波兰语
        public static final int NET_LANGUAGE_ROMANIAN = NET_LANGUAGE_POLISH+1; //罗马尼亚
        public static final int NET_LANGUAGE_HUNGARIAN = NET_LANGUAGE_ROMANIAN+1; //匈牙利语
        public static final int NET_LANGUAGE_FINNISH = NET_LANGUAGE_HUNGARIAN+1; //芬兰语
        public static final int NET_LANGUAGE_ESTONIAN = NET_LANGUAGE_FINNISH+1; //爱沙尼亚语
        public static final int NET_LANGUAGE_KOREAN = NET_LANGUAGE_ESTONIAN+1; //韩语
        public static final int NET_LANGUAGE_FARSI = NET_LANGUAGE_KOREAN+1; //波斯语
        public static final int NET_LANGUAGE_DANSK = NET_LANGUAGE_FARSI+1; //丹麦语
        public static final int NET_LANGUAGE_CZECHISH = NET_LANGUAGE_DANSK+1; //捷克文
        public static final int NET_LANGUAGE_BULGARIA = NET_LANGUAGE_CZECHISH+1; //保加利亚文
        public static final int NET_LANGUAGE_SLOVAKIAN = NET_LANGUAGE_BULGARIA+1; //斯洛伐克语
        public static final int NET_LANGUAGE_SLOVENIA = NET_LANGUAGE_SLOVAKIAN+1; //斯洛文尼亚文
        public static final int NET_LANGUAGE_CROATIAN = NET_LANGUAGE_SLOVENIA+1; //克罗地亚语
        public static final int NET_LANGUAGE_DUTCH = NET_LANGUAGE_CROATIAN+1; //荷兰语
        public static final int NET_LANGUAGE_GREEK = NET_LANGUAGE_DUTCH+1; //希腊语
        public static final int NET_LANGUAGE_UKRAINIAN = NET_LANGUAGE_GREEK+1; //乌克兰语
        public static final int NET_LANGUAGE_SWEDISH = NET_LANGUAGE_UKRAINIAN+1; //瑞典语
        public static final int NET_LANGUAGE_SERBIAN = NET_LANGUAGE_SWEDISH+1; //塞尔维亚语
        public static final int NET_LANGUAGE_VIETNAMESE = NET_LANGUAGE_SERBIAN+1; //越南语
        public static final int NET_LANGUAGE_LITHUANIAN = NET_LANGUAGE_VIETNAMESE+1; //立陶宛语
        public static final int NET_LANGUAGE_FILIPINO = NET_LANGUAGE_LITHUANIAN+1; //菲律宾语
        public static final int NET_LANGUAGE_ARABIC = NET_LANGUAGE_FILIPINO+1; //阿拉伯语
        public static final int NET_LANGUAGE_CATALAN = NET_LANGUAGE_ARABIC+1; //加泰罗尼亚语
        public static final int NET_LANGUAGE_LATVIAN = NET_LANGUAGE_CATALAN+1; //拉脱维亚语
        public static final int NET_LANGUAGE_THAI = NET_LANGUAGE_LATVIAN+1; //泰语
        public static final int NET_LANGUAGE_HEBREW = NET_LANGUAGE_THAI+1; //希伯来语
        public static final int NET_LANGUAGE_Bosnian = NET_LANGUAGE_HEBREW+1; //波斯尼亚文
    }
    
    // 区域信息
    public static class CFG_RECT extends Structure
    {
        public int nLeft;
        public int nTop;
        public int nRight;
        public int nBottom;
    }

    // 视频输入夜晚特殊配置选项，在晚上光线较暗时自动切换到夜晚的配置参数
    public static class CFG_VIDEO_IN_NIGHT_OPTIONS extends Structure
    {
        public byte bySwitchMode;//已废弃,使用CFG_VIDEO_IN_OPTIONS里面的bySwitchMode
        //0-不切换，总是使用白天配置；1-根据亮度切换；2-根据时间切换；3-不切换，总是使用夜晚配置；4-使用普通配置
        public byte byProfile;//当前使用的配置文件.
                              // 0-白天
                              // 1-晚上
                              // 2-Normal
                              // 0，1,2都为临时配置，使图像生效，便于查看图像调试效果，不点击确定，离开页面不保存至设备。
                              ///3-非临时配置，点击确定后保存至设备，与SwitchMode结合使用，根据SwitchMode决定最终生效的配置。
                              // SwitchMode=0，Profile=3，设置白天配置到设备；
                              // SwitchMode=1，Profile=3，则设置夜晚配置到设备
                              // SwitchMode=2，Profile=3，根据日出日落时间段切换，白天时间段使用白天配置，夜晚时间段使用夜晚配置，保存至设备；
                              // SwitchMode=4，Profile=3；使用普通配置，保存至设备
        public byte byBrightnessThreshold;//亮度阈值0~100
        public byte bySunriseHour;//大致日出和日落时间，日落之后日出之前，将采用夜晚特殊的配置。
        public byte bySunriseMinute;//00:00:00 ~ 23:59:59
        public byte bySunriseSecond;
        public byte bySunsetHour;
        public byte bySunsetMinute;
        public byte bySunsetSecond;
        public byte byGainRed;//红色增益调节，白平衡为"Custom"模式下有效0~100
        public byte byGainBlue;//绿色增益调节，白平衡为"Custom"模式下有效0~100
        public byte byGainGreen;//蓝色增益调节，白平衡为"Custom"模式下有效0~100
        public byte byExposure;//曝光模式；取值范围取决于设备能力集：0-自动曝光，1-曝光等级1，2-曝光等级2…n-1最大曝光等级数n带时间上下限的自动曝光n+1自定义时间手动曝光 (n==byExposureEn）
        public float fExposureValue1;//自动曝光时间下限或者手动曝光自定义时间,毫秒为单位，取值0.1ms~80ms
        public float fExposureValue2;//自动曝光时间上限,毫秒为单位，取值0.1ms~80ms
        public byte byWhiteBalance;//白平衡,0-"Disable", 1-"Auto", 2-"Custom", 3-"Sunny", 4-"Cloudy", 5-"Home", 6-"Office", 7-"Night", 8-"HighColorTemperature", 9-"LowColorTemperature", 10-"AutoColorTemperature", 11-"CustomColorTemperature"
        public byte byGain;//0~100,GainAuto为true时表示自动增益的上限，否则表示固定的增益值
        public byte bGainAuto;//自动增益, 类型为bool, 取值0或1
        public byte bIrisAuto;//自动光圈, 类型为bool, 取值0或1
        public float fExternalSyncPhase;//外同步的相位设置0~360
        public byte byGainMin;//增益下限
        public byte byGainMax;//增益上限
        public byte byBacklight;//背光补偿：取值范围取决于设备能力集：0-关闭1-启用2-指定区域背光补偿
        public byte byAntiFlicker;//防闪烁模式0-Outdoor1-50Hz防闪烁 2-60Hz防闪烁
        public byte byDayNightColor;//日/夜模式；0-总是彩色，1-根据亮度自动切换，2-总是黑白
        public byte byExposureMode;//曝光模式调节曝光等级为自动曝光时有效，取值：0-默认自动，1-增益优先，2-快门优先
        public byte byRotate90;//0-不旋转，1-顺时针90°，2-逆时针90°
        public byte bMirror;//镜像, 类型为bool, 取值0或1
        public byte byWideDynamicRange;//宽动态值0-关闭，1~100-为真实范围值
        public byte byGlareInhibition;//强光抑制0-关闭，1~100为范围值
        public CFG_RECT stuBacklightRegion = new CFG_RECT();//背光补偿区域
        public byte byFocusMode;//0-关闭，1-辅助聚焦，2-自动聚焦
        public byte bFlip;//翻转, 类型为bool, 取值0或1
        public byte[] reserved = new byte[74];//保留
    }

    // 闪光灯配置
    public static class CFG_FLASH_CONTROL extends Structure
    {
        public byte byMode;//工作模式，0-禁止闪光，1-始终闪光，2-自动闪光
        public byte byValue;//工作值,0-0us,1-64us, 2-128us, 3-192...15-960us
        public byte byPole;//触发模式,0-低电平1-高电平 2-上升沿 3-下降沿
        public byte byPreValue;//亮度预设值区间0~100
        public byte byDutyCycle;//占空比,0~100
        public byte byFreqMultiple;//倍频,0~10
        public byte[] reserved = new byte[122];//保留
    }

    // 抓拍参数特殊配置
    public static class CFG_VIDEO_IN_SNAPSHOT_OPTIONS extends Structure
    {
        public byte byGainRed;//红色增益调节，白平衡为"Custom"模式下有效0~100
        public byte byGainBlue;//绿色增益调节，白平衡为"Custom"模式下有效0~100
        public byte byGainGreen;//蓝色增益调节，白平衡为"Custom"模式下有效0~100
        public byte byExposure;//曝光模式；取值范围取决于设备能力集：0-自动曝光，1-曝光等级1，2-曝光等级2…n-1最大曝光等级数n带时间上下限的自动曝光n+1自定义时间手动曝光 (n==byExposureEn）
        public float fExposureValue1;//自动曝光时间下限或者手动曝光自定义时间,毫秒为单位，取值0.1ms~80ms
        public float fExposureValue2;//自动曝光时间上限,毫秒为单位，取值0.1ms~80ms  
        public byte byWhiteBalance;//白平衡,0-"Disable", 1-"Auto", 2-"Custom", 3-"Sunny", 4-"Cloudy", 5-"Home", 6-"Office", 7-"Night", 8-"HighColorTemperature", 9-"LowColorTemperature", 10-"AutoColorTemperature", 11-"CustomColorTemperature"
        public byte byColorTemperature;//色温等级,白平衡为"CustomColorTemperature"模式下有效
        public byte bGainAuto;//自动增益, 类型为bool, 取值0或1
        public byte byGain;//增益调节,GainAuto为true时表示自动增益的上限，否则表示固定的增益值
        public byte[] reversed = new byte[112];//保留
    }

    // 鱼眼镜头配置
    public static class CFG_FISH_EYE extends Structure
    {
        public CFG_POLYGON stuCenterPoint;//鱼眼圆心坐标,范围[0,8192]
        public int nRadius;//鱼眼半径大小,范围[0,8192], 类型为unsigned int
        public float fDirection;//镜头旋转方向,旋转角度[0,360.0]
        public byte byPlaceHolder;//镜头安装方式1顶装，2壁装；3地装,默认1
        public byte byCalibrateMode;//鱼眼矫正模式,详见CFG_CALIBRATE_MODE枚举值
        public byte[] reversed = new byte[31];//保留
    }

    public static class CFG_VIDEO_IN_NORMAL_OPTIONS extends Structure
    {
        public byte byGainRed;//红色增益调节，白平衡为"Custom"模式下有效0~100
        public byte byGainBlue;//绿色增益调节，白平衡为"Custom"模式下有效0~100
        public byte byGainGreen;//蓝色增益调节，白平衡为"Custom"模式下有效0~100
        public byte byExposure;//曝光模式；取值范围取决于设备能力集：0-自动曝光，1-曝光等级1，2-曝光等级2…n-1最大曝光等级数n带时间上下限的自动曝光n+1自定义时间手动曝光 (n==byExposureEn）
        public float fExposureValue1;//自动曝光时间下限或者手动曝光自定义时间,毫秒为单位，取值0.1ms~80ms
        public float fExposureValue2;//自动曝光时间上限,毫秒为单位，取值0.1ms~80ms
        public byte byWhiteBalance;//白平衡,0-"Disable", 1-"Auto", 2-"Custom", 3-"Sunny", 4-"Cloudy", 5-"Home", 6-"Office", 7-"Night", 8-"HighColorTemperature", 9-"LowColorTemperature", 10-"AutoColorTemperature", 11-"CustomColorTemperature"
        public byte byGain;//0~100,GainAuto为true时表示自动增益的上限，否则表示固定的增益值
        public byte bGainAuto;//自动增益, 类型为bool, 取值0或1
        public byte bIrisAuto;//自动光圈, 类型为bool, 取值0或1
        public float fExternalSyncPhase;//外同步的相位设置0~360
        public byte byGainMin;//增益下限
        public byte byGainMax;//增益上限
        public byte byBacklight;//背光补偿：取值范围取决于设备能力集：0-关闭1-启用2-指定区域背光补偿
        public byte byAntiFlicker;//防闪烁模式0-Outdoor1-50Hz防闪烁 2-60Hz防闪烁
        public byte byDayNightColor;//日/夜模式；0-总是彩色，1-根据亮度自动切换，2-总是黑白
        public byte byExposureMode;//曝光模式调节曝光等级为自动曝光时有效，取值：0-默认自动，1-增益优先，2-快门优先
        public byte byRotate90;//0-不旋转，1-顺时针90°，2-逆时针90°
        public byte bMirror;//镜像, 类型为bool, 取值0或1
        public byte byWideDynamicRange;//宽动态值0-关闭，1~100-为真实范围值
        public byte byGlareInhibition;//强光抑制0-关闭，1~100为范围值
        public CFG_RECT stuBacklightRegion;//背光补偿区域
        public byte byFocusMode;//0-关闭，1-辅助聚焦，2-自动聚焦
        public byte bFlip;//翻转, 类型为bool, 取值0或1
        public byte[] reserved = new byte[74];//保留
    }

    // 视频输入前端选项
    public static class CFG_VIDEO_IN_OPTIONS extends Structure
    {
        public byte byBacklight;//背光补偿：取值范围取决于设备能力集：0-关闭1-启用2-指定区域背光补偿
        public byte byDayNightColor;//日/夜模式；0-总是彩色，1-根据亮度自动切换，2-总是黑白
        public byte byWhiteBalance;//白平衡,0-"Disable", 1-"Auto", 2-"Custom", 3-"Sunny", 4-"Cloudy", 5-"Home", 6-"Office", 7-"Night", 8-"HighColorTemperature", 9-"LowColorTemperature", 10-"AutoColorTemperature", 11-"CustomColorTemperature"
        public byte byColorTemperature;//色温等级,白平衡为"CustomColorTemperature"模式下有效
        public byte bMirror;//镜像, 类型为bool, 取值0或1
        public byte bFlip;//翻转, 类型为bool, 取值0或1
        public byte bIrisAuto;//自动光圈, 类型为bool, 取值0或1
        public byte bInfraRed;//根据环境光自动开启红外补偿灯, 类型为bool, 取值0或1
        public byte byGainRed;//红色增益调节，白平衡为"Custom"模式下有效0~100
        public byte byGainBlue;//绿色增益调节，白平衡为"Custom"模式下有效0~100
        public byte byGainGreen;//蓝色增益调节，白平衡为"Custom"模式下有效0~100
        public byte byExposure;//曝光模式；取值范围取决于设备能力集：0-自动曝光，1-曝光等级1，2-曝光等级2…n-1最大曝光等级数n带时间上下限的自动曝光n+1自定义时间手动曝光 (n==byExposureEn）
        public float fExposureValue1;//自动曝光时间下限或者手动曝光自定义时间,毫秒为单位，取值0.1ms~80ms
        public float fExposureValue2;//自动曝光时间上限,毫秒为单位，取值0.1ms~80ms
        public byte bGainAuto;//自动增益, 类型为bool, 取值0或1
        public byte byGain;//增益调节,GainAuto为true时表示自动增益的上限，否则表示固定的增益值
        public byte bySignalFormat;//信号格式,0-Inside(内部输入)1-BT656 2-720p 3-1080p  4-1080i  5-1080sF
        public byte byRotate90;//0-不旋转，1-顺时针90°，2-逆时针90°
        public float fExternalSyncPhase;//外同步的相位设置 0~360   
        public byte byExternalSync;//外部同步信号输入,0-内部同步 1-外部同步
        public byte bySwitchMode;//0-不切换，总是使用白天配置；1-根据亮度切换；2-根据时间切换；3-不切换，总是使用夜晚配置；4-使用普通配置
        public byte byDoubleExposure;//双快门,0-不启用，1-双快门全帧率，即图像和视频只有快门参数不同，2-双快门半帧率，即图像和视频快门及白平衡参数均不同
        public byte byWideDynamicRange;//宽动态值
        public CFG_VIDEO_IN_NIGHT_OPTIONS stuNightOptions;//夜晚参数
        public CFG_FLASH_CONTROL stuFlash;//闪光灯配置
        public CFG_VIDEO_IN_SNAPSHOT_OPTIONS stuSnapshot;//抓拍参数,双快门时有效
        public CFG_FISH_EYE stuFishEye;//鱼眼镜头
        public byte byFocusMode;//0-关闭，1-辅助聚焦，2-自动聚焦
        public byte[] reserved = new byte[28];//保留
        public byte byGainMin;//增益下限
        public byte byGainMax;//增益上限
        public byte byAntiFlicker;//防闪烁模式 0-Outdoor 1-50Hz防闪烁 2-60Hz防闪烁
        public byte byExposureMode;//曝光模式调节曝光等级为自动曝光时有效，取值：0-默认自动，1-增益优先，2-快门优先,4-手动
        public byte byGlareInhibition;//强光抑制0-关闭，1~100为范围值
        public CFG_RECT stuBacklightRegion;//背光补偿区域
        public CFG_VIDEO_IN_NORMAL_OPTIONS stuNormalOptions;//普通参数
    }
    
    // 通用云台控制命令
    public static class NET_PTZ_ControlType extends Structure
    {
        public static final int NET_PTZ_UP_CONTROL = 0;//上
        public static final int NET_PTZ_DOWN_CONTROL = NET_PTZ_UP_CONTROL+1; //下
        public static final int NET_PTZ_LEFT_CONTROL = NET_PTZ_DOWN_CONTROL+1; //左
        public static final int NET_PTZ_RIGHT_CONTROL = NET_PTZ_LEFT_CONTROL+1; //右
        public static final int NET_PTZ_ZOOM_ADD_CONTROL = NET_PTZ_RIGHT_CONTROL+1; //变倍+
        public static final int NET_PTZ_ZOOM_DEC_CONTROL = NET_PTZ_ZOOM_ADD_CONTROL+1; //变倍-
        public static final int NET_PTZ_FOCUS_ADD_CONTROL = NET_PTZ_ZOOM_DEC_CONTROL+1; //调焦+
        public static final int NET_PTZ_FOCUS_DEC_CONTROL = NET_PTZ_FOCUS_ADD_CONTROL+1; //调焦-
        public static final int NET_PTZ_APERTURE_ADD_CONTROL = NET_PTZ_FOCUS_DEC_CONTROL+1; //光圈+
        public static final int NET_PTZ_APERTURE_DEC_CONTROL = NET_PTZ_APERTURE_ADD_CONTROL+1; //光圈-
        public static final int NET_PTZ_POINT_MOVE_CONTROL = NET_PTZ_APERTURE_DEC_CONTROL+1; //转至预置点
        public static final int NET_PTZ_POINT_SET_CONTROL = NET_PTZ_POINT_MOVE_CONTROL+1; //设置
        public static final int NET_PTZ_POINT_DEL_CONTROL = NET_PTZ_POINT_SET_CONTROL+1; //删除
        public static final int NET_PTZ_POINT_LOOP_CONTROL = NET_PTZ_POINT_DEL_CONTROL+1; //点间巡航
        public static final int NET_PTZ_LAMP_CONTROL = NET_PTZ_POINT_LOOP_CONTROL+1; //灯光雨刷
    }

    // 云台控制扩展命令
    public static class NET_EXTPTZ_ControlType extends Structure
    {
        public static final int NET_EXTPTZ_LEFTTOP = 0x20;//左上
        public static final int NET_EXTPTZ_RIGHTTOP = NET_EXTPTZ_LEFTTOP+1; //右上
        public static final int NET_EXTPTZ_LEFTDOWN = NET_EXTPTZ_RIGHTTOP+1; //左下
        public static final int NET_EXTPTZ_RIGHTDOWN = NET_EXTPTZ_LEFTDOWN+1; //右下
        public static final int NET_EXTPTZ_ADDTOLOOP = NET_EXTPTZ_RIGHTDOWN+1; //加入预置点到巡航巡航线路预置点值
        public static final int NET_EXTPTZ_DELFROMLOOP = NET_EXTPTZ_ADDTOLOOP+1; //删除巡航中预置点巡航线路预置点值
        public static final int NET_EXTPTZ_CLOSELOOP = NET_EXTPTZ_DELFROMLOOP+1; //清除巡航巡航线路
        public static final int NET_EXTPTZ_STARTPANCRUISE = NET_EXTPTZ_CLOSELOOP+1; //开始水平旋转
        public static final int NET_EXTPTZ_STOPPANCRUISE = NET_EXTPTZ_STARTPANCRUISE+1; //停止水平旋转
        public static final int NET_EXTPTZ_SETLEFTBORDER = NET_EXTPTZ_STOPPANCRUISE+1; //设置左边界
        public static final int NET_EXTPTZ_SETRIGHTBORDER = NET_EXTPTZ_SETLEFTBORDER+1; //设置右边界
        public static final int NET_EXTPTZ_STARTLINESCAN = NET_EXTPTZ_SETRIGHTBORDER+1; //开始线扫
        public static final int NET_EXTPTZ_CLOSELINESCAN = NET_EXTPTZ_STARTLINESCAN+1; //停止线扫
        public static final int NET_EXTPTZ_SETMODESTART = NET_EXTPTZ_CLOSELINESCAN+1; //设置模式开始模式线路
        public static final int NET_EXTPTZ_SETMODESTOP = NET_EXTPTZ_SETMODESTART+1; //设置模式结束模式线路
        public static final int NET_EXTPTZ_RUNMODE = NET_EXTPTZ_SETMODESTOP+1; //运行模式模式线路
        public static final int NET_EXTPTZ_STOPMODE = NET_EXTPTZ_RUNMODE+1; //停止模式模式线路
        public static final int NET_EXTPTZ_DELETEMODE = NET_EXTPTZ_STOPMODE+1; //清除模式模式线路
        public static final int NET_EXTPTZ_REVERSECOMM = NET_EXTPTZ_DELETEMODE+1; //翻转命令
        public static final int NET_EXTPTZ_FASTGOTO = NET_EXTPTZ_REVERSECOMM+1; //快速定位水平坐标(8192)垂直坐标(8192)变倍(4)
        public static final int NET_EXTPTZ_AUXIOPEN = NET_EXTPTZ_FASTGOTO+1; //辅助开关开辅助点
        public static final int NET_EXTPTZ_AUXICLOSE = NET_EXTPTZ_AUXIOPEN+1; //辅助开关关辅助点
        public static final int NET_EXTPTZ_OPENMENU = 0x36;//打开球机菜单
        public static final int NET_EXTPTZ_CLOSEMENU = NET_EXTPTZ_OPENMENU+1; //关闭菜单
        public static final int NET_EXTPTZ_MENUOK = NET_EXTPTZ_CLOSEMENU+1; //菜单确定
        public static final int NET_EXTPTZ_MENUCANCEL = NET_EXTPTZ_MENUOK+1; //菜单取消
        public static final int NET_EXTPTZ_MENUUP = NET_EXTPTZ_MENUCANCEL+1; //菜单上
        public static final int NET_EXTPTZ_MENUDOWN = NET_EXTPTZ_MENUUP+1; //菜单下
        public static final int NET_EXTPTZ_MENULEFT = NET_EXTPTZ_MENUDOWN+1; //菜单左
        public static final int NET_EXTPTZ_MENURIGHT = NET_EXTPTZ_MENULEFT+1; //菜单右
        public static final int NET_EXTPTZ_ALARMHANDLE = 0x40;//报警联动云台parm1：报警输入通道；parm2：报警联动类型1-预置点2-线扫3-巡航；parm3：联动值，如预置点号
        public static final int NET_EXTPTZ_MATRIXSWITCH = 0x41;//矩阵切换parm1：监视器号(视频输出号)；parm2：视频输入号；parm3：矩阵号
        public static final int NET_EXTPTZ_LIGHTCONTROL= NET_EXTPTZ_MATRIXSWITCH+1; //灯光控制器
        public static final int NET_EXTPTZ_EXACTGOTO = NET_EXTPTZ_LIGHTCONTROL+1; //三维精确定位parm1：水平角度(0~3600)；parm2：垂直坐标(0~900)；parm3：变倍(1~128)
        public static final int NET_EXTPTZ_RESETZERO = NET_EXTPTZ_EXACTGOTO+1; //三维定位重设零位
        public static final int NET_EXTPTZ_MOVE_ABSOLUTELY = NET_EXTPTZ_RESETZERO+1; //绝对移动控制命令，param4对应结构PTZ_CONTROL_ABSOLUTELY
        public static final int NET_EXTPTZ_MOVE_CONTINUOUSLY = NET_EXTPTZ_MOVE_ABSOLUTELY+1; //持续移动控制命令，param4对应结构PTZ_CONTROL_CONTINUOUSLY
        public static final int NET_EXTPTZ_GOTOPRESET = NET_EXTPTZ_MOVE_CONTINUOUSLY+1; //云台控制命令，以一定速度转到预置位点，parm4对应结构PTZ_CONTROL_GOTOPRESET
        public static final int NET_EXTPTZ_SET_VIEW_RANGE = 0x49;//设置可视域(param4对应结构PTZ_VIEW_RANGE_INFO)
        public static final int NET_EXTPTZ_FOCUS_ABSOLUTELY = 0x4A;//绝对聚焦(param4对应结构PTZ_FOCUS_ABSOLUTELY)
        public static final int NET_EXTPTZ_HORSECTORSCAN = 0x4B;//水平扇扫(param4对应PTZ_CONTROL_SECTORSCAN,param1、param2、param3无效)
        public static final int NET_EXTPTZ_VERSECTORSCAN = 0x4C;//垂直扇扫(param4对应PTZ_CONTROL_SECTORSCAN,param1、param2、param3无效)
        public static final int NET_EXTPTZ_SET_ABS_ZOOMFOCUS = 0x4D;//设定绝对焦距、聚焦值,param1为焦距,范围:0,255],param2为聚焦,范围:[0,255],param3、param4无效
        public static final int NET_EXTPTZ_SET_FISHEYE_EPTZ = 0x4E;//控制鱼眼电子云台，param4对应结构PTZ_CONTROL_SET_FISHEYE_EPTZ
        public static final int NET_EXTPTZ_UP_TELE = 0x70;    //上 + TELE param1=速度(1-8)，下同
        public static final int NET_EXTPTZ_DOWN_TELE = NET_EXTPTZ_UP_TELE+1; //下 + TELE
        public static final int NET_EXTPTZ_LEFT_TELE = NET_EXTPTZ_DOWN_TELE+1; //左 + TELE
        public static final int NET_EXTPTZ_RIGHT_TELE = NET_EXTPTZ_LEFT_TELE+1; //右 + TELE
        public static final int NET_EXTPTZ_LEFTUP_TELE = NET_EXTPTZ_RIGHT_TELE+1; //左上 + TELE
        public static final int NET_EXTPTZ_LEFTDOWN_TELE = NET_EXTPTZ_LEFTUP_TELE+1; //左下 + TELE
        public static final int NET_EXTPTZ_TIGHTUP_TELE = NET_EXTPTZ_LEFTDOWN_TELE+1; //右上 + TELE
        public static final int NET_EXTPTZ_RIGHTDOWN_TELE = NET_EXTPTZ_TIGHTUP_TELE+1; //右下 + TELE
        public static final int NET_EXTPTZ_UP_WIDE = NET_EXTPTZ_RIGHTDOWN_TELE+1; // 上 + WIDEparam1=速度(1-8)，下同
        public static final int NET_EXTPTZ_DOWN_WIDE = NET_EXTPTZ_UP_WIDE+1; //下 + WIDE
        public static final int NET_EXTPTZ_LEFT_WIDE = NET_EXTPTZ_DOWN_WIDE+1; //左 + WIDE
        public static final int NET_EXTPTZ_RIGHT_WIDE = NET_EXTPTZ_LEFT_WIDE+1; //右 + WIDE
        public static final int NET_EXTPTZ_LEFTUP_WIDE = NET_EXTPTZ_RIGHT_WIDE+1; //左上 + WIDE
        public static final int NET_EXTPTZ_LEFTDOWN_WIDE = NET_EXTPTZ_LEFTUP_WIDE+1; //左下 + WIDE
        public static final int NET_EXTPTZ_TIGHTUP_WIDE = NET_EXTPTZ_LEFTDOWN_WIDE+1; //右上 + WIDE
        public static final int NET_EXTPTZ_RIGHTDOWN_WIDE = NET_EXTPTZ_TIGHTUP_WIDE+1; //右下 + WIDE
        public static final int NET_EXTPTZ_TOTAL = NET_EXTPTZ_RIGHTDOWN_WIDE+1; //最大命令值
    }

    // 雨刷工作模式
    public static class EM_CFG_RAINBRUSHMODE_MODE extends Structure
    {
        public static final int EM_CFG_RAINBRUSHMODE_MODE_UNKNOWN = 0; //未知
        public static final int EM_CFG_RAINBRUSHMODE_MODE_MANUAL = EM_CFG_RAINBRUSHMODE_MODE_UNKNOWN+1; //手动模式
        public static final int EM_CFG_RAINBRUSHMODE_MODE_TIMING = EM_CFG_RAINBRUSHMODE_MODE_MANUAL+1; //定时模式
    }

    // 雨刷使能电平模式
    public static class EM_CFG_RAINBRUSHMODE_ENABLEMODE extends Structure
    {
        public static final int EM_CFG_RAINBRUSHMODE_ENABLEMODE_UNKNOWN = 0; //未知
        public static final int EM_CFG_RAINBRUSHMODE_ENABLEMODE_LOW = EM_CFG_RAINBRUSHMODE_ENABLEMODE_UNKNOWN+1; //低电平有效（常闭）
        public static final int EM_CFG_RAINBRUSHMODE_ENABLEMODE_HIGH = EM_CFG_RAINBRUSHMODE_ENABLEMODE_LOW+1; //高电平有效（常开）
    }

    // 雨刷模式相关配置(对应 CFG_RAINBRUSHMODE_INFO 命令)
    public static class CFG_RAINBRUSHMODE_INFO extends Structure
    {
        public int emMode;//雨刷工作模式, 取值为EM_CFG_RAINBRUSHMODE_MODE中的值
        public int emEnableMode;//雨刷使能电平模式, 取值为EM_CFG_RAINBRUSHMODE_ENABLEMODE中的值
        public int nPort;//雨刷使用的IO端口,-1表示未接入设备,-2表示该字段无效（设备未传送该字段）
    }

    public static class CFG_RAINBRUSH_INFO extends Structure
    {
        public byte bEnable;//雨刷使能, 类型为bool, 取值0或1
        public byte bSpeedRate;//雨刷速度,1:快速;2:中速;3:慢速
        public byte[] bReserved = new byte[2];//保留对齐
        public CFG_TIME_SECTION[] stuTimeSection = (CFG_TIME_SECTION[])new CFG_TIME_SECTION().toArray(WEEK_DAY_NUM*MAX_REC_TSECT);// 事件响应时间段
    }

    // 控制类型，对应CLIENT_ControlDevice接口
    // 控制类型，对应CLIENT_ControlDevice接口
    public static class CtrlType extends Structure
    {
        public static final int CTRLTYPE_CTRL_REBOOT = 0;//重启设备
        public static final int CTRLTYPE_CTRL_SHUTDOWN = CTRLTYPE_CTRL_REBOOT+1; //关闭设备
        public static final int CTRLTYPE_CTRL_DISK = CTRLTYPE_CTRL_SHUTDOWN+1; //硬盘管理
        public static final int CTRLTYPE_KEYBOARD_POWER =3;//网络键盘
        public static final int CTRLTYPE_KEYBOARD_ENTER = CTRLTYPE_KEYBOARD_POWER+1; 
        public static final int CTRLTYPE_KEYBOARD_ESC = CTRLTYPE_KEYBOARD_ENTER+1; 
        public static final int CTRLTYPE_KEYBOARD_UP = CTRLTYPE_KEYBOARD_ESC+1; 
        public static final int CTRLTYPE_KEYBOARD_DOWN = CTRLTYPE_KEYBOARD_UP+1; 
        public static final int CTRLTYPE_KEYBOARD_LEFT = CTRLTYPE_KEYBOARD_DOWN+1; 
        public static final int CTRLTYPE_KEYBOARD_RIGHT = CTRLTYPE_KEYBOARD_LEFT+1; 
        public static final int CTRLTYPE_KEYBOARD_BTN0 = CTRLTYPE_KEYBOARD_RIGHT+1; 
        public static final int CTRLTYPE_KEYBOARD_BTN1 = CTRLTYPE_KEYBOARD_BTN0+1; 
        public static final int CTRLTYPE_KEYBOARD_BTN2 = CTRLTYPE_KEYBOARD_BTN1+1; 
        public static final int CTRLTYPE_KEYBOARD_BTN3 = CTRLTYPE_KEYBOARD_BTN2+1; 
        public static final int CTRLTYPE_KEYBOARD_BTN4 = CTRLTYPE_KEYBOARD_BTN3+1; 
        public static final int CTRLTYPE_KEYBOARD_BTN5 = CTRLTYPE_KEYBOARD_BTN4+1; 
        public static final int CTRLTYPE_KEYBOARD_BTN6 = CTRLTYPE_KEYBOARD_BTN5+1; 
        public static final int CTRLTYPE_KEYBOARD_BTN7 = CTRLTYPE_KEYBOARD_BTN6+1; 
        public static final int CTRLTYPE_KEYBOARD_BTN8 = CTRLTYPE_KEYBOARD_BTN7+1; 
        public static final int CTRLTYPE_KEYBOARD_BTN9 = CTRLTYPE_KEYBOARD_BTN8+1; 
        public static final int CTRLTYPE_KEYBOARD_BTN10 = CTRLTYPE_KEYBOARD_BTN9+1; 
        public static final int CTRLTYPE_KEYBOARD_BTN11 = CTRLTYPE_KEYBOARD_BTN10+1; 
        public static final int CTRLTYPE_KEYBOARD_BTN12 = CTRLTYPE_KEYBOARD_BTN11+1; 
        public static final int CTRLTYPE_KEYBOARD_BTN13 = CTRLTYPE_KEYBOARD_BTN12+1; 
        public static final int CTRLTYPE_KEYBOARD_BTN14 = CTRLTYPE_KEYBOARD_BTN13+1; 
        public static final int CTRLTYPE_KEYBOARD_BTN15 = CTRLTYPE_KEYBOARD_BTN14+1; 
        public static final int CTRLTYPE_KEYBOARD_BTN16 = CTRLTYPE_KEYBOARD_BTN15+1; 
        public static final int CTRLTYPE_KEYBOARD_SPLIT = CTRLTYPE_KEYBOARD_BTN16+1; 
        public static final int CTRLTYPE_KEYBOARD_ONE = CTRLTYPE_KEYBOARD_SPLIT+1; 
        public static final int CTRLTYPE_KEYBOARD_NINE = CTRLTYPE_KEYBOARD_ONE+1; 
        public static final int CTRLTYPE_KEYBOARD_ADDR = CTRLTYPE_KEYBOARD_NINE+1; 
        public static final int CTRLTYPE_KEYBOARD_INFO = CTRLTYPE_KEYBOARD_ADDR+1; 
        public static final int CTRLTYPE_KEYBOARD_REC = CTRLTYPE_KEYBOARD_INFO+1; 
        public static final int CTRLTYPE_KEYBOARD_FN1 = CTRLTYPE_KEYBOARD_REC+1; 
        public static final int CTRLTYPE_KEYBOARD_FN2 = CTRLTYPE_KEYBOARD_FN1+1; 
        public static final int CTRLTYPE_KEYBOARD_PLAY = CTRLTYPE_KEYBOARD_FN2+1; 
        public static final int CTRLTYPE_KEYBOARD_STOP = CTRLTYPE_KEYBOARD_PLAY+1; 
        public static final int CTRLTYPE_KEYBOARD_SLOW = CTRLTYPE_KEYBOARD_STOP+1; 
        public static final int CTRLTYPE_KEYBOARD_FAST = CTRLTYPE_KEYBOARD_SLOW+1; 
        public static final int CTRLTYPE_KEYBOARD_PREW = CTRLTYPE_KEYBOARD_FAST+1; 
        public static final int CTRLTYPE_KEYBOARD_NEXT = CTRLTYPE_KEYBOARD_PREW+1; 
        public static final int CTRLTYPE_KEYBOARD_JMPDOWN = CTRLTYPE_KEYBOARD_NEXT+1; 
        public static final int CTRLTYPE_KEYBOARD_JMPUP = CTRLTYPE_KEYBOARD_JMPDOWN+1; 
        public static final int CTRLTYPE_KEYBOARD_10PLUS = CTRLTYPE_KEYBOARD_JMPUP+1; 
        public static final int CTRLTYPE_KEYBOARD_SHIFT = CTRLTYPE_KEYBOARD_10PLUS+1; 
        public static final int CTRLTYPE_KEYBOARD_BACK = CTRLTYPE_KEYBOARD_SHIFT+1; 
        public static final int CTRLTYPE_KEYBOARD_LOGIN = CTRLTYPE_KEYBOARD_BACK+1;//新网络键盘功能
        public static final int CTRLTYPE_KEYBOARD_CHNNEL = CTRLTYPE_KEYBOARD_LOGIN+1;//切换视频通道
        public static final int CTRLTYPE_TRIGGER_ALARM_IN =100;//触发报警输入
        public static final int CTRLTYPE_TRIGGER_ALARM_OUT = CTRLTYPE_TRIGGER_ALARM_IN+1; //触发报警输出
        public static final int CTRLTYPE_CTRL_MATRIX = CTRLTYPE_TRIGGER_ALARM_OUT+1; //矩阵控制
        public static final int CTRLTYPE_CTRL_SDCARD = CTRLTYPE_CTRL_MATRIX+1; //SD卡控制(IPC产品)参数同硬盘控制
        public static final int CTRLTYPE_BURNING_START = CTRLTYPE_CTRL_SDCARD+1; //刻录机控制，开始刻录
        public static final int CTRLTYPE_BURNING_STOP = CTRLTYPE_BURNING_START+1; //刻录机控制，结束刻录
        public static final int CTRLTYPE_BURNING_ADDPWD = CTRLTYPE_BURNING_STOP+1; //刻录机控制，叠加密码(以'\0'为结尾的字符串，最大长度8位)
        public static final int CTRLTYPE_BURNING_ADDHEAD = CTRLTYPE_BURNING_ADDPWD+1; //刻录机控制，叠加片头(以'\0'为结尾的字符串，最大长度1024字节，支持分行，行分隔符'\n')
        public static final int CTRLTYPE_BURNING_ADDSIGN = CTRLTYPE_BURNING_ADDHEAD+1; //刻录机控制，叠加打点到刻录信息(参数无)
        public static final int CTRLTYPE_BURNING_ADDCURSTOMINFO = CTRLTYPE_BURNING_ADDSIGN+1; //刻录机控制，自定义叠加(以'\0'为结尾的字符串，最大长度1024字节，支持分行，行分隔符'\n')
        public static final int CTRLTYPE_CTRL_RESTOREDEFAULT = CTRLTYPE_BURNING_ADDCURSTOMINFO+1; //恢复设备的默认设置
        public static final int CTRLTYPE_CTRL_CAPTURE_START = CTRLTYPE_CTRL_RESTOREDEFAULT+1; //触发设备抓图
        public static final int CTRLTYPE_CTRL_CLEARLOG = CTRLTYPE_CTRL_CAPTURE_START+1; //清除日志
        public static final int CTRLTYPE_TRIGGER_ALARM_WIRELESS =200;//触发无线报警(IPC产品)
        public static final int CTRLTYPE_MARK_IMPORTANT_RECORD = CTRLTYPE_TRIGGER_ALARM_WIRELESS+1; //标识重要录像文件
        public static final int CTRLTYPE_CTRL_DISK_SUBAREA = CTRLTYPE_MARK_IMPORTANT_RECORD+1; //网络硬盘分区
        public static final int CTRLTYPE_BURNING_ATTACH = CTRLTYPE_CTRL_DISK_SUBAREA+1; //刻录机控制，附件刻录.
        public static final int CTRLTYPE_BURNING_PAUSE = CTRLTYPE_BURNING_ATTACH+1; //刻录暂停
        public static final int CTRLTYPE_BURNING_CONTINUE = CTRLTYPE_BURNING_PAUSE+1; //刻录继续
        public static final int CTRLTYPE_BURNING_POSTPONE = CTRLTYPE_BURNING_CONTINUE+1; //刻录顺延
        public static final int CTRLTYPE_CTRL_OEMCTRL = CTRLTYPE_BURNING_POSTPONE+1; //报停控制
        public static final int CTRLTYPE_BACKUP_START = CTRLTYPE_CTRL_OEMCTRL+1; //设备备份开始
        public static final int CTRLTYPE_BACKUP_STOP = CTRLTYPE_BACKUP_START+1; //设备备份停止
        public static final int CTRLTYPE_VIHICLE_WIFI_ADD = CTRLTYPE_BACKUP_STOP+1; //车载手动增加WIFI配置
        public static final int CTRLTYPE_VIHICLE_WIFI_DEC = CTRLTYPE_VIHICLE_WIFI_ADD+1; //车载手动删除WIFI配置
        public static final int CTRLTYPE_BUZZER_START = CTRLTYPE_VIHICLE_WIFI_DEC+1; //蜂鸣器控制开始
        public static final int CTRLTYPE_BUZZER_STOP = CTRLTYPE_BUZZER_START+1; //蜂鸣器控制结束
        public static final int CTRLTYPE_REJECT_USER = CTRLTYPE_BUZZER_STOP+1; //剔除用户
        public static final int CTRLTYPE_SHIELD_USER = CTRLTYPE_REJECT_USER+1; //屏蔽用户
        public static final int CTRLTYPE_RAINBRUSH = CTRLTYPE_SHIELD_USER+1; //智能交通,雨刷控制
        public static final int CTRLTYPE_MANUAL_SNAP = CTRLTYPE_RAINBRUSH+1; //智能交通,手动抓拍(对应结构体MANUAL_SNAP_PARAMETER)
        public static final int CTRLTYPE_MANUAL_NTP_TIMEADJUST = CTRLTYPE_MANUAL_SNAP+1; //手动NTP校时
        public static final int CTRLTYPE_NAVIGATION_SMS = CTRLTYPE_MANUAL_NTP_TIMEADJUST+1; //导航信息和短消息
        public static final int CTRLTYPE_CTRL_ROUTE_CROSSING = CTRLTYPE_NAVIGATION_SMS+1; //路线点位信息
        public static final int CTRLTYPE_BACKUP_FORMAT = CTRLTYPE_CTRL_ROUTE_CROSSING+1; //格式化备份设备
        public static final int CTRLTYPE_DEVICE_LOCALPREVIEW_SLIPT = CTRLTYPE_BACKUP_FORMAT+1; //控制设备端本地预览分割(对应结构体DEVICE_LOCALPREVIEW_SLIPT_PARAMETER)
        public static final int CTRLTYPE_CTRL_INIT_RAID = CTRLTYPE_DEVICE_LOCALPREVIEW_SLIPT+1; //RAID初始化
        public static final int CTRLTYPE_CTRL_RAID = CTRLTYPE_CTRL_INIT_RAID+1; //RAID操作
        public static final int CTRLTYPE_CTRL_SAPREDISK = CTRLTYPE_CTRL_RAID+1; //热备盘操作
        public static final int CTRLTYPE_WIFI_CONNECT = CTRLTYPE_CTRL_SAPREDISK+1; //手动发起WIFI连接(对应结构体WIFI_CONNECT)
        public static final int CTRLTYPE_WIFI_DISCONNECT = CTRLTYPE_WIFI_CONNECT+1; //手动断开WIFI连接(对应结构体WIFI_CONNECT)
        public static final int CTRLTYPE_CTRL_ARMED = CTRLTYPE_WIFI_DISCONNECT+1; //布撤防操作
        public static final int CTRLTYPE_CTRL_IP_MODIFY = CTRLTYPE_CTRL_ARMED+1; //修改前端IP(对应结构体NET_CTRL_IPMODIFY_PARAM)
        public static final int CTRLTYPE_CTRL_WIFI_BY_WPS = CTRLTYPE_CTRL_IP_MODIFY+1; //wps连接wifi(对应结构体NET_CTRL_CONNECT_WIFI_BYWPS)
        public static final int CTRLTYPE_CTRL_FORMAT_PATITION = CTRLTYPE_CTRL_WIFI_BY_WPS+1; //格式化分区(对应结构体NET_FORMAT_PATITION)
        public static final int CTRLTYPE_CTRL_EJECT_STORAGE = CTRLTYPE_CTRL_FORMAT_PATITION+1; //手动卸载设备(对应结构体NET_EJECT_STORAGE_DEVICE)
        public static final int CTRLTYPE_CTRL_LOAD_STORAGE = CTRLTYPE_CTRL_EJECT_STORAGE+1; //手动装载设备(对应结构体NET_LOAD_STORAGE_DEVICE)
        public static final int CTRLTYPE_CTRL_CLOSE_BURNER = CTRLTYPE_CTRL_LOAD_STORAGE+1; //关闭刻录机光驱门(对应结构体NET_CTRL_BURNERDOOR)一般需要等6
        public static final int CTRLTYPE_CTRL_EJECT_BURNER = CTRLTYPE_CTRL_CLOSE_BURNER+1; //弹出刻录机光驱门(对应结构体NET_CTRL_BURNERDOOR)一般需要等4秒
        public static final int CTRLTYPE_CTRL_CLEAR_ALARM = CTRLTYPE_CTRL_EJECT_BURNER+1; //消警(对应结构体NET_CTRL_CLEAR_ALARM)
        public static final int CTRLTYPE_CTRL_MONITORWALL_TVINFO = CTRLTYPE_CTRL_CLEAR_ALARM+1; //电视墙信息显示(对应结构体NET_CTRL_MONITORWALL_TVINFO)
        public static final int CTRLTYPE_CTRL_START_VIDEO_ANALYSE = CTRLTYPE_CTRL_MONITORWALL_TVINFO+1; //开始视频智能分析(对应结构体NET_CTRL_START_VIDEO_ANALYSE)
        public static final int CTRLTYPE_CTRL_STOP_VIDEO_ANALYSE = CTRLTYPE_CTRL_START_VIDEO_ANALYSE+1; //停止视频智能分析(对应结构体NET_CTRL_STOP_VIDEO_ANALYSE)
        public static final int CTRLTYPE_CTRL_UPGRADE_DEVICE = CTRLTYPE_CTRL_STOP_VIDEO_ANALYSE+1; //控制启动设备升级,由设备独立完成升级过程,不需要传输升级文件
        public static final int CTRLTYPE_CTRL_MULTIPLAYBACK_CHANNALES = CTRLTYPE_CTRL_UPGRADE_DEVICE+1; //切换多通道预览回放的通道(对应结构体NET_CTRL_MULTIPLAYBACK_CHANNALES)
        public static final int CTRLTYPE_CTRL_SEQPOWER_OPEN = CTRLTYPE_CTRL_MULTIPLAYBACK_CHANNALES+1; //电源时序器打开开关量输出口(对应NET_CTRL_SEQPOWER_PARAM)
        public static final int CTRLTYPE_CTRL_SEQPOWER_CLOSE = CTRLTYPE_CTRL_SEQPOWER_OPEN+1; //电源时序器关闭开关量输出口(对应NET_CTRL_SEQPOWER_PARAM)
        public static final int CTRLTYPE_CTRL_SEQPOWER_OPEN_ALL = CTRLTYPE_CTRL_SEQPOWER_CLOSE+1; //电源时序器打开开关量输出口组(对应NET_CTRL_SEQPOWER_PARAM)
        public static final int CTRLTYPE_CTRL_SEQPOWER_CLOSE_ALL = CTRLTYPE_CTRL_SEQPOWER_OPEN_ALL+1; //电源时序器关闭开关量输出口组(对应NET_CTRL_SEQPOWER_PARAM)
        public static final int CTRLTYPE_CTRL_PROJECTOR_RISE = CTRLTYPE_CTRL_SEQPOWER_CLOSE_ALL+1; //投影仪上升(对应NET_CTRL_PROJECTOR_PARAM)
        public static final int CTRLTYPE_CTRL_PROJECTOR_FALL = CTRLTYPE_CTRL_PROJECTOR_RISE+1; //投影仪下降(对应NET_CTRL_PROJECTOR_PARAM)
        public static final int CTRLTYPE_CTRL_PROJECTOR_STOP = CTRLTYPE_CTRL_PROJECTOR_FALL+1; //投影仪停止(对应NET_CTRL_PROJECTOR_PARAM)
        public static final int CTRLTYPE_CTRL_INFRARED_KEY = CTRLTYPE_CTRL_PROJECTOR_STOP+1; //红外按键(对应NET_CTRL_INFRARED_KEY_PARAM)
        public static final int CTRLTYPE_CTRL_START_PLAYAUDIO = CTRLTYPE_CTRL_INFRARED_KEY+1; //设备开始播放音频文件(对应结构体NET_CTRL_START_PLAYAUDIO)
        public static final int CTRLTYPE_CTRL_STOP_PLAYAUDIO = CTRLTYPE_CTRL_START_PLAYAUDIO+1; //设备停止播放音频文件
        public static final int CTRLTYPE_CTRL_START_ALARMBELL = CTRLTYPE_CTRL_STOP_PLAYAUDIO+1; //开启警号(对应结构体NET_CTRL_ALARMBELL)
        public static final int CTRLTYPE_CTRL_STOP_ALARMBELL = CTRLTYPE_CTRL_START_ALARMBELL+1; //关闭警号(对应结构体NET_CTRL_ALARMBELL)
        public static final int CTRLTYPE_CTRL_ACCESS_OPEN = CTRLTYPE_CTRL_STOP_ALARMBELL+1; //门禁控制-开门(对应结构体NET_CTRL_ACCESS_OPEN)
        public static final int CTRLTYPE_CTRL_SET_BYPASS = CTRLTYPE_CTRL_ACCESS_OPEN+1; //设置旁路功能(对应结构体NET_CTRL_SET_BYPASS)
        public static final int CTRLTYPE_CTRL_RECORDSET_INSERT = CTRLTYPE_CTRL_SET_BYPASS+1; //添加记录，获得记录集编号(对应NET_CTRL_RECORDSET_INSERT_PARAM)
        public static final int CTRLTYPE_CTRL_RECORDSET_UPDATE = CTRLTYPE_CTRL_RECORDSET_INSERT+1; //更新某记录集编号的记录(对应NET_CTRL_RECORDSET_PARAM)
        public static final int CTRLTYPE_CTRL_RECORDSET_REMOVE = CTRLTYPE_CTRL_RECORDSET_UPDATE+1; //根据记录集编号删除某记录(对应NET_CTRL_RECORDSET_PARAM)
        public static final int CTRLTYPE_CTRL_RECORDSET_CLEAR = CTRLTYPE_CTRL_RECORDSET_REMOVE+1; //清除所有记录集信息(对应NET_CTRL_RECORDSET_PARAM)
        public static final int CTRLTYPE_CTRL_ACCESS_CLOSE = CTRLTYPE_CTRL_RECORDSET_CLEAR+1; //门禁控制-关门(对应结构体NET_CTRL_ACCESS_CLOSE)
        public static final int CTRLTYPE_CTRL_ALARM_SUBSYSTEM_ACTIVE_SET = CTRLTYPE_CTRL_ACCESS_CLOSE+1; //报警子系统激活设置(对应结构体NET_CTRL_ALARM_SUBSYSTEM_SETACTIVE)
        public static final int CTRLTYPE_CTRL_FORBID_OPEN_STROBE = CTRLTYPE_CTRL_ALARM_SUBSYSTEM_ACTIVE_SET+1; //禁止设备端开闸(对应结构体NET_CTRL_FORBID_OPEN_STROBE)
        public static final int CTRLTYPE_CTRL_OPEN_STROBE = CTRLTYPE_CTRL_FORBID_OPEN_STROBE+1; //开启道闸(对应结构体 NET_CTRL_OPEN_STROBE)
        public static final int CTRLTYPE_CTRL_TALKING_REFUSE = CTRLTYPE_CTRL_OPEN_STROBE+1; //对讲拒绝接听(对应结构体NET_CTRL_TALKING_REFUSE)
        public static final int CTRLTYPE_CTRL_ARMED_EX = CTRLTYPE_CTRL_TALKING_REFUSE+1; //布撤防操作(对应结构体CTRL_ARM_DISARM_PARAM_EX),对CTRL_ARM_DISARM_PARAM升级，建议用这个
        public static final int CTRLTYPE_CTRL_NET_KEYBOARD =400;//网络键盘控制(对应结构体NET_CTRL_NET_KEYBOARD)
        public static final int CTRLTYPE_CTRL_AIRCONDITION_OPEN = CTRLTYPE_CTRL_NET_KEYBOARD+1; //打开空调(对应结构体NET_CTRL_OPEN_AIRCONDITION)
        public static final int CTRLTYPE_CTRL_AIRCONDITION_CLOSE = CTRLTYPE_CTRL_AIRCONDITION_OPEN+1; //关闭空调(对应结构体NET_CTRL_CLOSE_AIRCONDITION)
        public static final int CTRLTYPE_CTRL_AIRCONDITION_SET_TEMPERATURE = CTRLTYPE_CTRL_AIRCONDITION_CLOSE+1; //设定空调温度(对应结构体NET_CTRL_SET_TEMPERATURE)
        public static final int CTRLTYPE_CTRL_AIRCONDITION_ADJUST_TEMPERATURE = CTRLTYPE_CTRL_AIRCONDITION_SET_TEMPERATURE+1; //调节空调温度(对应结构体NET_CTRL_ADJUST_TEMPERATURE)
        public static final int CTRLTYPE_CTRL_AIRCONDITION_SETMODE = CTRLTYPE_CTRL_AIRCONDITION_ADJUST_TEMPERATURE+1; //设置空调工作模式(对应结构体NET_CTRL_ADJUST_TEMPERATURE)
        public static final int CTRLTYPE_CTRL_AIRCONDITION_SETWINDMODE = CTRLTYPE_CTRL_AIRCONDITION_SETMODE+1; //设置空调送风模式(对应结构体NET_CTRL_AIRCONDITION_SETMODE)
        public static final int CTRLTYPE_CTRL_RESTOREDEFAULT_EX  = CTRLTYPE_CTRL_AIRCONDITION_SETWINDMODE+1;//恢复设备的默认设置新协议(对应结构体NET_CTRL_RESTORE_DEFAULT)
                                                                                                  // 恢复配置优先使用该枚举，如果接口失败，
                                                                                                  // 且CLIENT_GetLastError返回NET_UNSUPPORTED,再尝试使用NET_CTRL_RESTOREDEFAULT恢复配置
        public static final int CTRLTYPE_CTRL_NOTIFY_EVENT = CTRLTYPE_CTRL_RESTOREDEFAULT_EX+1; //向设备发送事件(对应结构体NET_NOTIFY_EVENT_DATA)
        public static final int CTRLTYPE_CTRL_SILENT_ALARM_SET = CTRLTYPE_CTRL_NOTIFY_EVENT+1; //无声报警设置
        public static final int CTRLTYPE_CTRL_START_PLAYAUDIOEX = CTRLTYPE_CTRL_SILENT_ALARM_SET+1; //设备开始语音播报(对应结构体NET_CTRL_START_PLAYAUDIOEX)
        public static final int CTRLTYPE_CTRL_STOP_PLAYAUDIOEX = CTRLTYPE_CTRL_START_PLAYAUDIOEX+1; //设备停止语音播报
        public static final int CTRLTYPE_CTRL_CLOSE_STROBE = CTRLTYPE_CTRL_STOP_PLAYAUDIOEX+1; //关闭道闸(对应结构体 NET_CTRL_CLOSE_STROBE)
        public static final int CTRLTYPE_CTRL_SET_ORDER_STATE = CTRLTYPE_CTRL_CLOSE_STROBE+1; //设置车位预定状态(对应结构体NET_CTRL_SET_ORDER_STATE)
        public static final int CTRLTYPE_CTRL_RECORDSET_INSERTEX = CTRLTYPE_CTRL_SET_ORDER_STATE+1; //添加记录，获得记录集编号(对应NET_CTRL_RECORDSET_INSERT_PARAM)
        public static final int CTRLTYPE_CTRL_RECORDSET_UPDATEEX = CTRLTYPE_CTRL_RECORDSET_INSERTEX+1; //更新某记录集编号的记录(对应NET_CTRL_RECORDSET_PARAM)
        public static final int CTRLTYPE_CTRL_CAPTURE_FINGER_PRINT = CTRLTYPE_CTRL_RECORDSET_UPDATEEX+1; //指纹采集(对应结构体NET_CTRL_CAPTURE_FINGER_PRINT)
        public static final int CTRLTYPE_CTRL_ECK_LED_SET = CTRLTYPE_CTRL_CAPTURE_FINGER_PRINT+1; //停车场出入口控制器LED设置(对应结构体NET_CTRL_ECK_LED_SET_PARAM)
        public static final int CTRLTYPE_CTRL_ECK_IC_CARD_IMPORT = CTRLTYPE_CTRL_ECK_LED_SET+1; //智能停车系统出入口机IC卡信息导入(对应结构体NET_CTRL_ECK_IC_CARD_IMPORT_PARAM)
        public static final int CTRLTYPE_CTRL_ECK_SYNC_IC_CARD = CTRLTYPE_CTRL_ECK_IC_CARD_IMPORT+1; //智能停车系统出入口机IC卡信息同步指令，收到此指令后，设备删除原有IC卡信息(对应结构体NET_CTRL_ECK_SYNC_IC_CARD_PARAM)
        public static final int CTRLTYPE_CTRL_LOWRATEWPAN_REMOVE = CTRLTYPE_CTRL_ECK_SYNC_IC_CARD+1; //删除指定无线设备(对应结构体NET_CTRL_LOWRATEWPAN_REMOVE)
        public static final int CTRLTYPE_CTRL_LOWRATEWPAN_MODIFY = CTRLTYPE_CTRL_LOWRATEWPAN_REMOVE+1; //修改无线设备信息(对应结构体NET_CTRL_LOWRATEWPAN_MODIFY)
        public static final int CTRLTYPE_CTRL_ECK_SET_PARK_INFO = CTRLTYPE_CTRL_LOWRATEWPAN_MODIFY+1; //智能停车系统出入口机设置车位信息(对应结构体NET_CTRL_ECK_SET_PARK_INFO_PARAM)
        public static final int CTRLTYPE_CTRL_VTP_DISCONNECT = CTRLTYPE_CTRL_ECK_SET_PARK_INFO+1; //挂断视频电话(对应结构体NET_CTRL_VTP_DISCONNECT)
        public static final int CTRLTYPE_CTRL_UPDATE_FILES = CTRLTYPE_CTRL_VTP_DISCONNECT+1; //远程投放多媒体文件更新(对应结构体NET_CTRL_UPDATE_FILES)
        public static final int CTRLTYPE_CTRL_MATRIX_SAVE_SWITCH = CTRLTYPE_CTRL_UPDATE_FILES+1; //保存上下位矩阵输出关系(对应结构体NET_CTRL_MATRIX_SAVE_SWITCH)
        public static final int CTRLTYPE_CTRL_MATRIX_RESTORE_SWITCH = CTRLTYPE_CTRL_MATRIX_SAVE_SWITCH+1; //恢复上下位矩阵输出关系(对应结构体NET_CTRL_MATRIX_RESTORE_SWITCH)
        public static final int CTRLTYPE_CTRL_VTP_DIVERTACK = CTRLTYPE_CTRL_MATRIX_RESTORE_SWITCH+1; //呼叫转发响应(对应结构体NET_CTRL_VTP_DIVERTACK)
        public static final int CTRLTYPE_CTRL_RAINBRUSH_MOVEONCE = CTRLTYPE_CTRL_VTP_DIVERTACK+1; //雨刷来回刷一次，雨刷模式配置为手动模式时有效(对应结构体NET_CTRL_RAINBRUSH_MOVEONCE)
        public static final int CTRLTYPE_CTRL_RAINBRUSH_MOVECONTINUOUSLY = CTRLTYPE_CTRL_RAINBRUSH_MOVEONCE+1; //雨刷来回循环刷，雨刷模式配置为手动模式时有效(对应结构体NET_CTRL_RAINBRUSH_MOVECONTINUOUSLY)
        public static final int CTRLTYPE_CTRL_RAINBRUSH_STOPMOVE = CTRLTYPE_CTRL_RAINBRUSH_MOVECONTINUOUSLY+1; //雨刷停止刷，雨刷模式配置为手动模式时有效(对应结构体NET_CTRL_RAINBRUSH_STOPMOVE)
        public static final int CTRLTYPE_CTRL_ALARM_ACK = CTRLTYPE_CTRL_RAINBRUSH_STOPMOVE+1; //报警事件确认(对应结构体NET_CTRL_ALARM_ACK)
                                                                                    // NET_CTRL_ALARM_ACK 该操作切勿在报警回调接口中调用
                                                                                    // 以下命令只在 CLIENT_ControlDeviceEx 上有效
        public static final int CTRLTYPE_CTRL_THERMO_GRAPHY_ENSHUTTER = 0x10000;//设置热成像快门启用/禁用,pInBuf= NET_IN_THERMO_EN_SHUTTER*, pOutBuf= NET_OUT_THERMO_EN_SHUTTER * 
        public static final int CTRLTYPE_CTRL_RADIOMETRY_SETOSDMARK = CTRLTYPE_CTRL_THERMO_GRAPHY_ENSHUTTER+1; //设置测温项的osd为高亮,pInBuf=NET_IN_RADIOMETRY_SETOSDMARK*,pOutBuf= NET_OUT_RADIOMETRY_SETOSDMARK * 
        public static final int CTRLTYPE_CTRL_AUDIO_REC_START_NAME = CTRLTYPE_CTRL_RADIOMETRY_SETOSDMARK+1; //开启音频录音并得到录音名,pInBuf = NET_IN_AUDIO_REC_MNG_NAME *, pOutBuf = NET_OUT_AUDIO_REC_MNG_NAME *
        public static final int CTRLTYPE_CTRL_AUDIO_REC_STOP_NAME = CTRLTYPE_CTRL_AUDIO_REC_START_NAME+1; //关闭音频录音并返回文件名称,pInBuf = NET_IN_AUDIO_REC_MNG_NAME *, pOutBuf = NET_OUT_AUDIO_REC_MNG_NAME *
        public static final int CTRLTYPE_CTRL_SNAP_MNG_SNAP_SHOT = CTRLTYPE_CTRL_AUDIO_REC_STOP_NAME+1; //即时抓图(又名手动抓图),pInBuf  =NET_IN_SNAP_MNG_SHOT *, pOutBuf = NET_OUT_SNAP_MNG_SHOT *
        public static final int CTRLTYPE_CTRL_LOG_STOP = CTRLTYPE_CTRL_SNAP_MNG_SNAP_SHOT+1; //强制同步缓存数据到数据库并关闭数据库,pInBuf = NET_IN_LOG_MNG_CTRL *, pOutBuf = NET_OUT_LOG_MNG_CTRL *
        public static final int CTRLTYPE_CTRL_LOG_RESUME = CTRLTYPE_CTRL_LOG_STOP+1; //恢复数据库,pInBuf = NET_IN_LOG_MNG_CTRL *, pOutBuf = NET_OUT_LOG_MNG_CTRL *
    }

    // 视频压缩格式
    public static class CFG_VIDEO_COMPRESSION extends Structure
    {
        public static final int VIDEO_FORMAT_MPEG4 = 0; //MPEG4
        public static final int VIDEO_FORMAT_MS_MPEG4 = VIDEO_FORMAT_MPEG4+1; //MS-MPEG4
        public static final int VIDEO_FORMAT_MPEG2 = VIDEO_FORMAT_MS_MPEG4+1; //MPEG2
        public static final int VIDEO_FORMAT_MPEG1 = VIDEO_FORMAT_MPEG2+1; //MPEG1
        public static final int VIDEO_FORMAT_H263 = VIDEO_FORMAT_MPEG1+1; //H.263
        public static final int VIDEO_FORMAT_MJPG = VIDEO_FORMAT_H263+1; //MJPG
        public static final int VIDEO_FORMAT_FCC_MPEG4 = VIDEO_FORMAT_MJPG+1; //FCC-MPEG4
        public static final int VIDEO_FORMAT_H264 = VIDEO_FORMAT_FCC_MPEG4+1; //H.264
        public static final int VIDEO_FORMAT_H265 = VIDEO_FORMAT_H264+1; //H.265
    }

    // 码流控制模式
    public static class CFG_BITRATE_CONTROL extends Structure
    {
        public static final int BITRATE_CBR = 0;              //固定码流
        public static final int BITRATE_VBR = BITRATE_CBR+1; //可变码流
    }

    // H264 编码级别
    public static class CFG_H264_PROFILE_RANK extends Structure
    {
        public static final int PROFILE_BASELINE = 1;//提供I/P帧，仅支持progressive(逐行扫描)和CAVLC
        public static final int PROFILE_MAIN = PROFILE_BASELINE+1; //提供I/P/B帧，支持progressiv和interlaced，提供CAVLC或CABAC
        public static final int PROFILE_EXTENDED = PROFILE_MAIN+1; //提供I/P/B/SP/SI帧，仅支持progressive(逐行扫描)和CAVLC
        public static final int PROFILE_HIGH = PROFILE_EXTENDED+1; //即FRExt，Main_Profile基础上新增：8x8intraprediction(8x8帧内预测), custom 
                                                                   // quant(自定义量化), lossless video coding(无损视频编码), 更多的yuv格式
    }

    // 画质
    public static class CFG_IMAGE_QUALITY extends Structure
    {
        public static final int IMAGE_QUALITY_Q10 = 1;//图像质量10%
        public static final int IMAGE_QUALITY_Q30 = IMAGE_QUALITY_Q10+1; //图像质量30%
        public static final int IMAGE_QUALITY_Q50 = IMAGE_QUALITY_Q30+1; //图像质量50%
        public static final int IMAGE_QUALITY_Q60 = IMAGE_QUALITY_Q50+1; //图像质量60%
        public static final int IMAGE_QUALITY_Q80 = IMAGE_QUALITY_Q60+1; //图像质量80%
        public static final int IMAGE_QUALITY_Q100 = IMAGE_QUALITY_Q80+1; //图像质量100%
    }

    // 视频格式
    public static class CFG_VIDEO_FORMAT extends Structure
    {
        // 能力
        public byte abCompression;// 类型为bool, 取值0或1
        public byte abWidth;// 类型为bool, 取值0或1
        public byte abHeight;// 类型为bool, 取值0或1
        public byte abBitRateControl;// 类型为bool, 取值0或1
        public byte abBitRate;// 类型为bool, 取值0或1
        public byte abFrameRate;// 类型为bool, 取值0或1
        public byte abIFrameInterval;// 类型为bool, 取值0或1
        public byte abImageQuality;// 类型为bool, 取值0或1
        public byte abFrameType;// 类型为bool, 取值0或1
        public byte abProfile;// 类型为bool, 取值0或1
        // 信息
        public int emCompression;//视频压缩格式, 取值为CFG_VIDEO_COMPRESSION中的值
        public int nWidth;//视频宽度
        public int nHeight;//视频高度
        public int emBitRateControl;//码流控制模式, 取值为CFG_BITRATE_CONTROL中的值
        public int nBitRate;//视频码流(kbps)
        public float nFrameRate;//视频帧率
        public int nIFrameInterval;//I帧间隔(1-100)，比如50表示每49个B帧或P帧，设置一个I帧。
        public int emImageQuality;//图像质量, 取值为CFG_IMAGE_QUALITY中的值
        public int nFrameType;//打包模式，0－DHAV，1－"PS"
        public int emProfile;//H.264编码级别, 取值为CFG_H264_PROFILE_RANK中的值
    }

    // 音频编码模式
    public static class CFG_AUDIO_FORMAT extends Structure
    {
        public static final int  AUDIO_FORMAT_G711A = 0; //G711a
        public static final int  AUDIO_FORMAT_PCM = AUDIO_FORMAT_G711A+1; //PCM
        public static final int  AUDIO_FORMAT_G711U = AUDIO_FORMAT_PCM+1; //G711u
        public static final int  AUDIO_FORMAT_AMR = AUDIO_FORMAT_G711U+1; //AMR
        public static final int  AUDIO_FORMAT_AAC = AUDIO_FORMAT_AMR+1; //AAC
    }

    // 音频格式
    public static class CFG_AUDIO_ENCODE_FORMAT extends Structure
    {
        // 能力
        public byte abCompression;// 类型为bool, 取值0或1
        public byte abDepth;// 类型为bool, 取值0或1
        public byte abFrequency;// 类型为bool, 取值0或1
        public byte abMode;// 类型为bool, 取值0或1
        public byte abFrameType;// 类型为bool, 取值0或1
        public byte abPacketPeriod;// 类型为bool, 取值0或1
        // 信息
        public int emCompression;//音频压缩模式，取值为CFG_AUDIO_FORMAT中的值
        public int nDepth;//音频采样深度
        public int nFrequency;//音频采样频率
        public int nMode;//音频编码模式
        public int nFrameType;//音频打包模式,0-DHAV,1-PS
        public int nPacketPeriod;//音频打包周期,ms
    }

    // 视频编码参数
    public static class CFG_VIDEOENC_OPT extends Structure
    {
        // 能力
        public byte abVideoEnable;// 类型为bool, 取值0或1
        public byte abAudioEnable;// 类型为bool, 取值0或1
        public byte abSnapEnable;// 类型为bool, 取值0或1
        public byte abAudioAdd;//音频叠加能力, 类型为bool, 取值0或1
        public byte abAudioFormat;// 类型为bool, 取值0或1
        // 信息
        public int bVideoEnable;//视频使能, 类型为BOOL, 取值0或者1
        public CFG_VIDEO_FORMAT stuVideoFormat;//视频格式
        public int bAudioEnable;//音频使能, 类型为BOOL, 取值0或者1
        public int bSnapEnable;//定时抓图使能, 类型为BOOL, 取值0或者1
        public int bAudioAddEnable;//音频叠加使能, 类型为BOOL, 取值0或者1
        public CFG_AUDIO_ENCODE_FORMAT stuAudioFormat;//音频格式
    }

    // 遮挡信息
    public static class CFG_COVER_INFO extends Structure
    {
        // 能力
        public byte abBlockType;// 类型为bool, 取值0或1
        public byte abEncodeBlend;// 类型为bool, 取值0或1
        public byte abPreviewBlend;// 类型为bool, 取值0或1
        // 信息
        public CFG_RECT stuRect = new CFG_RECT();//覆盖的区域坐标
        public CFG_RGBA stuColor = new CFG_RGBA();//覆盖的颜色
        public int nBlockType;//覆盖方式；0－黑块，1－马赛克
        public int nEncodeBlend;//编码级遮挡；1－生效，0－不生效
        public int nPreviewBlend;//预览遮挡；1－生效，0－不生效
    }

    // 多区域遮挡配置
    public static class CFG_VIDEO_COVER extends Structure
    {
        public int nTotalBlocks;//支持的遮挡块数
        public int nCurBlocks;//已设置的块数
        public CFG_COVER_INFO[] stuCoverBlock = (CFG_COVER_INFO[])new CFG_COVER_INFO().toArray(MAX_VIDEO_COVER_NUM);// 覆盖的区域    
    }

    // OSD信息
    public static class CFG_OSD_INFO extends Structure
    {
        // 能力
        public byte abShowEnable;// 类型为bool, 取值0或1
        // 信息
        public CFG_RGBA stuFrontColor = new CFG_RGBA();//前景颜色
        public CFG_RGBA stuBackColor = new CFG_RGBA();//背景颜色
        public CFG_RECT stuRect = new CFG_RECT();//矩形区域
        public int bShowEnable;//显示使能, 类型为BOOL, 取值0或者1
    }

    // 画面颜色属性
    public static class CFG_COLOR_INFO extends Structure
    {
        public int nBrightness;//亮度(0-100)
        public int nContrast;//对比度(0-100)
        public int nSaturation;//饱和度(0-100)
        public int nHue;//色度(0-100)
        public int nGain;//增益(0-100)
        public int bGainEn;//增益使能, 类型为BOOL, 取值0或者1
    }

    // 图像通道属性信息
    public static class CFG_ENCODE_INFO extends Structure
    {
        public int nChannelID;//通道号(0开始),获取时，该字段有效；设置时，该字段无效
        public byte[] szChnName = new byte[MAX_CHANNELNAME_LEN];//无效字段
        public CFG_VIDEOENC_OPT[] stuMainStream = (CFG_VIDEOENC_OPT[])new CFG_VIDEOENC_OPT().toArray(MAX_VIDEOSTREAM_NUM);    // 主码流，0－普通录像，1-动检录像，2－报警录像
        public CFG_VIDEOENC_OPT[] stuExtraStream = (CFG_VIDEOENC_OPT[])new CFG_VIDEOENC_OPT().toArray(MAX_VIDEOSTREAM_NUM);    // 辅码流，0－辅码流1，1－辅码流2，2－辅码流3
        public CFG_VIDEOENC_OPT[] stuSnapFormat = (CFG_VIDEOENC_OPT[])new CFG_VIDEOENC_OPT().toArray(MAX_VIDEOSTREAM_NUM);    // 抓图，0－普通抓图，1－动检抓图，2－报警抓图
        public int dwCoverAbilityMask;//无效字段
        public int dwCoverEnableMask;//无效字段
        public CFG_VIDEO_COVER stuVideoCover;//无效字段
        public CFG_OSD_INFO stuChnTitle;//无效字段
        public CFG_OSD_INFO stuTimeTitle;//无效字段
        public CFG_COLOR_INFO stuVideoColor;//无效字段
        public int emAudioFormat;//无效字段, 取值为CFG_AUDIO_FORMAT中的值
        public int nProtocolVer;//协议版本号,只读,获取时，该字段有效；设置时，该字段无效
    }

    // 设备软件版本信息,高16位表示主版本号,低16位表示次版本号
    public static class NET_VERSION_INFO extends Structure
    {
        public int dwSoftwareVersion;
        public int dwSoftwareBuildDate;
        public int dwDspSoftwareVersion;
        public int dwDspSoftwareBuildDate;
        public int dwPanelVersion;
        public int dwPanelSoftwareBuildDate;
        public int dwHardwareVersion;
        public int dwHardwareDate;
        public int dwWebVersion;
        public int dwWebBuildDate;
    }

    // DSP能力描述,对应CLIENT_GetDevConfig接口
    public static class NET_DSP_ENCODECAP extends Structure
    {
        public int dwVideoStandardMask;//视频制式掩码,按位表示设备能够支持的视频制式
        public int dwImageSizeMask;//分辨率掩码,按位表示设备能够支持的分辨率设置
        public int dwEncodeModeMask;//编码模式掩码,按位表示设备能够支持的编码模式设置
        public int dwStreamCap;    // 按位表示设备支持的多媒体功能,
                                // 第一位表示支持主码流
                                // 第二位表示支持辅码流1
                                // 第三位表示支持辅码流2
                                // 第五位表示支持jpg抓图
        public int[] dwImageSizeMask_Assi = new int[8];//表示主码流为各分辨率时,支持的辅码流分辨率掩码。
        public int dwMaxEncodePower;//DSP支持的最高编码能力
        public short wMaxSupportChannel;//每块DSP支持最多输入视频通道数
        public short wChannelMaxSetSync;//DSP每通道的最大编码设置是否同步；0：不同步,1：同步
    }

    // 系统信息
    public static class NET_DEV_SYSTEM_ATTR_CFG extends Structure
    {
        public int dwSize;
        /* 下面是设备的只读部分 */
        public NET_VERSION_INFO stVersion;
        public NET_DSP_ENCODECAP stDspEncodeCap;//DSP能力描述
        public byte[] szDevSerialNo = new byte[NET_DEV_SERIALNO_LEN];//序列号
        public byte byDevType;//设备类型,见枚举NET_DEVICE_TYPE
        public byte[] szDevType = new byte[NET_DEV_TYPE_LEN];//设备详细型号,字符串格式,可能为空
        public byte byVideoCaptureNum;//视频口数量
        public byte byAudioCaptureNum;//音频口数量
        public byte byTalkInChanNum;//对讲输入接口数量
        public byte byTalkOutChanNum;//对讲输出接口数量
        public byte byDecodeChanNum;//NSP
        public byte byAlarmInNum;//报警输入口数
        public byte byAlarmOutNum;//报警输出口数
        public byte byNetIONum;//网络口数
        public byte byUsbIONum;//USB口数量
        public byte byIdeIONum;//IDE数量
        public byte byComIONum;//串口数量
        public byte byLPTIONum;//并口数量
        public byte byVgaIONum;//NSP
        public byte byIdeControlNum;//NSP
        public byte byIdeControlType;//NSP
        public byte byCapability;//NSP,扩展描述
        public byte byMatrixOutNum;//视频矩阵输出口数
        /* 下面是设备的可写部分 */
        public byte byOverWrite;//硬盘满处理方式(覆盖、停止)
        public byte byRecordLen;//录象打包长度
        public byte byDSTEnable;//是否实行夏令时1-实行0-不实行
        public short wDevNo;//设备编号,用于遥控
        public byte byVideoStandard;//视频制式:0-PAL,1-NTSC
        public byte byDateFormat;//日期格式
        public byte byDateSprtr;//日期分割符(0：".",1："-",2："/")
        public byte byTimeFmt;//时间格式(0-24小时,1－12小时)
        public byte byLanguage;//枚举值详见NET_LANGUAGE_TYPE

        public NET_DEV_SYSTEM_ATTR_CFG()
        {
            this.dwSize = this.size();
        }
    }

    // 入侵方向
    public static class EM_MSG_OBJ_PERSON_DIRECTION extends Structure
    {
        public static final int EM_MSG_OBJ_PERSON_DIRECTION_UNKOWN = 0; //未知方向
        public static final int EM_MSG_OBJ_PERSON_DIRECTION_LEFT_TO_RIGHT = EM_MSG_OBJ_PERSON_DIRECTION_UNKOWN+1; //从左向右
        public static final int EM_MSG_OBJ_PERSON_DIRECTION_RIGHT_TO_LEFT = EM_MSG_OBJ_PERSON_DIRECTION_LEFT_TO_RIGHT+1; //从右向左
    }

    // 视频分析物体信息扩展结构体
    public static class NET_MSG_OBJECT_EX extends Structure
    {
        public int dwSize;
        public int nObjectID;//物体ID,每个ID表示一个唯一的物体
        public byte[] szObjectType = new byte[128];//物体类型
        public int nConfidence;//置信度(0~255),值越大表示置信度越高
        public int nAction;//物体动作:1:Appear2:Move3:Stay 4:Remove 5:Disappear 6:Split 7:Merge 8:Rename
        public NET_RECT BoundingBox;//包围盒
        public NET_POINT Center;//物体型心
        public int nPolygonNum;//多边形顶点个数
        public NET_POINT[] Contour = (NET_POINT[])new NET_POINT().toArray(NET_MAX_POLYGON_NUM);// 较精确的轮廓多边形
        public int rgbaMainColor;//表示车牌、车身等物体主要颜色；按字节表示,分别为红、绿、蓝和透明度,例如:RGB值为(0,255,0),透明度为0时,其值为0x00ff0000.
        public byte[] szText = new byte[128];//同NET_MSG_OBJECT相应字段
        public byte[] szObjectSubType = new byte[64];//物体子类别,根据不同的物体类型,可以取以下子类型：
        // 同NET_MSG_OBJECT相应字段
        public byte[] byReserved1 = new byte[3];
        public byte bPicEnble;//是否有物体对应图片文件信息, 类型为bool, 取值0或1
        public NET_PIC_INFO stPicInfo;//物体对应图片信息
        public byte bShotFrame;//是否是抓拍张的识别结果, 类型为bool, 取值0或1
        public byte bColor;//物体颜色(rgbaMainColor)是否可用, 类型为bool, 取值0或1
        public byte bLowerBodyColor;//下半身颜色(rgbaLowerBodyColor)是否可用
        public byte byTimeType;//时间表示类型,详见EM_TIME_TYPE说明
        public NET_TIME_EX stuCurrentTime;//针对视频浓缩,当前时间戳（物体抓拍或识别时,会将此识别智能帧附在一个视频帧或jpeg图片中,此帧所在原始视频中的出现时间）
        public NET_TIME_EX stuStartTime;//开始时间戳（物体开始出现时）
        public NET_TIME_EX stuEndTime;//结束时间戳（物体最后出现时）
        public NET_RECT stuOriginalBoundingBox;//包围盒(绝对坐标)
        public NET_RECT stuSignBoundingBox;//车标坐标包围盒
        public int dwCurrentSequence;//当前帧序号（抓下这个物体时的帧）
        public int dwBeginSequence;//开始帧序号（物体开始出现时的帧序号）
        public int dwEndSequence;//结束帧序号（物体消逝时的帧序号）
        public long nBeginFileOffset;//开始时文件偏移,单位:字节（物体开始出现时,视频帧在原始视频文件中相对于文件起始处的偏移）
        public long nEndFileOffset;//结束时文件偏移,单位:字节（物体消逝时,视频帧在原始视频文件中相对于文件起始处的偏移）
        public byte[] byColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX];//物体颜色相似度,取值范围：0-100,数组下标值代表某种颜色,详见EM_COLOR_TYPE
        public byte[] byUpperBodyColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX];//上半身物体颜色相似度(物体类型为人时有效)
        public byte[] byLowerBodyColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX];//下半身物体颜色相似度(物体类型为人时有效)
        public int nRelativeID;//相关物体ID
        public byte[] szSubText = new byte[20];//"ObjectType"为"Vehicle"或者"Logo"时,表示车标下的某一车系,比如奥迪A6L,由于车系较多,SDK实现时透传此字段,设备如实填写。
        public int nPersonStature;//入侵人员身高,单位cm
        public int emPersonDirection;//人员入侵方向, 取值为EM_MSG_OBJ_PERSON_DIRECTION中的值
        public int rgbaLowerBodyColor;//使用方法同rgbaMainColor,物体类型为人时有效

        public NET_MSG_OBJECT_EX()
        {
            this.dwSize = this.size();
            
            // 强制采用最大四字节对其
            setAlignType(ALIGN_GNUC);
        }
    }
    
    // 视频分析物体信息扩展结构体,扩展版本2
    public static class NET_MSG_OBJECT_EX2 extends Structure
    {
        public int dwSize;
        public int nObjectID;//物体ID,每个ID表示一个唯一的物体
        public byte[] szObjectType = new byte[128];//物体类型
        public int nConfidence;//置信度(0~255),值越大表示置信度越高
        public int nAction;//物体动作:1:Appear2:Move3:Stay 4:Remove 5:Disappear 6:Split 7:Merge 8:Rename
        public NET_RECT BoundingBox;//包围盒
        public NET_POINT Center;//物体型心
        public int nPolygonNum;//多边形顶点个数
        public NET_POINT[] Contour = (NET_POINT[])new NET_POINT().toArray(NET_MAX_POLYGON_NUM);//较精确的轮廓多边形
        public int rgbaMainColor;//表示车牌、车身等物体主要颜色；按字节表示,分别为红、绿、蓝和透明度,例如:RGB值为(0,255,0),透明度为0时,其值为0x00ff0000.
        public byte[] szText = new byte[128];//同NET_MSG_OBJECT相应字段
        public byte[] szObjectSubType = new byte[64];//物体子类别,根据不同的物体类型,可以取以下子类型：
        // 同NET_MSG_OBJECT相应字段
        public byte[] byReserved1 = new byte[3];
        public byte bPicEnble;//是否有物体对应图片文件信息, 类型为bool, 取值0或者1
        public NET_PIC_INFO stPicInfo;//物体对应图片信息
        public byte bShotFrame;//是否是抓拍张的识别结果, 类型为bool, 取值0或者1
        public byte bColor;//物体颜色(rgbaMainColor)是否可用, 类型为bool, 取值0或者1
        public byte bLowerBodyColor;//下半身颜色(rgbaLowerBodyColor)是否可用
        public byte byTimeType;//时间表示类型,详见EM_TIME_TYPE说明
        public NET_TIME_EX stuCurrentTime;//针对视频浓缩,当前时间戳（物体抓拍或识别时,会将此识别智能帧附在一个视频帧或jpeg图片中,此帧所在原始视频中的出现时间）
        public NET_TIME_EX stuStartTime;//开始时间戳（物体开始出现时）
        public NET_TIME_EX stuEndTime;//结束时间戳（物体最后出现时）
        public NET_RECT stuOriginalBoundingBox;//包围盒(绝对坐标)
        public NET_RECT stuSignBoundingBox;//车标坐标包围盒
        public int dwCurrentSequence;//当前帧序号（抓下这个物体时的帧）
        public int dwBeginSequence;//开始帧序号（物体开始出现时的帧序号）
        public int dwEndSequence;//结束帧序号（物体消逝时的帧序号）
        public long nBeginFileOffset;//开始时文件偏移,单位:字节（物体开始出现时,视频帧在原始视频文件中相对于文件起始处的偏移）
        public long nEndFileOffset;//结束时文件偏移,单位:字节（物体消逝时,视频帧在原始视频文件中相对于文件起始处的偏移）
        public byte[] byColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX];//物体颜色相似度,取值范围：0-100,数组下标值代表某种颜色,详见EM_COLOR_TYPE
        public byte[] byUpperBodyColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX];//上半身物体颜色相似度(物体类型为人时有效)
        public byte[] byLowerBodyColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX];//下半身物体颜色相似度(物体类型为人时有效)
        public int nRelativeID;//相关物体ID
        public byte[] szSubText = new byte[20];//"ObjectType"为"Vehicle"或者"Logo"时,表示车标下的某一车系,比如奥迪A6L,由于车系较多,SDK实现时透传此字段,设备如实填写。
        public int nPersonStature;//入侵人员身高,单位cm
        public int emPersonDirection;//人员入侵方向, 取值为EM_MSG_OBJ_PERSON_DIRECTION中的值
        public int rgbaLowerBodyColor;//使用方法同rgbaMainColor,物体类型为人时有效
        //视频浓缩额外信息
        public int nSynopsisSpeed;//浓缩速度域值,共分1~10共十个档位,5表示浓缩后只保留5以上速度的物体。是个相对单位
        // 为0时,该字段无效
        public int nSynopsisSize;//浓缩尺寸域值,共分1~10共十个档位,3表示浓缩后只保留3以上大小的物体。是个相对单位
        // 为0时,该字段无效
        public int bEnableDirection;//为True时,对物体运动方向做过滤, 类型为BOOL, 取值0或者1
        // 为False时,不对物体运动方向做过滤,
        public NET_POINT stuSynopsisStartLocation;//浓缩运动方向,起始坐标点,点的坐标归一化到[0,8192)区间,bEnableDirection为True时有效
        public NET_POINT stuSynopsisEndLocation;//浓缩运动方向,终止坐标点,点的坐标归一化到[0,8192)区间,bEnableDirection为True时有效
        public byte[] byReserved = new byte[2048];//扩展字节
        
        public NET_MSG_OBJECT_EX2()
        {
            this.dwSize = this.size();
            
            // 强制采用最大四字节对其
            setAlignType(ALIGN_GNUC);
        }
    }
    
    // 设备协议类型
    public static class NET_DEVICE_PROTOCOL extends Structure
    {
        public static final int NET_PROTOCOL_PRIVATE2 = 0; //私有2代协议
        public static final int NET_PROTOCOL_PRIVATE3 = NET_PROTOCOL_PRIVATE2+1; //私有3代协议
        public static final int NET_PROTOCOL_ONVIF = NET_PROTOCOL_PRIVATE3+1; //Onvif
        public static final int NET_PROTOCOL_VNC = NET_PROTOCOL_ONVIF+1; //虚拟网络计算机
        public static final int NET_PROTOCOL_TS = NET_PROTOCOL_VNC+1; //标准TS
        public static final int NET_PROTOCOL_PRIVATE = 100;//私有协议
        public static final int NET_PROTOCOL_AEBELL = NET_PROTOCOL_PRIVATE+1; //美电贝尔
        public static final int NET_PROTOCOL_PANASONIC = NET_PROTOCOL_AEBELL+1; //松下
        public static final int NET_PROTOCOL_SONY = NET_PROTOCOL_PANASONIC+1; //索尼
        public static final int NET_PROTOCOL_DYNACOLOR = NET_PROTOCOL_SONY+1; //Dynacolor
        public static final int NET_PROTOCOL_TCWS = NET_PROTOCOL_DYNACOLOR+1; //天城威视
        public static final int NET_PROTOCOL_SAMSUNG = NET_PROTOCOL_TCWS+1; //三星
        public static final int NET_PROTOCOL_YOKO = NET_PROTOCOL_SAMSUNG+1; //YOKO
        public static final int NET_PROTOCOL_AXIS = NET_PROTOCOL_YOKO+1; //安讯视
        public static final int NET_PROTOCOL_SANYO = NET_PROTOCOL_AXIS+1; //三洋
        public static final int NET_PROTOCOL_BOSH = NET_PROTOCOL_SANYO+1; //Bosch
        public static final int NET_PROTOCOL_PECLO = NET_PROTOCOL_BOSH+1; //Peclo
        public static final int NET_PROTOCOL_PROVIDEO = NET_PROTOCOL_PECLO+1; //Provideo
        public static final int NET_PROTOCOL_ACTI = NET_PROTOCOL_PROVIDEO+1; //ACTi
        public static final int NET_PROTOCOL_VIVOTEK = NET_PROTOCOL_ACTI+1; //Vivotek
        public static final int NET_PROTOCOL_ARECONT = NET_PROTOCOL_VIVOTEK+1; //Arecont
        public static final int NET_PROTOCOL_PRIVATEEH = NET_PROTOCOL_ARECONT+1; //PrivateEH
        public static final int NET_PROTOCOL_IMATEK = NET_PROTOCOL_PRIVATEEH+1; //IMatek
        public static final int NET_PROTOCOL_SHANY = NET_PROTOCOL_IMATEK+1; //Shany
        public static final int NET_PROTOCOL_VIDEOTREC = NET_PROTOCOL_SHANY+1; //动力盈科
        public static final int NET_PROTOCOL_URA = NET_PROTOCOL_VIDEOTREC+1; //Ura
        public static final int NET_PROTOCOL_BITICINO = NET_PROTOCOL_URA+1; //Bticino
        public static final int NET_PROTOCOL_ONVIF2 = NET_PROTOCOL_BITICINO+1; //Onvif协议类型,同NET_PROTOCOL_ONVIF
        public static final int NET_PROTOCOL_SHEPHERD = NET_PROTOCOL_ONVIF2+1; //视霸
        public static final int NET_PROTOCOL_YAAN = NET_PROTOCOL_SHEPHERD+1; //亚安
        public static final int NET_PROTOCOL_AIRPOINT = NET_PROTOCOL_YAAN+1; //Airpop
        public static final int NET_PROTOCOL_TYCO = NET_PROTOCOL_AIRPOINT+1; //TYCO
        public static final int NET_PROTOCOL_XUNMEI = NET_PROTOCOL_TYCO+1; //讯美
        public static final int NET_PROTOCOL_HIKVISION = NET_PROTOCOL_XUNMEI+1; //海康
        public static final int NET_PROTOCOL_LG = NET_PROTOCOL_HIKVISION+1; //LG
        public static final int NET_PROTOCOL_AOQIMAN = NET_PROTOCOL_LG+1; //奥奇曼
        public static final int NET_PROTOCOL_BAOKANG = NET_PROTOCOL_AOQIMAN+1; //宝康
        public static final int NET_PROTOCOL_WATCHNET = NET_PROTOCOL_BAOKANG+1; //Watchnet
        public static final int NET_PROTOCOL_XVISION = NET_PROTOCOL_WATCHNET+1; //Xvision
        public static final int NET_PROTOCOL_FUSITSU = NET_PROTOCOL_XVISION+1; //富士通
        public static final int NET_PROTOCOL_CANON = NET_PROTOCOL_FUSITSU+1; //Canon
        public static final int NET_PROTOCOL_GE = NET_PROTOCOL_CANON+1; //GE
        public static final int NET_PROTOCOL_Basler = NET_PROTOCOL_GE+1; //巴斯勒
        public static final int NET_PROTOCOL_Patro = NET_PROTOCOL_Basler+1; //帕特罗
        public static final int NET_PROTOCOL_CPKNC = NET_PROTOCOL_Patro+1; //CPPLUSK系列
        public static final int NET_PROTOCOL_CPRNC = NET_PROTOCOL_CPKNC+1; //CPPLUSR系列
        public static final int NET_PROTOCOL_CPUNC = NET_PROTOCOL_CPRNC+1; //CPPLUSU系列
        public static final int NET_PROTOCOL_CPPLUS = NET_PROTOCOL_CPUNC+1; //CPPLUSIPC
        public static final int NET_PROTOCOL_XunmeiS = NET_PROTOCOL_CPPLUS+1; //讯美s,实际协议为Onvif
        public static final int NET_PROTOCOL_GDDW = NET_PROTOCOL_XunmeiS+1; //广东电网
        public static final int NET_PROTOCOL_PSIA = NET_PROTOCOL_GDDW+1; //PSIA
        public static final int NET_PROTOCOL_GB2818 = NET_PROTOCOL_PSIA+1; //GB2818
        public static final int NET_PROTOCOL_GDYX = NET_PROTOCOL_GB2818+1; //GDYX
        public static final int NET_PROTOCOL_OTHER = NET_PROTOCOL_GDYX+1; //由用户自定义
    }
    
    // 雨刷来回循环刷,雨刷模式配置为手动模式时有效(对应命令 CTRLTYPE_CTRL_RAINBRUSH_MOVECONTINUOUSLY)
    public static class NET_CTRL_RAINBRUSH_MOVECONTINUOUSLY extends Structure
    {
        public int dwSize;
        public int nChannel;//表示雨刷的索引
        public int nInterval;//雨刷间隔
        
        public NET_CTRL_RAINBRUSH_MOVECONTINUOUSLY()
        {
            this.dwSize = this.size();
        }
    }

    // 雨刷停止刷,雨刷模式配置为手动模式时有效(对应命令 CTRLTYPE_CTRL_RAINBRUSH_STOPMOVE)
    public static class NET_CTRL_RAINBRUSH_STOPMOVE extends Structure
    {
        public int dwSize;
        public int nChannel;//表示雨刷的索引
        
        public NET_CTRL_RAINBRUSH_STOPMOVE()
        {
            this.dwSize = this.size();
        }
    }

    // 雨刷来回刷一次，雨刷模式配置为手动模式时有效(对应命令 CTRLTYPE_CTRL_RAINBRUSH_MOVEONCE)
    public static class NET_CTRL_RAINBRUSH_MOVEONCE extends Structure
    {
        public int dwSize;
        public int nChannel;//表示雨刷的索引
        
        public NET_CTRL_RAINBRUSH_MOVEONCE()
        {
            this.dwSize = this.size();
        }
    }
    
    // DSP能力描述，扩展类型，对应CLIENT_QueryDevState接口
    public static class NET_DEV_DSP_ENCODECAP extends Structure
    {
        public int dwVideoStandardMask;//视频制式掩码，按位表示设备能够支持的视频制式
        public int dwImageSizeMask;//分辨率掩码，按位表示设备能够支持的分辨率
        public int dwEncodeModeMask;//编码模式掩码，按位表示设备能够支持的编码模式
        public int dwStreamCap;//按位表示设备支持的多媒体功能，
                               // 第一位表示支持主码流
                               // 第二位表示支持辅码流1
                               // 第三位表示支持辅码流2
                               // 第五位表示支持jpg抓图
        public int[] dwImageSizeMask_Assi = new int[32];//表示主码流为各分辨率时，支持的辅码流分辨率掩码。
        public int dwMaxEncodePower;//DSP支持的最高编码能力
        public short wMaxSupportChannel;//每块DSP支持最多输入视频通道数
        public short wChannelMaxSetSync;//DSP每通道的最大编码设置是否同步；0：不同步，1：同步
        public byte[] bMaxFrameOfImageSize = new byte[32];//不同分辨率下的最大采集帧率，与dwVideoStandardMask按位对应
        public byte bEncodeCap;//标志，配置时要求符合下面条件，否则配置不能生效；
                               // 0：主码流的编码能力+辅码流的编码能力 <= 设备的编码能力，
                               // 1：主码流的编码能力+辅码流的编码能力 <= 设备的编码能力，
                               // 辅码流的编码能力 <= 主码流的编码能力，
                               // 辅码流的分辨率 <= 主码流的分辨率，
                               // 主码流和辅码流的帧率 <= 前端视频采集帧率
                               // 2：N5的计算方法
                               // 辅码流的分辨率 <= 主码流的分辨率
                               // 查询支持的分辨率和相应最大帧率
        public byte[] reserved = new byte[95];
    }
    
    //云台控制坐标单元
    public static class PTZ_SPACE_UNIT extends Structure
    {
        public int nPositionX;//云台水平运动位置,有效范围：0,3600]
        public int nPositionY;//云台垂直运动位置,有效范围：-1800,1800]
        public int nZoom;//云台光圈变动位置,有效范围：0,128]
        public byte[] szReserve = new byte[32];//预留32字节
    }

    //云台控制速度单元
    public static class PTZ_SPEED_UNIT extends Structure
    {
        public float fPositionX;//云台水平方向速率,归一化到-1~1
        public float fPositionY;//云台垂直方向速率,归一化到-1~1
        public float fZoom;//云台光圈放大倍率,归一化到0~1
        public byte[] szReserve = new byte[32];//预留32字节
    }

    //持续控制云台对应结构
    public static class PTZ_CONTROL_CONTINUOUSLY extends Structure
    {
        public PTZ_SPEED_UNIT stuSpeed;//云台运行速度
        public int nTimeOut;//连续移动超时时间,单位为秒
        public byte[] szReserve = new byte[64];//预留64字节
    }

    //绝对控制云台对应结构
    public static class PTZ_CONTROL_ABSOLUTELY extends Structure
    {
        public PTZ_SPACE_UNIT stuPosition;//云台绝对移动位置
        public PTZ_SPEED_UNIT stuSpeed;//云台运行速度
        public byte[] szReserve = new byte[64];//预留64字节
    }

    //带速度转动到预置位点云台控制对应结构
    public static class PTZ_CONTROL_GOTOPRESET extends Structure
    {
        public int nPresetIndex;//预置位索引
        public PTZ_SPEED_UNIT stuSpeed;//云台运行速度
        public byte[] szReserve = new byte[64];//预留64字节
    }

    //设置云台可视域信息
    public static class PTZ_VIEW_RANGE_INFO extends Structure
    {
        public int nStructSize;
        public int nAzimuthH;//水平方位角度,0~3600,单位:度
        
        public PTZ_VIEW_RANGE_INFO()
        {
            this.nStructSize = this.size();
        }
    }

    //云台绝对聚焦对应结构
    public static class PTZ_FOCUS_ABSOLUTELY extends Structure
    {
        public int dwValue;//云台聚焦位置,取值范围(0~8191)
        public int dwSpeed;//云台聚焦速度,取值范围(0~7)
        public byte[] szReserve = new byte[64];//预留64字节
    }

    // 云台控制-扇扫对应结构
    public static class PTZ_CONTROL_SECTORSCAN extends Structure
    {
        public int nBeginAngle;//起始角度,范围:-180,180]
        public int nEndAngle;//结束角度,范围:-180,180]
        public int nSpeed;//速度,范围:0,255]
        public byte[] szReserve = new byte[64];//预留64字节
    }

    // 控制鱼眼电子云台信息
    public static class PTZ_CONTROL_SET_FISHEYE_EPTZ extends Structure
    {
        public int dwSize;//结构体大小
        public int dwWindowID;//进行EPtz控制的窗口编号
        public int dwCommand;//电子云台命令
        public int dwParam1;//命令对应参数1
        public int dwParam2;//命令对应参数2
        public int dwParam3;//命令对应参数3
        public int dwParam4;//命令对应参数4
    }
    
    // 变倍设置基本信息单元
    public static class CFG_VIDEO_IN_ZOOM_UNIT extends Structure
    {
        public int nSpeed;//变倍速率(0~7)
        public int bDigitalZoom;//是否数字变倍, 类型为BOOL, 取值0或者1
        public int nZoomLimit;//当前速率下最大变倍上限(0~13)。
    }

    // 单通道变倍设置基本信息
    public static class CFG_VIDEO_IN_ZOOM extends Structure
    {
        public int nChannelIndex;//通道号
        public int nVideoInZoomRealNum;//配置使用个数
        public CFG_VIDEO_IN_ZOOM_UNIT[] stVideoInZoomUnit = (CFG_VIDEO_IN_ZOOM_UNIT[])new CFG_VIDEO_IN_ZOOM_UNIT().toArray(MAX_VIDEO_IN_ZOOM);//通道变速配置单元信息
    }

    // 设备状态
    public static class CFG_TRAFFIC_DEVICE_STATUS extends Structure 
    {
        public byte[]                 szType = new byte[MAX_PATH];          // 设备类型 支持："Radar","Detector","SigDetector","StroboscopicLamp"," FlashLamp"
        public byte[]                 szSerialNo = new byte[MAX_PATH];      // 设备编号
        public byte[]                 szVendor = new byte[MAX_PATH];        // 生产厂商
        public int                    nWokingState;                         // 工作状态 0-故障,1-正常工作
    }
    
    // 获取设备工作状态是否正常 (对应命令 CFG_CAP_CMD_DEVICE_STATE )
    public static class CFG_CAP_TRAFFIC_DEVICE_STATUS extends Structure
    {
        public int                          nStatus;                        // stuStatus 实际个数
        public CFG_TRAFFIC_DEVICE_STATUS[]  stuStatus = (CFG_TRAFFIC_DEVICE_STATUS[]) new CFG_TRAFFIC_DEVICE_STATUS().toArray(MAX_STATUS_NUM);
    }
    
    // 视频输入通道
    public static class CFG_RemoteDeviceVideoInput extends Structure
    {
    	public int				bEnable;
    	public byte[]			szName = new byte[MAX_DEVICE_NAME_LEN];
    	public byte[]			szControlID = new byte[MAX_DEV_ID_LEN_EX];
    	public byte[]			szMainUrl = new byte[MAX_PATH];	// 主码流url地址
    	public byte[]			szExtraUrl = new byte[MAX_PATH]; // 辅码流url地址
    	public int				nServiceType; // 服务类型, 0-TCP, 1-UDP, 2-MCAST, -1-AUTO
    }
    
    // 远程设备
    public static class AV_CFG_RemoteDevice extends Structure
    {
    	public int 				nStructSize;
    	public int 				bEnable; // 使能
    	public byte[]			szID = new byte[AV_CFG_Device_ID_Len]; // 设备ID
    	public byte[]			szIP = new byte[AV_CFG_IP_Address_Len];// 设备IP
    	public int 				nPort; // 端口
    	public byte[]			szProtocol = new byte[AV_CFG_Protocol_Len]; // 协议类型
    	public byte[]			szUser = new byte[AV_CFG_User_Name_Len]; // 用户名
    	public byte[]			szPassword = new byte[AV_CFG_Password_Len]; // 密码
    	public byte[]			szSerial = new byte[AV_CFG_Serial_Len];	// 设备序列号
    	public byte[]			szDevClass = new byte[AV_CFG_Device_Class_Len]; // 设备类型
    	public byte[]			szDevType = new byte[AV_CFG_Device_Type_Len]; // 设备型号
    	public byte[]			szName = new byte[AV_CFG_Device_Name_Len]; // 机器名称
    	public byte[]			szAddress =  new byte[AV_CFG_Address_Len]; // 机器部署地点
    	public byte[]			szGroup = new byte[AV_CFG_Group_Name_Len]; // 机器分组
    	public int 				nDefinition; // 清晰度, 0-标清, 1-高清
    	public int 				nVideoChannel; // 视频输入通道数
    	public int 				nAudioChannel; // 音频输入通道数
    	public int             	nRtspPort; // Rtsp端口号
    	public byte[]           szVendor = new byte[MAX_PATH]; // 设备接入类型
    	public Pointer 			pVideoInput; // 视频输入通道，用户申请nMaxVideoInputs个CFG_RemoteDeviceVideoInput空间
    	public int              nMaxVideoInputs;
    	public int              nRetVideoInputs;
    	public int				nHttpPort; // http端口号
    	
    	/* 以下3项为国际接入方式相关  */
    	public int 				bGB28181; // 是否有国际接入方式
    	public int				nDevLocalPort; // 设备本地端口
    	public byte[]			szDeviceNo = new byte[AV_CFG_DeviceNo_Len]; // 设备编号
    	
    	public AV_CFG_RemoteDevice() {
        	this.nStructSize = this.size();
    	}
    }
    
    // 视频分析资源类型
    public static class CFG_VIDEO_SOURCE_TYPE extends Structure {
    	public static final int CFG_VIDEO_SOURCE_REALSTREAM = 0; // 实时流
    	public static final int CFG_VIDEO_SOURCE_FILESTREAM = 1; // 文件流
    }
    
    // 分析源文件类型
    public static class CFG_SOURCE_FILE_TYPE extends Structure {
    	public static final int CFG_SOURCE_FILE_UNKNOWN = 0; // 未知类型
    	public static final int CFG_SOURCE_FILE_RECORD = 1; // 录像文件
    	public static final int CFG_SOURCE_FILE_PICTURE = 2; // 图片文件
    }
    
    // 视频分析源文件信息
    public static class CFG_SOURCE_FILE_INFO extends Structure {
    	public byte[]				szFilePath = new byte[MAX_PATH];// 文件路径
    	public int 					emFileType; // 文件类型，详见 CFG_SOURCE_FILE_TYPE
    }
    
    // 每个视频输入通道对应的视频分析资源配置信息
    public static class CFG_ANALYSESOURCE_INFO extends Structure {
    	public byte					bEnable; // 视频分析使能   1-使能， 0-禁用
    	public int					nChannelID;	// 智能分析的前端视频通道号
    	public int					nStreamType;// 智能分析的前端视频码流类型，0:抓图码流; 1:主码流; 2:子码流1; 3:子码流2; 4:子码流3; 5:物体流
    	public byte[]				szRemoteDevice = new byte[MAX_NAME_LEN];// 设备名
    	public byte					abDeviceInfo; // 设备信息是否有效 ; 1-有效，0-无效
    	public AV_CFG_RemoteDevice  stuDeviceInfo; // 设备信息
    	public int                  emSourceType; // 视频分析源类型，详见  CFG_VIDEO_SOURCE_TYPE
    	public CFG_SOURCE_FILE_INFO stuSourceFile; // 当视频分析源类型为 CFG_VIDEO_SOURCE_FILESTREAM 时，有效
    }   
    
    // 手动抓拍参数
    public static class MANUAL_SNAP_PARAMETER extends Structure
    {
    	public int                   nChannel;               			// 抓图通道,从0开始
    	public byte[]                bySequence = new byte[64];	        // 抓图序列号字符串
    	public byte[]                byReserved = new byte[60];         // 保留字段
    }
    
    // 视频统计小计信息
    public static class NET_VIDEOSTAT_SUBTOTAL extends Structure
    {
    	 public int                 nTotal;                         // 设备运行后人数统计总数
         public int                 nHour;                          // 小时内的总人数
         public int                 nToday;                         // 当天的总人数
         public int                 nOSD;							// 统计人数,用于OSD显示, 可手动清除
         public byte[]              reserved = new byte[252];
    }

    // 视频统计摘要信息
    public static class NET_VIDEOSTAT_SUMMARY extends Structure
    {
    	public int                     nChannelID;                 	// 通道号
        public byte[]                  szRuleName = new byte[32];	// 规则名称
        public NET_TIME_EX             stuTime;                    	// 统计时间
        public NET_VIDEOSTAT_SUBTOTAL  stuEnteredSubtotal;         	// 进入小计
        public NET_VIDEOSTAT_SUBTOTAL  stuExitedSubtotal;          	// 出去小计
        public byte[]                  reserved = new byte[512];
    }

    // CLIENT_AttachVideoStatSummary 入参
    public static class NET_IN_ATTACH_VIDEOSTAT_SUM extends Structure
    {
    	 public int                   	dwSize;
         public int                     nChannel;                    // 视频通道号         
         public fVideoStatSumCallBack   cbVideoStatSum;              // 视频统计摘要信息回调
         public NativeLong              dwUser;                      // 用户数据                  
         
         public NET_IN_ATTACH_VIDEOSTAT_SUM()
         {
        	 this.dwSize = this.size();
         }
    }
    
    public static class NET_OUT_ATTACH_VIDEOSTAT_SUM extends Structure
    {
    	public int 					dwSize;
    	
    	public NET_OUT_ATTACH_VIDEOSTAT_SUM()
    	{
    		this.dwSize = this.size();
    	}
   
    }

    // 接口(CLIENT_StartFindNumberStat)输入参数
    public static class NET_IN_FINDNUMBERSTAT extends Structure {
        public int                 dwSize;                     // 此结构体大小
        public int                 nChannelID;                 // 要进行查询的通道号
        public NET_TIME            stStartTime;                // 开始时间 暂时精确到小时
        public NET_TIME            stEndTime;                  // 结束时间 暂时精确到小时
        public int                 nGranularityType;           // 查询粒度0:分钟,1:小时,2:日,3:周,4:月,5:季,6:年
        public int                 nWaittime;                  // 等待接收数据的超时时间
        
        public NET_IN_FINDNUMBERSTAT() {
        	this.dwSize = this.size();
        }
    }

    // 接口(CLIENT_StartFindNumberStat)输出参数
    public static class NET_OUT_FINDNUMBERSTAT extends Structure {
        public int               dwSize;                     // 此结构体大小
        public int               dwTotalCount;               // 符合此次查询条件的结果总条数
        
        public NET_OUT_FINDNUMBERSTAT() {
        	this.dwSize = this.size();
		}
    }


    // 接口(CLIENT_DoFindNumberStat)输入参数
    public static class NET_IN_DOFINDNUMBERSTAT extends Structure {
        public int               dwSize;                     // 此结构体大小
        public int        		 nBeginNumber;               // [0, totalCount-1], 查询起始序号,表示从beginNumber条记录开始,取count条记录返回; 
        public int        		 nCount;                     // 每次查询的流量统计条数
        public int               nWaittime;                  // 等待接收数据的超时时间            
        
        public NET_IN_DOFINDNUMBERSTAT() {
        	this.dwSize = this.size();
		}
    }

    public static class NET_NUMBERSTAT extends Structure {
        public int      dwSize;
        public int      nChannelID;                           //统计通道号
        public byte[]   szRuleName = new byte[NET_CHAN_NAME_LEN]; //规则名称
        public NET_TIME stuStartTime;                         //开始时间
        public NET_TIME stuEndTime;                           //结束时间
        public int      nEnteredSubTotal;                     //进入人数小计
        public int      nExitedSubtotal;                      //出去人数小计
        public int      nAvgInside;                           //平均保有人数(除去零值)
        public int      nMaxInside;                           //最大保有人数
        public int      nEnteredWithHelmet;                   //戴安全帽进入人数小计
        public int      nEnteredWithoutHelmet;                //不戴安全帽进入人数小计
        public int      nExitedWithHelmet;                    //戴安全帽出去人数小计
        public int      nExitedWithoutHelmet;                 //不戴安全帽出去人数小计
        
        public NET_NUMBERSTAT() {
        	this.dwSize = this.size();
        }
        
        public static class ByReference extends NET_NUMBERSTAT implements Structure.ByReference { }
    }

    // 接口(CLIENT_DoFindNumberStat)输出参数
    public static class NET_OUT_DOFINDNUMBERSTAT extends Structure {
        public int                 dwSize;                          // 此结构体大小
        public int                 nCount;                          // 查询返回人数统计信息个数
        public Pointer    		   pstuNumberStat;        			// 返回人数统计信息数组, NET_NUMBERSTAT 类型 
        public int                 nBufferLen;                      // 用户申请的内存大小,以NET_NUMBERSTAT中的dwsize大小为单位
    
        public NET_OUT_DOFINDNUMBERSTAT() {
        	this.dwSize = this.size();
        }
    }
    
    public static class CONNECT_STATE extends Structure
    {
        public static final int CONNECT_STATE_UNCONNECT = 0;
        public static final int CONNECT_STATE_CONNECTING = 1;
        public static final int CONNECT_STATE_CONNECTED = 2;
        public static final int CONNECT_STATE_ERROR = 255;
    }

    // 虚拟摄像头状态查询
    public static class NET_DEV_VIRTUALCAMERA_STATE_INFO extends Structure
    {
        public int nStructSize;//结构体大小
        public int nChannelID;//通道号
        public int emConnectState;//连接状态, 取值范围为CONNECT_STATE中的值
        public int uiPOEPort;//此虚拟摄像头所连接的POE端口号,0表示不是POE连接, 类型为unsigned int
        public byte[] szDeviceName = new byte[64];//设备名称
        public byte[] szDeviceType = new byte[128];//设备类型
        public byte[] szSystemType = new byte[128];//系统版本
        public byte[] szSerialNo = new byte[NET_SERIALNO_LEN];//序列号
        public int nVideoInput;//视频输入
        public int nAudioInput;//音频输入
        public int nAlarmOutput;//外部报警
        
        public NET_DEV_VIRTUALCAMERA_STATE_INFO()
        {
        	this.nStructSize = this.size();
        }
    }
    
    // 录像文件类型
    public static class NET_RECORD_TYPE extends Structure
    {
    	public final static int NET_RECORD_TYPE_ALL = 0; // 所有录像
    	public final static int NET_RECORD_TYPE_NORMAL = 1; // 普通录像
    	public final static int NET_RECORD_TYPE_ALARM = 2; // 外部报警录像
    	public final static int NET_RECORD_TYPE_MOTION = 3; // 动检报警录像
    }
    
    // 对讲方式
    public static class EM_USEDEV_MODE extends Structure
    {
    	public static final int NET_TALK_CLIENT_MODE 	  = 0; // 设置客户端方式进行语音对讲
    	public static final int NET_TALK_SERVER_MODE 	  = 1; // 设置服务器方式进行语音对讲
    	public static final int NET_TALK_ENCODE_TYPE 	  = 2; // 设置语音对讲编码格式(对应 NET_DEV_TALKDECODE_INFO)
    	public static final int NET_ALARM_LISTEN_MODE 	  = 3; // 设置报警订阅方式
    	public static final int NET_CONFIG_AUTHORITY_MODE = 4; // 设置通过权限进行配置管理
    	public static final int NET_TALK_TALK_CHANNEL 	  = 5; // 设置对讲通道(0~MaxChannel-1)
    	public static final int NET_RECORD_STREAM_TYPE	  = 6; // 设置待查询及按时间回放的录像码流类型(0-主辅码流,1-主码流,2-辅码流)  
    	public static final int NET_TALK_SPEAK_PARAM      = 7; // 设置语音对讲喊话参数,对应结构体 NET_SPEAK_PARAM
    	public static final int NET_RECORD_TYPE           = 8; // 设置按时间录像回放及下载的录像文件类型(详见  NET_RECORD_TYPE)
    	public static final int NET_TALK_MODE3            = 9; // 设置三代设备的语音对讲参数, 对应结构体 NET_TALK_EX
    	public static final int NET_PLAYBACK_REALTIME_MODE = 10; // 设置实时回放功能(0-关闭,1开启)
    	public static final int NET_TALK_TRANSFER_MODE    = 11; // 设置语音对讲是否为转发模式, 对应结构体 NET_TALK_TRANSFER_PARAM
    	public static final int NET_TALK_VT_PARAM         = 12; // 设置VT对讲参数, 对应结构体 NET_VT_TALK_PARAM
    	public static final int NET_TARGET_DEV_ID         = 13; // 设置目标设备标示符, 用以查询新系统能力(非0-转发系统能力消息)
    }
    

    // 预览类型,对应CLIENT_RealPlayEx接口
    public static class NET_RealPlayType extends Structure
    {
    	public static final int NET_RType_Realplay   = 0; // 实时预览
    	public static final int NET_RType_Multiplay  = 1; // 多画面预览
    	public static final int NET_RType_Realplay_0 = 2; // 实时监视-主码流 ,等同于NET_RType_Realplay
    	public static final int NET_RType_Realplay_1 = 3; // 实时监视-从码流1
    	public static final int NET_RType_Realplay_2 = 4; // 实时监视-从码流2
    	public static final int NET_RType_Realplay_3 = 5; // 实时监视-从码流3    
    	public static final int NET_RType_Multiplay_1 = 6; // 多画面预览－1画面
    	public static final int NET_RType_Multiplay_4 = 7; // 多画面预览－4画面
    	public static final int NET_RType_Multiplay_8 = 8; // 多画面预览－8画面
    	public static final int NET_RType_Multiplay_9 = 9; // 多画面预览－9画面
    	public static final int NET_RType_Multiplay_16 = 10; // 多画面预览－16画面
    	public static final int NET_RType_Multiplay_6 = 11;  // 多画面预览－6画面
    	public static final int NET_RType_Multiplay_12 = 12; // 多画面预览－12画面
    	public static final int NET_RType_Multiplay_25 = 13; // 多画面预览－25画面
    	public static final int NET_RType_Multiplay_36 = 14; // 多画面预览－36画面
    }
    
    // 电池电压过低报警
    public static class ALARM_BATTERYLOWPOWER_INFO extends Structure
    {
	    public int dwSize;			//结构体大小
	    public int nAction;			//0:开始1:停止
	    public int nBatteryLeft;	//剩余电量百分比,单位%
	    public NET_TIME stTime;		//事件发生时间
	    public int nChannelID;		//通道号,标识子设备电池,从0开始
	    
	    public ALARM_BATTERYLOWPOWER_INFO()
	    {
	    	this.dwSize = this.size();
	    }
    }
    
    // 温度过高报警
    public static class ALARM_TEMPERATURE_INFO extends Structure
    {
	    public int dwSize;//结构体大小
	    public byte[] szSensorName = new byte[NET_MACHINE_NAME_NUM];//温度传感器名称
	    public int nChannelID;//通道号
	    public int nAction;//0:开始1:停止
	    public float fTemperature;//当前温度值,单位摄氏度
	    public NET_TIME stTime;//事件发生时间
	    
	    public ALARM_TEMPERATURE_INFO()
	    {
	    	this.dwSize = this.size();
	    }
    }
   
    // 视频遮挡报警状态信息对应结构体
    public static class NET_CLIENT_VIDEOBLIND_STATE extends Structure
    {
	    public int dwSize;
	    public int channelcount;
	    public int[] dwAlarmState = new int[NET_MAX_CHANMASK];//每一个DWORD按位表示32通道的报警状态,0-表示无报警,1-表示有报警
	    
	    public NET_CLIENT_VIDEOBLIND_STATE()
	    {
	    	this.dwSize = this.size();
	    }
    }
    
    // 视频丢失报警状态信息对应结构体
    public static class NET_CLIENT_VIDEOLOST_STATE extends Structure
    {
	    public int	dwSize;
	    public int	channelcount;
	    public int[]	dwAlarmState = new int[NET_MAX_CHANMASK];//每一个DWORD按位表示32通道的报警状态,0-表示无报警,1-表示有报警
	    
	    public NET_CLIENT_VIDEOLOST_STATE()
	    {
	    	this.dwSize = this.size();
	    }
    }
    
    // 开启道闸参数(对应 CTRLTYPE_CTRL_OPEN_STROBE 命令)
    public static class NET_CTRL_OPEN_STROBE extends Structure {
    	public int dwSize;
    	public int nChannelId; // 通道号 
    	public byte[] szPlateNumber = new byte[64]; // 车牌号码
 
    	public NET_CTRL_OPEN_STROBE() {
    		this.dwSize = this.size();
    	}
    }
    
    // 关闭道闸参数(对应  CTRLTYPE_CTRL_CLOSE_STROBE 命令)
    public static class NET_CTRL_CLOSE_STROBE extends Structure {
    	public int	dwSize;
    	public int	nChannelId; // 通道号
    	
    	public NET_CTRL_CLOSE_STROBE() {
    		this.dwSize = this.size();
    	}
    }
    
    // 报警状态 (对应   CTRLTYPE_TRIGGER_ALARM_OUT 命令)
    public static class ALARMCTRL_PARAM extends Structure {
    	public int	dwSize;
    	public int	nAlarmNo; // 报警通道号,从0开始
    	public int	nAction; // 1：触发报警,0：停止报警
    	
    	public ALARMCTRL_PARAM() {
    		this.dwSize = this.size();
    	}
    }
    
    // 查询 IVS 前端设备入参
    public static class NET_IN_IVS_REMOTE_DEV_INFO extends Structure {
        public int                     dwSize;                         // 该结构体大小   
        public int                     nChannel;                       // 通道号
        
        public NET_IN_IVS_REMOTE_DEV_INFO() {
        	this.dwSize = this.size();
        }
    }
    
    // 查询 IVS 前端设备出参
    public static class NET_OUT_IVS_REMOTE_DEV_INFO extends Structure    {
        public int                   dwSize;                         	// 该结构体大小 
        public int     			     nPort;								// 端口
        public byte[]				 szIP = new byte[64];	            // 设备IP
        public byte[]				 szUser = new byte[64];	            // 用户名
    	public byte[]				 szPassword = new byte[64];         // 密码    
        public byte[]				 szAddress = new byte[128];	        // 机器部署地点
    
        public NET_OUT_IVS_REMOTE_DEV_INFO() {
        	this.dwSize = this.size();
        }
    }
    
    // 传感器感应方式枚举类型
    public static class NET_SENSE_METHOD extends Structure
    {
    	public static final int NET_SENSE_UNKNOWN = -1;//未知类型
    	public static final int NET_SENSE_DOOR	= 0; //门磁
    	public static final int NET_SENSE_PASSIVEINFRA = 1; //被动红外
    	public static final int NET_SENSE_GAS = 2; //气感
    	public static final int NET_SENSE_SMOKING = 3; //烟感
    	public static final int	NET_SENSE_WATER = 4; //水感
    	public static final int	NET_SENSE_ACTIVEFRA = 5; //主动红外
    	public static final int	NET_SENSE_GLASS = 6; //玻璃破碎
    	public static final int	NET_SENSE_EMERGENCYSWITCH = 7; //紧急开关
    	public static final int	NET_SENSE_SHOCK = 8; //震动
    	public static final int	NET_SENSE_DOUBLEMETHOD = 9; //双鉴(红外+微波)
    	public static final int	NET_SENSE_THREEMETHOD = 10; //三技术
    	public static final int	NET_SENSE_TEMP = 11; //温度
    	public static final int	NET_SENSE_HUMIDITY = 12; //湿度
    	public static final int	NET_SENSE_WIND = 13; //风速
    	public static final int	NET_SENSE_CALLBUTTON = 14; //呼叫按钮
    	public static final int	NET_SENSE_GASPRESSURE = 15; //气体压力
    	public static final int	NET_SENSE_GASCONCENTRATION = 16; //燃气浓度
    	public static final int	NET_SENSE_GASFLOW = 17; //气体流量
    	public static final int	NET_SENSE_OTHER = 18; //其他
    	public static final int	NET_SENSE_OIL = 19; //油量检测,汽油、柴油等车辆用油检测
    	public static final int	NET_SENSE_MILEAGE = 20; //里程数检测
    	public static final int	NET_SENSE_URGENCYBUTTON = 21; //紧急按钮
    	public static final int	NET_SENSE_STEAL = 22; //盗窃
    	public static final int	NET_SENSE_PERIMETER = 23; //周界
    	public static final int	NET_SENSE_PREVENTREMOVE = 24; //防拆
    	public static final int	NET_SENSE_DOORBELL = 25; //门铃
    	public static final int	NET_SENSE_ALTERVOLT = 26; //交流电压传感器
    	public static final int	NET_SENSE_DIRECTVOLT = 27; //直流电压传感器
    	public static final int	NET_SENSE_ALTERCUR = 28; //交流电流传感器
    	public static final int	NET_SENSE_DIRECTCUR = 29; //直流电流传感器
    	public static final int	NET_SENSE_RSUGENERAL = 30; //高新兴通用模拟量4~20mA或0~5V
    	public static final int	NET_SENSE_RSUDOOR = 31; //高新兴门禁感应
    	public static final int	NET_SENSE_RSUPOWEROFF = 32; //高新兴断电感应
    	public static final int	NET_SENSE_TEMP1500 = 33;//1500温度传感器
    	public static final int	NET_SENSE_TEMPDS18B20 = 34;//DS18B20温度传感器
    	public static final int	NET_SENSE_HUMIDITY1500 = 35; //1500湿度传感器
    	public static final int	NET_SENSE_NUM = 36; //枚举类型总数
    }
    
    //-------------------------------报警属性---------------------------------
	// 云台联动
	public static class NET_PTZ_LINK extends Structure
	{
		public int iType;//0-None,1-Preset,2-Tour,3-Pattern
		public int iValue;
	}

	////////////////////////////////HDVR专用//////////////////////////////////
    // 报警联动扩展结构体
    public static class NET_MSG_HANDLE_EX extends Structure
    {
        /* 消息处理方式,可以同时多种处理方式,包括
         * 0x00000001 - 报警上传
         * 0x00000002 - 联动录象
         * 0x00000004 - 云台联动
         * 0x00000008 - 发送邮件
         * 0x00000010 - 本地轮巡
         * 0x00000020 - 本地提示
         * 0x00000040 - 报警输出
         * 0x00000080 - Ftp上传
         * 0x00000100 - 蜂鸣
         * 0x00000200 - 语音提示
         * 0x00000400 - 抓图
        */
		/*当前报警所支持的处理方式,按位掩码表示*/
		public int dwActionMask;
		/*触发动作,按位掩码表示,具体动作所需要的参数在各自的配置中体现*/
		public int dwActionFlag;
		/*报警触发的输出通道,报警触发的输出,为1表示触发该输出*/
		public byte[] byRelAlarmOut = new byte[NET_MAX_ALARMOUT_NUM_EX];
		public int dwDuration;/*报警持续时间*/
		/*联动录象*/
		public byte[] byRecordChannel = new byte[NET_MAX_VIDEO_IN_NUM_EX];/*报警触发的录象通道,为1表示触发该通道*/
		public int dwRecLatch;/*录象持续时间*/
		/*抓图通道*/
		public byte[] bySnap = new byte[NET_MAX_VIDEO_IN_NUM_EX];
		/*轮巡通道*/
		public byte[] byTour = new byte[NET_MAX_VIDEO_IN_NUM_EX];/*轮巡通道0-31路*/
		/*云台联动*/
		public NET_PTZ_LINK[] struPtzLink = (NET_PTZ_LINK[])new NET_PTZ_LINK().toArray(NET_MAX_VIDEO_IN_NUM_EX);
		public int dwEventLatch;/*联动开始延时时间,s为单位,范围是0~15,默认值是0*/
		/*报警触发的无线输出通道,报警触发的输出,为1表示触发该输出*/
		public byte[] byRelWIAlarmOut = new byte[NET_MAX_ALARMOUT_NUM_EX];
		public byte bMessageToNet;
		public byte bMMSEn;/*短信报警使能*/
		public byte bySnapshotTimes;/*短信发送抓图张数*/
		public byte bMatrixEn;/*!<矩阵使能*/
		public int dwMatrix;/*!<矩阵掩码*/
		public byte bLog;/*!<日志使能,目前只有在WTN动态检测中使用*/
		public byte bSnapshotPeriod;/*!<抓图帧间隔,每隔多少帧抓一张图片,一定时间内抓拍的张数还与抓图帧率有关。0表示不隔帧,连续抓拍。*/
		public byte[] byTour2 = new byte[NET_MAX_VIDEO_IN_NUM_EX];/*轮巡通道32-63路*/
		public byte byEmailType;/*<0,图片附件,1,录像附件>*/
		public byte byEmailMaxLength;/*<附件录像时的最大长度,单位MB>*/
		public byte byEmailMaxTime;/*<附件是录像时最大时间长度,单位秒>*/
		public byte[] byReserved = new byte[475];
    }

    public static class EM_NET_DEFENCE_AREA_TYPE extends Structure
    {
    	public static final int EM_NET_DEFENCE_AREA_TYPE_UNKNOW = 0; //未知
    	public static final int EM_NET_DEFENCE_AREA_TYPE_INTIME = 1; //即时防区
    	public static final int EM_NET_DEFENCE_AREA_TYPE_DELAY = 2; //延时防区
    	public static final int EM_NET_DEFENCE_AREA_TYPE_FULLDAY = 3; //24小时防区
    	public static final int EM_NET_DEFENCE_AREA_TYPE_Follow = 4; //跟随防区
    	public static final int EM_NET_DEFENCE_AREA_TYPE_MEDICAL = 5; //医疗紧急防区
    	public static final int EM_NET_DEFENCE_AREA_TYPE_PANIC = 6; //恐慌防区
    	public static final int EM_NET_DEFENCE_AREA_TYPE_FIRE = 7; //火警防区
    	public static final int EM_NET_DEFENCE_AREA_TYPE_FULLDAYSOUND = 8; //24小时有声防区
    	public static final int EM_NET_DEFENCE_AREA_TYPE_FULLDATSLIENT = 9; //24小时无声防区
    	public static final int EM_NET_DEFENCE_AREA_TYPE_ENTRANCE1 = 10; //出入防区1
    	public static final int EM_NET_DEFENCE_AREA_TYPE_ENTRANCE2 = 11; //出入防区2
    	public static final int EM_NET_DEFENCE_AREA_TYPE_INSIDE = 12; //内部防区
    	public static final int EM_NET_DEFENCE_AREA_TYPE_OUTSIDE = 13; //外部防区
    	public static final int EN_NET_DEFENCE_AREA_TYPE_PEOPLEDETECT = 14; //人员检测防区
    }

    // 本地报警事件(对NET_ALARM_ALARM_EX升级)
    public static class ALARM_ALARM_INFO_EX2 extends Structure
    {
    	public int dwSize;
    	public int nChannelID;						//通道号
    	public int nAction;							//0:开始, 1:停止
    	public NET_TIME stuTime;					//报警事件发生的时间
    	public int emSenseType;						//传感器类型, 取值范围为NET_SENSE_METHOD中的值
    	public NET_MSG_HANDLE_EX stuEventHandler;	//联动信息
    	public int emDefenceAreaType;				//防区类型, 取值类型为EM_NET_DEFENCE_AREA_TYPE中的值
    	public ALARM_ALARM_INFO_EX2() {
    		this.dwSize = this.size();
    	}
    }
    
    ///////////////////////白名单相关结构体/////////////////////////////
    // CLIENT_FindRecord接口输入参数
    public static class NET_IN_FIND_RECORD_PARAM extends Structure {
        public int                       dwSize;          							 // 结构体大小
        public int                       emType;          							 // 待查询记录类型,emType对应  EM_NET_RECORD_TYPE
        public Pointer                   pQueryCondition;							 // 查询类型对应的查询条件 =1时，是白名单账户记录, 查询条件对应 FIND_RECORD_TRAFFICREDLIST_CONDITION 结构体,记录信息对应 NET_TRAFFIC_LIST_RECORD 结构体    
        
        public NET_IN_FIND_RECORD_PARAM() {
        	this.dwSize = this.size();
        }
    }
    
    // 交通黑白名单账户记录查询条件
    public static class FIND_RECORD_TRAFFICREDLIST_CONDITION extends Structure {
    	public int          dwSize;
        public byte[]       szPlateNumber = new byte[NET_MAX_PLATE_NUMBER_LEN];      // 车牌号
        public byte[]       szPlateNumberVague = new byte[NET_MAX_PLATE_NUMBER_LEN]; // 车牌号码模糊查询
        public int          nQueryResultBegin;                          			 // 第一个条返回结果在查询结果中的偏移量 
        public boolean      bRapidQuery;       										 // 是否快速查询, TRUE:为快速,快速查询时不等待所有增、删、改操作完成。默认为非快速查询
        
        public FIND_RECORD_TRAFFICREDLIST_CONDITION() {
        	this.dwSize = this.size();
        }
    }
   
    // 记录集类型
    public static class EM_NET_RECORD_TYPE extends Structure {
        public static final int NET_RECORD_UNKNOWN = 0;
        public static final int NET_RECORD_TRAFFICREDLIST = 1; 					 // 交通白名单账户记录, 查询条件对应 FIND_RECORD_TRAFFICREDLIST_CONDITION 结构体,记录信息对应 NET_TRAFFIC_LIST_RECORD 结构体    
        public static final int NET_RECORD_TRAFFICBLACKLIST = 2;  				 // 交通黑名单账号记录,查询条件对应 FIND_RECORD_TRAFFICREDLIST_CONDITION 结构体,记录信息对应 NET_TRAFFIC_LIST_RECORD 结构体       
        public static final int NET_RECORD_BURN_CASE = 3;      					 // 刻录案件记录,查询条件对应 FIND_RECORD_BURN_CASE_CONDITION 结构体,记录信息对应 NET_BURN_CASE_INFO 结构体
        public static final int NET_RECORD_ACCESSCTLCARD = 4;  					 // 门禁卡,查询条件对应 FIND_RECORD_ACCESSCTLCARD_CONDITION 结构体,记录信息对应 NET_RECORDSET_ACCESS_CTL_CARD 结构体
        public static final int NET_RECORD_ACCESSCTLPWD = 5;      				 // 门禁密码,查询条件对应 FIND_RECORD_ACCESSCTLPWD_CONDITION 结构体,记录信息对应 NET_RECORDSET_ACCESS_CTL_PWD
        public static final int NET_RECORD_ACCESSCTLCARDREC = 6; 				 // 门禁出入记录（必须同时按卡号和时间段查询,建议用NET_RECORD_ACCESSCTLCARDREC_EX查询）,查询条件对应 FIND_RECORD_ACCESSCTLCARDREC_CONDITION 结构体,记录信息对应 NET_RECORDSET_ACCESS_CTL_CARDREC 结构体 
        public static final int NET_RECORD_ACCESSCTLHOLIDAY = 7; 				 // 假日记录集,查询条件对应 FIND_RECORD_ACCESSCTLHOLIDAY_CONDITION 结构体,记录信息对应 NET_RECORDSET_HOLIDAY 结构体
        public static final int NET_RECORD_TRAFFICFLOW_STATE = 8;  				 // 查询交通流量记录,查询条件对应 FIND_RECORD_TRAFFICFLOW_CONDITION 结构体,记录信息对应 NET_RECORD_TRAFFIC_FLOW_STATE 结构体
        public static final int NET_RECORD_VIDEOTALKLOG = 9;    				 // 通话记录,查询条件对应 FIND_RECORD_VIDEO_TALK_LOG_CONDITION 结构体,记录信息对应 NET_RECORD_VIDEO_TALK_LOG 结构体
        public static final int NET_RECORD_REGISTERUSERSTATE = 10;  			 // 状态记录,查询条件对应 FIND_RECORD_REGISTER_USER_STATE_CONDITION 结构体,记录信息对应 NET_RECORD_REGISTER_USER_STATE 结构体
        public static final int NET_RECORD_VIDEOTALKCONTACT = 11;  				 // 联系人记录,查询条件对应 FIND_RECORD_VIDEO_TALK_CONTACT_CONDITION 结构体,记录信息对应 NET_RECORD_VIDEO_TALK_CONTACT 结构体
        public static final int NET_RECORD_ANNOUNCEMENT = 12;					 //公告记录,查询条件对应 FIND_RECORD_ANNOUNCEMENT_CONDITION 结构体,记录信息对应 NET_RECORD_ANNOUNCEMENT_INFO 结构体    														
        public static final int NET_RECORD_ALARMRECORD = 13; 					 //报警记录,查询条件对应 FIND_RECORD_ALARMRECORD_CONDITION 结构体,记录信息对应 NET_RECORD_ALARMRECORD_INFO 结构体
        public static final int NET_RECORD_COMMODITYNOTICE = 14;  				 // 下发商品记录,查询条件对应 FIND_RECORD_COMMODITY_NOTICE_CONDITION 结构体,记录信息对应 NET_RECORD_COMMODITY_NOTICE 结构体                                                          
        public static final int NET_RECORD_HEALTHCARENOTICE = 15;  				 // 就诊信息记录,查询条件对应 FIND_RECORD_HEALTH_CARE_NOTICE_CONDITION 结构体,记录信息对应 NET_RECORD_HEALTH_CARE_NOTICE 结构体
        public static final int NET_RECORD_ACCESSCTLCARDREC_EX = 16; 			 // 门禁出入记录(可选择部分条件查询,建议替代NET_RECORD_ACCESSCTLCARDREC),查询条件对应 FIND_RECORD_ACCESSCTLCARDREC_CONDITION_EX 结构体,记录信息对应 NET_RECORDSET_ACCESS_CTL_CARDREC 结构体
        public static final int NET_RECORD_GPS_LOCATION = 17;  					 // GPS位置信息记录, 只实现import和clear,记录信息对应 NET_RECORD_GPS_LOCATION_INFO 结构体
        public static final int NET_RECORD_RESIDENT = 18;      					 // 公租房租户信息,查询条件对应 FIND_RECORD_RESIDENT_CONDTION结构体, 记录信息对应 NET_RECORD_RESIDENT_INFO 结构体
        public static final int NET_RECORD_SENSORRECORD = 19;   			 	 // 监测量数据记录,查询条件对应 FIND_RECORD_SENSORRECORD_CONDITION 结构体,记录信息对应 NET_RECORD_SENSOR_RECORD 结构体      
        public static final int NET_RECORD_ACCESSQRCODE = 20;  					 //开门二维码记录集,记录信息对应 NET_RECORD_ACCESSQRCODE_INFO结构体
    }
 
    //交通黑白名单记录信息
    public static class NET_TRAFFIC_LIST_RECORD extends Structure {
    	  public int                      dwSize; 
    	  public int                  nRecordNo;                                // 之前查询到的记录号
    	  public byte[]      szMasterOfCar = new byte[NET_MAX_NAME_LEN];        // 车主姓名
    	  public byte[]      szPlateNumber = new byte[NET_MAX_PLATE_NUMBER_LEN];// 车牌号码 
    	  public int          emPlateType;                               		// 车牌类型,对应EM_NET_PLATE_TYPE
    	  public int          emPlateColor;                              		// 车牌颜色 ，对应EM_NET_PLATE_COLOR_TYPE
    	  public int          emVehicleType;                             		// 车辆类型 ，对应EM_NET_VEHICLE_TYPE
    	  public int         emVehicleColor;                         		    // 车身颜色，对应EM_NET_VEHICLE_COLOR_TYPE
    	  public NET_TIME                   stBeginTime;                        // 开始时间
    	  public NET_TIME                   stCancelTime;                       // 撤销时间
    	  public int                        nAuthrityNum;                       // 权限个数
    	  public NET_AUTHORITY_TYPE[]  stAuthrityTypes = (NET_AUTHORITY_TYPE[])new NET_AUTHORITY_TYPE().toArray(NET_MAX_AUTHORITY_LIST_NUM); // 权限列表 , 白名单仅有
    	  public int             emControlType;                    			    // 布控类型 ,黑名单仅有，对应EM_NET_TRAFFIC_CAR_CONTROL_TYPE
    	  
    	  public NET_TRAFFIC_LIST_RECORD() {
    		  this.dwSize = this.size();
    	  }
    }
 
    //权限列表 , 白名单仅有
    public static class NET_AUTHORITY_TYPE extends Structure {
    	  public int                        dwSize; 
    	  public int               emAuthorityType;                 		  //权限类型，对应EM_NET_AUTHORITY_TYPE
    	  public boolean          bAuthorityEnable;                 		  //权限使能
    	  
    	  public NET_AUTHORITY_TYPE() {
    		  this.dwSize = this.size();
    	  }
    }

    //权限类型
    public static class EM_NET_AUTHORITY_TYPE extends Structure {
    	public static final int NET_AUTHORITY_UNKNOW = 0;
    	public static final int NET_AUTHORITY_OPEN_GATE = 1;                 //开闸权限
    }

    // CLIENT_FindRecord接口输出参数
    public static class NET_OUT_FIND_RECORD_PARAM extends Structure {
    	 public int                     dwSize;          					// 结构体大小
    	 public NativeLong              lFindeHandle;    					// 查询记录句柄,唯一标识某次查询
    	 
    	 public NET_OUT_FIND_RECORD_PARAM() {
    		 this.dwSize = this.size();
    	 }
    }
    
    // CLIENT_FindNextRecord接口输入参数
    public static class NET_IN_FIND_NEXT_RECORD_PARAM extends Structure {
        public int                      dwSize;          					// 结构体大小
        public NativeLong               lFindeHandle;    					// 查询句柄
        public int                      nFileCount;      					// 当前想查询的记录条数
        
        public NET_IN_FIND_NEXT_RECORD_PARAM() {
        	this.dwSize = this.size();
        }
    }
    
    //CLIENT_FindNextRecord接口输出参数
    public static class NET_OUT_FIND_NEXT_RECORD_PARAM extends Structure {
        public int                     dwSize;          					// 结构体大小
        public Pointer                 pRecordList;     				    // 记录列表,用户分配内存，对应 交通黑白名单记录信息 NET_TRAFFIC_LIST_RECORD
        public int                     nMaxRecordNum;   					// 列表记录数
        public int                     nRetRecordNum;   					// 查询到的记录条数,当查询到的条数小于想查询的条数时,查询结束
        
        public NET_OUT_FIND_NEXT_RECORD_PARAM() {
        	this.dwSize = this.size();
        }
    }
    
    // CLIENT_OperateTrafficList接口输入参数,
    public static class NET_IN_OPERATE_TRAFFIC_LIST_RECORD extends Structure {
        public int                       dwSize;
        public int                       emOperateType;  					 // emOperateType对应EM_RECORD_OPERATE_TYPE
        public int                       emRecordType;    					 // 要操作记录信息类型,emRecordType对应EM_NET_RECORD_TYPE
        public Pointer                   pstOpreateInfo;  				   	// 对应 添加NET_INSERT_RECORD_INFO/ 删除NET_REMOVE_RECORD_INFO / 修改NET_UPDATE_RECORD_INFO
        
        public NET_IN_OPERATE_TRAFFIC_LIST_RECORD() {
        	this.dwSize = this.size();
        }
    }
    // 添加
    public static class NET_INSERT_RECORD_INFO extends Structure {
        public int                       dwSize;
        public Pointer                   pRecordInfo;   					 // 记录内容信息 ,对应NET_TRAFFIC_LIST_RECORD指针
        
        public NET_INSERT_RECORD_INFO () {
        	this.dwSize = this.size();
        }
    }
    // 删除
    public static class NET_REMOVE_RECORD_INFO extends Structure {
        public int                      dwSize;
        public int                      nRecordNo;      			    	 // 之前查询到的记录号，对应NET_TRAFFIC_LIST_RECORD里的nRecordNo
        
        public NET_REMOVE_RECORD_INFO() {
        	this.dwSize = this.size();
        }
    }
    // 修改
    public static class NET_UPDATE_RECORD_INFO extends Structure{
        public int                     dwSize;
        public Pointer 				   pRecordInfo;    					    // 记录内容信息 ，对应NET_TRAFFIC_LIST_RECORD指针
        
        public NET_UPDATE_RECORD_INFO() {
        	this.dwSize = this.size();
        }
    }
    
    // 黑白名单操作类型
    public static class EM_RECORD_OPERATE_TYPE extends Structure {
        public static final int NET_TRAFFIC_LIST_INSERT = 0;               // 增加记录操作
        public static final int NET_TRAFFIC_LIST_UPDATE = 1;               // 更新记录操作
        public static final int NET_TRAFFIC_LIST_REMOVE = 2;               // 删除记录操作
        public static final int NET_TRAFFIC_LIST_MAX = 3;
    }
    
    // CLIENT_OperateTrafficList接口输出参数,现阶段实现的操作接口中,只有返回nRecordNo的操作,stRetRecord暂时不可用,是null
    public static class NET_OUT_OPERATE_TRAFFIC_LIST_RECORD extends Structure {
        public int                     dwSize;
        public int                     nRecordNo;        //记录号 
        
        public NET_OUT_OPERATE_TRAFFIC_LIST_RECORD() {
        	this.dwSize = this.size();
        }
    }
    
    // 黑白名单上传
    public static class NETDEV_BLACKWHITE_LIST_INFO extends Structure {
        public byte[]        						  szFile = new byte[MAX_PATH_STOR];      // 黑白名单文件路径
        public int                                    nFileSize;            				 // 升级文件大小
        public byte                  			      byFileType;         					 // 当前文件类型,0-黑名单,1-白名单 
        public byte                   			      byAction;            					 // 动作,0-覆盖,1-追加
        public byte[]      		   					  byReserved = new byte[126];            // 保留
    }
       
    
    /************************************************************************
     ** 回调
     ***********************************************************************/
    //JNA CALLBACK方法定义,断线回调
    public interface fDisConnect extends StdCallCallback {
        public int invoke(NativeLong lLoginID, String pchDVRIP, int nDVRPort, NativeLong dwUser);
    }

    // 网络连接恢复回调函数原形
    public interface fHaveReConnect extends StdCallCallback {
        public int invoke(NativeLong lLoginID, String pchDVRIP, int nDVRPort, NativeLong dwUser);
    }
    
    // 消息回调函数原形(pBuf内存由SDK内部申请释放)
    public interface fMessCallBack extends StdCallCallback{
        public boolean invoke( NativeLong lCommand , NativeLong lLoginID , Pointer pStuEvent , int dwBufLen , String strDeviceIP ,  NativeLong nDevicePort , NativeLong dwUser);
    }
    
    public interface fFaceFindState extends StdCallCallback {
        // pstStates 指向NET_CB_FACE_FIND_STATE的指针
        public int invoke(NativeLong lLoginID, NativeLong lAttachHandle, Pointer pstStates, int nStateNum, NativeLong dwUser);
    }
    
    // 智能分析数据回调;nSequence表示上传的相同图片情况，为0时表示是第一次出现，为2表示最后一次出现或仅出现一次，为1表示此次之后还有
    // int nState = *(int*) reserved 表示当前回调数据的状态, 为0表示当前数据为实时数据，为1表示当前回调数据是离线数据，为2时表示离线数据传送结束
    public interface fAnalyzerDataCallBack extends StdCallCallback {
        public int invoke(NativeLong lAnalyzerHandle, int dwAlarmType, Pointer pAlarmInfo, Pointer pBuffer, int dwBufSize, Pointer dwUser, int nSequence, Pointer reserved);
    }
    
    // 抓图回调函数原形(pBuf内存由SDK内部申请释放)
    // EncodeType 编码类型，10：表示jpeg图片      0：mpeg4
    public interface fSnapRev extends StdCallCallback{
        public boolean invoke( NativeLong lLoginID ,Pointer pBuf, int RevLen, int EncodeType, NativeLong CmdSerial, NativeLong dwUser);
    }
    
    // 异步搜索设备回调(pDevNetInfo内存由SDK内部申请释放)
    public interface fSearchDevicesCB extends StdCallCallback{
        public int invoke(Pointer pDevNetInfo, Pointer pUserData);
    }
    
    // 按时间回放进度回调函数原形
    public interface fTimeDownLoadPosCallBack extends StdCallCallback {    
        public void invoke(NativeLong lPlayHandle, int dwTotalSize, int dwDownLoadSize, int index, NET_RECORDFILE_INFO.ByValue recordfileinfo, NativeLong dwUser);
    } 
    
    // 回放数据回调函数原形
    public interface fDataCallBack extends StdCallCallback {
        public int invoke(NativeLong lRealHandle, int dwDataType, Pointer pBuffer, int dwBufSize, NativeLong dwUser);
    }
    
    // 回放进度回调函数原形
    public interface fDownLoadPosCallBack extends StdCallCallback {
    	public void invoke(NativeLong lPlayHandle, int dwTotalSize, int dwDownLoadSize, NativeLong dwUser);
    }
    
    // 视频统计摘要信息回调函数原形，lAttachHandle 是 CLIENT_AttachVideoStatSummary 返回值
    public interface fVideoStatSumCallBack extends StdCallCallback {
    	public int invoke(NativeLong lAttachHandle, NET_VIDEOSTAT_SUMMARY pBuf, int dwBufLen, NativeLong dwUser);
    }

    // lHandle是文件传输句柄 ，nTransType是文件传输类型，nState是文件传输状态，
    public interface fTransFileCallBack extends StdCallCallback {
    	public void invoke(NativeLong lHandle, int nTransType, int nState, int nSendSize, int nTotalSize, NativeLong  dwUser);
    }    

    /************************************************************************
     ** 接口
     ***********************************************************************/
    //  JNA直接调用方法定义，cbDisConnect实际情况并不回调Java代码，仅为定义可以使用如下方式进行定义。
    public boolean CLIENT_Init(fDisConnect cbDisConnect, NativeLong dwUser);
    
    //  JNA直接调用方法定义，SDK退出清理
    public void CLIENT_Cleanup();
    
    //  JNA直接调用方法定义，设置断线重连成功回调函数，设置后SDK内部断线自动重连
    public void CLIENT_SetAutoReconnect(fHaveReConnect cbAutoConnect, NativeLong dwUser);
    
    // 返回函数执行失败代码
    public int CLIENT_GetLastError();

    // 设置连接设备超时时间和尝试次数
    public void CLIENT_SetConnectTime(int nWaitTime, int nTryTimes);

    // 设置登陆网络环境
    public void CLIENT_SetNetworkParam(NET_PARAM pNetParam);

    // 获取SDK的版本信息
    public int CLIENT_GetSDKVersion();
    
    //  JNA直接调用方法定义，登陆接口
    public NativeLong CLIENT_LoginEx(String pchDVRIP, short wDVRPort, String pchUserName, String pchPassword, int nSpecCap, Pointer pCapParam, NET_DEVICEINFO lpDeviceInfo, int[] error/*= 0*/);
    
    //  JNA直接调用方法定义，登陆扩展接口///////////////////////////////////////////////////
    //  nSpecCap 对应 EM_LOGIN_SPAC_CAP_TYPE登陆类型
    public NativeLong CLIENT_LoginEx2(String pchDVRIP, short wDVRPort, String pchUserName, String pchPassword, int nSpecCap, Pointer pCapParam, NET_DEVICEINFO_Ex lpDeviceInfo, int[] error/*= 0*/);
    
    //  JNA直接调用方法定义，向设备注销
    public boolean CLIENT_Logout(NativeLong lLoginID);
    
    // 获取配置
    // error 为设备返回的错误码： 0-成功 1-失败 2-数据不合法 3-暂时无法设置 4-没有权限
    public boolean CLIENT_GetNewDevConfig(NativeLong lLoginID , String szCommand , int nChannelID , byte[] szOutBuffer , int dwOutBufferSize , int[] error , int waiitime);
    
    // 设置配置
    public boolean CLIENT_SetNewDevConfig(NativeLong lLoginID , String szCommand , int nChannelID , byte[] szInBuffer, int dwInBufferSize, int[] error, int[] restart, int waittime );
    
    // 解析查询到的配置信息
    public boolean CLIENT_ParseData(String szCommand, byte[] szInBuffer, Pointer lpOutBuffer, int dwOutBufferSize, Pointer pReserved);

    // 组成要设置的配置信息
    public boolean CLIENT_PacketData(String szCommand, Pointer lpInBuffer, int dwInBufferSize, byte[] szOutBuffer, int dwOutBufferSize);

    // 设置报警回调函数
    public void  CLIENT_SetDVRMessCallBack(fMessCallBack cbMessage , NativeLong dwUser);
    
    // 向设备订阅报警--扩展
    public boolean  CLIENT_StartListenEx(NativeLong lLoginID);

    /////////////////////////////////人脸识别接口/////////////////////////////////////////
    //人脸识别数据库信息操作（包括添加,修改和删除）
    // pstInParam指向NET_IN_OPERATE_FACERECONGNITIONDB类型的指针
    // pstOutParam指向NET_OUT_OPERATE_FACERECONGNITIONDB类型的指针
    public boolean  CLIENT_OperateFaceRecognitionDB(NativeLong lLoginID, final NET_IN_OPERATE_FACERECONGNITIONDB pstInParam, NET_OUT_OPERATE_FACERECONGNITIONDB pstOutParam, int nWaitTime);
    
    //按条件查询人脸识别结果 
    // pstInParam指向NET_IN_STARTFIND_FACERECONGNITION类型的指针
    // pstOutParam指向NET_OUT_STARTFIND_FACERECONGNITION类型的指针
    public boolean  CLIENT_StartFindFaceRecognition(NativeLong lLoginID, final NET_IN_STARTFIND_FACERECONGNITION pstInParam, NET_OUT_STARTFIND_FACERECONGNITION pstOutParam, int nWaitTime);
    
    //查找人脸识别结果:nFilecount:需要查询的条数, 返回值为媒体文件条数 返回值<nFilecount则相应时间段内的文件查询完毕(每次最多只能查询20条记录)
    // pstInParam指向NET_IN_DOFIND_FACERECONGNITION类型的指针
    // pstOutParam指向NET_OUT_DOFIND_FACERECONGNITION类型的指针
    public boolean  CLIENT_DoFindFaceRecognition(final NET_IN_DOFIND_FACERECONGNITION pstInParam, NET_OUT_DOFIND_FACERECONGNITION pstOutParam, int nWaitTime);
    
    //结束查询
    public boolean  CLIENT_StopFindFaceRecognition(NativeLong lFindHandle);
    
    //人脸检测(输入一张大图,输入大图中被检测出来的人脸图片)
    // pstInParam指向NET_IN_DETECT_FACE类型的指针
    // pstOutParam指向NET_OUT_DETECT_FACE类型的指针
    public boolean  CLIENT_DetectFace(NativeLong lLoginID, final NET_IN_DETECT_FACE pstInParam, NET_OUT_DETECT_FACE pstOutParam, int nWaitTime);
    
    //人脸识别人员组操作（包括添加,修改和删除）
    // pstInParam指向NET_IN_OPERATE_FACERECONGNITION_GROUP类型的指针
    // pstOutParam指向NET_OUT_OPERATE_FACERECONGNITION_GROUP类型的指针
    public boolean  CLIENT_OperateFaceRecognitionGroup(NativeLong lLoginID, final NET_IN_OPERATE_FACERECONGNITION_GROUP pstInParam, NET_OUT_OPERATE_FACERECONGNITION_GROUP pstOutParam, int nWaitTime);
    
    //查询人脸识别人员组信息
    // pstInParam指向NET_IN_FIND_GROUP_INFO类型的指针
    // pstOutParam指向NET_OUT_FIND_GROUP_INFO类型的指针
    public boolean  CLIENT_FindGroupInfo(NativeLong NativeLong, final NET_IN_FIND_GROUP_INFO pstInParam, NET_OUT_FIND_GROUP_INFO pstOutParam, int nWaitTime);
    
    //布控通道人员组信息
    // pstInParam指向NET_IN_SET_GROUPINFO_FOR_CHANNEL类型的指针
    // pstOutParam指向NET_OUT_SET_GROUPINFO_FOR_CHANNEL类型的指针
    public boolean CLIENT_SetGroupInfoForChannel(NativeLong lLoginID, final NET_IN_SET_GROUPINFO_FOR_CHANNEL pstInParam, NET_OUT_SET_GROUPINFO_FOR_CHANNEL pstOutParam, int nWaitTime);
    
    //订阅人脸查询状态
    // pstInParam指向NET_IN_FACE_FIND_STATE类型的指针
    // pstOutParam指向NET_OUT_FACE_FIND_STATE类型的指针
    public NativeLong CLIENT_AttachFaceFindState(NativeLong lLoginID, final NET_IN_FACE_FIND_STATE pstInParam, NET_OUT_FACE_FIND_STATE pstOutParam, int nWaitTime);
    
    //取消订阅人脸查询状态,lAttachHandle为CLIENT_AttachFaceFindState返回的句柄
    public boolean CLIENT_DetachFaceFindState(NativeLong lAttachHandle);
    
    // 打开日志功能
    // pstLogPrintInfo指向LOG_SET_PRINT_INFO的指针
    public boolean CLIENT_LogOpen(LOG_SET_PRINT_INFO pstLogPrintInfo);

    // 关闭日志功能
    public boolean CLIENT_LogClose();
    
    // 按查询条件查询文件
    // pQueryCondition为void *, 具体类型根据emType的类型确定
    // reserved为void *, 具体类型根据emType的类型确定
    public NativeLong CLIENT_FindFileEx(NativeLong lLoginID, int emType, Pointer pQueryCondition, Pointer reserved, int waittime);
    
    // 获取符合查询条件的文件总数
    // reserved为void *
    public boolean CLIENT_GetTotalFileCount(NativeLong lFindHandle, int[] pTotalCount,  Pointer reserved, int waittime);
    
    // 设置查询跳转条件
    // reserved为void *
    public boolean  CLIENT_SetFindingJumpOption(NativeLong lFindHandle, NET_FINDING_JUMP_OPTION_INFO pOption, Pointer reserved, int waittime);
    
    // 查找文件:nFilecount:需要查询的条数, 返回值为媒体文件条数 返回值<nFilecount则相应时间段内的文件查询完毕
    // pMediaFileInfo为void *
    // reserved为void *
    public int CLIENT_FindNextFileEx(NativeLong lFindHandle, int nFilecount, Pointer pMediaFileInfo, int maxlen, Pointer reserved, int waittim);
    
    // 结束录像文件查找
    public boolean CLIENT_FindCloseEx(NativeLong lFindHandle);
    
    // 实时上传智能分析数据－图片(扩展接口,bNeedPicFile表示是否订阅图片文件,Reserved类型为RESERVED_PARA) 
    // bNeedPicFile为BOOL类型，取值范围为0或者1
    public NativeLong CLIENT_RealLoadPictureEx(NativeLong lLoginID, int nChannelID, int dwAlarmType, int bNeedPicFile, fAnalyzerDataCallBack cbAnalyzerData, Pointer dwUser, Pointer Reserved);
    
    // 停止上传智能分析数据－图片
    public boolean CLIENT_StopLoadPic(NativeLong lAnalyzerHandle);
    
    // 设置抓图回调函数
    public void CLIENT_SetSnapRevCallBack(fSnapRev OnSnapRevMessage, NativeLong dwUser);
    
    // 抓图请求扩展接口
    public boolean CLIENT_SnapPictureEx(NativeLong lLoginID, SNAP_PARAMS stParam, int[] reserved);
    
    // 异步搜索局域网内IPC、NVS等设备
    public NativeLong CLIENT_StartSearchDevices(fSearchDevicesCB cbSearchDevices, Pointer pUserData, Pointer szLocalIp);
    
    // 停止异步搜索局域网内IPC、NVS等设备
    public boolean CLIENT_StopSearchDevices(NativeLong lSearchHandle);
    
    // 开始实时监视
    // rType  : NET_RealPlayType
    public NativeLong CLIENT_RealPlayEx(NativeLong lLoginID, int nChannelID, HWND hWnd, int rType);
    
    // 停止实时预览
    public boolean CLIENT_StopRealPlayEx(NativeLong lRealHandle);
    
    // 打开声音
    public boolean CLIENT_OpenSound(NativeLong hPlayHandle);
    
    // 关闭声音
    public boolean CLIENT_CloseSound();
    
    // 获取所有有效显示源
    // pInParam  对应  NET_IN_MATRIX_GET_CAMERAS
    // pOutParam 对应  NET_OUT_MATRIX_GET_CAMERAS
    public boolean CLIENT_MatrixGetCameras(NativeLong lLoginID, NET_IN_MATRIX_GET_CAMERAS pInParam, NET_OUT_MATRIX_GET_CAMERAS pOutParam, int nWaitTime);

    // 抓图同步接口,将图片数据直接返回给用户
    public boolean CLIENT_SnapPictureToFile(NativeLong lLoginID, final NET_IN_SNAP_PIC_TO_FILE_PARAM pInParam, NET_OUT_SNAP_PIC_TO_FILE_PARAM pOutParam, int nWaitTime);
    
    // 通过时间下载录像--扩展
    // nRecordFileType 对应 EM_QUERY_RECORD_TYPE
    public NativeLong CLIENT_DownloadByTimeEx(
            NativeLong lLoginID, 
            int nChannelId, 
            int nRecordFileType, 
            NET_TIME tmStart, 
            NET_TIME tmEnd, 
            String sSavedFileName, 
            fTimeDownLoadPosCallBack cbTimeDownLoadPos, 
            NativeLong dwUserData, 
            fDataCallBack fDownLoadDataCallBack, 
            NativeLong dwDataUser, 
            Pointer pReserved);
    
    // 停止录像下载
    public boolean CLIENT_StopDownload(NativeLong lFileHandle);

    // 云台控制扩展接口,支持三维快速定位,鱼眼
    // dwStop类型为BOOL, 取值0或者1
    // dwPTZCommand取值为NET_EXTPTZ_ControlType中的值或者是NET_PTZ_ControlType中的值
    public boolean CLIENT_DHPTZControlEx2(NativeLong lLoginID, int nChannelID, int dwPTZCommand, int lParam1, int lParam2, int lParam3, int dwStop, Pointer param4);
       
    // 设备控制扩展接口，兼容 CLIENT_ControlDevice (pInBuf, pOutBuf内存由用户申请释放)
    // emType的取值为CtrlType中的值
    public boolean CLIENT_ControlDeviceEx(NativeLong lLoginID, int emType, Pointer pInBuf, Pointer pOutBuf, int nWaitTime);
    
    // 查询配置信息(lpOutBuffer内存由用户申请释放)
    public boolean CLIENT_GetDevConfig(NativeLong lLoginID, int dwCommand, int lChannel, Pointer lpOutBuffer, int dwOutBufferSize, Pointer lpBytesReturned,int waittime);

    // 设置配置信息(lpInBuffer内存由用户申请释放)
    public boolean CLIENT_SetDevConfig(NativeLong lLoginID, int dwCommand, int lChannel, Pointer lpInBuffer, int dwInBufferSize, int waittime);
    
    // 查询设备状态(pBuf内存由用户申请释放)
    // pBuf指向char *
    // pRetLen指向int *
    public boolean CLIENT_QueryDevState(NativeLong lLoginID, int nType, Pointer pBuf, int nBufLen, Pointer pRetLen, int waittime);
    
    // 停止订阅报警
    public boolean CLIENT_StopListen(NativeLong lLoginID);
    
    // 新系统能力查询接口，查询系统能力信息(以Json格式，具体见配置SDK)(szOutBuffer内存由用户申请释放)
    // szCommand: 对应命令查看上文
    // szOutBuffer: 获取到的信息, 通过 CLIENT_ParseData 解析
    // error 指向 int * ： 错误码大于0表示设备返回的，小于0表示缓冲不够或数据校验引起的
    public boolean CLIENT_QueryNewSystemInfo(NativeLong lLoginID, String szCommand, int nChannelID, byte[] szOutBuffer, int dwOutBufferSize, int[] error, int waittime);
    
    // 订阅视频统计摘要信息
    public NativeLong CLIENT_AttachVideoStatSummary(NativeLong lLoginID, NET_IN_ATTACH_VIDEOSTAT_SUM pInParam, NET_OUT_ATTACH_VIDEOSTAT_SUM pOutParam, int nWaitTime);

    // 取消订阅视频统计摘要信息
    public boolean CLIENT_DetachVideoStatSummary(NativeLong lAttachHandle);
    
    // 开始查询视频统计信息
    public NativeLong CLIENT_StartFindNumberStat(NativeLong lLoginID, NET_IN_FINDNUMBERSTAT pstInParam, NET_OUT_FINDNUMBERSTAT pstOutParam);

    // 继续查询视频统计
    public int CLIENT_DoFindNumberStat(NativeLong lFindHandle, NET_IN_DOFINDNUMBERSTAT pstInParam, NET_OUT_DOFINDNUMBERSTAT pstOutParam);

    // 结束查询视频统计
    public boolean CLIENT_StopFindNumberStat(NativeLong lFindHandle);
   
    // 设置语音对讲模式,客户端方式还是服务器方式
    // emType : 方式类型 参照 EM_USEDEV_MODE
    public boolean CLIENT_SetDeviceMode(NativeLong lLoginID, int emType, Pointer pValue);
    
    ///////////////// 录像回放相关接口 ///////////////////////
    // 按时间方式回放--扩展接口
    public NativeLong CLIENT_PlayBackByTimeEx(NativeLong lLoginID, int nChannelID, NET_TIME lpStartTime, NET_TIME lpStopTime, HWND hWnd, 
                                               fDownLoadPosCallBack cbDownLoadPos, NativeLong dwPosUser, 
                                               fDataCallBack fDownLoadDataCallBack, NativeLong dwDataUser);
    // 停止录像回放接口
    public boolean CLIENT_StopPlayBack(NativeLong lPlayHandle);
    
    // 获取回放OSD时间
    public boolean CLIENT_GetPlayBackOsdTime(NativeLong lPlayHandle, NET_TIME lpOsdTime, NET_TIME lpStartTime, NET_TIME lpEndTime);

    // 暂停或恢复录像回放
    // bPause: 1 - 暂停	0 - 恢复 
    public boolean CLIENT_PausePlayBack(NativeLong lPlayHandle, int bPause);
    
    // 快进录像回放
    public boolean CLIENT_FastPlayBack(NativeLong lPlayHandle);

    // 慢进录像回放
    public boolean CLIENT_SlowPlayBack(NativeLong lPlayHandle);
 
    // 恢复正常回放速度
    public boolean CLIENT_NormalPlayBack(NativeLong lPlayHandle);
    
    // 查询设备当前时间
    public boolean CLIENT_QueryDeviceTime(NativeLong lLoginID, NET_TIME pDeviceTime, int waittime);
    
    // 设置设备当前时间
    public boolean CLIENT_SetupDeviceTime(NativeLong lLoginID, NET_TIME pDeviceTime);
    // 获得亮度、色度、对比度、饱和度的参数      
    // param1/param2/param3/param4 四个参数范围0~255
  	public boolean CLIENT_ClientGetVideoEffect(NativeLong lPlayHandle, byte[] param1, byte[] param2, byte[] param3, byte[] param4);

  	// 设置亮度、色度、对比度、饱和度的参数    
  	// nBrightness/nContrast/nHue/nSaturation四个参数为 unsigned byte 范围0~255
  	public boolean CLIENT_ClientSetVideoEffect(NativeLong lPlayHandle, byte nBrightness, byte nContrast, byte nHue, byte nSaturation);    

  	// 查询设备信息
  	public boolean CLIENT_QueryDevInfo(NativeLong lLoginID, int nQueryType, Pointer pInBuf, Pointer pOutBuf, Pointer pReservedL, int nWaitTime);
    
    //////////////////// 白名单//////////////////////
    // 按查询条件查询记录          pInParam查询记录参数        pOutParam返回查询句柄  
    // 可以先调用本接口获得查询句柄，再调用  CLIENT_FindNextRecord函数获取记录列表，查询完毕可以调用CLIENT_FindRecordClose关闭查询句柄。 
    public boolean CLIENT_FindRecord(NativeLong lLoginID, NET_IN_FIND_RECORD_PARAM pInParam, NET_OUT_FIND_RECORD_PARAM pOutParam, int waittime);
    
    // 查找记录:nFilecount:需要查询的条数, 返回值为媒体文件条数 返回值小于nFilecount则相应时间段内的文件查询完毕
    public boolean CLIENT_FindNextRecord(NET_IN_FIND_NEXT_RECORD_PARAM pInParam, NET_OUT_FIND_NEXT_RECORD_PARAM pOutParam, int waittime);
    
    // 结束记录查找,lFindHandle是CLIENT_FindRecord的返回值 
    public boolean CLIENT_FindRecordClose(NativeLong lFindHandle);
    
    // 黑白名单操作 ,pstOutParam = null;
    public boolean CLIENT_OperateTrafficList(NativeLong lLoginID ,  NET_IN_OPERATE_TRAFFIC_LIST_RECORD pstInParam, NET_OUT_OPERATE_TRAFFIC_LIST_RECORD pstOutParam , int waittime);
    
    // 文件上传控制接口，白名单上传需要三个步骤配合使用，CLIENT_FileTransmit的 NET_DEV_BLACKWHITETRANS_START、  NET_DEV_BLACKWHITETRANS_SEND、   NET_DEV_BLACKWHITETRANS_STOP，如下所示
    public NativeLong CLIENT_FileTransmit(NativeLong lLoginID, int nTransType, Pointer szInBuf, int nInBufLen, fTransFileCallBack cbTransFile, NativeLong dwUserData, int waittime); 
    
 
    
}

