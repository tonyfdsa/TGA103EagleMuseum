package index_exhibition_controller;

import static prod.common.setHeaders.setHeaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import index_exhibition_dto.IndexExhibitionDto;
import index_exhibition_service.IndexExhibitionService;
import prod.common.Result;
import static core.util.CommonUtil.*;

/**
 * Servlet implementation class IndexExhibitionGetLastestExhibition
 */
@WebServlet("/IndexExhibitionGetLastestExhibition")
public class IndexExhibitionGetLastestExhibition extends HttpServlet {

	private IndexExhibitionService service = new IndexExhibitionService();
//	private Gson gson = new Gson();
	private Gson gson = new GsonBuilder()
	        .setPrettyPrinting()
	        .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
	        .create();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		setHeaders(response);
		Result r = new Result();
		try {
			IndexExhibitionDto dto = service.getLastExhibition();
			r.success(dto);
			response.getWriter().print(gson.toJson(r));	
		} catch (Exception e) {
			r.fail(e.toString());
			response.getWriter().print(gson.toJson(r));
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		setHeaders(resp);
		IndexExhibitionDto dto = gson.fromJson(req.getReader(), IndexExhibitionDto.class);
//        IndexExhibitionDto dto = json2Pojo(req, IndexExhibitionDto.class);
        System.out.println(dto);
        Long id = service.insertExhibition(dto);
        writePojo2Json(resp, id);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		setHeaders(resp);
		IndexExhibitionDto dto = gson.fromJson(req.getReader(), IndexExhibitionDto.class);
        service.updateExhibition(dto);
	}


	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}
}
class LocalDateAdapter implements JsonSerializer<LocalDateTime> {

    public JsonElement serialize(LocalDateTime date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
    }
}
