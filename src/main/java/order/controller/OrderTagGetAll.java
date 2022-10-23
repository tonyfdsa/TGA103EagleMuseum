package order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import order.service.impl.OrderServiceImpl;
import static prod.common.setHeaders.setHeaders;

@WebServlet("/OrderTagGetAll")
public class OrderTagGetAll extends HttpServlet{
	private OrderServiceImpl service = new OrderServiceImpl();
	private Gson gson = new Gson();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
		req.setCharacterEncoding("UTF-8");
		resp.getWriter().print(gson.toJson(service.orderTagGetAll()));
		
	}
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}

}
