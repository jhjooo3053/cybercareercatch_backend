package com.ccc.mainpage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Result;

public class MainpageFrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public MainpageFrontController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("메인 프론트 컨트롤러 진입 : " + target);

		Result result = null;

		switch (target) {

		case "/mainpage/mainpage.mafc":
			System.out.println("메인페이지 조회 요청");
			result = new MainpageController().execute(request, response);
			System.out.println("메인페이지 조회 완료");
			break;

		case "/mainpage/mainpageAnswerOk.mafc":
			System.out.println("메인 직군검사 답변 저장 요청");
			result = new MainpageAnswerOkController().execute(request, response);
			System.out.println("메인 직군검사 답변 저장 완료");
			break;

		case "/mainpage/mainpageAnswerReset.mafc":
			System.out.println("메인 직군검사 진행상태 초기화 요청");
			result = new MainpageAnswerResetController().execute(request, response);
			System.out.println("메인 직군검사 진행상태 초기화 완료");
			break;

		default:
			System.out.println("MainpageFrontController에서 처리할 수 없는 주소 : " + target);
			break;
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