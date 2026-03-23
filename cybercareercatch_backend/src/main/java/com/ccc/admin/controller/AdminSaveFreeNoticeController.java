package com.ccc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.post.dao.PostDAO;

public class AdminSaveFreeNoticeController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PostDAO postDAO = new PostDAO();
		Result result = new Result();

		Object adminNumberObj = request.getSession().getAttribute("adminNumber");
		String noticeContent = request.getParameter("noticeContent");

		if (adminNumberObj != null && noticeContent != null && !noticeContent.trim().equals("")) {
			int adminNumber = Integer.parseInt(String.valueOf(adminNumberObj));
			postDAO.saveAdminFreeNotice(noticeContent, adminNumber);
		}

		result.setPath(request.getContextPath() + "/admin/communityManagement.adfc?result=saved");
		result.setRedirect(true);

		return result;
	}
}