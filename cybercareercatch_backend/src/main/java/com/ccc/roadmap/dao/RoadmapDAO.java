package com.ccc.roadmap.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ccc.mybatis.config.MyBatisConfig;
import com.ccc.roadmap.dto.RoadmapCurriculumDTO;
import com.ccc.roadmap.dto.RoadmapDTO;
import com.ccc.roadmap.dto.RoadmapJobDTO;
import com.ccc.roadmap.dto.RoadmapRecDTO;

public class RoadmapDAO {
	public SqlSession sqlSession;

	public RoadmapDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	// 로드맵 기본 조회
	public RoadmapDTO selectRoadmap(int jobNumber) {
		return sqlSession.selectOne("roadmap.selectRoadmap", jobNumber);
	}

	// 로드맵 이미지 수정
	public void updateRoadmap(RoadmapDTO roadmapDTO) {
		sqlSession.update("roadmap.updateRoadmap", roadmapDTO);
	}

	// 로드맵 직업 목록 조회
	public List<RoadmapJobDTO> selectRoadmapJobList(int jobNumber) {
		return sqlSession.selectList("roadmap.selectRoadmapJobList", jobNumber);
	}

	// 로드맵 직업 수정
	public void updateRoadmapJob(RoadmapJobDTO roadmapJobDTO) {
		sqlSession.update("roadmap.updateRoadmapJob", roadmapJobDTO);
	}

	// 로드맵 직업 추가
	public void insertRoadmapJob(RoadmapJobDTO roadmapJobDTO) {
		sqlSession.insert("roadmap.insertRoadmapJob", roadmapJobDTO);
	}

	// 로드맵 직업 삭제
	public void deleteRoadmapJob(int roadmapJobNumber) {
		sqlSession.delete("roadmap.deleteRoadmapJob", roadmapJobNumber);
	}

	// 로드맵 커리큘럼 목록 조회
	public List<RoadmapCurriculumDTO> selectCurriculumList(int jobNumber) {
		return sqlSession.selectList("roadmap.selectCurriculumList", jobNumber);
	}

	// 로드맵 커리큘럼 수정
	public void updateCurriculum(RoadmapCurriculumDTO roadmapCurriculumDTO) {
		sqlSession.update("roadmap.updateCurriculum", roadmapCurriculumDTO);
	}

	// 로드맵 커리큘럼 추가
	public void insertCurriculum(RoadmapCurriculumDTO roadmapCurriculumDTO) {
		sqlSession.insert("roadmap.insertCurriculum", roadmapCurriculumDTO);
	}

	// 로드맵 커리큘럼 삭제
	public void deleteCurriculum(int curriculumNumber) {
		sqlSession.delete("roadmap.deleteCurriculum", curriculumNumber);
	}

	// 로드맵 추천 목록 조회
	public List<RoadmapRecDTO> selectRecList(int jobNumber) {
		return sqlSession.selectList("roadmap.selectRecList", jobNumber);
	}

	// 로드맵 추천 수정
	public void updateRec(RoadmapRecDTO roadmapRecDTO) {
		sqlSession.update("roadmap.updateRec", roadmapRecDTO);
	}

	// 로드맵 추천 추가
	public void insertRec(RoadmapRecDTO roadmapRecDTO) {
		sqlSession.insert("roadmap.insertRec", roadmapRecDTO);
	}

	// 로드맵 추천 삭제
	public void deleteRec(int recNumber) {
		sqlSession.delete("roadmap.deleteRec", recNumber);
	}
}