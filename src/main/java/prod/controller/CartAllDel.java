package prod.controller;

import static prod.common.setHeaders.setHeaders;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import contact.common.setHeaders;
import order.common.json2VO;
import order.common.Result;

@WebServlet("/CartDelAll")
public class CartAllDel extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Result R = new Result();
	private Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		session.removeAttribute("cart");
		resp.getWriter().print(gson.toJson(R.success("清除完成")));
		

	}
	

}
