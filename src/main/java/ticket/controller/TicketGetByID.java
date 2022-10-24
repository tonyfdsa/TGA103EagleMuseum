package ticket.controller;

import static prod.common.setHeaders.setHeaders;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ticket.service.impl.TicketServiceIm;
import ticket.vo.TicketVOo;

@WebServlet("/TicketGetByID")
public class TicketGetByID extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private TicketServiceIm service = new TicketServiceIm();
	
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
			
		}
		
		PrintWriter out = response.getWriter();
		TicketVOo vo = gson.fromJson(request.getReader().readLine(), TicketVOo.class);
		out.print(gson.toJson(service.getById(vo.getTicketID())));	
	}

	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}

}
