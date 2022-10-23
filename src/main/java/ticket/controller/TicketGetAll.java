package ticket.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ticket.service.impl.TicketServiceIm;

@WebServlet("/TicketGetAll")
public class TicketGetAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Gson _gson = new Gson();
	private TicketServiceIm service = new TicketServiceIm();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		String pathInfo = request.getPathInfo();
		String id;

		// 判斷是否有 ID
		if (pathInfo != null) {
			id = pathInfo.split("/")[1];
		} else {
			id = request.getParameter("ticketID");
		}
		// 資料轉換 JSON 後回傳
		response.getWriter().print(_gson.toJson(id == null ? service.getAll() : service.getById(Integer.parseInt(id))));
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		PrintWriter out = response.getWriter();
		// Read POST
		BufferedReader read = request.getReader();
		// 存字串
		String json = read.readLine();
		
	}
	
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);

	}

	/*
	 * 誇域
	 */
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