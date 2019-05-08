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

import com.bibliobarn.beans.Book;
import com.bibliobarn.utils.DBUtils;
import com.bibliobarn.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/home"})
public class HomeServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
 
   public HomeServlet() {
       super();
   }
 
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
 
	  HttpSession session = request.getSession();
	  // UserAccount loginedUser = MyUtils.getLoginedUser(session);

       Connection conn = MyUtils.getStoredConnection(request);
       try {
           List<String> categoryList = DBUtils.getCategories(conn);
           categoryList.add("Show All");
           List<Book> bookList = DBUtils.queryProduct(conn);
           request.getSession().setAttribute("categoryList", categoryList);
           request.getSession().setAttribute("bookList", bookList);
            
       } catch (SQLException e) {
           e.printStackTrace();
       }
	   
		   RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");
		   dispatcher.forward(request, response);
        
   }
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doGet(request, response);
   }
 
}