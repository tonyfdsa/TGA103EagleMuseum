package index_exhibition_service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import index_exhibition.dao.IndexExhibitionDao;
import index_exhibition.vo.IndexExhibitionVo;
import index_exhibition_dto.IndexExhibitionDto;
import prod.common.Global;

public class IndexExhibitionService {
	
	private IndexExhibitionDao dao = new IndexExhibitionDao();
	
	public IndexExhibitionDto getLastExhibition() throws Exception{
		
		IndexExhibitionDto dto = new IndexExhibitionDto();

		IndexExhibitionVo vo = dao.getLatestExhibition();
		
		dto.setExhibitionID(vo.getExhibitionID());
		dto.setExhibitionName(vo.getExhibitionName());
		dto.setExhibitionArticle(vo.getExhibitionArticle());
		dto.setExhibitionStartDate(vo.getExhibitionStartDate().toString());
		dto.setExhibitionEndDate(vo.getExhibitionEndDate().toString());
		dto.setExhibitionImageBase64(getBase64(vo.getExhibitionImage()));
		
		return dto;
	}
	
	public static void main(String[] args) {
		IndexExhibitionService service = new IndexExhibitionService();
		try {
			System.out.println(service.getLastExhibition());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateExhibition(IndexExhibitionDto update) {

		IndexExhibitionVo vo = new IndexExhibitionVo();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		vo.setExhibitionID(update.getExhibitionID());
		vo.setExhibitionName(update.getExhibitionName());
		vo.setExhibitionArticle(update.getExhibitionArticle());
		vo.setExhibitionStartDate(LocalDateTime.parse(update.getExhibitionStartDate(), formatter));
		vo.setExhibitionEndDate(LocalDateTime.parse(update.getExhibitionEndDate(), formatter));
		vo.setExhibitionImage(toImage(update.getExhibitionImageBase64()));

		dao.updateExhibition(vo);
	}
	
	public Long insertExhibition(IndexExhibitionDto insert) {
		IndexExhibitionVo vo = new IndexExhibitionVo();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		vo.setExhibitionID(insert.getExhibitionID());
		vo.setExhibitionName(insert.getExhibitionName());
		vo.setExhibitionArticle(insert.getExhibitionArticle());
		vo.setExhibitionStartDate(LocalDateTime.parse(insert.getExhibitionStartDate(), formatter));
		vo.setExhibitionEndDate(LocalDateTime.parse(insert.getExhibitionEndDate(), formatter));
		vo.setExhibitionImage(toImage(insert.getExhibitionImageBase64()));
		return dao.insertExhibition(vo);
	}
	
	private String getBase64(byte[] img) {
		return Global.BASE64 + Base64.getEncoder().encodeToString(img);
	}
	
	private byte[] toImage(String base64) {
		return Base64.getDecoder().decode(base64);
	}
	
	
}
