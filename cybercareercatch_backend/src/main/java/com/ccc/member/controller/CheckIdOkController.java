package com.ccc.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.member.dao.UserDAO;

public class CheckIdOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDAO userDAO = new UserDAO();
		Result result = new Result();
		
		String userId = request.getParameter("userId");
		boolean isAvailable = userDAO.checkId(userId);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		try (PrintWriter out = response.getWriter()) {
		    out.print("{\"available\":" + isAvailable + "}");
		    out.flush();
		}

		
		result.setPath(null);
		result.setRedirect(false);
		return result;
	}

}
