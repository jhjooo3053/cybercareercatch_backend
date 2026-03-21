document.addEventListener("DOMContentLoaded", () => {
  // 글쓰기 폼 찾기
  const form = document.getElementById("postWriteForm");

  // 제목 입력칸 찾기
  const titleInput = document.getElementById("postTitle");

  // 내용 입력칸 찾기
  const contentInput = document.getElementById("postContent");

  // 등록 버튼 찾기
  const submitBtn = document.querySelector(".cmw-btn");

  // 제출 전에 제목/내용 검사
  form?.addEventListener("submit", (e) => {
    // 제목 공백 제거
    const title = titleInput.value.trim();

    // 내용 공백 제거
    const content = contentInput.value.trim();

    // 제목이 비면 제출 막기
    if (!title) {
      e.preventDefault();
      alert("제목을 입력해주세요.");
      titleInput.focus();
      return;
    }

    // 내용이 비면 제출 막기
    if (!content) {
      e.preventDefault();
      alert("내용을 입력해주세요.");
      contentInput.focus();
      return;
    }

    // 정리된 값 다시 넣기
    titleInput.value = title;
    contentInput.value = content;

    // 중복 제출 방지
    if (submitBtn) {
      submitBtn.disabled = true;
      submitBtn.textContent = "등록 중...";
    }
  });
});