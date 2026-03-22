<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>기업 QnA 상세</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main/qna/qna-detail.css">
</head>
<body>
<header></header>

<main class="qna-detail-main">
<div class="qna-detail-top">
    <h2 class="qna-detail-section-title">기업 QnA</h2>

    <div class="qna-detail-top-actions">
        <a href="${pageContext.request.contextPath}/qna/list.qfc" class="qna-btn-primary">기업 QnA 목록 이동하기</a>

        <form action="${pageContext.request.contextPath}/qna/delete.qfc"
              method="post"
              onsubmit="return confirm('게시글을 삭제하시겠습니까? 댓글도 함께 삭제됩니다.');">
            <input type="hidden" name="postNumber" value="${qnaDetail.postNumber}">
            <button type="submit" class="qna-btn-danger">게시글 삭제</button>
        </form>
    </div>
</div>

    <div class="qna-detail-post">
        <div class="qna-detail-post-hdr">
            <div class="qna-detail-post-title">${qnaDetail.postTitle}</div>
        </div>

        <div class="qna-detail-post-body">${qnaDetail.postContent}</div>

        <div class="qna-detail-post-writer-wrap">
            <div class="qna-detail-post-writer">
                작성자 : <span>${qnaDetail.memberId}</span>
            </div>
            <div class="qna-detail-post-date">
                작성일 : ${qnaDetail.postCreatedDate}
            </div>
        </div>
    </div>

    <!-- 댓글 목록 -->
<div class="qna-detail-cmts">
    <c:choose>
        <c:when test="${not empty commentList}">
            <c:forEach var="comment" items="${commentList}">
                <div class="qna-detail-cmt">
                    <div class="qna-detail-cmt-meta">
                        <span>작성자 : ${comment.memberId}</span>
                        <span>${comment.commentCreatedDate}</span>
                    </div>
                    <div class="qna-detail-cmt-body">${comment.commentContent}</div>

                    <form action="${pageContext.request.contextPath}/qna/comment-delete.qfc"
                          method="post"
                          class="qna-detail-cmt-del-form"
                          onsubmit="return confirm('댓글을 삭제하시겠습니까?');">
                        <input type="hidden" name="commentNumber" value="${comment.commentNumber}">
                        <input type="hidden" name="postNumber" value="${qnaDetail.postNumber}">
                        <button type="submit" class="qna-detail-cmt-del">삭제</button>
                    </form>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="qna-detail-cmt qna-detail-empty">
                아직 등록된 댓글이 없습니다.
            </div>
        </c:otherwise>
    </c:choose>
</div>

    <!-- 댓글 등록 -->
    <form action="${pageContext.request.contextPath}/qna/comment-write.qfc" method="post" class="qna-detail-reply">
        <input type="hidden" name="postNumber" value="${qnaDetail.postNumber}">
        <input class="qna-detail-reply-inp" type="text" name="commentContent" id="replyInput" placeholder="댓글을 입력하세요">
        <button class="qna-btn-primary" type="submit">입력</button>
    </form>
</main>

<script src="${pageContext.request.contextPath}/assets/js/main/qna/qna-detail.js"></script>
</body>
</html>