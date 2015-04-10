<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Search Results</title>

<script type="text/javascript">
	var url = window.location.toString();
	var res = url.split("&sort=");
	url = res[0];

	function checkEnable(propertyid, sold) {
		if (sold == "Sold") {
			document.getElementById(propertyid).disabled = true;
		}
	}

	function RankbyLowestPrice() {
		url = url.concat("&sort=by+Price%3A+low+to+high");
		window.location.assign(url);
	}
	function RankbyHighestPrice() {
		url = url.concat("&sort=by+Price%3A+high+to+low");
		window.location.assign(url);
	}
	function RankbyOldestPost() {
		url = url.concat("&sort=by+PostDate%3A+oldest+first");
		window.location.assign(url);
	}
	function RankbyNewestPost() {
		url = url.concat("&sort=by+PostDate%3A+recently+posted+first");
		window.location.assign(url);
	}
</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<input type="button" id="lowprice" name="lowprice"
		value="Rank by Lowest Price" class="select"
		onclick="RankbyLowestPrice( )" />
	<input type="button" id="highprice" name="highprice"
		value="Rank by Highest Price" class="select"
		onclick="RankbyHighestPrice( )" />
	<input type="button" id="earliest" name="newest"
		value="Rank by Oldest Post" class="select"
		onclick="RankbyOldestPost( )" />
	<input type="button" id="oldest" name="oldest"
		value="Rank by Newest Post" class="select"
		onclick="RankbyNewestPost( )" />

		<table style="width: 100%; background-color: white;" border="1">
			<c:forEach var="list" items="${list}">
				<tr>
					<td align="center"><img
						src="${pageContext.request.contextPath}/resources/images/${list.propertyid}.jpg"
						width="300" height="200" /> <br> 
	                    <form id="${list.propertyid}" action="details" method="get">
						<input type="submit" value="Details" class="select"/> 
                        <input type="hidden" id="${list.propertyid}" name="propertyid" value="${list.propertyid}"/> 
						<script>
							checkEnable('${list.propertyid}',
									'${list.soldValue}')
						</script>
					    </form>
						<span
						style="font-weight: bold; font-style: italic; font-size: xx-large; color: red;">${list.soldValue}</span>
						<br> Posted on : ${list.postDate}<br> Number of Offers
						made: ${list.offers}</td>
					<td width="400" valign="top"><table>
							<tr>
								<td><h4>${list.address}</h4></td>
							</tr>
							<tr>
								<td>Property Region : ${list.region}</td>
							</tr>
							<tr>
								<td>Type : ${list.type}</td>
							</tr>
							<tr>
								<td>Price : ${list.price}</td>
							</tr>
							<tr>
								<td>Property Size : ${list.size}</td>
							</tr>
							<tr>
								<td>Year of Construction : ${list.year}</td>
							</tr>
							<tr> </tr>
								<tr>
									<td><b>Amenities:</b></td>
									<td>${list.garageValue}</td>
									<td>${list.poolValue}</td>
									<td>${list.acValue}</td>
								</tr>
								<tr>
									<td><b>Facilities:</b></td>
									<td>${list.schoolValue}</td>
									<td>${list.metroValue}</td>
									<td>${list.hospitalValue}</td>
									<td>${list.shoppingMallValue}</td>
								</tr>
						</table></td>
					<td valign="top"><b>Description:</b>
						<p>${list.description}</p></td>
			</c:forEach>
		</table>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>