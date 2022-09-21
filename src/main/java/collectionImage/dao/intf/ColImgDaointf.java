package collectionImage.dao.intf;

import collection.vo.CollectionVO;
import collectionImage.vo.ColImgVO;

public interface ColImgDaointf {
	public boolean insert(ColImgVO ColImgVO);
	public boolean update(ColImgVO ColImgVO);
	public boolean delete(Integer collectionID);

}
