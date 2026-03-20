package com.ccc.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.mypage.dao.MypageDAO;
import com.ccc.mypage.dto.MemberMypageInfoDTO;

public class MypageMemberQuitController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MypageMemberQuitPwController");
		Result result = new Result();
		HttpSession session = request.getSession();
		MypageDAO mypageDAO = new MypageDAO();
		
		//로그인 한 회원 정보 가져오기
		Integer userNumber = (Integer) session.getAttribute("userNumber");
		System.out.println("로그인한 회원 번호 : " + userNumber);
		String userType = (String) session.getAttribute("userType");
		System.out.println("로그인한 회원 타입 : " + userType);
		
		
		//결과를 저장할 MemberMypageInfoDTO 생성
		MemberMypageInfoDTO memberMypageInfoDTO = new MemberMypageInfoDTO();
		
		// 비로그인
		if (userNumber == null) {
			result.setPath(request.getContextPath() + "/member/login.mefc");
			result.setRedirect(true);
			return result;
		}

		// 일반회원이 아님
		if (!"일반회원".equals(userType)) {
			result.setPath(request.getContextPath() + "/main/main.mafc");
			result.setRedirect(true);
			return result;
		}
		
		memberMypageInfoDTO = mypageDAO.selectMemberMypageInfo(userNumber);

		result.setPath("/app/main/mypage/mypage-member-quit.jsp");
		result.setRedirect(false);
		return result;
	}

}
