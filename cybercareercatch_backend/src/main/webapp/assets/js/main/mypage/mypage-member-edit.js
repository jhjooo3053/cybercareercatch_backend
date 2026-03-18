document.addEventListener("DOMContentLoaded", () => {
  const memberMainUrl = `${contextPath}/mypage/member/main.my`;

  const phoneForm = document.getElementById("member-phone-form");
  const passwordForm = document.getElementById("member-password-form");

  const phoneInput = document.getElementById("member-phonenumber");
  const phoneMessage = document.getElementById("member-phonenumber-message");
  const sendPhoneBtn = document.getElementById("member-phonenumber-submit-btn");

  const authCodeInput = document.getElementById("member-verificationcode");
  const authMessage = document.getElementById("member-verificationcode-message");
  const authCheckBtn = document.getElementById("member-verificationcode-btn");

  const currentPwInput = document.getElementById("member-current-pw");
  const currentPwMessage = document.getElementById("member-current-pw-message");
  const currentPwCheckBtn = document.getElementById("currentpw-check-btn");

  const newPwInput = document.getElementById("member-change-pw");
  const newPwMessage = document.getElementById("member-change-pw-message");

  const newPwConfirmInput = document.getElementById("member-check-pw");
  const newPwConfirmMessage = document.getElementById("member-check-pw-message");
  const newPwCheckBtn = document.getElementById("changepw-check-btn");

  const infoCancelBtn = document.getElementById("mypage-infoeditcancel-btn");
  const pwCancelBtn = document.getElementById("mypage-pweditcancel-btn");

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

  function moveToMemberMainWithConfirm() {
    const isConfirmed = confirm(
      "수정하던 내용이 사라집니다.\n일반회원 마이페이지로 이동하시겠습니까?"
    );

    if (isConfirmed) {
      location.href = memberMainUrl;
    }
  }

  if (infoCancelBtn) {
    infoCancelBtn.addEventListener("click", moveToMemberMainWithConfirm);
  }

  if (pwCancelBtn) {
    pwCancelBtn.addEventListener("click", moveToMemberMainWithConfirm);
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
        const response = await fetch(`${contextPath}/mypage/member/check-password.ajax`, {
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