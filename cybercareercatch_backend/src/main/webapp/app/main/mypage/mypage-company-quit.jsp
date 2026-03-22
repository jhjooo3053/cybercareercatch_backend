<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>기업회원 마이페이지 - 회원탈퇴 안내</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/main/mypage/mypage-company-quit.css">
</head>

<body>
	<main>
		<div class="main-container">
			<div class="mypage-title">마이페이지</div>

			<div class="main-session">
				<div class="mypage-notice-box">
					<div class="mypage-notice-title">회원탈퇴 안내</div>
					<div class="mypage-notice-text">
						기업 담당자 회원은 탈퇴가 불가능합니다.<br>
						탈퇴를 원하시면 관리자에게 문의해주세요.
					</div>

					<div class="btn-box">
						<a href="${pageContext.request.contextPath}/company/mypage.mpfc" class="btn">돌아가기</a>
					</div>
				</div>
			</div>
		</div>
	</main>
</body>
</html>