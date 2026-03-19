<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>CyberCareerCatch - 박람회 일정</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main/expo/expo-calendar.css" />

    <!-- FullCalendar -->
    <link href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js"></script>
</head>

<script>
    const contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/assets/js/main/expo/expo-calendar.js"></script>

<body>
    <header></header>

    <main class="expo-main">

        <!-- 캘린더 -->
        <section class="expo-cal">
            <div class="expo-cal-hdr">
                <button class="expo-cal-btn" id="prevMonth">&#8249;</button>

                <div class="expo-cal-month">
                    <span class="expo-cal-month-num" id="calMonthNum"></span>
                    <span class="expo-cal-month-txt" id="calMonthText"></span>
                    <span class="expo-cal-year" id="calYearText"></span>
                </div>

                <button class="expo-cal-btn" id="nextMonth">&#8250;</button>
            </div>

            <div id="calendar"></div>
        </section>

        <!-- 박람회 상세 -->
        <section class="expo-list">
            <div class="expo-info-box">
                <div class="expo-info-head">
                    <h2 class="expo-list-title" id="expoTitle">박람회를 선택해주세요.</h2>
                    <p class="expo-list-location" id="expoLocation">
                        달력의 박람회 일정 바를 클릭하면 개최 장소와 참가 기업 정보가 표시됩니다.
                    </p>
                </div>

                <div class="expo-info-divider"></div>

                <div class="expo-company-list" id="expoBody">
                    <div class="expo-company-empty">
                        박람회 일정이 선택되지 않았습니다.
                    </div>
                </div>
            </div>

            <div class="expo-list-notice">
                <p><strong>기업 신청 이메일 : CyberCarrerCatch@gmail.com</strong></p>
                <p><strong>기업 신청은 박람회 개최일 전 10일 까지 입니다</strong></p>
            </div>
        </section>

    </main>

    <script src="${pageContext.request.contextPath}/assets/js/main/expo/expo-calendar.js"></script>
</body>

</html>