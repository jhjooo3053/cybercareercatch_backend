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

		// 로그인 정보 확인
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

		// 기업회원 아님
		if (!"기업회원".equals(userType)) {
			result.setPath(request.getContextPath() + "/main/main.mafc");
			result.setRedirect(true);
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
		request.setAttribute("companyMypageInfoDTO", companyMypageInfoDTO);

		
		result.setPath("/app/main/mypage/mypage-company-edit.jsp");
		result.setRedirect(false);

		return result;
	}
}