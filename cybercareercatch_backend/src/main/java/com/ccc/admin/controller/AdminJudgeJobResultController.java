package com.ccc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.job.dao.JobDAO;
import com.ccc.job.dto.JobResultDTO;

public class AdminJudgeJobResultController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 질의문 관련 DB 작업을 처리할 DAO 객체이다.
		JobDAO jobDAO = new JobDAO();

		// 직군 판정 결과를 담을 DTO 객체이다.
		JobResultDTO jobResultDTO = new JobResultDTO();

		// 이동 정보를 담을 객체이다.
		Result result = new Result();

		// 파라미터로 넘어온 질의문 결과 번호를 가져온다.
		int jobResultNumber = Integer.parseInt(request.getParameter("jobResultNumber"));

		// 파라미터로 넘어온 직군 번호를 가져온다.
		int jobNumber = Integer.parseInt(request.getParameter("jobNumber"));

		// DTO에 값을 저장한다.
		jobResultDTO.setJobResultNumber(jobResultNumber);
		jobResultDTO.setJobNumber(jobNumber);

		// 직군 판정을 저장한다.
		jobDAO.judgeJobResult(jobResultDTO);

		// 저장 후 다시 상세 페이지로 이동한다.
		result.setPath(request.getContextPath() + "/admin/jobCheckDetail.adfc?jobResultNumber=" + jobResultNumber);
		result.setRedirect(true);

		return result;
	}
}