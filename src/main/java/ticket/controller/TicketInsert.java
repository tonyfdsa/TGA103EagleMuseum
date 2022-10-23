package ticket.controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ticket.service.impl.TicketServiceIm;
import ticket.vo.TicketVO;

import static prod.common.setHeaders.setHeaders;

@WebServlet("/TicketInsert")
public class TicketInsert extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
//	public static final Gson GSON = new GsonBuilder().create();
	private TicketServiceIm service = new TicketServiceIm();
	
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		TicketVO vo = gson.fromJson(request.getReader().readLine(), TicketVO.class);
		response.getWriter().print(gson.toJson(service.insert(vo)));	
	}
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}
}
