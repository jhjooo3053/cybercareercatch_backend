package com.ccc.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.mypage.dao.MypageDAO;

public class MypageMemberEditPhonenumController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MypageMemberEditPhonenumController 실행");

		MypageDAO mypageDAO = new MypageDAO();
		Result result = new Result();
		HttpSession session = request.getSession();

		Integer userNumber = (Integer) session.getAttribute("userNumber");

		Boolean memberPwChecked = (Boolean) session.getAttribute("memberPwChecked");
		if (memberPwChecked == null || !memberPwChecked) {
			result.setPath(request.getContextPath() + "/member/mypage/checkPw.mpfc");
			result.setRedirect(true);
			return result;
		}

		String newPhone = request.getParameter("userPhone");
		String authCode = request.getParameter("authCode");

		if (newPhone == null || newPhone.trim().isEmpty()) {
			request.setAttribute("phoneMessage", "전화번호를 입력해주세요.");
			request.setAttribute("memberMypageInfoDTO", mypageDAO.selectMemberMypageInfo(userNumber));
			result.setPath("/app/main/mypage/mypage-member-edit.jsp");
			result.setRedirect(false);
			return result;
		}

		if (authCode == null || authCode.trim().isEmpty()) {
			request.setAttribute("authMessage", "인증번호를 입력해주세요.");
			request.setAttribute("memberMypageInfoDTO", mypageDAO.selectMemberMypageInfo(userNumber));
			result.setPath("/app/main/mypage/mypage-member-edit.jsp");
			result.setRedirect(false);
			return result;
		}

		newPhone = newPhone.trim();
		authCode = authCode.trim();

		String sessionCode = (String) session.getAttribute("verificationCode");

		if (sessionCode == null || !sessionCode.equals(authCode)) {
			request.setAttribute("authMessage", "인증번호가 일치하지 않습니다.");
			request.setAttribute("memberMypageInfoDTO", mypageDAO.selectMemberMypageInfo(userNumber));
			result.setPath("/app/main/mypage/mypage-member-edit.jsp");
			result.setRedirect(false);
			return result;
		}

		mypageDAO.updateMemberPhone(userNumber, newPhone);
		System.out.println("변경할 전화번호 : " + newPhone);

		session.removeAttribute("verificationCode");
		session.removeAttribute("memberPwChecked");

		result.setPath(request.getContextPath() + "/member/mypage.mpfc?editSuccess=true");
		result.setRedirect(true);

		return result;
	}
}