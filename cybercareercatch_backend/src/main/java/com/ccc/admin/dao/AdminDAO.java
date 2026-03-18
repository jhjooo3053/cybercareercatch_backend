package com.ccc.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ccc.admin.dto.AdminCompanyListDTO;
import com.ccc.admin.dto.AdminDTO;
import com.ccc.admin.dto.AdminMemberListDTO;
import com.ccc.company.dto.CompanyDTO;
import com.ccc.company.dto.CompanyDetailDTO;
import com.ccc.member.dto.MemberDTO;
import com.ccc.mybatis.config.MyBatisConfig;

public class AdminDAO {
	public SqlSession sqlSession;

	public AdminDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	// 관리자 로그인
	public AdminDTO login(AdminDTO adminDTO) {
		return sqlSession.selectOne("admin.login", adminDTO);
	}

	// 관리자 메인 총 일반 회원 수
	public int getMemberTotal() {
		return sqlSession.selectOne("admin.getMemberTotal");
	}

	// 관리자 메인 총 기업 회원 수
	public int getCompanyMemberCount() {
		return sqlSession.selectOne("admin.getCompanyMemberCount");
	}

	// 관리자 메인 총 박람회 수
	public int getExpoCount() {
		return sqlSession.selectOne("admin.getExpoCount");
	}

	// 관리자 일반 회원 목록 조회
	public List<AdminMemberListDTO> selectMemberList(Map<String, Integer> pageMap) {
		return sqlSession.selectList("admin.selectMemberList", pageMap);
	}

	// 관리자 일반 회원 기본 정보 조회
	public AdminMemberListDTO selectMemberUserInfo(int userNumber) {
		return sqlSession.selectOne("admin.selectMemberUserInfo", userNumber);
	}

	// 관리자 일반 회원 상세 정보 조회
	public MemberDTO selectMemberDetail(int userNumber) {
		return sqlSession.selectOne("admin.selectMemberDetail", userNumber);
	}

	// 관리자 일반 회원 삭제
	public void deleteMember(int userNumber) {
		sqlSession.delete("admin.deleteMember", userNumber);
	}

	// 관리자 기업 회원 승인/반려 목록 조회
	public List<AdminCompanyListDTO> selectCompanyApproveList(Map<String, Integer> pageMap) {
		return sqlSession.selectList("admin.selectCompanyApproveList", pageMap);
	}

	// 관리자 기업 회원 승인/반려 총 개수
	public int getCompanyApproveTotal() {
		return sqlSession.selectOne("admin.getCompanyApproveTotal");
	}

	// 관리자 기업 회원 승인/반려 상세 정보 조회
	public CompanyDetailDTO selectCompanyApproveDetail(int companyNumber) {
		return sqlSession.selectOne("admin.selectCompanyApproveDetail", companyNumber);
	}

	// 관리자 기업 회원 승인/반려 상태 변경
	public void updateCompanyState(CompanyDTO companyDTO) {
		sqlSession.update("admin.updateCompanyState", companyDTO);
	}
}