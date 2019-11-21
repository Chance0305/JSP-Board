<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<jsp:include page="./layout/header.jsp"></jsp:include>
	<form action="/register" method="post">
		<table border="1" style="width: 400">
			<tr>
				<td colspan="2" align="center"><b>회원 가입 정보 입력</b></td>
			</tr>
			<tr>
				<td align="right">이름</td>
				<td><input class="cls1" type="text" name="name" /></td>
			</tr>
			<tr>
				<td align="right">아이디</td>
				<td><input class="cls1" type="text" name="id" /></td>
			</tr>
			<tr>
				<td align="right">비밀번호</td>
				<td><input class="cls1" type="password" name="password" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input class="cls2" type="submit" value="가입"/>
					<input class="cls2" type="reset" value="재작성" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>