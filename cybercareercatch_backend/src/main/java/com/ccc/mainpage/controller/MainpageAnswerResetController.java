package com.ccc.mainpage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.mainpage.dao.MainpageDAO;

public class MainpageAnswerResetController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session != null) {
			Integer jobResultNumber = (Integer) session.getAttribute("mainJobResultNumber");

			if (jobResultNumber != null) {
				MainpageDAO mainpageDAO = new MainpageDAO();
				mainpageDAO.deleteMainJobResultAnswers(jobResultNumber);
				mainpageDAO.deleteMainJobResult(jobResultNumber);
			}

			session.removeAttribute("mainJobResultNumber");
			session.removeAttribute("mainCurrentQuestionNumber");
		}

		response.setStatus(HttpServletResponse.SC_OK);
		return null;
	}
}