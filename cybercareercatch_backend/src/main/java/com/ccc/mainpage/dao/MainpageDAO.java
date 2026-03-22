package com.ccc.mainpage.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ccc.common.config.MyBatisConfig;
import com.ccc.company.dto.CompanyCardDTO;
import com.ccc.expo.dto.ExpoListDTO;
import com.ccc.job.dto.JobGroupDTO;
import com.ccc.job.dto.JobRecommendQuestionDTO;
import com.ccc.job.dto.JobTestResultDTO;
import com.ccc.job.dto.JobTestSubmitDTO;
import com.ccc.post.dto.PostListDTO;
import com.ccc.qna.dto.QnaListDTO;

public class MainpageDAO {

    private final SqlSession sqlSession;

    public MainpageDAO() {
        this.sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }

<<<<<<< HEAD
	// 메인 상단 질문 1개 조회
    public JobRecommendQuestionDTO selectMainQuestion(JobRecommendQuestionDTO jobQuestionNumber) {
    	System.out.println("메인 상단 질문 1개 조회");
            return sqlSession.selectOne("main.selectMainQuestion",jobQuestionNumber);
=======
    // 메인 상단 질문 1개 조회
    public JobRecommendQuestionDTO selectMainQuestion(int jobQuestionNumber) {
        System.out.println("메인 상단 질문 1개 조회");
        return sqlSession.selectOne("main.selectMainQuestion", jobQuestionNumber);
>>>>>>> 8158ab0 (260322 김진옥메인 수정)
    }

    // 메인 질문 총 개수 조회
    public int selectMainQuestionCount() {
        System.out.println("메인 질문 총 개수 조회");
        Integer count = sqlSession.selectOne("main.selectMainQuestionCount");
        return count == null ? 0 : count;
    }

    // 일반회원 여부 확인
    public boolean isGeneralMember(int userNumber) {
        System.out.println("일반회원 여부 확인");
        Integer count = sqlSession.selectOne("main.checkGeneralMember", userNumber);
        return count != null && count > 0;
    }

    // 직군검사 결과 테이블 생성
    public void insertMainJobResult(JobTestResultDTO jobTestResultDTO) {
        System.out.println("메인 직군검사 결과 1건 생성");
        sqlSession.insert("main.insertMainJobResult", jobTestResultDTO);
    }

    // 직군검사 최종 제출 처리
    public void updateMainJobResultSubmitted(int jobResultNumber) {
        System.out.println("메인 직군검사 제출완료 처리");
        sqlSession.update("main.updateMainJobResultSubmitted", jobResultNumber);
    }

    // 메인 상단 질문 1개 저장
    public void insertMainAnswer(JobTestSubmitDTO jobTestSubmitDTO) {
        System.out.println("메인 상단 질문에 대한 답변 1개 저장");
        sqlSession.insert("main.insertMainAnswer", jobTestSubmitDTO);
    }

    // 진행중 답변 삭제
    public void deleteMainJobResultAnswers(int jobResultNumber) {
        System.out.println("진행중 직군검사 답변 삭제");
        sqlSession.delete("main.deleteMainJobResultAnswers", jobResultNumber);
    }

    // 진행중 결과 삭제
    public void deleteMainJobResult(int jobResultNumber) {
        System.out.println("진행중 직군검사 결과 삭제");
        sqlSession.delete("main.deleteMainJobResult", jobResultNumber);
    }

    // 메인 추천 직군 유형 4개 조회
    public List<JobGroupDTO> selectMainJobGroupList() {
        System.out.println("메인 추천 직군 유형 4개 조회");
        return sqlSession.selectList("main.selectMainJobGroupList");
    }

    // 메인 기업 홍보 카드 3개 조회
    public List<CompanyCardDTO> selectMainCompanyList() {
        System.out.println("메인 기업홍보카드 최신순 3개 조회");

        CompanyCardDTO companyCardDTO = new CompanyCardDTO();
        companyCardDTO.setSortType("latest");
        companyCardDTO.setStartRow(1);
        companyCardDTO.setEndRow(3);

        return sqlSession.selectList("companyAd.companyadlist", companyCardDTO);
    }

    // 메인 자유게시판 최신글 5개 조회
    public List<PostListDTO> selectMainFreePostList() {
        System.out.println("메인 자유게시판 최신글 5개 조회");
        return sqlSession.selectList("main.selectMainFreePostList");
    }

    // 메인 기업 Q&A 최신글 5개 조회
    public List<QnaListDTO> selectMainQnaList() {
        System.out.println("메인 기업 Q&A 최신글 5개 조회");
        return sqlSession.selectList("main.selectMainQnaList");
    }

    // 메인 박람회 일정 조회
    public List<ExpoListDTO> selectMainExpoList() {
        System.out.println("메인 박람회 일정 조회");
        return sqlSession.selectList("main.selectMainExpoList");
    }
}