package com.ccc.company.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.company.dao.CompanyDAO;
import com.ccc.company.dto.CompanyDetailDTO;

public class CompanyInfoController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Result result = new Result();
		CompanyDAO companyDAO = new CompanyDAO();

		String companyNumberParameter = request.getParameter("companyNumber");
		int companyNumber = 0;

		try {
			companyNumber = Integer.parseInt(companyNumberParameter);
		} catch (NumberFormatException e) {
			result.setPath(request.getContextPath() + "/company/companyAd.cfc");
			result.setRedirect(true);
			return result;
		}

		CompanyDetailDTO companyDetailDTO = companyDAO.selectCompanyDetail(companyNumber);

		if (companyDetailDTO == null) {
			result.setPath(request.getContextPath() + "/company/companyAd.cfc");
			result.setRedirect(true);
			return result;
		}

		request.setAttribute("companyDetail", companyDetailDTO);

		result.setPath("/app/main/company/company-info.jsp");
		result.setRedirect(false);

		return result;
	}
}