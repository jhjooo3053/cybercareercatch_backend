document.addEventListener("DOMContentLoaded", function () {
    // 공지 textarea를 가져온다.
    const noticeBox = document.querySelector("#notice-box");

    // 공지 수정 버튼을 가져온다.
    const noticeButton = document.querySelector("#notice-btn");

    // 기업 QnA 삭제 버튼을 가져온다.
    const deleteButton = document.querySelector("#delete-btn");

    // 기업 선택 select를 가져온다.
    const companySelect = document.querySelector(".qna-filter select");

    // QnA 행을 전부 가져온다.
    const qnaRows = document.querySelectorAll(".qna-row:not(.qna-head)");

    // 페이지 번호를 가져온다.
    const pageSpans = document.querySelectorAll(".qna-page span");

    // 체크박스를 가져온다.
    const checkboxes = document.querySelectorAll(".qna-row input[type='checkbox']");

    // 공지 버튼 클릭 시 공지 입력값 검사를 한다.
    if (noticeButton) {
        noticeButton.addEventListener("click", function () {
            if (!noticeBox.value.trim()) {
                alert("공지 내용을 입력하세요.");
                noticeBox.focus();
                return;
            }

            alert("공지사항이 저장됩니다.");
        });
    }

    // 삭제 버튼 클릭 시 체크된 항목이 있는지 검사한다.
    if (deleteButton) {
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
    }

    // 기업 선택 필터
    if (companySelect) {
        companySelect.addEventListener("change", function () {
            const selectedCompany = companySelect.value.trim();

            qnaRows.forEach(function (row) {
                const companyCell = row.querySelector(".col3");

                if (!companyCell) {
                    return;
                }

                const companyName = companyCell.textContent.trim();

                if (selectedCompany === "기업선택" || selectedCompany === companyName) {
                    row.style.display = "flex";
                } else {
                    row.style.display = "none";
                }
            });
        });
    }

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