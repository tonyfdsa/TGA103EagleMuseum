package member.controller;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import member.service.MemberService;
import member.service.impl.MemberServiceImpl;
import member.vo.Member;

@WebServlet("/member/register")
public class MemberRegisterServlet extends HttpServlet {
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String memberEmail = req.getParameter("memberEmail");
		final String memberPassword = req.getParameter("memberPassword");
		final String memberName = req.getParameter("memberName");
		final String memberQA = req.getParameter("memberQA");
		final String memberAns = req.getParameter("memberAns");
		final String memberAddress = req.getParameter("memberAddress");
		final String memberPhone = req.getParameter("memberPhone");
		
		
		final String memberGender = req.getParameter("memberGender");
		final String memberBirthday = req.getParameter("memberBirthday");
		final String memberPermission = req.getParameter("memberPermission");
		
		Member member = new Member();
		member.setMemberEmail(memberEmail);
		member.setMemberPassword(memberPassword);
		member.setMemberName(memberName);
		member.setMemberQA(memberQA);
		member.setMemberAns(memberAns);
		member.setMemberAddress(memberAddress);
		member.setMemberPhone(memberPhone);
		member.setMemberGender(memberGender);
		member.setMemberBirthday(memberBirthday);
		member.setMemberPermission(memberPermission);
		
//		final String MemberPhone = req.getParameter("memberPhone");
		// 第一次進入此頁，使用者尚未選擇questionTypeID，故先不進行字串轉Int
		Integer MemberPhone = null;
		if(StringUtils.isNotBlank(memberPhone)) {
			MemberPhone = Integer.parseInt(memberPhone);
		}

		final String questionTypeIDStr = req.getParameter("questionTypeID");
		//第一次進入此頁，使用者尚未選擇questionTypeID，故先不進行字串轉Int
		Integer questionTypeID = null;
		if(StringUtils.isNotBlank(questionTypeIDStr)) {
			questionTypeID = Integer.parseInt(questionTypeIDStr);
		}

		
		
		final boolean result = service.registerMember(member);
		
		req.setAttribute("result", result ? "註冊成功" : "註冊失敗");
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	
}
