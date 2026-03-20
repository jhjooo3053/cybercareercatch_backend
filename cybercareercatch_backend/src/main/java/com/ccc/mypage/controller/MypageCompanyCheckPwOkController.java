package com.ccc.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.mypage.dao.MypageDAO;

public class MypageCompanyCheckPwOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MypageCompanyCheckPwOkController 실행");

		Result result = new Result();
		HttpSession session = request.getSession();
		MypageDAO mypageDAO = new MypageDAO();

		// 로그인 정보 확인
		Integer userNumber = (Integer) session.getAttribute("userNumber");
		String userType = (String) session.getAttribute("userType");

		System.out.println("로그인한 회원 번호 : " + userNumber);
		System.out.println("로그인한 회원 타입 : " + userType);

		// 비로그인
		if (userNumber == null) {
			result.setPath(request.getContextPath() + "/member/login.mefc");
			result.setRedirect(true);
			return result;
		}

		// 기업회원 아님
		if (!"기업회원".equals(userType)) {
			result.setPath(request.getContextPath() + "/main/main.mafc");
			result.setRedirect(true);
			return result;
		}

		String userPw = request.getParameter("userPw");

		// 공백 체크
		if (userPw == null || userPw.trim().isEmpty()) {
			request.setAttribute("passwordError", "비밀번호를 입력해주세요.");
			result.setPath("/app/main/mypage/company-password-check.jsp");
			result.setRedirect(false);
			return result;
		}

		userPw = userPw.trim();

		// 비밀번호 확인
		boolean isCorrectPw = mypageDAO.checkCompanyPw(userNumber, userPw);

		if (!isCorrectPw) {
			request.setAttribute("passwordError", "비밀번호가 일치하지 않습니다.");
			result.setPath("/app/main/mypage/company-password-check.jsp");
			result.setRedirect(false);
			return result;
		}

		// 확인 완료 세션 저장
		session.setAttribute("companyPwChecked", true);

		// 기업회원 정보수정 페이지로 이동
		result.setPath(request.getContextPath() + "/company/mypage/editInfo.mpfc");
		result.setRedirect(true);
		return result;
	}
}