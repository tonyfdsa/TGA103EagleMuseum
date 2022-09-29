package contact.service;

import java.sql.Timestamp;
import java.util.List;

import contact.common.Result;
import contact.vo.QuesContent;

public interface QuesContentService {

	Boolean submitAnswer(String answerContent, Integer questionContentID); 
	Boolean submitQuestion(QuesContent questionContent); 
	Result findAllQs();
	Result getByMemberId(Integer memberId);
	Result getByDate(Timestamp quesTime, Timestamp answerTime);
	Result getByIdAndDate(Integer memberId, Timestamp quesTime, Timestamp answerTime);
	String getMemNameAndMailAndQues(Integer questionContentID);
	String confirmQues(Integer memberId);
}
