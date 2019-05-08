package com.bibliobarn.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.bibliobarn.beans.UserAccount;
import com.bibliobarn.utils.DBUtils;
import com.bibliobarn.utils.MyUtils;

@Path("/userLoginService")
public class UserLoginService {

	// Gets the list of product categories for the store //
	@Path("/checkUserLogin")
	@Produces(MediaType.APPLICATION_XML)
	@GET
public Response checkUserLogin( @FormParam("request") HttpServletRequest request,@FormParam("userName") String userName,@FormParam("password") String password) {
		
		Connection conn = MyUtils.getStoredConnection(request);
		UserAccount user = null;
		try {
			user = DBUtils.findUser(conn, userName, password);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.ACCEPTED).entity(user).build();
		
	}

	// Gets the list of products for a category, or all products if no category is
	
/*	// specified //
	@GET
	@Path("/addUser")
	public Response insertUserDetails(@QueryParam("categoryId") String categoryId) {}

	// Gets the detailed product information for a product //
	@GET
	@Path("/checkUserExists")
	public Response checkUserExists(@QueryParam("productid") String productid) {}
*/
}