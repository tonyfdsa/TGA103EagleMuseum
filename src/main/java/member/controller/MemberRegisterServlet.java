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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import member.vo.Member;


@WebServlet("/member/register")
public class MemberRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final Gson GSON = new GsonBuilder().create();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
		
		Member member = json2Pojo(req, Member.class);
		System.out.println(555);

		System.out.println(member.getMemberBirthday());
		member = SERVICE.registerMember(member);
		
		writePojo2Json(resp, member);
	
	
	}
	
	
}
