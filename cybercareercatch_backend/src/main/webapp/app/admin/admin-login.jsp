<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>관리자 로그인 페이지</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/admin-login.css">
</head>

<body>

	<div class="container">

		<div class="logo">
			CyberCareerCatch <span>Admin</span>
		</div>

		<form class="login-box"
			action="${pageContext.request.contextPath}/admin/loginOk.adfc"
			method="post">

			<div class="form-group">
				<label for="adminId">아이디</label> <input type="text" id="adminId"
					name="adminId" placeholder="Admin ID">
			</div>

			<div class="form-group">
				<label for="adminPw">비밀번호</label> <input type="password"
					id="adminPw" name="adminPw" placeholder="Admin PW">

				<%
				String login = request.getParameter("login");
				%>

				<%
				if ("fail".equals(login)) {
				%>
				<div class="error">아이디 또는 비밀번호가 올바르지 않습니다.</div>
				<%
				}
				%>
			</div>

			<button type="submit" class="login-btn">로그인</button>

		</form>

		<div class="footer">© 2026 CyberCareerCatch Company. All rights
			reserved.</div>

	</div>

</body>

</html>