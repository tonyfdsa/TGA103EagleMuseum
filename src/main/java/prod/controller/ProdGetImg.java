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
import prod.vo.ProdImgVO;
import prod.vo.productVO;

@WebServlet("/ProdGetImg")
public class ProdGetImg extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private ProductServicelm service = new ProductServicelm();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		
		Integer ProdID = json2Vo(request, ProdImgVO.class).getProductID();
		Result R  = service.prodGetImg(ProdID);
		
		response.getWriter().print(gson.toJson(R));	
	}

	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}

}
