package order.controller;

import static prod.common.setHeaders.setHeaders;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import order.common.json2VO;
import order.common.setHeaders;
import order.service.impl.OrderServiceImpl;
import order.vo.OrderVO;

@WebServlet("/OrderGetAll")
public class OrderGetAll extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private OrderServiceImpl service = new OrderServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
		req.setCharacterEncoding("UTF-8");
		resp.getWriter().print(gson.toJson(service.orderGetAll()));	
	}
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
		
	}
	
}
