<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Login</title>
   </head>
   <body>
      <jsp:include page="_menu.jsp"></jsp:include> 
 
 
 <div align="center">
      <h3>Login Page</h3>
      <p style="color: red;">${errorString}</p>
      <form  method="POST" action="${pageContext.request.contextPath}/login">
         <table border="0">
            <tr>
               <td>User Name</td>
               <td><input type="text" name="userName" value= "${user.userName}" /> </td>
            </tr>
            
            <tr>
               <td>Password</td>
               <td><input type="password" name="password" value= "${user.password}" /> </td>
            </tr>
            <tr>
               <td>Remember me</td>
               <td><input type="checkbox" name="rememberMe" value= "Y" /> </td>
            </tr>
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Login" /> 
                  <a href="${pageContext.request.contextPath}/login">reset</a><br/>
                  <a href="${pageContext.request.contextPath}/createUser">Sign up</a>
               </td>
            </tr>
         </table>
      </form>
      </div>
 
      <jsp:include page="_footer.jsp"></jsp:include>
   </body>
</html>