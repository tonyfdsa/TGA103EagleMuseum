package prod.controller;


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
import prod.vo.CartVO;


//單純請求購物車資料
@WebServlet("/CartGet")
public class CartGet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Result R = new Result();
	private Gson gson = new Gson();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		HashMap<Integer,CartVO> map = (HashMap)session.getAttribute("cart");
		
		if(map != null) {
			List<String> valueList = new ArrayList(map.values());
			response.getWriter().print(gson.toJson(R.success(valueList)));
		}else {
			response.getWriter().print(gson.toJson(R.fail("0")));
		}
	}

	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}
}
