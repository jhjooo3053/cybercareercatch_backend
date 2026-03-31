<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
/*
 * 로그인하지 않은 관리자는
 * 기업회원 상세 페이지에 접근할 수 없도록 막는다.
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
<title>기업회원 상세 조회</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/member-management/recruiter-infodetail.css">
</head>

<body class="recruiter-detail-body">

	<div class="recruiter-detail-container">

		<header class="recruiter-detail-header">

			<div class="recruiter-detail-title">
				<a href="${pageContext.request.contextPath}/admin/main.adfc">관리자
					페이지</a>
			</div>

			<nav class="recruiter-detail-menu">
				<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">회원
					관리</a> <a
					href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">메인
					관리</a> <a
					href="${pageContext.request.contextPath}/admin/expoSchedule.adfc">커뮤니티
					관리</a>
			</nav>

			<a href="${pageContext.request.contextPath}/admin/logout.adfc"
				class="recruiter-detail-logout">로그아웃</a>

		</header>

		<main class="recruiter-detail-main">

			<!-- 왼쪽 사이드바 -->
			<aside class="recruiter-detail-sidebar">

				<div class="recruiter-detail-sidebar-item">
					<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">일반회원
						정보 조회</a>
				</div>

				<div class="recruiter-detail-sidebar-item">
					<a
						href="${pageContext.request.contextPath}/admin/companyCheck.adfc">기업회원
						승인, 반려</a>
				</div>

				<div class="recruiter-detail-sidebar-item recruiter-detail-active">
					<a
						href="${pageContext.request.contextPath}/admin/recruiterInfo.adfc">기업회원
						정보 조회</a>
				</div>
				<div class="recruiter-detail-sidebar-item">
					<a href="${pageContext.request.contextPath}/admin/jobCheck.adfc">질의문
						답변 조회</a>
				</div>

			</aside>

			<!-- 오른쪽 콘텐츠 -->
			<section class="recruiter-detail-content">

				<div class="recruiter-detail-top">
					<h2 class="recruiter-detail-page-title">기업회원 상세 정보</h2>
					<a
						href="${pageContext.request.contextPath}/admin/recruiterInfo.adfc"
						class="recruiter-detail-back-btn">목록으로</a>
				</div>

				<div class="recruiter-detail-box">

					<div class="recruiter-detail-row">
						<div class="recruiter-detail-label">회원번호</div>
						<div class="recruiter-detail-value">${recruiterInfo.userNumber}</div>
					</div>

					<div class="recruiter-detail-row">
						<div class="recruiter-detail-label">아이디</div>
						<div class="recruiter-detail-value">${recruiterInfo.userId}</div>
					</div>

					<div class="recruiter-detail-row">
						<div class="recruiter-detail-label">이름</div>
						<div class="recruiter-detail-value">${recruiterInfo.userName}</div>
					</div>

					<div class="recruiter-detail-row">
						<div class="recruiter-detail-label">전화번호</div>
						<div class="recruiter-detail-value">${recruiterInfo.userPhone}</div>
					</div>

					<div class="recruiter-detail-row">
						<div class="recruiter-detail-label">가입일</div>
						<div class="recruiter-detail-value">${recruiterInfo.userJoinDate}</div>
					</div>

				</div>

			</section>

		</main>

	</div>
</body>
</html>