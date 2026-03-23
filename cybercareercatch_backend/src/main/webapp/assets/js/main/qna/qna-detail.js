document.addEventListener("DOMContentLoaded", function () {
  const page = document.getElementById("qnaDetailPage");

  if (!page) {
    return;
  }

  const loginRequired = page.dataset.loginRequired === "true";
  const loginMessage = page.dataset.loginMessage;
  const loginUrl = page.dataset.loginUrl;

  if (loginRequired) {
    alert(loginMessage);
    location.href = loginUrl;
    return;
  }

  const deleteButton = document.querySelector(".js-qna-delete");
  if (deleteButton) {
    deleteButton.addEventListener("click", function () {
      const deleteUrl = this.dataset.deleteUrl;

      if (confirm("정말 삭제하시겠습니까?")) {
        location.href = deleteUrl;
      }
    });
  }

  const commentDeleteButton = document.querySelector(".js-comment-delete");
  if (commentDeleteButton) {
    commentDeleteButton.addEventListener("click", function () {
      const deleteUrl = this.dataset.deleteUrl;

      if (confirm("댓글을 삭제하시겠습니까?")) {
        location.href = deleteUrl;
      }
    });
  }

  const commentForm = document.getElementById("qnaCommentForm");
  if (commentForm) {
    commentForm.addEventListener("submit", function (e) {
      const commentInput = document.getElementById("commentContent");
      const comment = commentInput.value.trim();

      if (comment.length < 10) {
        alert("답변 내용은 공백 제외 10자 이상 입력해주세요.");
        e.preventDefault();
        return;
      }

      if (comment.length > 1000) {
        alert("답변 내용은 1000자 이하로 입력해주세요.");
        e.preventDefault();
        return;
      }

      if (!confirm("답변을 등록하시겠습니까?")) {
        e.preventDefault();
      }
    });
  }
});
