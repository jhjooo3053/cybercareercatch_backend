<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CyberCareerCatch</title>
    <link rel="stylesheet" href="../../../assets/css/main/company/company-info.css">
</head>

<body>
    <header></header>

    <c:set var="logoSrc" value="" />
    <c:if test="${not empty companyDetail.filePath}">
        <c:choose>
            <c:when test="${fn:startsWith(companyDetail.filePath, 'http://') or fn:startsWith(companyDetail.filePath, 'https://')}">
                <c:set var="logoSrc" value="${companyDetail.filePath}" />
            </c:when>
            <c:when test="${fn:startsWith(companyDetail.filePath, '/')}">
                <c:set var="logoSrc" value="${pageContext.request.contextPath}${companyDetail.filePath}" />
            </c:when>
            <c:otherwise>
                <c:set var="logoSrc" value="${pageContext.request.contextPath}/${companyDetail.filePath}" />
            </c:otherwise>
        </c:choose>
    </c:if>

    <main class="cmp-page">
        <section class="cmp-hdr">
            <div class="cmp-hdr-inner">
                <div class="cmp-hdr-main">
                    <div class="cmp-hdr-title-row">
                        <span class="cmp-hdr-brand">CyberCareerCatch</span>
                        <h1 class="cmp-hdr-name">
                            <c:out value="${companyDetail.companyName}" default="기업명 미등록" />
                        </h1>
                    </div>

                    <div class="cmp-chip-list">
                        <c:if test="${companyDetail.cat1IsHiring eq 1}">
                            <span class="cmp-chip">보안컨설팅</span>
                        </c:if>
                        <c:if test="${companyDetail.cat2IsHiring eq 1}">
                            <span class="cmp-chip">시스템/네트워크/엔지니어</span>
                        </c:if>
                        <c:if test="${companyDetail.cat3IsHiring eq 1}">
                            <span class="cmp-chip">보안관제</span>
                        </c:if>
                        <c:if test="${companyDetail.cat4IsHiring eq 1}">
                            <span class="cmp-chip">침해사고/포렌식</span>
                        </c:if>

                        <c:if test="${companyDetail.cat1IsHiring ne 1 and companyDetail.cat2IsHiring ne 1 and companyDetail.cat3IsHiring ne 1 and companyDetail.cat4IsHiring ne 1}">
                            <span class="cmp-chip cmp-chip--muted">채용 직군 미등록</span>
                        </c:if>
                    </div>

                    <div class="cmp-summary-box">
                        ${empty companyDetail.compSummary ? '기업 한줄 소개가 아직 등록되지 않았습니다.' : companyDetail.compSummary}
                    </div>

                    <div class="cmp-info-grid">
                        <div class="cmp-info-item">
                            <span class="cmp-info-label">대표자명</span>
                            <span class="cmp-info-value"><c:out value="${companyDetail.compCeoName}" default="-" /></span>
                        </div>

                        <div class="cmp-info-item">
                            <span class="cmp-info-label">설립년도</span>
                            <span class="cmp-info-value">
                                <c:choose>
                                    <c:when test="${companyDetail.compFndYear gt 0}">
                                        ${companyDetail.compFndYear}
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </span>
                        </div>

                        <div class="cmp-info-item">
                            <span class="cmp-info-label">사원 수</span>
                            <span class="cmp-info-value">
                                <c:choose>
                                    <c:when test="${companyDetail.compEmplCnt gt 0}">
                                        ${companyDetail.compEmplCnt}명
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </span>
                        </div>

                        <div class="cmp-info-item">
                            <span class="cmp-info-label">매출액</span>
                            <span class="cmp-info-value">
                                <c:choose>
                                    <c:when test="${companyDetail.compRev gt 0}">
                                        ${companyDetail.compRev}
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </span>
                        </div>

                        <div class="cmp-info-item">
                            <span class="cmp-info-label">자본금</span>
                            <span class="cmp-info-value">
                                <c:choose>
                                    <c:when test="${companyDetail.compCap gt 0}">
                                        ${companyDetail.compCap}
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </span>
                        </div>

                        <div class="cmp-info-item">
                            <span class="cmp-info-label">기업형태</span>
                            <span class="cmp-info-value"><c:out value="${companyDetail.compType}" default="-" /></span>
                        </div>

                        <div class="cmp-info-item">
                            <span class="cmp-info-label">대표 기술</span>
                            <span class="cmp-info-value cmp-info-value--multiline">
                                <c:out value="${companyDetail.compTech}" default="-" />
                            </span>
                        </div>

                        <div class="cmp-info-item">
                            <span class="cmp-info-label">주요사업</span>
                            <span class="cmp-info-value cmp-info-value--multiline">
                                <c:out value="${companyDetail.compMainBiz}" default="-" />
                            </span>
                        </div>

                        <div class="cmp-info-item">
                            <span class="cmp-info-label">회사 위치</span>
                            <span class="cmp-info-value cmp-info-value--multiline">
                                <c:out value="${companyDetail.companyAddress}" default="-" />
                            </span>
                        </div>

                        <div class="cmp-info-item">
                            <span class="cmp-info-label">사업자번호</span>
                            <span class="cmp-info-value"><c:out value="${companyDetail.companyBrn}" default="-" /></span>
                        </div>
                    </div>
                </div>

                <div class="cmp-hdr-logo">
                    <c:choose>
                        <c:when test="${not empty logoSrc}">
                            <img class="cmp-hdr-logo-img"
                                 src="${logoSrc}"
                                 alt="<c:out value='${companyDetail.companyName}' default='기업' /> 로고"
                                 onerror="this.style.display='none'; this.nextElementSibling.style.display='flex';">
                            <div class="cmp-hdr-logo-fallback" style="display:none;">로고 이미지</div>
                        </c:when>
                        <c:otherwise>
                            <div class="cmp-hdr-logo-fallback">로고 이미지</div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </section>

        <section class="cmp-sec">
            <h2 class="cmp-sec-title">기업 정보</h2>
            <div class="cmp-sec-body">
                <div class="cmp-text-block cmp-text-block--strong">
                    ${empty companyDetail.compSummary ? '기업 한줄 소개가 아직 등록되지 않았습니다.' : companyDetail.compSummary}
                </div>

                <div class="cmp-text-block cmp-text-block--multiline">
                    ${empty companyDetail.compInfo ? '기업 상세 소개가 아직 등록되지 않았습니다.' : companyDetail.compInfo}
                </div>
            </div>
        </section>

        <section class="cmp-sec">
            <h2 class="cmp-sec-title">서비스 확장 및 운영 이력</h2>
            <div class="cmp-sec-body">
                <div class="cmp-text-block cmp-text-block--multiline">
                    ${empty companyDetail.compSvcHist ? '서비스 확장 및 운영 이력이 아직 등록되지 않았습니다.' : companyDetail.compSvcHist}
                </div>
            </div>
        </section>

        <section class="cmp-sec">
            <h2 class="cmp-sec-title">인재상</h2>
            <div class="cmp-sec-body">
                <div class="cmp-text-block cmp-text-block--multiline">
                    ${empty companyDetail.jobPostProfile ? '인재상이 아직 등록되지 않았습니다.' : companyDetail.jobPostProfile}
                </div>
            </div>
        </section>

        <section class="cmp-sec">
            <h2 class="cmp-sec-title">채용 부분</h2>
            <div class="cmp-sec-body">
                <div class="cmp-text-block cmp-text-block--multiline">
                    ${empty companyDetail.jobPostContent ? '채용 내용이 아직 등록되지 않았습니다.' : companyDetail.jobPostContent}
                </div>
            </div>
        </section>

        <section class="cmp-sec">
            <h2 class="cmp-sec-title">채용 절차</h2>
            <div class="cmp-sec-body">
                <div class="cmp-text-block cmp-text-block--multiline">
                    ${empty companyDetail.jobPostProcess ? '채용 절차가 아직 등록되지 않았습니다.' : companyDetail.jobPostProcess}
                </div>
            </div>
        </section>

        <section class="cmp-sec">
            <h2 class="cmp-sec-title">지원 방법</h2>
            <div class="cmp-sec-body">
                <div class="cmp-text-block cmp-text-block--multiline">
                    ${empty companyDetail.jobPostMethod ? '지원 방법이 아직 등록되지 않았습니다.' : companyDetail.jobPostMethod}
                </div>
            </div>
        </section>

        <section class="cmp-sec cmp-qna">
            <div class="cmp-qna-box" data-url="${pageContext.request.contextPath}/app/main/qna/qna-list.jsp">
                <p class="cmp-qna-title">기업 QnA 게시판</p>
                <p class="cmp-qna-sub">기업 담당자와 대화해보세요!</p>
            </div>
        </section>
    </main>

    <script src="${pageContext.request.contextPath}/assets/js/main/company/company-info.js"></script>
</body>
</html>