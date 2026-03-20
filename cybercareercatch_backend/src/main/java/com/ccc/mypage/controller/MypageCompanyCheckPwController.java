package com.ccc.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;

public class MypageCompanyCheckPwController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MypageCompanyCheckPwController 실행");

		Result result = new Result();
		HttpSession session = request.getSession();

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

		// 비밀번호 확인 페이지로 이동
		result.setPath("/app/main/mypage/company-password-check.jsp");
		result.setRedirect(false);
		return result;
	}
}