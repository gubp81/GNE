<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Offer Page</title>
</head>
<jsp:include page="header.jsp"></jsp:include>
<body>
<form action="makeanoffer" method="post">
<div class="esu">
<h1>Make an Offer</h1>


<table width="80%" height="100%">
<tr><td>
//checking repository.

Name:
<input type="text" name="name" >
<br>
Phone Number:
<input type="text" name="phone">
<br>
Email:
<input type="text" name="email">
<br>
Offer Price:
<input type="text" name="amount">
<br>
<input type="submit" value="Submit">
<input type="button" name="Cancel" value="Cancel" onclick="history.go(-1);" />
</form></body>
</div>

<jsp:include page="footer.jsp"></jsp:include>
</html>