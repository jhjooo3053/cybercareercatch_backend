package com.ccc.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;

public class MypageMemberQuitController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MypageMemberQuitPwController");
		Result result = new Result();
		
//		MemberMypageInfoDTO memberMypageInfoDTO = mypageDAO.selectMemberMypageInfo(userNumber);

		result.setPath("/app/main/mypage/mypage-member-quit.jsp");
		result.setRedirect(false);
		return result;
	}

}
