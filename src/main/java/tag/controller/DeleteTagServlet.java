package tag.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import tag.service.TagService;
import tag.service.TagServiceImpl.TagServiceImpl;
import tag.vo.TagVO;



@WebServlet("/tagDelete")
public class DeleteTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1;
	private TagService service = new TagServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		setHeaders(response);

        TagVO deleteTag = json2Pojo(request, TagVO.class);
        deleteTag = service.deleteTag(deleteTag);
        writePojo2Json(response, deleteTag);

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
