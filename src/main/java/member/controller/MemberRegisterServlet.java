package member.controller;

//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.PrintWriter;



import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static member.common.MemberConstants.SERVICE;

//import javax.sql.DataSource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import member.service.MemberService;
//import member.service.impl.MemberServiceImpl;
import member.vo.Member;

@WebServlet("/member/register")
public class MemberRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		Member member = json2Pojo(req, Member.class);
//		String password = member.getMemberPassword();
//		System.out.println(password);
		if (member == null) {
			member = new Member();
			member.setMessage("無會員資訊");
			member.setSuccessful(false);
			writePojo2Json(resp, member);
			return;
		}
		
		member = SERVICE.loginMember(member);
		if (member.isSuccessful()) {
			if (req.getSession(false) != null) {
				req.changeSessionId();
			}
			final HttpSession session = req.getSession();
			session.setAttribute("loggedin", true);
			session.setAttribute("member", member);
		}
		writePojo2Json(resp, member);
			
	}
	

	
	
	
	
	
	
	
	
	
	
	
}
