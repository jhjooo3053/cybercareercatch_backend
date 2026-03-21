<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>자유게시판</title>
<%--   <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main/community/post-list.css"> --%>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main/community/post-list.css?v=3">
</head>

<body>
  <c:set var="listUrl" value="${pageContext.request.contextPath}/post/list.pfc" />
  <c:set var="readUrl" value="${pageContext.request.contextPath}/post/read.pfc" />
  <c:set var="writeUrl" value="${pageContext.request.contextPath}/post/write.pfc" />

  <main class="cml-wrap">

    <div class="cml-top">
      <h2 class="cml-top-ttl">자유게시판</h2>
    </div>

    <div class="cml-head">
      <div class="cml-head-col-num">번호</div>
      <div class="cml-head-col-ttl">제목</div>
      <div class="cml-head-col-wrt">작성자</div>
      <div class="cml-head-col-date">작성일</div>
      <div class="cml-head-col-view">조회수</div>
    </div>

    <div class="cml-list">

      <c:if test="${not empty noticeList}">
        <c:forEach var="notice" items="${noticeList}">
          <div class="cml-row">
            <div class="cml-row-col-num">공지</div>

            <div class="cml-row-col-ttl">
              <a href="${readUrl}?postNumber=${notice.postNumber}" class="cml-link">
                <c:out value="${notice.postTitle}" />
              </a>
            </div>

            <div class="cml-row-col-wrt">
              <c:out value="${notice.adminId}" />
            </div>

            <div class="cml-row-col-date">
              <c:out value="${notice.postDate}" />
            </div>

            <div class="cml-row-col-view">
              <c:out value="${notice.viewCount}" />
            </div>
          </div>
        </c:forEach>
      </c:if>

      <c:choose>
        <c:when test="${not empty postList}">
          <c:forEach var="post" items="${postList}">
            <div class="cml-row">
              <div class="cml-row-col-num">
                <c:out value="${post.postNumber}" />
              </div>

              <div class="cml-row-col-ttl">
                <a href="${readUrl}?postNumber=${post.postNumber}" class="cml-link">
                  <c:out value="${post.postTitle}" />
                </a>
              </div>

              <div class="cml-row-col-wrt">
                <c:out value="${post.userId}" />
              </div>

              <div class="cml-row-col-date">
                <c:out value="${post.postDate}" />
              </div>

              <div class="cml-row-col-view">
                <c:out value="${post.viewCount}" />
              </div>
            </div>
          </c:forEach>
        </c:when>

        <c:otherwise>
          <div class="cml-row">
            <div class="cml-row-col-empty">
              등록된 게시글이 없습니다.
            </div>
          </div>
        </c:otherwise>
      </c:choose>
    </div>

    <div class="cml-pg">
      <c:if test="${prev}">
        <a href="${listUrl}?page=${startPage - 1}" class="cml-pg-btn">&lt;</a>
      </c:if>

      <c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
        <a
          href="${listUrl}?page=${pageNum}"
          class="cml-pg-btn ${page == pageNum ? 'cml-pg-btn-now' : ''}">
          <c:out value="${pageNum}" />
        </a>
      </c:forEach>

      <c:if test="${next}">
        <a href="${listUrl}?page=${endPage + 1}" class="cml-pg-btn">&gt;</a>
      </c:if>
    </div>

    <form action="${listUrl}" method="get" class="cml-sch">
      <select name="searchType" class="cml-sch-sel">
        <option value="title" ${param.searchType == 'title' ? 'selected' : ''}>제목</option>
        <option value="writer" ${param.searchType == 'writer' ? 'selected' : ''}>작성자</option>
        <option value="content" ${param.searchType == 'content' ? 'selected' : ''}>내용</option>
      </select>

      <input
        type="text"
        name="keyword"
        class="cml-sch-inp"
        placeholder="검색할 내용을 입력하세요"
        value="${param.keyword}"
      >

      <button type="submit" class="cml-sch-btn">검색</button>

      <a href="${writeUrl}" class="cml-write-btn">글쓰기</a>
    </form>
  </main>

  <script src="${pageContext.request.contextPath}/assets/js/main/community/post-list.js"></script>
</body>
</html>