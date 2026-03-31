<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
/*
 * 로그인하지 않은 관리자는
 * 일반회원 상세 페이지에 접근할 수 없도록 막는다.
 */
if (session.getAttribute("adminNumber") == null) {
	response.sendRedirect(request.getContextPath() + "/admin/login.adfc");
	return;
}
%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<title>일반회원 상세 조회</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/member-management/member-infodetail.css">
</head>

<body class="member-detail-body">

	<div class="member-detail-container">

		<!-- 상단 헤더 -->
		<header class="member-detail-header">

			<div class="member-detail-title">
				<a href="${pageContext.request.contextPath}/admin/main.adfc">관리자
					페이지</a>
			</div>

			<nav class="member-detail-menu">
				<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">회원
					관리</a> <a
					href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">메인
					관리</a> <a
					href="${pageContext.request.contextPath}/admin/expoSchedule.adfc">커뮤니티
					관리</a>
			</nav>

			<a href="${pageContext.request.contextPath}/admin/logout.adfc"
				class="member-detail-logout">로그아웃</a>
		</header>

		<main class="member-detail-main">

			<!-- 왼쪽 사이드바 -->
			<aside class="member-detail-sidebar">

				<div class="member-detail-sidebar-item member-detail-active">
					<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">일반회원
						정보 조회</a>
				</div>

				<div class="member-detail-sidebar-item">
					<a
						href="${pageContext.request.contextPath}/admin/companyCheck.adfc">기업회원
						승인, 반려</a>
				</div>

				<div class="member-detail-sidebar-item">
					<a
						href="${pageContext.request.contextPath}/admin/recruiterInfo.adfc">기업회원
						정보 조회</a>
				</div>
				<div class="member-detail-sidebar-item">
					<a href="${pageContext.request.contextPath}/admin/jobCheck.adfc">질의문
						답변 조회</a>
				</div>
			</aside>

			<!-- 오른쪽 콘텐츠 -->
			<section class="member-detail-content">

				<div class="member-detail-top">
					<h2 class="member-detail-page-title">회원 상세 정보</h2>
					<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc"
						class="member-detail-back-btn">목록으로</a>
				</div>

				<div class="member-detail-box">

					<div class="member-detail-row">
						<div class="member-detail-label">회원번호</div>
						<div class="member-detail-value">${userInfo.userNumber}</div>
					</div>

					<div class="member-detail-row">
						<div class="member-detail-label">아이디</div>
						<div class="member-detail-value">${userInfo.userId}</div>
					</div>

					<div class="member-detail-row">
						<div class="member-detail-label">이름</div>
						<div class="member-detail-value">${userInfo.userName}</div>
					</div>

					<div class="member-detail-row">
						<div class="member-detail-label">전화번호</div>
						<div class="member-detail-value">${userInfo.userPhone}</div>
					</div>

					<div class="member-detail-row">
						<div class="member-detail-label">가입일</div>
						<div class="member-detail-value">${userInfo.userJoinDate}</div>
					</div>

					<div class="member-detail-row">
						<div class="member-detail-label">생년월일</div>
						<div class="member-detail-value">${empty memberInfo.memberDob ? '-' : memberInfo.memberDob}
						</div>
					</div>

					<div class="member-detail-row">
						<div class="member-detail-label">성별</div>
						<div class="member-detail-value">${empty memberInfo.memberGender ? '-' : memberInfo.memberGender}
						</div>
					</div>

					<div class="member-detail-row">
						<div class="member-detail-label">희망 직군</div>
						<div class="member-detail-value">${preferredJobName}</div>
					</div>

				</div>

			</section>

		</main>

	</div>

</body>
</html>