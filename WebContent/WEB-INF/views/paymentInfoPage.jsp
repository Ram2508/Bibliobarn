<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Payment page</title>
 </head>
 <body>
 
    <jsp:include page="_menu.jsp"></jsp:include>
  <form  method="POST" action="${pageContext.request.contextPath}/placeOrder">
 	<div align="center">
    <h3>Enter credit card details</h3>
 
    <b>Card Number :</b> <input type="text" name="cardNumber"><br />
    <b>Name :</b> <input type="text" name="cardName"> <br />
    <b>expiry date :</b> <input type="text" name="cardExpiry"> <br />
    <b>CVV :</b><input type="text" name="cardExpiry"> 
    
    <input type="submit" name="checkout" value="Place order">
    
    <br />
 </div>
 </form>
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>