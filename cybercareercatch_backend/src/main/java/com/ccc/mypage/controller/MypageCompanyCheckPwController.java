package com.ccc.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;

public class MypageCompanyCheckPwController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MypageCompanyCheckPwController 실행");

		Result result = new Result();

		// 비밀번호 확인 페이지로 이동
		result.setPath("/app/main/mypage/company-password-check.jsp");
		result.setRedirect(false);
		return result;
	}
}