document.addEventListener("DOMContentLoaded", () => {
  // 컨텍스트 경로 구하기
  function getContextPath() {
    const path = location.pathname;
    const postIndex = path.indexOf("/post/");
    const appIndex = path.indexOf("/app/");

    if (postIndex > -1) {
      return path.substring(0, postIndex);
    }

    if (appIndex > -1) {
      return path.substring(0, appIndex);
    }

    return "";
  }

  // 상단 이동 링크 찾기
  const topMoveBtn = document.querySelector(".cnd-top-mv");

  // 하단 목록 버튼 찾기
  const listBtn = document.querySelector(".cnd-btn");

  // 컨텍스트 경로 저장
  const contextPath = getContextPath();

  // 상단 링크 클릭 시 목록으로 이동
  topMoveBtn?.addEventListener("click", (e) => {
    e.preventDefault();
    location.href = `${contextPath}/post/list.pfc`;
  });

  // 하단 버튼 클릭 시 목록으로 이동
  listBtn?.addEventListener("click", (e) => {
    e.preventDefault();
    location.href = `${contextPath}/post/list.pfc`;
  });
});