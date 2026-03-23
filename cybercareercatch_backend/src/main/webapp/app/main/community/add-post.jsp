<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>자유게시판 글쓰기</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main/community/add-post.css">
</head>

<body>
  <main class="cmw-wrap">
    <div class="cmw-top">
      <h2 class="cmw-top-ttl">자유게시판</h2>

      <a href="${pageContext.request.contextPath}/post/list.pfc" class="cmw-top-mv">
        자유게시판 목록으로 이동하기
      </a>
    </div>

    <div class="cmw-pnl">
      <form action="${pageContext.request.contextPath}/post/writeOk.pfc" method="post" class="cmw-frm" id="postWriteForm">

        <input type="hidden" name="postType" value="FREE_POST">

        <div class="cmw-row">
          <label class="cmw-lbl" for="postTitle">제목</label>
          <input
            type="text"
            id="postTitle"
            name="postTitle"
            class="cmw-inp"
            placeholder="제목을 입력해주세요"
            required
          >
        </div>

        <div class="cmw-row">
          <label class="cmw-lbl" for="postContent">내용</label>
          <textarea
            id="postContent"
            name="postContent"
            class="cmw-ta"
            placeholder="자유롭게 내용을 입력하세요"
            required
          ></textarea>
        </div>

        <div class="cmw-btm">
          <button type="submit" class="cmw-btn">등록 완료</button>
        </div>
      </form>
    </div>
  </main>

  <script src="${pageContext.request.contextPath}/assets/js/main/community/add-post.js"></script>
</body>
</html>