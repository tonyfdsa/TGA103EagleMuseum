package prod.controller;

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

@WebServlet("/ProdImgGetAll")
public class ProdImgGetAll extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private Gson gson = new Gson();
	private ProductServicelm service = new ProductServicelm();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException,IOException  {
		setHeaders(response);

			Result R  = service.prodGetAllImg();
			response.getWriter().print(gson.toJson(R));


		}
		
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}
}
