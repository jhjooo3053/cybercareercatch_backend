  const fileInput = document.getElementById("company-profile-img");
  const fileDeleteBtn = document.getElementById("file-delete-btn");

  fileDeleteBtn.addEventListener("click", function () {
    fileInput.value = "";
  });