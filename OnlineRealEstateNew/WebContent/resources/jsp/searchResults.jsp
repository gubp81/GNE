<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<c:forEach var="list" items="${list}">
<table style="width:100%;background-color:white;" border="1">
  <tr>
  <td width="300">
  <h4>${list.address}</h4>
  </td>
  </tr>
  <tr>
 <td>
 <img src="data:image/jpeg;base64,${list.encodedImage}" width="300" height="200"/><br>
 <input type="button" name="Make an offer" value="${list.propertyID}" onclick="window.location = 'resources/jsp/offer.jsp' " />
 <br>
 Posted on			  : ${list.postDate}<br>
 Number of Offers made: ${list.offers}
 
  </td>
  <td width="200" valign="top"><table>
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
<td valign="top"><b>Description:</b>
<p>${list.description}</p>

</td>

  </table>
</c:forEach>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>