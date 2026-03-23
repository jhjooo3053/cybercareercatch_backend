package com.ccc.qna.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.company.dto.CompanyDTO;
import com.ccc.qna.dao.QnaDAO;

public class QnaWriteFormController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		QnaDAO qnaDAO = new QnaDAO();
		Result result = new Result();

		// =========================================================
		// [원본 세션 방식]
		// 테스트 끝나면 아래 주석 풀고 테스트용 코드는 지우면 됩니다.
		// =========================================================
		/*
		 * HttpSession session = request.getSession(false);
		 * 
		 * // 비회원 접근 불가 if (session == null || session.getAttribute("userNumber") ==
		 * null) { result.setPath(request.getContextPath() + "/member/login.mefc");
		 * result.setRedirect(true); return result; }
		 * 
		 * String userType = (String) session.getAttribute("userType");
		 * 
		 * // 일반회원만 글쓰기 폼 접근 가능 if (!"일반회원".equals(userType)) {
		 * result.setPath(request.getContextPath() + "/qna/list.qfc");
		 * result.setRedirect(true); return result; }
		 */

		// =========================================================
		// [테스트용 로그인 분기]
		// 아래 2줄 중 하나만 사용
		// =========================================================

		// 일반회원 테스트
//		String testLoginId = "member01";

		// 기업회원 테스트
		String testLoginId = "company08";

		Integer userNumber = null;
		String userType = null;

		if ("member01".equals(testLoginId)) {
			userNumber = 1;
			userType = "일반회원";
		} else if ("company03".equals(testLoginId)) {
			userNumber = 53;
			userType = "기업회원";
		}

		// =========================================================
		// 비회원 처리
		// =========================================================
		if (userNumber == null) {
			result.setPath(request.getContextPath() + "/member/login.mefc");
			result.setRedirect(true);
			return result;
		}

		// =========================================================
		// 일반회원만 글쓰기 폼 접근 가능
		// =========================================================
		if (!"일반회원".equals(userType)) {
			result.setPath(request.getContextPath() + "/qna/list.qfc");
			result.setRedirect(true);
			return result;
		}

		List<CompanyDTO> companyList = qnaDAO.selectCompanyList();

		request.setAttribute("companyList", companyList);
		request.setAttribute("userNumber", userNumber);
		request.setAttribute("userType", userType);
		request.setAttribute("testLoginId", testLoginId);

		// 핵심 수정
		// add-qna.jsp 말고 qna-write.jsp로 통일
		result.setPath("/app/main/qna/qna-write.jsp");
		result.setRedirect(false);

		return result;
	}
}