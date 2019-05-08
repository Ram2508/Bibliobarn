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
 <div align="center">
    <h3 style='color:green;'>User created successfully....</h3>
    <h5><a href="${pageContext.request.contextPath}/login">Click here</a> to login</h5>
 </div>
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>