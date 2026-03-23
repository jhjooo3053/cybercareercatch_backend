package com.ccc.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.mypage.dao.MypageDAO;

public class MypageCompanypageRegisterController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("MypageCompanypageRegisterController 실행");

		MypageDAO mypageDAO = new MypageDAO();
		Result result = new Result();
		HttpSession session = request.getSession();

		Integer userNumber = (Integer) session.getAttribute("userNumber");
		
		// 이미 기업정보페이지가 있으면 수정페이지로 보냄
		int companyPageCount = mypageDAO.countCompanyPageByUserNumber(userNumber);
		if (companyPageCount > 0) {
			result.setPath(request.getContextPath() + "/company/mypage/companypageEdit.mpfc");
			result.setRedirect(true);
			return result;
		}
		
		//jsp에 뿌려줄값
		request.setAttribute("jobGroupList", mypageDAO.selectJobGroupList());
		// 등록 JSP로 이동
		
		result.setPath("/app/main/mypage/mypage-company-reg-jobposting.jsp");
		result.setRedirect(false);

		return result;
	}
}