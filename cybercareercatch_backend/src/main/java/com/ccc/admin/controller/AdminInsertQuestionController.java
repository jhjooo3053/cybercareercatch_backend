package com.ccc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Result;

/*
 * 관리자 관련 요청을 한 곳에서 받는 프론트 컨트롤러이다.
 */
public class AdminFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminFrontController() {
		super();
	}

	/*
	 * GET 방식 요청을 doProcess()로 넘긴다.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/*
	 * POST 방식 요청을 doProcess()로 넘긴다.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/*
	 * 실제 요청을 처리하는 메소드이다.
	 */
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* 요청/응답 한글 처리 */
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		/* 현재 요청 경로를 구한다. */
		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("관리자 프론트 컨트롤러 진입 : " + target);

		/* 이동 정보를 담을 객체이다. */
		Result result = null;

		switch (target) {

		/* =====================================================
		   관리자 로그인 관련
		===================================================== */

		case "/admin/login.adfc":
			System.out.println("관리자 로그인 페이지 요청");
			result = new AdminLoginController().execute(request, response);
			System.out.println("관리자 로그인 페이지 이동 완료");
			break;

		case "/admin/loginOk.adfc":
			System.out.println("관리자 로그인 처리 요청");
			result = new AdminLoginOkController().execute(request, response);
			System.out.println("관리자 로그인 처리 완료");
			break;

		case "/admin/logout.adfc":
			System.out.println("관리자 로그아웃 처리 요청");
			result = new AdminLogoutController().execute(request, response);
			System.out.println("관리자 로그아웃 처리 완료");
			break;

		/* =====================================================
		   질의문 관련
		===================================================== */

		case "/admin/insertQuestion.adfc":
			System.out.println("질의문 관리 페이지 요청");
			result = new AdminInsertQuestionController().execute(request, response);
			System.out.println("질의문 관리 페이지 이동 완료");
			break;

		case "/admin/insertQuestionOk.adfc":
			System.out.println("질의문 질문 저장 처리 요청");
			result = new AdminInsertQuestionControllerOk().execute(request, response);
			System.out.println("질의문 질문 저장 처리 완료");
			break;

		case "/admin/jobCheck.adfc":
			System.out.println("질의문 답변 목록 페이지 요청");
			result = new AdminJobCheckController().execute(request, response);
			System.out.println("질의문 답변 목록 페이지 이동 완료");
			break;

		case "/admin/jobCheckDetail.adfc":
			System.out.println("질의문 답변 상세 페이지 요청");
			result = new AdminJobCheckDetailController().execute(request, response);
			System.out.println("질의문 답변 상세 페이지 이동 완료");
			break;

		case "/admin/judgeJobResult.adfc":
			System.out.println("질의문 직군 판정 저장 요청");
			result = new AdminJudgeJobResultController().execute(request, response);
			System.out.println("질의문 직군 판정 저장 완료");
			break;

		/* =====================================================
		   로드맵 관련
		===================================================== */

		case "/admin/roadmapManagement.adfc":
			System.out.println("관리자 로드맵 관리 페이지 요청");
			result = new AdminRoadmapManagementController().execute(request, response);
			System.out.println("관리자 로드맵 관리 페이지 이동 완료");
			break;

		case "/admin/updateRoadmap.adfc":
			System.out.println("관리자 로드맵 수정 저장 요청");
			result = new AdminUpdateRoadmapController().execute(request, response);
			System.out.println("관리자 로드맵 수정 저장 완료");
			break;
		}

		/* 이동 정보가 있으면 forward 또는 redirect를 처리한다. */
		if (result != null && result.getPath() != null) {
			if (result.isRedirect()) {
				response.sendRedirect(result.getPath());
			} else {
				request.getRequestDispatcher(result.getPath()).forward(request, response);
			}
		}
	}
}