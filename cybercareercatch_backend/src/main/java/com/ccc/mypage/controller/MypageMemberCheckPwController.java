package com.ccc.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.mypage.dao.MypageDAO;

public class MypageMemberCheckPwController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("마이페이지 수정 진입전 비밀번호 확인 요청 컨트롤러 이동 완료");
		Result result = new Result();
		
//		MemberMypageInfoDTO memberMypageInfoDTO = mypageDAO.selectMemberMypageInfo(userNumber);

		// 비밀번호 확인 페이지로 이동
		result.setPath("/app/main/mypage/member-password-check.jsp");
		result.setRedirect(false);
		return result;
	}

}
