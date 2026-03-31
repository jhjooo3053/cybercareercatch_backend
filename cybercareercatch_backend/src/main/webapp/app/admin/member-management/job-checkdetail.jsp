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
<title>질의문 답변 상세</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/member-management/job-checkdetail.css">
</head>

<body class="job-checkdetail-body">

	<div class="job-checkdetail-container">

		<header class="job-checkdetail-header">
			<div class="job-checkdetail-title">
				<a href="${pageContext.request.contextPath}/admin/main.adfc">관리자
					페이지</a>
			</div>

			<nav class="job-checkdetail-menu">
				<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">회원
					관리</a> <a
					href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">메인
					관리</a> <a
					href="${pageContext.request.contextPath}/admin/expoSchedule.adfc">커뮤니티
					관리</a>
			</nav>

			<a href="${pageContext.request.contextPath}/admin/logout.adfc"
				class="job-checkdetail-logout">로그아웃</a>
		</header>

		<main class="job-checkdetail-main">

			<aside class="job-checkdetail-sidebar">
				<div class="job-checkdetail-sidebar-item">
					<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">일반회원
						정보 조회</a>
				</div>

				<div class="job-checkdetail-sidebar-item">
					<a
						href="${pageContext.request.contextPath}/admin/companyCheck.adfc">기업회원
						승인, 반려</a>
				</div>

				<div class="job-checkdetail-sidebar-item">
					<a
						href="${pageContext.request.contextPath}/admin/recruiterInfo.adfc">기업회원
						정보 조회</a>
				</div>

				<div class="job-checkdetail-sidebar-item job-checkdetail-active">
					<a href="${pageContext.request.contextPath}/admin/jobCheck.adfc">질의문
						답변 조회</a>
				</div>
			</aside>

			<section class="job-checkdetail-content">

				<div class="job-checkdetail-top">
					<h2>질의문 답변 상세</h2>
				</div>

				<c:choose>
					<c:when test="${empty detailInfo}">
						<div class="job-checkdetail-userbox">
							<div>조회할 상세 정보가 없습니다.</div>
						</div>

						<div class="job-checkdetail-btnbox">
							<a href="${pageContext.request.contextPath}/admin/jobCheck.adfc"
								class="job-checkdetail-back">목록으로</a>
						</div>
					</c:when>

					<c:otherwise>

						<div class="job-checkdetail-userbox">
							<div>
								<strong>질의문 결과 번호 :</strong> ${detailInfo.jobResultNumber}
							</div>
							<div>
								<strong>회원 아이디 :</strong> ${detailInfo.userId}
							</div>
							<div>
								<strong>회원 이름 :</strong> ${detailInfo.userName}
							</div>
							<div>
								<strong>상태 :</strong> ${detailInfo.jobResultStatus}
							</div>
							<div>
								<strong>제출일 :</strong> ${detailInfo.jobResultCreatedDate}
							</div>
							<div>
								<strong>판정일 :</strong>
								<c:choose>
									<c:when test="${not empty detailInfo.jobResultConfirmDate}">
										${detailInfo.jobResultConfirmDate}
									</c:when>
									<c:otherwise>
										-
									</c:otherwise>
								</c:choose>
							</div>
							<div>
								<strong>현재 판정 직군 :</strong>
								<c:choose>
									<c:when test="${not empty detailInfo.jobName}">
										${detailInfo.jobName}
									</c:when>
									<c:otherwise>
										미판정
									</c:otherwise>
								</c:choose>
							</div>
						</div>

						<c:forEach var="detail" items="${detailList}">
							<div class="job-checkdetail-question">
								<div class="job-checkdetail-qtitle">
									Q${detail.jobQuestionNumber}</div>

								<div class="job-checkdetail-qcontent">
									${detail.jobQuestionContentSnapshot}</div>

								<div class="job-checkdetail-acontent">
									${detail.answerContent}</div>
							</div>
						</c:forEach>
						<c:choose>
							<c:when test="${isLatest}">
								<form
									action="${pageContext.request.contextPath}/admin/judgeJobResult.adfc"
									method="post" id="judgeForm">

									<input type="hidden" name="jobResultNumber"
										value="${detailInfo.jobResultNumber}">

									<div class="job-checkdetail-jobbox">
										<div class="job-checkdetail-jobtitle">직군 판정</div>

										<c:forEach var="jobGroup" items="${jobGroupList}">
											<label class="job-checkdetail-radio"> <input
												type="radio" name="jobNumber" value="${jobGroup.jobNumber}"
												<c:if test="${detailInfo.jobName eq jobGroup.jobName}">checked="checked"</c:if>>
												${jobGroup.jobName}
											</label>
										</c:forEach>
									</div>

									<div class="job-checkdetail-btnbox">
										<a
											href="${pageContext.request.contextPath}/admin/jobCheck.adfc"
											class="job-checkdetail-back">목록으로</a>
										<button type="submit" class="job-checkdetail-save">판정
											저장</button>
									</div>
								</form>
							</c:when>
							<c:otherwise>
								<div class="job-checkdetail-btnbox">
									<a
										href="${pageContext.request.contextPath}/admin/jobCheck.adfc"
										class="job-checkdetail-back">목록으로</a> <span
										style="color: #999; font-size: 14px;">※ 최신 검사 결과에서만 판정
										가능합니다.</span>
								</div>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</section>

		</main>

	</div>

	<script>
		document.addEventListener("DOMContentLoaded", function() {
			const judgeForm = document.getElementById("judgeForm");

			if (judgeForm) {
				judgeForm.addEventListener("submit", function(e) {
					const checkedRadio = document
							.querySelector("input[name='jobNumber']:checked");

					if (!checkedRadio) {
						alert("직군을 선택해주세요.");
						e.preventDefault();
						return;
					}

					if (!confirm("선택한 직군으로 판정하시겠습니까?")) {
						e.preventDefault();
					}
				});
			}
		});
	</script>

</body>
</html>