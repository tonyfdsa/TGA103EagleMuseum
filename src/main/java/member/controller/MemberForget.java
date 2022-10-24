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

@WebServlet("/member/forgetpass")
public class MemberForget extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		Member member = json2Pojo(req, Member.class);
		
        member = SERVICE.forgetpass(member);
		
		if (member.isSuccessful()) {
			final HttpSession session = req.getSession();
			session.setAttribute("forget", member);
		}
        
		writePojo2Json(resp, member);
	}
}
