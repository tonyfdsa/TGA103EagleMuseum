package tag.service;

import java.util.List;

import core.service.CoreService;
import tag.vo.TagVO;

public interface TagService  extends CoreService {
	TagVO addTag(TagVO tag);
	TagVO editTag(TagVO tag);
	TagVO findById(TagVO tag);
	TagVO findByName(TagVO tag);
	TagVO deleteTag(TagVO tag);
	public List<TagVO> getAll();
}
