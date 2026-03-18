package com.ccc.roadmap.dto;

public class RoadmapCurriculumDTO {
	private int curriculumNumber;
	private int jobNumber;
	private String skillType;
	private String skillName;
	private String skillDesc;

	public RoadmapCurriculumDTO() {;}

	public int getCurriculumNumber() {
		return curriculumNumber;
	}

	public void setCurriculumNumber(int curriculumNumber) {
		this.curriculumNumber = curriculumNumber;
	}

	public int getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(int jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getSkillType() {
		return skillType;
	}

	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getSkillDesc() {
		return skillDesc;
	}

	public void setSkillDesc(String skillDesc) {
		this.skillDesc = skillDesc;
	}

	@Override
	public String toString() {
		return "RoadmapCurriculumDTO [curriculumNumber=" + curriculumNumber + ", jobNumber=" + jobNumber
				+ ", skillType=" + skillType + ", skillName=" + skillName + ", skillDesc=" + skillDesc + "]";
	}
}