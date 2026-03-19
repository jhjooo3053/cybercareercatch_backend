package com.ccc.mypage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ccc.common.config.MyBatisConfig;
import com.ccc.company.dto.CompanyInfoDTO;
import com.ccc.company.dto.CompanyDetailDTO;
import com.ccc.company.dto.FileDTO;
import com.ccc.company.dto.JobPostDTO;
import com.ccc.mypage.dto.CompanyMypageInfoDTO;
import com.ccc.mypage.dto.MemberMypageInfoDTO;
import com.ccc.mypage.dto.MypageQnaListDTO;

public class MypageDAO {
	private SqlSession sqlSession;

	public MypageDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

//=========일반회원 마이페이지============

	// 일반회원 정보조회
	public MemberMypageInfoDTO selectMemberMyPageInfo(int userNumber) {
		return sqlSession.selectOne("myPage.selectMemberMyPageInfo", userNumber);
	}

	// 일반회원 비밀번호 확인
	public boolean checkMemberPw(int userNumber, String userPw) {
		System.out.println("일반회원 비밀번호 확인 메소드 실행");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userNumber", userNumber);
		paramMap.put("userPw", userPw);

		return (Integer)sqlSession.selectOne("myPage.checkMemberPw", paramMap) < 1;
	}

	// 일반회원 전화번호 수정
	public void updateMemberPhone(int userNumber, String userPhone) {
		System.out.println("일반회원 전화번호 수정 메소드 실행");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userNumber", userNumber);
		paramMap.put("userPhone", userPhone);

		sqlSession.update("myPage.updateMemberPhone", paramMap);
	}

	// 일반회원 비밀번호 수정
	public void updateMemberPw(int userNumber, String newUserPw) {
		System.out.println("일반회원 비밀번호 수정 메소드 실행");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userNumber", userNumber);
		paramMap.put("newUserPw", newUserPw);

		sqlSession.update("myPage.updateMemberPw", paramMap);
	}

	// 일반회원 나의 Q&A 리스트 조회
	public List<MypageQnaListDTO> selectMyQnaList(int userNumber) {
		System.out.println("일반회원 나의 Q&A 리스트 조회 메소드");
		return sqlSession.selectList("myPage.selectMyQnaList", userNumber);
	}

	// 일반회원 나의 Q&A 리스트 총 개수
	public int myQnaTotal(int userNumber) {
		return sqlSession.selectOne("myPage.myQnaTotal", userNumber);
	}

	// 일반회원 나의 Q&A 선택 삭제
	public void deleteMyQnaPosts(int userNumber, List<Integer> postNumbers) {
		System.out.println("일반회원 나의 Q&A 선택 삭제 메소드 실행");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userNumber", userNumber);
		paramMap.put("postNumbers", postNumbers);

		sqlSession.delete("myPage.deleteMyQnaPosts", paramMap);
	}

	// 일반회원 회원탈퇴
	public void deleteMemberByUserNumber(int userNumber) {
		System.out.println("일반회원 회원탈퇴 메호드 실행");
		sqlSession.delete("myPage.deleteMemberByUserNumber", userNumber);
	}

//==========기업회원 마이페이지=============

	// 기업회원 기본정보 조회
	public CompanyMypageInfoDTO selectCompanyMemberMyPageInfo(int userNumber) {
		System.out.println("기업회원 기본정보 조회 메소드 실행");
		return sqlSession.selectOne("myPage.selectCompanyMemberMyPageInfo", userNumber);
	}

	// 기업회원 답변대기 Q&A글 총 개수
	public int compQnaTotal(int companyNumber) {
		System.out.println("기업회원 답변대기 Q&A글 총 개수");
		return sqlSession.selectOne("myPage.compQnaTotal", companyNumber);
	}
	
