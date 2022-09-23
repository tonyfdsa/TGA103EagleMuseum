package tag.dao.intf;

import java.util.*;

import tag.vo.TagVO;

public interface TagDaointf {
	public boolean insert(TagVO TagVO);
	public boolean update(TagVO TagVO);
	public TagVO findByPrimaryKey(Integer TagVO);
	public TagVO findByNane(String TagVO);
	public List<TagVO> getAll();
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
