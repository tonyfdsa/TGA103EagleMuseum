package contact.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import contact.common.Result;
import contact.vo.Member;
import contact.vo.QuesContent;
import core.util.HibernateUtil;

public interface QuesContentDao {
	
	default Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();	
	}
	
	Integer insert(QuesContent questionContent);
	List<QuesContent> findByMemberId(Integer memberId);
	
	List<QuesContent> selectAll();
	List<QuesContent> findByDate(Date startTime, Date endTime);
	List<QuesContent> findByIdAndDate(Integer memberId, Date startTime, Date endTime);
	void updateAns(String answerContent, Integer questionContentID);
	String getMemNameAndMailAndQues(Integer questionContentID);
	Member confirmQues(Integer memberId);
	QuesContent getQAContent(Integer questionContentID);

}
