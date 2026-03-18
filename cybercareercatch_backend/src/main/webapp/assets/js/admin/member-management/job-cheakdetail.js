document.addEventListener("DOMContentLoaded", function () {
    // 직군 선택 라디오 버튼을 가져온다.
    const jobRadios = document.querySelectorAll("input[name='jobNumber']");

    // 저장 버튼을 가져온다.
    const saveButton = document.querySelector(".company-checkdetail-save");

    // 저장 버튼이 없으면 종료한다.
    if (!saveButton) {
        return;
    }

    // 저장 버튼 클릭 시 직군 선택 여부를 검사한다.
    saveButton.addEventListener("click", function (e) {
        let isChecked = false;

        jobRadios.forEach(function (radio) {
            if (radio.checked) {
                isChecked = true;
            }
        });

        if (!isChecked) {
            alert("직군을 선택하세요.");
            e.preventDefault();
            return;
        }

        alert("직군 판정이 저장됩니다.");
    });
});