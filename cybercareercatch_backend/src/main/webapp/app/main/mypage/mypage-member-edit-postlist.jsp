<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>일반회원 마이페이지 - 게시물목록확인</title>
<link rel="stylesheet"
	href="${contextPath}/assets/css/main/mypage/mypage-member-edit-postlist.css">
</head>

<body>
	<main>
		<div class="main-container">
			<div class="mypage-title">마이페이지</div>

			<div class="mypage-section">
				<div class="mypage-subtitle">나의 Q&amp;A 게시글</div>
				<div class="mypage-subtitle">Q&amp;A 리스트</div>

				<form action="${contextPath}/mypage/member/delete-my-qna.my"
					method="post">
					<ul class="mypage-QnAlist">
						<li class="mypage-QnAlist-header">
							<div class="check">선택</div>
							<div class="no">번호</div>
							<div class="QnAlist-title">제목</div>
							<div class="companyname">기업명</div>
							<div class="writer">작성자</div>
							<div class="date">날짜</div>
							<div class="answer-status">답변상태</div>
						</li>

						<c:choose>
							<c:when test="${empty myQnaList}">
								<li class="mypage-QnAlist-content">
									<div style="width: 100%; text-align: center; padding: 20px 0;">
										작성한 Q&amp;A 게시글이 없습니다.</div>
								</li>
							</c:when>

							<c:otherwise>
								<c:forEach var="qna" items="${myQnaList}">
									<li class="mypage-QnAlist-content">
										<div class="check">
											<input type="checkbox" name="postNumbers"
												value="${qna.postNumber}">
										</div>

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

					<div class="QnAlist-button-wrap">
						<button type="submit" class="btn" id="mypage-delete-btn">삭제</button>
					</div>
				</form>
				
				
				<c:if test="${not empty myQnaList}">
					<div class="mypage-QnAlist-bottom">
						<div class="mypage-pagination">

							<c:if test="${prev}">
								<a class="page-btn page-arrow"
									href="${contextPath}/mypage/member/my-qna-list.my?page=${startPage - 1}">
									&lt; </a>
							</c:if>

							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<a class="page-btn ${page == i ? 'page-btn-active' : ''}"
									href="${contextPath}/mypage/member/my-qna-list.my?page=${i}">
									${i} </a>
							</c:forEach>

							<c:if test="${next}">
								<a class="page-btn page-arrow"
									href="${contextPath}/mypage/member/my-qna-list.my?page=${endPage + 1}">
									&gt; </a>
							</c:if>

						</div>
					</div>
				</c:if>
				
				
			</div>
		</div>
	</main>
</body>

</html>