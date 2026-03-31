<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
if (session.getAttribute("adminNumber") == null) {
	response.sendRedirect(request.getContextPath() + "/admin/login.adfc");
	return;
}
%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>관리자 메인 대시보드</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/admin-main.css">
</head>

<body class="admin-main-body">

	<div class="admin-main-container">

		<header class="admin-main-top">

			<div class="admin-main-title">
				<a href="${pageContext.request.contextPath}/admin/main.adfc">관리자 페이지</a>
			</div>

			<nav class="admin-main-menu">
				<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">회원 관리</a>
				<a href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">메인 관리</a>
				<a href="${pageContext.request.contextPath}/admin/expoSchedule.adfc">커뮤니티 관리</a>
			</nav>

			<a href="${pageContext.request.contextPath}/admin/logout.adfc" class="admin-main-logout">로그아웃</a>
		</header>

		<main class="admin-main-main">

			<section class="admin-main-content">

				<h2 class="admin-main-dashboard-title">관리자 메인 대시보드</h2>
				<p class="admin-main-desc">관리자가 자주 사용하는 기능으로 바로 이동할 수 있는 페이지입니다.</p>

				<div class="admin-main-stats">
					<div class="admin-main-stat-box">
						<div class="admin-main-stat-box-title">총 일반 회원 수</div>
						<div class="admin-main-stat-box-number">${memberTotal}</div>
					</div>

					<div class="admin-main-stat-box">
						<div class="admin-main-stat-box-title">총 기업 회원 수</div>
						<div class="admin-main-stat-box-number">${companyMemberCount}</div>
					</div>

					<div class="admin-main-stat-box">
						<div class="admin-main-stat-box-title">총 박람회 수</div>
						<div class="admin-main-stat-box-number">${expoCount}</div>
					</div>
				</div>

				<div class="admin-main-shortcut-wrap">

					<div class="admin-main-card">
						<h3 class="admin-main-card-title">일반회원 정보 조회</h3>
						<p class="admin-main-card-desc">일반회원 목록 조회, 검색, 상세 조회, 삭제를 관리합니다.</p>
						<a class="admin-main-card-btn" href="${pageContext.request.contextPath}/admin/memberInfo.adfc">바로가기</a>
					</div>

					<div class="admin-main-card">
						<h3 class="admin-main-card-title">기업회원 승인 / 반려</h3>
						<p class="admin-main-card-desc">기업회원 승인, 반려 처리를 진행합니다.</p>
						<a class="admin-main-card-btn" href="${pageContext.request.contextPath}/admin/companyCheck.adfc">바로가기</a>
					</div>

					<div class="admin-main-card">
						<h3 class="admin-main-card-title">기업회원 정보 조회</h3>
						<p class="admin-main-card-desc">기업회원 목록 조회, 검색, 상세 조회, 삭제를 관리합니다.</p>
						<a class="admin-main-card-btn" href="${pageContext.request.contextPath}/admin/recruiterInfo.adfc">바로가기</a>
					</div>

					<div class="admin-main-card">
						<h3 class="admin-main-card-title">질의문 관리</h3>
						<p class="admin-main-card-desc">질의문 조회, 수정, 저장을 관리합니다.</p>
						<a class="admin-main-card-btn" href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">바로가기</a>
					</div>

					<div class="admin-main-card">
						<h3 class="admin-main-card-title">기업 정보페이지</h3>
						<p class="admin-main-card-desc">기업 정보 목록 조회, 검색, 삭제를 관리합니다.</p>
						<a class="admin-main-card-btn" href="${pageContext.request.contextPath}/admin/companyInfoListOk.adfc">바로가기</a>
					</div>

					<div class="admin-main-card">
						<h3 class="admin-main-card-title">로드맵 관리</h3>
						<p class="admin-main-card-desc">직군별 로드맵 정보를 수정하고 저장합니다.</p>
						<a class="admin-main-card-btn" href="${pageContext.request.contextPath}/admin/roadmapManagement.adfc">바로가기</a>
					</div>

				</div>

			</section>

		</main>

	</div>

</body>
</html>