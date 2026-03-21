package com.ccc.qna.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ccc.common.config.MyBatisConfig;
import com.ccc.company.dto.CompanyDTO;
import com.ccc.qna.dto.QnaCommentDTO;
import com.ccc.qna.dto.QnaDetailDTO;
import com.ccc.qna.dto.QnaListDTO;
import com.ccc.qna.dto.QnaWriteDTO;

public class QnaDAO {

	private SqlSession sqlSession;

	public QnaDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	// 회사 목록 조회
	public List<CompanyDTO> selectCompanyList() {
		return sqlSession.selectList("qna.selectCompanyList");
	}

	// 목록 조회 + 검색 + 기업 필터
	public List<QnaListDTO> selectQnaList(String searchType, String searchKeyword, String companyNumber) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("searchType", searchType);
		params.put("searchKeyword", searchKeyword);
		params.put("companyNumber", companyNumber);

		return sqlSession.selectList("qna.selectQnaList", params);
	}

	// 게시글 상세 조회
	public QnaDetailDTO selectQnaDetail(Long postNumber) {
		return sqlSession.selectOne("qna.selectQnaDetail", postNumber);
	}

	// 댓글 목록 조회
	public List<QnaCommentDTO> selectCommentListByPostNumber(Long postNumber) {
		return sqlSession.selectList("qna.selectCommentListByPostNumber", postNumber);
	}

	// 댓글 등록
	public void insertComment(QnaCommentDTO qnaCommentDTO) {
		int count = sqlSession.insert("qna.insertComment", qnaCommentDTO);
		System.out.println("insert count : " + count);
	}
	public void updateAnswerStatusToDone(Long postNumber) {
		int count = sqlSession.update("qna.updateAnswerStatusToDone", postNumber);
		System.out.println("answer status update count : " + count);
	}
	public int selectCommentCountByPostNumber(Long postNumber) {
		return sqlSession.selectOne("qna.selectCommentCountByPostNumber", postNumber);
	}

	public void updateAnswerStatusToWait(Long postNumber) {
		int count = sqlSession.update("qna.updateAnswerStatusToWait", postNumber);
		System.out.println("answer status wait update count : " + count);
	}
	// 댓글 삭제
	public void deleteComment(Long commentNumber) {
		int count = sqlSession.delete("qna.deleteComment", commentNumber);
		System.out.println("delete count : " + count);
	}
	
	public void deleteCommentsByPostNumber(Long postNumber) {
		int count = sqlSession.delete("qna.deleteCommentsByPostNumber", postNumber);
		System.out.println("comments delete count : " + count);
	}

	public void deleteQna(Long postNumber) {
		int count = sqlSession.delete("qna.deleteQna", postNumber);
		System.out.println("qna delete count : " + count);
	}

	// 게시글 등록
	public void insertQna(QnaWriteDTO qnaWriteDTO) {
		int count = sqlSession.insert("qna.insertQna", qnaWriteDTO);
		System.out.println("qna insert count : " + count);
	}

	// 마지막 등록 게시글 번호 조회
	public Long selectLastPostNumber() {
		return sqlSession.selectOne("qna.selectLastPostNumber");
	}
}