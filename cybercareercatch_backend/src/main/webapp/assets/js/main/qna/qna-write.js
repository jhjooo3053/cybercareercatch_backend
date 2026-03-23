document.addEventListener("DOMContentLoaded", function () {
  const page = document.getElementById("qnaWritePage");

  if (!page) {
    return;
  }

  const loginRequired = page.dataset.loginRequired === "true";
  const companyBlocked = page.dataset.companyBlocked === "true";
  const loginMessage = page.dataset.loginMessage;
  const companyMessage = page.dataset.companyMessage;
  const loginUrl = page.dataset.loginUrl;
  const listUrl = page.dataset.listUrl;

  if (loginRequired) {
    alert(loginMessage);
    location.href = loginUrl;
    return;
  }

  if (companyBlocked) {
    alert(companyMessage);
    location.href = listUrl;
    return;
  }

  const cancelButton = document.getElementById("qnaWriteCancelBtn");
  if (cancelButton) {
    cancelButton.addEventListener("click", function () {
      location.href = listUrl;
    });
  }

  const writeForm = document.getElementById("qnaWriteForm");
  if (writeForm) {
    writeForm.addEventListener("submit", function (e) {
      const companyNumber = document.getElementById("companyNumber").value;
      const title = document.getElementById("postTitle").value.trim();
      const contentElement = document.getElementById("postContent");
      const content = contentElement.value;
      const trimmedContent = contentElement.value.trim();

      if (!companyNumber) {
        alert("기업을 선택해주세요.");
        e.preventDefault();
        return;
      }

      if (title.length < 10 || title.length > 100) {
        alert("제목은 10자 이상 100자 이하로 입력해주세요.");
        e.preventDefault();
        return;
      }

      if (trimmedContent.length === 0 || content.length < 10 || content.length > 1000) {
        alert("내용은 공백 포함 10자 이상 1000자 이하로 입력해주세요.");
        e.preventDefault();
        return;
      }

      if (!confirm("글을 등록하시겠습니까?")) {
        e.preventDefault();
      }
    });
  }
});