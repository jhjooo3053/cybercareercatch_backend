package com.ccc.post.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Result;

public class PostFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostFrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("PostFrontController 현재 경로 : " + target);
		Result result = new Result();
		System.out.println("커뮤니티 프컨 확인=======");

		switch (target) {
		case "/post/list.pfc":
			System.out.println("### 내가 수정한 새 FrontController 실행됨 ###");
			result = new PostListController().execute(request, response);
			System.out.println("### 내가 수정한 새 FrontController 실행됨 ###");
			break;

		case "/post/read.pfc":
			System.out.println("게시물 읽기 처리 요청");
			result = new PostReadOkController().execute(request, response);
			System.out.println("게시물 읽기 처리 완료");
			break;

		case "/post/write.pfc":
			System.out.println("게시물 작성 요청 시작");
			result = new PostWriteController().execute(request, response);
			System.out.println("게시물 목록 처리 완료");
			break;

		case "/post/writeOk.pfc":
			System.out.println("게시물 글쓰기ok 처리 요청");
			result = new PostWriteOkController().execute(request, response);
			System.out.println("게시물 글쓰기ok 처리 완료");
			break;

		case "/post/readOk.pfc":
			System.out.println("게시물 읽기 ok 처리 요청");
			result = new PostReadOkController().execute(request, response);
			System.out.println("게시물 읽기 ok 처리 완료");
			break;

		case "/post/deleteOk.pfc":
			System.out.println("게시물 삭제 처리 요청");
			result = new PostDeleteOkController().execute(request, response);
			System.out.println("게시물 삭제 처리 완료");
			break;

		case "/post/commentWriteOk.pfc":
			System.out.println("댓글 작성 처리 요청");
			result = new CommentWriteOkController().execute(request, response);
			System.out.println("댓글 작성 처리 완료");
			break;

		case "/post/commentDeleteOk.pfc":
			System.out.println("댓글 삭제 처리 요청");
			result = new CommentDeleteOkController().execute(request, response);
			System.out.println("댓글 삭제 처리 완료");
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
