<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
<<<<<<< HEAD
    <title>기업 정보페이지 관리</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/main-management/company-info.css">
=======
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CyberCareerCatch</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main/company/company-info.css">
>>>>>>> 8158ab0 (260322 김진옥메인 수정)
</head>

<body class="companyInfo-body">

    <div class="companyInfo-ctr">

        <header class="companyInfo-hdr">

            <!-- 관리자 메인으로 이동하는 타이틀 -->
            <div class="companyInfo-title">
                <a href="${pageContext.request.contextPath}/app/admin/admin-main.jsp">관리자 페이지</a>
            </div>

            <!-- 상단 메뉴 -->
            <nav class="companyInfo-nav">
                <a href="${pageContext.request.contextPath}/app/admin/member-management/member-info.jsp">회원 관리</a>
                <a href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">메인 관리</a>
                <a href="${pageContext.request.contextPath}/app/admin/community-management/expo-schedule.jsp">커뮤니티 관리</a>
            </nav>

            <!-- 로그아웃 버튼 -->
            <a href="${pageContext.request.contextPath}/admin/logout.adfc" class="companyInfo-logout">로그아웃</a>
        </header>

        <main class="companyInfo-main">

            <!-- 왼쪽 사이드 메뉴 -->
            <aside class="companyInfo-leftbar">

                <div class="companyInfo-left-item">
                    <a href="${pageContext.request.contextPath}/admin/insertQuestion.adfc">질의문 관리</a>
                </div>

                <div class="companyInfo-left-item companyInfo-active">
                    <a href="${pageContext.request.contextPath}/admin/companyInfoListOk.adfc">기업 정보페이지</a>
                </div>

                <div class="companyInfo-left-item">
                    <a href="${pageContext.request.contextPath}/admin/roadmapManagement.adfc">로드맵 관리</a>
                </div>

            </aside>

            <!-- 오른쪽 콘텐츠 영역 -->
            <section class="companyInfo-ct">

                <!-- 페이지 제목 -->
                <div class="companyInfo-topbox">
                    <h2 class="companyInfo-subtitle">기업 정보 목록</h2>
                </div>

                <!-- 검색 폼 -->
                <form action="${pageContext.request.contextPath}/admin/companyInfoListOk.adfc" method="get" class="companyInfo-searchForm">
                    <div class="companyInfo-search">
                        <input type="text" name="keyword" value="${keyword}" placeholder="기업명 검색">
                        <button type="submit">검색</button>
                    </div>
                </form>

                <!-- 삭제 폼 -->
                <form id="companyDeleteForm" action="${pageContext.request.contextPath}/admin/deleteCompanyInfo.adfc" method="post">

                    <input type="hidden" name="page" value="${page}">
                    <input type="hidden" name="keyword" value="${keyword}">

                    <!-- 테이블 헤더 -->
                    <div class="companyInfo-tblHead">
                        <div class="col1">선택</div>
                        <div class="col2">번호</div>
                        <div class="col3">기업명</div>
                        <div class="col4">상태</div>
                        <div class="col5">주소</div>
                    </div>

                    <!-- 목록 -->
                    <c:choose>
                        <c:when test="${empty companyList}">
                            <div class="companyInfo-empty">등록된 기업 정보페이지가 없습니다.</div>
                        </c:when>

                        <c:otherwise>
                            <c:forEach var="company" items="${companyList}">
                                <div class="companyInfo-row">
                                    <div class="col1">
                                        <input type="checkbox" name="companyNumber" value="${company.companyNumber}">
                                    </div>
                                    <div class="col2">${company.companyNumber}</div>
                                    <div class="col3">${company.companyName}</div>
                                    <div class="col4">${company.companyState}</div>
                                    <div class="col5">${company.companyAddress}</div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>

                    <!-- 페이지네이션 -->
                    <c:if test="${total > 0}">
                        <div class="companyInfo-page">

                            <c:choose>
                                <c:when test="${prev}">
                                    <a href="${pageContext.request.contextPath}/admin/companyInfoListOk.adfc?page=${startPage - 1}&keyword=${keyword}">&lt;</a>
                                </c:when>
                                <c:otherwise>
                                    <span class="companyInfo-page-disabled">&lt;</span>
                                </c:otherwise>
                            </c:choose>

                            <c:forEach var="i" begin="${startPage}" end="${endPage}">
                                <c:choose>
                                    <c:when test="${i == page}">
                                        <span class="active">${i}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${pageContext.request.contextPath}/admin/companyInfoListOk.adfc?page=${i}&keyword=${keyword}">${i}</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <c:choose>
                                <c:when test="${next}">
                                    <a href="${pageContext.request.contextPath}/admin/companyInfoListOk.adfc?page=${endPage + 1}&keyword=${keyword}">&gt;</a>
                                </c:when>
                                <c:otherwise>
                                    <span class="companyInfo-page-disabled">&gt;</span>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </c:if>

                    <!-- 삭제 버튼 -->
                    <div class="companyInfo-del">
                        <button type="submit">삭제</button>
                    </div>

                </form>

            </section>
        </main>
    </div>

    <script src="${pageContext.request.contextPath}/assets/js/admin/main-management/company-info.js"></script>
