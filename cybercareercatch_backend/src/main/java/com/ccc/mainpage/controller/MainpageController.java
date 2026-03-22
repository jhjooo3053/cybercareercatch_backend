package com.ccc.mainpage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.company.dto.CompanyCardDTO;
import com.ccc.expo.dto.ExpoListDTO;
import com.ccc.job.dto.JobGroupDTO;
import com.ccc.job.dto.JobRecommendQuestionDTO;
import com.ccc.mainpage.dao.MainpageDAO;
import com.ccc.post.dto.PostListDTO;
import com.ccc.qna.dto.QnaListDTO;

public class MainpageController implements Execute {

	private static final int DEFAULT_QUESTION_NUMBER = 1;
	private static final int MAX_QUESTION_COUNT = 10;

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MainpageDAO mainpageDAO = new MainpageDAO();
		Result result = new Result();
		HttpSession session = request.getSession();

		boolean isResume = "true".equals(request.getParameter("resume"));
		boolean isComplete = "true".equals(request.getParameter("complete"));

		// resume=true가 아니면 새로 진입한 것으로 보고 진행중 데이터 삭제
		if (!isResume) {
			deleteInProgressMainTest(session, mainpageDAO);
		}

		int questionCount = getQuestionCount(mainpageDAO);

<<<<<<< HEAD
		JobRecommendQuestionDTO jrqDTO = new JobRecommendQuestionDTO();
		jrqDTO.setJobQuestionNumber(1);
		mainQuestion = mainpageDAO.selectMainQuestion(jrqDTO); // 메인 상단에 보여줄 질문 1개를 조회한다.
		mainJobGroupList = mainpageDAO.selectMainJobGroupList(); // 메인 추천 직군 4개를 조회한다.
		mainCompanyList = mainpageDAO.selectMainCompanyList(); // 메인 기업 홍보 카드 3개를 조회한다.
		mainFreePostList = mainpageDAO.selectMainFreePostList(); // 메인 자유게시판 최신글 5개를 조회한다.
		mainQnaList = mainpageDAO.selectMainQnaList(); // 메인 기업 Q&A 최신글 5개를 조회한다.
		mainExpoList = mainpageDAO.selectMainExpoList(); // 메인 박람회 일정 목록을 조회한다.

		request.setAttribute("mainQuestion", mainQuestion); // 조회한 메인 질문 1개를 request 영역에 저장한다.
		request.setAttribute("mainJobGroupList", mainJobGroupList); // 조회한 추천 직군 목록을 request 영역에 저장한다.
		request.setAttribute("mainCompanyList", mainCompanyList); // 조회한 기업 홍보 카드 목록을 request 영역에 저장한다.
		request.setAttribute("mainFreePostList", mainFreePostList); // 조회한 자유게시판 최신글 목록을 request 영역에 저장한다.
		request.setAttribute("mainQnaList", mainQnaList); // 조회한 기업 Q&A 최신글 목록을 request 영역에 저장한다.
		request.setAttribute("mainExpoList", mainExpoList); // 조회한 박람회 일정 목록을 request 영역에 저장한다.

		if (isLogin) { // 로그인 상태인 경우이다.
			result.setPath("/app/main/mainpage/mainpage-login.jsp"); // 로그인 상태용 메인 JSP 경로를 설정한다.
		} else { // 로그인하지 않은 상태인 경우이다.
			result.setPath("/app/main/mainpage/mainpage-logout.jsp"); // 로그아웃 상태용 메인 JSP 경로를 설정한다.
=======
		Integer currentQuestionNumber = (Integer) session.getAttribute("mainCurrentQuestionNumber");
		if (currentQuestionNumber == null || currentQuestionNumber < 1 || currentQuestionNumber > questionCount) {
			currentQuestionNumber = DEFAULT_QUESTION_NUMBER;
>>>>>>> 8158ab0 (260322 김진옥메인 수정)
		}

		JobRecommendQuestionDTO mainQuestion = mainpageDAO.selectMainQuestion(currentQuestionNumber);
		if (mainQuestion == null) {
			currentQuestionNumber = DEFAULT_QUESTION_NUMBER;
			mainQuestion = mainpageDAO.selectMainQuestion(currentQuestionNumber);
		}

		List<JobGroupDTO> mainJobGroupList = mainpageDAO.selectMainJobGroupList();
		List<CompanyCardDTO> mainCompanyList = mainpageDAO.selectMainCompanyList();
		List<PostListDTO> mainFreePostList = mainpageDAO.selectMainFreePostList();
		List<QnaListDTO> mainQnaList = mainpageDAO.selectMainQnaList();
		List<ExpoListDTO> mainExpoList = mainpageDAO.selectMainExpoList();

		request.setAttribute("mainQuestion", mainQuestion);
		request.setAttribute("mainJobGroupList", mainJobGroupList);
		request.setAttribute("mainCompanyList", mainCompanyList);
		request.setAttribute("mainFreePostList", mainFreePostList);
		request.setAttribute("mainQnaList", mainQnaList);
		request.setAttribute("mainExpoList", mainExpoList);

		request.setAttribute("mainCurrentQuestionNumber", currentQuestionNumber);
		request.setAttribute("mainQuestionCount", questionCount);
		request.setAttribute("mainIsTestInProgress", session.getAttribute("mainJobResultNumber") != null);

		if (isComplete) {
			request.setAttribute("mainAlertMessage",
					"답변이 완료 되었습니다. 결과는 3일~5일정도 소요됩니다. 마이페이지에서 확인하실 수 있습니다");
		} else if ("true".equals(request.getParameter("emptyAnswer"))) {
			request.setAttribute("mainAlertMessage", "답변을 입력해주세요.");
		} else if ("true".equals(request.getParameter("loginRequired"))) {
			request.setAttribute("mainAlertMessage", "로그인 후 이용할 수 있습니다.");
		} else if ("true".equals(request.getParameter("memberOnly"))) {
			request.setAttribute("mainAlertMessage", "직군 검사는 일반회원만 제출할 수 있습니다.");
		}

		result.setPath("/app/main/mainpage/mainpage-login.jsp");
		result.setRedirect(false);

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