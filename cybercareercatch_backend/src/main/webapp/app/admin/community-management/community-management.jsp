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
<title>자유 게시판 관리</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/community-management/community-management.css">
</head>

<body class="qnaMgmt-body">

	<div class="qnaMgmt-ctr">

		<header class="qnaMgmt-hdr">

			<div class="qnaMgmt-title">
				<a href="${pageContext.request.contextPath}/admin/main.adfc">관리자
					페이지</a>
			</div>

			<nav class="qnaMgmt-nav">
				<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">회원
					관리</a> <a
					href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">메인
					관리</a> <a
					href="${pageContext.request.contextPath}/admin/expoSchedule.adfc">커뮤니티
					관리</a>
			</nav>

			<a href="${pageContext.request.contextPath}/admin/logout.adfc"
				class="qnaMgmt-logout">로그아웃</a>
		</header>

		<main class="qnaMgmt-main">

			<aside class="qna-leftbar">
				<div class="qna-left-item">
					<a
						href="${pageContext.request.contextPath}/admin/expoSchedule.adfc">박람회
						일정</a>
				</div>

				<div class="qna-left-item qna-active">
					<a
						href="${pageContext.request.contextPath}/admin/communityManagement.adfc">자유
						게시판</a>
				</div>

				<div class="qna-left-item">
					<a
						href="${pageContext.request.contextPath}/admin/companyQnaManagement.adfc">기업
						QnA</a>
				</div>
			</aside>

			<section class="qnaMgmt-ct">

				<form
					action="${pageContext.request.contextPath}/admin/saveFreeNotice.adfc"
					method="post" id="freeNoticeForm">

					<h2 class="qnaMgmt-notice-title">자유게시판 공지</h2>

					<textarea id="free-notice-box" name="noticeContent"
						placeholder="공지 내용을 입력하세요...">${freeNotice.postContent}</textarea>

					<div class="qnaMgmt-notice-btn">
						<button type="submit">저장</button>
					</div>
				</form>

				<div class="qnaMgmt-topBox">
					<h2 class="qnaMgmt-subtitle">자유 게시판 목록</h2>
				</div>

				<form id="freePostDeleteForm"
					action="${pageContext.request.contextPath}/admin/deleteFreePost.adfc"
					method="post">

					<input type="hidden" name="page" value="${page}">

					<div class="qnaMgmt-table">

						<div class="qnaMgmt-row qnaMgmt-head">
							<div class="col1">번호</div>
							<div class="col2">제목</div>
							<div class="col3">ID</div>
							<div class="col4">작성일</div>
							<div class="col5">삭제여부</div>
						</div>

						<c:if test="${not empty freeNotice}">
							<div class="qnaMgmt-row qnaMgmt-notice-row">
								<div class="col1">공지</div>
								<div class="col2">관리자 공지사항</div>
								<div class="col3">관리자</div>
								<div class="col4">${freeNotice.postDate}</div>
								<div class="col5"></div>
							</div>
						</c:if>

						<c:choose>
							<c:when test="${empty postList}">
								<div class="qnaMgmt-empty">등록된 자유 게시판 글이 없습니다.</div>
							</c:when>

							<c:otherwise>
								<c:forEach var="post" items="${postList}">
									<div class="qnaMgmt-row">
										<div class="col1">${post.postNumber}</div>
										<div class="col2" title="${post.postTitle}">${post.postTitle}</div>
										<div class="col3" title="${post.userId}">${post.userId}</div>
										<div class="col4">${post.postDate}</div>
										<div class="col5">
											<input type="checkbox" name="postNumber"
												value="${post.postNumber}">
										</div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>

					</div>

					<c:if test="${total > 0}">
						<div class="qnaMgmt-page">

							<c:choose>
								<c:when test="${prev}">
									<a
										href="${pageContext.request.contextPath}/admin/communityManagement.adfc?page=${startPage - 1}">&lt;</a>
								</c:when>
								<c:otherwise>
									<span>&lt;</span>
								</c:otherwise>
							</c:choose>

							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<c:choose>
									<c:when test="${i == page}">
										<span class="active">${i}</span>
									</c:when>
									<c:otherwise>
										<a
											href="${pageContext.request.contextPath}/admin/communityManagement.adfc?page=${i}">${i}</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<c:choose>
								<c:when test="${next}">
									<a
										href="${pageContext.request.contextPath}/admin/communityManagement.adfc?page=${endPage + 1}">&gt;</a>
								</c:when>
								<c:otherwise>
									<span>&gt;</span>
								</c:otherwise>
							</c:choose>

						</div>
					</c:if>

					<div class="qnaMgmt-del">
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
			const freeNoticeForm = document.getElementById("freeNoticeForm");
			const freeNoticeBox = document.getElementById("free-notice-box");

			if (!freeNoticeForm || !freeNoticeBox) {
				return;
			}

			freeNoticeForm.addEventListener("submit", function(event) {
				const content = freeNoticeBox.value.trim();

				if (content !== "" && getByteLength(content) > 4000) {
					alert("글자 수 초과하셨습니다.");
					freeNoticeBox.focus();
					event.preventDefault();
				}
			});
		});
	</script>
	<c:if test="${param.result eq 'saved'}">
		<script>
			alert("작성 완료했습니다.");
		</script>
	</c:if>
</body>

</html>