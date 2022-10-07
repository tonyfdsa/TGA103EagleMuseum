package index_prod.controller;

import static prod.common.json2VO.json2Vo;
import static prod.common.setHeaders.setHeaders;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import index_exhibition.vo.Index_productListVo;
import index_prod.service.impl.Index_prodServiceImpl;
import prod.common.Result;

@WebServlet("/Index_prodGetById")
public class Index_prodGetById extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private Index_prodServiceImpl service = new Index_prodServiceImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		
		Integer ProdID = json2Vo(request, Index_productListVo.class).getProductID();
		Result R  = service.prodGetByID(ProdID);
		response.getWriter().print(gson.toJson(R));	
	}

	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}

	
	
}
