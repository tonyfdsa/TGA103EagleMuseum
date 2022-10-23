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

@WebServlet("/member/UpdatePass")
public class MemberUpdatePass extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		System.out.println("又來了");
		final HttpSession session = req.getSession();
		final String memberEmail = ((Member) session.getAttribute("forget")).getMemberEmail();
		final String verification = ((Member) session.getAttribute("forget")).getVerification();
		
		Member member = json2Pojo(req, Member.class);
		
		member.setMemberEmail(memberEmail);
		member.setVerification(verification);
		System.out.println(member.getMemberNewPass());
		System.out.println(member.getCaptcha());
        
		member = SERVICE.updateForPass(member);
		System.out.println(member+"1111");
		writePojo2Json(resp, member);
	
	}
	

}
