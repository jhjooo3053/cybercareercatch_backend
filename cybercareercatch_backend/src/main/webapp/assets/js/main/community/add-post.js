document.addEventListener("DOMContentLoaded", () => {
  // 글쓰기 폼 찾기
  const form = document.getElementById("postWriteForm");

  // 제목 입력칸 찾기
  const titleInput = document.getElementById("postTitle");

  // 내용 입력칸 찾기
  const contentInput = document.getElementById("postContent");

  // 제목 글자 수 표시 영역 찾기
  const titleCount = document.getElementById("titleCount");

  // 내용 글자 수 표시 영역 찾기
  const contentCount = document.getElementById("contentCount");

  // 등록 버튼 찾기
  const submitBtn = document.querySelector(".cmw-btn");

  // 제목 최소/최대 글자 수
  const TITLE_MIN = 10;
  const TITLE_MAX = 66;

  // 내용 최소/최대 글자 수
  const CONTENT_MIN = 10;
  const CONTENT_MAX = 1000;

  // 같은 초과 알림이 연속으로 너무 많이 뜨는 것 방지
  let titleAlertShown = false;
  let contentAlertShown = false;

  // 현재 글자 수 표시
  function updateCount() {
    if (titleInput && titleCount) {
      titleCount.textContent = `${titleInput.value.length} / ${TITLE_MAX}`;
    }

    if (contentInput && contentCount) {
      contentCount.textContent = `${contentInput.value.length} / ${CONTENT_MAX}`;
    }
  }

  // 최대 글자 수 초과 시 잘라내기 + 알림
  function limitInput(input, max, type) {
    if (!input) return;

    if (input.value.length > max) {
      input.value = input.value.substring(0, max);

      if (type === "title" && !titleAlertShown) {
        alert("제목은 더이상 입력할 수 없습니다. 최대 66자까지 입력 가능합니다.");
        titleAlertShown = true;
      }

      if (type === "content" && !contentAlertShown) {
        alert("내용은 더이상 입력할 수 없습니다. 최대 1000자까지 입력 가능합니다.");
        contentAlertShown = true;
      }
    } else {
      if (type === "title") {
        titleAlertShown = false;
      }

      if (type === "content") {
        contentAlertShown = false;
      }
    }
  }

  // 제목 입력 시 글자 수 반영
  if (titleInput) {
    titleInput.addEventListener("input", () => {
      limitInput(titleInput, TITLE_MAX, "title");
      updateCount();
    });
  }

  // 내용 입력 시 글자 수 반영
  if (contentInput) {
    contentInput.addEventListener("input", () => {
      limitInput(contentInput, CONTENT_MAX, "content");
      updateCount();
    });
  }

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

    // 제목이 10자 미만이면 제출 막기
    if (title.length < TITLE_MIN) {
      e.preventDefault();
      alert("제목은 10자 이상 입력해주세요.");
      titleInput.focus();
      return;
    }

    // 제목이 66자 초과면 제출 막기
    if (title.length > TITLE_MAX) {
      e.preventDefault();
      alert("제목은 최대 66자까지 입력 가능합니다.");
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

    // 내용이 10자 미만이면 제출 막기
    if (content.length < CONTENT_MIN) {
      e.preventDefault();
      alert("내용은 10자 이상 입력해주세요.");
      contentInput.focus();
      return;
    }

    // 내용이 1000자 초과면 제출 막기
    if (content.length > CONTENT_MAX) {
      e.preventDefault();
      alert("내용은 최대 1000자까지 입력 가능합니다.");
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

  // 페이지 로드 시 초기 카운트 표시
  updateCount();
});