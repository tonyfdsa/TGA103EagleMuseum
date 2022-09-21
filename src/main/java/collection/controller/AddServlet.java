package collection.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import collection.service.CollectionService;
import collection.service.CollectionServiceImpl.CollectionServiceImpl;
import collection.vo.CollectionVO;



@WebServlet("/collectionAdd")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1;
	private CollectionService service = new CollectionServiceImpl();
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		setHeaders(response);

		BufferedReader br = request.getReader();
		String json = br.readLine();
		CollectionVO addCollection = gson.fromJson(json, CollectionVO.class);
		
		response.getWriter().print(gson.toJson(service.add(addCollection)));

	}
	@Override
	 protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  setHeaders(response);
	 }
	
	
	private void setHeaders(HttpServletResponse response) {
		response.setContentType("application/json;charset=UTF-8"); // 重要
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");

		response.addHeader("Access-Control-Allow-Origin", "*"); // 重要
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Headers", "*");
		response.addHeader("Access-Control-Max-Age", "86400");
	}

}
