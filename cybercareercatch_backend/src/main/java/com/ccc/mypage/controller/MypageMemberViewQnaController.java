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
import com.ccc.mypage.dto.MypageQnaListDTO;

public class MypageMemberViewQnaController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("=== MypageMemberViewQnaController 실행 ===");

		MypageDAO mypageDAO = new MypageDAO();
		Result result = new Result();
		HttpSession session = request.getSession();

		Integer userNumber = (Integer) session.getAttribute("userNumber");
		String userType = (String) session.getAttribute("userType");
		
		//테스트용 - 삭제
		session.setAttribute("userNumber", 1);
		session.setAttribute("userType", "일반회원");
		
		System.out.println("로그인한 회원 번호 : " + userNumber);
		System.out.println("로그인한 회원 타입 : " + userType);
		
		
		// 비로그인
		if (userNumber == null) {
			result.setPath(request.getContextPath() + "/member/login.mefc");
			result.setRedirect(true);
			return result;
		}

		// 일반회원이 아님
		if (!"일반회원".equals(userType)) {
			result.setPath(request.getContextPath() + "/mainpage/mainpage.mafc");
			result.setRedirect(true);
			return result;
		}

		// ===== 페이징 처리 =====
		String temp = request.getParameter("page");
		int page = (temp == null) ? 1 : Integer.valueOf(temp);

		// 한 페이지당 게시글 수
		int rowCount = 10;
		// 페이지 버튼 수
		int pageCount = 5;

		// 전체 목록 조회
		List<MypageQnaListDTO> allQnaList = mypageDAO.selectMyQnaList(userNumber);

		// 전체 게시글 수
		int total = mypageDAO.myQnaTotal(userNumber);

		// 현재 페이지에 보여줄 시작/끝 인덱스
		int startRow = (page - 1) * rowCount;
		int endRow = Math.min(startRow + rowCount, total);

		List<MypageQnaListDTO> myQnaList;

		if (total == 0 || startRow >= total) {
			myQnaList = Collections.emptyList();
		} else {
			myQnaList = allQnaList.subList(startRow, endRow);
		}

		request.setAttribute("myQnaList", myQnaList);

		// ===== 페이징 정보 설정 =====
		int realEndPage = (int) Math.ceil(total / (double) rowCount);
		int endPage = (int) (Math.ceil(page / (double) pageCount) * pageCount);
		int startPage = endPage - (pageCount - 1);

		endPage = Math.min(endPage, realEndPage);

		boolean prev = startPage > 1;
		boolean next = endPage < realEndPage;

		request.setAttribute("page", page);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);

		System.out.println("====== 페이징 정보 확인 ======");
		System.out.println("allQnaList size : " + allQnaList.size());
		System.out.println("myQnaList size : " + myQnaList.size());
		System.out.println("page : " + page);
		System.out.println("startPage : " + startPage);
		System.out.println("endPage : " + endPage);
		System.out.println("prev : " + prev);
		System.out.println("next : " + next);
		System.out.println("=========================");

		result.setPath("/app/main/mypage/mypage-member-edit-postlist.jsp");
		result.setRedirect(false);

		return result;
	}
}