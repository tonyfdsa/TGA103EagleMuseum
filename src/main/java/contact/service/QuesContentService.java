package contact.service;

import java.util.List;

import contact.vo.QuesContent;

public interface QuesContentService {

	Boolean submitAnswer(String ansContent, Integer questionContentID); 
	Boolean submitQuestion(QuesContent questionContent); 
	List<QuesContent> findAllQs();
	List<QuesContent> getByMemberId(Integer memberId);
	List<QuesContent> getByDate(String lastUpdateDate1, String lastUpdateDate2);
	List<QuesContent> getByIdAndDate(Integer memberId, String lastUpdateDate1, String lastUpdateDate2);
}
