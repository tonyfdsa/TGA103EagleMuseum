package contact.service;

import java.util.Date;
import java.util.List;

import javax.naming.NamingException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import contact.common.Result;
import contact.dao.QuesContentDao;
import contact.dao.QuesContentDaoImpl;
import contact.vo.Member;
import contact.vo.QuesContent;
import core.util.Global;
import core.util.HibernateUtil;

public class QuesContentServiceImpl implements QuesContentService {

	private Result R;
	private QuesContentDao dao;
	private Global base64;

	public QuesContentServiceImpl() throws NamingException {
		dao = new QuesContentDaoImpl();
		R = new Result();
		base64 = new Global();
	}

	@Override
//	public Boolean submitQuestion(QuesContent questionContent) {

	public Result submitQuestion(QuesContent questionContent) {

		final String questionContentString = questionContent.getQuestionContent();
		if (StringUtils.isBlank(questionContentString)) {
			return R.fail("2");
		}

		final Integer id = dao.insert(questionContent);
		if (id == null) {
			return R.fail("2");
		}
		return R.success(1);
	}

	@Override
	public Result findAllQs() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			List<QuesContent> quesContents = dao.selectAll();

			transaction.commit();
			Result aLLQs = R.success(quesContents);
			return aLLQs;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	@Override
	public Result getByMemberId(Integer memberId) {
		try {
			List<QuesContent> quesContents = dao.findByMemberId(memberId);
			if (quesContents.isEmpty()) {
				return R.fail("查無相關資訊！");
			}
			return R.success(quesContents);
		} catch (Exception e) {
			return R.fail(e.toString());
		}
	}

	@Override
	public Result getByDate(Date startTime, Date endTime) {
		List<QuesContent> quesContents = dao.findByDate(startTime, endTime);
		if (quesContents.isEmpty()) {
			return R.fail("查無相關資訊！");
		}
		return R.success(quesContents);
	}

	@Override
	public Result getByIdAndDate(Integer memberId, Date startTime, Date endTime) {
		List<QuesContent> quesContents = dao.findByIdAndDate(memberId, startTime, endTime);
		if (quesContents.isEmpty()) {
			return R.fail("查無相關資訊！");
		}
		return R.success(quesContents);
	}

	@Override
	public Boolean submitAnswer(String answerContent, Integer questionContentID) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (StringUtils.isBlank(answerContent)) {
				return false;
			}

			if (questionContentID == null) {
				return false;
			}

			dao.updateAns(answerContent, questionContentID);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	@Override
	public String getMemNameAndMailAndQues(Integer questionContentID) {
		return dao.getMemNameAndMailAndQues(questionContentID);
	}

	@Override
	public Member confirmQues(Integer memberId) {
		return dao.confirmQues(memberId);
	}

	@Override
	public QuesContent getQAContentService(Integer questionContentID) {
		return dao.getQAContent(questionContentID);
	}

}
