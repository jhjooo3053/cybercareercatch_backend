<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <title>기업 정보페이지 관리</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/main-management/company-info.css">
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
                <a href="${pageContext.request.contextPath}/app/admin/main-management/qna-management.jsp">메인 관리</a>
                <a href="${pageContext.request.contextPath}/app/admin/community-management/expo-schedule.jsp">커뮤니티 관리</a>
            </nav>

            <!-- 로그아웃 버튼 -->
            <a href="${pageContext.request.contextPath}/admin/logout.adfc" class="companyInfo-logout">로그아웃</a>

        </header>

        <main class="companyInfo-main">

            <!-- 왼쪽 사이드 메뉴 -->
            <aside class="companyInfo-leftbar">

                <div class="companyInfo-left-item">
                    <a href="${pageContext.request.contextPath}/app/admin/main-management/qna-management.jsp">질의문 관리</a>
                </div>

                <div class="companyInfo-left-item companyInfo-active">
                    <a href="${pageContext.request.contextPath}/app/admin/main-management/company-info.jsp">기업 정보페이지</a>
                </div>

                <div class="companyInfo-left-item">
                    <a href="${pageContext.request.contextPath}/app/admin/main-management/roadmap-management.jsp">로드맵 관리</a>
                </div>

            </aside>

            <!-- 오른쪽 콘텐츠 영역 -->
            <form>
                <section class="companyInfo-ct">

                    <!-- 페이지 제목 -->
                    <div class="companyInfo-topbox">
                        <h2 class="companyInfo-subtitle">기업 정보 목록</h2>
                    </div>

                    <!-- 검색 영역 -->
                    <div class="companyInfo-search">
                        <input type="text" placeholder="기업명 검색">
                        <button type="button">검색</button>
                    </div>

                    <!-- 테이블 헤더 -->
                    <div class="companyInfo-tblHead">
                        <div class="col1">선택</div>
                        <div class="col2">번호</div>
                        <div class="col3">기업명</div>
                        <div class="col4">상태</div>
                        <div class="col5">주소</div>
                    </div>

                    <!-- 목록 -->
                    <div class="companyInfo-row">
                        <div class="col1"><input type="checkbox"></div>
                        <div class="col2">20</div>
                        <div class="col3">에버가드</div>
                        <div class="col4">승인</div>
                        <div class="col5">경상북도 포항시 남구 포스코대로 120</div>
                    </div>

                    <div class="companyInfo-row">
                        <div class="col1"><input type="checkbox"></div>
                        <div class="col2">19</div>
                        <div class="col3">락포인트</div>
                        <div class="col4">반려</div>
                        <div class="col5">충청남도 천안시 서북구 불당로 919</div>
                    </div>

                    <div class="companyInfo-row">
                        <div class="col1"><input type="checkbox"></div>
                        <div class="col2">18</div>
                        <div class="col3">시큐브릿지</div>
                        <div class="col4">승인</div>
                        <div class="col5">경기도 고양시 일산동구 중앙로 818</div>
                    </div>

                    <div class="companyInfo-row">
                        <div class="col1"><input type="checkbox"></div>
                        <div class="col2">17</div>
                        <div class="col3">쉴드웍스</div>
                        <div class="col4">대기</div>
                        <div class="col5">서울특별시 구로구 디지털로 717</div>
                    </div>

                    <div class="companyInfo-row">
                        <div class="col1"><input type="checkbox"></div>
                        <div class="col2">16</div>
                        <div class="col3">인포락</div>
                        <div class="col4">승인</div>
                        <div class="col5">제주특별자치도 제주시 연북로 616</div>
                    </div>

                    <div class="companyInfo-row">
                        <div class="col1"><input type="checkbox"></div>
                        <div class="col2">15</div>
                        <div class="col3">네오쉴드</div>
                        <div class="col4">대기</div>
                        <div class="col5">경상남도 창원시 성산구 중앙대로 515</div>
                    </div>

                    <div class="companyInfo-row">
                        <div class="col1"><input type="checkbox"></div>
                        <div class="col2">14</div>
                        <div class="col3">세이프큐브</div>
                        <div class="col4">승인</div>
                        <div class="col5">전라북도 전주시 덕진구 백제대로 414</div>
                    </div>

                    <div class="companyInfo-row">
                        <div class="col1"><input type="checkbox"></div>
                        <div class="col2">13</div>
                        <div class="col3">가디언테크</div>
                        <div class="col4">반려</div>
                        <div class="col5">충청북도 청주시 상당구 상당로 313</div>
                    </div>

                    <div class="companyInfo-row">
                        <div class="col1"><input type="checkbox"></div>
                        <div class="col2">12</div>
                        <div class="col3">트러스트원</div>
                        <div class="col4">승인</div>
                        <div class="col5">경기도 수원시 영통구 광교로 212</div>
                    </div>

                    <div class="companyInfo-row">
                        <div class="col1"><input type="checkbox"></div>
                        <div class="col2">11</div>
                        <div class="col3">스마트시큐어</div>
                        <div class="col4">대기</div>
                        <div class="col5">서울특별시 마포구 월드컵북로 111</div>
                    </div>

                    <!-- 페이지네이션 -->
                    <div class="companyInfo-page">
                        <span>&lt;</span>
                        <span class="active">1</span>
                        <span>2</span>
                        <span>3</span>
                        <span>4</span>
                        <span>5</span>
                        <span>&gt;</span>
                    </div>

                    <!-- 삭제 버튼 -->
                    <div class="companyInfo-del">
                        <button type="button">삭제</button>
                    </div>

                </section>
            </form>

        </main>
    </div>

    <script src="${pageContext.request.contextPath}/assets/js/admin/main-management/company-info.js"></script>
</body>

</html>