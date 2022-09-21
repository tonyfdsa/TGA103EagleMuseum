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

import prod.service.impl.ProductServicelm;
import prod.vo.ProdTypeVO;
import prod.vo.productVO;

@WebServlet("/ProdTagInsert")
public class ProdTagInsert extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public static final Gson GSON = new GsonBuilder().create();
	private ProductServicelm service = new ProductServicelm();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		String ProdType = json2Vo(request, ProdTypeVO.class).getProdType();	
		System.out.println(ProdType);
		service.insertTag(ProdType);
	
	}

	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}

}
