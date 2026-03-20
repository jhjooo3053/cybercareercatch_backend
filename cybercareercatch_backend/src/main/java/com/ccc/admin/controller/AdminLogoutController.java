package com.ccc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;

/*
 * 관리자 로그아웃을 처리하는 컨트롤러이다.
 */
public class AdminLogoutController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* 이동 정보를 담을 객체를 생성한다. */
		Result result = new Result();

		/* 현재 세션을 가져온다. */
		HttpSession session = request.getSession();

		/* 세션이 존재하면 로그인 정보를 모두 삭제한다. */
		if (session != null) {
			session.invalidate();
		}

		/* 로그아웃 후 다시 관리자 로그인 페이지로 이동한다. */
		result.setPath(request.getContextPath() + "/admin/login.adfc");
		result.setRedirect(true);

		return result;
	}
}