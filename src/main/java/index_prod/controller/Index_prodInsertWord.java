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
import prod.service.impl.ProductServicelm;

@WebServlet("/ProWord")
public class Index_prodInsertWord extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	private Gson gson = new Gson();
	private ProductServicelm service = new ProductServicelm();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException,IOException  {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		String descripition = json2Vo(request, Index_productListVo.class).getProdDescription();
		System.out.println(descripition);
	}
	
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}
	
	
}
