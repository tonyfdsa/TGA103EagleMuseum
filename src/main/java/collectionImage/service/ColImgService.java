package collectionImage.service;

import java.util.List;

import collectionImage.vo.ColImgVO;
import core.service.CoreService;

public interface ColImgService  extends CoreService{
	ColImgVO add(ColImgVO ColImg);
	ColImgVO edit(ColImgVO ColImg);
	ColImgVO remove(ColImgVO ColImg);
	public List<ColImgVO> getAll();

}
