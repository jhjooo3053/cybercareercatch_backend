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

public class QnaWriteFormController implements Execute {

   @Override
   public Result execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      QnaDAO qnaDAO = new QnaDAO();
      Result result = new Result();

      List<CompanyDTO> companyList = qnaDAO.selectCompanyList();
      request.setAttribute("companyList", companyList);

      result.setPath("/app/main/qna/add-qna.jsp");
      result.setRedirect(false);

      return result;
   }
}