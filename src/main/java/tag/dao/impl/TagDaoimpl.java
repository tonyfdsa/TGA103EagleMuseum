package tag.dao.impl;


import java.util.List;

import org.hibernate.query.Query;

import tag.dao.intf.TagDaointf;
import tag.vo.TagVO;

public class TagDaoimpl implements TagDaointf {

	@Override
	public int insert(TagVO tag) {
		getSession().persist(tag);
		return 1;
	}

	@Override
	public int deleteById(Integer id) {
		TagVO  tag = new TagVO();
		tag.setTagID(id);
		getSession().remove(tag);
		return 1;
	}

	@Override
	public int update(TagVO tag) {
		final StringBuilder hql = new StringBuilder().append("UPDATE tag SET ");
		 hql.append("tag = :tag, ");
		Query<?> query = getSession().createQuery(hql.toString());
		return query
				.setParameter("tag", tag.getTag())
				.executeUpdate();
	}

	@Override
	public TagVO selectById(Integer id) {
		Query<TagVO> query =  getSession().createQuery("FROM tag where tagId = :tagId", TagVO.class);
		query.setParameter("tagId", id);
		TagVO tag = query.uniqueResult();
		return tag;
	}

	@Override
	public List<TagVO> selectAll() {
		Query<TagVO> query =  getSession().createQuery("FROM tag ", TagVO.class);
		List<TagVO> list = query.list();
		return list;
	}

	@Override
	public TagVO selectByName(String tag) {
		Query<TagVO> query =  getSession().createQuery("FROM tag where tag = :tag", TagVO.class);
		query.setParameter("tag", tag);
		TagVO tagName = query.uniqueResult();
		return tagName;
	}
}