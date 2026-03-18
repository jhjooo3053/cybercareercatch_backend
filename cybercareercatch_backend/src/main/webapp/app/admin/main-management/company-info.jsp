<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>기업 정보페이지 관리</title>
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/assets/css/admin/main-management/company-info.css">
</head>

<body class="companyInfo-body">

	<div class="companyInfo-container">

		<header class="companyInfo-top">

			<div class="companyInfo-title">
				<a href="${pageContext.request.contextPath}/app/admin/admin-main.jsp">관리자 페이지</a>
			</div>

			<nav class="companyInfo-menu">
				<a href="${pageContext.request.contextPath}/app/admin/member-management/member-info.jsp">회원 관리</a>
				<a href="${pageContext.request.contextPath}/app/admin/main-management/qna-management.jsp">메인 관리</a>
				<a href="${pageContext.request.contextPath}/app/admin/community-management/expo-schedule.jsp">커뮤니티 관리</a>
			</nav>

			<button class="companyInfo-logout" type="button">로그아웃</button>

		</header>

		<main class="companyInfo-main">

			<aside class="companyInfo-leftbar">

				<div class="companyInfo-left-item">
					<a href="${pageContext.request.contextPath}/app/admin/main-management/qna-management.jsp">질의문 관리</a>
				</div>

				<div class="companyInfo-left-item companyInfo-active">
					<a href="${pageContext.request.contextPath}/app/admin/main-management/company-info.jsp">기업 정보페이지</a>
				</div>

				<div class="companyInfo-left-item">
					<a href="${pageContext.request.contextPath}/app/admin/main-management/roadmap-management.jsp">로드맵 관리</a>
				</div>

			</aside>

			<section class="companyInfo-content">

				<div class="companyInfo-topbox">
					<h2 class="companyInfo-subtitle">기업 정보 목록</h2>
				</div>

				<div class="companyInfo-table">

					<div class="companyInfo-row companyInfo-head">
						<div class="companyInfo-col1">번호</div>
						<div class="companyInfo-col2">기업명</div>
						<div class="companyInfo-col3">주소</div>
						<div class="companyInfo-col4">상태</div>
						<div class="companyInfo-col5">삭제</div>
					</div>

					<div class="companyInfo-row">
						<div class="companyInfo-col1">20</div>
						<div class="companyInfo-col2">
							<a href="${pageContext.request.contextPath}/app/admin/main-management/company-detail.jsp">에버가드</a>
						</div>
						<div class="companyInfo-col3">경상북도 포항시 남구 포스코대로 120</div>
						<div class="companyInfo-col4">승인</div>
						<div class="companyInfo-col5"><input type="checkbox"></div>
					</div>

					<div class="companyInfo-row">
						<div class="companyInfo-col1">19</div>
						<div class="companyInfo-col2">
							<a href="${pageContext.request.contextPath}/app/admin/main-management/company-detail.jsp">락포인트</a>
						</div>
						<div class="companyInfo-col3">충청남도 천안시 서북구 불당로 919</div>
						<div class="companyInfo-col4">반려</div>
						<div class="companyInfo-col5"><input type="checkbox"></div>
					</div>

					<div class="companyInfo-row">
						<div class="companyInfo-col1">18</div>
						<div class="companyInfo-col2">
							<a href="${pageContext.request.contextPath}/app/admin/main-management/company-detail.jsp">시큐브릿지</a>
						</div>
						<div class="companyInfo-col3">경기도 고양시 일산동구 중앙로 818</div>
						<div class="companyInfo-col4">승인</div>
						<div class="companyInfo-col5"><input type="checkbox"></div>
					</div>

					<div class="companyInfo-row">
						<div class="companyInfo-col1">17</div>
						<div class="companyInfo-col2">
							<a href="${pageContext.request.contextPath}/app/admin/main-management/company-detail.jsp">쉴드웍스</a>
						</div>
						<div class="companyInfo-col3">서울특별시 구로구 디지털로 717</div>
						<div class="companyInfo-col4">대기</div>
						<div class="companyInfo-col5"><input type="checkbox"></div>
					</div>

					<div class="companyInfo-row">
						<div class="companyInfo-col1">16</div>
						<div class="companyInfo-col2">
							<a href="${pageContext.request.contextPath}/app/admin/main-management/company-detail.jsp">인포락</a>
						</div>
						<div class="companyInfo-col3">제주특별자치도 제주시 연북로 616</div>
						<div class="companyInfo-col4">승인</div>
						<div class="companyInfo-col5"><input type="checkbox"></div>
					</div>

					<div class="companyInfo-row">
						<div class="companyInfo-col1">15</div>
						<div class="companyInfo-col2">
							<a href="${pageContext.request.contextPath}/app/admin/main-management/company-detail.jsp">네오쉴드</a>
						</div>
						<div class="companyInfo-col3">경상남도 창원시 성산구 중앙대로 515</div>
						<div class="companyInfo-col4">대기</div>
						<div class="companyInfo-col5"><input type="checkbox"></div>
					</div>

					<div class="companyInfo-row">
						<div class="companyInfo-col1">14</div>
						<div class="companyInfo-col2">
							<a href="${pageContext.request.contextPath}/app/admin/main-management/company-detail.jsp">세이프큐브</a>
						</div>
						<div class="companyInfo-col3">전라북도 전주시 덕진구 백제대로 414</div>
						<div class="companyInfo-col4">승인</div>
						<div class="companyInfo-col5"><input type="checkbox"></div>
					</div>

					<div class="companyInfo-row">
						<div class="companyInfo-col1">13</div>
						<div class="companyInfo-col2">
							<a href="${pageContext.request.contextPath}/app/admin/main-management/company-detail.jsp">가디언테크</a>
						</div>
						<div class="companyInfo-col3">충청북도 청주시 상당구 상당로 313</div>
						<div class="companyInfo-col4">반려</div>
						<div class="companyInfo-col5"><input type="checkbox"></div>
					</div>

					<div class="companyInfo-row">
						<div class="companyInfo-col1">12</div>
						<div class="companyInfo-col2">
							<a href="${pageContext.request.contextPath}/app/admin/main-management/company-detail.jsp">트러스트원</a>
						</div>
						<div class="companyInfo-col3">경기도 수원시 영통구 광교로 212</div>
						<div class="companyInfo-col4">승인</div>
						<div class="companyInfo-col5"><input type="checkbox"></div>
					</div>

					<div class="companyInfo-row">
						<div class="companyInfo-col1">11</div>
						<div class="companyInfo-col2">
							<a href="${pageContext.request.contextPath}/app/admin/main-management/company-detail.jsp">스마트시큐어</a>
						</div>
						<div class="companyInfo-col3">서울특별시 마포구 월드컵북로 111</div>
						<div class="companyInfo-col4">대기</div>
						<div class="companyInfo-col5"><input type="checkbox"></div>
					</div>

				</div>

				<div class="companyInfo-page">
					<span>&lt;</span>
					<span class="active">1</span>
					<span>2</span>
					<span>3</span>
					<span>4</span>
					<span>5</span>
					<span>&gt;</span>
				</div>

				<div class="companyInfo-delete">
					<button type="button">삭제</button>
				</div>

			</section>

		</main>

	</div>

</body>

</html>