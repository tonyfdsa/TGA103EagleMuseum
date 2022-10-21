package member.controller;

import static core.util.GsonWithDateFormatUtil.json2Pojo;
import static core.util.GsonWithDateFormatUtil.writePojo2Json;
import static member.common.MemberConstants.SERVICE;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.pojo.CoreSimple;
import member.vo.Member;

@WebServlet("/member/remove")
public class ManagerRemove extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		final Integer memberId = json2Pojo(req, Member.class).getMemberID();
		final CoreSimple core = new CoreSimple();
		System.out.println(memberId);
		if (memberId == null) {
			core.setMessage("無id");
			core.setSuccessful(false);
		} else {
			core.setSuccessful(SERVICE.removeMember(memberId));
		}
		System.out.println(core);
		writePojo2Json(resp, core);
	}
}
