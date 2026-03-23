<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>기업 QnA 작성</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/main/qna/add-qna.css">
</head>
<body>

	<jsp:include page="/app/main/header/header-logout.jsp" />

	<div class="qnw-wrap">
		<div class="qnw-top">
			<h2 class="qnw-top-ttl">기업 QnA 작성</h2>
			<button type="button" class="qnw-top-mv"
				onclick="location.href='${pageContext.request.contextPath}/qna/list.qfc'">목록</button>
		</div>

		<div class="qnw-pnl">
			<form id="qnaWriteForm"
				action="${pageContext.request.contextPath}/qna/write.qfc"
				method="post">
				<div class="qnw-row">
					<label class="qnw-lbl" for="companyNumber">기업 선택</label> <select
						class="qnw-sel" name="companyNumber" id="companyNumber" required>
						<option value="">기업을 선택하세요</option>
						<c:forEach var="company" items="${companyList}">
							<option value="${company.companyNumber}">${company.companyName}</option>
						</c:forEach>
					</select>
				</div>

				<div class="qnw-row">
					<label class="qnw-lbl" for="postTitle">제목</label> <input
						class="qnw-inp" type="text" name="postTitle" id="postTitle"
						maxlength="100" placeholder="제목은 10자 이상 100자 이하로 입력해주세요" required>
				</div>

				<div class="qnw-row qnw-row-col">
					<label class="qnw-lbl" for="postContent">내용</label>
					<textarea class="qnw-ta" name="postContent" id="postContent"
						maxlength="1000" placeholder="내용은 공백 포함 10자 이상 1000자 이하로 입력해주세요"
						required></textarea>
				</div>

				<div class="qnw-btm">
					<button type="button" class="qnw-btn qnw-btn-cancel"
						onclick="location.href='${pageContext.request.contextPath}/qna/list.qfc'">취소</button>
					<button type="submit" class="qnw-btn qnw-btn-submit">등록</button>
				</div>
			</form>
		</div>
	</div>

	<script
		src="${pageContext.request.contextPath}/assets/js/main/qna/add-qna.js"></script>
</body>
</html>