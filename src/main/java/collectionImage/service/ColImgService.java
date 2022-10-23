package collectionImage.service;

import java.util.List;

import collectionImage.vo.ColImgVO;
import core.service.CoreService;
import lombok.val;

public interface ColImgService  extends CoreService{
	int add(ColImgVO colImgVO);
	ColImgVO remove(ColImgVO ColImg);
	public List<ColImgVO> getAll();
	public List<ColImgVO> getImgName(ColImgVO colImgVO);

}
