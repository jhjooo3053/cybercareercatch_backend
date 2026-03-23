package com.ccc.member.dao;

import org.apache.ibatis.session.SqlSession;

import com.ccc.common.config.MyBatisConfig;
import com.ccc.company.dto.CompanyJoinDTO;
import com.ccc.member.dto.MemberJoinDTO;

public class MemberDAO {
	public SqlSession sqlSession;
	
	public MemberDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	public void join(MemberJoinDTO memberJoinDTO) {
		sqlSession.insert("user.userJoin", memberJoinDTO);
		sqlSession.insert("user.memberJoin", memberJoinDTO);
	}
	
	public void joinCompanyMember(CompanyJoinDTO companyJoinDTO) {
		sqlSession.insert("user.companyUserJoin", companyJoinDTO);
		sqlSession.insert("user.companyMemberJoin", companyJoinDTO);
	}
}
