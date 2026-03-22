<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>기업 QnA 작성</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main/qna/add-qna.css">
</head>

<body>
  <!--
    이 페이지도 비회원 접근 불가

    중요:
    이 페이지는 일반회원이 기업에 질문하는 일반 QnA 글 작성 페이지입니다.
    즉 관리자 공지 작성 페이지가 아닙니다.

    관리자 공지(QNA_NOTICE)는 관리자 페이지에서 작성해야 합니다.

    QnaWriteDTO 기준
    - userNumber
    - companyNumber
    - postTitle
    - postContent
  -->
  <c:choose>
    <c:when test="${not empty sessionScope.userNumber}">

      <main class="qna-write-main">
        <div class="qna-write-top">
          <h2 class="qna-write-title">기업QnA</h2>
          <a href="${pageContext.request.contextPath}/app/main/qna/qna-list.jsp" class="qna-btn-primary">
            기업 QnA 목록 이동하기
          </a>
        </div>

        <div class="qna-write-form">

          <%--
            현재는 action="#"
            나중에 Controller가 생기면 QnA 등록 처리 경로로 변경

            예시
            action="${pageContext.request.contextPath}/qna/write.qn"
          --%>
          <form action="#" method="post" id="qnaWriteForm">

            <%-- 로그인한 일반회원 번호 hidden 값 --%>
            <input type="hidden" name="userNumber" value="${sessionScope.userNumber}">

            <div class="qna-write-filter">
              <select class="qna-write-filter-sel" id="writeCompanyFilter" name="companyNumber">
                <option value="">기업선택</option>

                <%--
                  companyList는 CompanyDTO 목록이라고 가정
                  - companyNumber
                  - companyName
                --%>
                <c:if test="${not empty companyList}">
                  <c:forEach var="company" items="${companyList}">
                    <option value="${company.companyNumber}">
                      <c:out value="${company.companyName}" />
                    </option>
                  </c:forEach>
                </c:if>
              </select>
            </div>

            <%-- 제목 입력 --%>
            <input
              class="qna-write-inp-title"
              type="text"
              placeholder="제목을 입력해주세요"
              id="writeTitle"
              name="postTitle"/>

            <%-- 내용 입력 --%>
            <textarea
              class="qna-write-textarea"
              placeholder="글 내용을 입력해주세요."
              id="writeBody"
              name="postContent"></textarea>

            <div class="qna-write-actions">
              <button class="qna-btn-primary" id="btnSubmit" type="submit">작성완료</button>
            </div>
          </form>
        </div>
      </main>
    </c:when>

    <c:otherwise>
      <script>
        alert("기업 QnA 게시판은 로그인 후 이용할 수 있습니다.");
        location.href = "${pageContext.request.contextPath}/app/main/account/login.jsp";
      </script>
    </c:otherwise>
  </c:choose>

  <script src="${pageContext.request.contextPath}/assets/js/main/qna/add-qna.js"></script>
</body>
</html>