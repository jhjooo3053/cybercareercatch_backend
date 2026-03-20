<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>자유게시판</title>

  <%--
    contextPath를 붙이는 이유
    -> 프로젝트 경로가 바뀌어도 CSS 경로가 안 깨지게 하기 위해서입니다.
  --%>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main/community/post-list.css">
</head>

<body>
  <%--
    자유게시판 접근 기준
    1. 비회원 접근 가능
    2. 일반회원 접근 가능
    3. 기업회원 접근 불가

    그래서 userType이 없으면(비회원) 허용,
    userType이 일반회원이면 허용,
    userType이 기업회원이면 차단합니다.
  --%>
  <c:choose>
    <c:when test="${empty sessionScope.userType or sessionScope.userType == '일반회원'}">

      <!--
        Controller가 나중에 넘겨주면 좋은 값 이름

        1. noticeList : 자유게시판 공지 목록 (List<NoticeDTO>)
           -> 관리자 페이지에서 작성된 공지 목록

        2. postList : 자유게시판 일반글 목록 (List<PostListDTO>)

        3. pageInfo : 페이지 정보 (PostPageDTO)
      -->

      <main class="cml-wrap">
        <div class="cml-top">
          <h2 class="cml-top-ttl">자유게시판</h2>
        </div>

        <div class="cml-head">
          <div class="cml-head-col-num">번호</div>
          <div class="cml-head-col-ttl">제목</div>
          <div class="cml-head-col-wrt">작성자</div>
          <div class="cml-head-col-date">작성일</div>
        </div>

        <div class="cml-list">

          <%-- =========================
               1. 자유게시판 공지 출력
               NoticeDTO 기준
               - postNumber
               - adminId
               - postTitle
               - postDate
             ========================= --%>
          <c:if test="${not empty noticeList}">
            <c:forEach var="notice" items="${noticeList}">
              <div class="cml-row">
                <div class="cml-row-col-num">공지</div>

                <div class="cml-row-col-ttl">
                  <%--
                    현재는 JSP 직접 이동
                    나중에 Controller가 생기면 공지 상세 컨트롤러 경로로 변경
                  --%>
                  <a
                    href="${pageContext.request.contextPath}/app/main/community/post-notification.jsp?postNumber=${notice.postNumber}"
                    class="cml-link">
                    <c:out value="${notice.postTitle}" />
                  </a>
                </div>

                <div class="cml-row-col-wrt">
                  <c:out value="${notice.adminId}" />
                </div>

                <div class="cml-row-col-date">
                  <c:out value="${notice.postDate}" />
                </div>
              </div>
            </c:forEach>
          </c:if>

          <%-- =========================
               2. 자유게시판 일반글 출력
               PostListDTO 기준
               - postNumber
               - postTitle
               - userId
               - postDate
             ========================= --%>
          <c:choose>
            <c:when test="${not empty postList}">
              <c:forEach var="post" items="${postList}">
                <div class="cml-row">
                  <div class="cml-row-col-num">
                    <c:out value="${post.postNumber}" />
                  </div>

                  <div class="cml-row-col-ttl">
                    <%--
                      현재는 JSP 직접 이동
                      나중에 Controller가 생기면 상세 컨트롤러 경로로 변경
                    --%>
                    <a
                      href="${pageContext.request.contextPath}/app/main/community/post-detail.jsp?postNumber=${post.postNumber}"
                      class="cml-link">
                      <c:out value="${post.postTitle}" />
                    </a>
                  </div>

                  <div class="cml-row-col-wrt">
                    <c:out value="${post.userId}" />
                  </div>

                  <div class="cml-row-col-date">
                    <c:out value="${post.postDate}" />
                  </div>
                </div>
              </c:forEach>
            </c:when>

            <c:otherwise>
              <div class="cml-row">
                <div class="cml-row-col-ttl" style="grid-column: 1 / 5; text-align: center;">
                  등록된 게시글이 없습니다.
                </div>
              </div>
            </c:otherwise>
          </c:choose>
        </div>

        <%-- 페이지네이션 --%>
        <div class="cml-pg">
          <c:if test="${not empty pageInfo}">

            <c:if test="${pageInfo.prev}">
              <a href="?page=${pageInfo.startPage - 1}" class="cml-pg-btn">&lt;</a>
            </c:if>

            <c:forEach var="pageNum" begin="${pageInfo.startPage}" end="${pageInfo.endPage}">
              <a
                href="?page=${pageNum}"
                class="cml-pg-btn ${pageInfo.page == pageNum ? 'cml-pg-btn-now' : ''}">
                <c:out value="${pageNum}" />
              </a>
            </c:forEach>

            <c:if test="${pageInfo.next}">
              <a href="?page=${pageInfo.endPage + 1}" class="cml-pg-btn">&gt;</a>
            </c:if>

          </c:if>
        </div>

        <%-- 검색 폼 --%>
        <form action="#" method="get" class="cml-sch">
          <%--
            현재는 action="#"
            나중에 Controller가 생기면 목록 컨트롤러 경로로 변경
          --%>
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

          <%--
            글쓰기는 일반회원만 가능
            비회원은 글쓰기 버튼을 보이지 않게 처리
          --%>
          <c:if test="${sessionScope.userType == '일반회원'}">
            <a href="${pageContext.request.contextPath}/app/main/community/add-post.jsp" class="cml-sch-write">글쓰기</a>
          </c:if>
        </form>
      </main>
    </c:when>

    <c:otherwise>
      <%-- 기업회원 차단 처리 --%>
      <script>
        alert("자유게시판은 일반회원만 이용할 수 있습니다.");
        location.href = "${pageContext.request.contextPath}/";
      </script>
    </c:otherwise>
  </c:choose>
</body>
</html>