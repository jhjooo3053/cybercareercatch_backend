document.addEventListener("DOMContentLoaded", function () {
    // 질의문 입력창 전체를 가져온다.
    const questionInputs = document.querySelectorAll(".qnaMgmt-qItem input[type='text']");

    // 저장 버튼을 가져온다.
    const saveButton = document.querySelector(".qnaMgmt-check");

    // 저장 버튼이 없으면 아래 코드를 실행하지 않는다.
    if (!saveButton) {
        return;
    }

    // 저장 버튼 클릭 시 전체 질문 입력 여부를 검사한다.
    saveButton.addEventListener("click", function (e) {
        for (let i = 0; i < questionInputs.length; i++) {
            const currentInput = questionInputs[i];

            // 질문 내용이 비어 있으면 저장을 막는다.
            if (currentInput.value.trim() === "") {
                alert((i + 1) + "번 질문 내용을 입력하세요.");
                currentInput.focus();
                e.preventDefault();
                return;
            }
        }

        // 모든 질문이 입력되었으면 저장 안내를 띄운다.
        alert("질의문이 저장됩니다.");
    });
});