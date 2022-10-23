package member.controller;

import java.io.IOException;

import static core.util.GsonWithDateFormatUtil.json2Pojo;
import static core.util.GsonWithDateFormatUtil.writePojo2Json;
import static member.common.MemberConstants.SERVICE;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.vo.Member;

@WebServlet("/member/selectSelf")
public class MemberSelectSelf extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		System.out.println("我有跑");
		Member member = json2Pojo(req, Member.class);
		System.out.println(member.getMemberEmail());
		
		member = SERVICE.selectByMember(member);
        
        writePojo2Json(resp, member);
        System.out.println( member);
		
	}
	
}
