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
<title>기업회원 승인 / 반려</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/member-management/company-check.css">
</head>

<body class="company-check-body">

	<div class="company-check-container">

		<header class="company-check-header">

			<div class="company-check-title">
				<a href="${pageContext.request.contextPath}/admin/main.adfc">관리자 페이지</a>
			</div>

			<nav class="company-check-menu">
				<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">회원 관리</a>
				<a href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">메인 관리</a>
				<a href="${pageContext.request.contextPath}/app/admin/community-management/expo-schedule.jsp">커뮤니티 관리</a>
			</nav>

			<a href="${pageContext.request.contextPath}/admin/logout.adfc"
				class="company-check-logout">로그아웃</a>

		</header>

		<main class="company-check-main">

			<aside class="company-check-sidebar">

				<div class="company-check-sidebar-item">
					<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">일반회원 정보 조회</a>
				</div>

				<div class="company-check-sidebar-item company-check-active">
					<a href="${pageContext.request.contextPath}/admin/companyCheck.adfc">기업회원 승인, 반려</a>
				</div>

				<div class="company-check-sidebar-item">
					<a href="${pageContext.request.contextPath}/admin/recruiterInfo.adfc">기업회원 정보 조회</a>
				</div>

				<div class="company-check-sidebar-item">
					<a href="${pageContext.request.contextPath}/admin/jobCheck.adfc">질의문 답변 조회</a>
				</div>

			</aside>

			<section class="company-check-content">

				<h2 class="company-check-content-title">기업회원 승인 / 반려</h2>

				<div class="company-check-table">

					<div class="company-check-table-header">
						<div class="company-check-col company-check-col-num">번호</div>
						<div class="company-check-col company-check-col-id">아이디</div>
						<div class="company-check-col company-check-col-name">기업명</div>
						<div class="company-check-col company-check-col-date">가입일</div>
						<div class="company-check-col company-check-col-state">상태</div>
					</div>

					<c:choose>
						<c:when test="${empty companyList}">
							<div class="company-check-row company-check-row-empty">
								<div class="company-check-col company-check-col-num">-</div>
								<div class="company-check-col company-check-col-id">등록된 기업회원이 없습니다.</div>
								<div class="company-check-col company-check-col-name">-</div>
								<div class="company-check-col company-check-col-date">-</div>
								<div class="company-check-col company-check-col-state">-</div>
							</div>
						</c:when>

						<c:otherwise>
							<c:forEach var="company" items="${companyList}">
								<a
									href="${pageContext.request.contextPath}/admin/companyCheckDetail.adfc?companyNumber=${company.companyNumber}"
									class="company-check-row-link">
									<div class="company-check-row">
										<div class="company-check-col company-check-col-num">${company.companyNumber}</div>
										<div class="company-check-col company-check-col-id">${company.userId}</div>
										<div class="company-check-col company-check-col-name">${company.companyName}</div>
										<div class="company-check-col company-check-col-date">${company.userJoinDate}</div>
										<div class="company-check-col company-check-col-state">${company.companyState}</div>
									</div>
								</a>
							</c:forEach>
						</c:otherwise>
					</c:choose>

					<c:if test="${total > 0}">
						<div class="company-check-pagination">

							<c:choose>
								<c:when test="${prev}">
									<a href="${pageContext.request.contextPath}/admin/companyCheck.adfc?page=${startPage - 1}">&lt;</a>
								</c:when>
								<c:otherwise>
									<span>&lt;</span>
								</c:otherwise>
							</c:choose>

							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<c:choose>
									<c:when test="${i == page}">
										<a class="company-check-page company-check-page-active">${i}</a>
									</c:when>
									<c:otherwise>
										<a class="company-check-page"
											href="${pageContext.request.contextPath}/admin/companyCheck.adfc?page=${i}">${i}</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<c:choose>
								<c:when test="${next}">
									<a href="${pageContext.request.contextPath}/admin/companyCheck.adfc?page=${endPage + 1}">&gt;</a>
								</c:when>
								<c:otherwise>
									<span>&gt;</span>
								</c:otherwise>
							</c:choose>

						</div>
					</c:if>

				</div>

			</section>

		</main>

	</div>
</body>
</html>