package com.ccc.mainpage.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ccc.common.config.MyBatisConfig;
import com.ccc.company.dto.CompanyCardDTO;
import com.ccc.expo.dto.ExpoListDTO;
import com.ccc.job.dto.JobGroupDTO;
import com.ccc.job.dto.JobRecommendQuestionDTO;
import com.ccc.job.dto.JobTestSubmitDTO;
import com.ccc.post.dto.PostListDTO;
import com.ccc.qna.dto.QnaListDTO;

public class MainpageDAO {

    private final SqlSession sqlSession;
    
    

    public MainpageDAO() {
		this.sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	// 메인 상단 질문 1개 조회
    public JobRecommendQuestionDTO selectMainQuestion(int jobQuestionNumber) {
    	System.out.println("메인 상단 질문 1개 조회");
            return sqlSession.selectOne("main.selectMainQuestion",jobQuestionNumber);
    }
    
    
	// 메인 상단 질문 1개 저장
    public void insertMainAnswer(JobTestSubmitDTO jobTestSubmitDTO) {
    	System.out.println("메인 상단 질문에 대한 답변 1개 저장");
            sqlSession.insert("main.insertMainAnswer",jobTestSubmitDTO);  //매개변수의 값을 쿼리문에 저장하고 main.insertMainAnswer 에 저장한다. 
            															//db에 저장하도록 하는게 목적이다 어디에서 가져와서 - 매퍼
    }
    

    
    // 메인 추천 직군 유형 4개 조회
    
    public List<JobGroupDTO> selectMainJobGroupList() {   
    	System.out.println("메인 추천 직군 유형 4개 조회"); //이거는 반환타입이 list고 그걸 저장하는데가 selectmainjopgrouplist인거고 그걸 쿼리문에서 실행해서 조회할수있도록 전달하는거야?
            return sqlSession.selectList("main.selectMainJobGroupList"); 
            
    }
    //------------------------------------------------------------------------------------   
    
    
    // 메인 기업 홍보 카드 3개 조회
    public List<CompanyCardDTO> selectMainCompanyList() {
    	System.out.println("메인 기업홍보카드 3개 조회");
            return sqlSession.selectList("main.selectMainCompanyList");
            
        
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