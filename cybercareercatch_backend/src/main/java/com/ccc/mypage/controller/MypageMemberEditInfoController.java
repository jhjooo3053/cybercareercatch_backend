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

public class MypageMemberEditInfoController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MypageMemberEditInfoController 실행");
		MypageDAO mypageDAO = new MypageDAO();
		Result result = new Result();
		HttpSession session = request.getSession();
		
		//로그인 정보 가져오기
		Integer userNumber = (Integer) session.getAttribute("userNumber");
		System.out.println("로그인한 회원 번호 : " + userNumber);
		String userType = (String) session.getAttribute("userType");
		System.out.println("로그인한 회원 타입 : " + userType);
		Boolean memberPwChecked = (Boolean) session.getAttribute("memberPwChecked");
		System.out.println("로그인한 비밀번호 확인 : " + memberPwChecked);
		
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
		
		if(memberPwChecked == null || !memberPwChecked) {
			result.setPath(request.getContextPath() + "/mypage/member/checkPw.mpfc");
			result.setRedirect(true);
			return result;
		}
		
		session.removeAttribute("memberPwChecked");
			
		//보여줄 아이디 조회
		MemberMypageInfoDTO memberMypageInfoDTO = new MemberMypageInfoDTO();
		memberMypageInfoDTO = mypageDAO.selectMemberMyPageInfo(userNumber);
		
		// 조회결과가 없는 경우
		if (memberMypageInfoDTO == null) {
			result.setPath(request.getContextPath() + "/main/main.mafc");
			result.setRedirect(true);
			return result;
		}
		
		request.setAttribute("memberMypageInfoDTO", memberMypageInfoDTO);
		
		result.setPath("/app/mypage/mypage-member-edit.jsp");
		result.setRedirect(false);
		
		return result;
	}

}
