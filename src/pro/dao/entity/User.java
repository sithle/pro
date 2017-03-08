package pro.dao.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int user_id; // 用户ID
	private String username; // 用户名
	private String password; // 密码
	private String bridge_name; // 桥名
	private float weight_standard; // 超重标准
	// 在一方定义一个多方的集合
	private Set<Stream> streams = new HashSet<Stream>();

	public User() {
		super();
	}

	public User(int user_id, String username, String password,
			String bridge_name, float weight_standard, Set<Stream> streams) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.bridge_name = bridge_name;
		this.weight_standard = weight_standard;
		this.streams = streams;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBridge_name() {
		return bridge_name;
	}

	public void setBridge_name(String bridge_name) {
		this.bridge_name = bridge_name;
	}

	public Set<Stream> getStreams() {
		return streams;
	}

	public void setStreams(Set<Stream> streams) {
		this.streams = streams;
	}

	public double getWeight_standard() {
		return weight_standard;
	}

	public void setWeight_standard(float weight_standard) {
		this.weight_standard = weight_standard;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username
				+ ", password=" + password + ", bridge_name=" + bridge_name
				+ "]";
	}

}
