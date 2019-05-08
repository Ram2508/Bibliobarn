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

import com.bibliobarn.beans.Book;
import com.bibliobarn.utils.DBUtils;
import com.bibliobarn.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/productList" })
public class ProductListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public ProductListServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String errorString =  (String) request.getAttribute("errorString");
        String code = (String) request.getParameter("category");
        if(code == null)
        	code = (String) request.getSession().getAttribute("code");
        List<Book> list = null;
        try {
        	if(code == null)
        	{
        		list = DBUtils.queryProduct(conn);
        	}
        	else 
        	{
        		if(!code.trim().equalsIgnoreCase("Show All"))
        		{        		
        			list = DBUtils.getProductsByCategory(conn,code.trim());
        		}else
        		{
        			list = DBUtils.queryProduct(conn);
        		}
        	}
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store info in request attribute, before forward to views
        if(errorString != null)
        request.setAttribute("errorString", errorString);
        request.setAttribute("productList", list);
        request.getSession().setAttribute("code", code);
         
        // Forward to /WEB-INF/views/productListView.jsp
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/productListView.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}