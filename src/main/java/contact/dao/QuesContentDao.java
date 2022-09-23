package contact.dao;

import java.util.List;

import contact.vo.QuesContent;

public interface QuesContentDao {
	
	
	Integer insert(QuesContent questionContent);
	List<QuesContent> selectAll();
	List<QuesContent> findByMemberId(Integer memberId);
	List<QuesContent> findByDate(String lastUpdateDate1, String lastUpdateDate2);
	List<QuesContent> findByIdAndDate(Integer memberId, String lastUpdateDate1, String lastUpdateDate2);
	void updateAns(String ansContent, Integer questionContentID);
	String getMemNameAndMailAndQues(Integer questionContentID);
	String confirmQues(Integer memberId);

}
