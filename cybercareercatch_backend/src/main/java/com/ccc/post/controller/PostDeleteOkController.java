package com.ccc.post.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.post.dao.PostDAO;
import com.ccc.post.dto.PostDTO;

public class PostDeleteOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("게시물 삭제 처리 요청");

		// DAO 객체 생성
		PostDAO postDAO = new PostDAO();

		// 삭제용 DTO 객체 생성
		PostDTO postDTO = new PostDTO();

		// 이동 정보 객체 생성
		Result result = new Result();

		// 세션 객체 가져오기
		HttpSession session = request.getSession();

		// 로그인한 사용자 번호 가져오기
		Integer userNumber = (Integer) session.getAttribute("userNumber");

		// 삭제할 게시글 번호 받기
		String temp = request.getParameter("postNumber");
		int postNumber = 0;

		try {
			postNumber = Integer.parseInt(temp);
		} catch (Exception e) {
			System.out.println("오류 : postNumber 파라미터가 잘못되었습니다. -> " + temp);
			result.setPath(request.getContextPath() + "/post/list.pfc");
			result.setRedirect(true);
			return result;
		}

		System.out.println("현재 로그인한 회원 번호 : " + userNumber);
		System.out.println("삭제 요청 게시글 번호 : " + postNumber);

		/* =========================================================
		   TEMP START
		   로그인 기능 연결 전 임시 테스트용
		   - 현재는 세션이 없어도 테스트 가능해야 하므로
		   - userNumber가 없으면 DB에 실제 존재하는 회원번호 2를 사용
		   - 따라서 member 2가 작성한 글만 삭제됨
		   - 다른 사용자의 글은 mapper 조건에서 자동으로 삭제되지 않음
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

		// 삭제 조건 세팅
		postDTO.setPostNumber(postNumber);
		postDTO.setUserNumber(userNumber);

		System.out.println("삭제용 PostDTO : " + postDTO);

		// 삭제 실행
		// 주의:
		// DAO 쪽 delete는 postNumber + userNumber가 둘 다 맞아야 삭제됨
		// 그래서 member 2가 쓴 글만 삭제 가능
		postDAO.delete(postDTO);

		// 삭제 후 목록으로 이동
		result.setPath(request.getContextPath() + "/post/list.pfc");
		result.setRedirect(true);

		System.out.println("게시물 삭제 처리 완료");

		return result;
	}
}