package com.ccc.mypage.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.mypage.dao.MypageDAO;
import com.ccc.mypage.dto.CompanyMypageInfoDTO;
import com.ccc.mypage.dto.MypageQnaListDTO;


public class MypageCompanyController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("=== MypageCompanyController 실행 ===");

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

		// 기업회원 기본정보 조회
		CompanyMypageInfoDTO companyMypageInfoDTO = mypageDAO.selectCompanyMemberMypageInfo(userNumber);
		request.setAttribute("companyMypageInfoDTO", companyMypageInfoDTO);

		// 기업정보페이지 개수 조회 - 등록 / 수정버튼 나눠서 보여주기용
		int companyPageCount = 0;
		if (companyMypageInfoDTO != null) {
			companyPageCount = mypageDAO.countCompanyPageByUserNumber(userNumber);
		}
		request.setAttribute("companyPageCount", companyPageCount);

		// ===== 페이징 처리 =====
		String temp = request.getParameter("page");
		int page = 1;

		try {
			page = (temp == null) ? 1 : Integer.parseInt(temp);
			if (page < 1) {
				page = 1;
			}
		} catch (NumberFormatException e) {
			page = 1;
		}

		// 한 페이지당 게시글 수
		int rowCount = 10;
		// 페이지 버튼 수
		int pageCount = 5;

		// 답변대기 Q&A 전체 목록 조회
		List<MypageQnaListDTO> allWaitingQnaList = mypageDAO.selectWaitingQnaListByCompanyUser(userNumber);
		int total = mypageDAO.compQnaTotal(userNumber);

		// 현재 페이지에 보여줄 시작/끝 인덱스
		int startRow = (page - 1) * rowCount;
		int endRow = Math.min(startRow + rowCount, total);

		List<MypageQnaListDTO> waitingQnaList;

		if (total == 0 || startRow >= total) {
			waitingQnaList = Collections.emptyList();
		} else {
			waitingQnaList = allWaitingQnaList.subList(startRow, endRow);
		}

		request.setAttribute("waitingQnaList", waitingQnaList);

		// ===== 페이징 정보 설정 =====
		int realEndPage = (int) Math.ceil(total / (double) rowCount);
		int endPage = (int) (Math.ceil(page / (double) pageCount) * pageCount);
		int startPage = endPage - (pageCount - 1);

		if (realEndPage == 0) {
			startPage = 1;
			endPage = 1;
		} else {
			endPage = Math.min(endPage, realEndPage);
		}

		boolean prev = startPage > 1;
		boolean next = endPage < realEndPage;

		request.setAttribute("page", page);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);

		System.out.println("====== 기업회원 마이페이지 정보 확인 ======");
		System.out.println("companyMypageInfoDTO : " + companyMypageInfoDTO);
		System.out.println("companyPageCount : " + companyPageCount);
		System.out.println("allWaitingQnaList size : " + allWaitingQnaList.size());
		System.out.println("waitingQnaList size : " + waitingQnaList.size());
		System.out.println("page : " + page);
		System.out.println("startPage : " + startPage);
		System.out.println("endPage : " + endPage);
		System.out.println("prev : " + prev);
		System.out.println("next : " + next);
		System.out.println("=====================================");

		// JSP 이동
		result.setPath("/app/main/mypage/mypage-company.jsp");
		result.setRedirect(false);

		return result;
	}
}