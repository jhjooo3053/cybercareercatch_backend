package com.ccc.company.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ccc.common.config.MyBatisConfig;
import com.ccc.company.dto.CompanyCardDTO;
import com.ccc.company.dto.CompanyDetailDTO;

public class CompanyDAO {

    public SqlSession sqlSession;

    public CompanyDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }

    // 관리자 기업 정보페이지 목록 조회
    public List<CompanyCardDTO> selectCompanyList(Map<String, Object> pageMap) {
        return sqlSession.selectList("company.selectCompanyList", pageMap);
    }

    // 관리자 기업 정보페이지 총 개수 조회
    public int getCompanyTotal(String keyword) {
        return sqlSession.selectOne("company.getCompanyTotal", keyword);
    }

    // 관리자 기업 정보페이지 상세 조회
    public CompanyDetailDTO selectCompanyDetail(int companyNumber) {
        return sqlSession.selectOne("company.selectCompanyDetail", companyNumber);
    }

    // 관리자 기업 정보페이지 대표 이미지 삭제
    public void deleteFileByCompanyNumber(int companyNumber) {
        sqlSession.delete("company.deleteFileByCompanyNumber", companyNumber);
    }

    // 관리자 기업 정보페이지 삭제
    public void deleteCompInfoByCompanyNumber(int companyNumber) {
        sqlSession.delete("company.deleteCompInfoByCompanyNumber", companyNumber);
    }
}