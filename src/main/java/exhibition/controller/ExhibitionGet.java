package exhibition.controller;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/ExhibitionGet")
public class ExhibitionGet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
//	public static final Gson GSON = new GsonBuilder().create();
	private ExhibitionServiceIm service = new ExhibitionServiceIm();
	
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		PrintWriter out =  response.getWriter();
		ExhibitionVO vo = gson.fromJson(request.getReader().readLine(), ExhibitionVO.class);
		System.out.println(vo.getExhibitionStartDate());
		System.out.println(vo.getExhibitionEndDate());
		if(vo.getExhibitionName() != null && !"".equals(vo.getExhibitionName())) {
//			System.out.println(1);
			out.print(gson.toJson(service.getByName("%" + vo.getExhibitionName() + "%")));
			return;
		}
//		System.out.println(2);
		out.print(gson.toJson(service.getByDate(vo.getExhibitionStartDate(), vo.getExhibitionEndDate())));	
	}

	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}



}
