package member.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static member.common.MemberConstants.SERVICE;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.pojo.Core;
import member.vo.Member;

@WebServlet("/member/remove")
public class ManagerRemove extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		final Integer memberId = json2Pojo(req, Member.class).getMemberID();
		final Core core = new Core();
		if (memberId == null) {
			core.setMessage("無id");
			core.setSuccessful(false);
		} else {
			core.setSuccessful(SERVICE.removeMember(memberId));
		}
		writePojo2Json(resp, core);
	}
}
