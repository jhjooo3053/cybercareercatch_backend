package com.ccc.qna.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.company.dto.CompanyDTO;
import com.ccc.qna.dao.QnaDAO;
import com.ccc.qna.dto.QnaDTO;
import com.ccc.qna.dto.QnaListDTO;

public class QnaListController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("=== QnaListController 실행 ===");

		request.setCharacterEncoding("UTF-8");

		QnaDAO qnaDAO = new QnaDAO();
		Result result = new Result();

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
		Integer companyNumber = null;

		// =========================================================
		// [테스트용 사용자 정보]
		// 필요하면 여기 숫자 바꿔서 쓰세요
		// =========================================================
		if ("member01".equals(testLoginId)) {
			userNumber = 1;
			userType = "일반회원";
			companyNumber = null;
		} else if ("company03".equals(testLoginId)) {
			userNumber = 53;
			userType = "기업회원";
			companyNumber = 3;
		}

		boolean isLogin = (userNumber != null);
		boolean isCompanyUser = "기업회원".equals(userType);
		boolean isMemberUser = "일반회원".equals(userType);

		// =========================================================
		// 파라미터 받기
		// =========================================================
		String temp = request.getParameter("page");
		int page = 1;

		try {
			if (temp != null && !temp.trim().isEmpty()) {
				page = Integer.parseInt(temp);
			}
		} catch (NumberFormatException e) {
			page = 1;
		}

		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		String companyFilter = request.getParameter("companyNumber");

		if (searchType != null && searchType.trim().isEmpty()) {
			searchType = null;
		}

		if (searchKeyword != null) {
			searchKeyword = searchKeyword.trim();
			if (searchKeyword.isEmpty()) {
				searchKeyword = null;
			}
		}

		if (companyFilter != null) {
			companyFilter = companyFilter.trim();
			if (companyFilter.isEmpty()) {
				companyFilter = null;
			}
		}

		// =========================================================
		// 기업회원이면 자기 회사만 보이게 강제
		// =========================================================
		if (isCompanyUser) {
			companyFilter = String.valueOf(companyNumber);
		}

		System.out.println("testLoginId = " + testLoginId);
		System.out.println("userNumber = " + userNumber);
		System.out.println("userType = " + userType);
		System.out.println("companyNumber = " + companyNumber);
		System.out.println("isLogin = " + isLogin);
		System.out.println("isCompanyUser = " + isCompanyUser);
		System.out.println("isMemberUser = " + isMemberUser);
		System.out.println("searchType = " + searchType);
		System.out.println("searchKeyword = " + searchKeyword);
		System.out.println("companyFilter = " + companyFilter);
		System.out.println("page = " + page);

		// =========================================================
		// 공지 / 기업 목록
		// =========================================================
		QnaDTO adminQnaNotice = qnaDAO.selectAdminQnaNotice();
		List<CompanyDTO> companyList = qnaDAO.selectCompanyList();

		// =========================================================
		// 전체 목록 조회
		// =========================================================
		List<QnaListDTO> fullList = qnaDAO.selectQnaList(searchType, searchKeyword, companyFilter);

		if (fullList == null) {
			fullList = new ArrayList<QnaListDTO>();
		}

		// =========================================================
		// 페이징
		// =========================================================
		int rowCount = 10;
		int pageCount = 5;

		int total = fullList.size();

		int realEnd = (int) Math.ceil(total / (double) rowCount);
		if (realEnd == 0) {
			realEnd = 1;
		}

		if (page < 1) {
			page = 1;
		}

		if (page > realEnd) {
			page = realEnd;
		}

		int startRow = (page - 1) * rowCount;
		int endRow = Math.min(startRow + rowCount, total);

		List<QnaListDTO> qnaList = new ArrayList<QnaListDTO>();
		if (startRow < total) {
			qnaList = fullList.subList(startRow, endRow);
		}

		// 화면용 번호 시작값
		int listStartNumber = total - startRow;

		int endPage = (int) (Math.ceil(page / (double) pageCount) * pageCount);
		int startPage = endPage - (pageCount - 1);

		if (endPage > realEnd) {
			endPage = realEnd;
		}

		boolean prev = startPage > 1;
		boolean next = endPage < realEnd;

		// =========================================================
		// JSP 전달
		// =========================================================
		request.setAttribute("adminQnaNotice", adminQnaNotice);
		request.setAttribute("latestQnaNotice", adminQnaNotice);
		request.setAttribute("companyList", companyList);
		request.setAttribute("qnaList", qnaList);

		request.setAttribute("page", page);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);
		request.setAttribute("realEnd", realEnd);
		request.setAttribute("total", total);
		request.setAttribute("rowCount", rowCount);
		request.setAttribute("listStartNumber", listStartNumber);

		request.setAttribute("searchType", searchType);
		request.setAttribute("searchKeyword", searchKeyword);
		request.setAttribute("companyNumber", companyFilter);

		request.setAttribute("isLogin", isLogin);
		request.setAttribute("isCompanyUser", isCompanyUser);
		request.setAttribute("isMemberUser", isMemberUser);
		request.setAttribute("userNumber", userNumber);
		request.setAttribute("userType", userType);
		request.setAttribute("testLoginId", testLoginId);

		result.setPath("/app/main/qna/qna-list.jsp");
		result.setRedirect(false);

		return result;
	}
}