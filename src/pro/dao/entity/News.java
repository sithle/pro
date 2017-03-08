package pro.dao.entity;

import java.util.Date;

public class News {
	private Integer id;
	private String title;
	private String author;
	private String author2;
	private Date date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthor2() {
		return author2;
	}

	public void setAuthor2(String author2) {
		this.author2 = author2;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public News(String title, String author, Date date,String author2) {
		super();
		this.title = title;
		this.author = author;
		this.date = date;
		this.author2 = author2;
	}
	
	public News() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", author=" + author
				+ ", date=" + date + "]";
	}
	
	
	
}
