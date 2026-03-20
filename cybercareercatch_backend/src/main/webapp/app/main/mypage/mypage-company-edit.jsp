<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>기업회원 마이페이지(수정)</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/main/mypage/mypage-company-edit.css">
<script defer
	src="${pageContext.request.contextPath}/assets/js/main/mypage/mypage-company-edit.js"></script>
</head>

<body>
	<main>
		<div class="main-container">
			<div class="mypage-title">마이페이지</div>

			<!-- 기업회원 정보 수정 -->
			<form id="company-phone-form"
				action="${pageContext.request.contextPath}/company/mypage/updatePhone.mpfc"
				method="post">

				<div class="mypage-subtitle">기업정보 조회/수정</div>
				<div class="main-container-section-changeinfo">

					<div class="mypage-info-box">
						<div class="mypage-info-title">아이디</div>
						<div class="mypage-input-wrap">
							<div class="mypage-info-text" id="company-id">${companyMypageInfoDTO.userId}</div>
						</div>
					</div>

					<div class="mypage-info-box">
						<div class="mypage-info-title">기업명</div>
						<div class="mypage-input-wrap">
							<div class="mypage-info-text" id="company-name">${companyMypageInfoDTO.companyName}</div>
						</div>
					</div>

					<div class="mypage-info-box">
						<div class="mypage-info-title">사업자등록번호</div>
						<div class="mypage-input-wrap">
							<div class="mypage-info-text" id="company-number">${companyMypageInfoDTO.companyBrn}</div>
						</div>
					</div>

					<div class="mypage-info-box">
						<div class="mypage-info-title">기업 주소</div>
						<div class="mypage-input-wrap">
							<div class="mypage-info-text" id="company-address">${companyMypageInfoDTO.companyAddress}</div>
						</div>
					</div>

					<div class="mypage-info-box">
						<div class="mypage-info-title">채용담당자 이름</div>
						<div class="mypage-input-wrap">
							<div class="mypage-info-text" id="company-manager-name">${companyMypageInfoDTO.userName}</div>
						</div>
					</div>

					<div class="mypage-info-box">
						<div class="mypage-info-title">채용담당자 연락처</div>
						<div class="mypage-input-wrap">
							<input type="text" name="userPhone" class="mypage-info-text"
								id="company-manager-phonenumber"
								value="${companyMypageInfoDTO.userPhone}"
								placeholder="01012345678" maxlength="11">
							<div class="input-message" id="company-phonenumber-message">${phoneMessage}</div>
						</div>
						<button type="button" class="btn"
							id="company-phonenumber-submit-btn">전송</button>
					</div>

					<div class="mypage-info-box">
						<div class="mypage-info-title">인증번호</div>
						<div class="mypage-input-wrap">
							<input type="text" name="authCode" class="mypage-info-text"
								id="company-verificationcode" placeholder="인증번호" maxlength="6">
							<div class="input-message" id="company-verificationcode-message">${authMessage}</div>
						</div>
						<button type="button" class="btn"
							id="company-verificationcode-btn">확인</button>
					</div>

					<div class="mypage-button-box">
						<button type="submit" class="btn" id="mypage-edit-btn">수정</button>
						<button type="button" class="btn" id="mypage-editcancel-btn">취소</button>
					</div>
				</div>
			</form>

			<!-- 비밀번호 변경 -->
			<div class="mypage-subtitle">비밀번호 변경</div>
			<form id="company-password-form"
				action="${pageContext.request.contextPath}/company/mypage/updatePw.mpfc"
				method="post">

				<div class="main-container-section-changeinfo">
					<div class="mypage-info-box">
						<div class="mypage-info-title">현재 비밀번호</div>
						<div class="mypage-input-wrap">
							<input type="password" name="currentUserPw"
								class="mypage-info-text" id="company-current-pw">
							<div class="input-message" id="company-current-pw-message">${currentPwMessage}</div>
						</div>
						<button type="submit" class="btn" id="company-currentpwcheck-btn"
							formaction="${pageContext.request.contextPath}/company/mypage/checkCurrentPw.mpfc"
							formmethod="post">확인</button>
					</div>

					<div class="mypage-info-box">
						<div class="mypage-info-title">변경할 비밀번호</div>
						<div class="mypage-input-wrap">
							<input type="password" name="newUserPw" class="mypage-info-text"
								id="company-change-pw">
							<div class="input-message" id="company-change-pw-message">${newPwMessage}</div>
						</div>
					</div>

					<div class="mypage-info-box">
						<div class="mypage-info-title">변경할 비밀번호 확인</div>
						<div class="mypage-input-wrap">
							<input type="password" name="newUserPwConfirm"
								class="mypage-info-text" id="company-check-pw">
							<div class="input-message" id="company-check-pw-message">${newPwConfirmMessage}</div>
						</div>
						<button type="button" class="btn" id="company-changepwcheck-btn">확인</button>
					</div>
				</div>

				<div class="mypage-button-box">
					<button type="submit" class="btn" id="mypage-pwedit-btn">수정</button>
					<button type="button" class="btn" id="mypage-pwcancel-btn">취소</button>
				</div>
			</form>

			<div class="company-quit-wrap">
				<a
					href="${pageContext.request.contextPath}/company/mypage/quit.mpfc"
					class="btn" id="company-quit">회원탈퇴</a>
			</div>
		</div>
	</main>
</body>

</html>