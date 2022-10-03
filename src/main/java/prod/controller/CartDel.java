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

//刪除購物車的某樣商品
@WebServlet("/CartDel")
public class CartDel extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private Result R = new Result();
	private Gson gson = new Gson();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		
		//前端來的數據
		Integer ProdID = json2Vo(request, productVO.class).getProductID();
		
		HttpSession session = request.getSession();
		HashMap<Integer,CartVO> map = (HashMap)session.getAttribute("cart");
		
		//如果購物車有項商品則刪除
		if(map != null) {
			map.remove(ProdID);
			session.setAttribute("cart", map);
		}
		
		//回傳至前端
		response.getWriter().print(gson.toJson(R.success("1")));
	}

	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}

}
