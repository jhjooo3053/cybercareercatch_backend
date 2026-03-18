document.addEventListener("DOMContentLoaded", () => {
  const quitForm = document.getElementById("member-quit-form");
  const passwordInput = document.getElementById("member-quit-password");
  const confirmBtn = document.getElementById("confirm");
  const errorMessage = document.getElementById("pwError");

  function setMessage(message = "", type = "") {
    errorMessage.textContent = message;
    errorMessage.classList.remove("error", "success");

    if (type) {
      errorMessage.classList.add(type);
    }
  }

  confirmBtn.addEventListener("click", async () => {
    const userPw = passwordInput.value.trim();

    if (!userPw) {
      setMessage("비밀번호를 입력해주세요.", "error");
      passwordInput.focus();
      return;
    }

    try {
      const response = await fetch(`${contextPath}/mypage/member/check-password.ajax`, {
        method: "POST",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
        },
        body: new URLSearchParams({
          userPw: userPw
        })
      });

      const result = await response.json();

      if (!result.success) {
        setMessage(result.message || "비밀번호가 일치하지 않습니다.", "error");
        passwordInput.focus();
        return;
      }

      const isConfirmed = confirm("회원탈퇴를 진행하면 계정을 복구할 수 없습니다.\n정말 탈퇴하시겠습니까?");

      if (isConfirmed) {
        quitForm.submit();
      }
    } catch (error) {
      setMessage("비밀번호 확인 중 오류가 발생했습니다.", "error");
      console.error(error);
    }
  });

  passwordInput.addEventListener("input", () => {
    setMessage("");
  });
});