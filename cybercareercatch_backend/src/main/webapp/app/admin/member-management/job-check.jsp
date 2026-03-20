<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<title>질의문 답변 목록</title>
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/assets/css/admin/member-management/job-check.css">
</head>

<body class="job-check-body">

	<div class="job-check-container">

		<!-- ================= 상단 헤더 ================= -->
		<header class="job-check-header">

			<!-- 관리자 메인 대시보드로 이동 -->
			<div class="job-check-title">
				<a href="${pageContext.request.contextPath}/app/admin/admin-main.jsp">관리자 페이지</a>
			</div>

			<!-- 상단 메뉴 -->
			<nav class="job-check-menu">
				<a href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">메인 관리</a>
				<a href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">질의문 관리</a>
				<a href="${pageContext.request.contextPath}/admin/jobCheck.adfc">질의문 답변 목록</a>
			</nav>

			<!-- 로그아웃 -->
			<a href="${pageContext.request.contextPath}/admin/logout.adfc" class="job-check-logout">로그아웃</a>

		</header>

		<!-- ================= 메인 영역 ================= -->
		<main class="job-check-main">

			<!-- 왼쪽 사이드바 -->
			<aside class="job-check-sidebar">

				<div class="job-check-sidebar-item">
					<a href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">질의문 관리</a>
				</div>

				<div class="job-check-sidebar-item job-check-active">
					<a href="${pageContext.request.contextPath}/admin/jobCheck.adfc">질의문 답변 목록</a>
				</div>

				<div class="job-check-sidebar-item">
					<a href="${pageContext.request.contextPath}/app/admin/main-management/company-info.jsp">기업 정보페이지</a>
				</div>

				<div class="job-check-sidebar-item">
					<a href="${pageContext.request.contextPath}/app/admin/main-management/roadmap-management.jsp">로드맵 관리</a>
				</div>

			</aside>

			<!-- 오른쪽 콘텐츠 -->
			<section class="job-check-content">

				<div class="job-check-title-area">
					<h2>질의문 답변 목록</h2>
				</div>

				<div class="job-check-subtitle">
					회원들이 제출한 질의문 답변 목록
				</div>

				<!-- 목록 헤더 -->
				<div class="job-check-row job-check-head">
					<div class="job-check-col number">번호</div>
					<div class="job-check-col id">아이디</div>
					<div class="job-check-col company">이름</div>
					<div class="job-check-col date">상태</div>
					<div class="job-check-col status">제출일</div>
				</div>

				<!-- 목록 데이터 -->
				<c:forEach var="jobResult" items="${jobResultList}">
					<a href="${pageContext.request.contextPath}/admin/jobCheckDetail.adfc?jobResultNumber=${jobResult.jobResultNumber}"
					   class="job-check-row">
						<div class="job-check-col number">${jobResult.jobResultNumber}</div>
						<div class="job-check-col id">user${jobResult.userNumber}</div>
						<div class="job-check-col company">${jobResult.userName}</div>
						<div class="job-check-col date">${jobResult.jobResultStatus}</div>
						<div class="job-check-col status">${jobResult.jobResultCreatedDate}</div>
					</a>
				</c:forEach>

				<!-- 페이지네이션 -->
				<div class="job-check-pagination">
					<span>&lt;</span>
					<a class="job-check-page job-check-page-active">1</a>
					<a class="job-check-page">2</a>
					<a class="job-check-page">3</a>
					<a class="job-check-page">4</a>
					<a class="job-check-page">5</a>
					<span>&gt;</span>
				</div>

			</section>

		</main>

	</div>

	<script src="${pageContext.request.contextPath}/assets/js/admin/member-management/job-check.js"></script>
</body>

</html>