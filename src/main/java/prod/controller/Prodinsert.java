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
import com.google.gson.GsonBuilder;

import prod.common.Result;
import prod.service.impl.ProductServicelm;
import prod.vo.productVO;

@WebServlet("/ProdInsert")
public class Prodinsert extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public static final Gson GSON = new GsonBuilder().create();
	private ProductServicelm service = new ProductServicelm();
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");

		productVO productVO = json2Vo(request, productVO.class);	
		response.getWriter().print(GSON.toJson(service.insert(productVO)));	
	}

	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}
}
