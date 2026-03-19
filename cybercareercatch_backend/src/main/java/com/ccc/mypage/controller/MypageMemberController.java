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

public class MypageMemberController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MyPageController 실행");
		MypageDAO mypageDAO = new MypageDAO();
		Result result = new Result();
		HttpSession session = request.getSession();
		
		//로그인 한 회원 정보 가져오기
		Integer userNumber = (Integer) session.getAttribute("userNumber");
		System.out.println("로그인한 회원 번호 : " + userNumber);
		String userType = (String) session.getAttribute("userType");
		System.out.println("로그인한 회원 타입 : " + userType);
		
		
		//결과를 저장할 MemberMypageInfoDTO 생성
		MemberMypageInfoDTO memberMypageInfoDTO = new MemberMypageInfoDTO();

		// 비회원인경우
		if (userNumber == null) {
			result.setPath(request.getContextPath() + "/member/login.mpfc");
			result.setRedirect(true);
			return result;
		}

		// 일반회원이 아닌경우
		if (!"일반회원".equals(userType)) {
			result.setPath(request.getContextPath() + "/main/main.mafc");
			result.setRedirect(true);
			return result;
		}

		memberMypageInfoDTO = mypageDAO.selectMemberMyPageInfo(userNumber);

		// 조회결과가 없는 경우
		if (memberMypageInfoDTO == null) {
			result.setPath(request.getContextPath() + "/main/main.mafc");
			result.setRedirect(true);
			return result;
		}
		
		request.setAttribute("memberMypageInfoDTO", memberMypageInfoDTO);
		
		result.setPath("/app/mypage/mypage-member.jsp");
		result.setRedirect(false);
		
		return result;
	}

}
