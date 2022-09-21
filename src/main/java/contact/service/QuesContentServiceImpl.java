package contact.service;

import java.util.List;

import javax.naming.NamingException;

import contact.dao.QuesContentDao;
import contact.dao.QuesContentDaoImpl;
import contact.vo.QuesContent;

public class QuesContentServiceImpl implements QuesContentService {

	private QuesContentDao dao;

	public QuesContentServiceImpl() throws NamingException {
		dao = new QuesContentDaoImpl();
	}

	//還沒寫好
	@Override
	public Boolean submitAnswer(QuesContent ansContent) {
		final Integer questionType = ansContent.getQuestionTypeID();
		final String answerContent = ansContent.getAnswerContent();

		if (questionType == null) {
			// final String pickType = "請選擇問題類型";
			return false;
		}

		if (answerContent.isEmpty()) {
			// final String inputQ = "請輸入問題內容";
			return false;
		}

		dao.insertAns(ansContent);

		return true;
	}

	@Override
	public Boolean submitQuestion(QuesContent questionContent) {
		final Integer questionType = questionContent.getQuestionTypeID();
		final String questionContentString = questionContent.getQuestionContent();

		if (questionType == null) {
			// final String pickType = "請選擇問題類型";
			return false;
		}

		if (questionContentString.isEmpty()) {
			// final String inputQ = "請輸入問題內容";
			return false;
		}

		final Integer id = dao.insert(questionContent);
		if (id == null) {
			return false;
		}

		return true;
	}

	@Override
	public List<QuesContent> findAllQs() {
		return dao.selectAll();
	}

	@Override
	public List<QuesContent> getByMemberId(Integer memberId) {
		return dao.findByMemberId(memberId);
	}

	@Override
	public List<QuesContent> getByDate(String lastUpdateDate1, String lastUpdateDate2) {
		return dao.findByDate(lastUpdateDate1, lastUpdateDate2);
	}

	@Override
	public List<QuesContent> getByIdAndDate(Integer memberId, String lastUpdateDate1, String lastUpdateDate2) {
		return dao.findByIdAndDate(memberId, lastUpdateDate1, lastUpdateDate2);
	}

}
