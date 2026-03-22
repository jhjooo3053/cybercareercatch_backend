document.addEventListener("DOMContentLoaded", function () {
    const container = document.querySelector(".mainpage-main-container");
    const companyAdMoreLink = document.getElementById("mainpage-companyad-more-link");
    const form = document.getElementById("mainQuestionForm");
    const answerInput = document.getElementById("mainpage-searchjob-answer");
    const searchImg = document.getElementById("searchimg");
    const hiddenSubmitButton = document.getElementById("mainpage-hidden-submit");

    let isSubmitting = false;

    if (answerInput) {
        answerInput.focus();
    }

    if (companyAdMoreLink) {
        companyAdMoreLink.addEventListener("click", function () {
            const isLoginRequired = companyAdMoreLink.dataset.loginRequired === "true";

            if (isLoginRequired) {
                alert("기업 홍보 페이지는 로그인 후 이용할 수 있습니다.");
            }
        });
    }

    if (searchImg && hiddenSubmitButton) {
        searchImg.addEventListener("click", function () {
            hiddenSubmitButton.click();
        });
    }

    if (form && answerInput) {
        form.addEventListener("submit", function (event) {
            const answerValue = answerInput.value.trim();

            if (!answerValue) {
                event.preventDefault();
                alert("답변을 입력해주세요.");
                answerInput.focus();
                return;
            }

            isSubmitting = true;
        });
    }

    if (container) {
        const resetUrl = container.dataset.resetUrl;
        const isTestInProgress = container.dataset.testInProgress === "true";

        if (resetUrl && isTestInProgress) {
            window.addEventListener("pagehide", function () {
                if (isSubmitting) {
                    return;
                }

                try {
                    if (navigator.sendBeacon) {
                        navigator.sendBeacon(resetUrl);
                    } else {
                        fetch(resetUrl, {
                            method: "POST",
                            keepalive: true
                        });
                    }
                } catch (e) {
                    console.log("직군검사 초기화 요청 실패", e);
                }
            });
        }
    }
});