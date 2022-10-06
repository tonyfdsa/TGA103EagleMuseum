package exhibition.controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import exhibition.common.Result;
import exhibition.service.impl.ExhibitionServiceIm;
import exhibition.vo.ExhibitionVO;
import exhibition.vo.ExhibitionVOo;

import static prod.common.setHeaders.setHeaders;

@WebServlet("/ExhibitionInsert")
public class ExhibitionInsert extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
//	public static final Gson GSON = new GsonBuilder().create();
	private ExhibitionServiceIm service = new ExhibitionServiceIm();
	
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		ExhibitionVOo vo = gson.fromJson(request.getReader().readLine(), ExhibitionVOo.class);
//		System.out.println(vo.getExhibitionName());
		vo.getExhibitionImg(Base64.getDecoder().decode(vo.getImg()));
		System.out.println(vo.getImg());
		
		response.getWriter().print(gson.toJson(service.insert(vo)));	
	}
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}
}
