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
<title>Property Details</title>
</head>
<jsp:include page="header.jsp"></jsp:include>
<body>
	<form action="makeanoffer" method="post">
		<table style="width: 100%; background-color: white;" border="0">
			<tr>
				<td align="center" style="width: 30%;"><img
					src="${pageContext.request.contextPath}/resources/images/${property.propertyid}.jpeg"
					width="300" height="200" /> <br> <span
					style="font-weight: bold; font-style: italic; font-size: xx-large; color: red;">${property.soldValue}</span>
					Posted on : ${property.postDate} <br> Number of Offers made:
					${property.offers}
					<input type="hidden" name="propertyid" value="${property.propertyid}"> <br>
					<table>
						<tr>
							<td><h4>${property.address}</h4></td>
						</tr>
						<tr>
							<td>Property Region : ${property.region}</td>
						</tr>
						<tr>
							<td>Type : ${property.type}</td>
						</tr>
						<tr>
							<td>Price : ${property.price}</td>
						</tr>
					</table>
				<td style="width: 70%;">
					<div id="content">
						<div class="innertube" style="position: relative;">
							<div class="equalCol">
								<div id="page-title" align="right">Neighborhood</div>
								<div class="clear"></div>
								<p style="text-align: center;">
									Find out more about the municipality, its services and
									financial incentives visit: <a
										href="http://www.ville.montreal.qc.ca" target="_blank"><strong>www.ville.montreal.qc.ca</strong></a>
								</p>
								<p style="text-align: center;">
									<strong>Find out more about the area and its services
										on the interactive map.</strong>
								</p>

								<div id="Message"></div>
								<div id="propertymap"></div>

								<div id="hotspotsContainer">
									<ul id="list_left">
										<li id="hs01">Restaurants</li>
										<li id="hs02">Coffee</li>
										<li id="hs03">Bakery</li>
										<li id="hs04">Boutique</li>
										<li id="hs05">Church</li>
										<li id="hs06">Synagogue</li>
										<li id="hs07">Mosque</li>
										<li id="hs08">Grocery store</li>
										<li id="hs09">Pharmacy</li>
										<li id="hs10">Video store</li>
										<li id="hs11">Bank</li>
										<li id="hs12">Canada Post</li>
									</ul>
									<ul id="list_right">
										<li id="hs13" style="display: none;">Doggy daycare</li>
										<li id="hs14">Police</li>
										<li id="hs15">Fire</li>
										<li id="hs16">Hospital</li>
										<li id="hs17">Medical Clinic</li>
										<li id="hs18">Metro stop</li>
										<li id="hs19">Bus stop</li>
										<li id="hs20">Children daycare</li>
										<li id="hs21">Elementary school</li>
										<li id="hs22">High school</li>
										<li id="hs23">College</li>
										<li id="hs24">University</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</table>


		<div class="esu">
			<h1>Make an Offer</h1>
			<table width="80%" height="100%">
				<tr>
					<td>Name: <input type="text" name="name"> <br>
						Phone Number: <input type="text" name="phone"> <br>
						Email: <input type="text" name="email"> <br> Offer
						Price: <input type="text" name="amount"> <br> <input
						type="submit" value="Submit"> <input type="button"
						name="Cancel" value="Cancel" onclick="history.go(-1);" />
					</td>
				</tr>
			</table>
		</div>
	</form>

	<script type="text/javascript">
		var _gaq = _gaq || [];

		_gaq.push([ '_setAccount', 'UA-25476721-1' ]);

		_gaq.push([ '_setDomainName', 'belcourtcondos.com' ]);

		_gaq.push([ '_setAllowHash', false ]);

		_gaq.push([ '_setAllowLinker', true ]);

		_gaq.push([ '_trackPageview' ]);

		(function() {

			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;

			ga.src = ('https:' == document.location.protocol ? 'https://ssl'
					: 'http://www')
					+ '.google-analytics.com/ga.js';

			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);

		})();
	</script>
	<script type="text/javascript">

		var google_conversion_id = 970861215;
		var google_custom_params = window.google_tag_params;
		var google_remarketing_only = true;
	</script>

	<script type="text/javascript"
		src="http://www.belcourtcondos.com/themes/belcourt/javascript/jquery-1.4.2.min.js?m=1295983780"></script>

	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDAAXMNXj2-4YqusW9mkaPjBAQOCaT0a1M?sensor=true"></script>
	<script type="text/javascript"
		src="http://maps.googleapis.com/maps/api/js?libraries=places"></script>
	<script type="text/javascript">
		var map;
		var infowindow;
		var LatLng;
		var geocoder = new google.maps.Geocoder();      
		var propertyAddress = "${property.address}";
