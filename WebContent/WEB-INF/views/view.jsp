<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상세보기 화면</title>
</head>
<body>
	<jsp:include page="./layout/header.jsp"></jsp:include>
	<h2>글 상세보기</h2>
	<table width="400">
		<tr>
			<th>글번호</th>
			<th>${data.id}</th>
		</tr>
		<tr>
			<th>제목</th>
			<th>${data.title}</th>
		</tr>
		<tr>
			<th>내용</th>
			<th>${data.content}</th>
		</tr>

		<tr>
			<th>글쓴이</th>
			<th>${data.writer}</th>
		</tr>
		<tr>
			<th>파일</th>
			<c:if test="${not empty data.files}">
				<th><a href="/upload/${data.files}" download="${data.files}"><img src="/upload/${data.files}"></a></th>
			</c:if>
			<c:if test="${empty data.files}">
				<th>파일이 없습니다.</th>
			</c:if>
		</tr>
	</table>
	<c:if test="${data.writer eq user.id }">
		<a href="/board/delete?id=${data.id}">글 삭제</a>
		<a href="/board/modify?id=${data.id}">글 수정</a>
	</c:if>

</body>
</html>