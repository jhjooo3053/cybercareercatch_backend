const passwordCheckForm = document.getElementById("passwordCheckForm");
const userPwInput = document.getElementById("userPw");
const pwError = document.getElementById("pwError");

function showMessage(message, color = "red") {
	if (!pwError) return;
	pwError.textContent = message;
	pwError.style.color = color;
}

if (userPwInput) {
	userPwInput.addEventListener("input", function () {
		showMessage("");
	});
}

if (passwordCheckForm) {
	passwordCheckForm.addEventListener("submit", function (e) {
		const userPw = userPwInput.value.trim();

		if (userPw === "") {
			e.preventDefault();
			showMessage("비밀번호를 입력해주세요.");
			userPwInput.focus();
			return;
		}
	});
}