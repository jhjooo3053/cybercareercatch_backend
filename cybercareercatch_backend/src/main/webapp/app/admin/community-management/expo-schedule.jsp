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
<title>박람회 일정 관리</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/community-management/expo-schedule.css">
</head>

<body class="expoSchedule-body">

	<div class="expoSchedule-container">

		<header class="expoSchedule-top">

			<div class="expoSchedule-title">
				<a href="${pageContext.request.contextPath}/admin/main.adfc">관리자
					페이지</a>
			</div>

			<nav class="expoSchedule-menu1">
				<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">회원
					관리</a> <a
					href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">메인
					관리</a> <a
					href="${pageContext.request.contextPath}/admin/expoSchedule.adfc">커뮤니티
					관리</a>
			</nav>

			<a href="${pageContext.request.contextPath}/admin/logout.adfc"
				class="expoSchedule-logout">로그아웃</a>

		</header>

		<main class="expoSchedule-main">

			<aside class="expoSchedule-leftbar">

				<div class="expoSchedule-left-item expoSchedule-active">
					<a
						href="${pageContext.request.contextPath}/admin/expoSchedule.adfc">박람회
						일정</a>
				</div>

				<div class="expoSchedule-left-item">
					<a
						href="${pageContext.request.contextPath}/admin/communityManagement.adfc">자유
						게시판</a>
				</div>

				<div class="expoSchedule-left-item">
					<a
						href="${pageContext.request.contextPath}/admin/companyQnaManagement.adfc">기업
						QnA</a>
				</div>

			</aside>

			<section class="expoSchedule-content">

				<div class="expoSchedule-tab">
					<div class="expoSchedule-tab-item expoSchedule-active">박람회 일정</div>
				</div>

				<form
					action="${pageContext.request.contextPath}/admin/insertExpo.adfc"
					method="post" class="expoSchedule-addForm" id="expoInsertForm">

					<div class="expoSchedule-formTitle">박람회 추가</div>

					<div class="expoSchedule-addRow">
						<input type="text" name="expoName" placeholder="박람회명"> <input
							type="date" name="startDate"> <input type="date"
							name="endDate"> <input type="text" name="location"
							placeholder="장소">
						<button type="submit">추가</button>
					</div>
				</form>

				<form
					action="${pageContext.request.contextPath}/admin/deleteExpo.adfc"
					method="post" id="expoDeleteForm">

					<input type="hidden" name="page" value="${page}">

					<div class="expoSchedule-table">

						<div class="expoSchedule-row expoSchedule-head">
							<div class="col0">선택</div>
							<div class="col1">번호</div>
							<div class="col2">박람회명</div>
							<div class="col3">시작일</div>
							<div class="col4">종료일</div>
							<div class="col5">장소</div>
							<div class="col6">관리</div>
						</div>

						<c:choose>
							<c:when test="${empty expoList}">
								<div class="expoSchedule-empty">등록된 박람회 일정이 없습니다.</div>
							</c:when>

							<c:otherwise>
								<c:forEach var="expo" items="${expoList}">
									<div class="expoSchedule-row">
										<div class="col0">
											<input type="checkbox" name="expoNumber"
												value="${expo.expoNumber}">
										</div>
										<div class="col1">${expo.expoNumber}</div>
										<div class="col2" title="${expo.expoName}">${expo.expoName}</div>
										<div class="col3">${expo.startDate}</div>
										<div class="col4">${expo.endDate}</div>
										<div class="col5" title="${expo.location}">${expo.location}</div>
										<div class="col6">
											<a class="expoSchedule-detailBtn"
												href="${pageContext.request.contextPath}/admin/expoDetailSchedule.adfc?expoNumber=${expo.expoNumber}">
												상세조회 </a>
										</div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>

					</div>

					<c:if test="${total > 0}">
						<div class="expoSchedule-page">

							<c:choose>
								<c:when test="${prev}">
									<a
										href="${pageContext.request.contextPath}/admin/expoSchedule.adfc?page=${startPage - 1}">&lt;</a>
								</c:when>
								<c:otherwise>
									<span>&lt;</span>
								</c:otherwise>
							</c:choose>

							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<c:choose>
									<c:when test="${i == page}">
										<span class="expoSchedule-page-active">${i}</span>
									</c:when>
									<c:otherwise>
										<a
											href="${pageContext.request.contextPath}/admin/expoSchedule.adfc?page=${i}">${i}</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<c:choose>
								<c:when test="${next}">
									<a
										href="${pageContext.request.contextPath}/admin/expoSchedule.adfc?page=${endPage + 1}">&gt;</a>
								</c:when>
								<c:otherwise>
									<span>&gt;</span>
								</c:otherwise>
							</c:choose>

						</div>
					</c:if>

					<div class="expoSchedule-del">
						<button type="submit">삭제</button>
					</div>

				</form>

			</section>

		</main>

	</div>

	<script>
		function getByteLength(value) {
			return new TextEncoder().encode(value).length;
		}

		document.addEventListener("DOMContentLoaded", function() {
			const expoInsertForm = document.getElementById("expoInsertForm");

			if (!expoInsertForm) {
				return;
			}

			expoInsertForm.addEventListener("submit", function(event) {
				const expoNameInput = expoInsertForm
						.querySelector("input[name='expoName']");
				const locationInput = expoInsertForm
						.querySelector("input[name='location']");

				if (expoNameInput) {
					const expoName = expoNameInput.value.trim();

					if (expoName !== "" && getByteLength(expoName) > 100) {
						alert("글자 수 초과하셨습니다.");
						expoNameInput.focus();
						event.preventDefault();
						return;
					}
				}

				if (locationInput) {
					const location = locationInput.value.trim();

					if (location !== "" && getByteLength(location) > 200) {
						alert("글자 수 초과하셨습니다.");
						locationInput.focus();
						event.preventDefault();
					}
				}
			});
		});
	</script>
</body>

</html>