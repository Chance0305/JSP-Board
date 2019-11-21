<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="./layout/header.jsp"></jsp:include>
	<h2>게시판</h2>
	<table width="800">
		<tr>
			<th>글번호</th><th width="70%">글제목</th><th>글쓴이</th>
		</tr>
		<c:forEach var="vo" items="${list}">
			<tr>
				<td>${vo.id}</td>
				<td>
					<a href="/board/view?id=${vo.id}">${vo.title}</a>
				</td>
				<td>${vo.writer}</td>
			</tr>
		</c:forEach>
		
	</table>
	<a href="/board/write">글쓰기</a>
</body>
</html>