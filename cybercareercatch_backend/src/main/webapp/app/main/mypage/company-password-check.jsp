<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>기업회원마이페이지-비밀번호확인</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/main/mypage/company-password-check.css">
<script defer
	src="${contextPath}/assets/js/main/mypage/company-password-check.js"></script>
</head>
<body>
	<main>
		<div class="main-container">
			<div class="mypage-title">마이페이지</div>
			<div class="main-session">
				<form id="passwordCheckForm" action="${pageContext.request.contextPath}/company/mypage/checkPwOk.mpfc" method="post">
					<div class="mypageedit-subtitle">기업 정보 변경을 위해 비밀번호를 입력하세요</div>
					<input type="password" id="userPw" name="userPw" class="mypageedit-inputpw"
						placeholder="비밀번호 입력">
					<div class="pw-error" id="pwError">
						<c:out value="${passwordError}" default=""/>
					</div>
					<div class="btn-box">
						<button type="submit" class="btn" id="confirm">확인</button>
						<a href="${pageContext.request.contextPath}/company/mypage.mpfc" class="btn" id="return">돌아가기</a>
					</div>
				</form>

			</div>
		</div>
	</main>
</body>
</html>