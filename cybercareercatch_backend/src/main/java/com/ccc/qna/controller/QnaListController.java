package com.ccc.qna.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.company.dto.CompanyDTO;
import com.ccc.qna.dao.QnaDAO;
import com.ccc.qna.dto.QnaListDTO;

public class QnaListController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		QnaDAO qnaDAO = new QnaDAO();
		Result result = new Result();

		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		String companyNumber = request.getParameter("companyNumber");

		if(searchType != null) {
			searchType = searchType.trim();
			if(searchType.isEmpty()) {
				searchType = null;
			}
		}

		if(searchKeyword != null) {
			searchKeyword = searchKeyword.trim();
			if(searchKeyword.isEmpty()) {
				searchKeyword = null;
			}
		}

		if(companyNumber != null) {
			companyNumber = companyNumber.trim();
			if(companyNumber.isEmpty()) {
				companyNumber = null;
			}
		}

		List<CompanyDTO> companyList = qnaDAO.selectCompanyList();
		List<QnaListDTO> qnaList = qnaDAO.selectQnaList(searchType, searchKeyword, companyNumber);

		request.setAttribute("companyList", companyList);
		request.setAttribute("qnaList", qnaList);
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchKeyword", searchKeyword);
		request.setAttribute("companyNumber", companyNumber);

		result.setPath("/app/main/qna/qna-list.jsp");
		result.setRedirect(false);

		return result;
	}
}