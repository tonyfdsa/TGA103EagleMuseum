package order.controller;

import static prod.common.json2VO.json2Vo;
import static prod.common.setHeaders.setHeaders;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import member.vo.Member;
import order.service.impl.OrderServiceImpl;
import order.vo.OrderVO;

@WebServlet("/OrdergetbyMemStat")
public class OrdergetbyMemStat extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private OrderServiceImpl service = new OrderServiceImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		
		OrderVO orderVO = json2Vo(request, OrderVO.class);
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		orderVO.setMemberId(member.getMemberID());
		
		response.getWriter().print(gson.toJson(service.OrdergetbyMemStat(orderVO)));	
	}

	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}
}
