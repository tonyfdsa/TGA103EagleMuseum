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
import com.google.gson.GsonBuilder;

import index_exhibition.vo.Index_productListVo;
import index_prod.service.impl.Index_prodServiceImpl;
import prod.common.Result;

@WebServlet("/Index_prodStatusUpdate")
public class Index_prodStatusUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	public static final Gson GSON = new GsonBuilder().create();
	private Index_prodServiceImpl service = new Index_prodServiceImpl();
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		Index_productListVo Index_productListVo = json2Vo(request, Index_productListVo.class);	
//		System.out.println(productVO);
		Result R  = service.updateStatus(Index_productListVo);
	
	}
	
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}

}
