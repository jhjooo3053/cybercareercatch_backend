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
<title>박람회 일정 상세</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/community-management/expodetail-schedule.css">
</head>

<body class="expoDetail-body">

	<div class="expoDetail-container">

		<header class="expoDetail-top">

			<div class="expoDetail-title">
				<a href="${pageContext.request.contextPath}/admin/main.adfc">관리자 페이지</a>
			</div>

			<nav class="expoDetail-menu1">
				<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">회원 관리</a>
				<a href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">메인 관리</a>
				<a href="${pageContext.request.contextPath}/admin/expoSchedule.adfc">커뮤니티 관리</a>
			</nav>

			<a href="${pageContext.request.contextPath}/admin/logout.adfc"
				class="expoDetail-logout">로그아웃</a>

		</header>

		<main class="expoDetail-main">

			<aside class="expoDetail-leftbar">

				<div class="expoDetail-left-item expoDetail-active">
					<a href="${pageContext.request.contextPath}/admin/expoSchedule.adfc">박람회 일정</a>
				</div>

				<div class="expoDetail-left-item">
					<a href="${pageContext.request.contextPath}/admin/communityManagement.adfc">자유 게시판</a>
				</div>

				<div class="expoDetail-left-item">
					<a href="${pageContext.request.contextPath}/admin/companyQnaManagement.adfc">기업 QnA</a>
				</div>

			</aside>

			<section class="expoDetail-content">

				<div class="expoDetail-tab">
					<div class="expoDetail-tab-item expoDetail-active">박람회 상세</div>
				</div>

				<!-- 박람회 기본 정보 -->
				<div class="expoDetail-infoBox">
					<div class="expoDetail-infoRow">
						<div class="expoDetail-label">박람회 번호</div>
						<div class="expoDetail-value">${expoInfo.expoNumber}</div>
					</div>

					<div class="expoDetail-infoRow">
						<div class="expoDetail-label">박람회명</div>
						<div class="expoDetail-value">${expoInfo.expoName}</div>
					</div>

					<div class="expoDetail-infoRow">
						<div class="expoDetail-label">시작일</div>
						<div class="expoDetail-value">${expoInfo.startDate}</div>
					</div>

					<div class="expoDetail-infoRow">
						<div class="expoDetail-label">종료일</div>
						<div class="expoDetail-value">${expoInfo.endDate}</div>
					</div>

					<div class="expoDetail-infoRow">
						<div class="expoDetail-label">장소</div>
						<div class="expoDetail-value">${expoInfo.location}</div>
					</div>
				</div>

				<!-- 상세에서는 박람회 추가가 아니라 기업 추가만 -->
				<form action="${pageContext.request.contextPath}/admin/addExpoCompany.adfc"
					method="post" class="expoDetail-addForm">

					<input type="hidden" name="expoNumber" value="${expoInfo.expoNumber}">

					<div class="expoDetail-addTitle">참여 기업 추가</div>

					<div class="expoDetail-addRow">
						<select name="companyNumber">
							<option value="">기업 선택</option>
							<c:forEach var="company" items="${availableCompanyList}">
								<option value="${company.companyNumber}">
									${company.companyName}
								</option>
							</c:forEach>
						</select>

						<button type="submit">기업 추가</button>
					</div>

				</form>

				<div class="expoDetail-company-title">참여 기업 목록</div>

				<div class="expoDetail-company-list">

					<div class="expoDetail-company-row expoDetail-company-head">
						<div class="expoDetail-company-col col1">번호</div>
						<div class="expoDetail-company-col col2">기업명</div>
						<div class="expoDetail-company-col col3">주소</div>
					</div>

					<c:choose>
						<c:when test="${empty expoDetailList}">
							<div class="expoDetail-empty">참여 기업이 없습니다.</div>
						</c:when>

						<c:otherwise>
							<c:forEach var="detail" items="${expoDetailList}">
								<div class="expoDetail-company-row">
									<div class="expoDetail-company-col col1">${detail.companyNumber}</div>
									<div class="expoDetail-company-col col2">${detail.companyName}</div>
									<div class="expoDetail-company-col col3">${detail.companyAddress}</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>

				</div>

				<div class="expoDetail-btnWrap">
					<a href="${pageContext.request.contextPath}/admin/expoSchedule.adfc"
						class="expoDetail-backBtn">목록</a>
				</div>

			</section>

		</main>

	</div>

</body>
</html>