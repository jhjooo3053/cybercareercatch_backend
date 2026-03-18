document.addEventListener("DOMContentLoaded", function () {
    // 질의문 답변 목록 행을 가져온다.
    const rows = document.querySelectorAll(".company-check-row:not(.company-check-head)");

    // 페이지 번호를 가져온다.
    const pageButtons = document.querySelectorAll(".company-check-page");

    // 목록 행 hover 처리
    rows.forEach(function (row) {
        row.addEventListener("mouseenter", function () {
            row.classList.add("is-hover");
        });

        row.addEventListener("mouseleave", function () {
            row.classList.remove("is-hover");
        });
    });

    // 페이지 버튼 active 처리
    pageButtons.forEach(function (button) {
        button.addEventListener("click", function () {
            pageButtons.forEach(function (item) {
                item.classList.remove("company-check-page-active");
            });

            button.classList.add("company-check-page-active");
        });
    });
});