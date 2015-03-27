<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="header.jsp"></jsp:include>
<body>
<form>
<div class="esu">
<h1>Make an Offer</h1>


<table width="80%" height="100%">
<tr><td>


First name:
<input type="text" name="firstname" >
<br>
Last name:
<input type="text" name="lastname">
<br>
Offer Price:
<input type="text" name="offerprice">
<br>
Phone Number:
<input type="text" name="phonenumber">
<br>
Email:
<input type="text" name="email">
<br>

<input type="submit" value="Submit">
<input type="button" name="Cancel" value="Cancel" onclick="window.location = 'resources/jsp/body2.jsp' " />
</form></body>
</div>

<jsp:include page="footer.jsp"></jsp:include>
</html>