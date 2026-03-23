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

	// 회사 목록 조회
	public List<QnaCompanyDTO> selectCompanyList() {
		return sqlSession.selectList("qna.selectCompanyList");
	}

	// 사용자 목록 조회 + 검색 + 기업 필터
	public List<QnaListDTO> selectQnaList(String searchType, String searchKeyword, String companyNumber) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchKeyword", searchKeyword);
		params.put("companyNumber", companyNumber);

		return sqlSession.selectList("qna.selectQnaList", params);
	}

	public int getAdminQnaTotal(String companyNumber) {
		return sqlSession.selectOne("qna.getAdminQnaTotal", companyNumber);
	}

	public List<QnaListDTO> selectAdminQnaList(Map<String, Object> pageMap) {
		return sqlSession.selectList("qna.selectAdminQnaList", pageMap);
	}

	public QnaDTO selectAdminQnaNotice() {
		return sqlSession.selectOne("qna.selectAdminQnaNotice");
	}

	public void saveAdminQnaNotice(String noticeContent, int adminNumber) {
		Integer count = sqlSession.selectOne("qna.getAdminQnaNoticeCount");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("noticeContent", noticeContent);
		params.put("adminNumber", adminNumber);

		if (count == null || count == 0) {
			sqlSession.insert("qna.insertAdminQnaNotice", params);
			return;
		}

		sqlSession.update("qna.updateAdminQnaNotice", params);
	}

	public QnaDetailDTO selectQnaDetail(Long postNumber) {
		return sqlSession.selectOne("qna.selectQnaDetail", postNumber);
	}

	public List<QnaCommentDTO> selectCommentListByPostNumber(Long postNumber) {
		return sqlSession.selectList("qna.selectCommentListByPostNumber", postNumber);
	}

	public void insertComment(QnaCommentDTO qnaCommentDTO) {
		sqlSession.insert("qna.insertComment", qnaCommentDTO);
	}

	public void updateAnswerStatusToDone(Long postNumber) {
		sqlSession.update("qna.updateAnswerStatusToDone", postNumber);
	}

	public int selectCommentCountByPostNumber(Long postNumber) {
		return sqlSession.selectOne("qna.selectCommentCountByPostNumber", postNumber);
	}

	public void updateAnswerStatusToWait(Long postNumber) {
		sqlSession.update("qna.updateAnswerStatusToWait", postNumber);
	}

	public void deleteComment(Long commentNumber) {
		sqlSession.delete("qna.deleteComment", commentNumber);
	}

	public void deleteCommentsByPostNumber(Long postNumber) {
		sqlSession.delete("qna.deleteCommentsByPostNumber", postNumber);
	}

	public void deleteQna(Long postNumber) {
		sqlSession.delete("qna.deleteQna", postNumber);
	}

	public void insertQna(QnaWriteDTO qnaWriteDTO) {
		sqlSession.insert("qna.insertQna", qnaWriteDTO);
	}

	public Long selectLastPostNumber() {
		return sqlSession.selectOne("qna.selectLastPostNumber");
	}
}