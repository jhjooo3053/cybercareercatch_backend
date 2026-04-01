const sendSmsBtn = document.getElementById('sendSmsBtn');
const verifyAuthBtn = document.getElementById('verifyAuthBtn');
const confirmPwBtn = document.getElementById('confirmPwBtn');

const phoneErrorMsg = document.getElementById('phoneErrorMsg');
const phoneSuccessMsg = document.getElementById('phoneSuccessMsg');
const authErrorMsg = document.getElementById('authErrorMsg');
const authSuccessMsg = document.getElementById('authSuccessMsg');
const pwMatchErrorMsg = document.getElementById('pwMatchErrorMsg');
const newPasswordSection = document.getElementById('newPasswordSection');

const pwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[^A-Za-z0-9])\S{8,20}$/;

[phoneErrorMsg, phoneSuccessMsg, authErrorMsg, authSuccessMsg, pwMatchErrorMsg]
	.forEach(el => el.style.display = 'none');

let smsVerified = false;
let verifiedUserId = null;

sendSmsBtn.addEventListener('click', () => {
	const userId = document.getElementById('findId').value.trim();
	const phone = document.getElementById('findPhone').value.trim();

	phoneErrorMsg.style.display = 'none';
	phoneSuccessMsg.style.display = 'none';

	if (!userId || !phone) {
		phoneErrorMsg.textContent = '아이디와 전화번호를 모두 입력해 주세요.';
		phoneErrorMsg.style.display = 'block';
		return;
	}

	fetch(`${base}/member/findPw.mefc?userId=${encodeURIComponent(userId)}&phoneNumber=${encodeURIComponent(phone)}`,
		{ headers: { 'Accept': 'application/json' } }
	)
		.then(res => {
			if (!res.ok) throw new Error();
			return res.json();
		})
		.then(data => {
			if (!data.exists) {
				phoneErrorMsg.textContent = '아이디 또는 전화번호가 잘못 되었습니다. 정확히 입력해 주세요.';
				phoneErrorMsg.style.display = 'block';
				return;
			}

			verifiedUserId = userId;

			fetch(`${base}/member/sendSMS.mefc`, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded',
					'X-Requested-With': 'XMLHttpRequest'
				},
				body: new URLSearchParams({ phoneNumber: phone })
			})
				.then(res => {
					if (!res.ok) throw new Error('SMS 발송 실패');
					return res.text();
				})
				.then(() => {
					phoneSuccessMsg.style.display = 'block';
				})
				.catch(() => {
					phoneErrorMsg.textContent = 'SMS 전송에 실패했습니다.';
					phoneErrorMsg.style.display = 'block';
				});
		})
		.catch(() => {
			phoneErrorMsg.textContent = '서버 오류가 발생했습니다.';
			phoneErrorMsg.style.display = 'block';
		});
});

verifyAuthBtn.addEventListener('click', () => {
	const code = document.getElementById('authCode').value.trim();

	authErrorMsg.style.display = 'none';
	authSuccessMsg.style.display = 'none';

	if (!verifiedUserId) {
		authErrorMsg.textContent = '먼저 전송 버튼을 눌러 인증번호를 받아주세요.';
		authErrorMsg.style.display = 'block';
		return;
	}
	if (!code) {
		authErrorMsg.textContent = '인증번호를 입력해 주세요.';
		authErrorMsg.style.display = 'block';
		return;
	}

	fetch(`${base}/member/verifyCode.mefc`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json; charset=utf-8',
			'Accept': 'application/json'
		},
		body: JSON.stringify({ code: code })
	})
		.then(res => {
			if (!res.ok) throw new Error();
			return res.json();
		})
		.then(data => {
			if (data.success) {
				smsVerified = true;
				authSuccessMsg.style.display = 'block';
				newPasswordSection.classList.remove('hidden-section');
			} else {
				authErrorMsg.textContent = '인증번호가 올바르지 않습니다.';
				authErrorMsg.style.display = 'block';
			}
		})
		.catch(() => {
			authErrorMsg.textContent = '서버 오류가 발생했습니다.';
			authErrorMsg.style.display = 'block';
		});
});

confirmPwBtn.addEventListener('click', () => {
	pwMatchErrorMsg.style.display = 'none';

	if (!smsVerified) {
		alert('먼저 인증을 완료해 주세요.');
		return;
	}

	const newPw = document.getElementById('newPw').value.trim();
	const newPwConfirm = document.getElementById('newPwConfirm').value.trim();

	if (!newPw || !newPwConfirm) {
		pwMatchErrorMsg.textContent = '비밀번호를 입력해 주세요.';
		pwMatchErrorMsg.style.display = 'block';
		return;
	}
	if (!pwRegex.test(newPw)) {
		pwMatchErrorMsg.textContent = '비밀번호는 영문, 숫자, 특수문자 포함 8자 이상이어야 합니다.';
		pwMatchErrorMsg.style.display = 'block';
		return;
	}
	if (newPw !== newPwConfirm) {
		pwMatchErrorMsg.textContent = '비밀번호가 일치하지 않습니다.';
		pwMatchErrorMsg.style.display = 'block';
		return;
	}

	fetch(`${base}/member/findPwOk.mefc`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded',
			'Accept': 'application/json'
		},
		body: `userId=${encodeURIComponent(verifiedUserId)}&newPw=${encodeURIComponent(newPw)}`
	})
		.then(res => res.json())
		.then(data => {
			if (data.success) {
				alert('비밀번호가 변경되었습니다. 로그인 페이지로 이동합니다.');
				window.location.href = `${base}/member/login.mefc`;
			} else {
				alert('비밀번호 변경 중 오류가 발생했습니다.');
			}
		})
		.catch(() => {
			alert('서버 오류가 발생했습니다.');
		});
});

function checkPwMatch() {
	const newPw = document.getElementById('newPw').value.trim();
	const newPwConfirm = document.getElementById('newPwConfirm').value.trim();

	if (pwRegex.test(newPw) && newPw === newPwConfirm && newPw.length > 0) {
		confirmPwBtn.disabled = false;
		pwMatchErrorMsg.style.display = 'none';
	} else {
		confirmPwBtn.disabled = true;
		if (newPwConfirm.length > 0) {
			pwMatchErrorMsg.style.display = 'block';
			pwMatchErrorMsg.textContent = newPw !== newPwConfirm
				? '비밀번호가 일치하지 않습니다.'
				: '비밀번호는 영문, 숫자, 특수문자 포함 8자 이상이어야 합니다.';
		}
	}
}

document.getElementById('newPw').addEventListener('input', checkPwMatch);
document.getElementById('newPwConfirm').addEventListener('input', checkPwMatch);