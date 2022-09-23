package tag.service.TagServiceImpl;

import java.util.List;

import tag.dao.impl.TagDaoimpl;
import tag.dao.intf.TagDaointf;
import tag.service.TagService;
import tag.vo.TagVO;

public class TagServiceImpl implements TagService {
	private TagDaointf dao;

	public TagServiceImpl() {
		dao = new TagDaoimpl();
	}

	@Override
	public TagVO add(TagVO Tag) {

		if (Tag.getTag() == null) {
			Tag.setMessage("說明未輸入");
			Tag.setSuccessful(false);
			return Tag;
		}
		Tag.setMessage("新增成功");
		Tag.setSuccessful(true);
		return Tag;
	}

	@Override
	public TagVO edit(TagVO tag) {
		if (dao.update(tag) == false) {
			tag.setMessage("資料更改出現錯誤，請聯絡管理員!");
			tag.setSuccessful(false);
			return tag;
		}
//	 	mem = dao.selectByUsername(mem.getMemUsername());

		tag.setMessage("資料更改成功");
		tag.setSuccessful(true);
		return tag;
	}

	@Override
	public TagVO findByPrimaryKey(Integer tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TagVO> getAll() {
		return dao.getAll();
	}



	@Override
	public TagVO findByName(String tag) {
		// TODO Auto-generated method stub
		return null;
	}
}