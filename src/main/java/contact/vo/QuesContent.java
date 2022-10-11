package contact.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "questionContent")
public class QuesContent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer questionContentID;
	private Integer memberId;
	private Integer questionTypeID;
	private String questionContent;
	private String answerContent;
	@Column(insertable = false)
	private Boolean answered;
	@Column(insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date quesTime;
	@Column(insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date answerTime;
	
	public QuesContent() {
	}
	
	

	public QuesContent(Integer memberId) {
		super();
		this.memberId = memberId;
	}


	
	public QuesContent(Integer questionContentID, Integer memberId, Integer questionTypeID,
			String questionContent, String answerContent, Boolean answered) {
		this.questionContentID = questionContentID;
		this.memberId = memberId;
		this.questionTypeID = questionTypeID;
		this.questionContent = questionContent;
		this.answerContent = answerContent;
		this.answered = answered;
	}
	
	public QuesContent(Integer questionContentID, Integer memberId, Integer questionTypeID,
			String questionContent, String answerContent, Boolean answered, Date quesTime, 
			Date answerTime) {
		this.questionContentID = questionContentID;
		this.memberId = memberId;
		this.questionTypeID = questionTypeID;
		this.questionContent = questionContent;
		this.answerContent = answerContent;
		this.answered = answered;
		this.quesTime = quesTime;
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

	public Date getQuesTime() {
		return quesTime;
	}

	public void setQuesTime(Date quesTime) {
		this.quesTime = quesTime;
	}

	public Date getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}
	
	
}
