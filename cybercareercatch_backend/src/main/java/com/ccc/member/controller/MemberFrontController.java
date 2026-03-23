package com.ccc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Result;

/**
 * Servlet implementation class MemberFrontController
 */

public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public MemberFrontController() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String target = request.getRequestURI().substring(request.getContextPath().length());
		Result result = null;

		switch (target) {
		case "/member/login.mefc":
			result = new LoginController().execute(request, response);
			break;
		case "/member/loginOk.mefc":
			result = new LoginOkController().execute(request, response);
			break;
		case "/member/signup.mefc":
			result = new Result();
			result.setPath("/app/main/account/signup.jsp");
			result.setRedirect(false);
			break;
		case "/member/user-agreement-member.mefc":
			result = new Result();
			result.setPath("/app/main/account/user-agreement-member.jsp");
			result.setRedirect(false);
			break;
		case "/member/signup-member.mefc":
			result = new Result();
			result.setPath("/app/main/account/signup-member.jsp");
			result.setRedirect(false);
			break;
		case "/member/signupOk-member.mefc":
			result = new MemberJoinOkController().execute(request, response);
			break;
		case "/member/user-agreement-company.mefc":
			result = new Result();
			result.setPath("/app/main/account/user-agreement-company.jsp");
			result.setRedirect(false);
			break;
		case "/member/signup-company.mefc":
			result = new Result();
			result.setPath("/app/main/account/signup-company.jsp");
			result.setRedirect(false);
			break;
		case "/member/signupOk-company.mefc":
			result = new CompanyJoinOkController().execute(request, response);
			break;
		case "/member/checkIdOk.mefc":
			result = new CheckIdOkController().execute(request, response);
			break;
		case "/member/findPw.mefc":
			result = new FindPwCheckController().execute(request, response);
			break;
		case "/member/findPwOk.mefc":
			result = new FindPwOkController().execute(request, response);
			break;
		case "/member/find-password.mefc":
			result = new Result();
			result.setPath("/app/main/account/find-password.jsp");
			result.setRedirect(false);
			break;
		case "/member/sendSMS.mefc":
			System.out.println("핸드폰 인증 요청");
			result = new JoinSMSController().execute(request, response);
			break;
		case "/member/verifyCode.mefc":
			System.out.println("인증번호 확인 요청");
			result = new VerifyCodeController().execute(request, response);
			break;
		case "/member/logoutOk.mefc":
			result = new LogoutOkController().execute(request, response);
			break;
		default:
			System.out.println("어디로감?");
		}

		if (result != null && result.getPath() != null) {
			if (result.isRedirect()) {
				response.sendRedirect(result.getPath());
			} else {
				request.getRequestDispatcher(result.getPath()).forward(request, response);
			}
		}
	}

}
