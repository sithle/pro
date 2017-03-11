package pro.dao.entity;

import java.sql.Timestamp;
//import java.util.Date;

public class Alarm {


	private String info;
	private int id;
	private Timestamp starttime;
	private Timestamp endtime;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getStarttime() {
		return starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}
	public Timestamp getEndtime() {
		return endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Alarm(Timestamp starttime,Timestamp endtime,String info) {
		super();

		this.starttime = starttime;
		this.info = info;
		this.endtime = endtime;
		
	}
	
	public Alarm() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Alarm [ starttime=" + starttime + ", endtime=" + endtime
				+ ", info=" + info + "]";
	}
	
	
	
}
