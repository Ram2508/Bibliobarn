

package com.bibliobarn.servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bibliobarn.beans.Book;
import com.bibliobarn.beans.Order;
import com.bibliobarn.beans.UserAccount;
import com.bibliobarn.utils.DBUtils;
import com.bibliobarn.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/placeOrder" })
public class OrderManageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public OrderManageServlet() {
        super();
    }
 
    // Show Login page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Forward to /WEB-INF/views/loginView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)
     //   RequestDispatcher dispatcher //
 //               = this.getServletContext().getRequestDispatcher("/WEB-INF/views/createUserView.jsp");
 
       // dispatcher.forward(request, response);
 
    }
 
    // When the user enters userName & password, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	 UserAccount loginedUser = MyUtils.getLoginedUser(session);
    	 if (loginedUser == null) {
             // Redirect to login page.
             response.sendRedirect(request.getContextPath() + "/login");
             return;
         }
    	 Connection conn = MyUtils.getStoredConnection(request);
        String errorString = null;
        String[] items = (String[]) request.getSession().getAttribute("items");
       // List<Book> booksList = (List<Book>) request.getServletContext().getAttribute("finalList");
        
        if(items == null)
        {
        	errorString = "Please select atlease one product";
        	request.setAttribute("errorString", errorString);
        	
        	 RequestDispatcher dispatcher = request.getServletContext()
                     .getRequestDispatcher("/productList");
             dispatcher.forward(request, response);
        	
        	//response.sendRedirect(request.getContextPath() + "/productList");
        }
        else {
        	//request.setAttribute("items", items);
        List<Book> list = new ArrayList<Book>();
        //list = request
        try {
        	for( String book:items)
        	{
        		Book book1 = new Book();
        		book1 = DBUtils.findProduct(conn,book);
        		list.add(book1);
        	}
        	//request.setAttribute("list", list);
        	for (Book book:list)
        	{
        		Order order = new Order();
        		order.setUserid(loginedUser.getUserid());
        		order.setBookId(book.getId());
        		order.setPrice(book.getPrice());
        		order.setStatus("ORDERED");
        		order.setAddress((String)request.getSession().getAttribute("address"));
        		DBUtils.createOrder(conn, order);
        	}
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        // Store info in request attribute, before forward to views
        
        request.setAttribute("errorString", errorString);
       // request.setAttribute("cartList", list);
         
        // Forward to /WEB-INF/views/productListView.jsp
        response.sendRedirect(request.getContextPath() + "/orderList");

 
        }
    }
 
}
