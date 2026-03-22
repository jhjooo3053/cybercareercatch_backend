document.addEventListener("DOMContentLoaded", function () {
    const filterSelect = document.querySelector(".ad-sort-sel");

    if (!filterSelect) {
        return;
    }

    filterSelect.addEventListener("change", function () {
        this.form.submit();
    });
});