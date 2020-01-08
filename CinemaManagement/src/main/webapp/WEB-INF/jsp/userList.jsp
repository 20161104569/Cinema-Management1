<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <table>
		<tr>
			<th>名字</th>
			<th>年龄</th>
		</tr>
		<c:forEach items="${userList}" var="u">
			<tr>
				<td>${u.name}</td>
				<td>${u.age}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>