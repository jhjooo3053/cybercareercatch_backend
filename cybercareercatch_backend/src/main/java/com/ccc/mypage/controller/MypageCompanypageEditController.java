package com.ccc.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.company.dto.CompanyDetailDTO;
import com.ccc.mypage.dao.MypageDAO;

public class MypageCompanypageEditController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("MypageCompanypageEditController 실행");

		MypageDAO mypageDAO = new MypageDAO();
		Result result = new Result();
		HttpSession session = request.getSession();

		Integer userNumber = (Integer) session.getAttribute("userNumber");

		// 기업정보페이지 존재 여부 확인
		int companyPageCount = mypageDAO.countCompanyPageByUserNumber(userNumber);
		System.out.println("기업정보페이지 존재 개수 : " + companyPageCount);

		// 기업정보페이지가 없으면 등록 페이지로 이동
		if (companyPageCount == 0) {
			result.setRedirect(true);
			result.setPath(request.getContextPath() + "/company/mypage/companypageRegister.mpfc");
			return result;
		}

		// 기존 기업정보 조회
		CompanyDetailDTO companyDetailDTO = mypageDAO.selectCompanyPageDetail(userNumber);

		if (companyDetailDTO == null) {
			System.out.println("기업정보페이지 상세 조회 실패");
			result.setRedirect(true);
			result.setPath(request.getContextPath() + "/company/mypage.mpfc");
			return result;
		}

		// JSP에서 사용할 데이터 세팅
		request.setAttribute("companyDetailDTO", companyDetailDTO);
		request.setAttribute("jobGroupList", mypageDAO.selectJobGroupList());
		
		// 수정 페이지로 forward
		result.setRedirect(false);
		result.setPath("/app/main/mypage/mypage-company-edit-jobposting.jsp");

		System.out.println("기업정보페이지 수정 화면 이동 완료");
		return result;
	}
}