package com.ccc.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;

public class MypageCompanyQuitController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MypageCompanyQuitController 실행");

		Result result = new Result();

		// 기업회원 탈퇴 불가 안내 페이지로 이동
		result.setRedirect(false);
		result.setPath("/app/main/mypage/mypage-company-quit.jsp");

		return result;
	}
}