package com.ccc.roadmap.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Result;

/*
 * 로드맵 관련 요청을 한 곳에서 받는 프론트 컨트롤러이다.
 */
public class RoadmapFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RoadmapFrontController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("로드맵 프론트 컨트롤러 진입 : " + target);

		Result result = null;

		switch (target) {

		case "/roadmap/detail.rfc":
			System.out.println("로드맵 상세 페이지 요청");
			result = new RoadmapDetailController().execute(request, response);
			System.out.println("로드맵 상세 페이지 이동 완료");
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