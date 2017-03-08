package pro.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * JavaBean car
 * 
 * @author lc
 * 
 */
public class Car2 implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private Timestamp datetime; // 时间（yyyy-MM-dd hh:mm:ss）
	private String lane; // 车道
	private String velocity; // 速度（km/h）
	private Double weight; // 总轴重（吨）
	private String axis; // 轴数
	private String carnumber; // 车牌号
	private String photo; // 照片地址
	private String stream; // 对应桥上/下游
	private String ischaozhong;
	private String ispicture;
	private String zhou1;
	private String zhou2;
	private String zhou3;
	private String zhou4;
	private String zhou5;
	private String zhou6;
	private String zhou7;
	private String zhou8;
	private String flag;
	private String videopath;
	public Car2() {
		super();
	}

	public Car2(Timestamp datetime, String lane, String velocity, Double weight,
			String axis,String carnumber, String photo, String stream,String ischaozhong,String ispicture,String zhou1,String zhou2,String zhou3,String zhou4,String zhou5,String zhou6,String zhou7,String zhou8,String flag,String videopath) {
		super();
		this.datetime = datetime;
		this.lane = lane;
		
		this.velocity = velocity;
		this.weight = weight;
		this.axis = axis;
		this.carnumber = carnumber;
		this.photo = photo;
		this.stream = stream;
		this.ischaozhong = ischaozhong;
		this.ispicture=ispicture;
		this.zhou1=zhou1;
		this.zhou2=zhou2;
		this.zhou3=zhou3;
		this.zhou4=zhou4;
		this.zhou5=zhou5;
		this.zhou6=zhou7;
		this.zhou7=zhou7;
		this.zhou8=zhou8;
		this.flag=flag;
		this.videopath=videopath;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDatetime() {
		return datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	public String getVelocity() {
		return velocity;
	}

	public void setVelocity(String velocity) {
		this.velocity = velocity;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getAxis() {
		return axis;
	}

	public void setAxis(String axis) {
		this.axis = axis;
	}

	public String getCarnumber() {
		return carnumber;
	}

	public void setCarnumber(String carnumber) {
		this.carnumber = carnumber;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}
	public String getIschaozhong() {
		return ischaozhong;
	}

	public void setIschaozhong(String ischaozhong) {
		this.ischaozhong = ischaozhong;
	}
	public String getIspicture() {
		return ispicture;
	}

	public void setIspicture(String ispicture) {
		this.ispicture = ispicture;
	}
	public String getZhou1() {
		return zhou1;
	}

	public void setZhou1(String zhou1) {
		this.zhou1 = zhou1;
	}
	public String getZhou2() {
		return zhou2;
	}

	public void setZhou2(String zhou2) {
		this.zhou2 = zhou2;
	}
	public String getZhou3() {
		return zhou3;
	}

	public void setZhou3(String zhou3) {
		this.zhou3 = zhou3;
	}
	public String getZhou4() {
		return zhou4;
	}

	public void setZhou4(String zhou4) {
		this.zhou4 = zhou4;
	}
	public String getZhou5() {
		return zhou5;
	}

	public void setZhou5(String zhou5) {
		this.zhou5 = zhou5;
	}
	public String getZhou6() {
		return zhou6;
	}

	public void setZhou6(String zhou6) {
		this.zhou6 = zhou6;
	}
	public String getZhou7() {
		return zhou7;
	}

	public void setZhou7(String zhou7) {
		this.zhou7 = zhou7;
	}
	public String getZhou8() {
		return zhou8;
	}

	public void setZhou8(String zhou8) {
		this.zhou8 = zhou8;
	}
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getVideopath() {
		return videopath;
	}

	public void setVideopath(String videopath) {
		this.videopath = videopath;
	}

	@Override
	public String toString() {
		return "Car [datetime=" + datetime + ", lane=" + lane + ", velocity="
				+ velocity + ", weight=" + weight + ", axis=" + axis
				+ ", carnumber=" + carnumber + ", photo=" + photo + ", stream="
				+ stream + "]";
	}

}
