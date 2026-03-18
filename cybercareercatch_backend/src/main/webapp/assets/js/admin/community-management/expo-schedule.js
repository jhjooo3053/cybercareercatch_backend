document.addEventListener("DOMContentLoaded", function () {
    // 박람회 일정 목록의 체크박스를 가져온다.
    const checkboxes = document.querySelectorAll(".expoSchedule-row input[type='checkbox']");

    // 삭제 버튼을 가져온다.
    const deleteButton = document.querySelector(".expoSchedule-delete button");

    // 페이지 번호를 가져온다.
    const pageSpans = document.querySelectorAll(".expoSchedule-page span");

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
            alert("삭제할 박람회를 선택하세요.");
            return;
        }

        alert("선택한 박람회 일정이 삭제됩니다.");
    });

    // 페이지 active 처리
    pageSpans.forEach(function (span) {
        span.addEventListener("click", function () {
            const text = span.textContent.trim();

            if (text === "<<" || text === "<" || text === ">" || text === ">>") {
                return;
            }

            pageSpans.forEach(function (item) {
                item.classList.remove("active");
            });

            span.classList.add("active");
        });
    });
});