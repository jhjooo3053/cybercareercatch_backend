package com.ccc.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.qna.dao.QnaDAO;
import com.ccc.qna.dto.QnaDTO;

public class QnaNoticeController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Result result = new Result();

		QnaDAO qnaDAO = new QnaDAO();
		QnaDTO notice = qnaDAO.selectAdminQnaNotice();

		if (notice == null) {
			result.setPath(request.getContextPath() + "/qna/list.qfc");
			result.setRedirect(true);
			return result;
		}

		result.setPath(request.getContextPath() + "/qna/detail.qfc?postNumber=" + notice.getPostNumber());
		result.setRedirect(true);
		return result;
	}
}