<%@ page language="java" pageEncoding="UTF-8" session="true" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Main page</h2>
<body>
	<form method="get" action="search.jsp">
		<input type="submit" value="go to search page ->">
	</form>
	
	<font color="red"><c:out value="${errorMessage}" /></font>
	<br>
	<form method="post" action="EntryController">
		<input type="hidden" name = "command" value="createEntry">
		<table>
			<tr>
				<td>News category</td>
				<td><input type="text" name="category" required></td>
			</tr>
			<tr>
				<td>News subcategory</td>
				<td><input type="text" name="subCategory" required></td>
			</tr>
			<tr>
				<td>News name</td>
				<td><input type="text" name="newsName" required></td>
			</tr>
			<tr>
				<td>News provider</td>
				<td><input type="text" name="provider"></td>
			</tr>
			<tr>
				<td>News dateOfIssue</td>
				<td><input type="text" name="dateOfIssue"></td>
			</tr>
			<tr>
				<td>News Body</td>
				<td><textarea name="newsBody" style = "width:500px; height:100px" required>
				</textarea></td>
			</tr>
			<tr>
				<td/>
				<td>
					<input type="submit" value="Add news">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
