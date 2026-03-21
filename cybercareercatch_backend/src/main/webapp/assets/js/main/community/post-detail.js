document.addEventListener("DOMContentLoaded", () => {
  const contextPath = document.body.dataset.contextPath || "";

  const listBtn = document.querySelector(".cmd-list-btn");
  const deleteBtn = document.querySelector(".cmd-delete-btn");

  listBtn?.addEventListener("click", () => {
    location.href = `${contextPath}/post/list.pfc`;
  });

  deleteBtn?.addEventListener("click", () => {
    const postNumber = deleteBtn.dataset.postNumber;

    if (!postNumber) {
      alert("게시글 번호가 없습니다.");
      return;
    }

    if (!confirm("정말 삭제하시겠습니까?")) {
      return;
    }

    const form = document.createElement("form");
    form.method = "post";
    form.action = `${contextPath}/post/deleteOk.pfc`;

    const input = document.createElement("input");
    input.type = "hidden";
    input.name = "postNumber";
    input.value = postNumber;

    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
  });
});