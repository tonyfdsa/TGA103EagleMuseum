package contact.service;

import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.lang3.StringUtils;

import contact.dao.QuesContentDao;
import contact.dao.QuesContentDaoImpl;
import contact.vo.QuesContent;

public class QuesContentServiceImpl implements QuesContentService {

	private QuesContentDao dao;

	public QuesContentServiceImpl() throws NamingException {
		dao = new QuesContentDaoImpl();
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

	@Override
	public Boolean submitAnswer(String ansContent, Integer questionContentID) {

		if (StringUtils.isBlank(ansContent)) {
			// final String pickType = "請輸入答覆內容";
			return false;
		}

		if (questionContentID == null) {
			// final String inputQ = "請輸入問題內容";
			return false;
		}

		dao.updateAns(ansContent, questionContentID);

		return true;
	}

	@Override
	public String getMemNameAndMailAndQues(Integer questionContentID) {
		return dao.getMemNameAndMailAndQues(questionContentID);
	}

	@Override
	public String confirmQues(Integer memberId) {
		return dao.confirmQues(memberId);
	}


}
