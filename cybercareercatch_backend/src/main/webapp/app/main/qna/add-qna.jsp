<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>기업 QnA 글쓰기</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main/qna/add-qna.css?v=20260320">
</head>
<body>
    <header></header>

    <main class="qna-write-main">
        <div class="qna-write-top">
            <h2 class="qna-write-title">기업 QnA 글쓰기</h2>
            <a href="${pageContext.request.contextPath}/qna/list.qfc" class="qna-btn-secondary">목록으로</a>
        </div>

        <form action="${pageContext.request.contextPath}/qna/write.qfc" method="post" class="qna-write-form">
            <div class="qna-write-group">
                <label for="companyNumber">기업 선택</label>
                <select name="companyNumber" id="companyNumber" class="qna-write-select" required>
                    <option value="">기업을 선택하세요</option>
                    <c:forEach var="company" items="${companyList}">
                        <option value="${company.companyNumber}">
                            ${company.companyName}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="qna-write-group">
                <label for="postTitle">제목</label>
                <input
                    type="text"
                    name="postTitle"
                    id="postTitle"
                    class="qna-write-input"
                    placeholder="제목을 입력하세요"
                    required
                >
            </div>

            <div class="qna-write-group">
                <label for="postContent">내용</label>
                <textarea
                    name="postContent"
                    id="postContent"
                    class="qna-write-textarea"
                    placeholder="내용을 입력하세요"
                    required
                ></textarea>
            </div>

            <div class="qna-write-actions">
                <button type="submit" class="qna-btn-primary">등록</button>
            </div>
        </form>
    </main>

    <script src="${pageContext.request.contextPath}/assets/js/main/qna/add-qna.js"></script>
</body>
</html>