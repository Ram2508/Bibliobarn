<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <%@ page import="java.util.*" %>
  <%@ page import="com.bibliobarn.beans.Book" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <script>
    
    </script>
 </head>
 <body>
    <jsp:include page="_menu.jsp"></jsp:include>
    <div align="center">
 
    <h3 align="center">Product List</h3>
 
    <p style="color: red;">${errorString}</p>
    <input type="hidden" name="errorString" value="${errorString}" style= "display: none;">
    <form  method="POST" action="${pageContext.request.contextPath}/checkout">
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Select</th>
          <th>Image</th>
          <th>BookId</th>
          <th>Title</th>
          <th>Price</th>
          <th>Author</th>
          <th>Category</th>
       </tr>
     
       <c:forEach items="${productList}" var="product" >
          <tr>
          	 <td><input type="checkbox" name="item" id="${product.id}" value="${product.id}"></td>
             <td><img border="0" src="${product.path}"
     alt="book image" width="304" height="228"></td>
             <td>${product.id}</td>
             <td>${product.title}</td>
             <td>${product.price}</td>
             <td>${product.author}</td>
             <td>${product.category}</td>
          </tr>
       </c:forEach>
       
    </table>
     <input type="submit" name="checkout" value="Continue to checkout">
     <%
     
     HttpSession s = request.getSession();
     //System.out.println(s);
     if(s != null)
     {
    	 response.setContentType("text/html");
    	 String str = request.getContextPath();
    	 out.println("<a href=\""+str+"/logout\">Logout</a>");
     }
     
     
     %>
    </form>
 </div>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>