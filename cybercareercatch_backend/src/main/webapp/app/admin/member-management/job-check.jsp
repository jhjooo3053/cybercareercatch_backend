<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<title>질의문 답변 목록</title>
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/assets/css/admin/member-management/job-check.css">
</head>

<body class="company-check-body">

	<div class="company-check-container">

		<header class="company-check-header">

			<div class="company-check-title">
				<a href="${pageContext.request.contextPath}/app/admin/admin-main.jsp">관리자 페이지</a>
			</div>

			<nav class="company-check-menu">
				<a href="${pageContext.request.contextPath}/app/admin/member-management/member-info.jsp">회원 관리</a>
				<a href="${pageContext.request.contextPath}/app/admin/main-management/qna-management.jsp">메인 관리</a>
				<a href="${pageContext.request.contextPath}/app/admin/community-management/expo-schedule.jsp">커뮤니티 관리</a>
			</nav>

			<button type="button" class="company-check-logout">로그아웃</button>

		</header>

		<main class="company-check-main">

			<aside class="company-check-sidebar">

				<div class="company-check-sidebar-item">
					<a href="${pageContext.request.contextPath}/app/admin/main-management/qna-management.jsp">질의문 관리</a>
				</div>

				<div class="company-check-sidebar-item company-check-active">
					<a href="${pageContext.request.contextPath}/app/admin/main-management/job-check.jsp">질의문 답변 목록</a>
				</div>

				<div class="company-check-sidebar-item">
					<a href="${pageContext.request.contextPath}/app/admin/main-management/company-info.jsp">기업 정보페이지</a>
				</div>

				<div class="company-check-sidebar-item">
					<a href="${pageContext.request.contextPath}/app/admin/main-management/roadmap-management.jsp">로드맵 관리</a>
				</div>

			</aside>

			<section class="company-check-content">

				<div class="company-check-title-area">
					<h2>질의문 답변 목록</h2>
				</div>

				<div class="company-check-subtitle">
					회원들이 제출한 질의문 답변 목록
				</div>

				<div class="company-check-table">

					<div class="company-check-row company-check-head">
						<div class="company-check-col number">번호</div>
						<div class="company-check-col id">아이디</div>
						<div class="company-check-col company">이름</div>
						<div class="company-check-col date">상태</div>
						<div class="company-check-col status">제출일</div>
					</div>

					<a href="${pageContext.request.contextPath}/app/admin/main-management/job-checkdetail.jsp" class="company-check-row">
						<div class="company-check-col number">5</div>
						<div class="company-check-col id">user05</div>
						<div class="company-check-col company">정하늘</div>
						<div class="company-check-col date">제출완료</div>
						<div class="company-check-col status">2026-03-10</div>
					</a>

					<a href="${pageContext.request.contextPath}/app/admin/main-management/job-checkdetail.jsp" class="company-check-row">
						<div class="company-check-col number">4</div>
						<div class="company-check-col id">user04</div>
						<div class="company-check-col company">최지우</div>
						<div class="company-check-col date">제출완료</div>
						<div class="company-check-col status">2026-03-13</div>
					</a>

					<a href="${pageContext.request.contextPath}/app/admin/main-management/job-checkdetail.jsp" class="company-check-row">
						<div class="company-check-col number">3</div>
						<div class="company-check-col id">user03</div>
						<div class="company-check-col company">박준호</div>
						<div class="company-check-col date">제출완료</div>
						<div class="company-check-col status">2026-03-07</div>
					</a>

					<a href="${pageContext.request.contextPath}/app/admin/main-management/job-checkdetail.jsp" class="company-check-row">
						<div class="company-check-col number">2</div>
						<div class="company-check-col id">user02</div>
						<div class="company-check-col company">이서연</div>
						<div class="company-check-col date">판정완료</div>
						<div class="company-check-col status">2026-03-03</div>
					</a>

					<a href="${pageContext.request.contextPath}/app/admin/main-management/job-checkdetail.jsp" class="company-check-row">
						<div class="company-check-col number">1</div>
						<div class="company-check-col id">user01</div>
						<div class="company-check-col company">김민수</div>
						<div class="company-check-col date">판정완료</div>
						<div class="company-check-col status">2026-03-01</div>
					</a>

				</div>

				<div class="company-check-pagination">
					<span>&lt;</span>
					<a class="company-check-page company-check-page-active">1</a>
					<a class="company-check-page">2</a>
					<a class="company-check-page">3</a>
					<a class="company-check-page">4</a>
					<a class="company-check-page">5</a>
					<span>&gt;</span>
				</div>

			</section>

		</main>

	</div>

</body>

</html>