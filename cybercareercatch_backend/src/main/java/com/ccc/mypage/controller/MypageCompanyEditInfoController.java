package com.ccc.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.mypage.dao.MypageDAO;
import com.ccc.mypage.dto.CompanyMypageInfoDTO;

public class MypageCompanyEditInfoController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MypageCompanyEditInfoController 실행");

		MypageDAO mypageDAO = new MypageDAO();
		Result result = new Result();
		HttpSession session = request.getSession();

		Integer userNumber = (Integer) session.getAttribute("userNumber");
		String userType = (String) session.getAttribute("userType");
		
		//테스트용 - 삭제
		session.setAttribute("userNumber", 51);
		session.setAttribute("userType", "기업회원");
		
		System.out.println("로그인한 회원 번호 : " + userNumber);
		System.out.println("로그인한 회원 타입 : " + userType);
		
		// 로그인 안 된 경우
		if (userNumber == null) {
			result.setRedirect(true);
			result.setPath(request.getContextPath() + "/member/login.mefc");
			return result;
		}

		// 기업회원이 아니면 접근 불가
		if (userType == null || !userType.equals("기업회원")) {
			result.setRedirect(true);
			result.setPath(request.getContextPath() + "/mainpage/mainpage.mafc");
			return result;
		}

		// 비밀번호 확인 안 했으면 다시 비밀번호 확인 페이지로
		Boolean companyPwChecked = (Boolean) session.getAttribute("companyPwChecked");
		if (companyPwChecked == null || !companyPwChecked) {
			result.setPath(request.getContextPath() + "/company/mypage/checkPw.mpfc");
			result.setRedirect(true);
			return result;
		}

		// 기업회원 기본정보 조회
		CompanyMypageInfoDTO companyMypageInfoDTO = mypageDAO.selectCompanyMemberMypageInfo(userNumber);

		// 조회 실패 시 마이페이지로 돌려보내기
		if (companyMypageInfoDTO == null) {
			result.setPath(request.getContextPath() + "/company/mypage.mpfc");
			result.setRedirect(true);
			return result;
		}

		request.setAttribute("companyMypageInfoDTO", companyMypageInfoDTO);

		result.setPath("/app/main/mypage/mypage-company-edit.jsp");
		result.setRedirect(false);

		return result;
	}
}