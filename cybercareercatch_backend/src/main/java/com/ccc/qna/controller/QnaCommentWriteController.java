package com.ccc.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.qna.dao.QnaDAO;
import com.ccc.qna.dto.QnaCommentDTO;

public class QnaCommentWriteController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		QnaDAO qnaDAO = new QnaDAO();
		Result result = new Result();

		String postNumberStr = request.getParameter("postNumber");
		String commentContent = request.getParameter("commentContent");

		if(postNumberStr == null || postNumberStr.trim().isEmpty()) {
			result.setPath(request.getContextPath() + "/qna/list.qfc");
			result.setRedirect(true);
			return result;
		}

		Long postNumber = Long.parseLong(postNumberStr);

		if(commentContent == null || commentContent.trim().isEmpty()) {
			result.setPath(request.getContextPath() + "/qna/detail.qfc?postNumber=" + postNumber);
			result.setRedirect(true);
			return result;
		}

		QnaCommentDTO qnaCommentDTO = new QnaCommentDTO();
		qnaCommentDTO.setPostNumber(postNumber);

		// 테스트용 고정 회원번호
		qnaCommentDTO.setUserNumber(1L);

		qnaCommentDTO.setCommentContent(commentContent.trim());

		qnaDAO.insertComment(qnaCommentDTO);

		// 댓글 등록 후 게시글 답변상태를 답변완료로 변경
		qnaDAO.updateAnswerStatusToDone(postNumber);

		result.setPath(request.getContextPath() + "/qna/detail.qfc?postNumber=" + postNumber);
		result.setRedirect(true);

		return result;
	}
}