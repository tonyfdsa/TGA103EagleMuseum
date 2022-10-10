package collectionImage.dao.intf;
import java.util.List;

import collectionImage.vo.ColImgVO;
import core.dao.CoreDao;
import tag.vo.TagVO;

public interface ColImgDaointf extends CoreDao<ColImgVO, Integer> {
//	public List<ColImgVO> insertColImg(byte[] decode, Integer id);
	public List<ColImgVO> selectByName(ColImgVO colImgVO);

//	public Integer insertImg(byte[] imageName, Integer id);

}
