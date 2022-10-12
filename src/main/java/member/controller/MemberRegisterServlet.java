package member.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static core.util.Constants.GSON;
import static core.util.Constants.JSON_MIME_TYPE;
import static member.common.MemberConstants.SERVICE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import core.adapter.LocalDateAdapter;
import core.adapter.LocalDateTimeAdapter;
import core.adapter.LocalTimeAdapter;
import member.vo.Member;


@WebServlet("/member/register")
public class MemberRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final Gson GSON = new GsonBuilder()
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
			.registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
			.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
			.create();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
		BufferedReader br = req.getReader();
		Member member =  GSON.fromJson(br, Member.class);
		
//		Member member = json2Pojo(req, Member.class);
		System.out.println(555);

		System.out.println(member.getMemberBirthday());
		member = SERVICE.registerMember(member);
		
		resp.setContentType(JSON_MIME_TYPE);
		PrintWriter pw = resp.getWriter();
		pw.print(GSON.toJson(member));
//		writePojo2Json(resp, member);
	
	
	}
	
	
}
