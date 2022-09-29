package exhibition.controller;

import java.io.IOException;

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

import static prod.common.setHeaders.setHeaders;

@WebServlet("/ExhibitionGetByName")
public class ExhibitionGetByName extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
//	public static final Gson GSON = new GsonBuilder().create();
	private ExhibitionServiceIm service = new ExhibitionServiceIm();
	
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
//		String exhibitionName = json2Vo(request, ExhibitionVO.class).getExhibitionName();
//		exhibitionName = "%" + exhibitionName + "%";
		ExhibitionVO vo = gson.fromJson(request.getReader().readLine(), ExhibitionVO.class);
		System.out.println(vo.getExhibitionName());
		response.getWriter().print(gson.toJson(service.getByName("%" + vo.getExhibitionName() + "%")));	
	}

	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}



}
