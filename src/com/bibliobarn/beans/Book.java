package com.bibliobarn.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "book")

public class Book implements Serializable{
 
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String id;
   private String title;
   private int price;
   private String author;
   private String category;
   private String path;
   
public Book() {
	
}
public Book(String id, String title, int price, String author, String category, String path) {
	super();
	this.id = id;
	this.title = title;
	this.price = price;
	this.author = author;
	this.category = category;
	this.path = path;
}

public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
 
   
 
}