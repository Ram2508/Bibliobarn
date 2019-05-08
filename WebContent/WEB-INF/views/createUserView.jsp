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
      <h3>Signup Page</h3>
      <p style="color: red;">${errorString}</p>
 
      <form method="POST" action="${pageContext.request.contextPath}/createUser">
         <table border="0">
            <tr>
               <td>First Name</td>
               <td><input type="text" name="firstName" value= "${user.firstName}" /> </td>
            </tr>
            <tr>
               <td>Last Name</td>
               <td><input type="text" name="lastName" value= "${user.lastName}" /> </td>
            </tr>
            <tr>
               <td>User Name</td>
               <td><input type="text" name="userName" value= "${user.userName}" /> </td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="password" name="password" value= "${user.password}" /> </td>
            </tr>
             <tr>
               <td>E-Mail</td>
               <td><input type="text" name="email" value= "${user.email}" /> </td>
            </tr>
            <tr>
               <td>Phone</td>
               <td><input type="text" name="phone" value= "${user.phone}" /> </td>
            </tr>
            <tr>
               <td>Gender</td>
               <td>Male<input type="radio" name="gender" value= "M" /> </td>
               <td>Female<input type="radio" name="gender" value= "F" /> </td>
            </tr>
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Submit" />
                  <a href="${pageContext.request.contextPath}/">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
      </div>
  
      <jsp:include page="_footer.jsp"></jsp:include>
   </body>
</html>