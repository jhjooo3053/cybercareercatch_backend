package com.ccc.qna.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.qna.dao.QnaDAO;
import com.ccc.qna.dto.QnaCommentDTO;
import com.ccc.qna.dto.QnaDetailDTO;

public class QnaDetailController implements Execute {

   @Override
   public Result execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      QnaDAO qnaDAO = new QnaDAO();
      Result result = new Result();

      String postNumberStr = request.getParameter("postNumber");

      if(postNumberStr == null || postNumberStr.trim().isEmpty()) {
         result.setPath(request.getContextPath() + "/qna/list.qfc");
         result.setRedirect(true);
         return result;
      }

      Long postNumber = Long.parseLong(postNumberStr);

      QnaDetailDTO qnaDetail = qnaDAO.selectQnaDetail(postNumber);
      List<QnaCommentDTO> commentList = qnaDAO.selectCommentListByPostNumber(postNumber);

      System.out.println("상세 postNumber : " + postNumber);
      System.out.println("qnaDetail : " + qnaDetail);
      System.out.println("commentList size : " + (commentList == null ? "null" : commentList.size()));

      if(qnaDetail == null) {
         result.setPath(request.getContextPath() + "/qna/list.qfc");
         result.setRedirect(true);
         return result;
      }

      request.setAttribute("qnaDetail", qnaDetail);
      request.setAttribute("commentList", commentList);

      result.setPath("/app/main/qna/qna-detail.jsp");
      result.setRedirect(false);

      return result;
   }
}