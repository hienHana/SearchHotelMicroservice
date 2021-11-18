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
	<form:form action="/createRoom" method="post" modelAttribute="room">
		<table>			
			<tr>
				<td>Room Type:</td>
				<td>
					<form:select path="roomType" required="required">
						<form:option value="None" label="Select" />
						<form:options items="$(roomTypes)" />
					</form:select></td>
				<td><form:errors path="roomType" cssClass="error"/></td>
			</tr>				
			
			<tr>
				<td>Amenities:</td>
				<td>
					<form:select path="roomAmenities" required="required">
						<form:option value="None" label="Select" />
						<form:options items="$(amenitiesRoom)" />
					</form:select>
				</td>
				<td><form:errors path="roomAmenities" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>Description:</td>
				<td><form:input type="text" path="description" /></td>
				<td><form:errors path="description" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>Quantity:</td>
				<td><form:input type="number" path="quantity" /></td>
				<td><form:errors path="quantity" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>Price:</td>
				<td><form:input type="number" path="price" /></td>
				<td><form:errors path="price" cssClass="error"/></td>
			</tr>
			
			<tr>				
				<td><input type="submit" name="submit"  value="Submit"/></td>
			</tr>
		</table>

	</form:form>
	
</body>
</html>