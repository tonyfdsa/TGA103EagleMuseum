package prod.controller;

import static prod.common.json2VO.json2Vo;
import static prod.common.setHeaders.setHeaders;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import prod.common.Result;
import prod.service.impl.ProductServicelm;
import prod.vo.productVO;

@WebServlet("/ProdUpdate")
public class produpdate extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private ProductServicelm service = new ProductServicelm();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		
		productVO productVO = json2Vo(request, productVO.class);
//		System.out.println(ProdID);
		Result R  = service.prodUpdate(productVO);
		response.getWriter().print(gson.toJson(R));	
	}

	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}
}
