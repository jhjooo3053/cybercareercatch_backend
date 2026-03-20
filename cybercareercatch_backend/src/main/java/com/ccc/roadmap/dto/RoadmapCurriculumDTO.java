package com.ccc.roadmap.dto;

public class RoadmapDTO {
	private int jobNumber;
	private String roadmapImagePath;

	public RoadmapDTO() {;}

	public int getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(int jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getRoadmapImagePath() {
		return roadmapImagePath;
	}

	public void setRoadmapImagePath(String roadmapImagePath) {
		this.roadmapImagePath = roadmapImagePath;
	}

	@Override
	public String toString() {
		return "RoadmapDTO [jobNumber=" + jobNumber + ", roadmapImagePath=" + roadmapImagePath + "]";
	}
}