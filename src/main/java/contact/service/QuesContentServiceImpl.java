package contact.service;

import java.sql.Timestamp;
import javax.naming.NamingException;
import org.apache.commons.lang3.StringUtils;

import contact.common.Result;
import contact.dao.QuesContentDao;
import contact.dao.QuesContentDaoImpl;
import contact.vo.QuesContent;

public class QuesContentServiceImpl implements QuesContentService {

	private Result R;
	private QuesContentDao dao;
	
	public QuesContentServiceImpl() throws NamingException {
		dao = new QuesContentDaoImpl();
		R = new Result();
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
	public Result findAllQs() {
		return R.success(dao.selectAll());
	}

	@Override
	public Result getByMemberId(Integer memberId) {				  
		return R.success(dao.findByMemberId(memberId));		
	}

	@Override
	public Result getByDate(Timestamp quesTime, Timestamp answerTime) {
		return R.success(dao.findByDate(quesTime, answerTime));
	}

	@Override
	public Result getByIdAndDate(Integer memberId, Timestamp quesTime, Timestamp answerTime) {
		return R.success(dao.findByIdAndDate(memberId, quesTime, answerTime));
	}

	@Override
	public Boolean submitAnswer(String answerContent, Integer questionContentID) {

		if (StringUtils.isBlank(answerContent)) {
			// final String pickType = "請輸入答覆內容";
			return false;
		}

		if (questionContentID == null) {
			// final String inputQ = "請輸入問題內容";
			return false;
		}

		dao.updateAns(answerContent, questionContentID);

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

	@Override
	public String getQContentService(Integer questionContentID) {
		return dao.getQContent(questionContentID);
	}

	@Override
	public String getAContentService(Integer questionContentID) {
		return dao.getAContent(questionContentID);
	}


}
