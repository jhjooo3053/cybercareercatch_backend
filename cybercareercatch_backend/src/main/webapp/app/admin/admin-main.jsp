<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>관리자 메인 페이지</title>
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/assets/css/admin/admin-main.css">
</head>

<body>

	<div class="container">

		<header class="top-bar">
			<div class="logo">
				<a href="${pageContext.request.contextPath}/app/admin/admin-main.jsp">관리자 페이지</a>
			</div>

			<nav class="main-nav">
				<a href="${pageContext.request.contextPath}/app/admin/member-management/member-info.jsp">회원 관리</a>
				<a href="${pageContext.request.contextPath}/app/admin/main-management/qna-management.jsp">메인 관리</a>
				<a href="${pageContext.request.contextPath}/app/admin/community-management/expo-schedule.jsp">커뮤니티 관리</a>
			</nav>

			<button class="logout-btn" type="button">로그아웃</button>
		</header>

		<main class="main-content">

			<aside class="sidebar">
				<div class="sidebar-item sidebar-active">
					<a href="${pageContext.request.contextPath}/app/admin/admin-main.jsp">대시보드</a>
				</div>
				<div class="sidebar-item">
					<a href="${pageContext.request.contextPath}/app/admin/main-management/qna-management.jsp">질의문 관리</a>
				</div>
				<div class="sidebar-item">
					<a href="${pageContext.request.contextPath}/app/admin/main-management/company-info.jsp">기업 정보페이지</a>
				</div>
				<div class="sidebar-item">
					<a href="${pageContext.request.contextPath}/app/admin/main-management/roadmap-management.jsp">로드맵 관리</a>
				</div>
			</aside>

			<section class="dashboard">

				<div class="summary-cards">
					<div class="card">
						<div class="card-title">총 일반 회원 수</div>
						<div class="card-value">20</div>
					</div>

					<div class="card">
						<div class="card-title">총 기업 회원 수</div>
						<div class="card-value">20</div>
					</div>

					<div class="card">
						<div class="card-title">총 박람회 수</div>
						<div class="card-value">10</div>
					</div>
				</div>

				<div class="dashboard-section">
					<h3>최근 일반 회원</h3>

					<div class="table-box">
						<div class="table-row table-head">
							<div>회원번호</div>
							<div>아이디</div>
							<div>이름</div>
							<div>가입일</div>
						</div>

						<div class="table-row">
							<div>20</div>
							<div>user20</div>
							<div>노태윤</div>
							<div>2026-02-26</div>
						</div>

						<div class="table-row">
							<div>19</div>
							<div>user19</div>
							<div>강수아</div>
							<div>2026-02-25</div>
						</div>

						<div class="table-row">
							<div>18</div>
							<div>user18</div>
							<div>조민성</div>
							<div>2026-02-24</div>
						</div>
					</div>
				</div>

				<div class="dashboard-section">
					<h3>최근 기업 회원</h3>

					<div class="table-box">
						<div class="table-row table-head">
							<div>회원번호</div>
							<div>아이디</div>
							<div>기업명</div>
							<div>가입일</div>
							<div>상태</div>
						</div>

						<div class="table-row">
							<div>40</div>
							<div>comp20</div>
							<div>에버가드</div>
							<div>2026-03-17</div>
							<div>승인</div>
						</div>

						<div class="table-row">
							<div>39</div>
							<div>comp19</div>
							<div>락포인트</div>
							<div>2026-03-16</div>
							<div>반려</div>
						</div>

						<div class="table-row">
							<div>38</div>
							<div>comp18</div>
							<div>시큐브릿지</div>
							<div>2026-03-15</div>
							<div>승인</div>
						</div>
					</div>
				</div>

				<div class="dashboard-section">
					<h3>최근 박람회</h3>

					<div class="table-box">
						<div class="table-row table-head">
							<div>박람회번호</div>
							<div>박람회명</div>
							<div>시작일</div>
							<div>종료일</div>
							<div>장소</div>
						</div>

						<div class="table-row">
							<div>10</div>
							<div>2026 청년 IT 보안 일자리 박람회</div>
							<div>2026-07-01</div>
							<div>2026-07-03</div>
							<div>창원 컨벤션센터</div>
						</div>

						<div class="table-row">
							<div>9</div>
							<div>2026 AI 보안 기술 채용 박람회</div>
							<div>2026-06-20</div>
							<div>2026-06-21</div>
							<div>서울 SETEC</div>
						</div>

						<div class="table-row">
							<div>8</div>
							<div>2026 개인정보보호 산업 박람회</div>
							<div>2026-06-12</div>
							<div>2026-06-14</div>
							<div>수원 컨벤션센터</div>
						</div>
					</div>
				</div>

			</section>

		</main>

	</div>

</body>

</html>