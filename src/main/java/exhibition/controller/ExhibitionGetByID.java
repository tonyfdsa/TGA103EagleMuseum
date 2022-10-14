package exhibition.controller;

import static prod.common.setHeaders.setHeaders;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import exhibition.service.impl.ExhibitionServiceIm;
import exhibition.vo.ExhibitionVO;

@WebServlet("/ExhibitionGetByID")
public class ExhibitionGetByID extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private ExhibitionServiceIm service = new ExhibitionServiceIm();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		String pathInfo = request.getPathInfo();
		String id;

		// 判斷是否有 ID
		if (pathInfo != null) {
			id = pathInfo.split("/")[1];
		} else {
			id = request.getParameter("exhibitionID");
		}
		
		PrintWriter out = response.getWriter();
		ExhibitionVO vo = gson.fromJson(request.getReader().readLine(), ExhibitionVO.class);
		out.print(gson.toJson(service.getById(vo.getExhibitionID())));	
	}

	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}

}
