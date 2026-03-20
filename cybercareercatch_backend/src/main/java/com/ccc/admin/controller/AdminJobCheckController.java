package com.ccc.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.job.dao.JobDAO;
import com.ccc.job.dto.JobTestResultDTO;

public class AdminJobCheckController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 질의문 관련 DB 작업을 처리할 DAO 객체이다.
		JobDAO jobDAO = new JobDAO();

		// 이동 정보를 담을 객체이다.
		Result result = new Result();

		// 현재 페이지 번호를 저장할 변수이다.
		int page = 1;

		// 한 페이지당 보여줄 게시물 수이다.
		int rowCount = 10;

		// 페이지 파라미터를 가져온다.
		String temp = request.getParameter("page");

		// 페이지 번호가 있으면 정수로 변환한다.
		if (temp != null) {
			page = Integer.parseInt(temp);
		}

		// 시작 행 번호를 계산한다.
		int startRow = (page - 1) * rowCount + 1;

		// 끝 행 번호를 계산한다.
		int endRow = startRow + rowCount - 1;

		// Mapper로 넘길 페이지 정보를 담는다.
		Map<String, Integer> pageMap = new HashMap<String, Integer>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);

		// 질의문 답변 목록을 조회한다.
		List<JobTestResultDTO> jobResultList = jobDAO.selectJobResultList(pageMap);

		// 전체 개수를 조회한다.
		int total = jobDAO.getJobResultTotal();

		// JSP에서 사용할 수 있도록 request 영역에 저장한다.
		request.setAttribute("jobResultList", jobResultList);
		request.setAttribute("total", total);
		request.setAttribute("page", page);

		// 질의문 답변 목록 JSP로 이동한다.
		result.setPath("/app/admin/member-management/job-check.jsp");
		result.setRedirect(false);

		return result;
	}
}