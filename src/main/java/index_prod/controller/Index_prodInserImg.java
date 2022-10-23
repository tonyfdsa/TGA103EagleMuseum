package index_prod.controller;

import static prod.common.setHeaders.setHeaders;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import index_exhibition.vo.Index_productImgVo;
import index_prod.service.impl.Index_prodServiceImpl;
import prod.common.Result;

@WebServlet("/Index_prodInserImg")
public class Index_prodInserImg {
	
	public static final String BASE64 = "data:image/jpeg;base64,";
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private Index_prodServiceImpl service = new Index_prodServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		
		request.setCharacterEncoding("UTF-8");
		
		
		Index_productImgVo Index_productImgVo = gson.fromJson(request.getReader().readLine(), Index_productImgVo.class);
		Result R = service.insertProdImg(Index_productImgVo.getProductimg().replace(BASE64, ""), Index_productImgVo.getProductID());
		response.getWriter().print(gson.toJson(R));	
	}

	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}

}
