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

import com.bibliobarn.beans.Book;
import com.bibliobarn.utils.DBUtils;
import com.bibliobarn.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/checkout" })
public class CheckoutServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public CheckoutServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String errorString = null;
        String[] items = request.getParameterValues("item");
        
        if(items == null)
        {
        	errorString = "Please select atlease one product";
        	request.setAttribute("errorString", errorString);
        	  RequestDispatcher dispatcher 
              = this.getServletContext().getRequestDispatcher("/productList");

        	  dispatcher.forward(request, response);
        }else {
        	request.getSession().setAttribute("items", items);

        List<Book> list = new ArrayList<Book>();
        try {
        	for( String item:items)
        	{
        		Book book = new Book();
        		book = DBUtils.findProduct(conn,item);
        		list.add(book);
        	}
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("cartList", list);
         
        // Forward to /WEB-INF/views/productListView.jsp
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/checkoutPage.jsp");
        dispatcher.forward(request, response);
        }
        
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}