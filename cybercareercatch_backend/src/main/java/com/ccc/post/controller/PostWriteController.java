package com.ccc.post.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;

public class PostWriteController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 컨트롤러 실행 확인용 출력
		System.out.println("자유게시판 글쓰기 페이지 컨트롤러 이동 완료");

		// 이동 경로와 이동 방식을 담을 객체 생성
		Result result = new Result();

		// 현재 요청의 세션 객체 가져오기
//		HttpSession session = request.getSession();

		// 로그인한 사용자 번호 가져오기
		// 세션 담당자가 userNumber 라는 이름으로 넣어준다는 전제
//		Integer userNumber = (Integer) session.getAttribute("userNumber");

		// 이동할 경로를 담을 변수
		String path = null;

		/* =========================================================
		   TEMP START
		   로그인 기능 연결 전 임시 테스트용
		   - 지금은 글쓰기 화면 테스트를 위해 로그인 없이도 접근 허용
		   - 기존에 실제 열리던 JSP 경로 유지
		   ========================================================= */
		path = "/app/main/community/add-post.jsp";
		/* =========================================================
		   TEMP END
		   ========================================================= */

		/* =========================================================
		   RESTORE
		   로그인 기능 연결 완료 후 아래 코드로 원복

		   if (userNumber == null) {
		       path = "/실제로그인페이지경로/login.jsp";
		   } else {
		       path = "/app/main/community/add-post.jsp";
		   }
		   ========================================================= */

		// 이동 경로 저장
		result.setPath(path);

		// JSP로 forward 이동
		result.setRedirect(false);

		return result;
	}
}