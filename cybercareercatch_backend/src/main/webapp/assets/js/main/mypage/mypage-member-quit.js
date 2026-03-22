document.addEventListener("DOMContentLoaded", function () {
	const quitForm = document.getElementById("member-quit-form");
	const passwordInput = document.getElementById("member-quit-password");
	const confirmBtn = document.getElementById("confirm");
	const errorMessage = document.getElementById("pwError");
	const returnBtn = document.getElementById("return");

	function setMessage(message = "", color = "red") {
		if (!errorMessage) return;
		errorMessage.textContent = message;
		errorMessage.style.color = color;
	}

	// 확인 버튼
	if (confirmBtn) {
		confirmBtn.addEventListener("click", function () {
			const userPw = passwordInput.value.trim();

			if (userPw === "") {
				setMessage("비밀번호를 입력해주세요.");
				passwordInput.focus();
				return;
			}

			const isConfirmed = confirm("회원탈퇴를 진행하면 계정을 복구할 수 없습니다.\n정말 탈퇴하시겠습니까?");

			if (isConfirmed) {
				quitForm.submit();
			}
		});
	}

	// 입력 중 메시지 초기화
	if (passwordInput) {
		passwordInput.addEventListener("input", function () {
			setMessage("");
		});
	}

	// 돌아가기 버튼
	if (returnBtn) {
		returnBtn.addEventListener("click", function (e) {
			e.preventDefault();

			const moveUrl = returnBtn.dataset.moveUrl;

			if (!moveUrl) return;

			const isOk = confirm("회원탈퇴를 취소하고 마이페이지로 돌아가시겠습니까?");
			if (isOk) {
				location.href = moveUrl;
			}
		});
	}
});