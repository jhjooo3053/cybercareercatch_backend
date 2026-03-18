document.addEventListener("DOMContentLoaded", function () {
    // 로드맵 카테고리 버튼을 가져온다.
    const categoryButtons = document.querySelectorAll(".roadmap-btn");

    // 수정 버튼을 가져온다.
    const editButtons = document.querySelectorAll(".roadmap-edit-btn");

    // 카테고리 버튼 클릭 시 active 표시를 바꾼다.
    categoryButtons.forEach(function (button) {
        button.addEventListener("click", function () {
            categoryButtons.forEach(function (item) {
                item.classList.remove("active");
            });

            button.classList.add("active");
        });
    });

    // 수정 버튼 클릭 시 안내를 띄운다.
    editButtons.forEach(function (button) {
        button.addEventListener("click", function () {
            alert("로드맵 수정 페이지로 이동합니다.");
        });
    });
});