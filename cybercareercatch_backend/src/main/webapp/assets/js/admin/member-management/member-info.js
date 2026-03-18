document.addEventListener("DOMContentLoaded", function () {
    // 회원 목록 체크박스를 가져온다.
    const checkboxes = document.querySelectorAll(".memberInfo-row input[type='checkbox']");

    // 삭제 버튼을 가져온다.
    const deleteButton = document.querySelector(".memberInfo-delete button");

    // 페이지 버튼을 가져온다.
    const pageButtons = document.querySelectorAll(".memberInfo-page span");

    // 삭제 버튼이 없으면 종료한다.
    if (!deleteButton) {
        return;
    }

    // 삭제 버튼 클릭 시 체크 여부를 검사한다.
    deleteButton.addEventListener("click", function () {
        let checkedCount = 0;

        checkboxes.forEach(function (checkbox) {
            if (checkbox.checked) {
                checkedCount++;
            }
        });

        if (checkedCount === 0) {
            alert("삭제할 회원을 선택하세요.");
            return;
        }

        alert("선택한 회원 정보가 삭제됩니다.");
    });

    // 페이지 active 처리
    pageButtons.forEach(function (button) {
        button.addEventListener("click", function () {
            const text = button.textContent.trim();

            if (text === "<<" || text === "<" || text === ">" || text === ">>") {
                return;
            }

            pageButtons.forEach(function (item) {
                item.classList.remove("active");
            });

            button.classList.add("active");
        });
    });
});