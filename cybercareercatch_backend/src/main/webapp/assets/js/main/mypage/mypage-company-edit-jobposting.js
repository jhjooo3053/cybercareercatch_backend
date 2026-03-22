document.addEventListener("DOMContentLoaded", function () {
  // 폼 제출 시 직군 최소 1개 선택 검사
  const form = document.getElementById("company-edit-form");

  if (form) {
    form.addEventListener("submit", function (e) {
      const checkedJobs = document.querySelectorAll('input[name="job_group"]:checked');

      if (checkedJobs.length === 0) {
        alert("직군은 최소 1개 이상 선택해야 합니다.");
        e.preventDefault();
        return;
      }
    });
  }

  // 취소 버튼
  const cancelBtn = document.getElementById("cancel-btn");

  if (cancelBtn) {
    cancelBtn.addEventListener("click", function () {
      const moveUrl = cancelBtn.dataset.moveUrl;
      const isOk = confirm("수정 중인 내용이 사라집니다.\n정말 취소하시겠습니까?");

      if (isOk) {
        location.href = moveUrl;
      }
    });
  }

  // 게시물 삭제 버튼
  const deleteBtn = document.getElementById("delete-btn");

  if (deleteBtn) {
    deleteBtn.addEventListener("click", function () {
      const deleteUrl = deleteBtn.dataset.deleteUrl;
      const isOk = confirm("기업정보페이지를 삭제하시겠습니까?\n삭제 후 복구할 수 없습니다.");

      if (isOk) {
        location.href = deleteUrl;
      }
    });
  }
});