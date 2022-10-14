package index_prod.controller;

import static prod.common.setHeaders.setHeaders;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import index_prod.service.impl.Index_prodServiceImpl;
import prod.common.Result;

@WebServlet("/Index_prodImgGetAll")
public class Index_prodImgGetAll extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	private Gson gson = new Gson();
	private Index_prodServiceImpl service = new Index_prodServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException,IOException  {
		setHeaders(response);

			Result R  = service.prodGetAllImg();
			response.getWriter().print(gson.toJson(R));


		}
		
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}


}
