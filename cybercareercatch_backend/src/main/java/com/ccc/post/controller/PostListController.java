package com.ccc.post.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.post.dao.PostDAO;
import com.ccc.post.dto.NoticeDTO;
import com.ccc.post.dto.PostListDTO;

public class PostListController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("===postListController 실행===");

		PostDAO postDAO = new PostDAO();
		Result result = new Result();

		String temp = request.getParameter("page");
		int page = 1;

		try {
			if (temp != null && !temp.trim().isEmpty()) {
				page = Integer.parseInt(temp);
			}
		} catch (NumberFormatException e) {
			page = 1;
		}

		int rowCount = 10;
		int pageCount = 5;

		int startRow = (page - 1) * rowCount + 1;
		int endRow = page * rowCount;

		// 검색 조건 받기
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");

		if (keyword != null) {
			keyword = keyword.trim();
		}

		Map<String, Object> pageMap = new HashMap<>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		pageMap.put("searchType", searchType);
		pageMap.put("keyword", keyword);

		List<NoticeDTO> noticeList = postDAO.selectFreeNoticeList();
		request.setAttribute("noticeList", noticeList);

		List<PostListDTO> postList = postDAO.selectAll(pageMap);
		request.setAttribute("postList", postList);

		System.out.println("startRow = " + startRow);
		System.out.println("endRow = " + endRow);
		System.out.println("searchType = " + searchType);
		System.out.println("keyword = " + keyword);
		System.out.println("postList size = " + (postList == null ? "null" : postList.size()));
		System.out.println("postList = " + postList);

		int total = postDAO.getTotal(pageMap);
		int realEndPage = (int) Math.ceil(total / (double) rowCount);

		int endPage = (int) (Math.ceil(page / (double) pageCount) * pageCount);
		int startPage = endPage - (pageCount - 1);

		endPage = Math.min(endPage, realEndPage);

		if (realEndPage == 0) {
			startPage = 1;
			endPage = 1;
		}

		boolean prev = startPage > 1;
		boolean next = endPage < realEndPage;

		request.setAttribute("page", page);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);

		result.setPath("/app/main/community/post-list.jsp");
		result.setRedirect(false);

		return result;
	}
}