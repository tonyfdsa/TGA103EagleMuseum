package tag.service;

import java.util.*;
import tag.vo.TagVO;

public interface TagService {
	TagVO add(TagVO tag);
	TagVO edit(TagVO tag);
	TagVO findByPrimaryKey(Integer tag);
	TagVO findByName(String tag);
	public List<TagVO> getAll();
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
