package prod.controller;

import static prod.common.json2VO.json2Vo;
import static prod.common.setHeaders.setHeaders;


import java.io.IOException;
import java.util.regex.Matcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.bytebuddy.agent.builder.AgentBuilder.CircularityLock.Global;
import prod.common.Result;
import prod.service.impl.ProductServicelm;
import prod.vo.ProdImgVO;
import prod.vo.productVO;



@WebServlet("/InsertProdImg")
public class ProdInsertImg extends HttpServlet{
	public static final String BASE64 = "data:image/jpeg;base64,";
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private ProductServicelm service = new ProductServicelm();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		
		request.setCharacterEncoding("UTF-8");
		
//		ProdImgVO prodImgVO =  json2Vo(request, ProdImgVO.class);
//		Integer ProdID = prodImgVO.getProductID();
//		String ProdImg = json2Vo(request, ProdImgVO.class).getProductimg();
//		System.out.println(ProdID);
//		System.out.println(ProdImg);
		
		ProdImgVO prodImgVO = gson.fromJson(request.getReader().readLine(), ProdImgVO.class);
		Result R = service.insertProdImg(prodImgVO.getProductimg().replace(BASE64, ""), prodImgVO.getProductID());
		response.getWriter().print(gson.toJson(R));	
	}

	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}

}