	// 기업회원 답변대기 Q&A 조회
	public List<MypageQnaListDTO> selectWaitingQnaListByCompanyUser(int userNumber) {
		System.out.println("기업회원 답변대기 Q&A 조회");
		return sqlSession.selectList("myPage.selectWaitingQnaListByCompanyUser", userNumber);
	}

	// 기업회원 비밀번호 확인
	public int checkCompanyPw(int userNumber, String userPw) {
		System.out.println("기업회원 비밀번호 확인 메소드 실행");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userNumber", userNumber);
		paramMap.put("userPw", userPw);

		return sqlSession.selectOne("myPage.checkCompanyPw", paramMap);
	}

	// 기업회원 전화번호 수정
	public void updateCompanyPhone(int userNumber, String userPhone) {
		System.out.println("기업회원 전화번호 수정 메소드 실행");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userNumber", userNumber);
		paramMap.put("userPhone", userPhone);

		sqlSession.update("myPage.updateCompanyPhone", paramMap);
	}

	// 기업회원 비밀번호 수정
	public void updateCompanyPw(int userNumber, String newUserPw) {
		System.out.println("기업회원 비밀번호 수정 메소드 실행");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userNumber", userNumber);
		paramMap.put("newUserPw", newUserPw);

		sqlSession.update("myPage.updateCompanyPw", paramMap);
	}

	// 기업회원 회원탈퇴
//	public void deleteCompanyUserByUserNumber(int userNumber) {
//		sqlSession.delete("myPage.deleteCompanyUserByUserNumber", userNumber);
//	}

	// 기업정보페이지 존재 여부 확인
	public int countCompanyPageByCompanyNumber(int companyNumber) {
		System.out.println("기업정보페이지 존재 여부 확인 메소드");
		return sqlSession.selectOne("myPage.countCompanyPageByCompanyNumber", companyNumber);
	}

	// 기업정보페이지 상세조회
	public CompanyDetailDTO selectCompanyPageDetail(int companyNumber) {
		System.out.println("기업정보페이지 상세조회 메소드");
		return sqlSession.selectOne("myPage.selectCompanyPageDetail", companyNumber);
	}

	// 기업정보페이지 등록
	public void insertCompInfo(CompanyInfoDTO companyInfoDTO) {
		System.out.println("기업정보페이지 등록(기업소개) 메소드");
		sqlSession.insert("myPage.insertCompInfo", companyInfoDTO);
	}

	public void insertFile(FileDTO fileDTO) {
		System.out.println("기업정보페이지 등록(파일) 메소드");
		sqlSession.insert("myPage.insertFile", fileDTO);
	}

	public void insertJobPost(JobPostDTO jobPostDTO) {
		System.out.println("기업정보페이지 등록(채용공고) 메소드");
		sqlSession.insert("myPage.insertJobPost", jobPostDTO);
	}

	// 기업정보페이지 수정
	public void updateCompInfo(CompanyInfoDTO companyInfoDTO) {
		System.out.println("기업정보페이지 수정(기업소개) 메소드");
		sqlSession.update("myPage.updateCompInfo", companyInfoDTO);
	}

	public void updateFile(FileDTO fileDTO) {
		System.out.println("기업정보페이지 수정(파일) 메소드");
		sqlSession.update("myPage.updateFile", fileDTO);
	}

	public void updateJobPost(JobPostDTO jobPostDTO) {
		System.out.println("기업정보페이지 수정(채용공고) 메소드");
		sqlSession.update("myPage.updateJobPost", jobPostDTO);
	}

	// 기업정보페이지 삭제
	public void deleteFileByCompanyNumber(int companyNumber) {
		System.out.println("기업정포페이지 삭제(파일) 메소드");
		sqlSession.delete("myPage.deleteFileByCompanyNumber", companyNumber);
	}

	public void deleteCompInfoByCompanyNumber(int companyNumber) {
		System.out.println("기업정보페이지 삭제(기업소개) 메소드 ");
		sqlSession.delete("myPage.deleteCompInfoByCompanyNumber", companyNumber);
	}
}