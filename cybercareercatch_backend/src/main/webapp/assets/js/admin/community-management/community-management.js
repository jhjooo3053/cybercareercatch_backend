document.addEventListener("DOMContentLoaded", function () {
    // 자유게시판 목록의 체크박스를 가져온다.
    const checkboxes = document.querySelectorAll(".qnaMgmt-row input[type='checkbox']");

    // 삭제 버튼을 가져온다.
    const deleteButton = document.querySelector(".qnaMgmt-del button");

    // 페이지 번호를 가져온다.
    const pageSpans = document.querySelectorAll(".qnaMgmt-page span");

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
            alert("삭제할 게시글을 선택하세요.");
            return;
        }

        alert("선택한 게시글이 삭제됩니다.");
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