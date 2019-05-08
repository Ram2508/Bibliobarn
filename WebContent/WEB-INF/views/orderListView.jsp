<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    
    <title>My orders</title>
 </head>
 <body>
    <jsp:include page="_menu.jsp"></jsp:include>
    <div align="center">
 
    <h3 align="center">My orders</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Order Id</th>
          <th>Book Name</th>
          <th>Price</th>
          <th>Status</th>
          <th>Address</th>
       </tr>
       <c:forEach items="${ordersList}" var="order" >
          <tr>
             <td>${order.orderId}</td>
             <td>${order.bookName}</td>
             <td>${order.price}</td>
             <td>${order.status}</td>
             <td>${order.address}</td>
             <!--<td><td>
                <a href="deleteProduct?code=${product.id}">Delete</a>
             </td>-->
          </tr>
       </c:forEach>
    </table>
 
    <a href="${pageContext.request.contextPath}/logout" >Logout</a>
    </div>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>