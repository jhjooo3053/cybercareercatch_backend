package com.ccc.post.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.post.dao.PostDAO;
import com.ccc.post.dto.PostWriteDTO;

public class PostWriteOkController implements Execute {

   @Override
   public Result execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      // 한글 깨짐 방지
      request.setCharacterEncoding("UTF-8");

      // 게시글 정보를 담을 DTO 객체 생성
      PostWriteDTO postWriteDTO = new PostWriteDTO();

      // 게시글 등록용 DAO 객체 생성
      PostDAO postDAO = new PostDAO();

      // 이동 경로와 이동 방식을 담을 객체 생성
      Result result = new Result();

      // 세션 객체 가져오기
      HttpSession session = request.getSession();

      // 로그인한 사용자 번호 가져오기
      Integer userNumber = (Integer) session.getAttribute("userNumber");

      // 현재 로그인한 회원 번호 확인용 출력
      System.out.println("현재 로그인한 회원 번호 : " + userNumber);

      /* =========================================================
         TEMP START
         로그인 기능 연결 전 임시 테스트용
         - 세션에 userNumber가 없으면 글 등록이 막히므로
         - DB에 실제 존재하는 회원번호 2번을 강제로 사용
         - 로그인 기능 붙인 뒤 이 구간 삭제
         ========================================================= */
      if (userNumber == null) {
         userNumber = 2;
         System.out.println("임시 테스트용 회원번호 사용 : " + userNumber);
      }
      /* =========================================================
         TEMP END
         ========================================================= */

      /* =========================================================
         RESTORE
         로그인 기능 연결 완료 후 아래 코드로 원복

         if (userNumber == null) {
             System.out.println("오류 : 로그인 된 사용자가 없습니다.");
             result.setPath("/실제로그인페이지경로/login.jsp");
             result.setRedirect(false);
             return result;
         }
         ========================================================= */

      // 전달받은 게시글 타입 꺼내오기
      String postType = request.getParameter("postType");

      // 전달받은 제목 꺼내오기
      String postTitle = request.getParameter("postTitle");

      // 전달받은 내용 꺼내오기
      String postContent = request.getParameter("postContent");

      // 제목 앞뒤 공백 제거
      if (postTitle != null) {
         postTitle = postTitle.trim();
      }

      // 내용 앞뒤 공백 제거
      if (postContent != null) {
         postContent = postContent.trim();
      }

      /* =========================================================
         제목 길이 검증
         - 제목은 10자 이상 100자 이하만 허용
         - 조건에 맞지 않으면 글쓰기 페이지로 다시 이동
         ========================================================= */
      if (postTitle == null || postTitle.length() < 10 || postTitle.length() > 100) {
         System.out.println("게시글 등록 실패 : 제목 길이 조건 불일치");
         result.setPath(request.getContextPath() + "/post/write.pfc");
         result.setRedirect(true);
         return result;
      }

      /* =========================================================
         내용 길이 검증
         - 내용은 10자 이상 1000자 이하만 허용
         - 조건에 맞지 않으면 글쓰기 페이지로 다시 이동
         ========================================================= */
      if (postContent == null || postContent.length() < 10 || postContent.length() > 1000) {
         System.out.println("게시글 등록 실패 : 내용 길이 조건 불일치");
         result.setPath(request.getContextPath() + "/post/write.pfc");
         result.setRedirect(true);
         return result;
      }

      // 글 타입 세팅
      postWriteDTO.setPostType(postType);

      // 작성자 번호 세팅
      postWriteDTO.setUserNumber(userNumber);

      // 제목 세팅
      postWriteDTO.setPostTitle(postTitle);

      // 내용 세팅
      postWriteDTO.setPostContent(postContent);

      // DTO 값 확인용 출력
      System.out.println("게시글 추가 - PostWriteDTO : " + postWriteDTO);

      // DB insert 실행
      postDAO.insert(postWriteDTO);

      // 등록 완료 후 목록 컨트롤러로 이동
      result.setPath(request.getContextPath() + "/post/list.pfc");
      result.setRedirect(true);

      return result;
   }
}