document.addEventListener("DOMContentLoaded", function () {
   const deleteBtn = document.getElementById("mypage-delete-btn");

   if (!deleteBtn) return;

   deleteBtn.addEventListener("click", function () {
      const form = deleteBtn.closest("form");
      const checkedBoxes = document.querySelectorAll("input[name='postNumbers']:checked");

      if (checkedBoxes.length === 0) {
         alert("삭제할 게시글을 선택해주세요.");
         return;
      }

      const isConfirmed = confirm("선택한 Q&A 게시글을 삭제하시겠습니까?");

      if (isConfirmed) {
         form.submit();
      }
   });
});