package com.ccc.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Result;

public class QnaFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QnaFrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("QnaFrontController 현재 경로 : " + target);

		Result result = null;

		switch(target) {

		case "/qna/list.qfc":
			System.out.println("QnA 목록 처리 요청");
			result = new QnaListController().execute(request, response);
			System.out.println("QnA 목록 처리 완료");
			break;

		case "/qna/detail.qfc":
			System.out.println("QnA 상세 처리 요청");
			result = new QnaDetailController().execute(request, response);
			System.out.println("QnA 상세 처리 완료");
			break;

		case "/qna/comment-write.qfc":
			System.out.println("QnA 댓글 등록 처리 요청");
			result = new QnaCommentWriteController().execute(request, response);
			System.out.println("QnA 댓글 등록 처리 완료");
			break;

		case "/qna/comment-delete.qfc":
			System.out.println("QnA 댓글 삭제 처리 요청");
			result = new QnaCommentDeleteController().execute(request, response);
			System.out.println("QnA 댓글 삭제 처리 완료");
			break;
			//게시글 추가
		case "/qna/write.qfc":
			System.out.println("QnA 게시글 등록 처리 요청");
			result = new QnaWriteController().execute(request, response);
			System.out.println("QnA 게시글 등록 처리 완료");
			break;
		case "/qna/write-form.qfc":
			System.out.println("QnA 글쓰기 폼 이동 요청");
			result = new QnaWriteFormController().execute(request, response);
			System.out.println("QnA 글쓰기 폼 이동 완료");
			break;
		case "/qna/delete.qfc":
			System.out.println("QnA 게시글 삭제 처리 요청");
			result = new QnaDeleteController().execute(request, response);
			System.out.println("QnA 게시글 삭제 처리 완료");
			break;
		default:
			System.out.println("QnA 요청 경로 없음 : " + target);
			result = new Result();
			result.setPath(request.getContextPath() + "/qna/list.qfc");
			result.setRedirect(true);
			break;
		}

		if(result != null && result.getPath() != null) {
			if(result.isRedirect()) {
				response.sendRedirect(result.getPath());
			} else {
				request.getRequestDispatcher(result.getPath()).forward(request, response);
			}
		}
	}
}