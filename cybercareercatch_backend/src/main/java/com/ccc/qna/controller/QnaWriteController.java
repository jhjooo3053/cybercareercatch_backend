package com.ccc.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.qna.dao.QnaDAO;
import com.ccc.qna.dto.QnaWriteDTO;

public class QnaWriteController implements Execute {

   @Override
   public Result execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      QnaDAO qnaDAO = new QnaDAO();
      Result result = new Result();

      String postTitle = request.getParameter("postTitle");
      String postContent = request.getParameter("postContent");
      String companyNumberStr = request.getParameter("companyNumber");

      System.out.println("게시글 등록 요청");
      System.out.println("postTitle : " + postTitle);
      System.out.println("postContent : " + postContent);
      System.out.println("companyNumberStr : " + companyNumberStr);

      if(postTitle == null || postTitle.trim().isEmpty()
            || postContent == null || postContent.trim().isEmpty()
            || companyNumberStr == null || companyNumberStr.trim().isEmpty()) {
         result.setPath(request.getContextPath() + "/app/main/qna/add-qna.jsp");
         result.setRedirect(true);
         return result;
      }

      Long companyNumber = Long.parseLong(companyNumberStr);

      QnaWriteDTO qnaWriteDTO = new QnaWriteDTO();

      // 로그인 없이 테스트용 고정 회원번호
      qnaWriteDTO.setUserNumber(1L);

      qnaWriteDTO.setCompanyNumber(companyNumber);
      qnaWriteDTO.setPostTitle(postTitle.trim());
      qnaWriteDTO.setPostContent(postContent.trim());
      qnaWriteDTO.setPostType("QNA_POST");
      qnaWriteDTO.setAnswerStatus("답변대기");

      qnaDAO.insertQna(qnaWriteDTO);

      Long postNumber = qnaDAO.selectLastPostNumber();

      result.setPath(request.getContextPath() + "/qna/detail.qfc?postNumber=" + postNumber);
      result.setRedirect(true);

      return result;
   }
}