document.addEventListener("DOMContentLoaded", function () {
    const qnaBox = document.querySelector(".cmp-qna-box");

    if (!qnaBox) {
        return;
    }

    qnaBox.addEventListener("click", function () {
        const targetUrl = qnaBox.dataset.url;

        if (targetUrl) {
            window.location.href = targetUrl;
        }
    });
});