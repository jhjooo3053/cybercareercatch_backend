package com.ccc.mainpage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.job.dto.JobRecommendQuestionDTO;
import com.ccc.job.dto.JobTestResultDTO;
import com.ccc.job.dto.JobTestSubmitDTO;
import com.ccc.mainpage.dao.MainpageDAO;

public class MainpageAnswerOkController implements Execute {

	private static final int MAX_QUESTION_COUNT = 10;

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MainpageDAO mainpageDAO = new MainpageDAO();
		Result result = new Result();
		HttpSession session = request.getSession();

		Object memberNumberObj = session.getAttribute("memberNumber");
		if (memberNumberObj == null) {
			result.setRedirect(true);
			result.setPath(request.getContextPath() + "/mainpage/mainpage.mafc?loginRequired=true");
			return result;
		}

		int userNumber = Integer.parseInt(String.valueOf(memberNumberObj));

		if (!mainpageDAO.isGeneralMember(userNumber)) {
			result.setRedirect(true);
			result.setPath(request.getContextPath() + "/mainpage/mainpage.mafc?memberOnly=true");
			return result;
		}

		String answerContent = request.getParameter("answer");
		if (answerContent == null) {
			answerContent = "";
		}
		answerContent = answerContent.trim();

		if (answerContent.isEmpty()) {
			result.setRedirect(true);
			result.setPath(request.getContextPath() + "/mainpage/mainpage.mafc?resume=true&emptyAnswer=true");
			return result;
		}

		int questionCount = getQuestionCount(mainpageDAO);

		int currentQuestionNumber = 1;
		try {
			currentQuestionNumber = Integer.parseInt(request.getParameter("currentQuestionNumber"));
		} catch (Exception e) {
			Object currentQuestionNumberObj = session.getAttribute("mainCurrentQuestionNumber");
			if (currentQuestionNumberObj != null) {
				currentQuestionNumber = Integer.parseInt(String.valueOf(currentQuestionNumberObj));
			}
		}

		if (currentQuestionNumber < 1 || currentQuestionNumber > questionCount) {
			deleteInProgressMainTest(session, mainpageDAO);
			result.setRedirect(true);
			result.setPath(request.getContextPath() + "/mainpage/mainpage.mafc");
			return result;
		}

		JobRecommendQuestionDTO questionDTO = mainpageDAO.selectMainQuestion(currentQuestionNumber);
		if (questionDTO == null) {
			deleteInProgressMainTest(session, mainpageDAO);
			result.setRedirect(true);
			result.setPath(request.getContextPath() + "/mainpage/mainpage.mafc");
			return result;
		}

		Integer jobResultNumber = (Integer) session.getAttribute("mainJobResultNumber");

		if (jobResultNumber == null) {
			JobTestResultDTO jobTestResultDTO = new JobTestResultDTO();
			jobTestResultDTO.setUserNumber(userNumber);

			mainpageDAO.insertMainJobResult(jobTestResultDTO);

			jobResultNumber = jobTestResultDTO.getJobResultNumber();
			session.setAttribute("mainJobResultNumber", jobResultNumber);
		}

		JobTestSubmitDTO jobTestSubmitDTO = new JobTestSubmitDTO();
		jobTestSubmitDTO.setJobResultNumber(jobResultNumber);
		jobTestSubmitDTO.setUserNumber(userNumber);
		jobTestSubmitDTO.setJobQuestionNumber(currentQuestionNumber);
		jobTestSubmitDTO.setJobQuestionContentSnapshot(questionDTO.getJobQuestionContent());
		jobTestSubmitDTO.setAnswerContent(answerContent);

		mainpageDAO.insertMainAnswer(jobTestSubmitDTO);

		if (currentQuestionNumber >= questionCount) {
			mainpageDAO.updateMainJobResultSubmitted(jobResultNumber);
			clearMainTestSessionOnlySession(session);

			result.setRedirect(true);
			result.setPath(request.getContextPath() + "/mainpage/mainpage.mafc?complete=true");
			return result;
		}

		session.setAttribute("mainCurrentQuestionNumber", currentQuestionNumber + 1);

		result.setRedirect(true);
		result.setPath(request.getContextPath() + "/mainpage/mainpage.mafc?resume=true");
		return result;
	}

	private int getQuestionCount(MainpageDAO mainpageDAO) {
		int questionCount = mainpageDAO.selectMainQuestionCount();

		if (questionCount <= 0) {
			return MAX_QUESTION_COUNT;
		}

		return Math.min(questionCount, MAX_QUESTION_COUNT);
	}

	private void deleteInProgressMainTest(HttpSession session, MainpageDAO mainpageDAO) {
		Integer jobResultNumber = (Integer) session.getAttribute("mainJobResultNumber");

		if (jobResultNumber != null) {
			mainpageDAO.deleteMainJobResultAnswers(jobResultNumber);
			mainpageDAO.deleteMainJobResult(jobResultNumber);
		}

		clearMainTestSessionOnlySession(session);
	}

	private void clearMainTestSessionOnlySession(HttpSession session) {
		session.removeAttribute("mainJobResultNumber");
		session.removeAttribute("mainCurrentQuestionNumber");
	}
}