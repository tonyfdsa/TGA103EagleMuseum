package exhibition.service.inft;

import java.util.List;

import exhibition.common.Result;
import exhibition.vo.ExhibitionVO;
import exhibition.vo.ExhibitionVOo;

public interface ExhibitionServiceIn {

	public Result getAll() throws Exception;
	
	public Result getById(Integer id);
	
	public Result getByName(String exhibitionName);
	
	public Result getByDate(String exhibitionStartDate, String exhibitionEndDate);
	
	public Result insert(ExhibitionVOo vo);
}
