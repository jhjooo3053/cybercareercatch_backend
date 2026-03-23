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

public class QnaCommentDeleteController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		QnaDAO qnaDAO = new QnaDAO();
		Result result = new Result();

		String commentNumberStr = request.getParameter("commentNumber");
		String postNumberStr = request.getParameter("postNumber");
		HttpSession session = request.getSession(false);

		// 비회원 접근 불가
		if (session == null || session.getAttribute("userNumber") == null) {
			result.setPath(request.getContextPath() + "/member/login.mefc");
			result.setRedirect(true);
			return result;
		}

		Integer userNumber = (Integer) session.getAttribute("userNumber");
		String userType = (String) session.getAttribute("userType");
		Integer loginCompanyNumber = (Integer) session.getAttribute("companyNumber");

		System.out.println("댓글 삭제 요청 들어옴");
		System.out.println("commentNumberStr : " + commentNumberStr);
		System.out.println("postNumberStr : " + postNumberStr);

		if (commentNumberStr == null || commentNumberStr.trim().isEmpty() || postNumberStr == null
				|| postNumberStr.trim().isEmpty()) {
			result.setPath(request.getContextPath() + "/qna/list.qfc");
			result.setRedirect(true);
			return result;
		}

		int commentNumber = 0;
		int postNumber = 0;

		try {
			commentNumber = Integer.parseInt(commentNumberStr);
			postNumber = Integer.parseInt(postNumberStr);
		} catch (NumberFormatException e) {
			result.setPath(request.getContextPath() + "/qna/list.qfc");
			result.setRedirect(true);
			return result;
		}

		QnaDetailDTO qna = qnaDAO.selectQnaDetail(postNumber);
		QnaCommentDTO comment = qnaDAO.selectComment(commentNumber);

		// 기업회원, 자기 회사 글, 자기 댓글만 삭제 가능
		if (!"기업회원".equals(userType) || userNumber == null || loginCompanyNumber == null || qna == null
				|| qna.getCompanyNumber() == null || qna.getCompanyNumber().intValue() != loginCompanyNumber.intValue()
				|| comment == null || comment.getUserNumber() == null
				|| comment.getUserNumber().intValue() != userNumber.intValue()) {

			result.setPath(request.getContextPath() + "/qna/detail.qfc?postNumber=" + postNumber);
			result.setRedirect(true);
			return result;
		}

		qnaDAO.deleteComment(commentNumber);

		int commentCount = qnaDAO.selectCommentCountByPostNumber(postNumber);
		System.out.println("삭제 후 댓글 개수 : " + commentCount);

		if (commentCount == 0) {
			qnaDAO.updateAnswerStatusToWait(postNumber);
		}

		result.setPath(request.getContextPath() + "/qna/detail.qfc?postNumber=" + postNumber);
		result.setRedirect(true);

		return result;
	}
}