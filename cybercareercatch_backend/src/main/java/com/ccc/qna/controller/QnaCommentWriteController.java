package com.ccc.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.qna.dao.QnaDAO;
import com.ccc.qna.dto.QnaCommentDTO;
import com.ccc.qna.dto.QnaDetailDTO;

public class QnaCommentWriteController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		QnaDAO qnaDAO = new QnaDAO();
		Result result = new Result();
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("userNumber") == null) {
			result.setPath(request.getContextPath() + "/member/login.mefc");
			result.setRedirect(true);
			return result;
		}

		Integer userNumber = (Integer) session.getAttribute("userNumber");
		String userType = (String) session.getAttribute("userType");
		Integer loginCompanyNumber = (Integer) session.getAttribute("companyNumber");

		if (!"기업회원".equals(userType) || userNumber == null || loginCompanyNumber == null) {
			result.setPath(request.getContextPath() + "/qna/list.qfc");
			result.setRedirect(true);
			return result;
		}

		String postNumberStr = request.getParameter("postNumber");
		String commentContent = request.getParameter("commentContent");

		if (postNumberStr == null || postNumberStr.trim().isEmpty()) {
			result.setPath(request.getContextPath() + "/qna/list.qfc");
			result.setRedirect(true);
			return result;
		}

		int postNumber = 0;

		try {
			postNumber = Integer.parseInt(postNumberStr);
		} catch (NumberFormatException e) {
			result.setPath(request.getContextPath() + "/qna/list.qfc");
			result.setRedirect(true);
			return result;
		}

		if (commentContent == null || commentContent.trim().isEmpty()) {
			result.setPath(request.getContextPath() + "/qna/detail.qfc?postNumber=" + postNumber);
			result.setRedirect(true);
			return result;
		}

		QnaDetailDTO qna = qnaDAO.selectQnaDetail(postNumber);

		if (qna == null || qna.getCompanyNumber() == null
				|| qna.getCompanyNumber().intValue() != loginCompanyNumber.intValue()) {
			result.setPath(request.getContextPath() + "/qna/list.qfc");
			result.setRedirect(true);
			return result;
		}

		int commentCount = qnaDAO.selectCommentCountByPostNumber(postNumber);
		if (commentCount > 0) {
			result.setPath(request.getContextPath() + "/qna/detail.qfc?postNumber=" + postNumber);
			result.setRedirect(true);
			return result;
		}

		QnaCommentDTO qnaCommentDTO = new QnaCommentDTO();
		qnaCommentDTO.setPostNumber(postNumber);
		qnaCommentDTO.setUserNumber(userNumber);
		qnaCommentDTO.setCommentContent(commentContent.trim());

		qnaDAO.insertComment(qnaCommentDTO);
		qnaDAO.updateAnswerStatusToDone(postNumber);

		result.setPath(request.getContextPath() + "/qna/detail.qfc?postNumber=" + postNumber);
		result.setRedirect(true);
		return result;
	}
}