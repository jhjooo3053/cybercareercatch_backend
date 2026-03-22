package com.ccc.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.member.dao.UserDAO;
import com.ccc.member.dto.UserDTO;

public class FindPwOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userId");
		String newPw = request.getParameter("newPw");

		UserDTO dto = new UserDTO();
		dto.setUserId(userId);
		dto.setUserPassword(newPw);

		UserDAO userDAO = new UserDAO();
		boolean success = userDAO.updatePw(dto);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try (PrintWriter out = response.getWriter()) {
			out.print("{\"success\":" + success + "}");
			out.flush();
		}

		Result result = new Result();
		result.setPath(null);
		result.setRedirect(false);
		return result;
	}
}