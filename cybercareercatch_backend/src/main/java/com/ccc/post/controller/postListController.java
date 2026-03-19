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
import com.ccc.post.dto.PostListDTO;





public class postListController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		postListController 실행 디버깅용 
		System.out.println("===postListController 실행===");

//		DB에서 값을 가져오기위한 DAO 객체 생성
		PostDAO postDAO = new PostDAO();
		
//		컨트롤러가 실행을 끝내고 나서 어디로(pach) 어떻게(redirect, forward)갈지를 담을 객체가 필요함 이걸 result에 담는다
		Result result = new Result();
		
//		--------------------------------------------------------------
		
//		페이징 처리
//		request.getParameter은 url값중에 하나를 가져오는것으로 page라는 것을 문자열로 받는다 그리고 
//		해당 값을 temp의 변수에 담는다 
		String temp = request.getParameter("page");
		
//		temp에 저장된 값이 null이라면 기본 페이지 1을 반환하고 그게 아니라면 해당 페이지(문자열)을 
//		정수형으로 반환해서 페이지 변수에 담는다 
		int page = (temp ==null) ? 1 : Integer.valueOf("page");
		
//		한페이지에 게시물 수를 설정 
		int rowCount = 10;

//		페이지 네이션을 5개씩 보여주겠다는 뜻 
		int pageCount = 5;
		
//		페이지의 첫게시물의 번호를 설정한다
		int startRow  = (page-1) * rowCount;
		
//		페이지의 마지막게시물 번호를 설정한다
		int pageEnd = startRow +rowCount -1;
		
//		값을 묶는것 String과 int타입을 하나의 값으로 묶는것 
		Map<String, Integer> pageMap = new HashMap<>();
//		pageMap이라는 변수에 문자열과 값을 하나로 묶어서 하나씩 대입 
		pageMap.put("startRow",startRow); //시작게시물의 번호 
		pageMap.put("pageEnd", pageEnd); //게시물의 마지막 번호 
		
//		pageMap을 postDAO을 시켜 BD에서 값을 가져오고 
//		해당 값 하나하나가 DTO에 객체로 들어감 여러 값이기 때문에 List를 이용해 묶어 저장한다 
		List<PostListDTO> postList = postDAO.selectAll(pageMap);
//		그리고 request에 값을 넣어주는 것 postList라는 이름으로 postList를 저장 
//		그리고 "postList"의 이름으로 postList의 값을 꺼낼 수 있도록 값을 저장 
		request.setAttribute("postList", postList);
//		해당값은 forword로 넣기 때문에 request에 값을 잠깐 저장한다
//		redirect로 넘길 경우 해당값이 사라져서 URL이나 parameter, db에 값을 저장해야한다 
        // JSP에서 ${boardList}로 꺼내 쓸 수 있게 됨
        // forward 방식일 때만 이 값이 다음 JSP까지 전달 된다
		
		
//		--------------------------------------------------------------
//		페이징 정보 설정 
//		전체 게시물 갯수 출력
		int total = postDAO.getTotal();

//		마지막 페이지가 몇인지 반올림해서 저장 
		int realEndPage = (int)(Math.ceil(total/(double) rowCount));

		// 현재 페이지 그룹에서의 마지막 페이지
		int endPage = (int) (Math.ceil(page / (double) pageCount) * pageCount);
		// 현재 페이지 그룹에서의 첫 페이지
		int startPage = endPage - (pageCount - 1);

		// endPage가 실제 존재하는 마지막 페이지보다 크면 조정
		endPage = Math.min(endPage, realEndPage);

		// prev, next 버튼 활성화여부 확인
		boolean prev = startPage > 1;
		boolean next = endPage < realEndPage;

//		해당 값들을 request에 저장하기 (jsp에서 출력하기 위해) 
		request.setAttribute("page", page); 
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);

		System.out.println("======페이징 정보 확인======");
		System.out.println("pageMap : " + pageMap);
		System.out.println("postList : " + postList);
		System.out.println(
				"startPage : " + startPage + ", endPage : " + endPage + ", prev : " + prev + ", next : " + next);
		System.out.println("=========================");

		result.setPath("/app/community/post-list.jsp");
		result.setRedirect(false);

		return result;
		
		
		
		
	}

	
}
