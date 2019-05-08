package com.bibliobarn.servlet;
 
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bibliobarn.beans.OrderDisplay;
import com.bibliobarn.beans.UserAccount;
import com.bibliobarn.utils.DBUtils;
import com.bibliobarn.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/orderList" })
public class OrderListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public OrderListServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	 HttpSession session = request.getSession(false);
    	 
         // Check User has logged on
         UserAccount loginedUser = MyUtils.getLoginedUser(session);
  
         // Not logged in
         String errorString = null;
         if (loginedUser == null) {
             // Redirect to login page.
        	 errorString = "Please login first to track your orders ...!!";
        	 request.setAttribute("errorString", errorString);
        	 RequestDispatcher dispatcher = request.getServletContext()
                     .getRequestDispatcher("/WEB-INF/views/loginView.jsp");
             dispatcher.forward(request, response);
             return;
         }
         // Store info to the request attribute before forwarding.
         request.setAttribute("user", loginedUser);
  
         // If the user has logged in, then forward to the page
         // /WEB-INF/views/userInfoView.jsp
         Connection conn = MyUtils.getStoredConnection(request);
 
        List<OrderDisplay> list = null;
        try {
        	
        	
        	
            list = DBUtils.getOrders(conn, loginedUser);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("ordersList", list);
         
        // Forward to /WEB-INF/views/productListView.jsp
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/orderListView.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}