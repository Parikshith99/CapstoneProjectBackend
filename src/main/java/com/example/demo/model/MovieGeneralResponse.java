package com.example.demo.model;

public class MovieGeneralResponse {
	private int id;
	private String name;
	private int price;
	private String language;
	private String description;
	private String date;
	private String time;
	private String imageurl;
	private int catid;
	private String catname;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public int getCatid() {
		return catid;
	}
	public void setCatid(int catid) {
		this.catid = catid;
	}
	public String getCatname() {
		return catname;
	}
	public void setCatname(String catname) {
		this.catname = catname;
	}
	public MovieGeneralResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MovieGeneralResponse(int id, String name, int price, String language, String description, String date,
			String time, String imageurl, int catid, String catname) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.language = language;
		this.description = description;
		this.date = date;
		this.time = time;
		this.imageurl = imageurl;
		this.catid = catid;
		this.catname = catname;
	}
	
	
}
