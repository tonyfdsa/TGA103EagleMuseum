package member.controller;

import static core.util.GsonWithDateFormatUtil.json2Pojo;
import static core.util.GsonWithDateFormatUtil.writePojo2Json;
import static member.common.MemberConstants.SERVICE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.vo.Member;

@WebServlet("/member/edit")
public class MemberEdit extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		System.out.println("跑跑跑走");
		final HttpSession session = req.getSession();
		final String username = ((Member) session.getAttribute("member")).getMemberName();
		System.out.println("跑跑跑");
		Member member = json2Pojo(req, Member.class);
		member.setMemberName(username);
		writePojo2Json(resp, SERVICE.editMember(member));
		
		
	}
	

}
