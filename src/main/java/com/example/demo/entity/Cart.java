package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartid;
	
	private int id;
	private String name;
	private int price;
	private String language;
	private String description;
	private String date;
	private String imageurl;
	private int catid;
	private String catname;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getCartid() {
		return cartid;
	}

	public void setCartid(int cartid) {
		this.cartid = cartid;
	}

	public Cart(int id, String name, int price, String language, String description, String date, String imageurl,
			int catid, String catname) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.language = language;
		this.description = description;
		this.date = date;
		this.imageurl = imageurl;
		this.catid = catid;
		this.catname = catname;
	}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Cart [cartid=" + cartid + ", id=" + id + ", name=" + name + ", price=" + price + ", language="
				+ language + ", description=" + description + ", date=" + date + ", imageurl=" + imageurl + ", catid="
				+ catid + ", catname=" + catname + "]";
	}

	
	
}
