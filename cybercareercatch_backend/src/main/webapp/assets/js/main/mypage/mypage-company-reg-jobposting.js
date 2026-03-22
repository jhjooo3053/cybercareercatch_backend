document.addEventListener("DOMContentLoaded", function () {
  // 등록 폼 제출 시 직군 최소 1개 선택 검사
  const form = document.getElementById("company-register-form");

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
      const isOk = confirm("입력 중인 내용이 사라집니다.\n정말 취소하시겠습니까?");

      if (isOk && moveUrl) {
        location.href = moveUrl;
      }
    });
  }
});