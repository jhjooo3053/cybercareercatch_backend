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

public class MypageCompanyEditPwController implements Execute {


	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MypageCompanyEditPwController 실행");

		MypageDAO mypageDAO = new MypageDAO();
		Result result = new Result();
		HttpSession session = request.getSession();

		Integer userNumber = (Integer) session.getAttribute("userNumber");

		// 정보수정 전 비밀번호 확인 안 했으면 다시 비밀번호 확인 페이지로
		Boolean companyPwChecked = (Boolean) session.getAttribute("companyPwChecked");
		if (companyPwChecked == null || !companyPwChecked) {
			result.setPath(request.getContextPath() + "/company/mypage/checkPw.mpfc");
			result.setRedirect(true);
			return result;
		}

		// 입력값 받기
		String currentUserPw = request.getParameter("currentUserPw");
		String newUserPw = request.getParameter("newUserPw");
		String newUserPwConfirm = request.getParameter("newUserPwConfirm");

		// null 방지 + 공백 제거
		currentUserPw = currentUserPw == null ? "" : currentUserPw.trim();
		newUserPw = newUserPw == null ? "" : newUserPw.trim();
		newUserPwConfirm = newUserPwConfirm == null ? "" : newUserPwConfirm.trim();

		// 현재 비밀번호 공백 체크
		if (currentUserPw.isEmpty()) {
			request.setAttribute("currentPwMessage", "현재 비밀번호를 입력해주세요.");

			CompanyMypageInfoDTO companyMypageInfoDTO = mypageDAO.selectCompanyMemberMypageInfo(userNumber);
			request.setAttribute("companyMypageInfoDTO", companyMypageInfoDTO);

			result.setPath("/app/main/mypage/mypage-company-edit.jsp");
			result.setRedirect(false);
			return result;
		}

		// 새 비밀번호 공백 체크
		if (newUserPw.isEmpty()) {
			request.setAttribute("newPwMessage", "변경할 비밀번호를 입력해주세요.");

			CompanyMypageInfoDTO companyMypageInfoDTO = mypageDAO.selectCompanyMemberMypageInfo(userNumber);
			request.setAttribute("companyMypageInfoDTO", companyMypageInfoDTO);

			result.setPath("/app/main/mypage/mypage-company-edit.jsp");
			result.setRedirect(false);
			return result;
		}

		// 새 비밀번호 확인 공백 체크
		if (newUserPwConfirm.isEmpty()) {
			request.setAttribute("newPwConfirmMessage", "변경할 비밀번호 확인을 입력해주세요.");

			CompanyMypageInfoDTO companyMypageInfoDTO = mypageDAO.selectCompanyMemberMypageInfo(userNumber);
			request.setAttribute("companyMypageInfoDTO", companyMypageInfoDTO);

			result.setPath("/app/main/mypage/mypage-company-edit.jsp");
			result.setRedirect(false);
			return result;
		}

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

		// 새 비밀번호 확인 일치 체크
		if (!newUserPw.equals(newUserPwConfirm)) {
			request.setAttribute("newPwConfirmMessage", "변경할 비밀번호가 일치하지 않습니다.");

			CompanyMypageInfoDTO companyMypageInfoDTO = mypageDAO.selectCompanyMemberMypageInfo(userNumber);
			request.setAttribute("companyMypageInfoDTO", companyMypageInfoDTO);

			result.setPath("/app/main/mypage/mypage-company-edit.jsp");
			result.setRedirect(false);
			return result;
		}

		// 비밀번호 수정
		mypageDAO.updateCompanyPw(userNumber, newUserPw);
		System.out.println("기업회원 비밀번호 변경 완료");

		// 정보수정 접근용 비밀번호 확인 세션 제거
		session.removeAttribute("companyPwChecked");

		// 수정 후 기업회원 마이페이지 메인으로 이동
		result.setPath(request.getContextPath() + "/company/mypage.mpfc?editSuccess=true");
		result.setRedirect(true);

		return result;
	}
}