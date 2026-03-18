document.addEventListener("DOMContentLoaded", function () {
    // 목록 버튼을 가져온다.
    const backButton = document.querySelector(".expoDetail-back");

    // 저장 버튼을 가져온다.
    const saveButton = document.querySelector(".expoDetail-save");

    // 목록 버튼 클릭 시 이전 페이지로 이동한다.
    if (backButton) {
        backButton.addEventListener("click", function () {
            history.back();
        });
    }

    // 저장 버튼 클릭 시 안내를 띄운다.
    if (saveButton) {
        saveButton.addEventListener("click", function () {
            alert("박람회 상세 정보가 저장됩니다.");
        });
    }
});