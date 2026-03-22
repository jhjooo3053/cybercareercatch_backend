<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>CyberCareerCatch - 기업 QnA 목록</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main/qna/qna-list.css?v=20260320" />
</head>

<body>
    <header></header>

    <main class="qna-list-main">
        <div class="qna-list-top">
            <h2 class="qna-list-title">기업 QnA</h2>
            <div class="qna-list-top-actions">
<a href="${pageContext.request.contextPath}/qna/write-form.qfc"
   class="qna-list-search-btn qna-list-search-btn-write">글쓰기</a>
            </div>
        </div>

        <!-- 기업 선택 form -->
        <form action="${pageContext.request.contextPath}/qna/list.qfc" method="get">
            <div class="qna-list-filter">
                <select class="qna-list-filter-sel"
                        name="companyNumber"
                        id="companyFilter"
                        onchange="this.form.submit()">
                    <option value="">기업선택</option>
                    <c:forEach var="company" items="${companyList}">
                        <option value="${company.companyNumber}"
                            ${companyNumber eq company.companyNumber.toString() ? 'selected' : ''}>
                            ${company.companyName}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </form>

        <div class="qna-list-tbl">
            <div class="qna-list-tbl-row qna-list-tbl-header">
                <div class="qna-list-tbl-num">번호</div>
                <div class="qna-list-tbl-title">제목</div>
                <div class="qna-list-tbl-company">작성자</div>
                <div class="qna-list-tbl-date">작성일</div>
                <div class="qna-list-tbl-status">답변상태</div>
            </div>

            <div id="qna-tbl-body">
                <c:choose>
                    <c:when test="${not empty qnaList}">
                        <c:forEach var="post" items="${qnaList}">
                            <div class="qna-list-tbl-row" onclick="openPost(${post.postNumber})">
                                <div class="qna-list-tbl-num">${post.postNumber}</div>
                                <div class="qna-list-tbl-title">${post.postTitle}</div>
                                <div class="qna-list-tbl-company">${post.memberId}</div>
                                <div class="qna-list-tbl-date">${post.postCreatedDate}</div>
                                <div class="qna-list-tbl-status">
                                    <c:choose>
                                        <c:when test="${post.answerStatus eq '답변완료'}">
                                            <span class="qna-status qna-status-done">답변완료</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="qna-status qna-status-wait">답변대기</span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="qna-list-empty">검색 결과가 없습니다.</div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <!-- 제목/작성자 검색 form -->
        <form action="${pageContext.request.contextPath}/qna/list.qfc" method="get" class="qna-list-search">
            <select class="qna-list-search-sel" name="searchType">
                <option value="title" ${searchType eq 'title' ? 'selected' : ''}>제목</option>
                <option value="writer" ${searchType eq 'writer' ? 'selected' : ''}>작성자</option>
            </select>

            <input
                class="qna-list-search-inp"
                type="text"
                name="searchKeyword"
                placeholder="검색할 내용을 입력하세요"
                value="${searchKeyword}"
            />

            <button class="qna-list-search-btn" type="submit">검색</button>
        </form>
    </main>

    <script>
        const contextPath = "${pageContext.request.contextPath}";
    </script>
    <script src="${pageContext.request.contextPath}/assets/js/main/qna/qna-list.js"></script>
</body>
</html>