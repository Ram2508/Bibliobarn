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
 </head>
 <script language="JavaScript">
 function addToCartFunction()
 {
	 
	 document.getElementById("addtocart").innerHTML = "Remove";
	 document.getElementById("addtocart").style.color = 'red';
 }
 
 </script>
 <body>
    <jsp:include page="_menu.jsp"></jsp:include>
    <div align="center">
 
    <h3 align="center">Items List</h3>
 
    <p style="color: red;">${errorString}</p>
    <form  method="POST" action="${pageContext.request.contextPath}/payment">
 
   <%
       List<Book> list = new ArrayList<Book>();%>
       <c:set var="total" value="${0}"/>
             
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>BookId</th>
          <th>Title</th>
          <th>Author</th>
          <th>Category</th>
          <th>Price</th>
       </tr>
       <c:forEach items="${cartList}" var="product" >
          <tr>
             <td>${product.id}</td>
             <td>${product.title}</td>
             <td>${product.author}</td>
             <td>${product.category}</td>
             <td>${product.price}</td>
             <td><input type="checkbox" name="item" value="${product.id}" style='display:none;' checked></td>
          	
             <c:set var="total" value="${total + product.price}" />
            <!-- <td>
             <input id="addtocart" type="button" name="addToCart" value="Add to cart" onclick="addToCartFunction()"></input>
             </td>
             <td>
                <a href="deleteProduct?code=${product.id}">Delete</a>
             </td>-->
          </tr>
       </c:forEach>
    <% request.getServletContext().setAttribute("finalList", list);
    %>
    </table><br/>
       <h3> Total Price: ${total}</h3><br/>
        Address: <textarea name="address" id="address"></textarea><br/>
     <input type="submit" name="payment" value="Continue to payment">
    </form>
    
    <a href="${pageContext.request.contextPath}/logout" >Logout</a>
 </div>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>