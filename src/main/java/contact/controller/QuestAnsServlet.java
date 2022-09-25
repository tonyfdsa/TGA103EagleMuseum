package contact.controller;

import static prod.common.setHeaders.setHeaders;

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
import contact.service.QuesContentService;
import contact.service.QuesContentServiceImpl;
import contact.vo.QuesContent;

@WebServlet("/questionAns")
public class QuestAnsServlet extends HttpServlet {


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
		setHeaders(resp);
		resp.setContentType("application/json;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		final String memberIdStr = req.getParameter("memberId");
		final String start_date = req.getParameter("start_date");
		final String end_date = req.getParameter("end_date");
		final String ansContent = req.getParameter("ansContent");
		final String quesIdStr = req.getParameter("quesId");
		Integer memberId = null;
		Integer quesId = null;

		if (StringUtils.isAllBlank()) {
			final List<QuesContent> list = service.findAllQs();
			req.setAttribute("questionList", list);
		}
		
		// 第一次進入此頁，使用者尚未選擇memberId，故先不進行字串轉Int
		if (StringUtils.isNotBlank(ansContent) && StringUtils.isNotBlank(quesIdStr)) {
			quesId = Integer.parseInt(quesIdStr);
			final boolean result = service.submitAnswer(ansContent, quesId);
			req.setAttribute("result", result ? "答覆已送出" : "答覆失敗");
			final List<QuesContent> list = service.findAllQs();
			req.setAttribute("questionList", list);
					
			String memNameAndMailAndQues = service.getMemNameAndMailAndQues(quesId);
			String[] memNMQ = memNameAndMailAndQues.split(",");
//			System.out.println(Arrays.toString(memNMQ));
//			System.out.println("mail = " + xx[0]);
			new MailService(memNMQ[0], memNMQ[1], memNMQ[2], memNMQ[3]).eagleMail();		
		}

		if (StringUtils.isNotBlank(memberIdStr) && StringUtils.isNotBlank(start_date)
				&& StringUtils.isNotBlank(end_date)) {
			memberId = Integer.parseInt(memberIdStr);
			final List<QuesContent> list = service.getByIdAndDate(memberId, start_date, end_date);
			req.setAttribute("questionList", list);
		}

		
		if (StringUtils.isNotBlank(memberIdStr) && (StringUtils.isBlank(start_date))
				&& (StringUtils.isBlank(start_date))) {
			memberId = Integer.parseInt(memberIdStr);
			final List<QuesContent> list = service.getByMemberId(memberId);
			req.setAttribute("questionList", list);
		} else if (StringUtils.isBlank(memberIdStr)) {
			final List<QuesContent> list = service.findAllQs();
			req.setAttribute("questionList", list);
		}

		if ((StringUtils.isBlank(memberIdStr)) && (StringUtils.isNotBlank(start_date))
				&& (StringUtils.isNotBlank(start_date))) {
			final List<QuesContent> list = service.getByDate(start_date, end_date);
			req.setAttribute("questionList", list);
		} else if (StringUtils.isBlank(memberIdStr)) {
			final List<QuesContent> list = service.findAllQs();
			req.setAttribute("questionList", list);
		}


		req.getRequestDispatcher("/contact/questionAnswer.jsp").forward(req, resp);
	}

}
