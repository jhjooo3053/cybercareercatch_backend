<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="loginRequired" value="${empty sessionScope.userNumber}" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>기업 Q&A 상세</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/main/qna/qna-detail.css">
</head>
<body>

	<div id="qnaDetailPage" data-login-required="${loginRequired}"
		data-login-message="기업 Q&A는 로그인 후 이용 가능합니다."
		data-login-url="${pageContext.request.contextPath}/member/login.mefc"
		data-list-url="${pageContext.request.contextPath}/qna/list.qfc"></div>

	<c:if test="${not loginRequired}">
		<div class="qnd-wrap">

			<div class="qnd-top">
				<h2 class="qnd-top-ttl">기업 Q&A</h2>
				<a href="${pageContext.request.contextPath}/qna/list.qfc"
					class="qnd-top-mv">기업QnA페이지 바로가기</a>
			</div>

			<div class="qnd-pnl">
				<div class="qnd-head">
					<div class="qnd-head-ttl">
						<c:out value="${qnaDetail.postTitle}" />
					</div>

					<div class="qnd-head-info">
						<span>기업명 <c:out value="${qnaDetail.companyName}" /></span> <span>작성자
							<c:out value="${qnaDetail.userId}" />
						</span> <span>작성일 <c:out value="${qnaDetail.postDate}" /></span> <span>조회수
							<c:out value="${qnaDetail.viewCount}" />
						</span> <span> 상태 <c:choose>
								<c:when test="${qnaDetail.answerStatus eq '답변완료'}">
                  답변완료
                </c:when>
								<c:otherwise>
                  답변대기
                </c:otherwise>
							</c:choose>
						</span>
					</div>
				</div>

				<div class="qnd-cont">
					<c:out value="${qnaDetail.postContent}" />
				</div>

				<div class="qnd-btm">
					<c:if test="${showDeleteButton}">
						<button type="button" class="qnd-btn qnd-btn-del js-qna-delete"
							data-delete-url="${pageContext.request.contextPath}/qna/delete.qfc?postNumber=${qnaDetail.postNumber}">
							삭제</button>
					</c:if>
				</div>
			</div>

			<div class="qnd-cmt">
				<div class="qnd-cmt-top">
					<h3 class="qnd-cmt-ttl">답변</h3>
				</div>

				<c:choose>
					<c:when test="${not empty qnaComment}">
						<div class="qnd-cmt-box">
							<div class="qnd-cmt-meta">
								<span>작성자 <c:out value="${qnaComment.memberId}" /></span> <span>작성일
									<c:out value="${qnaComment.commentCreatedDate}" />
								</span>
							</div>

							<div class="qnd-cmt-cont">
								<c:out value="${qnaComment.commentContent}" />
							</div>

							<c:if test="${showCommentDeleteButton}">
								<div class="qnd-cmt-btm">
									<button type="button" class="qnd-cmt-del js-comment-delete"
										data-delete-url="${pageContext.request.contextPath}/qna/comment-delete.qfc?commentNumber=${qnaComment.commentNumber}&postNumber=${qnaDetail.postNumber}">
										댓글 삭제</button>
								</div>
							</c:if>
						</div>
					</c:when>

					<c:otherwise>
						<div class="qnd-cmt-empty">아직 등록된 답변이 없습니다.</div>
					</c:otherwise>
				</c:choose>

				<c:if test="${showCommentWriteForm}">
					<form
						action="${pageContext.request.contextPath}/qna/comment-write.qfc"
						method="post" class="qnd-cmt-write" id="qnaCommentForm">

						<input type="hidden" name="postNumber"
							value="${qnaDetail.postNumber}">

						<textarea name="commentContent" id="commentContent"
							class="qnd-cmt-ta" maxlength="1000" placeholder="답변 내용을 입력하세요"></textarea>

						<div class="qnd-cmt-write-btm">
							<button type="submit" class="qnd-cmt-btn">답변 등록</button>
						</div>
					</form>
				</c:if>
			</div>

		</div>
	</c:if>

	<script
		src="${pageContext.request.contextPath}/assets/js/main/qna/qna-detail.js"></script>
</body>
</html>