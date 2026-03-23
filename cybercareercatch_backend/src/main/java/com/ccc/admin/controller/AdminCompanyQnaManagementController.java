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
import com.ccc.company.dto.CompanyDTO;
import com.ccc.qna.dao.QnaDAO;
import com.ccc.qna.dto.QnaCompanyDTO;
import com.ccc.qna.dto.QnaDTO;
import com.ccc.qna.dto.QnaListDTO;

public class AdminCompanyQnaManagementController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		QnaDAO qnaDAO = new QnaDAO();
		Result result = new Result();

		String temp = request.getParameter("page");
		String companyNumber = request.getParameter("companyNumber");

		int page = 1;
		int rowCount = 10;
		int pageCount = 5;

		if (temp != null && !temp.trim().equals("")) {
			page = Integer.parseInt(temp);
		}

		if (companyNumber != null) {
			companyNumber = companyNumber.trim();
			if (companyNumber.equals("")) {
				companyNumber = null;
			}
		}

		int total = qnaDAO.getAdminQnaTotal(companyNumber);

		int realEndPage = (int)Math.ceil(total / (double)rowCount);

		if (realEndPage == 0) {
			realEndPage = 1;
		}

		if (page > realEndPage) {
			page = realEndPage;
		}

		if (page < 1) {
			page = 1;
		}

		int startRow = (page - 1) * rowCount + 1;
		int endRow = page * rowCount;

		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		pageMap.put("companyNumber", companyNumber);

		List<CompanyDTO> companyList = qnaDAO.selectCompanyList();
		List<QnaListDTO> qnaList = qnaDAO.selectAdminQnaList(pageMap);
		QnaDTO qnaNotice = qnaDAO.selectAdminQnaNotice();

		int startPage = ((page - 1) / pageCount) * pageCount + 1;
		int endPage = startPage + pageCount - 1;

		if (endPage > realEndPage) {
			endPage = realEndPage;
		}

		boolean prev = startPage > 1;
		boolean next = endPage < realEndPage;

		request.setAttribute("companyList", companyList);
		request.setAttribute("qnaList", qnaList);
		request.setAttribute("qnaNotice", qnaNotice);
		request.setAttribute("companyNumber", companyNumber);
		request.setAttribute("page", page);
		request.setAttribute("total", total);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);

		result.setPath("/app/admin/community-management/company-qna.jsp");
		result.setRedirect(false);

		return result;
	}
}