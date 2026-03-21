package com.ccc.post.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.post.dao.PostDAO;
import com.ccc.post.dto.CommentWriteDTO;

public class CommentWriteOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("댓글 작성 처리 요청");

		request.setCharacterEncoding("UTF-8");

		PostDAO postDAO = new PostDAO();
		CommentWriteDTO commentWriteDTO = new CommentWriteDTO();
		Result result = new Result();

		HttpSession session = request.getSession();
		Integer userNumber = (Integer) session.getAttribute("userNumber");

		String tempPostNumber = request.getParameter("postNumber");
		String commentContent = request.getParameter("commentContent");

		int postNumber = 0;

		try {
			postNumber = Integer.parseInt(tempPostNumber);
		} catch (Exception e) {
			System.out.println("오류 : postNumber 파라미터가 잘못되었습니다. -> " + tempPostNumber);
			result.setPath(request.getContextPath() + "/post/list.pfc");
			result.setRedirect(true);
			return result;
		}

		/* =========================================================
		   TEMP START
		   로그인 기능 연결 전 임시 테스트용
		   - 세션에 userNumber가 없으면 member 2로 댓글 작성
		   - 로그인 기능 연결 후 이 구간 삭제
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

		if (commentContent == null || commentContent.trim().isEmpty()) {
			System.out.println("오류 : 댓글 내용이 비어 있습니다.");
			result.setPath(request.getContextPath() + "/post/read.pfc?postNumber=" + postNumber);
			result.setRedirect(true);
			return result;
		}

		commentWriteDTO.setPostNumber(postNumber);
		commentWriteDTO.setUserNumber(userNumber);
		commentWriteDTO.setCommentContent(commentContent.trim());

		System.out.println("댓글 추가 - CommentWriteDTO : " + commentWriteDTO);

		postDAO.insertComment(commentWriteDTO);

		result.setPath(request.getContextPath() + "/post/read.pfc?postNumber=" + postNumber);
		result.setRedirect(true);

		System.out.println("댓글 작성 처리 완료");

		return result;
	}
}