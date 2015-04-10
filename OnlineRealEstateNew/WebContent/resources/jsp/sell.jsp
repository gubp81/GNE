<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sell your Property</title>
</head>
<jsp:include page="header.jsp"></jsp:include>
<body>
	<form action="sell" method="post">
		<div class="esu">
			<h1>Post a new property</h1>
			<table width="80%" height="100%">
				<tr>
					<td><br>
					<h2>Seller:</h2>
						<br> Name: <input type="text" name="name"> <br>
						Phone Number: <input type="text" name="phone"> <br>
						Email: <input type="text" name="email"> <br> <br>
					<h2>Property</h2>
						<br> Price: <input type="text" name="price"> <br>
						Address: <input type="text" name="address"> <br>
						Region<select name="region" class="select">
							<option>Click to Select</option>
							<option>Montreal Downtown</option>
							<option>Montreal East</option>
							<option>Montreal West</option>
							<option>Montreal North</option>
							<option>Montreal South</option>
							<option>North Shore</option>
							<option>South Shore</option>
					</select><br> Property Type:<select name="proptype" class="select">
							<option>Click to Select</option>
							<option>Condo</option>
							<option>Residential</option>
					</select><br> Number of Rooms:<select name="size" class="select">
							<option>Click to Select</option>
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5+</option>
					</select><br> <br>
					Year of Construction: <input type="text" name="year"> <br>
					<h2>Choose by Amenities:</h2>
						<br> <input type="checkbox" name="garage">Garage<br>
						<input type="checkbox" name="pool">Pool<br> <input
						type="checkbox" name="a/c">A/C<br> <br>
					<h2>Choose by Facilities:</h2>
						<br> <input type="checkbox" name="school">School<br> <input
						type="checkbox" name="metro">Metro<br> <input
						type="checkbox" name="hospital">Hospital<br> <input
						type="checkbox" name="shopping_mall">Shopping Mall<br>
						<br> Detailed Description: <textarea name="description"> </textarea>
						<br> <br>
						Upload a photo:<input id="image" name="image" type="file" accept=".jpg" /><br><br>
						<input type="submit" value="Submit" class="select"/> 
						<input type="button" name="Cancel"
						value="Cancel" onclick="history.go(-1);" class="select"/></td>
				</tr>
			</table>
		</div>
	</form>
</body>

<jsp:include page="footer.jsp"></jsp:include>
</html>