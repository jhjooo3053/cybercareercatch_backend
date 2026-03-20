package com.ccc.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.admin.dto.AdminJobResultDetailDTO;
import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.job.dao.JobDAO;

public class AdminJobCheckDetailController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 질의문 관련 DB 작업을 처리할 DAO 객체이다.
		JobDAO jobDAO = new JobDAO();

		// 이동 정보를 담을 객체이다.
		Result result = new Result();

		// 질의문 결과 번호를 파라미터로 받아온다.
		int jobResultNumber = Integer.parseInt(request.getParameter("jobResultNumber"));

		// 상세 목록을 조회한다.
		List<AdminJobResultDetailDTO> detailList = jobDAO.selectJobResultDetail(jobResultNumber);

		// 첫 번째 데이터를 대표 정보로 사용한다.
		AdminJobResultDetailDTO detailInfo = null;
		if (detailList != null && detailList.size() > 0) {
			detailInfo = detailList.get(0);
		}

		// JSP에서 사용할 수 있도록 request 영역에 저장한다.
		request.setAttribute("detailList", detailList);
		request.setAttribute("detailInfo", detailInfo);

		// 질의문 답변 상세 JSP로 이동한다.
		result.setPath("/app/admin/member-management/job-checkdetail.jsp");
		result.setRedirect(false);

		return result;
	}
}