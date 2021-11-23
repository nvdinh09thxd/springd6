<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Select ALL</title>
</head>
<body>
	<h1>Select ALL by JDBC Templates</h1>
	<div>
		<a href="${pageContext.request.contextPath}/jdbc/cat/addCat">Add Cat</a>
	</div>
	<div class="msg">
		${msg}
	</div>
	<div>
		<c:choose>
			<c:when test="${not empty categories}">
				<ul>
					<c:forEach items="${categories}" var="cat">
						<li>${cat.id} -- ${cat.name} 
							<span> <> </span>
							<a href="${pageContext.request.contextPath}/jdbc/cat/edit/${cat.id}">Edit</a>
							<span> -- </span>
							<a href="${pageContext.request.contextPath}/jdbc/cat/del/${cat.id}" onclick="return confirm('Do you want delete the category?')">Delete</a>
						</li>
					</c:forEach>
				</ul>
			</c:when>
			<c:otherwise>
				<p>Not data</p>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>