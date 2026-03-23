<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="loginRequired" value="${empty requestScope.userNumber}" />
<c:set var="companyBlocked" value="${requestScope.userType eq '기업회원'}" />

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>기업 QnA 작성</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main/qna/qna-write.css">
</head>
<body>

  <jsp:include page="/app/main/header/header-logout.jsp" />

  <div id="qnaWritePage"
       data-login-required="${loginRequired}"
       data-company-blocked="${companyBlocked}"
       data-login-message="기업 QnA 글쓰기는 로그인 후 이용 가능합니다."
       data-company-message="기업회원은 기업 QnA 글을 작성할 수 없습니다."
       data-login-url="${pageContext.request.contextPath}/member/login.mefc"
       data-list-url="${pageContext.request.contextPath}/qna/list.qfc"></div>

  <c:if test="${not loginRequired and not companyBlocked}">
    <div class="qnw-wrap">
      <div class="qnw-top">
        <h2 class="qnw-top-ttl">기업 QnA 작성</h2>
        <a href="${pageContext.request.contextPath}/qna/list.qfc" class="qnw-top-mv">기업QnA페이지 바로가기</a>
      </div>

      <div class="qnw-pnl">
        <form id="qnaWriteForm"
              action="${pageContext.request.contextPath}/qna/write.qfc"
              method="post"
              class="qnw-frm">

          <input type="hidden" name="userNumber" value="${requestScope.userNumber}" />

          <div class="qnw-row">
            <div class="qnw-row-col">
              <label for="companyNumber" class="qnw-lbl">기업 선택</label>
              <select name="companyNumber" id="companyNumber" class="qnw-sel" required>
                <option value="">기업을 선택하세요</option>
                <c:forEach var="company" items="${companyList}">
                  <option value="${company.companyNumber}">
                    <c:out value="${company.companyName}" />
                  </option>
                </c:forEach>
              </select>
            </div>
          </div>

          <div class="qnw-row">
            <div class="qnw-row-col">
              <label for="postTitle" class="qnw-lbl">제목</label>
              <input type="text"
                     name="postTitle"
                     id="postTitle"
                     class="qnw-inp"
                     maxlength="100"
                     placeholder="제목은 10자 이상 100자 이하로 입력해주세요"
                     required />
            </div>
          </div>

          <div class="qnw-row">
            <div class="qnw-row-col">
              <label for="postContent" class="qnw-lbl">내용</label>
              <textarea name="postContent"
                        id="postContent"
                        class="qnw-ta"
                        maxlength="1000"
                        placeholder="내용은 공백 포함 10자 이상 1000자 이하로 입력해주세요"
                        required></textarea>
            </div>
          </div>

          <div class="qnw-btm">
            <button type="button" class="qnw-btn qnw-btn-cancel" id="qnaWriteCancelBtn">취소</button>
            <button type="submit" class="qnw-btn qnw-btn-submit">등록</button>
          </div>
        </form>
      </div>
    </div>
  </c:if>

  <script src="${pageContext.request.contextPath}/assets/js/main/qna/qna-write.js"></script>
</body>
</html>