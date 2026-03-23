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
<title>기업 QnA 관리</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/main-management/company-qna.css">
</head>

<body class="qna-body">
	<div class="qna-container">

		<header class="qna-top">
			<div class="qna-title">
				<a href="${pageContext.request.contextPath}/admin/main.adfc">관리자
					페이지</a>
			</div>

			<nav class="qna-menu">
				<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">회원
					관리</a> <a
					href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">메인
					관리</a> <a
					href="${pageContext.request.contextPath}/admin/expoSchedule.adfc">커뮤니티
					관리</a>
			</nav>

			<a href="${pageContext.request.contextPath}/admin/logout.adfc"
				class="qna-logout">로그아웃</a>
		</header>

		<main class="qna-main">

			<aside class="qna-leftbar">
				<div class="qna-left-item">
					<a
						href="${pageContext.request.contextPath}/admin/expoSchedule.adfc">박람회
						일정</a>
				</div>
				<div class="qna-left-item">
					<a
						href="${pageContext.request.contextPath}/admin/communityManagement.adfc">자유
						게시판</a>
				</div>
				<div class="qna-left-item qna-active">
					<a
						href="${pageContext.request.contextPath}/admin/companyQnaManagement.adfc">기업
						QnA</a>
				</div>
			</aside>

			<section class="qna-content">

				<form
					action="${pageContext.request.contextPath}/admin/saveCompanyQnaNotice.adfc"
					method="post" id="qnaNoticeForm">

					<h2 class="qna-notice-title">기업 QnA 공지</h2>

					<textarea id="notice-box" name="noticeContent"
						placeholder="공지 내용을 입력하세요...">${qnaNotice.postContent}</textarea>

					<div class="qna-notice-btn">
						<button type="submit" id="notice-btn">저장</button>
					</div>

				</form>

				<h2 class="qna-title-text">기업 QnA</h2>

				<form
					action="${pageContext.request.contextPath}/admin/companyQnaManagement.adfc"
					method="get">
					<div class="qna-filter">
						<select name="companyNumber" onchange="this.form.submit()">
							<option value="">기업 전체</option>
							<c:forEach var="company" items="${companyList}">
								<option value="${company.companyNumber}"
									<c:if test="${companyNumber == company.companyNumber}">selected="selected"</c:if>>
									${company.companyName}</option>
							</c:forEach>
						</select>
					</div>
				</form>

				<form
					action="${pageContext.request.contextPath}/admin/deleteCompanyQna.adfc"
					method="post" id="qnaDeleteForm">

					<input type="hidden" name="page" value="${page}"> <input
						type="hidden" name="companyNumber" value="${companyNumber}">

					<div class="qna-table">

						<div class="qna-row qna-head">
							<div class="col1">번호</div>
							<div class="col2">제목</div>
							<div class="col3">기업명</div>
							<div class="col4">작성자</div>
							<div class="col5">작성일</div>
							<div class="col6">상태</div>
							<div class="col7">조회</div>
							<div class="col8">삭제</div>
						</div>

						<c:if test="${not empty qnaNotice}">
							<div class="qna-row qna-notice-row">
								<div class="col1">공지</div>
								<div class="col2">관리자 공지사항</div>
								<div class="col3">-</div>
								<div class="col4">관리자</div>
								<div class="col5">${qnaNotice.postDate}</div>
								<div class="col6">-</div>
								<div class="col7">${qnaNotice.viewCount}</div>
								<div class="col8"></div>
							</div>
						</c:if>

						<c:choose>
							<c:when test="${empty qnaList}">
								<div class="qna-empty">등록된 기업 QnA 글이 없습니다.</div>
							</c:when>

							<c:otherwise>
								<c:forEach var="qna" items="${qnaList}">
									<div class="qna-row">
										<div class="col1">${qna.postNumber}</div>
										<div class="col2" title="${qna.postTitle}">
											<a
												href="${pageContext.request.contextPath}/qna/detail.qfc?postNumber=${qna.postNumber}">
												${qna.postTitle} </a>
										</div>
										<div class="col3" title="${qna.companyName}">${qna.companyName}</div>
										<div class="col4" title="${qna.memberId}">${qna.memberId}</div>
										<div class="col5">${qna.postCreatedDate}</div>
										<div class="col6">${qna.answerStatus}</div>
										<div class="col7">${qna.viewCount}</div>
										<div class="col8">
											<input type="checkbox" name="postNumber"
												value="${qna.postNumber}">
										</div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>

					</div>

					<c:if test="${total > 0}">
						<div class="qna-page">
							<c:choose>
								<c:when test="${prev}">
									<a
										href="${pageContext.request.contextPath}/admin/companyQnaManagement.adfc?page=${startPage - 1}&companyNumber=${companyNumber}">&lt;</a>
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
											href="${pageContext.request.contextPath}/admin/companyQnaManagement.adfc?page=${i}&companyNumber=${companyNumber}">${i}</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<c:choose>
								<c:when test="${next}">
									<a
										href="${pageContext.request.contextPath}/admin/companyQnaManagement.adfc?page=${endPage + 1}&companyNumber=${companyNumber}">&gt;</a>
								</c:when>
								<c:otherwise>
									<span>&gt;</span>
								</c:otherwise>
							</c:choose>
						</div>
					</c:if>

					<div class="qna-delete">
						<button type="submit" id="delete-btn">삭제</button>
					</div>

				</form>

			</section>

		</main>

	</div>

	<script
		src="${pageContext.request.contextPath}/assets/js/admin/main-management/company-qna.js"></script>
	<script>
		function getByteLength(value) {
			return new TextEncoder().encode(value).length;
		}

		document.addEventListener("DOMContentLoaded", function() {
			const qnaNoticeForm = document.getElementById("qnaNoticeForm");
			const noticeBox = document.getElementById("notice-box");

			if (!qnaNoticeForm || !noticeBox) {
				return;
			}

			qnaNoticeForm.addEventListener("submit", function(event) {
				const content = noticeBox.value.trim();

				if (content !== "" && getByteLength(content) > 4000) {
					alert("글자 수 초과하셨습니다.");
					noticeBox.focus();
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