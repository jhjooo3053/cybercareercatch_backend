package com.ccc.job.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ccc.admin.dto.AdminJobResultDetailDTO;
import com.ccc.job.dto.JobGroupDTO;
import com.ccc.job.dto.JobRecommendQuestionDTO;
import com.ccc.job.dto.JobResultDTO;
import com.ccc.job.dto.JobTestResultDTO;
import com.ccc.mybatis.config.MyBatisConfig;

public class JobDAO {
	public SqlSession sqlSession;

	public JobDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	// 직군 목록 조회
	public List<JobGroupDTO> selectJobGroupList() {
		return sqlSession.selectList("job.selectJobGroupList");
	}

	// 질의문 목록 조회
	public List<JobRecommendQuestionDTO> selectQuestionList() {
		return sqlSession.selectList("job.selectQuestionList");
	}

	// 질의문 단건 조회
	public JobRecommendQuestionDTO selectQuestion(int jobQuestionNumber) {
		return sqlSession.selectOne("job.selectQuestion", jobQuestionNumber);
	}

	// 질의문 작성
	public void insertQuestion(JobRecommendQuestionDTO jobRecommendQuestionDTO) {
		sqlSession.insert("job.insertQuestion", jobRecommendQuestionDTO);
	}

	// 질의문 수정
	public void updateQuestion(JobRecommendQuestionDTO jobRecommendQuestionDTO) {
		sqlSession.update("job.updateQuestion", jobRecommendQuestionDTO);
	}

	// 관리자 질의문 답변 목록 조회
	public List<JobTestResultDTO> selectJobResultList(Map<String, Integer> pageMap) {
		return sqlSession.selectList("job.selectJobResultList", pageMap);
	}

	// 관리자 질의문 답변 총 개수
	public int getJobResultTotal() {
		return sqlSession.selectOne("job.getJobResultTotal");
	}

	// 관리자 질의문 답변 상세 조회
	public List<AdminJobResultDetailDTO> selectJobResultDetail(int jobResultNumber) {
		return sqlSession.selectList("job.selectJobResultDetail", jobResultNumber);
	}

	// 직군 판정
	public void judgeJobResult(JobResultDTO jobResultDTO) {
		sqlSession.update("job.judgeJobResult", jobResultDTO);
	}
}