package com.ccc.roadmap.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ccc.common.config.MyBatisConfig;
import com.ccc.roadmap.dto.RoadmapCurriculumDTO;
import com.ccc.roadmap.dto.RoadmapDTO;
import com.ccc.roadmap.dto.RoadmapJobDTO;
import com.ccc.roadmap.dto.RoadmapRecDTO;

/*
 * 로드맵 관련 DB 작업을 담당하는 DAO이다.
 */
public class RoadmapDAO {

	public SqlSession sqlSession;

	public RoadmapDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	/* 로드맵 기본 정보 */
	public RoadmapDTO selectRoadmap(int jobNumber) {
		return sqlSession.selectOne("roadmap.selectRoadmap", jobNumber);
	}

	public void updateRoadmap(RoadmapDTO roadmapDTO) {
		sqlSession.update("roadmap.updateRoadmap", roadmapDTO);
	}

	/* 직업 목록 */
	public List<RoadmapJobDTO> selectRoadmapJobList(int jobNumber) {
		return sqlSession.selectList("roadmap.selectRoadmapJobList", jobNumber);
	}

	public void updateRoadmapJob(RoadmapJobDTO roadmapJobDTO) {
		sqlSession.update("roadmap.updateRoadmapJob", roadmapJobDTO);
	}

	public void insertRoadmapJob(RoadmapJobDTO roadmapJobDTO) {
		sqlSession.insert("roadmap.insertRoadmapJob", roadmapJobDTO);
	}

	public void deleteRoadmapJob(int roadmapJobNumber) {
		sqlSession.delete("roadmap.deleteRoadmapJob", roadmapJobNumber);
	}

	/* 커리큘럼 목록 */
	public List<RoadmapCurriculumDTO> selectCurriculumList(int jobNumber) {
		return sqlSession.selectList("roadmap.selectCurriculumList", jobNumber);
	}

	public void updateCurriculum(RoadmapCurriculumDTO roadmapCurriculumDTO) {
		sqlSession.update("roadmap.updateCurriculum", roadmapCurriculumDTO);
	}

	public void insertCurriculum(RoadmapCurriculumDTO roadmapCurriculumDTO) {
		sqlSession.insert("roadmap.insertCurriculum", roadmapCurriculumDTO);
	}

	public void deleteCurriculum(int curriculumNumber) {
		sqlSession.delete("roadmap.deleteCurriculum", curriculumNumber);
	}

	/* 추천 목록 */
	public List<RoadmapRecDTO> selectRecList(int jobNumber) {
		return sqlSession.selectList("roadmap.selectRecList", jobNumber);
	}

	public void updateRec(RoadmapRecDTO roadmapRecDTO) {
		sqlSession.update("roadmap.updateRec", roadmapRecDTO);
	}

	public void insertRec(RoadmapRecDTO roadmapRecDTO) {
		sqlSession.insert("roadmap.insertRec", roadmapRecDTO);
	}

	public void deleteRec(int recNumber) {
		sqlSession.delete("roadmap.deleteRec", recNumber);
	}
}