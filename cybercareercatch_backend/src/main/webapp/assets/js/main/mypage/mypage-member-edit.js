// HTML 문서가 전부 로드된 뒤 실행
document.addEventListener("DOMContentLoaded", function () {

  // 전화번호 수정 폼 가져오기
  const phoneForm = document.getElementById("member-phone-form");

  // 전화번호 관련 요소 가져오기
  const sendSMSBtn = document.getElementById("member-phonenumber-submit-btn");   // 전송 버튼
  const phoneNumberInput = document.getElementById("member-phonenumber");        // 전화번호 입력창
  const phoneMessage = document.getElementById("member-phonenumber-message");    // 전화번호 메시지 출력 영역

  // 인증번호 관련 요소 가져오기
  const verificationCodeInput = document.getElementById("member-verificationcode");          // 인증번호 입력창
  const verificationCodeMessage = document.getElementById("member-verificationcode-message"); // 인증번호 메시지 출력 영역
  const verificationCodeBtn = document.getElementById("member-verificationcode-btn");         // 인증번호 확인 버튼

  // 취소 버튼 가져오기
  const cancelBtn = document.getElementById("mypage-infoeditcancel-btn");

  // 전송된 인증번호를 저장할 변수
  let generatedCode = "";

  // 전화번호 인증 완료 여부 저장
  let isPhoneVerified = false;

  // 전화번호 메시지를 화면에 출력하는 함수
  function showPhoneMessage(message, color = "red") {
    phoneMessage.textContent = message;
    phoneMessage.style.color = color;
  }

  // 인증번호 메시지를 화면에 출력하는 함수
  function showAuthMessage(message, color = "red") {
    verificationCodeMessage.textContent = message;
    verificationCodeMessage.style.color = color;
  }

  // 전화번호 형식 검사 함수
  // 01012345678 같은 형식만 통과
  function validatePhone(phone) {
    const phoneRegex = /^01[016789]\d{7,8}$/;
    return phoneRegex.test(phone);
  }

  // [전송] 버튼 클릭 시 실행
  sendSMSBtn.addEventListener("click", function () {
    // 입력한 전화번호 값 가져오기 (앞뒤 공백 제거)
    const phoneValue = phoneNumberInput.value.trim();

    // 전화번호를 입력하지 않았을 때
    if (phoneValue === "") {
      showPhoneMessage("전화번호를 입력해주세요.");
      isPhoneVerified = false;
      return;
    }

    // 전화번호 형식이 올바르지 않을 때
    if (!validatePhone(phoneValue)) {
      showPhoneMessage("올바른 전화번호 형식이 아닙니다.");
      isPhoneVerified = false;
      return;
    }

    // 실제 문자 발송 대신 임시 인증번호 6자리 생성
    generatedCode = String(Math.floor(100000 + Math.random() * 900000));

    // 아직 인증 완료는 아니므로 false
    isPhoneVerified = false;

    // 사용자에게 안내 메시지 출력
    showPhoneMessage("인증번호가 전송되었습니다.", "green");

    // 기존 인증번호 메시지 초기화
    showAuthMessage("");

    // 테스트용: 콘솔과 alert로 인증번호 확인
    console.log("테스트용 인증번호:", generatedCode);
    alert("테스트용 인증번호: " + generatedCode);
  });

  // [확인] 버튼 클릭 시 실행
  verificationCodeBtn.addEventListener("click", function () {
    // 사용자가 입력한 인증번호 값 가져오기
    const inputCode = verificationCodeInput.value.trim();

    // 아직 인증번호를 전송하지 않았을 때
    if (generatedCode === "") {
      showAuthMessage("먼저 인증번호를 전송해주세요.");
      isPhoneVerified = false;
      return;
    }

    // 인증번호를 입력하지 않았을 때
    if (inputCode === "") {
      showAuthMessage("인증번호를 입력해주세요.");
      isPhoneVerified = false;
      return;
    }

    // 입력한 인증번호와 발급된 인증번호가 같을 때
    if (inputCode === generatedCode) {
      showAuthMessage("인증이 완료되었습니다.", "green");
      isPhoneVerified = true;
    } else {
      // 인증번호가 다를 때
      showAuthMessage("인증번호가 일치하지 않습니다.");
      isPhoneVerified = false;
    }
  });

  // 전화번호 입력값이 바뀌면 다시 인증받도록 초기화
  phoneNumberInput.addEventListener("input", function () {
    // 전화번호가 바뀌면 기존 인증은 무효 처리
    isPhoneVerified = false;
    generatedCode = "";

    // 메시지 초기화
    showPhoneMessage("");
    showAuthMessage("");

    // 인증번호 입력창도 비우기
    verificationCodeInput.value = "";
  });

  // 폼 제출(수정 버튼 클릭) 시 실행
  phoneForm.addEventListener("submit", function (e) {
    // 입력한 전화번호 값 가져오기
    const phoneValue = phoneNumberInput.value.trim();

    // 전화번호가 비어 있으면 제출 막기
    if (phoneValue === "") {
      e.preventDefault();
      showPhoneMessage("전화번호를 입력해주세요.");
      phoneNumberInput.focus();
      return;
    }

    // 전화번호 형식이 틀리면 제출 막기
    if (!validatePhone(phoneValue)) {
      e.preventDefault();
      showPhoneMessage("올바른 전화번호 형식이 아닙니다.");
      phoneNumberInput.focus();
      return;
    }

    // 인증이 완료되지 않았으면 제출 막기
    if (!isPhoneVerified) {
      e.preventDefault();
      showAuthMessage("전화번호 인증을 완료해주세요.");
      verificationCodeInput.focus();
      return;
    }
  });

  // [취소] 버튼 클릭 시 확인창 띄운 후 이동
  cancelBtn.addEventListener("click", function () {
    const isCancel = confirm("수정 중인 내용이 저장되지 않을 수 있습니다. 취소하시겠습니까?");

    if (isCancel) {
      location.href = contextPath + "/member/mypage.mpfc";
    }
  });

});