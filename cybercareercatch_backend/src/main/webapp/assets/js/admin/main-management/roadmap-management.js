document.addEventListener("DOMContentLoaded", function () {

    const roadmapForm = document.getElementById("roadmapForm");
    const resetBtn = document.getElementById("roadmapResetBtn");
    const textareas = document.querySelectorAll(".rm-textarea");

    function resizeTextarea(textarea) {
        textarea.style.height = "auto";
        textarea.style.height = textarea.scrollHeight + "px";
    }

    textareas.forEach(function (textarea) {
        resizeTextarea(textarea);

        textarea.addEventListener("input", function () {
            resizeTextarea(textarea);
        });
    });

    if (roadmapForm) {
        roadmapForm.addEventListener("submit", function (e) {
            const isOk = confirm("로드맵 내용을 수정하시겠습니까?");

            if (!isOk) {
                e.preventDefault();
            }
        });
    }

    if (resetBtn) {
        resetBtn.addEventListener("click", function (e) {
            const isOk = confirm("입력한 내용을 초기화하시겠습니까?");

            if (!isOk) {
                e.preventDefault();
            }
        });
    }
});