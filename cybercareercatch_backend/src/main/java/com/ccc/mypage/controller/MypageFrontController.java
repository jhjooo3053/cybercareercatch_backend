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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("MypageFrontController 현재 경로 : " + target);
		Result result = null;

		switch (target) {
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
		case "/member/mypage/updatePw.mpfc":
			System.out.println("일반회원 마이패이지 비밀번호 수정 요청");
			result = new MypageMemberEditPwController().execute(request, response);
			System.out.println("일반회원 마이페이지 비밀번호 수정 완료");
			break;
		case "/member/mypage/myQna.mpfc":
			System.out.println("현재 마이페이지 나의 qna글 조회 요청");
			result = new MypageMemberViewQnaController().execute(request, response);
			System.out.println("현재 마이페이지 나의 qna글 조회 완료");
			break;
		case "/member/mypage/deleteMyQna.mpfc":
			System.out.println("현재 마이페이지 나의 qna글 삭제 요청");
			result = new MypageMemberDeleteMyQnaController().execute(request, response);
			System.out.println("현재 마이페이지 나의 qna글 삭제 완료");
			break;
		case "/member/mypage/quit.mpfc":
			System.out.println("일반회원 탈퇴 전 비밀번호 확인 진입 요청");
			result = new MypageMemberQuitController().execute(request, response);
			System.out.println("일반회원 탈퇴 전 비밀번호 확인 진입 완료");
			break;
		case "/member/mypage/quitOk.mpfc":
			System.out.println("일반회원 탈퇴 요청");
			result = new MypageMemberDeleteController().execute(request, response);
			System.out.println("일반회원 탈퇴 완료 ");
			break;
		case "/company/mypage.mpfc":
			System.out.println("기업회원 마이페이지 처리 요청");
			result = new MypageCompanyController().execute(request, response);
			System.out.println("기업회원 마이페이지 처리 와료");
			break;
		case "/company/mypage/checkPw.mpfc":
			System.out.println("기업회원 마이페이지 진입 전 비밀번호확인 페이지 진입 요청");
			result = new MypageCompanyCheckPwController().execute(request, response);
			System.out.println("기업회원 마이페이지 진입 전 비밀번호확인 페이지 진입 완료");
			break;
		case "/company/mypage/checkPwOk.mpfc":
			System.out.println("기업회원 마이페이지 진입 전 비밀번호 처리 요청");
			result = new MypageCompanyCheckPwOkController().execute(request, response);
			System.out.println("기업회원 마이페이지 진입 전 비밀번호 처리 완료");
			break;
		case "/company/mypage/editInfo.mpfc":
			System.out.println("기업회원 마이페이지 정보수정 진입 요청");
			result = new MypageCompanyEditInfoController().execute(request, response);
			System.out.println("기업회원 마이페이지 정보수정 진입 완료");
			break;
		case "/company/mypage/updatePhone.mpfc":
			System.out.println("기업회원 마이페이지 전화번호 수정 요청");
			result = new MypageCompanyEditPhonenumController().execute(request, response);
			System.out.println("기업회원 마이페이지 전화번호 수정 완료");
			break;
		case "/company/mypage/updatePw.mpfc":
			System.out.println("기업회원 마이패이지 비밀번호 수정 요청");
			result = new MypageCompanyEditPwController().execute(request, response);
			System.out.println("기업회원 마이페이지 비밀번호 수정 완료");
			break;
		case "/company/mypage/quit.mpfc":
			System.out.println("기업회원 탈퇴 요청");
			result = new MypageCompanyQuitController().execute(request, response);
			System.out.println("기업회원 탈퇴 요청 완료");
			break;
		case "/company/mypage/companypageEdit.mpfc":
			System.out.println("기업회원 기업정보페이지 수정 진입 요청");
			result = new MypageCompanypageEditController().execute(request, response);
			System.out.println("기업회원 기업정보페이지 수정 진입 완료");
			break;
		case "/company/mypage/companypageEditOk.mpfc":
			System.out.println("기업회원 기업정보페이지 수정 요청");
			result = new MypageCompanypageEditOkController().execute(request, response);
			System.out.println("기업회원 기업정보페이지 수정 요청 완료");
			break;
		case "/company/mypage/companypageDelete.mpfc":
			System.out.println("기업회원 기업정보페이지 삭제 요청");
			result = new MypageCompanypageDeleteController().execute(request, response);
			System.out.println("기업회원 기업정보페이지 삭제 완료");
			break;
		case "/company/mypage/companypageRegister.mpfc":
			System.out.println("기업정보페이지 등록 화면 요청");
			result = new MypageCompanypageRegisterController().execute(request, response);
			System.out.println("기업정보페이지 등록 화면 완료");
			break;

		case "/company/mypage/companypageRegisterOk.mpfc":
			System.out.println("기업정보페이지 등록 처리 요청");
			result = new MypageCompanypageRegisterOkController().execute(request, response);
			System.out.println("기업정보페이지 등록 처리 완료");
			break;
		}

		if (result != null && result.getPath() != null) {
			if (result.isRedirect()) {
				response.sendRedirect(result.getPath());
			} else {
				request.getRequestDispatcher(result.getPath()).forward(request, response);
			}
		}
	}

}
