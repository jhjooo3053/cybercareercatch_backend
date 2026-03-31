<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>질의문 답변 목록</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/member-management/job-check.css">
</head>

<body class="job-check-body">

	<div class="job-check-container">

		<header class="job-check-header">

			<div class="job-check-title">
				<a href="${pageContext.request.contextPath}/admin/main.adfc">관리자 페이지</a>
			</div>

			<nav class="job-check-menu">
				<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">회원 관리</a>
				<a href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">메인 관리</a>
				<a href="${pageContext.request.contextPath}/admin/expoSchedule.adfc">커뮤니티 관리</a>
			</nav>

			<a href="${pageContext.request.contextPath}/admin/logout.adfc"
				class="job-check-logout">로그아웃</a>

		</header>

		<main class="job-check-main">

			<aside class="job-check-sidebar">

				<div class="job-check-sidebar-item">
					<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">일반회원 정보 조회</a>
				</div>

				<div class="job-check-sidebar-item">
					<a href="${pageContext.request.contextPath}/admin/companyCheck.adfc">기업회원 승인, 반려</a>
				</div>

				<div class="job-check-sidebar-item">
					<a href="${pageContext.request.contextPath}/admin/recruiterInfo.adfc">기업회원 정보 조회</a>
				</div>

				<div class="job-check-sidebar-item job-check-active">
					<a href="${pageContext.request.contextPath}/admin/jobCheck.adfc">질의문 답변 조회</a>
				</div>

			</aside>

			<section class="job-check-content">

				<div class="job-check-title-area">
					<h2>질의문 답변 목록</h2>
				</div>

				<div class="job-check-subtitle">회원들이 제출한 질의문 답변 목록</div>

				<div class="job-check-row job-check-head">
					<div class="job-check-col number">번호</div>
					<div class="job-check-col id">아이디</div>
					<div class="job-check-col name">이름</div>
					<div class="job-check-col state">상태</div>
					<div class="job-check-col date">제출일</div>
					<div class="job-check-col manage">관리</div>
				</div>

				<c:choose>
					<c:when test="${empty jobResultList}">
						<div class="job-check-empty">등록된 질의문 답변이 없습니다.</div>
					</c:when>

					<c:otherwise>
						<c:forEach var="jobResult" items="${jobResultList}">
							<div class="job-check-row">
								<div class="job-check-col number">${jobResult.jobResultNumber}</div>
								<div class="job-check-col id">user${jobResult.userNumber}</div>
								<div class="job-check-col name">${jobResult.userName}</div>
								<div class="job-check-col state">${jobResult.jobResultStatus}</div>
								<div class="job-check-col date">${jobResult.jobResultCreatedDate}</div>
								<div class="job-check-col manage">
									<a
										href="${pageContext.request.contextPath}/admin/jobCheckDetail.adfc?jobResultNumber=${jobResult.jobResultNumber}"
										class="job-check-detail-link">상세조회</a>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>

				<c:if test="${total > 0}">
					<div class="job-check-pagination">

						<c:choose>
							<c:when test="${prev}">
								<a href="${pageContext.request.contextPath}/admin/jobCheck.adfc?page=${startPage - 1}">&lt;</a>
							</c:when>
							<c:otherwise>
								<span>&lt;</span>
							</c:otherwise>
						</c:choose>

						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<c:choose>
								<c:when test="${i == page}">
									<a class="job-check-page job-check-page-active">${i}</a>
								</c:when>
								<c:otherwise>
									<a class="job-check-page"
										href="${pageContext.request.contextPath}/admin/jobCheck.adfc?page=${i}">${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:choose>
							<c:when test="${next}">
								<a href="${pageContext.request.contextPath}/admin/jobCheck.adfc?page=${endPage + 1}">&gt;</a>
							</c:when>
							<c:otherwise>
								<span>&gt;</span>
							</c:otherwise>
						</c:choose>

					</div>
				</c:if>

			</section>

		</main>

	</div>

	<script
		src="${pageContext.request.contextPath}/assets/js/admin/member-management/job-check.js"></script>
</body>

</html>