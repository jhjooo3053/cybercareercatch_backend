package com.ccc.member.dao;

import org.apache.ibatis.session.SqlSession;

import com.ccc.common.config.MyBatisConfig;
import com.ccc.member.dto.UserDTO;

public class UserDAO {
	public SqlSession sqlSession;

	public UserDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	public int login(UserDTO userDTO) {
		Integer userNumber = sqlSession.selectOne("user.login", userDTO);
		return userNumber == null ? -1 : userNumber;
	}

	public boolean checkId(String userId) {
		int count = (int) sqlSession.selectOne("user.checkId", userId);
		return count == 0;
	}

	public boolean checkIdAndPhone(UserDTO dto) {
		Integer count = sqlSession.selectOne("user.checkIdAndPhone", dto);
		return count != null && count > 0;
	}

	public boolean updatePw(UserDTO dto) {
		return sqlSession.update("user.updatePw", dto) > 0;
	}

	public String selectUserType(int userNumber) {
		return sqlSession.selectOne("user.selectUserType", userNumber);
	}

	public Integer selectCompanyNumberByUserNumber(int userNumber) {
		return sqlSession.selectOne("user.selectCompanyNumberByUserNumber", userNumber);
	}
	
	public String selectUserNameByUserNumber(int userNumber) {
		return (String) sqlSession.selectOne("user.selectUserNameByUserNumber", userNumber);
	}
}
