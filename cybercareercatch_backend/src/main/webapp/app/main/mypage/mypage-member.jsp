<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>일반회원 마이페이지</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/main/mypage/mypage-member.css">
</head>

<body>
	<main>
		<div class="main-container">
			<div class="mypage-title">마이페이지</div>
			<div class="mypage-subtitle">회원정보조회</div>

			<div class="main-container-section">
				<div class="mypage-info-box">
					<div class="mypage-info-title">아이디</div>
					<div class="mypage-info-text">${memberMypageInfoDTO.userId}</div>
				</div>

				<div class="mypage-info-box">
					<div class="mypage-info-title">이름</div>
					<div class="mypage-info-text">${memberMypageInfoDTO.userName}</div>
				</div>

				<div class="mypage-info-box">
					<div class="mypage-info-title">생년월일</div>
					<div class="mypage-info-text">${memberMypageInfoDTO.memberDob}</div>
				</div>

				<div class="mypage-info-box">
					<div class="mypage-info-title">성별</div>
					<div class="mypage-info-text">
						<c:choose>
							<c:when test="${memberMypageInfoDTO.memberGender eq 'M'}">남</c:when>
							<c:when test="${memberMypageInfoDTO.memberGender eq 'F'}">여</c:when>
							<c:otherwise>${memberMypageInfoDTO.memberGender}</c:otherwise>
						</c:choose>
					</div>
				</div>

				<div class="mypage-info-box">
					<div class="mypage-info-title">전화번호</div>
					<div class="mypage-info-text">${memberMypageInfoDTO.userPhone}</div>
				</div>

				<div class="mypage-info-box">
					<div class="mypage-info-title">직군</div>
					<div class="mypage-info-text">
						<c:choose>
							<c:when test="${not empty memberMypageInfoDTO.jobName}">
								${memberMypageInfoDTO.jobName}
							</c:when>
							<c:otherwise>미판정</c:otherwise>
						</c:choose>
					</div>
				</div>

				<div class="mypage-info-box">
					<div class="mypage-info-title">직군판정일</div>
					<div class="mypage-info-text">
						<c:choose>
							<c:when test="${not empty memberMypageInfoDTO.jobConfirmDate}">
								${memberMypageInfoDTO.jobConfirmDate}
							</c:when>
							<c:otherwise>-</c:otherwise>
						</c:choose>
					</div>
				</div>

				<div class="mypage-button-box">
					<a
						href="${pageContext.request.contextPath}/member/mypage/checkPw.mpfc"
						class="btn" id="mypage-edit-btn">회원 정보 수정</a> <a
						href="${pageContext.request.contextPath}/member/mypage/myQna.mpfc"
						class="btn" id="mypage-mycommunity-check">내가 작성한 게시물 목록 확인</a>
				</div>
			</div>
		</div>
	</main>
</body>

</html>