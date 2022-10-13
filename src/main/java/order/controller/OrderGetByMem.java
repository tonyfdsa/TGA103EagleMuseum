package order.controller;

import static prod.common.json2VO.json2Vo;
import static prod.common.setHeaders.setHeaders;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import order.service.impl.OrderServiceImpl;
import order.vo.OrderVO;

@WebServlet("/OrderGetByMem")
public class OrderGetByMem extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private OrderServiceImpl service = new OrderServiceImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		setHeaders(resp);
		Integer memID  = json2Vo(req, OrderVO.class).getMemberId();
		resp.getWriter().print(gson.toJson(service.getByMem(memID)));	
	}
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}

}
