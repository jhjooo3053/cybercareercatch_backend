package com.ccc.roadmap.dto;

public class RoadmapJobDTO {
	private int roadmapJobNumber;
	private int jobNumber;
	private String roadmapJobName;
	private String roadmapJobDesc;

	public RoadmapJobDTO() {;}

	public int getRoadmapJobNumber() {
		return roadmapJobNumber;
	}

	public void setRoadmapJobNumber(int roadmapJobNumber) {
		this.roadmapJobNumber = roadmapJobNumber;
	}

	public int getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(int jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getRoadmapJobName() {
		return roadmapJobName;
	}

	public void setRoadmapJobName(String roadmapJobName) {
		this.roadmapJobName = roadmapJobName;
	}

	public String getRoadmapJobDesc() {
		return roadmapJobDesc;
	}

	public void setRoadmapJobDesc(String roadmapJobDesc) {
		this.roadmapJobDesc = roadmapJobDesc;
	}

	@Override
	public String toString() {
		return "RoadmapJobDTO [roadmapJobNumber=" + roadmapJobNumber + ", jobNumber=" + jobNumber
				+ ", roadmapJobName=" + roadmapJobName + ", roadmapJobDesc=" + roadmapJobDesc + "]";
	}
}