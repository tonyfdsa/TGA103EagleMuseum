package index_exhibition_service;

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
		dto.setExhibitionStartDate(vo.getExhibitionStartDate());
		dto.setExhibitionEndDate(vo.getExhibitionEndDate());
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
	
	public void updateExhibition(IndexExhibitionVo update) {
		dao.updateExhibition(update);
	}
	
	public Long insertExhibition(IndexExhibitionVo insert) {
		return dao.insertExhibition(insert);
	}
	
	private String getBase64(byte[] img) {
		return Global.BASE64 + Base64.getEncoder().encodeToString(img);
	}
	
	
}
