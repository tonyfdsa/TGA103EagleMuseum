package contact.controller;

import java.io.IOException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import static contact.common.setHeaders.*;
import static contact.common.json2VO.*;
import contact.common.MailService;
import contact.common.QuesConfirmMail;
import contact.common.Result;
import contact.service.QuesContentService;
import contact.service.QuesContentServiceImpl;
import contact.vo.QuesContent;

@WebServlet("/questionContent")
public class QuesContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//跨域
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
		setHeaders(resp);
		resp.setContentType("application/json;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		Integer memberId = json2Vo(req, QuesContent.class).getMemberId();

		final Result list = service.getByMemberId(memberId);
		resp.getWriter().print(gson.toJson(list));

	}

}
