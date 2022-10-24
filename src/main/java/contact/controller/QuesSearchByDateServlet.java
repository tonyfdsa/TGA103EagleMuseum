package contact.controller;

import static contact.common.json2VO.json2Vo;
import static prod.common.setHeaders.setHeaders;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

import contact.common.Result;
import contact.service.QuesContentService;
import contact.service.QuesContentServiceImpl;
import contact.vo.QuesContent;

@WebServlet("/quesSearchByDateServlet")
public class QuesSearchByDateServlet extends HttpServlet {

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
		
		Date quesTime = vo.getQuesTime();
		Date answerTime = vo.getAnswerTime();	
		
		final Result list = service.getByDate(quesTime, answerTime);
		resp.getWriter().print(gson.toJson(list));			
	}
}//class
