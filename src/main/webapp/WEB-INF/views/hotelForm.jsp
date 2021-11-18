<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hotel details</title>
<style type="text/css">
  .error{
     color:red;
  }
</style>
</head>
		
<body>
	<form:form action="/createHotel" method="post" modelAttribute="hotel">
		<table>			
			<tr>
				<td>Hotel Name:</td>
				<td><form:input type="text" path="hotelName" /></td>
				<td><form:errors path="hotelName" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>Address:</td>
				<td><form:input type="text" path="address" /></td>
				<td><form:errors path="address" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>City:</td>
				<td><form:input type="text" path="city" /></td>
				<td><form:errors path="city" cssClass="error"/></td>
			</tr>
			
			
			<tr>
				<td>State:</td>
				<td><form:input type="text" path="state" /></td>
				<td><form:errors path="state" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>Email:</td>
				<td><form:input type="text" path="email" /></td>
				<td><form:errors path="email" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>Mobile:</td>
				<td><form:input type="text" path="mobile" /></td>
				<td><form:errors path="mobile" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>Image url:</td>
				<td><form:input type="text" path="imageUrl" /></td>
				<td><form:errors path="imageUrl" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>Star:</td>
				<td><form:input type="text" path="star" /></td>
				<td><form:errors path="star" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>Amenities:</td>
				<td>
					<form:select path="hotelAmenities" required="required">
						<form:option value="None" label="Select" />
						<form:options items="$(amenitiesHotel)" />
					</form:select>
				</td>
				<td><form:errors path="hotelAmenities" cssClass="error"/></td>
			</tr>
			
			<tr>				
				<td><input type="submit" name="submit"  value="Submit"/></td>
			</tr>
		</table>

	</form:form>
	
</body>
</html>