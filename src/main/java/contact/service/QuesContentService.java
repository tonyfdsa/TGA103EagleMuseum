package contact.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import contact.common.Result;
import contact.vo.Member;
import contact.vo.QuesContent;
import core.service.CoreService;
import core.util.HibernateUtil;

public interface QuesContentService {
	
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	Session session = sessionFactory.openSession();

	Boolean submitAnswer(String answerContent, Integer questionContentID);
	Boolean submitQuestion(QuesContent questionContent); 
	Result findAllQs();
	Result getByMemberId(Integer memberId);
	Result getByDate(Date startTime, Date endTime);
	Result getByIdAndDate(Integer memberId, Date startTime, Date endTime);
	String getMemNameAndMailAndQues(Integer questionContentID);
	Member confirmQues(Integer memberId);
	QuesContent getQAContentService(Integer questionContentID);
}
