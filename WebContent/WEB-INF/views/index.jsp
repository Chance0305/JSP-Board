<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면</title>
</head>
<body>
<jsp:include page="./layout/header.jsp"></jsp:include>
	
	<c:if test="${empty user}">	
		<form method="post" action="/">
			<table border="1" style="width: 400">
				<tr>
					<th colspan="2" align="center">회원 로그인</th>
				</tr>
				<tr>
					<th align="right" width="80">아이디</th>
					<td><input class="cls1" type="text" name="id" /></td>
				</tr>
				<tr>
					<th align="right">비밀번호</th>
					<td><input class="cls1" type="password" name="password" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input class="cls2" type="submit" value="로그인">
					<a class="cls2" href="/register" >회원가입</a>
					</td>
				</tr>
			</table>
		</form>
	</c:if>
</body>
</html>