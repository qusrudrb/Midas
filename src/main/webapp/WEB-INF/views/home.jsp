<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.loginId == null }">
			<form action="login" method="post">
				ID : <input type="text" name="stomp_id"><br>
				PW : <input type="password" name="stomp_pw"><br>
				<input type="submit" value="로그인">
			</form>
		</c:when>
		<c:otherwise>
			<a href="basicChatRoom">일반 채팅 방</a>
			<a href="multiChatRoom?roomNum=1">1번방</a>
			<a href="multiChatRoom?roomNum=2">2번방</a>
		</c:otherwise>
	</c:choose>
</body>
</html>
