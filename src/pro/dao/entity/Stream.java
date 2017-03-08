package pro.dao.entity;

import java.io.Serializable;

public class Stream implements Serializable {

	private static final long serialVersionUID = 1L;

	private int stream_id; // 上/下游的id
	private String stream_name; // 上/下游的名字
	private int stream_port; // 对应桥上/下游的端口号
	// 在多方定义一个一方的引用
	private User user;
	private float stream_minute; // 实时报表的时间间隔（分钟）
	private int count;

	public Stream() {
		super();
	}

	public Stream(int stream_id, String stream_name, int stream_port,
			User user, float stream_minute, int count) {
		super();
		this.stream_id = stream_id;
		this.stream_name = stream_name;
		this.stream_port = stream_port;
		this.user = user;
		this.stream_minute = stream_minute;
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getStream_id() {
		return stream_id;
	}

	public void setStream_id(int stream_id) {
		this.stream_id = stream_id;
	}

	public String getStream_name() {
		return stream_name;
	}

	public void setStream_name(String stream_name) {
		this.stream_name = stream_name;
	}

	public int getStream_port() {
		return stream_port;
	}

	public void setStream_port(int stream_port) {
		this.stream_port = stream_port;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getStream_minute() {
		return stream_minute;
	}

	public void setStream_minute(float stream_minute) {
		this.stream_minute = stream_minute;
	}

	@Override
	public String toString() {
		return "Stream [stream_id=" + stream_id + ", stream_name="
				+ stream_name + ", stream_port=" + stream_port + ", user_name="
				+ user.getUsername() + ", stream_minute=" + stream_minute + "]";
	}

}
