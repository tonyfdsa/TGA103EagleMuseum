package tag.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import tag.service.TagService;
import tag.service.TagServiceImpl.TagServiceImpl;
import tag.vo.TagVO;

@WebServlet("/tagAdd")
public class AddTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1;
	private TagService service = new TagServiceImpl();
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		setHeaders(response);
		
		TagVO addTagName = json2Pojo(request, TagVO.class);
		addTagName = service.addTag(addTagName);
		writePojo2Json(response, addTagName);


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
