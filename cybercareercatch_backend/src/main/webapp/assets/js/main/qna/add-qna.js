/*
기업 QnA 글쓰기 페이지 스크립트

역할
1. 제목/내용 길이 검증
2. 등록 전 확인창 띄우기
*/

const qnaWriteForm = document.getElementById("qnaWriteForm");

if (qnaWriteForm) {
  qnaWriteForm.addEventListener("submit", function (e) {
    const companyNumber = document.getElementById("companyNumber").value;
    const postTitle = document.getElementById("postTitle").value.trim();
    const postContentElement = document.getElementById("postContent");
    const postContent = postContentElement.value;        // 공백 포함 길이 체크용
    const postContentTrimmed = postContentElement.value.trim(); // 공백만 입력 방지용

    if (!companyNumber) {
      alert("기업을 선택해주세요.");
      e.preventDefault();
      return;
    }

    if (postTitle.length < 10 || postTitle.length > 100) {
      alert("제목은 10자 이상 100자 이하로 입력해주세요.");
      return;
    }

    // 내용은 공백 포함 10자 이상 1000자 이하
    if (postContentTrimmed.length === 0 || postContent.length < 10 || postContent.length > 1000) {
      alert("내용은 공백 포함 10자 이상 1000자 이하로 입력해주세요.");
      e.preventDefault();
      return;
    }

    const ok = confirm("글을 생성하겠습니까?");
    if (!ok) {
      e.preventDefault();
    }
  });
}