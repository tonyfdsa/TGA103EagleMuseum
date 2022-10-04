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
@WebServlet("/OrderStatUpdate")
public class OrderStatUpdate extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private OrderServiceImpl service = new OrderServiceImpl();
	private Gson gson = new Gson();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		
		orderVO vo = json2Vo(request, orderVO.class);
		Integer orderID = vo.getOrderID();
		Integer orderStatus = vo.getOrderStatus();

		
		response.getWriter().print(gson.toJson(service.updateStatus(orderID, orderStatus)));	
	}

	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}
}
