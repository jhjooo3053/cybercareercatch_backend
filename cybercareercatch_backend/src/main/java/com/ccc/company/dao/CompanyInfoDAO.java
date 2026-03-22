package com.ccc.company.dao;

import org.apache.ibatis.session.SqlSession;

import com.ccc.common.config.MyBatisConfig;
import com.ccc.company.dto.CompanyDetailDTO;
import com.ccc.company.dto.CompanyInfoDTO;

public class CompanyInfoDAO {
	private final SqlSession sqlSession;

	public CompanyInfoDAO() {
		this.sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
//    <!-- 1. 기업 정보 존재 여부 확인 -->
//    <select id="companyinfonum" parameterType="int" resultType="int">
//        SELECT COUNT(*)
//        FROM TBL_COMP_INFO
//        WHERE COMPANY_NUMBER = #{companyNumber}
//    </select>

	public int companyinfonum(int companyNumber) {
		System.out.println("기업정보 존재 여부 확인메소드");
		return sqlSession.selectOne("companyInfo.companyinfonum", companyNumber);
	}

//    2. 기업정보등록 메소드
	public void insertCompanyInfo(CompanyInfoDTO companyinfoDTO) {
		System.out.println("기업정보 존재 여부 확인");
		sqlSession.insert("companyInfo.insertCompanyInfo", companyinfoDTO);
	}
	
//	3.기업 정보 페이지 조회
	public CompanyDetailDTO selectCompanyInfoPage(int userNumber) {
		System.out.println("기업정보페이지 조회");
		return sqlSession.selectOne("companyInfo.selectCompanyInfoPage", userNumber);
	}
}