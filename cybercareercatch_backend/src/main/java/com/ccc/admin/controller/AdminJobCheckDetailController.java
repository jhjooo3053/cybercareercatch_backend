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
import com.ccc.job.dto.JobGroupDTO;

public class AdminJobCheckDetailController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JobDAO jobDAO = new JobDAO();
		Result result = new Result();

		String temp = request.getParameter("jobResultNumber");

		if (temp == null || temp.trim().equals("")) {
			result.setPath(request.getContextPath() + "/admin/jobCheck.adfc");
			result.setRedirect(true);
			return result;
		}

		int jobResultNumber = Integer.parseInt(temp);

		List<AdminJobResultDetailDTO> detailList = jobDAO.selectJobResultDetail(jobResultNumber);
		List<JobGroupDTO> jobGroupList = jobDAO.selectJobGroupList();

		AdminJobResultDetailDTO detailInfo = null;
		if (detailList != null && !detailList.isEmpty()) {
			detailInfo = detailList.get(0);
		}

		request.setAttribute("detailList", detailList);
		request.setAttribute("detailInfo", detailInfo);
		request.setAttribute("jobGroupList", jobGroupList);

		result.setPath("/app/admin/member-management/job-checkdetail.jsp");
		result.setRedirect(false);

		return result;
	}
}