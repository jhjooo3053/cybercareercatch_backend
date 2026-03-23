package com.ccc.post.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.post.dao.PostDAO;
import com.ccc.post.dto.CommentViewDTO;
import com.ccc.post.dto.NoticeDTO;
import com.ccc.post.dto.PostDetailDTO;

public class PostReadOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("=== PostReadOkController 실행 ===");

		PostDAO postDAO = new PostDAO();
		Result result = new Result();

		String temp = request.getParameter("postNumber");
		int postNumber = 0;

		try {
			postNumber = Integer.parseInt(temp);
		} catch (Exception e) {
			System.out.println("postNumber 파라미터 오류 : " + temp);
			result.setPath(request.getContextPath() + "/post/list.pfc");
			result.setRedirect(true);
			return result;
		}

		// 조회수 증가
		postDAO.updateReadCount(postNumber);

		// 1. 공지 먼저 조회
		NoticeDTO notice = postDAO.selectFreeNoticeDetail(postNumber);

		if (notice != null) {
			System.out.println("공지사항 상세 조회 성공 : " + postNumber);

			request.setAttribute("notice", notice);

			result.setPath("/app/main/community/post-notification.jsp");
			result.setRedirect(false);
			return result;
		}

		// 2. 일반 게시글 조회
		PostDetailDTO postDetail = postDAO.select(postNumber);

		if (postDetail == null) {
			System.out.println("조회된 게시글 없음 : " + postNumber);
			result.setPath(request.getContextPath() + "/post/list.pfc");
			result.setRedirect(true);
			return result;
		}

		List<CommentViewDTO> commentList = postDAO.selectCommentList(postNumber);

		HttpSession session = request.getSession();
		Integer loginUserNumber = (Integer) session.getAttribute("userNumber");

		if (loginUserNumber == null) {
			loginUserNumber = 2;
			System.out.println("임시 테스트용 회원번호 사용 : " + loginUserNumber);
		}

		boolean isWriter = false;
		if (loginUserNumber != null && loginUserNumber == postDetail.getUserNumber()) {
			isWriter = true;
		}

		request.setAttribute("postDetail", postDetail);
		request.setAttribute("commentList", commentList);
		request.setAttribute("isWriter", isWriter);
		request.setAttribute("loginUserNumber", loginUserNumber);

		result.setPath("/app/main/community/post-detail.jsp");
		result.setRedirect(false);

		return result;
	}
}
