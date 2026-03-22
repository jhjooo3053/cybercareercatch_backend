package com.ccc.mypage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.mypage.dao.MypageDAO;

public class MypageMemberDeleteMyQnaController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("=== MypageMemberDeleteMyQnaController 실행 ===");

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

		String[] postNumbersParam = request.getParameterValues("postNumbers");

		// 선택 안 한 경우
		if (postNumbersParam == null || postNumbersParam.length == 0) {
			result.setPath(request.getContextPath() + "/member/mypage/myQna.mpfc");
			result.setRedirect(true);
			return result;
		}

		List<Integer> postNumbers = new ArrayList<>();

		for (String postNumber : postNumbersParam) {
			postNumbers.add(Integer.parseInt(postNumber));
		}

		mypageDAO.deleteMyQnaPosts(userNumber, postNumbers);

		result.setPath(request.getContextPath() + "/member/mypage/myQna.mpfc");
		result.setRedirect(true);

		return result;
	}
}