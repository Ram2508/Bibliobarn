package com.bibliobarn.beans;

public class Order {

	int userid;
	 int orderId;
     String bookId;
     int price;
     String status;
     String address;
     
	
	public Order() {
		
	}
	public Order(int orderId,int userId, String bookId, int price, String status, String address) {
		super();
		this.orderId = orderId;
		this.userid = userId;
		this.bookId = bookId;
		this.price = price;
		this.status = status;
		this.address = address;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
     
     
}
