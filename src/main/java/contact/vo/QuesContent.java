package contact.vo;

import java.sql.Timestamp;

public class QuesContent {

	private Integer questionContentID;
	private Integer memberId;
	private Integer questionTypeID;
	private String questionContent;
	private String answerContent;
	private Boolean answered;
	private Timestamp quesTime;
	private Timestamp answerTime;
	
	public QuesContent() {
	}

	public QuesContent(Integer questionContentID, Integer memberId, Integer questionTypeID,
			String questionContent, String answerContent, Boolean answered, Timestamp quesTime, 
			Timestamp answerTime) {
		this.questionContentID = questionContentID;
		this.memberId = memberId;
		this.questionTypeID = questionTypeID;
		this.questionContent = questionContent;
		this.answerContent = answerContent;
		this.answered = answered;
		this.answerTime= answerTime;
	}
	
	
	
	public Integer getQuestionContentID() {
		return questionContentID;
	}
	public void setQuestionContentID(Integer questionContentID) {
		this.questionContentID = questionContentID;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getQuestionTypeID() {
		return questionTypeID;
	}
	public void setQuestionTypeID(Integer questionTypeID) {
		this.questionTypeID = questionTypeID;
	}
	public String getQuestionContent() {
		return questionContent;
	}
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public Boolean getAnswered() {
		return answered;
	}
	public void setAnswered(Boolean answered) {
		this.answered = answered;
	}

	public Timestamp getQuesTime() {
		return quesTime;
	}

	public void setQuesTime(Timestamp quesTime) {
		this.quesTime = quesTime;
	}

	public Timestamp getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Timestamp answerTime) {
		this.answerTime = answerTime;
	}
	
	
}
