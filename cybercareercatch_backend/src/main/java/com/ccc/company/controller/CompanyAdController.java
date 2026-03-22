package com.ccc.company.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.company.dao.CompanyAdDAO;
import com.ccc.company.dto.CompanyAdDTO;
import com.ccc.company.dto.CompanyCardDTO;

public class CompanyAdController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CompanyAdDAO companyAdDAO = new CompanyAdDAO();
		CompanyAdDTO companyAdDTO = new CompanyAdDTO();
		CompanyCardDTO companyCardDTO = new CompanyCardDTO();
		Result result = new Result();

		String pageParameter = request.getParameter("page");
		String jobNumberParameter = request.getParameter("jobNumber");
		String sortTypeParameter = request.getParameter("sortType");

		int page = 1;
		int rowCount = 9;
		int pageCount = 5;
		int totalCount = 0;
		int realEndPage = 1;
		int startRow = 1;
		int endRow = 9;
		Integer jobNumber = null;

		try {
			if (pageParameter != null && !pageParameter.trim().isEmpty()) {
				page = Integer.parseInt(pageParameter);
			}
		} catch (NumberFormatException e) {
			page = 1;
		}

		try {
			if (jobNumberParameter != null && !jobNumberParameter.trim().isEmpty()) {
				jobNumber = Integer.valueOf(jobNumberParameter);
			}
		} catch (NumberFormatException e) {
			jobNumber = null;
		}

		if (jobNumber != null && (jobNumber < 1 || jobNumber > 4)) {
			jobNumber = null;
		}

		if (sortTypeParameter == null || sortTypeParameter.trim().isEmpty()) {
			sortTypeParameter = "latest";
		}

		if (!"latest".equals(sortTypeParameter) && !"name".equals(sortTypeParameter)) {
			sortTypeParameter = "latest";
		}

		companyCardDTO.setJobNumber(jobNumber);
		companyCardDTO.setSortType(sortTypeParameter);

		totalCount = companyAdDAO.selectCompanyAdCount(companyCardDTO);
		realEndPage = (int) Math.ceil(totalCount / (double) rowCount);

		if (realEndPage == 0) {
			realEndPage = 1;
		}

		if (page < 1) {
			page = 1;
		}

		if (page > realEndPage) {
			page = realEndPage;
		}

		startRow = (page - 1) * rowCount + 1;
		endRow = page * rowCount;

		companyCardDTO.setStartRow(startRow);
		companyCardDTO.setEndRow(endRow);

		companyAdDTO.setPage(page);
		companyAdDTO.setRowCount(rowCount);
		companyAdDTO.setTotalCount(totalCount);

		companyAdDTO.setEndPage((int) (Math.ceil(page / (double) pageCount) * pageCount));
		companyAdDTO.setStartPage(companyAdDTO.getEndPage() - (pageCount - 1));

		if (companyAdDTO.getEndPage() > realEndPage) {
			companyAdDTO.setEndPage(realEndPage);
		}

		companyAdDTO.setPrev(companyAdDTO.getStartPage() > 1);
		companyAdDTO.setNext(companyAdDTO.getEndPage() < realEndPage);

		List<CompanyCardDTO> companyAdList = companyAdDAO.selectCompanyAdList(companyCardDTO);

		request.setAttribute("companyAdList", companyAdList);
		request.setAttribute("companyAdDTO", companyAdDTO);
		request.setAttribute("companyCardQuery", companyCardDTO);

		result.setPath("/app/main/company/company-ad.jsp");
		result.setRedirect(false);

		return result;
	}
}