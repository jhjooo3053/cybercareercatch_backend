package com.ccc.qna.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.qna.dao.QnaDAO;
import com.ccc.qna.dto.QnaCommentDTO;
import com.ccc.qna.dto.QnaDetailDTO;

public class QnaDetailController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		QnaDAO qnaDAO = new QnaDAO();
		Result result = new Result();

		String postNumberStr = request.getParameter("postNumber");
		// =========================================================
		// [원본 세션 방식]
		// 테스트 끝나면 아래 주석 풀고 테스트용 코드는 지우면 됩니다.
		// =========================================================

		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("userNumber") == null) {
			HttpSession loginSession = request.getSession();

			if (postNumberStr != null && !postNumberStr.trim().isEmpty()) {
				loginSession.setAttribute("redirectAfterLogin",
						request.getContextPath() + "/qna/detail.qfc?postNumber=" + postNumberStr);
			} else {
				loginSession.setAttribute("redirectAfterLogin", request.getContextPath() + "/qna/list.qfc");
			}

			result.setPath(request.getContextPath() + "/member/login.mefc");
			result.setRedirect(true);
			return result;
		}

		Integer loginUserNumber = (Integer) session.getAttribute("userNumber");
		String userType = (String) session.getAttribute("userType");
		Integer loginCompanyNumber = (Integer) session.getAttribute("companyNumber");

		// =========================================================
		// 일반회원 / 기업회원만 접근 가능
		// =========================================================
		if (!"일반회원".equals(userType) && !"기업회원".equals(userType)) {
			result.setPath(request.getContextPath() + "/member/login.mefc");
			result.setRedirect(true);
			return result;
		}

		// =========================================================
		// postNumber 검사
		// =========================================================
		if (postNumberStr == null || postNumberStr.trim().isEmpty()) {
			result.setPath(request.getContextPath() + "/qna/list.qfc");
			result.setRedirect(true);
			return result;
		}

		int postNumber = 0;

		try {
			postNumber = Integer.parseInt(postNumberStr);
		} catch (NumberFormatException e) {
			result.setPath(request.getContextPath() + "/qna/list.qfc");
			result.setRedirect(true);
			return result;
		}

		// =========================================================
		// 게시글 상세 1차 조회
		// - 존재 여부 확인
		// - 기업회원 접근 권한 확인
		// =========================================================
		QnaDetailDTO qna = qnaDAO.selectQnaDetail(postNumber);

		if (qna == null) {
			result.setPath(request.getContextPath() + "/qna/list.qfc");
			result.setRedirect(true);
			return result;
		}

		// =========================================================
		// 기업회원은 자기 회사 글만 접근 가능
		// =========================================================
		if ("기업회원".equals(userType)) {
			if (loginCompanyNumber == null || qna.getCompanyNumber() == null
					|| qna.getCompanyNumber().intValue() != loginCompanyNumber.intValue()) {
				result.setPath(request.getContextPath() + "/qna/list.qfc");
				result.setRedirect(true);
				return result;
			}
		}

		// =========================================================
		// 조회수 증가
		// - 권한 확인 끝난 뒤 증가
		// - 증가 후 다시 상세 조회해서 최신 VIEW_COUNT 반영
		// =========================================================
		qnaDAO.updateViewCount(postNumber);
		qna = qnaDAO.selectQnaDetail(postNumber);

		// =========================================================
		// 댓글 조회
		// =========================================================
		List<QnaCommentDTO> commentList = qnaDAO.selectCommentListByPostNumber(postNumber);

		QnaCommentDTO qnaComment = null;
		if (commentList != null && !commentList.isEmpty()) {
			qnaComment = commentList.get(0);
		}

		// =========================================================
		// 버튼 / 폼 노출 여부
		// =========================================================
		boolean showDeleteButton = false;
		boolean showCommentWriteForm = false;
		boolean showCommentDeleteButton = false;

		// 일반회원 본인 글만 삭제 가능
		if ("일반회원".equals(userType) && loginUserNumber != null && qna.getUserNumber() != null
				&& qna.getUserNumber().intValue() == loginUserNumber.intValue()) {
			showDeleteButton = true;
		}

		// 기업회원은 자기 회사 글 + 댓글이 아직 없을 때만 댓글 작성 가능
		if ("기업회원".equals(userType) && loginCompanyNumber != null && qna.getCompanyNumber() != null
				&& qna.getCompanyNumber().intValue() == loginCompanyNumber.intValue() && qnaComment == null) {
			showCommentWriteForm = true;
		}

		// 기업회원은 자기 댓글만 삭제 가능
		if ("기업회원".equals(userType) && loginUserNumber != null && qnaComment != null
				&& qnaComment.getUserNumber() != null
				&& qnaComment.getUserNumber().intValue() == loginUserNumber.intValue()) {
			showCommentDeleteButton = true;
		}

		// =========================================================
		// JSP 전달값
		// =========================================================
		request.setAttribute("qnaDetail", qna);
		request.setAttribute("qnaComment", qnaComment);

		request.setAttribute("qna", qna);
		request.setAttribute("commentList", commentList);

		request.setAttribute("loginUserNumber", loginUserNumber);
		request.setAttribute("userNumber", loginUserNumber);
		request.setAttribute("userType", userType);
		request.setAttribute("loginCompanyNumber", loginCompanyNumber);

		request.setAttribute("showDeleteButton", showDeleteButton);
		request.setAttribute("showCommentWriteForm", showCommentWriteForm);
		request.setAttribute("showCommentDeleteButton", showCommentDeleteButton);

		result.setPath("/app/main/qna/qna-detail.jsp");
		result.setRedirect(false);
		return result;
	}
}