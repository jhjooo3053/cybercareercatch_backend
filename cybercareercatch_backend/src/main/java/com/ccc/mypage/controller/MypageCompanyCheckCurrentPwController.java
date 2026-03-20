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

public class MypageCompanyCheckCurrentPwController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MypageCompanyCheckCurrentPwController 실행");

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

		// 입력값 받기
		String currentUserPw = request.getParameter("currentUserPw");

		// 현재 비밀번호 공백 체크
		if (currentUserPw == null || currentUserPw.trim().isEmpty()) {
			request.setAttribute("currentPwMessage", "현재 비밀번호를 입력해주세요.");

			CompanyMypageInfoDTO companyMypageInfoDTO = mypageDAO.selectCompanyMemberMypageInfo(userNumber);
			request.setAttribute("companyMypageInfoDTO", companyMypageInfoDTO);

			result.setPath("/app/main/mypage/mypage-company-edit.jsp");
			result.setRedirect(false);
			return result;
		}

		currentUserPw = currentUserPw.trim();

		// 현재 비밀번호 일치 여부 확인
		boolean isCorrectPw = mypageDAO.checkCompanyPw(userNumber, currentUserPw);

		if (!isCorrectPw) {
			request.setAttribute("currentPwMessage", "현재 비밀번호가 일치하지 않습니다.");

			CompanyMypageInfoDTO companyMypageInfoDTO = mypageDAO.selectCompanyMemberMypageInfo(userNumber);
			request.setAttribute("companyMypageInfoDTO", companyMypageInfoDTO);

			result.setPath("/app/main/mypage/mypage-company-edit.jsp");
			result.setRedirect(false);
			return result;
		}

		// 현재 비밀번호 확인 성공
		request.setAttribute("currentPwMessage", "현재 비밀번호가 확인되었습니다.");

		CompanyMypageInfoDTO companyMypageInfoDTO = mypageDAO.selectCompanyMemberMypageInfo(userNumber);
		request.setAttribute("companyMypageInfoDTO", companyMypageInfoDTO);

		// 비밀번호 변경용 확인 완료 세션 저장
		session.setAttribute("companyCurrentPwChecked", true);

		result.setPath("/app/main/mypage/mypage-company-edit.jsp");
		result.setRedirect(false);
		return result;
	}
}