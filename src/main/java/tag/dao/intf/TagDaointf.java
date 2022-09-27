package tag.dao.intf;

import java.util.*;

import core.dao.CoreDao;
import tag.vo.TagVO;

public interface TagDaointf extends CoreDao<TagVO, Integer> {

	public TagVO selectByName(String tag);
}
