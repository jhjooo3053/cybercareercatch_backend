package com.ccc.roadmap.controller;

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
 * 회원이 보는 로드맵 상세 페이지 컨트롤러이다.
 *
 * 메인/헤더에서 직군을 클릭하면
 * jobNumber를 기준으로 해당 직군의 로드맵 정보를 조회해서
 * JSP로 전달한다.
 */
public class RoadmapDetailController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RoadmapDAO roadmapDAO = new RoadmapDAO();
		Result result = new Result();

		/* 여러 파라미터명을 모두 받아서 최대한 안전하게 처리한다. */
		int jobNumber = getJobNumber(request);

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

		List<RoadmapCurriculumDTO> basicList = curriculumList.subList(0, 3);
		List<RoadmapCurriculumDTO> securityBasicList = curriculumList.subList(3, 6);
		List<RoadmapCurriculumDTO> practiceList = curriculumList.subList(6, 9);
		List<RoadmapCurriculumDTO> resultMaterialList = curriculumList.subList(9, 12);

		List<RoadmapRecDTO> techList = recList.subList(0, 7);
		List<RoadmapRecDTO> tendencyList = recList.subList(7, 11);
		List<RoadmapRecDTO> certificateList = recList.subList(11, 15);

		request.setAttribute("jobNumber", jobNumber);
		request.setAttribute("roadmap", roadmap);
		request.setAttribute("roadmapJobList", roadmapJobList);
		request.setAttribute("curriculumList", curriculumList);
		request.setAttribute("recList", recList);

		request.setAttribute("basicList", basicList);
		request.setAttribute("securityBasicList", securityBasicList);
		request.setAttribute("practiceList", practiceList);
		request.setAttribute("resultMaterialList", resultMaterialList);

		request.setAttribute("techList", techList);
		request.setAttribute("tendencyList", tendencyList);
		request.setAttribute("certificateList", certificateList);

		request.setAttribute("roadmapTitle", getRoadmapTitle(jobNumber));
		request.setAttribute("roadmapSubDesc", getRoadmapSubDesc(jobNumber));
		request.setAttribute("roadmapIconPath", getRoadmapIconPath(jobNumber));

		result.setPath("/app/main/roadmap/roadmap-detail.jsp");
		result.setRedirect(false);

		return result;
	}

	/*
	 * jobNumber를 여러 이름으로 받아온다.
	 */
	private int getJobNumber(HttpServletRequest request) {
		String[] keys = { "jobNumber", "jobGroupNumber", "jobGroup", "groupNumber" };

		for (String key : keys) {
			String value = request.getParameter(key);

			if (value != null && !value.trim().equals("")) {
				try {
					int number = Integer.parseInt(value.trim());

					if (number >= 1 && number <= 4) {
						return number;
					}
				} catch (NumberFormatException e) {
					/* 무시하고 다음 키를 확인한다. */
				}
			}
		}

		return 1;
	}

	private String getRoadmapTitle(int jobNumber) {
		switch (jobNumber) {
		case 1:
			return "보안컨설팅 계열 직군 로드맵";
		case 2:
			return "시스템/네트워크 엔지니어 로드맵";
		case 3:
			return "보안관제 로드맵";
		case 4:
			return "침해사고/디지털 포렌식 로드맵";
		default:
			return "로드맵";
		}
	}

	private String getRoadmapSubDesc(int jobNumber) {
		switch (jobNumber) {
		case 1:
			return "IT 인프라와 서비스 운영 전반의 보안 수준을 진단하고, 조직 환경에 맞는 기준·개선 전략·이행 계획을 수립해 보안 체계를 체계적으로 정착시키는 직군입니다.";
		case 2:
			return "서버·네트워크·클라우드 등 인프라를 설계·구축·운영하며, 성능·장애·보안 이슈를 안정적으로 관리해 서비스 가용성을 유지하는 직군입니다.";
		case 3:
			return "다양한 보안 로그와 이벤트를 실시간으로 분석해 이상 징후를 탐지하고, 초기 대응과 확산 차단을 수행해 조직의 보안 사고 가능성을 낮추는 직군입니다.";
		case 4:
			return "침해사고 발생 시 증거를 기반으로 원인과 영향을 분석하고, 확산 차단·복구·재발방지까지 수행하며 디지털 증거를 체계적으로 확보하는 직군입니다.";
		default:
			return "";
		}
	}

	private String getRoadmapIconPath(int jobNumber) {
		switch (jobNumber) {
		case 1:
			return "보안컨설팅 계열 직군 마크.png";
		case 2:
			return "시스템 네트워크 엔지니어 마크.png";
		case 3:
			return "보안관제.png";
		case 4:
			return "침해 사고 디지털 포렌식.png";
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