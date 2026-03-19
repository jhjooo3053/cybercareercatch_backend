package com.ccc.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.mypage.dao.MypageDAO;

public class MypageMemberCheckPwOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MypageMemberCheckPwOkController 실행");
		MypageDAO mypageDAO = new MypageDAO();
		Result result = new Result();
		HttpSession session = request.getSession();
		
		//로그인 정보 확인
		Integer userNumber = (Integer) session.getAttribute("userNumber");
		System.out.println("로그인한 회원 번호 : " + userNumber);
		String userType = (String) session.getAttribute("userType");
		System.out.println("로그인한 회원 타입 : " + userType);
		
		// 비로그인
		if (userNumber == null) {
			result.setPath(request.getContextPath() + "/member/login.mpfc");
			result.setRedirect(true);
			return result;
		}

		// 일반회원 아님
		if (!"일반회원".equals(userType)) {
			result.setPath(request.getContextPath() + "/main/main.mafc");
			result.setRedirect(true);
			return result;
		}
		
		//클라이언트에서 전달받은 pw
		String userPw = request.getParameter("userPw");
		
		if (userPw == null || userPw.trim().isEmpty()) {
			request.setAttribute("passwordError", "비밀번호를 입력해주세요.");
			result.setPath("/app/mypage/member-password-check.jsp");
			result.setRedirect(false);
			return result;
		}
		
		System.out.println("입력받은 비밀번호 : " + userPw);
		
		//비밀번호 일치 여부 확인
		boolean isCheck = mypageDAO.checkMemberPw(userNumber, userPw);
		
		if(isCheck) {
			session.setAttribute("memberPwChecked", true);
			result.setPath(request.getContextPath() + "/mypage/member/edit.mpfc");
			result.setRedirect(true);
			return result;
		}
		
		//비밀번호 불일치
		request.setAttribute("passwordError", "비밀번호가 일치하지 않습니다.");
		result.setPath("/app/mypage/member-password-check.jsp");
		result.setRedirect(false);
		return result;

		
	}

}
