package contact.controller;

import static contact.common.json2VO.json2Vo;
import static prod.common.setHeaders.setHeaders;

import java.io.IOException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;

import com.google.gson.Gson;

import contact.common.MailService;
import contact.common.Result;
import contact.service.QuesContentService;
import contact.service.QuesContentServiceImpl;
import contact.vo.QuesContent;

@WebServlet("/questionAns")
public class QuestAnsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 跨域
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}

	private QuesContentService service;

	public void init() throws ServletException {
		try {
			service = new QuesContentServiceImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		super.init();
	}

	private Gson gson = new Gson();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
		resp.setContentType("application/json;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		QuesContent vo = json2Vo(req, QuesContent.class);
		String answerContent = vo.getAnswerContent();
		Integer questionContentID = vo.getQuestionContentID();
		
		if(StringUtils.isNotBlank(answerContent)) {
			service.submitAnswer(answerContent, questionContentID);

			
			final Result list = service.findAllQs();
			resp.getWriter().print(gson.toJson(list));
			
			String memNameAndMailAndQues = service.getMemNameAndMailAndQues(questionContentID);
			String[] memNMQ = memNameAndMailAndQues.split(",");
			/*把memNameAndMailAndQues取到的舊answerContent（memNMQ[3]）部分刪掉，
			  在這邊把新的answerContent加進去*/		 
			new MailService(memNMQ[0], memNMQ[1], memNMQ[2], answerContent).eagleMail();			
		}

	}

}// class
