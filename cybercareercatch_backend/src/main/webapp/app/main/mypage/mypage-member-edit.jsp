<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>일반회원 마이페이지(수정)</title>

<link rel="stylesheet"
	href="${contextPath}/assets/css/main/mypage/mypage-member-edit.css">

<script>
	const contextPath = "${contextPath}";
</script>
<script defer
	src="${contextPath}/assets/js/main/mypage/member-mypage-edit.js"></script>
</head>

<body>
	<main>
		<div class="main-container">
			<div class="mypage-title">마이페이지</div>

			<!-- 회원정보 변경 -->
			<form id="member-phone-form"
				action="${pageContext.request.contextPath}/member/mypage/updatePhone.mpfc"
				method="post">

				<div class="mypage-subtitle">회원정보 변경</div>
				<div class="main-container-section-changeinfo">

					<div class="mypage-info-box">
						<div class="mypage-info-title">아이디</div>
						<div class="mypage-input-wrap">
							<div class="mypage-info-text" id="member-id">${memberInfo.userId}</div>
						</div>
					</div>

					<div class="mypage-info-box">
						<div class="mypage-info-title">전화번호</div>
						<div class="mypage-input-wrap">
							<input type="text" name="userPhone" class="mypage-info-text"
								id="member-phonenumber"
								value="${memberInfo.userPhone}"
								placeholder="01012345678"
								maxlength="11">
							<div class="input-message" id="member-phonenumber-message">${phoneMessage}</div>
						</div>
						<button type="button" class="btn" id="member-phonenumber-submit-btn">전송</button>
					</div>

					<div class="mypage-info-box">
						<div class="mypage-info-title">인증번호</div>
						<div class="mypage-input-wrap">
							<input type="text" name="authCode" class="mypage-info-text"
								id="member-verificationcode"
								placeholder="인증번호"
								maxlength="6">
							<div class="input-message" id="member-verificationcode-message">${authMessage}</div>
						</div>
						<button type="button" class="btn" id="member-verificationcode-btn">확인</button>
					</div>

					<div class="mypage-button-box">
						<button type="submit" class="btn" id="mypage-infoedit-btn">수정</button>
						<button type="button" class="btn" id="mypage-infoeditcancel-btn">취소</button>
					</div>
				</div>
			</form>

			<!-- 비밀번호 변경 -->
			<form id="member-password-form"
				action="${pageContext.request.contextPath}/member/mypage/updatePw.mpfc"
				method="post">

				<div class="mypage-subtitle">비밀번호 변경</div>
				<div class="main-container-section-changeinfo">

					<div class="mypage-info-box">
						<div class="mypage-info-title">현재 비밀번호</div>
						<div class="mypage-input-wrap">
							<input type="password" name="currentUserPw"
								class="mypage-info-text" id="member-current-pw">
							<div class="input-message" id="member-current-pw-message">${currentPwMessage}</div>
						</div>
						<button type="button" class="btn" id="currentpw-check-btn">확인</button>
					</div>

					<div class="mypage-info-box">
						<div class="mypage-info-title">변경할 비밀번호</div>
						<div class="mypage-input-wrap">
							<input type="password" name="newUserPw"
								class="mypage-info-text" id="member-change-pw">
							<div class="input-message" id="member-change-pw-message">${newPwMessage}</div>
						</div>
					</div>

					<div class="mypage-info-box">
						<div class="mypage-info-title">변경할 비밀번호 확인</div>
						<div class="mypage-input-wrap">
							<input type="password" name="newUserPwConfirm"
								class="mypage-info-text" id="member-check-pw">
							<div class="input-message" id="member-check-pw-message">${newPwConfirmMessage}</div>
						</div>
						<button type="button" class="btn" id="changepw-check-btn">확인</button>
					</div>
				</div>

				<div class="mypage-button-box">
					<button type="submit" class="btn" id="mypage-pwedit-btn">수정</button>
					<button type="button" class="btn" id="mypage-pweditcancel-btn">취소</button>
				</div>
			</form>

			<div class="member-quit-wrap">
				<a href="${pageContext.request.contextPath}/member/mypage/quit.mpfc"
					class="btn" id="member-quit">회원탈퇴</a>
			</div>
		</div>
	</main>
</body>

</html>