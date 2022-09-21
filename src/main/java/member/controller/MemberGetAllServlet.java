package member.controller;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.MemberService;
import member.service.impl.MemberServiceImpl;
import member.vo.Member;

@WebServlet("/member/getAll")
public class MemberGetAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService service;
	
	@Override
	public void init() throws ServletException {
		try {
			service = new MemberServiceImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final List<Member> list = service.findAllMembers();
		req.setAttribute("memberList", list);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
		
	}
	
}
