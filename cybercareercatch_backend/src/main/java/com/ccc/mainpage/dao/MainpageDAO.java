package com.ccc.mainpage.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ccc.common.config.MyBatisConfig;
import com.ccc.company.dto.CompanyCardDTO;
import com.ccc.expo.dto.ExpoListDTO;
import com.ccc.job.dto.JobGroupDTO;
import com.ccc.job.dto.JobRecommendQuestionDTO;
import com.ccc.post.dto.PostListDTO;
import com.ccc.qna.dto.QnaListDTO;

public class MainpageDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public MainpageDAO() {
        this.sqlSessionFactory = MyBatisConfig.getSqlSessionFactory();
    }

    // 메인 상단 질문 1개 조회
    public JobRecommendQuestionDTO selectMainQuestion() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectOne("main.selectMainQuestion");
        }
    }

    // 메인 추천 직군 유형 4개 조회
    public List<JobGroupDTO> selectMainJobGroupList() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectList("main.selectMainJobGroupList");
        }
    }

    // 메인 기업 홍보 카드 3개 조회
    public List<CompanyCardDTO> selectMainCompanyList() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectList("main.selectMainCompanyList");
        }
    }

    // 메인 자유게시판 최신글 5개 조회
    public List<PostListDTO> selectMainFreePostList() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectList("main.selectMainFreePostList");
        }
    }

    // 메인 기업 Q&A 최신글 5개 조회
    public List<QnaListDTO> selectMainQnaList() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectList("main.selectMainQnaList");
        }
    }

    // 메인 박람회 일정 조회
    public List<ExpoListDTO> selectMainExpoList() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectList("main.selectMainExpoList");
        }
    }
}