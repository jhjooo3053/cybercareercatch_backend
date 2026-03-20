document.addEventListener("DOMContentLoaded", function () {

    const showMoreBtn = document.getElementById("showmore");
    const stepList = document.querySelector(".rmq-steplist");

    if (!showMoreBtn || !stepList) {
        return;
    }

    stepList.classList.remove("active");
    showMoreBtn.textContent = "더보기 +";

    showMoreBtn.addEventListener("click", function () {
        stepList.classList.toggle("active");

        if (stepList.classList.contains("active")) {
            showMoreBtn.textContent = "접기 -";
            stepList.scrollIntoView({
                behavior: "smooth",
                block: "start"
            });
        } else {
            showMoreBtn.textContent = "더보기 +";
        }
    });

});