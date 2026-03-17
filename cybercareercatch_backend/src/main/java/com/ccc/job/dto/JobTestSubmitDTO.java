package com.ccc.job.dto;

public class JobTestSubmitDTO {

	private int jobResultNumber;
    private int userNumber;
    private int jobQuestionNumber;
    private String jobQuestionContentSnapshot;
    private String answerContent;

    public JobTestSubmitDTO() {}

    public int getJobResultNumber() {
        return jobResultNumber;
    }

    public void setJobResultNumber(int jobResultNumber) {
        this.jobResultNumber = jobResultNumber;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public int getJobQuestionNumber() {
        return jobQuestionNumber;
    }

    public void setJobQuestionNumber(int jobQuestionNumber) {
        this.jobQuestionNumber = jobQuestionNumber;
    }

    public String getJobQuestionContentSnapshot() {
        return jobQuestionContentSnapshot;
    }

    public void setJobQuestionContentSnapshot(String jobQuestionContentSnapshot) {
        this.jobQuestionContentSnapshot = jobQuestionContentSnapshot;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }
}