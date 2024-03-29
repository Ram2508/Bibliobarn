package com.bibliobarn.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bibliobarn.utils.MyUtils;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	    	response.setContentType("text/html");
	    	MyUtils.deleteUserCookie(response);
	    	//invalidate the session if exists
	    	HttpSession session = request.getSession(false);
	    	System.out.println("User="+session.getAttribute("user"));
	    	if(session != null){
	    		session.invalidate();
	    	}
	    	response.sendRedirect(request.getContextPath()+"/home");
	    
		 
	 }
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}
