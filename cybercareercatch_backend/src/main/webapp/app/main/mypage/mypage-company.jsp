<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>기업회원마이페이지</title>
<link rel="stylesheet"
	href="${contextPath}/assets/css/main/mypage/mypage-company.css">
</head>

<body>
	<main>
		<div class="main-container">
			<div class="mypage-title">마이페이지</div>

			<div class="mypage-section">
				<div class="mypage-subtitle">기업정보 조회</div>

				<div class="main-container-section-changeinfo">
					<div class="mypage-info-box">
						<div class="mypage-info-title">아이디</div>
						<div class="mypage-info-text" id="company-id">${companyMypageInfoDTO.userId}</div>
					</div>

					<div class="mypage-info-box">
						<div class="mypage-info-title">기업명</div>
						<div class="mypage-info-text" id="company-name">${companyMypageInfoDTO.companyName}</div>
					</div>

					<div class="mypage-info-box">
						<div class="mypage-info-title">사업자등록번호</div>
						<div class="mypage-info-text" id="company-number">${companyMypageInfoDTO.companyBrn}</div>
					</div>

					<div class="mypage-info-box">
						<div class="mypage-info-title">기업 주소</div>
						<div class="mypage-info-text" id="company-address">${companyMypageInfoDTO.companyAddress}</div>
					</div>

					<div class="mypage-info-box">
						<div class="mypage-info-title">채용담당자 이름</div>
						<div class="mypage-info-text" id="company-manager-name">${companyMypageInfoDTO.userName}</div>
					</div>

					<div class="mypage-info-box">
						<div class="mypage-info-title">채용담당자 연락처</div>
						<div class="mypage-info-text" id="company-manager-phonenumber">${companyMypageInfoDTO.userPhone}</div>

						<a href="${contextPath}/mypage/company/check-password.my"
							class="btn" id="company-phonenumber-submit-btn">회원정보수정</a>
					</div>

					<div class="mypage-button-box">
						<c:choose>
							<c:when test="${empty companyPageCount or companyPageCount == 0}">
								<a href="${pageContext.request.contextPath}/company/mypage/companypageRegister.mpfc"
									class="btn"> 기업정보페이지 등록 </a>
							</c:when>
							<c:otherwise>
								<a
									href="${pageContext.request.contextPath}/company/mypage/companypageEdit.mpfc"
									class="btn"> 기업정보페이지 수정/삭제 </a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>

				<div class="mypage-section-QnAlist">
					<div class="mypage-subtitle">Q&amp;A 리스트</div>

					<ul class="mypage-QnAlist">
						<li class="mypage-QnAlist-header">
							<div class="no">번호</div>
							<div class="QnAlist-title">제목</div>
							<div class="companyname">기업명</div>
							<div class="writer">작성자</div>
							<div class="date">날짜</div>
							<div class="answer-status">답변상태</div>
						</li>

						<c:choose>
							<c:when test="${empty waitingQnaList}">
								<li class="mypage-QnAlist-content">
									<div style="width: 100%; text-align: center; padding: 20px 0;">
										답변대기 Q&amp;A가 없습니다.</div>
								</li>
							</c:when>

							<c:otherwise>
								<c:forEach var="qna" items="${waitingQnaList}">
									<li class="mypage-QnAlist-content">
										<div class="no">${qna.postNumber}</div> <a
										href="${contextPath}/qna/detail.qna?postNumber=${qna.postNumber}"
										class="QnAlist-title"> ${qna.postTitle} </a>

										<div class="companyname">${qna.companyName}</div>
										<div class="writer">${qna.userId}</div>
										<div class="date">${qna.postDate}</div>

										<div
											class="answer-status ${qna.answerStatus eq '답변완료' ? 'answer-status-active' : ''}">
											${qna.answerStatus}</div>
									</li>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</ul>

					<c:if test="${not empty waitingQnaList}">
						<div class="mypage-QnAlist-bottom">
							<div class="mypage-pagination">

								<c:if test="${prev}">
									<a class="page-btn page-arrow"
									   href="${pageContext.request.contextPath}/company/mypage.mpfc?page=${startPage - 1}">
										&lt;
									</a>
								</c:if>

								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<a class="page-btn ${page == i ? 'page-btn-active' : ''}"
									   href="${pageContext.request.contextPath}/company/mypage.mpfc?page=${i}">
										${i}
									</a>
								</c:forEach>

								<c:if test="${next}">
									<a class="page-btn page-arrow"
									   href="${pageContext.request.contextPath}/company/mypage.mpfc?page=${endPage + 1}">
										&gt;
									</a>
								</c:if>

							</div>
						</div>
					</c:if>
					
				</div>
			</div>
		</div>
	</main>
</body>
</html>