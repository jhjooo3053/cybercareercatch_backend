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

public class MypageCompanyEditPhonenumController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MypageCompanyEditPhonenumController 실행");

		MypageDAO mypageDAO = new MypageDAO();
		Result result = new Result();
		HttpSession session = request.getSession();

		Integer userNumber = (Integer) session.getAttribute("userNumber");

		Boolean companyPwChecked = (Boolean) session.getAttribute("companyPwChecked");
		if (companyPwChecked == null || !companyPwChecked) {
			result.setPath(request.getContextPath() + "/company/mypage/checkPw.mpfc");
			result.setRedirect(true);
			return result;
		}

		String newPhone = request.getParameter("userPhone");
		String authCode = request.getParameter("authCode");

		newPhone = newPhone == null ? "" : newPhone.trim();
		authCode = authCode == null ? "" : authCode.trim();

		if (newPhone.isEmpty()) {
			request.setAttribute("phoneMessage", "전화번호를 입력해주세요.");

			CompanyMypageInfoDTO companyMypageInfoDTO = mypageDAO.selectCompanyMemberMypageInfo(userNumber);
			request.setAttribute("companyMypageInfoDTO", companyMypageInfoDTO);

			result.setPath("/app/main/mypage/mypage-company-edit.jsp");
			result.setRedirect(false);
			return result;
		}

		if (authCode.isEmpty()) {
			request.setAttribute("authMessage", "인증번호를 입력해주세요.");

			CompanyMypageInfoDTO companyMypageInfoDTO = mypageDAO.selectCompanyMemberMypageInfo(userNumber);
			request.setAttribute("companyMypageInfoDTO", companyMypageInfoDTO);

			result.setPath("/app/main/mypage/mypage-company-edit.jsp");
			result.setRedirect(false);
			return result;
		}

		String sessionCode = (String) session.getAttribute("verificationCode");

		if (sessionCode == null || !sessionCode.equals(authCode)) {
			request.setAttribute("authMessage", "인증번호가 일치하지 않습니다.");

			CompanyMypageInfoDTO companyMypageInfoDTO = mypageDAO.selectCompanyMemberMypageInfo(userNumber);
			request.setAttribute("companyMypageInfoDTO", companyMypageInfoDTO);

			result.setPath("/app/main/mypage/mypage-company-edit.jsp");
			result.setRedirect(false);
			return result;
		}

		mypageDAO.updateCompanyPhone(userNumber, newPhone);
		System.out.println("변경할 전화번호 : " + newPhone);
		System.out.println("기업회원 전화번호 변경 완료");

		session.removeAttribute("verificationCode");
		session.removeAttribute("companyPwChecked");

		result.setPath(request.getContextPath() + "/company/mypage.mpfc?editSuccess=true");
		result.setRedirect(true);

		return result;
	}
}