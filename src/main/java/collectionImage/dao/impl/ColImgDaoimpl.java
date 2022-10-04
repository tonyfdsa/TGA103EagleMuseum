package collectionImage.dao.impl;

import java.util.List;
import org.hibernate.query.Query;

import collectionImage.dao.intf.ColImgDaointf;
import collectionImage.vo.ColImgVO;

public class ColImgDaoimpl implements ColImgDaointf {

	@Override
	public int insert(ColImgVO ColImgVO) {
		getSession().persist(ColImgVO);
		return 1;
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
		Query<ColImgVO> query = getSession().createQuery("FROM collectionImage WHERE imageID = :imageID", ColImgVO.class);
		query.setParameter("gymId", id);
		ColImgVO ColImg = query.uniqueResult();
		return ColImg;
	}
	@Override
	public List<ColImgVO> selectAll() {
		Query<ColImgVO> query = getSession().createQuery("FROM ColImgVO", ColImgVO.class);
		List<ColImgVO> list = query.list();
		return list;
	}

	
	
}
