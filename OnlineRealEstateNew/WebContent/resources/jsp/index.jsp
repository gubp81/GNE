<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/myStyles.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/login.js/"></script>
<title>GNE Properties  - Find your Dream home</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="esu">
<form action="search" method="GET">
<table width="80%" height="100%">
<tr><td>
Property Type<select name="proptype" class="select">
<option>Click to Select</option>
<option>Condo</option>
<option>Residential</option>
</select></td>

<td>Number of Rooms<select name="size" class="select">
<option>Click to Select</option>
<option>1</option>
<option>2</option>
<option>3</option>
<option>4</option>
<option>5+</option>
</select>
</tr>
<tr><td>
Price From<select name="price" class="select">
<option>Click to Select</option>
<option>1-less than 100,000</option>
<option>2-100,000 to 200,000</option>
<option>3-200,000 to 300,000</option>
<option>4-300,000 to 400,000</option>
<option>5-more than 400,000</option>
</select>
</td>
<td>Region<select name="region" class="select">
<option>Click to Select</option>
<option>Montreal Downtown</option>
<option>Montreal East</option>
<option>Montreal West</option>
<option>Montreal North</option>
<option>Montreal South</option>
<option>North Shore</option>
<option>South Shore</option>
</select>
</td>
</tr>
<tr><td>Choose by Amenities:</td></tr>
<tr><td><input type="checkbox" name="garage">Garage</td>
<td><input type="checkbox" name="pool">Pool</td>
<td><input type="checkbox" name="a/c">A/C</td></tr>

<tr><td>Choose by Facilities:</td></tr>
<tr><td><input type="checkbox" name="school">School</td>
<td><input type="checkbox" name="metro">Metro</td>
<td><input type="checkbox" name="hospital">Hospital</td>
<td><input type="checkbox" name="shopping_mall">Shopping Mall</td></tr>
<tr><td>Sort
<select name="sort" class="select">
<option>Click to Sort</option>
<option>by Price: low to high</option>
<option>by Price: high to low</option>
<option>by PostDate: recently posted first</option>
<option>by PostDate: oldest first</option>
</select>
</td></tr>
</table>
<input type="submit" value="Search" height="100px" width="100px" align="middle">
</form>
<table width="100%">
<tr>
<td> <img src="${pageContext.request.contextPath}/resources/images/home.jpg" height="500" width="1150"></td>
</tr></table>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>