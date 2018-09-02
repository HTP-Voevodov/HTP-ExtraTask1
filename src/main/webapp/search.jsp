<%@ page language="java" pageEncoding="UTF-8" session="true" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h2>Search page</h2>
<body>
	<form method="get" action="add.jsp">
		<input type="submit" value="go to main page ->">
	</form>
	<font color="red"><c:out value="${errorMessage}" /></font>
	<br>
	<form method="post" action="EntryController">
		<input type="hidden" name = "command" value="searchEntry">
		<table>
			<tr>
				<td>News category</td>
				<td><input type="text" name="category"></td>
			</tr>
			<tr>
				<td>News subcategory</td>
				<td><input type="text" name="subCategory"></td>
			</tr>
			<tr>
				<td>News name</td>
				<td><input type="text" name="newsName"></td>
			</tr>
			<tr>
				<td>News provider <br> (any author from list) </td>
				<td><input type="text" name="provider"></td>
			</tr>
			<tr>
				<td>News dateOfIssue</td>
				<td><input type="text" name="dateOfIssue"></td>
			</tr>
			<tr>
				<td>Any word form news body</td>
				<td><input type="text" name="newsBody"></td>
			</tr>
			<tr>
				<td/>
				<td>
					<input type="submit" value="Show news">
				</td>
			</tr>
		</table>
		<br>
		<br>
						
		<table border = "1">
			<col width="150">
  			<col width="150">
  			<col width="150">
  			<col width="150">
  			<col width="150">
  			<col width="500">
			<tr>    
				<th>News category</th>
			    <th>News subcategory</th>
			    <th>News name</th>
			    <th>News provider</th>
			    <th>News dateOfIssue</th>
			    <th>News body</th>
			</tr>
			 
			<c:forEach items="${requestScope.newsForPage}" var="element"> 
  			<tr>
   				<td><c:out value="${element.category}" /></td>
				<td><c:out value="${element.subCategory}" /></td>
				<td><c:out value="${element.newsName}" /></td>
				<td><c:out value="${element.provider}" /></td>
				<td><c:out value="${element.dateOfIssue}" /></td>
				<td><c:out value="${element.newsBody}" /></td> 
    		</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>