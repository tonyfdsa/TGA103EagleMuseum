package contact.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import contact.common.MailService;
import contact.common.QuesConfirmMail;
import contact.service.QuesContentService;
import contact.service.QuesContentServiceImpl;
import contact.vo.QuesContent;

@WebServlet("/questionContent")
public class QuesContentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private QuesContentService service;

	public void init() throws ServletException {
		try {
			service = new QuesContentServiceImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		super.init();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		// 假裝從session取得memberid（要跟servlet一致）
		final Integer memberId = 3;

		final String questionTypeIDStr = req.getParameter("questionTypeID");

		// 第一次進入此頁，使用者尚未選擇questionTypeID，故先不進行字串轉Int
		Integer questionTypeID = null;
		if (StringUtils.isNotBlank(questionTypeIDStr)) {
			questionTypeID = Integer.parseInt(questionTypeIDStr);
		}
		final String questionContent = req.getParameter("questionContent");

		QuesContent quesContent = new QuesContent();
		quesContent.setQuestionTypeID(questionTypeID);
		quesContent.setQuestionContent(questionContent);

		String getQuesContent = quesContent.getQuestionContent();
		if (StringUtils.isNotBlank(getQuesContent)) {
			final boolean result = service.submitQuestion(quesContent);

			if (result) {
				req.setAttribute("result", "提問已送出");
				String memberEmail = service.confirmQues(memberId);
				System.out.println(memberEmail);
				new QuesConfirmMail(memberEmail).quesConfirmMail();
			}
		} else if (StringUtils.isBlank(getQuesContent)) {
			req.setAttribute("result", "提問失敗，請輸入問題內容。");
		}

		final List<QuesContent> list = service.getByMemberId(memberId);
		req.setAttribute("questionList", list);

		req.getRequestDispatcher("/contact/otherQues.jsp").forward(req, resp);
//		questionContent
	}

}
