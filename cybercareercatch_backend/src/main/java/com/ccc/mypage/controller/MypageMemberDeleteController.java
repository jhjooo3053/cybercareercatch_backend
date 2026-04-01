package com.ccc.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.mypage.dao.MypageDAO;

public class MypageMemberDeleteController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("MypageMemberDeleteController 실행");

		MypageDAO mypageDAO = new MypageDAO();
		Result result = new Result();
		HttpSession session = request.getSession();

		Integer userNumber = (Integer) session.getAttribute("userNumber");

		String userPw = request.getParameter("userPw");

		// 비밀번호 공백 체크
		if (userPw == null || userPw.trim().isEmpty()) {
			request.setAttribute("pwMessage", "비밀번호를 입력해주세요.");
			result.setPath("/app/main/mypage/mypage-member-quit.jsp");
			result.setRedirect(false);
			return result;
		}

		userPw = userPw.trim();

		// 비밀번호 확인
		boolean isCorrectPw = mypageDAO.checkMemberPw(userNumber, userPw);

		if (!isCorrectPw) {
			request.setAttribute("pwMessage", "비밀번호가 일치하지 않습니다.");
			result.setPath("/app/main/mypage/mypage-member-quit.jsp");
			result.setRedirect(false);
			return result;
		}

		// 회원 탈퇴
		mypageDAO.deleteCommentsByUserNumber(userNumber);
		mypageDAO.deletePostsByUserNumber(userNumber);
		mypageDAO.deleteMemberByUserNumber(userNumber);
		
		// 세션 무효화
		session.invalidate();

		// 메인페이지 또는 로그인페이지로 이동
		result.setPath(request.getContextPath() + "/mainpage/mainpage.mafc");
		result.setRedirect(true);

		return result;
	}
}