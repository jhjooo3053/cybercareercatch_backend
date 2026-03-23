<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>자유게시판</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main/community/post-list.css">
</head>
<body>

    <main class="post-list-wrap">
        <section class="post-list-container">
            <h2 class="post-list-title">자유게시판</h2>

            <table class="post-list-table">
                <thead>
                    <tr>
                        <th class="col-no">번호</th>
                        <th class="col-title">제목</th>
                        <th class="col-writer">작성자</th>
                        <th class="col-date">작성일</th>
                        <th class="col-view">조회수</th>
                    </tr>
                </thead>

                <tbody>
                    <!-- 공지 -->
                    <c:if test="${not empty noticeList}">
                        <c:forEach var="notice" items="${noticeList}">
                            <tr>
                                <td>공지</td>
                                <td class="post-title-td">
                                    <a href="${pageContext.request.contextPath}/post/noticeDetail.pfc?postNumber=${notice.postNumber}"
                                       class="post-title-link">
                                        ${notice.postTitle}
                                    </a>
                                </td>
                                <td>${notice.adminId}</td>
                                <td>${notice.postDate}</td>
                                <td>${notice.viewCount}</td>
                            </tr>
                        </c:forEach>
                    </c:if>

                    <!-- 일반 게시글 -->
                    <c:if test="${not empty postList}">
                        <c:forEach var="post" items="${postList}">
                            <tr>
                                <td>${post.postNumber}</td>
                                <td class="post-title-td">
                                    <a href="${pageContext.request.contextPath}/post/readOk.pfc?postNumber=${post.postNumber}"
                                       class="post-title-link">
                                        ${post.postTitle}
                                    </a>
                                </td>
                                <td>${post.userId}</td>
                                <td>${post.postDate}</td>
                                <td>${post.viewCount}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>

            <!-- 페이지네이션 -->
            <div class="post-page-box">
                <c:if test="${prev}">
                    <a href="${pageContext.request.contextPath}/post/list.pfc?page=${startPage - 1}" class="page-btn">이전</a>
                </c:if>

                <c:forEach var="i" begin="${startPage}" end="${endPage}">
                    <c:choose>
                        <c:when test="${page == i}">
                            <span class="page-btn active">${i}</span>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/post/list.pfc?page=${i}" class="page-btn">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${next}">
                    <a href="${pageContext.request.contextPath}/post/list.pfc?page=${endPage + 1}" class="page-btn">다음</a>
                </c:if>
            </div>

            <!-- 검색 -->
            <div class="post-search-box">
                <form action="${pageContext.request.contextPath}/post/list.pfc" method="get">
                    <select name="searchType">
                        <option value="title">제목</option>
                        <option value="writer">작성자</option>
                        <option value="content">내용</option>
                    </select>

                    <input type="text" name="keyword" placeholder="검색할 내용을 입력하세요">
                    <button type="submit">검색</button>
                    <a href="${pageContext.request.contextPath}/post/write.pfc" class="write-btn">글쓰기</a>
                </form>
            </div>
        </section>
    </main>

</body>
</html>