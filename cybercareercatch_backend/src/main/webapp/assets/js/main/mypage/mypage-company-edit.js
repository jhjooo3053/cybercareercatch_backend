document.addEventListener("DOMContentLoaded", () => {
  const companyMainUrl = `${contextPath}/mypage/company/main.my`;

  const phoneForm = document.getElementById("company-phone-form");
  const passwordForm = document.getElementById("company-password-form");

  const phoneInput = document.getElementById("company-manager-phonenumber");
  const phoneMessage = document.getElementById("company-phonenumber-message");
  const sendPhoneBtn = document.getElementById("company-phonenumber-submit-btn");

  const authCodeInput = document.getElementById("company-verificationcode");
  const authMessage = document.getElementById("company-verificationcode-message");
  const authCheckBtn = document.getElementById("company-verificationcode-btn");

  const currentPwInput = document.getElementById("company-current-pw");
  const currentPwMessage = document.getElementById("company-current-pw-message");
  const currentPwCheckBtn = document.getElementById("company-currentpwcheck-btn");

  const newPwInput = document.getElementById("company-change-pw");
  const newPwMessage = document.getElementById("company-change-pw-message");

  const newPwConfirmInput = document.getElementById("company-check-pw");
  const newPwConfirmMessage = document.getElementById("company-check-pw-message");
  const newPwCheckBtn = document.getElementById("company-changepwcheck-btn");

  const editCancelBtn = document.getElementById("mypage-editcancel-btn");
  const pwCancelBtn = document.getElementById("mypage-pwcancel-btn");

  let isPhoneVerified = false;
  let isCurrentPwVerified = false;

  const passwordRegex =
    /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_\-+=\[{\]};:'",.<>/?\\|`~]).{8,20}$/;

  function setMessage(element, message, type = "") {
    if (!element) return;

    element.textContent = message;
    element.classList.remove("error", "success");

    if (type) {
      element.classList.add(type);
    }
  }

  function isValidPhone(phone) {
    return /^010\d{8}$/.test(phone);
  }

  function isValidPassword(password) {
    return passwordRegex.test(password);
  }

  function checkPasswordMatch() {
    const newPw = newPwInput.value.trim();
    const confirmPw = newPwConfirmInput.value.trim();

    if (!newPw) {
      setMessage(newPwMessage, "변경할 비밀번호를 입력해주세요.", "error");
      setMessage(newPwConfirmMessage, "");
      return false;
    }

    if (!isValidPassword(newPw)) {
      setMessage(
        newPwMessage,
        "비밀번호는 영문, 숫자, 특수기호를 포함한 8자 이상 20자 이하로 입력해주세요.",
        "error"
      );
      setMessage(newPwConfirmMessage, "");
      return false;
    }

    setMessage(newPwMessage, "사용 가능한 비밀번호 형식입니다.", "success");

    if (!confirmPw) {
      setMessage(newPwConfirmMessage, "변경할 비밀번호 확인을 입력해주세요.", "error");
      return false;
    }

    if (newPw !== confirmPw) {
      setMessage(newPwConfirmMessage, "비밀번호가 일치하지 않습니다.", "error");
      return false;
    }

    setMessage(newPwConfirmMessage, "비밀번호가 일치합니다.", "success");
    return true;
  }

  function moveToCompanyMainWithConfirm() {
    const isConfirmed = confirm(
      "수정하던 내용이 사라집니다.\n기업회원 마이페이지로 이동하시겠습니까?"
    );

    if (isConfirmed) {
      location.href = companyMainUrl;
    }
  }

  if (editCancelBtn) {
    editCancelBtn.addEventListener("click", moveToCompanyMainWithConfirm);
  }

  if (pwCancelBtn) {
    pwCancelBtn.addEventListener("click", moveToCompanyMainWithConfirm);
  }

  if (phoneInput) {
    phoneInput.addEventListener("input", () => {
      isPhoneVerified = false;
      setMessage(phoneMessage, "");
      setMessage(authMessage, "");
    });
  }

  if (currentPwInput) {
    currentPwInput.addEventListener("input", () => {
      isCurrentPwVerified = false;
      setMessage(currentPwMessage, "");
    });
  }

  if (sendPhoneBtn) {
    sendPhoneBtn.addEventListener("click", async () => {
      const phone = phoneInput.value.trim();

      if (!phone) {
        setMessage(phoneMessage, "전화번호를 입력해주세요.", "error");
        phoneInput.focus();
        return;
      }

      if (!isValidPhone(phone)) {
        setMessage(phoneMessage, "전화번호는 01012345678 형식으로 입력해주세요.", "error");
        phoneInput.focus();
        return;
      }

      try {
        const response = await fetch(`${contextPath}/sms/send.my`, {
          method: "POST",
          headers: {
            "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
          },
          body: new URLSearchParams({
            userPhone: phone
          })
        });

        const result = await response.json();

        if (result.success) {
          isPhoneVerified = false;
          setMessage(phoneMessage, result.message || "인증번호가 전송되었습니다.", "success");
        } else {
          setMessage(phoneMessage, result.message || "인증번호 전송에 실패했습니다.", "error");
        }
      } catch (error) {
        setMessage(phoneMessage, "문자 전송 중 오류가 발생했습니다.", "error");
        console.error(error);
      }
    });
  }

  if (authCheckBtn) {
    authCheckBtn.addEventListener("click", async () => {
      const phone = phoneInput.value.trim();
      const authCode = authCodeInput.value.trim();

      if (!isValidPhone(phone)) {
        setMessage(authMessage, "전화번호는 01012345678 형식으로 입력해주세요.", "error");
        phoneInput.focus();
        return;
      }

      if (!authCode) {
        setMessage(authMessage, "인증번호를 입력해주세요.", "error");
        authCodeInput.focus();
        return;
      }

      try {
        const response = await fetch(`${contextPath}/sms/verify.my`, {
          method: "POST",
          headers: {
            "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
          },
          body: new URLSearchParams({
            userPhone: phone,
            authCode: authCode
          })
        });

        const result = await response.json();

        if (result.success) {
          isPhoneVerified = true;
          setMessage(authMessage, result.message || "인증이 완료되었습니다.", "success");
        } else {
          isPhoneVerified = false;
          setMessage(authMessage, result.message || "인증번호가 올바르지 않습니다.", "error");
        }
      } catch (error) {
        isPhoneVerified = false;
        setMessage(authMessage, "인증 확인 중 오류가 발생했습니다.", "error");
        console.error(error);
      }
    });
  }

  if (currentPwCheckBtn) {
    currentPwCheckBtn.addEventListener("click", async () => {
      const currentPw = currentPwInput.value.trim();

      if (!currentPw) {
        setMessage(currentPwMessage, "현재 비밀번호를 입력해주세요.", "error");
        currentPwInput.focus();
        return;
      }

      try {
        const response = await fetch(`${contextPath}/mypage/company/check-password.ajax`, {
          method: "POST",
          headers: {
            "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
          },
          body: new URLSearchParams({
            userPw: currentPw
          })
        });

        const result = await response.json();

        if (result.success) {
          isCurrentPwVerified = true;
          setMessage(currentPwMessage, result.message || "현재 비밀번호가 확인되었습니다.", "success");
        } else {
          isCurrentPwVerified = false;
          setMessage(currentPwMessage, result.message || "현재 비밀번호가 일치하지 않습니다.", "error");
        }
      } catch (error) {
        isCurrentPwVerified = false;
        setMessage(currentPwMessage, "비밀번호 확인 중 오류가 발생했습니다.", "error");
        console.error(error);
      }
    });
  }

  if (newPwInput) {
    newPwInput.addEventListener("input", checkPasswordMatch);
  }

  if (newPwConfirmInput) {
    newPwConfirmInput.addEventListener("input", checkPasswordMatch);
  }

  if (newPwCheckBtn) {
    newPwCheckBtn.addEventListener("click", checkPasswordMatch);
  }

  if (phoneForm) {
    phoneForm.addEventListener("submit", (e) => {
      const phone = phoneInput.value.trim();
      const authCode = authCodeInput.value.trim();

      if (!isValidPhone(phone)) {
        e.preventDefault();
        setMessage(phoneMessage, "전화번호는 01012345678 형식으로 입력해주세요.", "error");
        phoneInput.focus();
        return;
      }

      if (!authCode) {
        e.preventDefault();
        setMessage(authMessage, "인증번호를 입력해주세요.", "error");
        authCodeInput.focus();
        return;
      }

      if (!isPhoneVerified) {
        e.preventDefault();
        setMessage(authMessage, "인증번호 확인을 완료해주세요.", "error");
        authCodeInput.focus();
      }
    });
  }

  if (passwordForm) {
    passwordForm.addEventListener("submit", (e) => {
      const currentPw = currentPwInput.value.trim();
      const isMatched = checkPasswordMatch();

      if (!currentPw) {
        e.preventDefault();
        setMessage(currentPwMessage, "현재 비밀번호를 입력해주세요.", "error");
        currentPwInput.focus();
        return;
      }

      if (!isCurrentPwVerified) {
        e.preventDefault();
        setMessage(currentPwMessage, "현재 비밀번호 확인을 먼저 해주세요.", "error");
        currentPwInput.focus();
        return;
      }

      if (!isMatched) {
        e.preventDefault();
        newPwConfirmInput.focus();
      }
    });
  }
});