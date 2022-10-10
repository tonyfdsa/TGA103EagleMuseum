package collectionImage.dao.impl;

import java.util.List;
import org.hibernate.query.Query;

import collection.vo.CollectionVO;
import collectionImage.dao.intf.ColImgDaointf;
import collectionImage.vo.ColImgVO;

public class ColImgDaoimpl implements ColImgDaointf {

	@Override
	public int insert(ColImgVO colImgVO) {
		try {
			return (int) getSession().save(colImgVO);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int update(ColImgVO ColImg) {
		final StringBuilder hql = new StringBuilder().append("UPDATE colImg SET ");
			hql.append("imageName = :imageName,");
		Query<?> query = getSession().createQuery(hql.toString());
		return query
				.setParameter("imageName", ColImg.getImageName()) 
				.executeUpdate();
	}

	@Override
	public int deleteById(Integer imageID) {
		ColImgVO ColImg = new ColImgVO();
		ColImg.setImageID(imageID);
		getSession().remove(ColImg);
		return 1;
	}

	@Override
	public ColImgVO selectById(Integer id) {
		Query<ColImgVO> query = getSession().createQuery("FROM ColImgVO WHERE imageID = :imageID", ColImgVO.class);
		query.setParameter("imageID", id);
		ColImgVO ColImgId = query.uniqueResult();
		return ColImgId;
	}
	@Override
	public List<ColImgVO> selectAll() {
		Query<ColImgVO> query = getSession().createQuery("FROM ColImgVO", ColImgVO.class);
		List<ColImgVO> list = query.list();
		return list;
	}

	@Override
	public List<ColImgVO> selectByName(ColImgVO colImgVO) {
//		if (colImgVO.getImageName() == null) {
//			return null;
//		}
		try {
			return getSession()
					.createQuery("FROM ColImgVO WHERE collectionID = :collectionID", ColImgVO.class)
					.setParameter("collectionID", colImgVO.getCollectionID())
					.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}



//	@Override
//	public ColImgVO selectByColId(ColImgVO collectionIdImg) {
//		try {
//			return getSession()
//					.createQuery("FROM ColImgVO WHERE collectionID = :collectionID", ColImgVO.class)
//					.setParameter("collectionID", collectionIdImg.getCollectionID())
//					.uniqueResult();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
}
