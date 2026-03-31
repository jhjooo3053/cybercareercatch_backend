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
<title>일반회원 정보 조회</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/member-management/member-info.css">
</head>

<body class="member-info-body">

	<div class="member-info-container">

		<header class="member-info-header">

			<div class="member-info-title">
				<a href="${pageContext.request.contextPath}/admin/main.adfc">관리자
					페이지</a>
			</div>

			<nav class="member-info-menu">
				<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">회원
					관리</a> <a
					href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">메인
					관리</a> <a
					href="${pageContext.request.contextPath}/admin/expoSchedule.adfc">커뮤니티
					관리</a>
			</nav>

			<a href="${pageContext.request.contextPath}/admin/logout.adfc"
				class="logout-btn">로그아웃</a>

		</header>

		<main class="member-info-main">

			<aside class="member-info-sidebar">

				<div class="member-info-sidebar-item member-info-active">
					<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">일반회원
						정보 조회</a>
				</div>

				<div class="member-info-sidebar-item">
					<a
						href="${pageContext.request.contextPath}/admin/companyCheck.adfc">기업회원
						승인, 반려</a>
				</div>

				<div class="member-info-sidebar-item">
					<a
						href="${pageContext.request.contextPath}/admin/recruiterInfo.adfc">기업회원
						정보 조회</a>
				</div>
				<div class="member-info-sidebar-item">
					<a href="${pageContext.request.contextPath}/admin/jobCheck.adfc">질의문
						답변 조회</a>
				</div>

			</aside>

			<section class="member-info-content">

				<h2 class="member-info-content-title">회원 정보 및 관리</h2>

				<form
					action="${pageContext.request.contextPath}/admin/memberInfo.adfc"
					method="get" class="member-info-search-box" id="memberSearchForm"
					onsubmit="return validateMemberSearch();">
					<span class="member-info-search-label">- 검색</span> <select
						class="member-info-search-select" name="keywordType">
						<option value="userName"
							${keywordType eq 'userName' ? 'selected' : ''}>이름</option>
						<option value="userId"
							${keywordType eq 'userId' ? 'selected' : ''}>아이디</option>
					</select> <input type="text" class="member-info-search-input" name="keyword"
						value="${keyword}" placeholder="검색어 입력">

					<button type="submit" class="member-info-search-btn">검색</button>
				</form>

				<form id="memberDeleteForm"
					action="${pageContext.request.contextPath}/admin/deleteMember.adfc"
					method="post" onsubmit="return validateMemberDelete();">

					<input type="hidden" name="page" value="${page}">

					<div class="member-info-table">

						<div class="member-info-table-header">
							<div class="member-info-col member-info-col-check">선택</div>
							<div class="member-info-col member-info-col-num">번호</div>
							<div class="member-info-col member-info-col-id">아이디</div>
							<div class="member-info-col member-info-col-name">이름</div>
							<div class="member-info-col member-info-col-manage">관리</div>
						</div>

						<c:choose>
							<c:when test="${empty memberList}">
								<div class="member-info-empty">등록된 일반회원이 없습니다.</div>
							</c:when>

							<c:otherwise>
								<c:forEach var="member" items="${memberList}" varStatus="status">
									<div class="member-info-row-wrap">
										<div class="member-info-col member-info-col-check">
											<input type="checkbox" name="userNumber"
												value="${member.userNumber}">
										</div>

										<a
											href="${pageContext.request.contextPath}/admin/memberInfoDetail.adfc?userNumber=${member.userNumber}"
											class="member-info-row-link">
											<div class="member-info-row">
												<div class="member-info-col member-info-col-num">${(page - 1) * rowCount + status.index + 1}</div>
												<div class="member-info-col member-info-col-id">${member.userId}</div>
												<div class="member-info-col member-info-col-name">${member.userName}</div>
												<div class="member-info-col member-info-col-manage">상세조회</div>
											</div>
										</a>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>

						<div class="member-info-bottom">
							<button type="submit" class="member-info-delete-btn">선택
								삭제</button>
						</div>

						<c:if test="${total > 0}">
							<div class="member-info-pagination">

								<c:choose>
									<c:when test="${prev}">
										<a
											href="${pageContext.request.contextPath}/admin/memberInfo.adfc?page=${startPage - 1}&keywordType=${keywordType}&keyword=${keyword}">&lt;</a>
									</c:when>
									<c:otherwise>
										<span>&lt;</span>
									</c:otherwise>
								</c:choose>

								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<c:choose>
										<c:when test="${i == page}">
											<a class="member-info-page member-info-page-active">${i}</a>
										</c:when>
										<c:otherwise>
											<a class="member-info-page"
												href="${pageContext.request.contextPath}/admin/memberInfo.adfc?page=${i}&keywordType=${keywordType}&keyword=${keyword}">${i}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:choose>
									<c:when test="${next}">
										<a
											href="${pageContext.request.contextPath}/admin/memberInfo.adfc?page=${endPage + 1}&keywordType=${keywordType}&keyword=${keyword}">&gt;</a>
									</c:when>
									<c:otherwise>
										<span>&gt;</span>
									</c:otherwise>
								</c:choose>

							</div>
						</c:if>

					</div>
				</form>

			</section>

		</main>

	</div>

	<script>
		function getByteLength(value) {
			return new TextEncoder().encode(value).length;
		}

		function validateMemberSearch() {
			const keywordInput = document
					.querySelector("#memberSearchForm input[name='keyword']");

			if (!keywordInput) {
				return true;
			}

			const keyword = keywordInput.value.trim();

			if (keyword !== "" && getByteLength(keyword) > 20) {
				alert("글자 수 초과하셨습니다.");
				keywordInput.focus();
				return false;
			}

			return true;
		}

		function validateMemberDelete() {
			const checkedList = document
					.querySelectorAll("input[name='userNumber']:checked");

			if (checkedList.length === 0) {
				alert("삭제할 회원을 선택하세요.");
				return false;
			}

			return confirm("선택한 일반회원을 삭제하시겠습니까?");
		}
	</script>

</body>
</html>