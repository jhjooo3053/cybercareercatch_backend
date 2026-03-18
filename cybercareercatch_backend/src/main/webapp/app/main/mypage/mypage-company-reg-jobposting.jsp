<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>기업정보페이지 수정</title>
  <link rel="stylesheet" href="../../../assets/css/main/mypage/mypage-company-edit-jobposting.css">
  <script defer src="../../../assets/js/main/mypage/mypage-company-edit-jobposting.js"></script>
</head>

<body>
  <main>
    <div class="jobpost-main-container">
      <div class="jobpost-title">마이페이지</div>
      <div class="jobpost-subtitle">기업정보 수정</div>
      <form action="" method="post" enctype="multipart/form-data">

        <!-- 기업 기본정보 -->
        <div class="jobpost-section">
          <div class="jobpost-section-title">기업 기본정보</div>
          <div class="jobpost-section-box">
            <div class="jobpost-section-subtitle">기업 프로필 이미지</div>
            <div class="jobpost-section-content"><input class="jobpost-section-inputcontent" type="file"
                name="company_profile_img" id="company-profile-img" accept="image/*">
              <button type="button" class="btn file-delete-btn" id="file-delete-btn">삭제</button>
            </div>
          </div>

          <div class="jobpost-section-box">
            <div class="jobpost-section-subtitle">대표자명</div>
            <div class="jobpost-section-content"><input class="jobpost-section-inputcontent" type="text"
                name="company_ceoname" id="company-ceoname" placeholder="예 : 이재용"></div>
          </div>

          <div class="jobpost-section-box">
            <div class="jobpost-section-subtitle">설립년도</div>
            <div class="jobpost-section-content"><input class="jobpost-section-inputcontent" type="text"
                name="company_yearfounded" id="company-yearfounded" placeholder="예 : 2010"></div>
          </div>

          <div class="jobpost-section-box">
            <div class="jobpost-section-subtitle">사원수</div>
            <div class="jobpost-section-content"><input class="jobpost-section-inputcontent" type="text"
                name="company_employeecount" id="company-employeecount" placeholder="예 : 1200명"></div>
          </div>

          <div class="jobpost-section-box">
            <div class="jobpost-section-subtitle">매출액</div>
            <div class="jobpost-section-content"><input class="jobpost-section-inputcontent" type="text"
                name="company_sales" id="company-sales" placeholder="예 : 100억"></div>
          </div>

          <div class="jobpost-section-box">
            <div class="jobpost-section-subtitle">자본금</div>
            <div class="jobpost-section-content"><input class="jobpost-section-inputcontent" type="text"
                name="company_capital" id="company-capital" placeholder="예 : 10억"></div>
          </div>

          <div class="jobpost-section-box">
            <div class="jobpost-section-subtitle">기업형태</div>
            <div class="jobpost-section-content"><input class="jobpost-section-inputcontent" type="text"
                name="company_type" id="company-type" placeholder="예 : 중견기업"></div>
          </div>
        </div>

        <!-- 기업 정보 -->
        <div class="jobpost-section">
          <div class="jobpost-section-title">기업 정보</div>

          <div class="jobpost-section-box">
            <div class="jobpost-section-subtitle">한줄소개</div>
            <div class="jobpost-section-content"><input class="jobpost-section-inputcontent" type="text"
                name="company_description" id="company-description" placeholder="예 : 보안서비스 개발 컨설팅 전문기업"></div>
          </div>
          <div class="jobpost-section-box">
            <div class="jobpost-section-subtitle">대표기술</div>
            <div class="jobpost-section-content-textarea"><textarea class="jobpost-section-content" name="company_tech"
                id="company-tech" placeholder="사용 기술, 보유 기술, 핵심 역량 등을 입력하세요"></textarea></div>
          </div>

          <div class="jobpost-section-box">
            <div class="jobpost-section-subtitle">주요사업</div>
            <div class="jobpost-section-content-textarea"><textarea name="company_business" id="company-business"
                placeholder="주요 서비스, 사업 분야 등을 입력하세요"></textarea></div>
          </div>

          <div class="jobpost-section-box">
            <div class="jobpost-section-subtitle">서비스 확장 및 운영 이력</div>
            <div class="jobpost-section-content-textarea"><textarea name="company_history" id="company-history"
                placeholder="주요 서비스, 사업 분야 등을 입력하세요"></textarea></div>
          </div>
        </div>

        <!-- 채용부분 -->
        <div class="jobpost-section">
          <div class="jobpost-section-title">채용공고</div>
          <div class="jobpost-section-jobchecklist">직군선택
            <div class="check-item">
              <label>
                <input type="checkbox" name="job_group" value="job1">
                <span>직군1</span>
              </label>
              <label>
                <input type="checkbox" name="job_group" value="job2">
                <span>직군2</span>
              </label>
              <label>
                <input type="checkbox" name="job_group" value="job3">
                <span>직군3</span>
              </label>
              <label>
                <input type="checkbox" name="job_group" value="job4">
                <span>직군4</span>
              </label>
            </div>
          </div>
          <div class="jobpost-section-box">
            <div class="jobpost-section-subtitle">인재상</div>
            <div class="jobpost-section-content-textarea"><textarea name="company_talent"
                id="company-talent"></textarea></div>
          </div>


          <div class="jobpost-section-box">
            <div class="jobpost-section-subtitle">채용부분</div>
            <div class="jobpost-section-content-textarea"><textarea name="company_jobpart"
                id="company-jobpart"></textarea></div>
          </div>


          <div class="jobpost-section-box">
            <div class="jobpost-section-subtitle">채용 절차</div>
            <div class="jobpost-section-content-textarea"><textarea name="company_process"
                id="company-process"></textarea></div>
          </div>

          <div class="jobpost-section-box">
            <div class="jobpost-section-subtitle">지원 정보</div>
            <div class="jobpost-section-content-textarea"><textarea name="company_apply" id="company-apply"></textarea>
            </div>
          </div>
        </div>

        <div class="btn-wrap">
          <button type="submit" class="btn" id="save-btn">입력완료</button>
          <button type="button" class="btn" id="cancel-btn">취소</button>
        </div>
        <button type="button" class="btn" id="delete-btn">게시물 삭제</button>
      </form>
    </div>
  </main>


</body>

</html>