<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>User Info</title>
 </head>
 <body>
 
    <jsp:include page="_menu.jsp"></jsp:include>
 
<table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>select</th>
          <th>BookId</th>
          <th>Title</th>
          <th>Price</th>
          <th>Author</th>
          <th>Category</th>
       </tr>
     
       <c:forEach items="${bookList}" var="product" >
          <tr>
          	 <td><input type="checkbox" name="item" id="${product.id}" value="${product.id}"></td>
             <td>${product.id}</td>
             <td>${product.title}</td>
             <td>${product.price}</td>
             <td>${product.author}</td>
             <td>${product.category}</td>
          </tr>
       </c:forEach>
    </table>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>