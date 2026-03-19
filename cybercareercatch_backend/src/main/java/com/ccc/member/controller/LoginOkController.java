package com.ccc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.member.dao.UserDAO;
import com.ccc.member.dto.UserDTO;

public class LoginOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserDTO userDTO = new UserDTO();
		UserDAO userDAO = new UserDAO();
		int memberNumber = 0;
		Result result = new Result();

		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		String remember = request.getParameter("remember");
		HttpSession session = request.getSession(); // +++ 세션저장
		String path = null;

		userDTO.setUserId(userId);
		userDTO.setUserPassword(userPassword);

		// 쿼리문 실행 메소드 호출
		memberNumber = userDAO.login(userDTO);
		System.out.println(memberNumber);

		if (memberNumber != -1) {
			path = "/post/postListOk.bo";
			session.setAttribute("memberNumber", memberNumber);
			System.out.println("세션값 : " + memberNumber);

			if (remember != null) {
				Cookie cookie = new Cookie("userId", userId);
				cookie.setMaxAge(60 * 60 * 24); // 1일
				response.addCookie(cookie);
			} else {
				// 체크 해제시 쿠키 삭제
				Cookie cookie = new Cookie("userId", "");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		} else {
			path = "/member/login.mefc?login=fail";
		}

		result.setRedirect(true); // 세션에 저장된 값은 유지
		result.setPath(path);

		return result;
	}

}
