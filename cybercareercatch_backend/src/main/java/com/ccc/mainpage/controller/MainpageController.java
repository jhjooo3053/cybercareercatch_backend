package com.ccc.mainpage.controller; // 메인페이지 컨트롤러 패키지를 선언한다.

import java.io.IOException; // 입출력 예외 처리를 위해 가져온다.
import java.util.List; // 여러 개의 데이터를 리스트로 받기 위해 가져온다.

import javax.servlet.ServletException; // 서블릿 예외 처리를 위해 가져온다.
import javax.servlet.http.HttpServletRequest; // 요청 객체를 사용하기 위해 가져온다.
import javax.servlet.http.HttpServletResponse; // 응답 객체를 사용하기 위해 가져온다.
import javax.servlet.http.HttpSession; // 세션을 사용하기 위해 가져온다.

import com.ccc.common.Execute; // Execute 인터페이스를 구현하기 위해 가져온다.
import com.ccc.common.Result; // 이동 정보 저장용 Result 클래스를 가져온다.
import com.ccc.company.dto.CompanyCardDTO; // 메인 기업 홍보 카드 DTO를 사용하기 위해 가져온다.
import com.ccc.expo.dto.ExpoListDTO; // 메인 박람회 일정 DTO를 사용하기 위해 가져온다.
import com.ccc.job.dto.JobGroupDTO; // 메인 추천 직군 DTO를 사용하기 위해 가져온다.
import com.ccc.job.dto.JobRecommendQuestionDTO; // 메인 질문 DTO를 사용하기 위해 가져온다.
import com.ccc.mainpage.dao.MainpageDAO; // 메인페이지 DAO를 사용하기 위해 가져온다.
import com.ccc.post.dto.PostListDTO; // 자유게시판 최신글 DTO를 사용하기 위해 가져온다.
import com.ccc.qna.dto.QnaListDTO; // 기업 Q&A 최신글 DTO를 사용하기 위해 가져온다.

public class MainpageController implements Execute { // 메인페이지 데이터를 조회해서 JSP로 넘기는 컨트롤러 클래스이다.

	@Override // Execute 인터페이스의 execute 메소드를 재정의한다.
	public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // 메인페이지 조회를 실제로 처리하는 메소드이다.

		MainpageDAO mainpageDAO = new MainpageDAO(); // 메인페이지 관련 DB 작업을 하기 위해 DAO 객체를 생성한다.
		Result result = new Result(); // 최종 이동 정보를 담기 위한 Result 객체를 생성한다.

		HttpSession session = request.getSession(false); // 기존 세션이 있으면 가져오고 없으면 새로 만들지 않는다.
		Integer memberNumber = null; // 로그인 여부 판단에 사용할 memberNumber 값을 담을 변수를 선언한다.
		boolean isLogin = false; // 로그인 여부를 저장할 변수를 선언한다.

		JobRecommendQuestionDTO mainQuestion = null; // 메인 상단 질문 1개를 담을 DTO 변수를 선언한다.
		List<JobGroupDTO> mainJobGroupList = null; // 메인 추천 직군 목록을 담을 리스트 변수를 선언한다.
		List<CompanyCardDTO> mainCompanyList = null; // 메인 기업 홍보 카드 목록을 담을 리스트 변수를 선언한다.
		List<PostListDTO> mainFreePostList = null; // 메인 자유게시판 최신글 목록을 담을 리스트 변수를 선언한다.
		List<QnaListDTO> mainQnaList = null; // 메인 기업 Q&A 최신글 목록을 담을 리스트 변수를 선언한다.
		List<ExpoListDTO> mainExpoList = null; // 메인 박람회 일정 목록을 담을 리스트 변수를 선언한다.

		if (session != null) { // 기존 세션이 존재하는 경우에만 아래 코드를 실행한다.
			memberNumber = (Integer) session.getAttribute("memberNumber"); // 로그인 시 세션에 저장된 memberNumber 값을 가져온다.
		}

		isLogin = memberNumber != null; // memberNumber가 있으면 로그인 상태로 판단한다.
		System.out.println("메인페이지 로그인 상태 : " + isLogin); // 로그인 상태를 콘솔에 출력한다.

		mainQuestion = mainpageDAO.selectMainQuestion(1); // 메인 상단에 보여줄 질문 1개를 조회한다.
		mainJobGroupList = mainpageDAO.selectMainJobGroupList(); // 메인 추천 직군 4개를 조회한다.
		mainCompanyList = mainpageDAO.selectMainCompanyList(); // 메인 기업 홍보 카드 3개를 조회한다.
		mainFreePostList = mainpageDAO.selectMainFreePostList(); // 메인 자유게시판 최신글 5개를 조회한다.
		mainQnaList = mainpageDAO.selectMainQnaList(); // 메인 기업 Q&A 최신글 5개를 조회한다.
		mainExpoList = mainpageDAO.selectMainExpoList(); // 메인 박람회 일정 목록을 조회한다.

		request.setAttribute("mainQuestion", mainQuestion); // 조회한 메인 질문 1개를 request 영역에 저장한다.
		request.setAttribute("mainJobGroupList", mainJobGroupList); // 조회한 추천 직군 목록을 request 영역에 저장한다.
		request.setAttribute("mainCompanyList", mainCompanyList); // 조회한 기업 홍보 카드 목록을 request 영역에 저장한다.
		request.setAttribute("mainFreePostList", mainFreePostList); // 조회한 자유게시판 최신글 목록을 request 영역에 저장한다.
		request.setAttribute("mainQnaList", mainQnaList); // 조회한 기업 Q&A 최신글 목록을 request 영역에 저장한다.
		request.setAttribute("mainExpoList", mainExpoList); // 조회한 박람회 일정 목록을 request 영역에 저장한다.

		if (isLogin) { // 로그인 상태인 경우이다.
			result.setPath("/app/main/mainpage/mainpage-login.jsp"); // 로그인 상태용 메인 JSP 경로를 설정한다.
		} else { // 로그인하지 않은 상태인 경우이다.
			result.setPath("/app/main/mainpage/mainpage-logout.jsp"); // 로그아웃 상태용 메인 JSP 경로를 설정한다.
		}

		result.setRedirect(false); // request에 담은 데이터를 JSP에서 바로 써야 하므로 forward 방식으로 이동한다.

		return result; // 최종 이동 정보를 프론트 컨트롤러로 반환한다.
	}
}