function callback(results, status) {
			if (status == google.maps.places.PlacesServiceStatus.OK) {
				for (var i = 0; i < results.length; i++) {
					createMarker(results[i]);
				}
			}
		}

		function createMarker(place) {
			var placeLoc = place.geometry.location;
			var marker = new google.maps.Marker({
				map : map,
				position : place.geometry.location
			});

			google.maps.event.addListener(marker, 'click', function() {
				infowindow.setContent(place.name);
				infowindow.open(map, this);
			});
		}
		function showMap(elemID) {
			geocoder = new google.maps.Geocoder();
			geocoder
					.geocode(
							{
								'address' : propertyAddress
							},
							function(results, status) {
								if (status == google.maps.GeocoderStatus.OK) {
									var mapOptions = {
										zoom : 14,
										center : results[0].geometry.location,
										mapTypeId : google.maps.MapTypeId.ROADMAP
									};
									map = new google.maps.Map(document
											.getElementById("propertymap"),
											mapOptions);
									var marker = new google.maps.Marker(
											{
												map : map,
												position : results[0].geometry.location,
												icon : '/themes/belcourt/images/arrow.png'
											});
									infowindow = new google.maps.InfoWindow();

									google.maps.event
											.addListener(
													marker,
													'click',
													function() {
														infowindow
																.setContent(propertyAddress);
														infowindow.open(map,
																marker);
													});
									if (elemID != '') {
										if (hotspotsList[elemID][0] == '') {
											var request = {
												location : results[0].geometry.location,
												radius : '1000',
												keyword : hotspotsList[elemID][1]
											};
										} else {
											var request = {
												location : results[0].geometry.location,
												radius : '1000',
												types : [ hotspotsList[elemID][0] ],
												keyword : hotspotsList[elemID][1]
											};
										}
										//infowindow = new google.maps.InfoWindow();
										var service = new google.maps.places.PlacesService(
												map);
										service.nearbySearch(request, callback);
									}
								} else {
									alert("Geocode was not successful for the following reason: "
											+ status);
								}
							});

			$('#hotspotsContainer li').each(function(index) {
				if ($(this).attr('id') == elemID) {
					$(this).css('font-weight', 'bold');
				} else {
					$(this).css('font-weight', 'normal');
				}
			});
		}

		var hotspotsList = new Array();
		hotspotsList["hs01"] = new Array();
		hotspotsList["hs02"] = new Array();
		hotspotsList["hs03"] = new Array();
		hotspotsList["hs04"] = new Array();
		hotspotsList["hs05"] = new Array();
		hotspotsList["hs06"] = new Array();
		hotspotsList["hs07"] = new Array();
		hotspotsList["hs08"] = new Array();
		hotspotsList["hs09"] = new Array();
		hotspotsList["hs10"] = new Array();
		hotspotsList["hs11"] = new Array();
		hotspotsList["hs12"] = new Array();
		hotspotsList["hs13"] = new Array();
		hotspotsList["hs14"] = new Array();
		hotspotsList["hs15"] = new Array();
		hotspotsList["hs16"] = new Array();
		hotspotsList["hs17"] = new Array();
		hotspotsList["hs18"] = new Array();
		hotspotsList["hs19"] = new Array();
		hotspotsList["hs20"] = new Array();
		hotspotsList["hs21"] = new Array();
		hotspotsList["hs22"] = new Array();
		hotspotsList["hs23"] = new Array();
		hotspotsList["hs24"] = new Array();
		hotspotsList["hs01"][0] = "restaurant";
		hotspotsList["hs01"][1] = "restaurant";
		hotspotsList["hs02"][0] = "cafe";
		hotspotsList["hs02"][1] = "cafe resto";
		hotspotsList["hs03"][0] = "bakery";
		hotspotsList["hs03"][1] = "boulangerie";
		hotspotsList["hs04"][0] = "store";
		hotspotsList["hs04"][1] = "boutique";
		hotspotsList["hs05"][0] = "church";
		hotspotsList["hs05"][1] = "��glise";
		hotspotsList["hs06"][0] = "synagogue";
		hotspotsList["hs06"][1] = "synagogue";
		hotspotsList["hs07"][0] = "mosque";
		hotspotsList["hs07"][1] = "mosque";
		hotspotsList["hs08"][0] = "grocery_or_supermarket";
		hotspotsList["hs08"][1] = "��picerie";
		hotspotsList["hs09"][0] = "pharmacy";
		hotspotsList["hs09"][1] = "pharmacie";
		hotspotsList["hs10"][0] = "movie_rental";
		hotspotsList["hs10"][1] = "video rental";
		hotspotsList["hs11"][0] = "bank";
		hotspotsList["hs11"][1] = "bank";
		hotspotsList["hs12"][0] = "post_office";
		hotspotsList["hs12"][1] = "";
		hotspotsList["hs13"][0] = "pet_store";
		hotspotsList["hs13"][1] = "pet store";
		hotspotsList["hs14"][0] = "police";
		hotspotsList["hs14"][1] = "";
		hotspotsList["hs15"][0] = "fire_station";
		hotspotsList["hs15"][1] = "";
		hotspotsList["hs16"][0] = "hospital";
		hotspotsList["hs16"][1] = "h��pital";
		hotspotsList["hs17"][0] = "health";
		hotspotsList["hs17"][1] = "clinique mdicale";
		hotspotsList["hs18"][0] = "subway_station";
		hotspotsList["hs18"][1] = "metro station";
		hotspotsList["hs19"][0] = "bus_station";
		hotspotsList["hs19"][1] = "bus station";
		hotspotsList["hs20"][0] = "";
		hotspotsList["hs20"][1] = "daycare+garderie";
		hotspotsList["hs21"][0] = "school";
		hotspotsList["hs21"][1] = "elementary school+��colde primaire";
		hotspotsList["hs22"][0] = "school";
		hotspotsList["hs22"][1] = "high school+��cole secondaire";
		hotspotsList["hs23"][0] = "school";
		hotspotsList["hs23"][1] = "college";
		hotspotsList["hs24"][0] = "university";
		hotspotsList["hs24"][1] = "university";

		jQuery(document).ready(function() {

			if (propertyAddress != "") {
				showMap('');
			}
			$('#hotspotsContainer li').each(function(index) {
				$(this).click(function() {
					showMap($(this).attr('id'));
				})
			});
		});
	</script>
</body>

<jsp:include page="footer.jsp"></jsp:include>
</html>