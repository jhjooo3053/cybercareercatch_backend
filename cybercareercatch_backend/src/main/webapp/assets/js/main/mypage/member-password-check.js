document.addEventListener("DOMContentLoaded", function () {
  const userPw = document.getElementById("userPw");
  const pwError = document.getElementById("pwError");

  userPw.addEventListener("input", function () {
    if (userPw.value.trim() !== "") {
      pwError.textContent = "";
    }
  });
});