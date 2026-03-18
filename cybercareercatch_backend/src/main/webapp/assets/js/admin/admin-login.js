document.addEventListener("DOMContentLoaded", function () {
    // 관리자 로그인 아이디 입력창이다.
    const adminIdInput = document.querySelector("#adminId");

    // 관리자 로그인 비밀번호 입력창이다.
    const adminPwInput = document.querySelector("#adminPw");

    // 로그인 폼이다.
    const loginForm = document.querySelector(".login-box");

    // 로그인 폼이 없으면 아래 코드를 실행하지 않는다.
    if (!loginForm) {
        return;
    }

    // 로그인 폼 제출 시 유효성 검사를 한다.
    loginForm.addEventListener("submit", function (e) {
        // 아이디가 비어 있으면 제출을 막는다.
        if (!adminIdInput.value.trim()) {
            alert("아이디를 입력하세요.");
            adminIdInput.focus();
            e.preventDefault();
            return;
        }

        // 비밀번호가 비어 있으면 제출을 막는다.
        if (!adminPwInput.value.trim()) {
            alert("비밀번호를 입력하세요.");
            adminPwInput.focus();
            e.preventDefault();
            return;
        }
    });
});