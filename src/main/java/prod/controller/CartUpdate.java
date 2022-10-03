package prod.controller;

import static prod.common.json2VO.json2Vo;
import static prod.common.setHeaders.setHeaders;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import prod.common.Result;
import prod.service.impl.ProductServicelm;
import prod.vo.CartVO;
import prod.vo.productVO;

//更新購物車
@WebServlet("/CartUpdate")
public class CartUpdate extends HttpServlet{
	private Gson gson = new Gson();
	private Result R = new Result();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		
		//前端來的數據
		CartVO cartVO = json2Vo(request, CartVO.class);
		
		HttpSession session = request.getSession();
		HashMap<Integer,CartVO> map = (HashMap)session.getAttribute("cart");
		
		CartVO cart = map.get(cartVO.getProductID());
		if(cart != null) {
			cart.setProdCount(cartVO.getProdCount());
		}
		
		//更新Session
		map.put(cartVO.getProductID(), cart);
		session.setAttribute("cart", map);
		
		//回傳至前端
		response.getWriter().print(gson.toJson(R.success(map)));
	}

	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}
}
