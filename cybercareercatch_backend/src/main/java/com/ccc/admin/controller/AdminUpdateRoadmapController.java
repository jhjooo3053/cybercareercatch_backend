package com.ccc.admin.controller;

import java.io.File;
import java.io.IOException;

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
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/*
 * 관리자 로드맵 수정 저장을 처리하는 컨트롤러이다.
 * 기존 텍스트 경로 입력 방식이 아니라 실제 이미지 파일 업로드 방식으로 수정하였다.
 */
public class AdminUpdateRoadmapController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RoadmapDAO roadmapDAO = new RoadmapDAO();
		Result result = new Result();

		String uploadPath = request.getSession()
				.getServletContext()
				.getRealPath("/upload/roadmap");

		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		int fileSize = 1024 * 1024 * 20; // 20MB

		MultipartRequest multipartRequest = new MultipartRequest(
				request,
				uploadPath,
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy()
		);

		int jobNumber = Integer.parseInt(multipartRequest.getParameter("jobNumber"));

		/* 1. 이미지 파일 저장 */
		String currentRoadmapImagePath = getTrimValue(multipartRequest.getParameter("currentRoadmapImagePath"));
		String uploadedFileName = multipartRequest.getFilesystemName("roadmapImageFile");

		String roadmapImagePath = currentRoadmapImagePath;

		if (!getTrimValue(uploadedFileName).equals("")) {
			roadmapImagePath = "/upload/roadmap/" + uploadedFileName;
		}

		if (!roadmapImagePath.equals("")) {
			RoadmapDTO roadmapDTO = new RoadmapDTO();
			roadmapDTO.setJobNumber(jobNumber);
			roadmapDTO.setRoadmapImagePath(roadmapImagePath);
			roadmapDAO.updateRoadmap(roadmapDTO);
		}

		/* 2. 직업 6개 수정 */
		for (int i = 1; i <= 6; i++) {
			String roadmapJobNumber = getTrimValue(multipartRequest.getParameter("roadmapJobNumber" + i));
			String roadmapJobName = getTrimValue(multipartRequest.getParameter("roadmapJobName" + i));
			String roadmapJobDesc = getTrimValue(multipartRequest.getParameter("roadmapJobDesc" + i));

			if (roadmapJobNumber.equals("")) {
				continue;
			}

			if (roadmapJobName.equals("") || roadmapJobDesc.equals("")) {
				continue;
			}

			RoadmapJobDTO roadmapJobDTO = new RoadmapJobDTO();
			roadmapJobDTO.setRoadmapJobNumber(Integer.parseInt(roadmapJobNumber));
			roadmapJobDTO.setJobNumber(jobNumber);
			roadmapJobDTO.setRoadmapJobName(roadmapJobName);
			roadmapJobDTO.setRoadmapJobDesc(roadmapJobDesc);

			roadmapDAO.updateRoadmapJob(roadmapJobDTO);
		}

		/* 3. 커리큘럼 12개 수정 */
		for (int i = 1; i <= 12; i++) {
			String curriculumNumber = getTrimValue(multipartRequest.getParameter("curriculumNumber" + i));
			String skillName = getTrimValue(multipartRequest.getParameter("skillName" + i));
			String skillDesc = getTrimValue(multipartRequest.getParameter("skillDesc" + i));

			if (curriculumNumber.equals("")) {
				continue;
			}

			if (skillName.equals("") || skillDesc.equals("")) {
				continue;
			}

			RoadmapCurriculumDTO curriculumDTO = new RoadmapCurriculumDTO();
			curriculumDTO.setCurriculumNumber(Integer.parseInt(curriculumNumber));
			curriculumDTO.setJobNumber(jobNumber);
			curriculumDTO.setSkillType(getSkillType(i));
			curriculumDTO.setSkillName(skillName);
			curriculumDTO.setSkillDesc(skillDesc);

			roadmapDAO.updateCurriculum(curriculumDTO);
		}

		/* 4. 추천 15개 수정 */
		for (int i = 1; i <= 15; i++) {
			String recNumber = getTrimValue(multipartRequest.getParameter("recNumber" + i));
			String recName = getTrimValue(multipartRequest.getParameter("recName" + i));

			if (recNumber.equals("")) {
				continue;
			}

			if (recName.equals("")) {
				continue;
			}

			RoadmapRecDTO recDTO = new RoadmapRecDTO();
			recDTO.setRecNumber(Integer.parseInt(recNumber));
			recDTO.setJobNumber(jobNumber);
			recDTO.setRecType(getRecType(i));
			recDTO.setRecName(recName);

			roadmapDAO.updateRec(recDTO);
		}

		result.setPath(request.getContextPath() + "/admin/roadmapManagement.adfc?jobNumber=" + jobNumber);
		result.setRedirect(true);

		return result;
	}

	private String getSkillType(int index) {
		if (index >= 1 && index <= 3) {
			return "기초 다지기";
		}
		if (index >= 4 && index <= 6) {
			return "보안기초 다지기";
		}
		if (index >= 7 && index <= 9) {
			return "실습";
		}
		return "결과물";
	}

	private String getRecType(int index) {
		if (index >= 1 && index <= 7) {
			return "추천기술";
		}
		if (index >= 8 && index <= 11) {
			return "추천성향";
		}
		return "추천자격증";
	}

	private String getTrimValue(String value) {
		if (value == null) {
			return "";
		}
		return value.trim();
	}
}