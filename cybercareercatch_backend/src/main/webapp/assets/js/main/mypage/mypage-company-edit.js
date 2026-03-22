document.addEventListener("DOMContentLoaded", function () {
	// 공통 메시지 출력 함수
	function showMessage(target, message, color = "red") {
		if (!target) return;
		target.textContent = message;
		target.style.color = color;
	}

	// 전화번호 검사
	function validatePhone(phone) {
		return /^010\d{8}$/.test(phone);
	}

	// 비밀번호 검사
	function validatePassword(password) {
		return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,20}$/.test(password);
	}

	// ====================
	// 전화번호 수정
	// ====================
	const phoneForm = document.getElementById("company-phone-form");
	const phoneInput = document.getElementById("company-manager-phonenumber");
	const phoneMsg = document.getElementById("company-phonenumber-message");

	const authInput = document.getElementById("company-verificationcode");
	const authMsg = document.getElementById("company-verificationcode-message");

	const sendBtn = document.getElementById("company-phonenumber-submit-btn");
	const authCheckBtn = document.getElementById("company-verificationcode-btn");
	const phoneCancelBtn = document.getElementById("mypage-editcancel-btn");

	let authCode = "";
	let phoneVerified = false;

	if (sendBtn) {
		sendBtn.addEventListener("click", function () {
			const phoneValue = phoneInput.value.trim();

			if (phoneValue === "") {
				showMessage(phoneMsg, "전화번호를 입력해주세요.");
				phoneVerified = false;
				return;
			}

			if (!validatePhone(phoneValue)) {
				showMessage(phoneMsg, "전화번호는 010으로 시작하는 11자리 숫자만 입력 가능합니다.");
				phoneVerified = false;
				return;
			}

			authCode = String(Math.floor(100000 + Math.random() * 900000));
			phoneVerified = false;

			showMessage(phoneMsg, "인증번호가 전송되었습니다.", "green");
			showMessage(authMsg, "");

			alert("테스트용 인증번호: " + authCode);
		});
	}

	if (authCheckBtn) {
		authCheckBtn.addEventListener("click", function () {
			const inputCode = authInput.value.trim();

			if (authCode === "") {
				showMessage(authMsg, "먼저 인증번호를 전송해주세요.");
				phoneVerified = false;
				return;
			}

			if (inputCode === "") {
				showMessage(authMsg, "인증번호를 입력해주세요.");
				phoneVerified = false;
				return;
			}

			if (inputCode === authCode) {
				showMessage(authMsg, "인증이 완료되었습니다.", "green");
				phoneVerified = true;
			} else {
				showMessage(authMsg, "인증번호가 일치하지 않습니다.");
				phoneVerified = false;
			}
		});
	}

	if (phoneInput) {
		phoneInput.addEventListener("input", function () {
			authCode = "";
			phoneVerified = false;

			if (authInput) {
				authInput.value = "";
			}

			showMessage(phoneMsg, "");
			showMessage(authMsg, "");
		});
	}

	if (phoneForm) {
		phoneForm.addEventListener("submit", function (e) {
			const phoneValue = phoneInput.value.trim();

			if (phoneValue === "") {
				e.preventDefault();
				showMessage(phoneMsg, "전화번호를 입력해주세요.");
				phoneInput.focus();
				return;
			}

			if (!validatePhone(phoneValue)) {
				e.preventDefault();
				showMessage(phoneMsg, "전화번호는 010으로 시작하는 11자리 숫자만 입력 가능합니다.");
				phoneInput.focus();
				return;
			}

			if (!phoneVerified) {
				e.preventDefault();
				showMessage(authMsg, "전화번호 인증을 완료해주세요.");
				authInput.focus();
				return;
			}
		});
	}

	if (phoneCancelBtn) {
		phoneCancelBtn.addEventListener("click", function () {
			const moveUrl = phoneCancelBtn.dataset.moveUrl;
			const isOk = confirm("수정 중인 내용이 저장되지 않을 수 있습니다.\n정말 취소하시겠습니까?");

			if (isOk && moveUrl) {
				location.href = moveUrl;
			}
		});
	}

	// ====================
	// 비밀번호 수정
	// ====================
	const pwForm = document.getElementById("company-password-form");

	const currentPwInput = document.getElementById("company-current-pw");
	const currentPwMsg = document.getElementById("company-current-pw-message");

	const newPwInput = document.getElementById("company-change-pw");
	const newPwMsg = document.getElementById("company-change-pw-message");

	const newPwConfirmInput = document.getElementById("company-check-pw");
	const newPwConfirmMsg = document.getElementById("company-check-pw-message");

	const pwCancelBtn = document.getElementById("mypage-pwcancel-btn");

	if (newPwInput) {
		newPwInput.addEventListener("input", function () {
			showMessage(newPwMsg, "");
			showMessage(newPwConfirmMsg, "");
		});
	}

	if (newPwConfirmInput) {
		newPwConfirmInput.addEventListener("input", function () {
			showMessage(newPwConfirmMsg, "");
		});
	}

	if (pwForm) {
		pwForm.addEventListener("submit", function (e) {
			const currentPw = currentPwInput.value.trim();
			const newPw = newPwInput.value.trim();
			const newPwConfirm = newPwConfirmInput.value.trim();

			showMessage(currentPwMsg, "");
			showMessage(newPwMsg, "");
			showMessage(newPwConfirmMsg, "");

			if (currentPw === "") {
				e.preventDefault();
				showMessage(currentPwMsg, "현재 비밀번호를 입력해주세요.");
				currentPwInput.focus();
				return;
			}

			if (newPw === "") {
				e.preventDefault();
				showMessage(newPwMsg, "변경할 비밀번호를 입력해주세요.");
				newPwInput.focus();
				return;
			}

			if (!validatePassword(newPw)) {
				e.preventDefault();
				showMessage(newPwMsg, "비밀번호는 영문, 숫자, 특수기호를 포함한 8~20자여야 합니다.");
				newPwInput.focus();
				return;
			}

			if (newPwConfirm === "") {
				e.preventDefault();
				showMessage(newPwConfirmMsg, "변경할 비밀번호 확인을 입력해주세요.");
				newPwConfirmInput.focus();
				return;
			}

			if (newPw !== newPwConfirm) {
				e.preventDefault();
				showMessage(newPwConfirmMsg, "변경할 비밀번호가 일치하지 않습니다.");
				newPwConfirmInput.focus();
				return;
			}
		});
	}

	if (pwCancelBtn) {
		pwCancelBtn.addEventListener("click", function () {
			const moveUrl = pwCancelBtn.dataset.moveUrl;
			const isOk = confirm("비밀번호 변경 내용을 취소하시겠습니까?");

			if (isOk && moveUrl) {
				location.href = moveUrl;
			}
		});
	}
});