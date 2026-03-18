<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>기업회원 마이페이지 - 회원탈퇴</title>
<link rel="stylesheet"
	href="${contextPath}/assets/css/main/mypage/mypage-company-quit.css">

<script>
	const contextPath = "${contextPath}";
</script>
<script defer
	src="${contextPath}/assets/js/main/mypage/mypage-company-quit.js"></script>
</head>

<body>
	<main>
		<div class="main-container">
			<div class="mypage-title">마이페이지</div>

			<div class="main-session">
				<form id="member-quit-form"
					action="${contextPath}/mypage/member/delete.my"
					method="post">

					<div class="mypageedit-subtitle">회원탈퇴를 위해 비밀번호를 입력하세요</div>

					<input type="password"
						class="mypageedit-inputpw"
						id="member-quit-password"
						name="userPw"
						placeholder="비밀번호 입력">

					<div class="pw-error" id="pwError" aria-live="polite"></div>

					<div class="btn-box">
						<button type="button" class="btn" id="confirm">확인</button>
						<a href="./mypage-company.js" class="btn" id="return">돌아가기</a>
					</div>
				</form>
			</div>
		</div>
	</main>
</body>

</html>