package order.controller;

import static prod.common.json2VO.json2Vo;
import static prod.common.setHeaders.setHeaders;

import java.io.IOException;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import order.common.json2VO;
import order.service.impl.OrderServiceImpl;
import order.vo.OrderVO;
import prod.common.Result;
import prod.vo.CartVO;


@WebServlet("/OrderInsert")
public class OrderInsert extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private OrderServiceImpl service = new OrderServiceImpl();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		OrderVO VO  = json2Vo(request, OrderVO.class);
		//抓Session的紀錄
		HttpSession session = request.getSession();
		HashMap<Integer,CartVO> map = (HashMap)session.getAttribute("cart");
		List<CartVO> valueList = new ArrayList<CartVO>(map.values());
		
		
		response.getWriter().print(gson.toJson(service.
				insertOrder(VO.getOrderAmount(),VO.getDeliveryAddress() ,valueList)));	
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}
}
