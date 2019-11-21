<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty msg}">
	<div class="msg-div">
		<h3>${msg}</h3>
	</div>
	<c:remove var="msg"/>
</c:if>
<c:if test="${not empty user}">
	<h1>${user.name}님안녕하세요.</h1>
	<a href="/board">게시판</a>
	<a href="/logout">로그아웃</a>
	<hr>
</c:if>