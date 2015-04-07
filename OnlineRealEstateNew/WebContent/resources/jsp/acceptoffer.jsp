<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success</title>
</head>
<body>
   <jsp:include page="header.jsp"></jsp:include>
    <c:forEach var="list" items="${list}">
    <table>
<tr>
<td>
<table width="80%" height="100%">
<tr><td>

<input type="hidden" name="address" value="${list.address}">
Offer Id:${list.offerid}<br>
Offered on: ${list.currentDate}<br>
First name:${list.firstname}<br>
Last name: ${list.lastname}<br>
Offer Price:${list.offerprice}<br>
Phone Number:${list.phonenumber}<br>
Email:${list.email}<br>

<!-- "window.location = 'resources/jsp/body2.jsp' " -->
</td>
</tr>
</table>

</td>
<td>
<table>
<tr><td><h4>${list.address}</h4></td></tr>
<tr><td>Property Type   : ${list.type}</td></tr> 
    <tr><td>Price           : ${list.price}</td> </tr>
    <tr><td>Property Size   : ${list.size}</td> </tr>
    <tr><td>Property Region : ${list.region}</td> </tr>
    <tr><td><b>Nearby:</b></td></tr>
    <tr><td>${list.schoolValue}</td></tr>
    <tr><td>${list.metroValue}</td></tr>
    <tr><td>${list.hospitalValue}</td></tr>
    <tr><td>${list.shoppingMallValue}</td></tr>
</table>
</td>
</tr>
</table>
</c:forEach>
<form action="accept" method="post">
Please select an offer id:
<select name="offerid">
<c:forEach var="list" items="${list}">
<option>${list.offerid}</option>
</c:forEach>
</select>
<input type="submit" value="Accept">
</form>
<!-- <form action="reject" method="post"> -->
<!-- <input type="submit" Value="Reject"> -->
<!-- </form> -->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</body>
</html>