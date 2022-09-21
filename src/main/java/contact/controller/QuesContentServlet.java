package contact.controller;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import contact.service.QuesContentService;
import contact.service.QuesContentServiceImpl;
import contact.vo.QuesContent;

@WebServlet("/questionContent")
public class QuesContentServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
private QuesContentService service;


public void init() throws ServletException{
	try {
		service = new QuesContentServiceImpl();
	} catch(NamingException e){
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
	
	final String questionTypeIDStr = req.getParameter("questionTypeID");
	//第一次進入此頁，使用者尚未選擇questionTypeID，故先不進行字串轉Int
	Integer questionTypeID = null;
	if(StringUtils.isNotBlank(questionTypeIDStr)) {
		questionTypeID = Integer.parseInt(questionTypeIDStr);
	}
	final String questionContent = req.getParameter("questionContent");
	
	QuesContent quesContent = new QuesContent();
	quesContent.setQuestionTypeID(questionTypeID);
	quesContent.setQuestionContent(questionContent);
	
	String quesContent1 = quesContent.getQuestionContent();	
	if(quesContent1 != null) {
		final boolean result = service.submitQuestion(quesContent);
		req.setAttribute("result", result? "提問已送出" : "提問失敗");
	}
	
	final List<QuesContent> list = service.findAllQs();
	req.setAttribute("questionList", list);
	
	req.getRequestDispatcher("/questionContent.jsp").forward(req, resp);
	
}
	

}
