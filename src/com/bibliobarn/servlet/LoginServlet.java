package com.bibliobarn.servlet;
 
import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.bibliobarn.beans.UserAccount;
import com.bibliobarn.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public LoginServlet() {
        super();
    }
 
    // Show Login page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Forward to /WEB-INF/views/loginView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)
    	 HttpSession session = request.getSession(false);
    	  UserAccount loginedUser = MyUtils.getLoginedUser(session);
    	 if(loginedUser == null)
    		 {
    		 RequestDispatcher dispatcher 
    		 = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
    		 dispatcher.forward(request, response);
    		 
    		 }else
    		 {
    			 response.sendRedirect(request.getContextPath() + "/home");
    		 }
    	 
 
 
    }
    
    private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/ServletFilter").build();
	}
 
    // When the user enters userName & password, and click Submit.
    // This method will be executed.
    @SuppressWarnings("unchecked")
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	 
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);
 
        //UserAccount user = null;
        boolean hasError = false;
        String errorString = null;
        UserAccount user = null;
        List<UserAccount> userList = null;
 
        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
        	
        	ClientConfig config = new ClientConfig();
     		Client client = ClientBuilder.newClient(config);

     		System.out.println("Calling " + getBaseURI());

     		WebTarget target = client.target(getBaseURI());
     		// Get XML
     		Response xmlResponse = target.path("login").path("userLoginService").path("checkUserLogin").queryParam("request", request).queryParam("userName", request.getParameter("userName")).queryParam("password", request.getParameter("password")).request().get();
        	
        	
           user =  xmlResponse.readEntity(UserAccount.class);
 
			if (userList == null) {
			    hasError = true;
			    errorString = "User Name or password invalid";
			}
        }
        // If error, forward to /WEB-INF/views/login.jsp
       
        if (hasError) {
            user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            
 
            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
 
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
            dispatcher.forward(request, response);
        }
        // If no error
        // Store user information in Session
        // And redirect to userInfo page.
        else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);
 
            // If user checked "Remember me".
            if (remember) {
                MyUtils.storeUserCookie(response, user);
            }
            // Else delete cookie.
            else {
                MyUtils.deleteUserCookie(response);
            }
 
            // Redirect to userInfo page.
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
 
}
