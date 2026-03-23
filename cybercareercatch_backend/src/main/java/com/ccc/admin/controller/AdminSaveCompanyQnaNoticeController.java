package com.ccc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.qna.dao.QnaDAO;

public class AdminSaveCompanyQnaNoticeController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		QnaDAO qnaDAO = new QnaDAO();
		Result result = new Result();

		Object adminNumberObj = request.getSession().getAttribute("adminNumber");
		String noticeContent = request.getParameter("noticeContent");

		if (adminNumberObj != null && noticeContent != null) {
			int adminNumber = Integer.parseInt(String.valueOf(adminNumberObj));
			qnaDAO.saveAdminQnaNotice(noticeContent, adminNumber);
		}

		result.setPath(request.getContextPath() + "/admin/companyQnaManagement.adfc?result=saved");
		result.setRedirect(true);

		return result;
	}
}