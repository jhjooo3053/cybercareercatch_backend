<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
  <!--
    이 페이지도 비회원 접근 불가

    QnaDetailDTO 기준 필드
    - postNumber
    - postTitle
    - postContent
    - companyNumber
    - companyName
    - userNumber
    - userId
    - userName
    - postDate
    - viewCount
    - answerStatus

    Controller 예시
    request.setAttribute("qna", qnaDetailDTO);
  -->
  <c:choose>
    <c:when test="${not empty sessionScope.userNumber}">

      <main class="qna-detail-main">
        <div class="qna-detail-top">
          <h2 class="qna-detail-section-title">기업 QnA</h2>
          <a href="${pageContext.request.contextPath}/app/main/qna/qna-list.jsp" class="qna-btn-primary">
		    기업 QnA 목록 이동하기
	    </a>
        </div>

        <c:choose>
          <c:when test="${not empty qna}">

            <div class="qna-detail-post">
              <div class="qna-detail-post-hdr">

                <%-- 게시글 제목 출력 --%>
                <div class="qna-detail-post-title" id="detailTitle">
                  <c:out value="${qna.postTitle}" />
                </div>

                <%-- 게시글 메타 정보 --%>
                <div class="qna-detail-post-meta">
                  <span id="detailCompany">
                    <c:out value="${qna.companyName}" />
                  </span>
                  <span>|</span>

                  <span>
                    글쓴이 :
                    <span id="detailAuthor">
                      <c:out value="${qna.userId}" />
                    </span>
                  </span>
                  <span>|</span>

                  <span>
                    조회수 :
                    <span id="detailViews">
                      <c:out value="${qna.viewCount}" />
                    </span>
                  </span>
                  <span>|</span>

                  <span>
                    답변상태 :
                    <span id="detailAnswerStatus">
                      <c:out value="${qna.answerStatus}" />
                    </span>
                  </span>
                </div>

                <%-- 작성일 출력 --%>
                <div class="qna-detail-post-date" id="detailDate">
                  <c:out value="${qna.postDate}" />
                </div>
              </div>

              <%-- 본문 내용 출력 --%>
              <div class="qna-detail-post-body" id="detailBody">
                <c:out value="${qna.postContent}" />
              </div>
            </div>

            <%--
              현재 범위에서는 수정 기능 없음
              삭제 버튼만 작성자 본인에게 노출
            --%>
            <div class="qna-write-actions">
              <c:if test="${sessionScope.userNumber == qna.userNumber}">
                <button
                  type="button"
                  class="qna-btn-primary"
                  id="btnDelete"
                  data-post-number="${qna.postNumber}">
                  삭제
                </button>
              </c:if>
            </div>

          </c:when>

          <c:otherwise>
            <div class="qna-detail-post">
              <div class="qna-detail-post-title">존재하지 않는 QnA 게시글입니다.</div>
            </div>
          </c:otherwise>
        </c:choose>
      </main>
    </c:when>

    <c:otherwise>
      <script>
        alert("기업 QnA 게시판은 로그인 후 이용할 수 있습니다.");
        location.href = "${pageContext.request.contextPath}/app/main/account/login.jsp";
      </script>
    </c:otherwise>
  </c:choose>

  <script>
    /*
      JS에서 사용할 수 있게 기본 값 선언
      현재는 JSP 직접 이동 구조지만
      나중에 Controller 경로가 생기면 JS에서 location.href만 바꾸면 됩니다.
    */
    const contextPath = "${pageContext.request.contextPath}";
  </script>
  <script src="${pageContext.request.contextPath}/assets/js/main/qna/qna-detail.js"></script>
</body>
</html>