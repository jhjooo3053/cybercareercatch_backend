<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Header - Login Company</title>
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;600;700&family=Poppins:wght@400;500;600&display=swap" rel="stylesheet" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main/header/header-login-company.css" />
</head>
<body>
  <header class="header">
    <div class="header-inner">
      <h1 class="logo">
        <a href="${pageContext.request.contextPath}/mainpage/mainpage.mafc" class="logo-link" aria-label="CyberCareerCatch 홈">
          <span class="logo-strong">C</span><span class="logo-soft">yber</span>
          <span class="logo-strong">C</span><span class="logo-soft">areer</span>
          <span class="logo-strong">C</span><span class="logo-soft">atch</span>
        </a>
      </h1>

      <nav class="main-nav" aria-label="메인 메뉴">
        <ul class="nav-list">
          <li class="nav-item dropdown">
            <a href="#" class="nav-link" onclick="toggleDropdown(event)">직군검사</a>
            <ul class="dropdown-menu job-menu">
              <li><a href="${pageContext.request.contextPath}/mainpage/mainpage.mafc">직군 검사</a></li>
              <li><a href="${pageContext.request.contextPath}/app/main/roadmap/roadmap-questionnaire1.jsp">보안컨설팅</a></li>
              <li><a href="${pageContext.request.contextPath}/app/main/roadmap/roadmap-questionnaire2.jsp">시스템/네트워크/엔지니어</a></li>
              <li><a href="${pageContext.request.contextPath}/app/main/roadmap/roadmap-questionnaire3.jsp">보안관제</a></li>
              <li><a href="${pageContext.request.contextPath}/app/main/roadmap/roadmap-questionnaire4.jsp">침해사고/디지털포렌식</a></li>
            </ul>
          </li>

          <li class="nav-item">
            <a href="${pageContext.request.contextPath}/company/companyAd.cfc" class="nav-link">기업홍보</a>
          </li>

          <li class="nav-item dropdown">
            <a href="#" class="nav-link" onclick="toggleDropdown(event)">커뮤니티</a>
            <ul class="dropdown-menu community-menu">
              <li><a href="${pageContext.request.contextPath}/post/postListOk.pfc">자유게시판</a></li>
              <li><a href="${pageContext.request.contextPath}/qna/list.qfc">기업QnA</a></li>
              <li><a href="${pageContext.request.contextPath}/app/main/expo/expo-calendar.jsp">박람회일정</a></li>
            </ul>
          </li>
        </ul>
      </nav>

      <div class="auth-area">
        <a href="${pageContext.request.contextPath}/app/main/mypage/company-password-check.jsp" class="auth-btn auth-btn--member" aria-label="마이페이지로 이동">
          <span class="member-name" id="headerMemberName">${sessionScope.memberName}님</span>
          <svg class="member-icon" viewBox="0 0 24 24" aria-hidden="true">
            <path d="M12 12a4.25 4.25 0 1 0-4.25-4.25A4.25 4.25 0 0 0 12 12Zm0 1.75c-4.14 0-7.5 2.52-7.5 5.63A1.12 1.12 0 0 0 5.63 20.5h12.74A1.12 1.12 0 0 0 19.5 19.38C19.5 16.27 16.14 13.75 12 13.75Z" fill="currentColor" />
          </svg>
        </a>
        <a href="${pageContext.request.contextPath}/member/logout.mefc" class="auth-btn auth-btn--logout">로그아웃</a>
      </div>
    </div>
  </header>

  <script>
    function toggleDropdown(event) {
        event.preventDefault();
        const currentItem = event.currentTarget.closest(".nav-item");
        const allItems = document.querySelectorAll(".nav-item");

        allItems.forEach((item) => {
            if (item !== currentItem) {
                item.classList.remove("active");
            }
        });

        currentItem.classList.toggle("active");
    }

    window.addEventListener("click", function (event) {
        if (!event.target.closest(".nav-item")) {
            document.querySelectorAll(".nav-item").forEach((item) => {
                item.classList.remove("active");
            });
        }
    });
  </script>
</body>
</html>