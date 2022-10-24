package contact.controller;

import static contact.common.json2VO.json2Vo;
import static contact.common.setHeaders.setHeaders;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

import contact.common.QuesConfirmMail;
import contact.common.Result;
import contact.service.QuesContentService;
import contact.service.QuesContentServiceImpl;
import contact.vo.QuesContent;

@WebServlet("/inserQuesServlet")
public class InserQuesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// 跨域
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}

	private Gson gson = new Gson();
	private QuesContentService service;

	public void init() throws ServletException {
		try {
			service = new QuesContentServiceImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		super.init();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 跨域
		setHeaders(resp);

		resp.setContentType("application/json;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		QuesContent vo = json2Vo(req, QuesContent.class);

		String getQuesContent = vo.getQuestionContent();
		if (StringUtils.isNotBlank(getQuesContent)) {
			Result result = service.submitQuestion(vo);
			//寄出確認信
			if (result != null) {
				String memberEmail = service.confirmQues(vo.getMemberId()).getMemberEmail();
				new QuesConfirmMail(memberEmail).quesConfirmMail();
			}
//			resp.getWriter().print(gson.toJson(true));
//			System.out.println(gson.toJson(result));
			resp.getWriter().print(gson.toJson(result));
			
			
		
		}
	}
}
