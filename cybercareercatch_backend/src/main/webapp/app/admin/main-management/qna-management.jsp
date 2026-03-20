<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<title>질의문 관리</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/community-management/qna-management.css">
</head>

<body class="qnaMgmt-body">

	<div class="qnaMgmt-ctr">

		<header class="qnaMgmt-hdr">

			<!-- 관리자 메인 대시보드로 이동 -->
			<div class="qnaMgmt-title">
				<div class="qnaMgmt-title">
					<a
						href="${pageContext.request.contextPath}/app/admin/admin-main.jsp">관리자
						페이지</a>
				</div>
			</div>

			<!-- 상단 메뉴 -->
			<nav class="qnaMgmt-nav">
				<a
					href="${pageContext.request.contextPath}/app/admin/member-management/member-info.jsp">회원
					관리</a> <a
					href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">메인
					관리</a> <a
					href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">질의문
					관리</a>
			</nav>

			<!-- 로그아웃 -->
			<a href="${pageContext.request.contextPath}/admin/logout.adfc"
				class="qnaMgmt-logout">로그아웃</a>

		</header>

		<main class="qnaMgmt-main">

			<!-- 왼쪽 사이드바 -->
			<aside class="qna-leftbar">

				<div class="qna-left-item qna-active">
					<a
						href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">질의문
						관리</a>
				</div>

				<div class="qna-left-item">
					<a
						href="${pageContext.request.contextPath}/app/admin/main-management/company-info.jsp">기업
						정보페이지</a>
				</div>

				<div class="qna-left-item">
					<a
						href="${pageContext.request.contextPath}/app/admin/main-management/roadmap-management.jsp">로드맵
						관리</a>
				</div>

			</aside>

			<!-- 오른쪽 콘텐츠 -->
			<form
				action="${pageContext.request.contextPath}/admin/insertQuestionOk.adfc"
				method="post">
				<section class="qnaMgmt-ct">

					<c:forEach var="question" items="${questionList}">
						<div class="qnaMgmt-qItem">
							<div class="qnaMgmt-qTitle">질문
								${question.jobQuestionNumber}</div>
							<input type="hidden"
								name="jobQuestionNumber${question.jobQuestionNumber}"
								value="${question.jobQuestionNumber}"> <input
								type="text"
								name="jobQuestionContent${question.jobQuestionNumber}"
								value="${question.jobQuestionContent}">
						</div>
					</c:forEach>

					<!-- 하단 버튼 영역 -->
					<div class="qnaMgmt-bottomBox">
						<a class="qnaMgmt-answerBtn"
							href="${pageContext.request.contextPath}/admin/jobCheck.adfc">질의문
							답변 목록</a>

						<button type="submit" class="qnaMgmt-check">저장</button>
					</div>

				</section>
			</form>

		</main>

	</div>

	<script
		src="${pageContext.request.contextPath}/assets/js/admin/community-management/qna-management.js"></script>
</body>

</html>