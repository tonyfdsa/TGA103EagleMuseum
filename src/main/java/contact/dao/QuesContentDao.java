package contact.dao;

import java.sql.Timestamp;
import java.util.List;

import contact.common.Result;
import contact.vo.QuesContent;

public interface QuesContentDao {
	
	
	Integer insert(QuesContent questionContent);
	List<QuesContent> selectAll();
	List<QuesContent> findByMemberId(Integer memberId);
	List<QuesContent> findByDate(Timestamp quesTime, Timestamp answerTime);
	List<QuesContent> findByIdAndDate(Integer memberId, Timestamp quesTime, Timestamp answerTime);
	void updateAns(String answerContent, Integer questionContentID);
	String getMemNameAndMailAndQues(Integer questionContentID);
	String confirmQues(Integer memberId);

}
