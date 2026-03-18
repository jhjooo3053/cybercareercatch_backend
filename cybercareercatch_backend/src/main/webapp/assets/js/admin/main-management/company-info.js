document.addEventListener("DOMContentLoaded", function () {

    const deleteBtn = document.querySelector(".companyInfo-del button");
    const allRows = Array.from(document.querySelectorAll(".companyInfo-row"));
    const pageSpans = document.querySelectorAll(".companyInfo-page span");

    const rowsPerPage = 10;
    let currentPage = 1;
    const totalPages = Math.ceil(allRows.length / rowsPerPage);

    function showPage(page) {
        const start = (page - 1) * rowsPerPage;
        const end = start + rowsPerPage;

        allRows.forEach((row, i) => {
            row.style.display = (i >= start && i < end) ? "flex" : "none";
        });

        pageSpans.forEach(span => span.classList.remove("active"));
        const span = Array.from(pageSpans).find(s => parseInt(s.textContent) === page);
        if (span) span.classList.add("active");

        currentPage = page;
    }		document.addEventListener("DOMContentLoaded", function () {
		    // 기업 정보 목록의 모든 체크박스를 가져온다.
		    const checkboxes = document.querySelectorAll(".companyInfo-col5 input[type='checkbox']");

		    // 삭제 버튼을 가져온다.
		    const deleteButton = document.querySelector(".companyInfo-delete button");

		    // 페이지 번호를 가져온다.
		    const pageButtons = document.querySelectorAll(".companyInfo-page span");

		    // 삭제 버튼이 없으면 아래 코드를 실행하지 않는다.
		    if (!deleteButton) {
		        return;
		    }

		    // 삭제 버튼 클릭 시 하나라도 선택되었는지 검사한다.
		    deleteButton.addEventListener("click", function () {
		        let checkedCount = 0;

		        checkboxes.forEach(function (checkbox) {
		            if (checkbox.checked) {
		                checkedCount++;
		            }
		        });

		        // 선택된 항목이 없으면 삭제를 막는다.
		        if (checkedCount === 0) {
		            alert("삭제할 기업을 선택하세요.");
		            return;
		        }

		        // 선택된 항목이 있으면 안내만 띄운다.
		        alert("선택한 기업 정보가 삭제됩니다.");
		    });

		    // 페이지 번호 active 처리
		    pageButtons.forEach(function (button) {
		        button.addEventListener("click", function () {
		            pageButtons.forEach(function (item) {
		                item.classList.remove("active");
		            });

		            if (
		                button.textContent.trim() !== "<" &&
		                button.textContent.trim() !== ">"
		            ) {
		                button.classList.add("active");
		            }
		        });
		    });
		});

    showPage(1);

    pageSpans.forEach(span => {
        span.addEventListener("click", () => {
            const text = span.textContent.trim();

            if (text === "<") {
                showPage(Math.max(1, currentPage - 1));
                return;
            }
            if (text === ">") {
                showPage(Math.min(totalPages, currentPage + 1));
                return;
            }

            const pageNum = parseInt(text);
            if (!isNaN(pageNum)) {
                showPage(pageNum);
            }
        });
    });

    deleteBtn.addEventListener("click", function () {

        const visibleRows = allRows.filter(row => row.style.display !== "none");
        const checkedBoxes = visibleRows
            .map(row => row.querySelector("input[type='checkbox']"))
            .filter(box => box.checked);

        if (checkedBoxes.length === 0) {
            alert("삭제할 항목을 선택하세요.");
            return;
        }

        if (confirm("선택한 항목을 삭제하시겠습니까?")) {
            checkedBoxes.forEach(box => {
                const index = allRows.indexOf(box.closest(".companyInfo-row"));
                if (index > -1) allRows.splice(index, 1);
                box.closest(".companyInfo-row").remove();
            });
            showPage(currentPage);
        }
    });

});