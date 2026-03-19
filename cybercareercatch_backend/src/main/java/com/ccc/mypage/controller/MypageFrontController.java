package com.ccc.mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Result;

/**
 * Servlet implementation class MypageController
 */
public class MypageFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("MypageFrontController 현재 경로 : " + target);
		Result result = null;
		
		switch(target) {
		case "/member/mypage.mpfc":
			System.out.println("일반회원 마이페이지 처리 요청");
			result = new MypageMemberController().execute(request, response);
			System.out.println("일반회원 마이페이지 처리 와료");
			break;
		case "/member/mypage/checkPw.mpfc":
			System.out.println("일반회원 마이페이지 진입 전 비밀번호확인 페이지 진입 요청");
			result = new MypageMemberCheckPwController().execute(request, response);
			System.out.println("일반회원 마이페이지 진입 전 비밀번호확인 페이지 진입 완료");
			break;
		case "/member/mypage/checkPwOk.mpfc":
			System.out.println("일반회원 마이페이지 진입 전 비밀번호 처리 요청");
			result = new MypageMemberCheckPwOkController().execute(request, response);
			System.out.println("일반회원 마이페이지 진입 전 비밀번호 처리 완료");
			break;
		case "/member/mypage/editInfo.mpfc":
			System.out.println("일반회원 마이페이지 정보수정 진입 요청");
			result = new MypageMemberEditInfoController().execute(request, response);
			System.out.println("일반회원 마이페이지 정보수정 진입 완료");
			break;
		case "/member/mypage/updatePhone.mpfc":
			System.out.println("일반회원 마이페이지 전화번호 수정 요청");
			result = new MypageMemberEditPhonenumController().execute(request, response);
			System.out.println("일반회원 마이페이지 전화번호 수정 완료");
			break;
		}
		
		
		if(result != null && result.getPath() != null) {
			if(result.isRedirect()) {
				response.sendRedirect(result.getPath());
			} else {
				request.getRequestDispatcher(result.getPath()).forward(request, response);
			}
		}
	}

}
