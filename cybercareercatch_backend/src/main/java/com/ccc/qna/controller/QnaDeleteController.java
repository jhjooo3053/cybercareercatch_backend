package com.ccc.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.qna.dao.QnaDAO;

public class QnaDeleteController implements Execute {

   @Override
   public Result execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      QnaDAO qnaDAO = new QnaDAO();
      Result result = new Result();

      String postNumberStr = request.getParameter("postNumber");

      System.out.println("게시글 삭제 요청 들어옴");
      System.out.println("postNumberStr : " + postNumberStr);

      if (postNumberStr == null || postNumberStr.trim().isEmpty()) {
         result.setPath(request.getContextPath() + "/qna/list.qfc");
         result.setRedirect(true);
         return result;
      }

      Long postNumber = Long.parseLong(postNumberStr);

      // 1. 해당 게시글의 댓글 전체 삭제
      qnaDAO.deleteCommentsByPostNumber(postNumber);

      // 2. 게시글 삭제
      qnaDAO.deleteQna(postNumber);

      // 3. 목록으로 이동
      result.setPath(request.getContextPath() + "/qna/list.qfc");
      result.setRedirect(true);

      return result;
   }
}