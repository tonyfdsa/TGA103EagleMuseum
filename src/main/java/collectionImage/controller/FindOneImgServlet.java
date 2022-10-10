package collectionImage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;


import collectionImage.service.ColImgService;
import collectionImage.service.impl.ColImgServiceimpl;
import collectionImage.vo.ColImgVO;

@WebServlet("/ImgGetOneById")
@MultipartConfig
public class FindOneImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1;
	private ColImgService service = new ColImgServiceimpl();
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		setHeaders(response);
		
		
		ColImgVO collectionImgNameID = json2Pojo(request, ColImgVO.class);

        writePojo2Json(response, service.getImgName(collectionImgNameID));
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
