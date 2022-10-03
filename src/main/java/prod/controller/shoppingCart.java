package prod.controller;

import static prod.common.json2VO.json2Vo;
import static prod.common.setHeaders.setHeaders;

import java.io.IOException;
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

import prod.common.Result;
import prod.service.impl.ProductServicelm;
import prod.vo.CartVO;
import prod.vo.productVO;

@WebServlet("/ProdShoppingCart")
public class shoppingCart extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private Result R = new Result();
	private ProductServicelm service = new ProductServicelm();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		//建立購物車用的session
		HttpSession session = request.getSession();
		HashMap<Integer,CartVO> map = (HashMap)session.getAttribute("cart");
		
		//如果沒有建立session就建立新的
		if(map == null) {
			map = new HashMap<Integer,CartVO>();
		}
		
		//取得前端的傳值並搜尋
		productVO productVO = json2Vo(request, productVO.class);
		Integer ProdID = productVO.getProductID();
		String ProdImg =productVO.getProdImg();
		CartVO cartVO = service.cartGetProd(ProdID);
		
		
		//取得已建立的session(prodID)並且數量+1，如果無法取得建立新的
		CartVO cart = map.get(ProdID); 
		CartVO cartSize = map.get(0);
		if(cart != null) {
			cart.setProdCount(cart.getProdCount() + 1);
//			cartSize.setSize(map.size());
		}else {
			cart = new CartVO();
			cart.setProdName(cartVO.getProdName());
			cart.setProductID(ProdID);
			cart.setProdCount(1);
			cart.setProdPrice(cartVO.getProdPrice());
			cart.setProdImg(ProdImg);

		}
		
		
		//將新的或更新的session(ProdID)塞回Session HashMap
		map.put(ProdID, cart);
		
		session.setAttribute("cart", map);
		//Hashmap 轉 list 回傳至前端
		List<String> valueList2 = new ArrayList(map.values());
		
		response.getWriter().print(gson.toJson(R.success(valueList2)));
	}
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}

}
