<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Offers posted</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<table style="width: 100%; background-color: white;" border="0">
	<tr>
	<td width=10%>
	<h3> Offered on</h3>
	</td>
	<td width=25%>
	<h3> Name</h3>
	</td>
	<td width=20%>
	<h3> Offer Price</h3>
	</td>
    </tr>
	<c:forEach var="offers" items="${offers}">
		<tr>
			<td>${offers.date}</td>
			<td>${offers.name}</td>
			<td>$${offers.amount}</td>
			<td><form id="${offers.buyerid}" action="decision" method="get">
				<input type="hidden"	id="propertyid" name="propertyid" value="${offers.propertyid}" />
				<input type="hidden"	id="buyerid" name="buyerid" value="${offers.buyerid}" />
			    <input type="submit"id="action" name="action" value="Accept" class="select" /> 
				</form>
			</td>
			<td><form id="${offers.buyerid}" action="decision" method="get">
				<input type="hidden"	id="propertyid" name="propertyid" value="${offers.propertyid}" />
				<input type="hidden"	id="buyerid" name="buyerid" value="${offers.buyerid}" />
				<input type="submit"id="action" name="action" value="Reject" class="select" />
				</form>
			</td>
		</tr>
	</c:forEach>
	</table>	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</body>
</html>