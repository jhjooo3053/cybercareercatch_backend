package com.ccc.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.mypage.dao.MypageDAO;

public class MypageMemberEditPhonenumController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MypageMemberEditPhonenumController 실행");
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
			result.setPath(request.getContextPath() + "/member/login.mefc");
			result.setRedirect(true);
			return result;
		}

		// 일반회원 아님
		if (!"일반회원".equals(userType)) {
			result.setPath(request.getContextPath() + "/main/main.mafc");
			result.setRedirect(true);
			return result;
		}
		
		String newPhone = request.getParameter("userPhone");
		
		//전화번호 공백 체크
		if( newPhone == null || newPhone.trim().isEmpty()) {
			request.setAttribute("phoneMessage", "전화번호를 입력해주세요");
			request.setAttribute("memberMypageInfoDTO", mypageDAO.selectMemberMypageInfo(userNumber));
			result.setPath("/app/main/mypage/mypage-member-edit.jsp");
			result.setRedirect(false);
			return result;
		}	
		
		String authCode = request.getParameter("authCode");
		
		//인증번호 공백 체크
		if (authCode == null || authCode.trim().isEmpty()) {
		    request.setAttribute("authMessage", "인증번호를 입력해주세요.");
		    request.setAttribute("memberMypageInfoDTO", mypageDAO.selectMemberMypageInfo(userNumber));
		    result.setPath("/app/main/mypage/mypage-member-edit.jsp");
		    result.setRedirect(false);
		    return result;
		}
		
		mypageDAO.updateMemberPhone(userNumber, newPhone);
		System.out.println("변경할 전화번호 " + newPhone);
		result.setPath(request.getContextPath() + "/member/mypage.mpfc");
		result.setRedirect(true);
		
		
		return result;
	}

}
