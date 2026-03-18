<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>질의문 관리</title>
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/assets/css/admin/community-management/qna-management.css">
</head>

<body class="qnaMgmt-body">

	<div class="qnaMgmt-ctr">

		<header class="qnaMgmt-hdr">
			<div class="qnaMgmt-title">
				<a href="${pageContext.request.contextPath}/app/admin/admin-main.jsp">관리자 페이지</a>
			</div>

			<nav class="qnaMgmt-nav">
				<a href="${pageContext.request.contextPath}/app/admin/member-management/member-info.jsp">회원 관리</a>
				<a href="${pageContext.request.contextPath}/app/admin/main-management/qna-management.jsp">메인 관리</a>
				<a href="${pageContext.request.contextPath}/app/admin/community-management/expo-schedule.jsp">커뮤니티 관리</a>
			</nav>
		</header>

		<main class="qnaMgmt-main">

			<aside class="qna-leftbar">
				<div class="qna-left-item qna-active">
					<a href="${pageContext.request.contextPath}/app/admin/main-management/qna-management.jsp">질의문 관리</a>
				</div>

				<div class="qna-left-item">
					<a href="${pageContext.request.contextPath}/app/admin/main-management/company-info.jsp">기업 정보페이지</a>
				</div>

				<div class="qna-left-item">
					<a href="${pageContext.request.contextPath}/app/admin/main-management/roadmap-management.jsp">로드맵 관리</a>
				</div>
			</aside>

			<form action="#" method="post">

				<section class="qnaMgmt-ct">

					<div class="qnaMgmt-qItem">
						<div>질문 1</div>
						<input type="hidden" name="jobQuestionNumber1" value="1">
						<input type="text" name="jobQuestionContent1" value="문제가 발생했을 때 원인을 끝까지 추적하는 편이다.">
					</div>

					<div class="qnaMgmt-qItem">
						<div>질문 2</div>
						<input type="hidden" name="jobQuestionNumber2" value="2">
						<input type="text" name="jobQuestionContent2" value="새로운 시스템이나 도구를 직접 만져보며 익히는 것이 재미있다.">
					</div>

					<div class="qnaMgmt-qItem">
						<div>질문 3</div>
						<input type="hidden" name="jobQuestionNumber3" value="3">
						<input type="text" name="jobQuestionContent3" value="규칙이나 기준을 정리하고 문서화하는 작업을 잘하는 편이다.">
					</div>

					<div class="qnaMgmt-qItem">
						<div>질문 4</div>
						<input type="hidden" name="jobQuestionNumber4" value="4">
						<input type="text" name="jobQuestionContent4" value="위험 요소를 미리 찾고 대비하는 것에 관심이 많다.">
					</div>

					<div class="qnaMgmt-qItem">
						<div>질문 5</div>
						<input type="hidden" name="jobQuestionNumber5" value="5">
						<input type="text" name="jobQuestionContent5" value="로그나 기록을 보면서 이상한 점을 찾아내는 것이 흥미롭다.">
					</div>

					<div class="qnaMgmt-qItem">
						<div>질문 6</div>
						<input type="hidden" name="jobQuestionNumber6" value="6">
						<input type="text" name="jobQuestionContent6" value="사람들에게 개념이나 절차를 설명하는 것을 부담스럽지 않게 느낀다.">
					</div>

					<div class="qnaMgmt-qItem">
						<div>질문 7</div>
						<input type="hidden" name="jobQuestionNumber7" value="7">
						<input type="text" name="jobQuestionContent7" value="정책, 절차, 가이드라인처럼 기준을 세우는 일에 관심이 있다.">
					</div>

					<div class="qnaMgmt-qItem">
						<div>질문 8</div>
						<input type="hidden" name="jobQuestionNumber8" value="8">
						<input type="text" name="jobQuestionContent8" value="사고가 발생했을 때 침착하게 대응 방향을 정리할 수 있다.">
					</div>

					<div class="qnaMgmt-qItem">
						<div>질문 9</div>
						<input type="hidden" name="jobQuestionNumber9" value="9">
						<input type="text" name="jobQuestionContent9" value="시스템의 취약한 부분을 찾아 개선하는 과정이 재미있다.">
					</div>

					<div class="qnaMgmt-qItem">
						<div>질문 10</div>
						<input type="hidden" name="jobQuestionNumber10" value="10">
						<input type="text" name="jobQuestionContent10" value="분석한 내용을 바탕으로 더 나은 보안 방법을 제안하는 편이다.">
					</div>

					<div class="qnaMgmt-bottomBox">
						<button type="submit" class="qnaMgmt-check">저장</button>
						<a class="qnaMgmt-answerBtn"
							href="${pageContext.request.contextPath}/app/admin/main-management/job-result-list.jsp">질의문 답변 목록</a>
					</div>

				</section>

			</form>

		</main>

	</div>

	<script src="${pageContext.request.contextPath}/assets/js/admin/community-management/qna-management.js"></script>
</body>

</html>