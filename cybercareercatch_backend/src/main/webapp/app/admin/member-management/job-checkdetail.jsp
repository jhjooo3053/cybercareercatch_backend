<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<title>질의문 답변 상세</title>
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}assets/css/admin/member-management/job-checkdetail.css">
</head>

<body class="company-checkdetail-body">

	<div class="company-checkdetail-container">

		<header class="company-checkdetail-header">

			<div class="company-checkdetail-title">
				<a href="${pageContext.request.contextPath}/app/admin/admin-main.jsp">관리자 페이지</a>
			</div>

			<nav class="company-checkdetail-menu">
				<a href="${pageContext.request.contextPath}/app/admin/member-management/member-info.jsp">회원 관리</a>
				<a href="${pageContext.request.contextPath}/app/admin/main-management/qna-management.jsp">메인 관리</a>
				<a href="${pageContext.request.contextPath}/app/admin/community-management/expo-schedule.jsp">커뮤니티 관리</a>
			</nav>

			<button type="button" class="company-checkdetail-logout">로그아웃</button>

		</header>

		<main class="company-checkdetail-main">

			<aside class="company-checkdetail-sidebar">

				<div class="company-checkdetail-sidebar-item">
					<a href="${pageContext.request.contextPath}/app/admin/main-management/qna-management.jsp">질의문 관리</a>
				</div>

				<div class="company-checkdetail-sidebar-item company-checkdetail-active">
					<a href="${pageContext.request.contextPath}/app/admin/main-management/job-check.jsp">질의문 답변 목록</a>
				</div>

				<div class="company-checkdetail-sidebar-item">
					<a href="${pageContext.request.contextPath}/app/admin/main-management/company-info.jsp">기업 정보페이지</a>
				</div>

				<div class="company-checkdetail-sidebar-item">
					<a href="${pageContext.request.contextPath}/app/admin/main-management/roadmap-management.jsp">로드맵 관리</a>
				</div>

			</aside>

			<section class="company-checkdetail-content">

				<div class="company-checkdetail-top">
					<h2>질의문 답변 상세</h2>
				</div>

				<div class="company-checkdetail-userbox">
					<div><strong>아이디:</strong> user01</div>
					<div><strong>이름:</strong> 김민수</div>
					<div><strong>상태:</strong> 제출완료</div>
					<div><strong>제출일:</strong> 2026-03-01</div>
				</div>

				<form action="#" method="post">

					<div class="company-checkdetail-answerbox">
						<div class="company-checkdetail-question">
							<div class="company-checkdetail-qtitle">질문 1</div>
							<div class="company-checkdetail-qcontent">문제가 발생했을 때 원인을 끝까지 추적하는 편이다.</div>
							<div class="company-checkdetail-acontent">매우 그렇다</div>
						</div>

						<div class="company-checkdetail-question">
							<div class="company-checkdetail-qtitle">질문 2</div>
							<div class="company-checkdetail-qcontent">새로운 시스템이나 도구를 직접 만져보며 익히는 것이 재미있다.</div>
							<div class="company-checkdetail-acontent">그렇다</div>
						</div>

						<div class="company-checkdetail-question">
							<div class="company-checkdetail-qtitle">질문 3</div>
							<div class="company-checkdetail-qcontent">규칙이나 기준을 정리하고 문서화하는 작업을 잘하는 편이다.</div>
							<div class="company-checkdetail-acontent">매우 그렇다</div>
						</div>

						<div class="company-checkdetail-question">
							<div class="company-checkdetail-qtitle">질문 4</div>
							<div class="company-checkdetail-qcontent">위험 요소를 미리 찾고 대비하는 것에 관심이 많다.</div>
							<div class="company-checkdetail-acontent">매우 그렇다</div>
						</div>

						<div class="company-checkdetail-question">
							<div class="company-checkdetail-qtitle">질문 5</div>
							<div class="company-checkdetail-qcontent">로그나 기록을 보면서 이상한 점을 찾아내는 것이 흥미롭다.</div>
							<div class="company-checkdetail-acontent">그렇다</div>
						</div>

						<div class="company-checkdetail-question">
							<div class="company-checkdetail-qtitle">질문 6</div>
							<div class="company-checkdetail-qcontent">사람들에게 개념이나 절차를 설명하는 것을 부담스럽지 않게 느낀다.</div>
							<div class="company-checkdetail-acontent">그렇다</div>
						</div>

						<div class="company-checkdetail-question">
							<div class="company-checkdetail-qtitle">질문 7</div>
							<div class="company-checkdetail-qcontent">정책, 절차, 가이드라인처럼 기준을 세우는 일에 관심이 있다.</div>
							<div class="company-checkdetail-acontent">매우 그렇다</div>
						</div>

						<div class="company-checkdetail-question">
							<div class="company-checkdetail-qtitle">질문 8</div>
							<div class="company-checkdetail-qcontent">사고가 발생했을 때 침착하게 대응 방향을 정리할 수 있다.</div>
							<div class="company-checkdetail-acontent">그렇다</div>
						</div>

						<div class="company-checkdetail-question">
							<div class="company-checkdetail-qtitle">질문 9</div>
							<div class="company-checkdetail-qcontent">시스템의 취약한 부분을 찾아 개선하는 과정이 재미있다.</div>
							<div class="company-checkdetail-acontent">그렇다</div>
						</div>

						<div class="company-checkdetail-question">
							<div class="company-checkdetail-qtitle">질문 10</div>
							<div class="company-checkdetail-qcontent">분석한 내용을 바탕으로 더 나은 보안 방법을 제안하는 편이다.</div>
							<div class="company-checkdetail-acontent">매우 그렇다</div>
						</div>
					</div>

					<div class="company-checkdetail-jobbox">
						<div class="company-checkdetail-jobtitle">직군 선택</div>

						<label class="company-checkdetail-radio">
							<input type="radio" name="jobNumber" value="1"> 보안컨설팅
						</label>

						<label class="company-checkdetail-radio">
							<input type="radio" name="jobNumber" value="2"> 시스템/네트워크 엔지니어
						</label>

						<label class="company-checkdetail-radio">
							<input type="radio" name="jobNumber" value="3"> 보안관제
						</label>

						<label class="company-checkdetail-radio">
							<input type="radio" name="jobNumber" value="4"> 침해사고/디지털포렌식
						</label>
					</div>

					<div class="company-checkdetail-btnbox">
						<a href="${pageContext.request.contextPath}/app/admin/main-management/job-check.jsp"
							class="company-checkdetail-back">목록</a>
						<button type="submit" class="company-checkdetail-save">저장</button>
					</div>

				</form>

			</section>

		</main>

	</div>

</body>

</html>