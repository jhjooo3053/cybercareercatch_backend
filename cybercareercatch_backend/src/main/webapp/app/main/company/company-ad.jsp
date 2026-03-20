<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CyberCareerCatch</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main/company/company-ad.css">
</head>
<body>
    <header></header>

    <main class="ad-main">
        <p class="ad-main-title">기업홍보페이지</p>

        <div class="ad-sort-bar">
            <form class="ad-sort-form" action="${pageContext.request.contextPath}/company/companyAd.cfc" method="get">
                <input type="hidden" name="sortType" value="latest">

                <select class="ad-sort-sel" name="jobNumber" aria-label="기업홍보 직군 필터">
                    <option value="" <c:if test="${empty companyCardQuery.jobNumber}">selected="selected"</c:if>>최신순</option>
                    <option value="1" <c:if test="${companyCardQuery.jobNumber eq 1}">selected="selected"</c:if>>보안컨설팅</option>
                    <option value="2" <c:if test="${companyCardQuery.jobNumber eq 2}">selected="selected"</c:if>>시스템/네트워크/엔지니어</option>
                    <option value="3" <c:if test="${companyCardQuery.jobNumber eq 3}">selected="selected"</c:if>>보안관제</option>
                    <option value="4" <c:if test="${companyCardQuery.jobNumber eq 4}">selected="selected"</c:if>>침해사고/포렌식</option>
                </select>
            </form>
        </div>

        <div class="ad-grid">
            <c:choose>
                <c:when test="${empty companyAdList}">
                    <div class="ad-grid-card">등록된 기업홍보 정보가 없습니다.</div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="company" items="${companyAdList}">
                        <a href="${pageContext.request.contextPath}/company/companyInfo.cfc?companyNumber=${company.companyNumber}">
                            <div class="ad-grid-card">
                                <span class="ad-grid-card-num">${company.companyNumber}</span>
                                <span>${company.companyName}</span>
                                <span>${company.compType}</span>
                                <span>${company.compSummary}</span>
                                <span class="ad-grid-card-meta">${company.compPageDate}</span>
                            </div>
                        </a>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>

      <div class="ad-pg">
    <c:choose>
        <c:when test="${not empty companyAdDTO}">
            <c:if test="${companyAdDTO.prev}">
                <button type="button"
                        onclick="location.href='${pageContext.request.contextPath}/company/companyAd.cfc?page=${companyAdDTO.startPage - 1}&jobNumber=${companyCardQuery.jobNumber}&sortType=latest'">
                    &lt;
                </button>
            </c:if>

            <c:forEach var="i" begin="${companyAdDTO.startPage}" end="${companyAdDTO.endPage}">
                <button type="button"
                        class="${i eq companyAdDTO.page ? 'ad-pg-active' : ''}"
                        onclick="location.href='${pageContext.request.contextPath}/company/companyAd.cfc?page=${i}&jobNumber=${companyCardQuery.jobNumber}&sortType=latest'">
                    ${i}
                </button>
            </c:forEach>

            <c:if test="${companyAdDTO.next}">
                <button type="button"
                        onclick="location.href='${pageContext.request.contextPath}/company/companyAd.cfc?page=${companyAdDTO.endPage + 1}&jobNumber=${companyCardQuery.jobNumber}&sortType=latest'">
                    &gt;
                </button>
            </c:if>
        </c:when>

        <c:otherwise>
            <button type="button"
                    class="ad-pg-active"
                    onclick="location.href='${pageContext.request.contextPath}/company/companyAd.cfc?page=1&sortType=latest'">
                1
            </button>
        </c:otherwise>
    </c:choose>
</div>
    </main>

    <script src="${pageContext.request.contextPath}/assets/js/main/company/company-ad.js"></script>
</body>
</html>