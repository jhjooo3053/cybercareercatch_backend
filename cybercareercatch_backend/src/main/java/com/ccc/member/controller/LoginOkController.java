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
		int userNumber = 0;
		Result result = new Result();

		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		String remember = request.getParameter("remember");
		HttpSession session = request.getSession();
		String path = null;

		userDTO.setUserId(userId);
		userDTO.setUserPassword(userPassword);

		userNumber = userDAO.login(userDTO);
		String userName = userDAO.selectUserNameByUserNumber(userNumber);
		System.out.println(userNumber);

		if (userNumber != -1) {
			String userType = userDAO.selectUserType(userNumber);
			Integer companyNumber = userDAO.selectCompanyNumberByUserNumber(userNumber);

			// 기존 프로젝트에서 쓰던 세션값 유지
			session.setAttribute("userNumber", userNumber);

			// [수정] 다른 컨트롤러/QNA 쪽에서 실제로 쓰는 세션값도 같이 저장
			// QnaListController, QnaDetailController 등은 userNumber, userType,
			// companyNumber를 사용함
			session.setAttribute("userName", userName);
			session.setAttribute("userType", userType);

			// [수정] 기업회원이면 companyNumber 저장, 아니면 제거
			if (companyNumber != null) {
				session.setAttribute("companyNumber", companyNumber);
			} else {
				session.removeAttribute("companyNumber");
			}

			System.out.println("세션 userNumber : " + userNumber);
			System.out.println("세션 userType : " + userType);
			System.out.println("세션 companyNumber : " + companyNumber);

			// [수정] 로그인 전에 가려던 페이지가 있으면 그쪽으로 보내기
			// 예: 비회원이 기업QNA 눌렀다가 로그인하면, 로그인 후 메인이 아니라 다시 QNA로 복귀
			String redirectAfterLogin = (String) session.getAttribute("redirectAfterLogin");

			if (redirectAfterLogin != null && !redirectAfterLogin.trim().isEmpty()) {
				path = redirectAfterLogin;

				// [수정] 한 번 사용했으면 제거
				// 안 지우면 다음 로그인 때도 예전 페이지로 다시 갈 수 있음
				session.removeAttribute("redirectAfterLogin");
			} else {
				// [수정] 별도 이동 목적지가 없을 때만 메인으로 이동
				path = request.getContextPath() + "/mainpage/mainpage.mafc";
			}

			// 아이디 저장 체크 처리
			if (remember != null) {
				Cookie cookie = new Cookie("userId", userId);
				cookie.setMaxAge(60 * 60 * 24);
				response.addCookie(cookie);
			} else {
				Cookie cookie = new Cookie("userId", "");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}

		} else {
			// 로그인 실패 시 다시 로그인 페이지로 이동
			path = request.getContextPath() + "/member/login.mefc?login=fail";
		}

		result.setRedirect(true);
		result.setPath(path);

		return result;
	}
}