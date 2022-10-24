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
	public TagVO addTag(TagVO tag) {
		try {
			if (tag.getTag().isEmpty()) {
				tag.setMessage("類別名稱未輸入");
				tag.setSuccessful(false);
				return tag;
			}

			if (dao.insert(tag) != 1) {
				tag.setMessage("新增發生錯誤!");
				tag.setSuccessful(false);
				return tag;
			}
			tag.setMessage("新增成功");
			tag.setSuccessful(true);
			return tag;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public TagVO editTag(TagVO tag) {
//		final TagVO oTag = dao.findByName(tag.getTag());
//		tag.setTag(tag.getTag());
		try {
			tag.setTagID(tag.getTagID());
			final int resultCount = dao.update(tag);
			tag.setSuccessful(resultCount > 0);
			tag.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
			return tag;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public TagVO findById(TagVO tag) {
		try {
			tag = dao.selectById(tag.getTagID());
			tag.setSuccessful(true);
			return tag;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public TagVO findByName(TagVO tag) {
		try {
			return dao.selectByName(tag.getTag());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<TagVO> getAll() {
		try {
			return (List<TagVO>) dao.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public TagVO deleteTag(TagVO tag) {
		if (dao.deleteById(tag.getTagID()) != 1) {
			tag.setMessage("刪除發生錯誤!");
			tag.setSuccessful(false);
			return tag;
		}
		tag.setMessage("刪除成功");
		tag.setSuccessful(true);
		return tag;
	}
}