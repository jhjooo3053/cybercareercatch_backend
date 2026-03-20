<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로드맵 상세</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/main/roadmap/roadmap-questionnaire1.css" />
</head>

<body>
	<div class="rmq-wrap">

		<div class="rmq-tab-wrap">

			<c:choose>
				<c:when test="${jobNumber == 1}">
					<a href="${pageContext.request.contextPath}/roadmap/detail.rfc?jobNumber=1"
						class="rmq-tab rmq-tab-active">보안컨설팅</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/roadmap/detail.rfc?jobNumber=1"
						class="rmq-tab">보안컨설팅</a>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${jobNumber == 2}">
					<a href="${pageContext.request.contextPath}/roadmap/detail.rfc?jobNumber=2"
						class="rmq-tab rmq-tab-active">시스템/네트워크</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/roadmap/detail.rfc?jobNumber=2"
						class="rmq-tab">시스템/네트워크</a>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${jobNumber == 3}">
					<a href="${pageContext.request.contextPath}/roadmap/detail.rfc?jobNumber=3"
						class="rmq-tab rmq-tab-active">보안관제</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/roadmap/detail.rfc?jobNumber=3"
						class="rmq-tab">보안관제</a>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${jobNumber == 4}">
					<a href="${pageContext.request.contextPath}/roadmap/detail.rfc?jobNumber=4"
						class="rmq-tab rmq-tab-active">침해사고/포렌식</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/roadmap/detail.rfc?jobNumber=4"
						class="rmq-tab">침해사고/포렌식</a>
				</c:otherwise>
			</c:choose>

		</div>

		<main class="rmq-hdr">
			<div class="rmq-hdr-ic">
				<img class="rmq-hdr-ic-img"
					src="${pageContext.request.contextPath}/assets/img/${roadmapIconPath}"
					alt="직군 아이콘"
					onerror="this.style.display='none';">
			</div>

			<div class="rmq-hdr-content">
				<div class="rmq-hdr-titlebox">
					<h1>${roadmapTitle}</h1>
					<div class="rmq-hdr-subbox">
						<h5>${roadmapSubDesc}</h5>
					</div>
				</div>
			</div>
		</main>

		<main class="rmq-job">
			<div class="rmq-job-rowtop">
				<c:forEach begin="0" end="2" var="i">
					<div class="rmq-job-card">
						<div class="rmq-job-cardin">
							<div class="rmq-job-cardttl">
								<h3>${roadmapJobList[i].roadmapJobName}</h3>
							</div>
							<div class="rmq-job-carddesc">
								<h5>${roadmapJobList[i].roadmapJobDesc}</h5>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>

			<div class="rmq-job-rowbottom">
				<c:forEach begin="3" end="5" var="i">
					<div class="rmq-job-card">
						<div class="rmq-job-cardin">
							<div class="rmq-job-cardttl">
								<h3>${roadmapJobList[i].roadmapJobName}</h3>
							</div>
							<div class="rmq-job-carddesc">
								<h5>${roadmapJobList[i].roadmapJobDesc}</h5>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</main>

		<main class="rmq-map">
			<c:choose>
				<c:when test="${not empty roadmap.roadmapImagePath}">
					<img src="${pageContext.request.contextPath}/assets/img/${roadmap.roadmapImagePath}"
						alt="로드맵 이미지"
						onerror="this.style.display='none'; document.getElementById('roadmapPlaceholder').style.display='flex';">
				</c:when>
				<c:otherwise>
					<div class="rmq-map-placeholder" id="roadmapPlaceholder">로드맵 이미지 영역</div>
				</c:otherwise>
			</c:choose>

			<c:if test="${not empty roadmap.roadmapImagePath}">
				<div class="rmq-map-placeholder" id="roadmapPlaceholder" style="display:none;">로드맵 이미지 영역</div>
			</c:if>
		</main>

		<main class="rmq-more">
			<button class="btn" id="showmore" type="button">더보기 +</button>
		</main>

		<main>
			<div class="rmq-steplist">

				<div class="rmq-pnl">
					<div class="rmq-pnl-frm">
						<div class="rmq-pnl-box">
							<div class="rmq-pnl-head">
								<h1>1. 기초 다지기</h1>
							</div>

							<c:forEach var="basic" items="${basicList}">
								<div class="rmq-pnl-row">
									<div class="rmq-pnl-coll">
										<h3>${basic.skillName}</h3>
									</div>
									<div class="rmq-pnl-colr">
										<h3>${basic.skillDesc}</h3>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>

				<div class="rmq-pnl">
					<div class="rmq-pnl-frm">
						<div class="rmq-pnl-box">
							<div class="rmq-pnl-head">
								<h1>2. 보안 기초 다지기</h1>
							</div>

							<c:forEach var="securityBasic" items="${securityBasicList}">
								<div class="rmq-pnl-row">
									<div class="rmq-pnl-coll">
										<h3>${securityBasic.skillName}</h3>
									</div>
									<div class="rmq-pnl-colr">
										<h3>${securityBasic.skillDesc}</h3>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>

				<div class="rmq-pnl">
					<div class="rmq-pnl-frm">
						<div class="rmq-pnl-box">
							<div class="rmq-pnl-head">
								<h1>3. 실습</h1>
							</div>

							<c:forEach var="practice" items="${practiceList}">
								<div class="rmq-pnl-row">
									<div class="rmq-pnl-coll">
										<h3>${practice.skillName}</h3>
									</div>
									<div class="rmq-pnl-colr">
										<h3>${practice.skillDesc}</h3>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>

				<div class="rmq-pnl">
					<div class="rmq-pnl-frm">
						<div class="rmq-pnl-box">
							<div class="rmq-pnl-head">
								<h1>4. 결과물</h1>
							</div>

							<c:forEach var="resultMaterial" items="${resultMaterialList}">
								<div class="rmq-pnl-row">
									<div class="rmq-pnl-coll">
										<h3>${resultMaterial.skillName}</h3>
									</div>
									<div class="rmq-pnl-colr">
										<h3>${resultMaterial.skillDesc}</h3>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>

			</div>
		</main>

		<main>
			<div class="rmq-pnl">
				<div class="rmq-pnl-frm">
					<div class="rmq-pnl-box">
						<div class="rmq-pnl-head">
							<h1>추천 기술</h1>
						</div>

						<c:forEach var="tech" items="${techList}">
							<div class="rmq-pnl-rowitwide">
								<div class="rmq-pnl-it">
									<h3>${tech.recName}</h3>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</main>

		<main class="rmq-grid">
			<div class="rmq-col">
				<div class="rmq-pnl">
					<div class="rmq-pnl-frm">
						<div class="rmq-pnl-box">
							<div class="rmq-pnl-head">
								<h1>추천 성향</h1>
							</div>

							<c:forEach var="tendency" items="${tendencyList}">
								<div class="rmq-pnl-row">
									<div class="rmq-pnl-it">
										<h3>${tendency.recName}</h3>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>

			<div class="rmq-col">
				<div class="rmq-pnl">
					<div class="rmq-pnl-frm">
						<div class="rmq-pnl-box">
							<div class="rmq-pnl-head">
								<h1>추천 자격증</h1>
							</div>

							<c:forEach var="certificate" items="${certificateList}">
								<div class="rmq-pnl-row">
									<div class="rmq-pnl-it">
										<h3>${certificate.recName}</h3>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</main>

		<main>
			<button class="btn" id="cnldjq" type="button"
				onclick="location.href='${pageContext.request.contextPath}/app/main/company/company-ad.jsp'">
				<h1>채용공고 바로가기</h1>
			</button>
		</main>
	</div>

	<script src="${pageContext.request.contextPath}/assets/js/main/roadmap/roadmap-questionnaire.js"></script>
</body>
</html>