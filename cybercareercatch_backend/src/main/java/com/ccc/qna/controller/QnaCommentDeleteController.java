package com.ccc.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.qna.dao.QnaDAO;

public class QnaCommentDeleteController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		QnaDAO qnaDAO = new QnaDAO();
		Result result = new Result();

		String commentNumberStr = request.getParameter("commentNumber");
		String postNumberStr = request.getParameter("postNumber");

		System.out.println("댓글 삭제 요청 들어옴");
		System.out.println("commentNumberStr : " + commentNumberStr);
		System.out.println("postNumberStr : " + postNumberStr);

		if(commentNumberStr == null || commentNumberStr.trim().isEmpty()
				|| postNumberStr == null || postNumberStr.trim().isEmpty()) {
			result.setPath(request.getContextPath() + "/qna/list.qfc");
			result.setRedirect(true);
			return result;
		}

		Long commentNumber = Long.parseLong(commentNumberStr);
		Long postNumber = Long.parseLong(postNumberStr);

		qnaDAO.deleteComment(commentNumber);

		int commentCount = qnaDAO.selectCommentCountByPostNumber(postNumber);
		System.out.println("삭제 후 댓글 개수 : " + commentCount);

		if(commentCount == 0) {
			qnaDAO.updateAnswerStatusToWait(postNumber);
		}

		result.setPath(request.getContextPath() + "/qna/detail.qfc?postNumber=" + postNumber);
		result.setRedirect(true);

		return result;
	}
}