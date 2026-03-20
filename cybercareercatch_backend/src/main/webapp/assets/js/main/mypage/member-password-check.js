const form = document.getElementById("passwordCheckForm");
const userPw = document.getElementById("userPw");
const pwError = document.getElementById("pwError");

if (form && userPw && pwError) {
  form.addEventListener("submit", function (e) {
    pwError.textContent = "";

    if (!userPw.value.trim()) {
      e.preventDefault();
      pwError.textContent = "비밀번호를 입력해주세요.";
      userPw.focus();
      return;
    }
  });

  userPw.addEventListener("input", function () {
    pwError.textContent = "";
  });
}