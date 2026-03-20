<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <title>관리자 페이지</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/community-management/expo-schedule.css">
</head>

<body class="expoSchedule-body">

    <div class="expoSchedule-container">

        <header class="expoSchedule-top">

            <div class="expoSchedule-title">관리자 페이지</div>

            <nav class="expoSchedule-menu1">
                <a href="../member-management/member-info.html">회원 관리</a>
                <a href="../main-management/qna-management.html">메인 관리</a>
                <a href="../community-management/expo-schedule.html">커뮤니티 관리</a>
            </nav>

           <a href="${pageContext.request.contextPath}/admin/logout.adfc" class="logout-btn">로그아웃</a>

        </header>


        <main class="expoSchedule-main">

            <aside class="expoSchedule-leftbar">

                <div class="expoSchedule-left-item expoSchedule-active">
                    <a href="./expo-schedule.html">박람회 일정</a>
                </div>

                <div class="expoSchedule-left-item">
                    <a href="./community-management.html">자유 게시판</a>
                </div>

                <div class="expoSchedule-left-item">
                    <a href="./company-qna.html">기업 QnA</a>
                </div>

            </aside>


            <form>

                <section class="expoSchedule-content">


                    <div class="expoSchedule-tab">

                        <div class="expoSchedule-tab-item expoSchedule-active">박람회 일정</div>

                        <div class="expoSchedule-tab-item">
                            <a href="${pageContext.request.contextPath}/html/admin/community-management/expodetail-schedule.html">박람회 일정 관리</a>
                        </div>

                    </div>


                    <div class="expoSchedule-table">

                        <div class="expoSchedule-row expoSchedule-head">
                            <div>번호</div>
                            <div>박람회 명</div>
                            <div>시작일</div>
                            <div>종료일</div>
                            <div>장소</div>
                            <div>참여기업수</div>
                            <div>진행상태</div>
                        </div>


                        <div class="expoSchedule-row">
                            <div>10</div>
                            <div>제1회 박람회</div>
                            <div>2026년01월01일</div>
                            <div>2026년01월04일</div>
                            <div>서울</div>
                            <div>5</div>
                            <div>예정</div>
                        </div>

                        <div class="expoSchedule-row">
                            <div>9</div>
                            <div>제1회 박람회</div>
                            <div>2026년01월01일</div>
                            <div>2026년01월04일</div>
                            <div>서울</div>
                            <div>5</div>
                            <div>예정</div>
                        </div>

                        <div class="expoSchedule-row">
                            <div>8</div>
                            <div>제1회 박람회</div>
                            <div>2026년01월01일</div>
                            <div>2026년01월04일</div>
                            <div>서울</div>
                            <div>5</div>
                            <div>예정</div>
                        </div>

                        <div class="expoSchedule-row">
                            <div>7</div>
                            <div>제1회 박람회</div>
                            <div>2026년01월01일</div>
                            <div>2026년01월04일</div>
                            <div>서울</div>
                            <div>5</div>
                            <div>진행 중</div>
                        </div>

                        <div class="expoSchedule-row">
                            <div>6</div>
                            <div>제1회 박람회</div>
                            <div>2026년01월01일</div>
                            <div>2026년01월04일</div>
                            <div>서울</div>
                            <div>5</div>
                            <div>진행 중</div>
                        </div>

                        <div class="expoSchedule-row">
                            <div>5</div>
                            <div>제1회 박람회</div>
                            <div>2026년01월01일</div>
                            <div>2026년01월04일</div>
                            <div>서울</div>
                            <div>5</div>
                            <div>진행 중</div>
                        </div>

                        <div class="expoSchedule-row">
                            <div>4</div>
                            <div>제1회 박람회</div>
                            <div>2026년01월01일</div>
                            <div>2026년01월04일</div>
                            <div>서울</div>
                            <div>5</div>
                            <div>진행 중</div>
                        </div>

                        <div class="expoSchedule-row">
                            <div>3</div>
                            <div>제1회 박람회</div>
                            <div>2026년01월01일</div>
                            <div>2026년01월04일</div>
                            <div>서울</div>
                            <div>5</div>
                            <div>진행 중</div>
                        </div>

                        <div class="expoSchedule-row">
                            <div>2</div>
                            <div>제1회 박람회</div>
                            <div>2026년01월01일</div>
                            <div>2026년01월04일</div>
                            <div>서울</div>
                            <div>5</div>
                            <div>진행 중</div>
                        </div>

                        <div class="expoSchedule-row">
                            <div>1</div>
                            <div>제1회 박람회</div>
                            <div>2026년01월01일</div>
                            <div>2026년01월04일</div>
                            <div>서울</div>
                            <div>5</div>
                            <div>진행 중</div>
                        </div>

                    </div>


                    <div class="expoSchedule-page">

                        <span>&lt;&lt;</span>
                        <span>&lt;</span>
                        <span class="expoSchedule-page-active">1</span>
                        <span>2</span>
                        <span>3</span>
                        <span>4</span>
                        <span>5</span>
                        <span>&gt;</span>
                        <span>&gt;&gt;</span>

                    </div>


                    <div class="expoSchedule-company">

                        <h3>참가기업 목록</h3>

                        <div class="expoSchedule-company-row">
                            <span>1</span>
                            <span>사랑기업</span>
                            <input type="checkbox">
                        </div>

                    </div>


                    <div class="expoSchedule-company-delete">
                        <button type="button" class="expoSchedule-company-delBtn">선택 기업 삭제</button>
                    </div>

                </section>

            </form>

        </main>

    </div>

    <script src="${pageContext.request.contextPath}/assets/js/admin/community-management/expo-schedule.js"></script>

</body>

</html>