</body>

<body>
  <header></header>

  <c:set var="qnaTargetUrl" value="${pageContext.request.contextPath}/member/login.mefc" />
  <c:set var="qnaLoginRequired" value="true" />

  <c:if test="${not empty sessionScope.memberNumber}">
      <c:set var="userNumber" value="${sessionScope.memberNumber}" scope="session" />
      <c:set var="qnaTargetUrl" value="${pageContext.request.contextPath}/app/main/qna/qna-list.jsp" />
      <c:set var="qnaLoginRequired" value="false" />
  </c:if>

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

  <div class="cmp-wrap">
    <!-- cmp-hdr : 기업 헤더 카드 -->
    <section class="cmp-hdr">
      <div class="cmp-hdr-inner">
        <div class="cmp-hdr-meta">
          <!-- cmp-hdr-meta-title : 브랜드+회사명 행 -->
          <div class="cmp-hdr-meta-title">
            <span class="cmp-hdr-meta-brand">CyberCareerCatch</span>
            <span class="cmp-hdr-meta-name">
              <c:choose>
                <c:when test="${not empty companyDetail.companyName}">
                  <c:out value="${companyDetail.companyName}" />
                </c:when>
                <c:otherwise>기업명 미등록</c:otherwise>
              </c:choose>
            </span>
          </div>

          <table class="cmp-hdr-meta-tbl">
            <tr>
              <td class="cmp-hdr-meta-lbl">대표자명</td>
              <td>
                <c:choose>
                  <c:when test="${not empty companyDetail.compCeoName}">
                    <c:out value="${companyDetail.compCeoName}" />
                  </c:when>
                  <c:otherwise>-</c:otherwise>
                </c:choose>
              </td>

              <td class="cmp-hdr-meta-lbl">설립년도</td>
              <td>
                <c:choose>
                  <c:when test="${companyDetail.compFndYear gt 0}">
                    ${companyDetail.compFndYear}
                  </c:when>
                  <c:otherwise>-</c:otherwise>
                </c:choose>
              </td>
            </tr>

            <tr>
              <td class="cmp-hdr-meta-lbl">사원 수</td>
              <td>
                <c:choose>
                  <c:when test="${companyDetail.compEmplCnt gt 0}">
                    ${companyDetail.compEmplCnt}명
                  </c:when>
                  <c:otherwise>-</c:otherwise>
                </c:choose>
              </td>

              <td class="cmp-hdr-meta-lbl">매출액</td>
              <td>
                <c:choose>
                  <c:when test="${not empty companyDetail.compRev}">
                    <c:out value="${companyDetail.compRev}" />
                  </c:when>
                  <c:otherwise>-</c:otherwise>
                </c:choose>
              </td>
            </tr>
          </table>

          <hr class="cmp-hdr-divider"/>

          <table class="cmp-hdr-meta-tbl">
            <tr>
              <td class="cmp-hdr-meta-lbl">사업자번호</td>
              <td>
                <c:choose>
                  <c:when test="${not empty companyDetail.companyBrn}">
                    <c:out value="${companyDetail.companyBrn}" />
                  </c:when>
                  <c:otherwise>-</c:otherwise>
                </c:choose>
              </td>
            </tr>

            <tr>
              <td class="cmp-hdr-meta-lbl">기업형태</td>
              <td>
                <c:choose>
                  <c:when test="${not empty companyDetail.compType}">
                    <c:out value="${companyDetail.compType}" />
                  </c:when>
                  <c:otherwise>-</c:otherwise>
                </c:choose>
              </td>
            </tr>

            <tr>
              <td class="cmp-hdr-meta-lbl">대표 기술</td>
              <td>
                <c:choose>
                  <c:when test="${not empty companyDetail.compTech}">
                    <span style="white-space: pre-line;"><c:out value="${companyDetail.compTech}" /></span>
                  </c:when>
                  <c:otherwise>-</c:otherwise>
                </c:choose>
              </td>
            </tr>

            <tr>
              <td class="cmp-hdr-meta-lbl">주요사업</td>
              <td>
                <c:choose>
                  <c:when test="${not empty companyDetail.compMainBiz}">
                    <span style="white-space: pre-line;"><c:out value="${companyDetail.compMainBiz}" /></span>
                  </c:when>
                  <c:otherwise>-</c:otherwise>
                </c:choose>
              </td>
            </tr>

            <tr>
              <td class="cmp-hdr-meta-lbl">회사위치</td>
              <td>
                <c:choose>
                  <c:when test="${not empty companyDetail.companyAddress}">
                    <span style="white-space: pre-line;"><c:out value="${companyDetail.companyAddress}" /></span>
                  </c:when>
                  <c:otherwise>-</c:otherwise>
                </c:choose>
              </td>
            </tr>
          </table>
        </div>

        <div class="cmp-hdr-logo">
          <div class="cmp-hdr-logo-img"
               <c:if test="${not empty logoSrc}">
                 style="background-image: url('${logoSrc}'); background-size: cover; background-position: center; background-repeat: no-repeat;"
               </c:if>>
          </div>
        </div>
      </div>
    </section>

    <!-- 기업 정보 -->
    <section class="cmp-sec" id="sec-info">
      <h2 class="cmp-sec-title">기업 정보</h2>
      <div class="cmp-sec-body">
        <p class="cmp-sec-sub">
          <c:choose>
            <c:when test="${not empty companyDetail.compSummary}">
              <c:out value="${companyDetail.compSummary}" />
            </c:when>
            <c:otherwise>기업 한줄 소개가 아직 등록되지 않았습니다.</c:otherwise>
          </c:choose>
        </p>

        <p style="white-space: pre-line;">
          <c:choose>
            <c:when test="${not empty companyDetail.compInfo}">
              <c:out value="${companyDetail.compInfo}" />
            </c:when>
            <c:otherwise>기업 상세 소개가 아직 등록되지 않았습니다.</c:otherwise>
          </c:choose>
        </p>

        <div class="cmp-sec-hlgt" style="white-space: pre-line;">
          <c:choose>
            <c:when test="${not empty companyDetail.compSummary}">
              <p><strong><c:out value="${companyDetail.compSummary}" /></strong></p>
            </c:when>
            <c:otherwise>
              <p><strong>기업 한줄 소개가 아직 등록되지 않았습니다.</strong></p>
            </c:otherwise>
          </c:choose>
        </div>
      </div>
    </section>

    <!-- 서비스 확장 및 운영 이력 -->
    <section class="cmp-sec" id="sec-history">
      <h2 class="cmp-sec-title">서비스 확장 및 운영 이력</h2>
      <div class="cmp-sec-body">
        <div class="cmp-sec-hlgt" style="white-space: pre-line;">
          <c:choose>
            <c:when test="${not empty companyDetail.compSvcHist}">
              <c:out value="${companyDetail.compSvcHist}" />
            </c:when>
            <c:otherwise>서비스 확장 및 운영 이력이 아직 등록되지 않았습니다.</c:otherwise>
          </c:choose>
        </div>
      </div>
    </section>

    <!-- 인재상 -->
    <section class="cmp-sec" id="sec-talent">
      <h2 class="cmp-sec-title">인재상</h2>
      <div class="cmp-sec-body">
        <div class="cmp-sec-hlgt" style="white-space: pre-line;">
          <c:choose>
            <c:when test="${not empty companyDetail.jobPostProfile}">
              <c:out value="${companyDetail.jobPostProfile}" />
            </c:when>
            <c:otherwise>인재상이 아직 등록되지 않았습니다.</c:otherwise>
          </c:choose>
        </div>
      </div>
    </section>

    <!-- 채용 부분 -->
    <section class="cmp-sec" id="sec-recruit">
      <h2 class="cmp-sec-title">채용 부분</h2>
      <div class="cmp-sec-body">
        <div class="cmp-sec-hlgt" style="white-space: pre-line;">
          <c:choose>
            <c:when test="${not empty companyDetail.jobPostContent}">
              <c:out value="${companyDetail.jobPostContent}" />
            </c:when>
            <c:otherwise>채용 내용이 아직 등록되지 않았습니다.</c:otherwise>
          </c:choose>
        </div>
      </div>
    </section>

    <!-- 채용 절차 -->
    <section class="cmp-sec" id="sec-process">
      <h2 class="cmp-sec-title">채용 절차</h2>
      <div class="cmp-sec-body">
        <div class="cmp-sec-hlgt" style="white-space: pre-line;">
          <c:choose>
            <c:when test="${not empty companyDetail.jobPostProcess}">
              <c:out value="${companyDetail.jobPostProcess}" />
            </c:when>
            <c:otherwise>채용 절차가 아직 등록되지 않았습니다.</c:otherwise>
          </c:choose>
        </div>
      </div>
    </section>

    <!-- 지원 방법 -->
    <section class="cmp-sec" id="sec-apply">
      <h2 class="cmp-sec-title">지원 방법</h2>
      <div class="cmp-sec-body">
        <div class="cmp-sec-hlgt" style="white-space: pre-line;">
          <c:choose>
            <c:when test="${not empty companyDetail.jobPostMethod}">
              <c:out value="${companyDetail.jobPostMethod}" />
            </c:when>
            <c:otherwise>지원 방법이 아직 등록되지 않았습니다.</c:otherwise>
          </c:choose>
        </div>
      </div>
    </section>

    <!-- 기업 QnA -->
    <section class="cmp-sec cmp-qna" id="sec-qna">
      <div class="cmp-qna-box"
           data-url="${qnaTargetUrl}"
           data-login-required="${qnaLoginRequired}">
        <p class="cmp-qna-title">기업 QnA 게시판</p>

        <c:choose>
          <c:when test="${not empty sessionScope.memberNumber}">
            <p class="cmp-qna-sub">기업 담당자와 대화해보세요!</p>
          </c:when>
          <c:otherwise>
            <p class="cmp-qna-sub">로그인 후 이용할 수 있습니다.</p>
          </c:otherwise>
        </c:choose>
      </div>
    </section>

  </div>

  <script src="${pageContext.request.contextPath}/assets/js/main/company/company-info.js"></script>
</body>
</html>