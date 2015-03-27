<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/myStyles.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/login.js/"></script>
<title>Index Page</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="esu">
<form action="test" method="post">
<table width="80%" height="100%">
<tr><td>
Property Type<select name="propType" class="select">
<option>Click to Select</option>
<option>Condo</option>
<option>Residential</option>
</select></td>

<td>Size<select name="size" class="select">
<option>Click to Select</option>
<option>1</option>
<option>2</option>
<option>3</option>
<option>4</option>
</select>
</tr>
<tr><td>
Price Range<select name="price" class="select">
<option>Click to Select</option>
<option>less than 500</option>
<option>500 to 1000</option>
<option>1000 to 2000</option>
<option>2000 to 5000</option>
<option>5000 to 10000</option>
</select>
</td>
<td>Region<select name="region" class="select">
<option>Click to Select</option>
<option>Montreal East</option>
<option>Montreal West</option>
<option>Montreal North</option>
<option>Montreal South</option>
</select>
</td>
<td>Sort by price
<select name="sort" class="select">
<option>Click to Sort</option>
<option>low to high</option>
<option>high to low</option>
</select>
</td>
</tr>
<tr><td>Nearby</td></tr>
<tr><td><input type="checkbox" name="school">School</td>
<td><input type="checkbox" name="metro">Metro</td>
<td><input type="checkbox" name="hospital">Hospital</td>
<td><input type="checkbox" name="shopping_mall">Shopping Mall</td></tr>
</table>
<input type="submit" height="100px" width="100px" align="middle">
</form>
<table width="100%">
<tr>
<td> <img src="${pageContext.request.contextPath}/resources/images/3.jpg" height="500" width="1150"></td>
</tr></table>
</div>
<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>