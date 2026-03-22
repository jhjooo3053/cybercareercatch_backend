package com.ccc.mypage.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.company.dto.CompanyDetailDTO;
import com.ccc.mypage.dao.MypageDAO;

public class MypageCompanypageDeleteController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("MypageCompanypageDeleteController 실행");

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


		// 기존 기업정보페이지 상세 조회
		CompanyDetailDTO companyDetailDTO = mypageDAO.selectCompanyPageDetail(userNumber);

		// 삭제할 기업정보페이지가 없는 경우
		if (companyDetailDTO == null) {
			result.setPath(request.getContextPath() + "/company/mypage.mpfc");
			result.setRedirect(true);
			return result;
		}

		// 서버 파일 먼저 삭제
		if (companyDetailDTO.getFilePath() != null && !companyDetailDTO.getFilePath().trim().isEmpty()) {
			File oldFile = new File(
					request.getSession().getServletContext().getRealPath("/"),
					companyDetailDTO.getFilePath()
			);

			if (oldFile.exists()) {
				System.out.println("기존 파일 삭제 : " + oldFile.getAbsolutePath());
				oldFile.delete();
			}
		}

		// DB 삭제
		// 자식 테이블부터 삭제
		mypageDAO.deleteJobPostByUserNumber(userNumber);
		System.out.println("TBL_JOB_POST 삭제 완료");

		mypageDAO.deleteFileByUserNumber(userNumber);
		System.out.println("TBL_FILE 삭제 완료");

		mypageDAO.deleteCompInfoByUserNumber(userNumber);
		System.out.println("TBL_COMP_INFO 삭제 완료");

		// 삭제 후 기업회원 마이페이지로 이동
		result.setPath(request.getContextPath() + "/company/mypage.mpfc");
		result.setRedirect(true);

		return result;
	}
}