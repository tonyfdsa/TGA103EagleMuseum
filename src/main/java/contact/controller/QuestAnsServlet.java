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

@WebServlet("/questionAns")
public class QuestAnsServlet extends HttpServlet {

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

		final String memberIdStr = req.getParameter("memberId");
		final String lastUpdateDate1 = req.getParameter("lastUpdateDate1");
		final String lastUpdateDate2 = req.getParameter("lastUpdateDate2");
		final String ansContent = req.getParameter("ansContent");
		Integer memberId = null;
		
		if(StringUtils.isNotBlank(ansContent)) {
			
		}

		if (StringUtils.isNotBlank(memberIdStr) && StringUtils.isNotBlank(lastUpdateDate1)
				&& StringUtils.isNotBlank(lastUpdateDate2)) {
			memberId = Integer.parseInt(memberIdStr);
			final List<QuesContent> list = service.getByIdAndDate(memberId, lastUpdateDate1, lastUpdateDate2);
			req.setAttribute("questionList", list);
		}

		// 第一次進入此頁，使用者尚未選擇memberId，故先不進行字串轉Int
		if (StringUtils.isNotBlank(memberIdStr) && (StringUtils.isBlank(lastUpdateDate1))
				&& (StringUtils.isBlank(lastUpdateDate1))) {
			memberId = Integer.parseInt(memberIdStr);
			final List<QuesContent> list = service.getByMemberId(memberId);
			req.setAttribute("questionList", list);
		} else if (StringUtils.isBlank(memberIdStr)) {
			final List<QuesContent> list = service.findAllQs();
			req.setAttribute("questionList", list);
		}

		if ((StringUtils.isBlank(memberIdStr)) && (StringUtils.isNotBlank(lastUpdateDate1))
				&& (StringUtils.isNotBlank(lastUpdateDate1))) {
			final List<QuesContent> list = service.getByDate(lastUpdateDate1, lastUpdateDate2);
			req.setAttribute("questionList", list);
		} else if (StringUtils.isBlank(memberIdStr)) {
			final List<QuesContent> list = service.findAllQs();
			req.setAttribute("questionList", list);
		}

		req.getRequestDispatcher("/questionAnswer.jsp").forward(req, resp);
	}

}
