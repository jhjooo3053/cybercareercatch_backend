package com.ccc.qna.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ccc.common.config.MyBatisConfig;
import com.ccc.company.dto.CompanyDTO;
import com.ccc.qna.dto.QnaCommentDTO;
import com.ccc.qna.dto.QnaDTO;
import com.ccc.qna.dto.QnaDetailDTO;
import com.ccc.qna.dto.QnaListDTO;
import com.ccc.qna.dto.QnaWriteDTO;

public class QnaDAO {

	private SqlSession sqlSession;

	public QnaDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	// 기업 목록 조회
	public List<CompanyDTO> selectCompanyList() {
		return sqlSession.selectList("qna.selectCompanyList");
	}

	// 사용자 기업 QnA 목록 조회
	public List<QnaListDTO> selectQnaList(String searchType, String searchKeyword, String companyNumber) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchKeyword", searchKeyword);
		params.put("companyNumber", companyNumber);

		return sqlSession.selectList("qna.selectQnaList", params);
	}

	// 기업 QnA 상세 조회
	public QnaDetailDTO selectQnaDetail(long postNumber) {
		return sqlSession.selectOne("qna.selectQnaDetail", postNumber);
	}

	// 조회수 증가
	public void updateViewCount(long postNumber) {
		sqlSession.update("qna.updateViewCount", postNumber);
	}

	// 댓글 목록 조회
	public List<QnaCommentDTO> selectCommentListByPostNumber(long postNumber) {
		return sqlSession.selectList("qna.selectCommentListByPostNumber", postNumber);
	}

	// 댓글 단건 조회
	public QnaCommentDTO selectComment(long commentNumber) {
		return sqlSession.selectOne("qna.selectComment", commentNumber);
	}

	// 댓글 등록
	public void insertComment(QnaCommentDTO qnaCommentDTO) {
		sqlSession.insert("qna.insertComment", qnaCommentDTO);
	}

	// 댓글 삭제
	public void deleteComment(long commentNumber) {
		sqlSession.delete("qna.deleteComment", commentNumber);
	}

	// 게시글별 댓글 개수 조회
	public int selectCommentCountByPostNumber(long postNumber) {
		return sqlSession.selectOne("qna.selectCommentCountByPostNumber", postNumber);
	}

	// 답변완료 처리
	public void updateAnswerStatusToDone(long postNumber) {
		sqlSession.update("qna.updateAnswerStatusToDone", postNumber);
	}

	// 답변대기 처리
	public void updateAnswerStatusToWait(long postNumber) {
		sqlSession.update("qna.updateAnswerStatusToWait", postNumber);
	}

	// 관리자 QnA 공지 1개 조회
	public QnaDTO selectAdminQnaNotice() {
		return sqlSession.selectOne("qna.selectAdminQnaNotice");
	}

	// 기업 QnA 글 등록
	public void insertQna(QnaWriteDTO qnaWriteDTO) {
		sqlSession.insert("qna.insertQna", qnaWriteDTO);
	}

	// 마지막 등록 게시글 번호 조회
	public int selectLastPostNumber() {
		Integer postNumber = sqlSession.selectOne("qna.selectLastPostNumber");
		return postNumber == null ? 0 : postNumber;
	}

	// 게시글 댓글 전체 삭제
	public void deleteCommentsByPostNumber(long postNumber) {
		sqlSession.delete("qna.deleteCommentsByPostNumber", postNumber);
	}

	// 게시글 삭제
	public void deleteQna(long postNumber) {
		sqlSession.delete("qna.deleteQna", postNumber);
	}

	// 관리자 기업 QnA 총 개수
	public int getAdminQnaTotal(String companyNumber) {
		Integer total = sqlSession.selectOne("qna.getAdminQnaTotal", companyNumber);
		return total == null ? 0 : total;
	}

	// 관리자 기업 QnA 목록 조회
	public List<QnaListDTO> selectAdminQnaList(int startRow, int endRow, String companyNumber) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startRow", startRow);
		params.put("endRow", endRow);
		params.put("companyNumber", companyNumber);

		return sqlSession.selectList("qna.selectAdminQnaList", params);
	}

	// 관리자 기업 QnA 공지 개수
	public int getAdminQnaNoticeCount() {
		Integer count = sqlSession.selectOne("qna.getAdminQnaNoticeCount");
		return count == null ? 0 : count;
	}

	// 관리자 기업 QnA 공지 등록
	public void insertAdminQnaNotice(int adminNumber, String noticeContent) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("adminNumber", adminNumber);
		params.put("noticeContent", noticeContent);

		sqlSession.insert("qna.insertAdminQnaNotice", params);
	}

	// 관리자 기업 QnA 공지 수정
	public void updateAdminQnaNotice(int adminNumber, String noticeContent) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("adminNumber", adminNumber);
		params.put("noticeContent", noticeContent);

		sqlSession.update("qna.updateAdminQnaNotice", params);
	}

	// QnaNoticeController를 계속 쓸 경우 필요
	public QnaListDTO selectQnaNotice(String companyNumber) {
		return sqlSession.selectOne("qna.selectQnaNotice", companyNumber);
	}
}