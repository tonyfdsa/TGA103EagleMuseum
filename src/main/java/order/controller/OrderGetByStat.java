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
import order.vo.orderVO;
import prod.service.impl.ProductServicelm;

@WebServlet("/OrderGetByStat")
public class OrderGetByStat extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private OrderServiceImpl service = new OrderServiceImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		
		Integer OrderStatus = json2Vo(request, orderVO.class).getOrderStatus();
		System.out.println(OrderStatus);
		response.getWriter().print(gson.toJson(service.getbyStatus(OrderStatus)));	
	}

	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}
}
