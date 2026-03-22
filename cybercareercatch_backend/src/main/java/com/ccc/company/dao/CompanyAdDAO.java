package com.ccc.company.dao; // 회사 DAO 패키지이다.

import java.util.List; // 여러 개의 기업 카드를 리스트로 받기 위해 가져온다.

import org.apache.ibatis.session.SqlSession; // MyBatis SqlSession을 사용하기 위해 가져온다.

import com.ccc.common.config.MyBatisConfig; // MyBatis 설정 클래스를 사용하기 위해 가져온다.
import com.ccc.company.dto.CompanyCardDTO; // 기업 카드 DTO를 사용하기 위해 가져온다.

public class CompanyAdDAO { // 기업홍보 목록 관련 DB 작업을 담당하는 DAO 클래스이다.

	public SqlSession sqlSession; // MyBatis에서 SQL을 실행할 때 사용할 객체이다.

	public CompanyAdDAO() { // DAO 객체가 생성될 때 SqlSession도 같이 생성하는 생성자이다.
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true); // 자동 커밋이 켜진 SqlSession을 생성한다.
	}

	public int selectCompanyAdCount(CompanyCardDTO companyCardDTO) { // 기업홍보 목록 총 개수를 조회하는 메소드이다.
		System.out.println("기업홍보 목록 총 개수 조회 DAO"); // 총 개수 조회 로그를 출력한다.
		return sqlSession.selectOne("companyAd.companyadcount", companyCardDTO); // mapper의 companyadcount 쿼리를 실행해서 개수를 반환한다.
	}

	public List<CompanyCardDTO> selectCompanyAdList(CompanyCardDTO companyCardDTO) { // 현재 페이지에 보여줄 기업홍보 목록을 조회하는 메소드이다.
		System.out.println("기업홍보 목록 조회 DAO"); // 목록 조회 로그를 출력한다.
		return sqlSession.selectList("companyAd.companyadlist", companyCardDTO); // mapper의 companyadlist 쿼리를 실행해서 목록을 반환한다.
	}
}