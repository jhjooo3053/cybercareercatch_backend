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

public class MypageMemberController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MypageMemberController 실행");

		MypageDAO mypageDAO = new MypageDAO();
		Result result = new Result();
		HttpSession session = request.getSession();

		Integer userNumber = (Integer) session.getAttribute("userNumber");
		String userType = (String) session.getAttribute("userType");
		
		//테스트용 - 삭제
		session.setAttribute("userNumber", 1);
		session.setAttribute("userType", "일반회원");
		
		System.out.println("로그인한 회원 번호 : " + userNumber);
		System.out.println("로그인한 회원 타입 : " + userType);
		
		
		// 비로그인
		if (userNumber == null) {
			result.setPath(request.getContextPath() + "/member/login.mefc");
			result.setRedirect(true);
			return result;
		}

		// 일반회원이 아님
		if (!"일반회원".equals(userType)) {
			result.setPath(request.getContextPath() + "/mainpage/mainpage.mafc");
			result.setRedirect(true);
			return result;
		}

		// 마이페이지 정보 조회
		MemberMypageInfoDTO memberMypageInfoDTO = mypageDAO.selectMemberMypageInfo(userNumber);

		// 조회결과가 없는 경우
		if (memberMypageInfoDTO == null) {
			result.setPath(request.getContextPath() + "/mainpage/mainpage.mafc");
			result.setRedirect(true);
			return result;
		}

		request.setAttribute("memberMypageInfoDTO", memberMypageInfoDTO);

		result.setPath("/app/main/mypage/mypage-member.jsp");
		result.setRedirect(false);

		return result;
	}
}