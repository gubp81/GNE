<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://www.google.com/uds/css/gsearch.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="http://www.belcourtcondos.com/assets/_combinedfiles/general.css?m=1405434178" />
<title>Message</title>
</head>
<jsp:include page="header.jsp"></jsp:include>
<body>
<h1>${msg}</h1>
<input type="button" name="Back" value="Back" onclick="history.go(-2);" />
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>