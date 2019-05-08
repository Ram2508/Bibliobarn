package com.bibliobarn.servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bibliobarn.beans.UserAccount;
import com.bibliobarn.utils.DBUtils;
import com.bibliobarn.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/createUser" })
public class CreateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public CreateUserServlet() {
        super();
    }
 
    // Show Login page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Forward to /WEB-INF/views/loginView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/createUserView.jsp");
 
        dispatcher.forward(request, response);
 
    }
 
    // When the user enters userName & password, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
       // boolean remember = "M".equals(rememberMeStr);
 
        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;
 
        if (userName == null || password == null|| email == null||phone == null ||(firstName == null)|| (lastName == null)||
        		userName.trim().length() == 0 || password.trim().length() == 0 || email.trim().length() == 0 ||
        		phone.trim().length() == 0 || firstName.trim().length() == 0 || lastName.trim().length() == 0) {
            hasError = true;
            errorString = "some feilds are empty...!!!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // Find the user in the DB.
                boolean bool = DBUtils.checkUsername(conn, userName);
 
                if (bool) {
                    hasError = true;
                    errorString = "User Name already exists...!!!";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        // If error, forward to /WEB-INF/views/login.jsp
            user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhone(phone);
 
            if (hasError) {
            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
 
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/createUserView.jsp");
 
            dispatcher.forward(request, response);
        }
        // If no error
        // Store user information in Session
        // And redirect to userInfo page.
        else {
           // HttpSession session = request.getSession();
            //MyUtils.storeLoginedUser(session, user);
            
            Connection conn = MyUtils.getStoredConnection(request);
            
            try {
				DBUtils.addUser(conn, user);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        /*    // If user checked "Remember me".
            if (remember) {
                MyUtils.storeUserCookie(response, user);
            }
            // Else delete cookie.
            else {
                MyUtils.deleteUserCookie(response);
            }*/
 
            // Redirect to userInfo page.
            RequestDispatcher dispatcher //
            = this.getServletContext().getRequestDispatcher("/WEB-INF/views/createUserSuccess.jsp");

    dispatcher.forward(request, response);        }
    }
 
}
