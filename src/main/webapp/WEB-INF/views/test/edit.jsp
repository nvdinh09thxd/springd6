<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Category</title>
</head>
<body>
	<h1>Edit Category by JDBC Templates</h1>
	<div>
		<div>
			<h3 style="color: red; background-color: yellow; text-align: center">${msg}</h3>
		</div>
		<form method="post" action="${pageContext.request.contextPath}/jdbc/cat/edit/${cat.id}">
			<table border="1" style="margin: 20px auto; width:500px">
				<tr>
					<th>ID</th>
					<td><input type="text" value="${cat.id}" disabled name="name" size="99%" /></td>
				</tr>
				<tr>
					<th>Name</th>
					<td><input type="text" value="${cat.name}" name="name" size="99%" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Edit"/></td>
				</tr>
			</table>
		</form>
	</div>	
</body>
</html>