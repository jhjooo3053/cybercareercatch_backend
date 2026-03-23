<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>자유게시판 상세</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main/community/post-detail.css">
</head>
<body data-context-path="${pageContext.request.contextPath}">

	<main class="cmd-wrap">
		<div class="cmd-top">
			<h2 class="cmd-top-ttl">자유게시판</h2>
		</div>

		<div class="cmd-pnl">
			<div class="cmd-post">
				<div class="cmd-head">
					<div class="cmd-head-top">
						<h3 class="cmd-post-ttl">
							<c:out value="${postDetail.postTitle}" />
						</h3>

						<div class="cmd-act">
							<button type="button" class="cmd-act-btn cmd-act-cancel cmd-list-btn">
								자유게시판 바로가기
							</button>

							<c:if test="${isWriter}">
								<button
									type="button"
									class="cmd-act-btn cmd-act-del cmd-delete-btn"
									data-post-number="${postDetail.postNumber}">
									삭제
								</button>
							</c:if>
						</div>
					</div>

					<div class="cmd-info">
						<div>작성자 <c:out value="${postDetail.userId}" /></div>
						<div>작성일 <c:out value="${postDetail.postDate}" /></div>
						<div>조회수 <c:out value="${postDetail.viewCount}" /></div>
					</div>
				</div>

				<div class="cmd-cont">
					<c:out value="${postDetail.postContent}" />
				</div>
			</div>

			<div class="cmd-cmt">
				<div class="cmd-cmt-list">
					<c:choose>
						<c:when test="${not empty commentList}">
							<c:forEach var="comment" items="${commentList}">
								<div class="cmd-cmt-row">
									<div class="cmd-cmt-left">
										<div class="cmd-cmt-meta">
											작성자 <c:out value="${comment.userId}" />
											|
											작성일 <c:out value="${comment.commentDate}" />
										</div>
										<div class="cmd-cmt-text">
											<c:out value="${comment.commentContent}" />
										</div>
									</div>

									<c:if test="${loginUserNumber == comment.userNumber}">
										<form action="${pageContext.request.contextPath}/post/commentDeleteOk.pfc" method="post">
											<input type="hidden" name="commentNumber" value="${comment.commentNumber}">
											<input type="hidden" name="postNumber" value="${postDetail.postNumber}">
											<button type="submit" class="cmd-cmt-del">X</button>
										</form>
									</c:if>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="cmd-cmt-row">
								<div class="cmd-cmt-left">
									<div class="cmd-cmt-text">등록된 댓글이 없습니다.</div>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>

				<form action="${pageContext.request.contextPath}/post/commentWriteOk.pfc" method="post" class="cmd-cmt-write">
					<input type="hidden" name="postNumber" value="${postDetail.postNumber}">
					<input
						type="text"
						name="commentContent"
						class="cmd-cmt-inp"
						placeholder="댓글을 입력하세요">
					<button type="submit" class="cmd-cmt-btn">입력</button>
				</form>
			</div>
		</div>
	</main>

	<script src="${pageContext.request.contextPath}/assets/js/main/community/post-detail.js"></script>
</body>
</html>