<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>User Info</title>
 </head>
 <body>
 
    <jsp:include page="_menu.jsp"></jsp:include>
 <div align ="center">
    <h3>Hello: ${user.firstName}</h3>
 
 First Name: <b>${user.firstName}</b><br />
 Last Name: <b>${user.lastName}</b><br />
    User Name: <b>${user.userName}</b>
    <br />
    Email: ${user.email } <br />
    Phone: ${user.phone }<br />
    <br />
    
    <a href="${pageContext.request.contextPath}/logout" >Logout</a>
 </div>
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>