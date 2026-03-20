package com.ccc.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.common.Execute;
import com.ccc.common.Result;
import com.ccc.roadmap.dao.RoadmapDAO;
import com.ccc.roadmap.dto.RoadmapCurriculumDTO;
import com.ccc.roadmap.dto.RoadmapDTO;
import com.ccc.roadmap.dto.RoadmapJobDTO;
import com.ccc.roadmap.dto.RoadmapRecDTO;

/*
 * 관리자 로드맵 관리 페이지로 이동시키는 컨트롤러이다.
 */
public class AdminRoadmapManagementController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RoadmapDAO roadmapDAO = new RoadmapDAO();
		Result result = new Result();

		int jobNumber = 1;
		String temp = request.getParameter("jobNumber");

		if (temp != null && !temp.trim().equals("")) {
			jobNumber = Integer.parseInt(temp);
		}

		RoadmapDTO roadmap = roadmapDAO.selectRoadmap(jobNumber);
		List<RoadmapJobDTO> roadmapJobList = roadmapDAO.selectRoadmapJobList(jobNumber);
		List<RoadmapCurriculumDTO> curriculumList = roadmapDAO.selectCurriculumList(jobNumber);
		List<RoadmapRecDTO> recList = roadmapDAO.selectRecList(jobNumber);

		if (roadmap == null) {
			roadmap = new RoadmapDTO();
			roadmap.setJobNumber(jobNumber);
			roadmap.setRoadmapImagePath("");
		}

		roadmapJobList = normalizeRoadmapJobList(roadmapJobList);
		curriculumList = normalizeCurriculumList(curriculumList);
		recList = normalizeRecList(recList);

		request.setAttribute("jobNumber", jobNumber);
		request.setAttribute("roadmap", roadmap);
		request.setAttribute("roadmapJobList", roadmapJobList);
		request.setAttribute("curriculumList", curriculumList);
		request.setAttribute("recList", recList);

		request.setAttribute("roadmapTitle", getRoadmapTitle(jobNumber));
		request.setAttribute("roadmapSubDesc", getRoadmapSubDesc(jobNumber));

		result.setPath("/app/admin/main-management/roadmap-management.jsp");
		result.setRedirect(false);

		return result;
	}

	private String getRoadmapTitle(int jobNumber) {
		switch (jobNumber) {
		case 1:
			return "보안컨설팅";
		case 2:
			return "시스템/네트워크 엔지니어";
		case 3:
			return "보안관제";
		case 4:
			return "침해사고/디지털 포렌식";
		default:
			return "로드맵";
		}
	}

	private String getRoadmapSubDesc(int jobNumber) {
		switch (jobNumber) {
		case 1:
			return "IT 인프라와 서비스 운영 전반의 보안 수준을 진단하고 조직 환경에 맞는 기준과 개선 전략을 수립하는 직군";
		case 2:
			return "서버·네트워크·클라우드 인프라를 설계·구축·운영하며 서비스 가용성을 유지하는 직군";
		case 3:
			return "보안 이벤트를 실시간으로 분석하고 이상 징후를 탐지·대응하는 직군";
		case 4:
			return "침해사고 발생 시 원인과 영향을 분석하고 증거를 확보·복구하는 직군";
		default:
			return "";
		}
	}

	private List<RoadmapJobDTO> normalizeRoadmapJobList(List<RoadmapJobDTO> sourceList) {
		List<RoadmapJobDTO> resultList = new ArrayList<RoadmapJobDTO>();

		if (sourceList != null) {
			resultList.addAll(sourceList);
		}

		while (resultList.size() < 6) {
			RoadmapJobDTO emptyDTO = new RoadmapJobDTO();
			emptyDTO.setRoadmapJobName("");
			emptyDTO.setRoadmapJobDesc("");
			resultList.add(emptyDTO);
		}

		while (resultList.size() > 6) {
			resultList.remove(resultList.size() - 1);
		}

		return resultList;
	}

	private List<RoadmapCurriculumDTO> normalizeCurriculumList(List<RoadmapCurriculumDTO> sourceList) {
		List<RoadmapCurriculumDTO> basicList = new ArrayList<RoadmapCurriculumDTO>();
		List<RoadmapCurriculumDTO> securityBasicList = new ArrayList<RoadmapCurriculumDTO>();
		List<RoadmapCurriculumDTO> practiceList = new ArrayList<RoadmapCurriculumDTO>();
		List<RoadmapCurriculumDTO> resultMaterialList = new ArrayList<RoadmapCurriculumDTO>();

		if (sourceList != null) {
			for (RoadmapCurriculumDTO curriculumDTO : sourceList) {
				if ("기초 다지기".equals(curriculumDTO.getSkillType())) {
					basicList.add(curriculumDTO);
					continue;
				}
				if ("보안기초 다지기".equals(curriculumDTO.getSkillType())) {
					securityBasicList.add(curriculumDTO);
					continue;
				}
				if ("실습".equals(curriculumDTO.getSkillType())) {
					practiceList.add(curriculumDTO);
					continue;
				}
				if ("결과물".equals(curriculumDTO.getSkillType())) {
					resultMaterialList.add(curriculumDTO);
				}
			}
		}

		fillCurriculumEmptyData(basicList, "기초 다지기", 3);
		fillCurriculumEmptyData(securityBasicList, "보안기초 다지기", 3);
		fillCurriculumEmptyData(practiceList, "실습", 3);
		fillCurriculumEmptyData(resultMaterialList, "결과물", 3);

		List<RoadmapCurriculumDTO> resultList = new ArrayList<RoadmapCurriculumDTO>();
		resultList.addAll(basicList);
		resultList.addAll(securityBasicList);
		resultList.addAll(practiceList);
		resultList.addAll(resultMaterialList);

		return resultList;
	}

	private List<RoadmapRecDTO> normalizeRecList(List<RoadmapRecDTO> sourceList) {
		List<RoadmapRecDTO> techList = new ArrayList<RoadmapRecDTO>();
		List<RoadmapRecDTO> tendencyList = new ArrayList<RoadmapRecDTO>();
		List<RoadmapRecDTO> certificateList = new ArrayList<RoadmapRecDTO>();

		if (sourceList != null) {
			for (RoadmapRecDTO recDTO : sourceList) {
				if ("추천기술".equals(recDTO.getRecType())) {
					techList.add(recDTO);
					continue;
				}
				if ("추천성향".equals(recDTO.getRecType())) {
					tendencyList.add(recDTO);
					continue;
				}
				if ("추천자격증".equals(recDTO.getRecType())) {
					certificateList.add(recDTO);
				}
			}
		}

		fillRecEmptyData(techList, "추천기술", 7);
		fillRecEmptyData(tendencyList, "추천성향", 4);
		fillRecEmptyData(certificateList, "추천자격증", 4);

		List<RoadmapRecDTO> resultList = new ArrayList<RoadmapRecDTO>();
		resultList.addAll(techList);
		resultList.addAll(tendencyList);
		resultList.addAll(certificateList);

		return resultList;
	}

	private void fillCurriculumEmptyData(List<RoadmapCurriculumDTO> targetList, String skillType, int maxSize) {
		while (targetList.size() < maxSize) {
			RoadmapCurriculumDTO emptyDTO = new RoadmapCurriculumDTO();
			emptyDTO.setSkillType(skillType);
			emptyDTO.setSkillName("");
			emptyDTO.setSkillDesc("");
			targetList.add(emptyDTO);
		}

		while (targetList.size() > maxSize) {
			targetList.remove(targetList.size() - 1);
		}
	}

	private void fillRecEmptyData(List<RoadmapRecDTO> targetList, String recType, int maxSize) {
		while (targetList.size() < maxSize) {
			RoadmapRecDTO emptyDTO = new RoadmapRecDTO();
			emptyDTO.setRecType(recType);
			emptyDTO.setRecName("");
			targetList.add(emptyDTO);
		}

		while (targetList.size() > maxSize) {
			targetList.remove(targetList.size() - 1);
		}
	}
}