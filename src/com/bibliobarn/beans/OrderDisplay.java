package com.bibliobarn.beans;

public class OrderDisplay {

	 int orderId;
     String bookName;
     int price;
     String status;
     String address;
     
	public OrderDisplay() {
		
	}
	public OrderDisplay(int orderId, String bookName, int price, String status, String address) {
		super();
		this.orderId = orderId;
		this.bookName = bookName;
		this.price = price;
		this.status = status;
		this.address = address;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
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
