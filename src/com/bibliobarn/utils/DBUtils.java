package com.bibliobarn.utils;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bibliobarn.beans.Book;
import com.bibliobarn.beans.Order;
import com.bibliobarn.beans.OrderDisplay;
import com.bibliobarn.beans.UserAccount;
 
public class DBUtils {
 
    public static UserAccount findUser(Connection conn, //
            String userName, String password) throws SQLException {
 
        String sql = "Select a.userid, a.username, a.password, a.firstname, a.lastname, a.email, a.phone from USERACCOUNT a " //
                + " where a.username = ? and a.password= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
        	String firstName = rs.getString("firstname");
        	String lastName = rs.getString("lastname");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            
            int userid = rs.getInt("userid");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setEmail(email);
            user.setPhone(phone);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUserid(userid);
            return user;
        }
        return null;
    }
    public static boolean checkUsername(Connection conn, //
            String userName) throws SQLException {
 
        String sql = "Select a.username from USERACCOUNT a " //
                + " where a.username = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            return true;
        }
        return false;
    }
 
    public static UserAccount findUser(Connection conn, String userName) throws SQLException {
 
        String sql = "Select a.userid, a.username, a.password, a.firstname, a.lastname, a.email, a.phone from USERACCOUNT a "//
                + " where a.username = ? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
        	int userid = rs.getInt("userid");
            String password = rs.getString("Password");
            String firstName = rs.getString("firstname");
            String lastName = rs.getString("lastname");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            UserAccount user = new UserAccount();
            user.setUserid(userid);
            user.setUserName(userName);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPhone(phone);
            return user;
        }
        return null;
    }
 
    public static List<Book> queryProduct(Connection conn) throws SQLException {
        String sql = "Select a.bookid, a.title, a.price, a.author, a.category, a.path from BOOK a ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Book> list = new ArrayList<Book>();
        while (rs.next()) {
        	String bookid = rs.getString("bookid");
        	String title = rs.getString("title");
            int price = rs.getInt("price");
            String author = rs.getString("author");
            String category = rs.getString("category");
            String path = rs.getString("path");
            Book book = new Book();
            book.setId(bookid);
            book.setTitle(title);
            book.setPrice(price);
            book.setAuthor(author);
            book.setCategory(category);
            book.setPath(path);
            list.add(book);
        }
        return list;
    }
    
    public static List<String> getCategories(Connection conn) throws SQLException {
        String sql = "Select distinct(a.category) from BOOK a ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        List<String> list = new ArrayList<String>();
        ResultSet rs = pstm.executeQuery();
        
        while (rs.next()) {
        	String cat = rs.getString("category");
            list.add(cat);
        }
        return list;
    }
 
 
    
    public static Book findProduct(Connection conn, String code) throws SQLException {
        String sql = "Select a.title, a.price, a.author, a.category, a.path from BOOK a where a.bookid=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, code);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
            String title = rs.getString("title");
            int price = rs.getInt("price");
            String author = rs.getString("author");
            String category = rs.getString("category");
            String path = rs.getString("path");
            Book Book = new Book(code,title, price, author, category, path);
            return Book;
        }
        return null;
    }
    
    public static List<OrderDisplay> getOrders(Connection conn, UserAccount user) throws SQLException {
        String sql = "select o.orderid,b.title,b.price,o.status, o.address from ORDERS o JOIN BOOK b JOIN USERACCOUNT u ON b.bookid=o.bookid where  o.userid = ? GROUP BY o.orderid;";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, user.getUserid());
 
        ResultSet rs = pstm.executeQuery();
        List<OrderDisplay> list = new ArrayList<OrderDisplay>();
        while (rs.next()) {
        	int id = rs.getInt("orderid");
            String name = rs.getString("title");
            int price = rs.getInt("price");
            String status = rs.getString("status");
            String address = rs.getString("address");
            OrderDisplay orderDisplay = new OrderDisplay(id,name, price, status, address);
            list.add(orderDisplay);
        }
        return list;
    }
 
    /*public static void updateProduct(Connection conn, Book Book) throws SQLException {
        String sql = "Update Book set Name =?, Price=? where Code=? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, Book.getName());
        pstm.setFloat(2, Book.getPrice());
        pstm.setString(3, Book.getCode());
        pstm.executeUpdate();
    }*/
 
    public static void addUser(Connection conn, UserAccount user) throws SQLException {
        String sql = "Insert into useraccount(firstname,lastname,username, password,email,phone) values (?,?,?,?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, user.getFirstName());
        pstm.setString(2, user.getLastName());
        pstm.setString(3, user.getUserName());
        pstm.setString(4, user.getPassword());
        pstm.setString(5, user.getEmail());
        pstm.setString(6, user.getPhone());
 
        pstm.executeUpdate();
    
    }
    
    public static void createOrder(Connection conn, Order order) throws SQLException {
        String sql = "insert into ORDERS(userid,bookid,price,status,address) values (?,?,?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setInt(1, order.getUserid());
        pstm.setString(2, order.getBookId());
        pstm.setInt(3,order.getPrice());
        pstm.setString(4, order.getStatus());
        pstm.setString(5, order.getAddress());
 
        pstm.executeUpdate();
    }
 /*
    public static void deleteProduct(Connection conn, String code) throws SQLException {
        String sql = "Delete From Book where Code= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, code);
 
        pstm.executeUpdate();
    }*/
	public static List<Book> getProductsByCategory(Connection conn, String category_param) throws SQLException{
		 String sql = "Select a.bookid, a.title, a.price, a.author, a.category, a.path from BOOK a where category = ?";
		 
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        pstm.setString(1, category_param);
	 
	        ResultSet rs = pstm.executeQuery();
	        List<Book> list = new ArrayList<Book>();
	        while (rs.next()) {
	        	String bookid = rs.getString("bookid");
	        	String title = rs.getString("title");
	            int price = rs.getInt("price");
	            String author = rs.getString("author");
	            String category = rs.getString("category");
	            String path = rs.getString("path");
	            Book book = new Book();
	            book.setId(bookid);
	            book.setTitle(title);
	            book.setPrice(price);
	            book.setAuthor(author);
	            book.setCategory(category);
	            book.setPath(path);
	            list.add(book);
	        }
	        return list;
	}
 
}