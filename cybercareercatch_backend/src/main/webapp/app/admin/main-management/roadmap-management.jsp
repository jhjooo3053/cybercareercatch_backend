<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<title>로드맵 관리</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/main-management/roadmap-management.css">
</head>

<body class="rm-body">

	<div class="rm-container">

		<header class="rm-hdr">

			<div class="rm-title">
				<a href="${pageContext.request.contextPath}/admin/main.adfc">관리자 페이지</a>
			</div>

			<nav class="rm-nav">
				<a href="${pageContext.request.contextPath}/admin/memberInfo.adfc">회원 관리</a>
				<a href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">메인 관리</a>
				<a href="${pageContext.request.contextPath}/app/admin/community-management/expo-schedule.jsp">커뮤니티 관리</a>
			</nav>

			<a href="${pageContext.request.contextPath}/admin/logout.adfc"
				class="rm-logout">로그아웃</a>

		</header>

		<main class="rm-main">

			<aside class="rm-leftbar">
				<div class="rm-left-item">
					<a href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">질의문 관리</a>
				</div>

				<div class="rm-left-item">
					<a href="${pageContext.request.contextPath}/admin/companyInfoListOk.adfc">기업 정보페이지</a>
				</div>

				<div class="rm-left-item rm-active">
					<a href="${pageContext.request.contextPath}/admin/roadmapManagement.adfc?jobNumber=1">로드맵 관리</a>
				</div>
			</aside>

			<section class="rm-content">

				<div class="rm-top-tab">
					<a href="${pageContext.request.contextPath}/admin/roadmapManagement.adfc?jobNumber=1"
						class="rm-tab ${jobNumber == 1 ? 'rm-tab-active' : ''}">보안컨설팅</a>

					<a href="${pageContext.request.contextPath}/admin/roadmapManagement.adfc?jobNumber=2"
						class="rm-tab ${jobNumber == 2 ? 'rm-tab-active' : ''}">시스템/네트워크 엔지니어</a>

					<a href="${pageContext.request.contextPath}/admin/roadmapManagement.adfc?jobNumber=3"
						class="rm-tab ${jobNumber == 3 ? 'rm-tab-active' : ''}">보안관제</a>

					<a href="${pageContext.request.contextPath}/admin/roadmapManagement.adfc?jobNumber=4"
						class="rm-tab ${jobNumber == 4 ? 'rm-tab-active' : ''}">침해사고/디지털포렌식</a>
				</div>

				<form action="${pageContext.request.contextPath}/admin/updateRoadmap.adfc"
					method="post" id="roadmapForm" enctype="multipart/form-data">

					<input type="hidden" name="jobNumber" value="${jobNumber}">
					<input type="hidden" name="currentRoadmapImagePath" value="${roadmap.roadmapImagePath}">

					<div class="rm-section">
						<div class="rm-section-title">기본 정보</div>

						<div class="rm-row">
							<label class="rm-label">현재 이미지</label>
							<div style="flex:1;">
								<c:choose>
									<c:when test="${not empty roadmap.roadmapImagePath and fn:startsWith(roadmap.roadmapImagePath, '/')}">
										<img
											src="${pageContext.request.contextPath}${roadmap.roadmapImagePath}"
											alt="로드맵 이미지"
											style="max-width:300px; max-height:220px; border:1px solid #ddd; border-radius:8px; background:#fff; padding:8px;">
									</c:when>

									<c:when test="${not empty roadmap.roadmapImagePath}">
										<img
											src="${pageContext.request.contextPath}/assets/img/${roadmap.roadmapImagePath}"
											alt="로드맵 이미지"
											style="max-width:300px; max-height:220px; border:1px solid #ddd; border-radius:8px; background:#fff; padding:8px;">
									</c:when>

									<c:otherwise>
										<div style="padding:20px; border:1px solid #ddd; border-radius:8px; background:#fafafa;">
											등록된 이미지가 없습니다.
										</div>
									</c:otherwise>
								</c:choose>
							</div>
						</div>

						<div class="rm-row">
							<label class="rm-label">이미지 파일</label>
							<input class="rm-input" type="file" name="roadmapImageFile" accept="image/*">
						</div>

						<div class="rm-row">
							<label class="rm-label">저장된 경로</label>
							<input class="rm-input rm-readonly" type="text"
								value="${roadmap.roadmapImagePath}" readonly="readonly">
						</div>

						<div class="rm-row">
							<label class="rm-label">직군 이름</label>
							<input class="rm-input rm-readonly" type="text" value="${roadmapTitle}" readonly="readonly">
						</div>

						<div class="rm-row rm-row-top">
							<label class="rm-label">직군 설명</label>
							<textarea class="rm-textarea rm-readonly" readonly="readonly">${roadmapSubDesc}</textarea>
						</div>
					</div>

					<div class="rm-section">
						<div class="rm-section-title">직업</div>

						<c:forEach begin="0" end="5" var="i">
							<input type="hidden" name="roadmapJobNumber${i + 1}" value="${roadmapJobList[i].roadmapJobNumber}">

							<div class="rm-row">
								<label class="rm-label">직업 ${i + 1}</label>
								<input class="rm-input" type="text" name="roadmapJobName${i + 1}"
									value="${roadmapJobList[i].roadmapJobName}">
							</div>

							<div class="rm-row rm-row-top">
								<label class="rm-label">직업 ${i + 1} 설명</label>
								<textarea class="rm-textarea" name="roadmapJobDesc${i + 1}">${roadmapJobList[i].roadmapJobDesc}</textarea>
							</div>
						</c:forEach>
					</div>

					<div class="rm-section">
						<div class="rm-section-title">1. 기초 다지기</div>

						<c:forEach begin="0" end="2" var="i">
							<input type="hidden" name="curriculumNumber${i + 1}" value="${curriculumList[i].curriculumNumber}">

							<div class="rm-row">
								<label class="rm-label">내용 ${i + 1}</label>
								<input class="rm-input" type="text" name="skillName${i + 1}" value="${curriculumList[i].skillName}">
							</div>

							<div class="rm-row rm-row-top">
								<label class="rm-label">내용 ${i + 1} 설명</label>
								<textarea class="rm-textarea" name="skillDesc${i + 1}">${curriculumList[i].skillDesc}</textarea>
							</div>
						</c:forEach>
					</div>

					<div class="rm-section">
						<div class="rm-section-title">2. 보안 기초 다지기</div>

						<c:forEach begin="3" end="5" var="i">
							<input type="hidden" name="curriculumNumber${i + 1}" value="${curriculumList[i].curriculumNumber}">

							<div class="rm-row">
								<label class="rm-label">내용 ${i - 2}</label>
								<input class="rm-input" type="text" name="skillName${i + 1}" value="${curriculumList[i].skillName}">
							</div>

							<div class="rm-row rm-row-top">
								<label class="rm-label">내용 ${i - 2} 설명</label>
								<textarea class="rm-textarea" name="skillDesc${i + 1}">${curriculumList[i].skillDesc}</textarea>
							</div>
						</c:forEach>
					</div>

					<div class="rm-section">
						<div class="rm-section-title">3. 실습</div>

						<c:forEach begin="6" end="8" var="i">
							<input type="hidden" name="curriculumNumber${i + 1}" value="${curriculumList[i].curriculumNumber}">

							<div class="rm-row">
								<label class="rm-label">내용 ${i - 5}</label>
								<input class="rm-input" type="text" name="skillName${i + 1}" value="${curriculumList[i].skillName}">
							</div>

							<div class="rm-row rm-row-top">
								<label class="rm-label">내용 ${i - 5} 설명</label>
								<textarea class="rm-textarea" name="skillDesc${i + 1}">${curriculumList[i].skillDesc}</textarea>
							</div>
						</c:forEach>
					</div>

					<div class="rm-section">
						<div class="rm-section-title">4. 결과물</div>

						<c:forEach begin="9" end="11" var="i">
							<input type="hidden" name="curriculumNumber${i + 1}" value="${curriculumList[i].curriculumNumber}">

							<div class="rm-row">
								<label class="rm-label">내용 ${i - 8}</label>
								<input class="rm-input" type="text" name="skillName${i + 1}" value="${curriculumList[i].skillName}">
							</div>

							<div class="rm-row rm-row-top">
								<label class="rm-label">내용 ${i - 8} 설명</label>
								<textarea class="rm-textarea" name="skillDesc${i + 1}">${curriculumList[i].skillDesc}</textarea>
							</div>
						</c:forEach>
					</div>

					<div class="rm-section">
						<div class="rm-section-title">추천 기술</div>

						<c:forEach begin="0" end="6" var="i">
							<input type="hidden" name="recNumber${i + 1}" value="${recList[i].recNumber}">

							<div class="rm-row">
								<label class="rm-label">내용 ${i + 1}</label>
								<input class="rm-input" type="text" name="recName${i + 1}" value="${recList[i].recName}">
							</div>
						</c:forEach>
					</div>

					<div class="rm-section">
						<div class="rm-section-title">추천 성향</div>

						<c:forEach begin="7" end="10" var="i">
							<input type="hidden" name="recNumber${i + 1}" value="${recList[i].recNumber}">

							<div class="rm-row">
								<label class="rm-label">내용 ${i - 6}</label>
								<input class="rm-input" type="text" name="recName${i + 1}" value="${recList[i].recName}">
							</div>
						</c:forEach>
					</div>

					<div class="rm-section">
						<div class="rm-section-title">추천 자격증</div>

						<c:forEach begin="11" end="14" var="i">
							<input type="hidden" name="recNumber${i + 1}" value="${recList[i].recNumber}">

							<div class="rm-row">
								<label class="rm-label">내용 ${i - 10}</label>
								<input class="rm-input" type="text" name="recName${i + 1}" value="${recList[i].recName}">
							</div>
						</c:forEach>
					</div>

					<div class="rm-btn-wrap">
						<button type="submit" class="rm-btn rm-btn-save">수정</button>
						<button type="reset" class="rm-btn rm-btn-reset" id="roadmapResetBtn">취소</button>
					</div>
				</form>
			</section>
		</main>
	</div>

	<script>
		function getByteLength(value) {
			return new TextEncoder().encode(value).length;
		}

		function isOverLimit(selector, maxByte) {
			const elements = document.querySelectorAll(selector);

			for (let i = 0; i < elements.length; i++) {
				const element = elements[i];
				const value = element.value == null ? "" : element.value.trim();

				if (value !== "" && getByteLength(value) > maxByte) {
					alert("글자 수 초과하셨습니다.");
					element.focus();
					return true;
				}
			}

			return false;
		}

		document.addEventListener("DOMContentLoaded", function() {
			const roadmapForm = document.getElementById("roadmapForm");
			const resetBtn = document.getElementById("roadmapResetBtn");
			const textareas = document.querySelectorAll(".rm-textarea");

			function resizeTextarea(textarea) {
				textarea.style.height = "auto";
				textarea.style.height = textarea.scrollHeight + "px";
			}

			textareas.forEach(function(textarea) {
				resizeTextarea(textarea);

				textarea.addEventListener("input", function() {
					resizeTextarea(textarea);
				});
			});

			if (roadmapForm) {
				roadmapForm.addEventListener("submit", function(e) {
					if (isOverLimit("input[name^='roadmapJobName']", 100)) {
						e.preventDefault();
						return;
					}

					if (isOverLimit("textarea[name^='roadmapJobDesc']", 4000)) {
						e.preventDefault();
						return;
					}

					if (isOverLimit("input[name^='skillName']", 100)) {
						e.preventDefault();
						return;
					}

					if (isOverLimit("textarea[name^='skillDesc']", 4000)) {
						e.preventDefault();
						return;
					}

					if (isOverLimit("input[name^='recName']", 200)) {
						e.preventDefault();
						return;
					}

					if (!confirm("로드맵 내용을 수정하시겠습니까?")) {
						e.preventDefault();
					}
				});
			}

			if (resetBtn) {
				resetBtn.addEventListener("click", function(e) {
					if (!confirm("입력한 내용을 초기화하시겠습니까?")) {
						e.preventDefault();
					}
				});
			}
		});
	</script>
</body>
</html>