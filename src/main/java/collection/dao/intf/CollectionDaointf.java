package collection.dao.intf;

import java.util.*;

import collection.vo.CollectionVO;

public interface CollectionDaointf {
	public boolean insert(CollectionVO collectionVO);
	public boolean update(CollectionVO collectionVO);
	public boolean delete(Integer collectionID);
	
	CollectionVO findByPrimaryKey(String collectionVO);
	CollectionVO findByName(String collectionVO);
	CollectionVO findByMaterial(String collectionVO);
	CollectionVO findByEar(String collectionVO);
	CollectionVO findByNameMaterial(String collectionVO);
	CollectionVO findByNameEar(String collectionVO);
	CollectionVO findByEarMaterial(String collectionVO);
	CollectionVO findByNEM(String collectionVO);

	public List<CollectionVO> getAll();
}
