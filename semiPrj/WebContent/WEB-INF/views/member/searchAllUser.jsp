<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<h1>탐색</h1>
	<c:forEach items="${memberList}" var="m">
		<h1>${m.name }</h1>
	</c:forEach>
</body>
</html>