const form = document.querySelector(".qna-write-form");

if (form) {
    form.addEventListener("submit", function (e) {
        const companyNumber = document.getElementById("companyNumber").value.trim();
        const postTitle = document.getElementById("postTitle").value.trim();
        const postContent = document.getElementById("postContent").value.trim();

        if (!companyNumber) {
            alert("기업을 선택하세요.");
            e.preventDefault();
            return;
        }

        if (!postTitle) {
            alert("제목을 입력하세요.");
            e.preventDefault();
            return;
        }

        if (!postContent) {
            alert("내용을 입력하세요.");
            e.preventDefault();
            return;
        }
    });
}