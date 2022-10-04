package collection.dao.intf;

import java.util.*;

import collection.vo.CollectionVO;
import core.dao.CoreDao;

public interface CollectionDaointf extends CoreDao<CollectionVO, Integer> {
	public boolean insertCol(CollectionVO collectionVO);
	public boolean updateCol(CollectionVO collectionVO);
	public boolean delete(Integer collectionID);
	
	CollectionVO findByPrimaryKey(Integer id);
	List<CollectionVO> findByName(String collectionTitle);
	CollectionVO findByMaterial(String collectionVO);
	CollectionVO findByEar(String collectionVO);
	CollectionVO findByNameMaterial(String collectionVO);
	CollectionVO findByNameEar(String collectionVO);
	CollectionVO findByEarMaterial(String collectionVO);
	CollectionVO findByNEM(String collectionVO);

	public List<CollectionVO> getAll();
}
