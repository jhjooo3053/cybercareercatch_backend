package com.ccc.company.controller; // 회사 컨트롤러 패키지이다.

import java.io.IOException; // 입출력 예외 처리를 위해 가져온다.

import javax.servlet.ServletException; // 서블릿 예외 처리를 위해 가져온다.
import javax.servlet.http.HttpServlet; // HttpServlet을 상속받기 위해 가져온다.
import javax.servlet.http.HttpServletRequest; // 요청 객체를 사용하기 위해 가져온다.
import javax.servlet.http.HttpServletResponse; // 응답 객체를 사용하기 위해 가져온다.

import com.ccc.common.Result; // 이동 경로와 이동 방식을 담는 Result 클래스를 가져온다.

public class CompanyFrontController extends HttpServlet { // 회사 관련 요청을 한 번에 받는 프론트 컨트롤러 클래스이다.

	private static final long serialVersionUID = 1L; // 서블릿 직렬화 버전 값이다.

	public CompanyFrontController() { // 기본 생성자이다.
		super(); // 부모 클래스 생성자를 호출한다.
	}

	@Override // GET 요청이 들어왔을 때 실행할 메소드를 재정의한다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // GET 요청 처리 메소드이다.
		doProcess(request, response); // GET 요청을 공통 처리 메소드로 보낸다.
	}

	@Override // POST 요청이 들어왔을 때 실행할 메소드를 재정의한다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // POST 요청 처리 메소드이다.
		doProcess(request, response); // POST 요청을 공통 처리 메소드로 보낸다.
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // 실제 분기 처리를 담당하는 공통 메소드이다.
		request.setCharacterEncoding("UTF-8"); // 요청 데이터의 한글 깨짐을 막기 위해 UTF-8 인코딩을 설정한다.
		response.setCharacterEncoding("UTF-8"); // 응답 데이터의 한글 깨짐을 막기 위해 UTF-8 인코딩을 설정한다.

		String target = request.getRequestURI().substring(request.getContextPath().length()); // 현재 요청 URI에서 contextPath를 제외한 실제 요청 주소만 잘라낸다.
		Result result = null; // 실행 결과를 담을 Result 객체를 선언한다.

		switch (target) { // 요청 주소에 따라 실행할 컨트롤러를 분기한다.
		case "/company/companyAd.cfc": // 기업홍보 목록 페이지 요청인 경우이다.
			result = new CompanyAdController().execute(request, response); // 기업홍보 목록 컨트롤러를 실행한다.
			break; // switch 문을 종료한다.
		case "/company/companyInfo.cfc":
			result = new CompanyInfoController().execute(request, response);
			break;
		default: // 위에서 처리하지 않은 주소가 들어온 경우이다.
			System.out.println("CompanyFrontController에서 처리할 수 없는 주소 : " + target); // 처리할 수 없는 주소를 콘솔에 출력한다.
			break; // switch 문을 종료한다.
		}

		if (result != null && result.getPath() != null) { // 실행 결과와 이동 경로가 모두 존재할 때만 이동 처리한다.
			if (result.isRedirect()) { // redirect 방식으로 이동해야 하는 경우이다.
				response.sendRedirect(result.getPath()); // 새로운 요청으로 다시 이동시킨다.
			} else { // forward 방식으로 이동해야 하는 경우이다.
				request.getRequestDispatcher(result.getPath()).forward(request, response); // request를 유지한 채 JSP로 이동시킨다.
			}
		}
	}
}