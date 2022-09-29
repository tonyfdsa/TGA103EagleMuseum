package exhibition.service.inft;

import java.util.List;

import exhibition.common.Result;
import exhibition.vo.ExhibitionVO;

public interface ExhibitionServiceIn {

	public Result getAll() throws Exception;
	
	public Result getById(Integer id);
	
	public Result getByName(String exhibitionName);
}
