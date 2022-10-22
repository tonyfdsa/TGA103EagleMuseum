package collection.service.CollectionServiceImpl;

import java.util.List;


import collection.dao.impl.CollectionDaoimpl;
import collection.dao.intf.CollectionDaointf;
import collection.service.CollectionService;
import collection.vo.CollectionVO;

public class CollectionServiceImpl implements CollectionService {
	private CollectionDaointf dao;

	public CollectionServiceImpl() {
		dao = new CollectionDaoimpl();
	}

	@Override
	public CollectionVO add(CollectionVO collection) {

		if (collection.getCollectionTitle().isEmpty()) {
			collection.setMessage("名稱未輸入");
			collection.setSuccessful(false);
			return collection;
		}

		if (collection.getCollectionText().isEmpty()) {
			collection.setMessage("說明未輸入");
			collection.setSuccessful(false);
			return collection;
		}
		if (collection.getCollectionEar().isEmpty()) {
			collection.setMessage("朝代未輸入");
			collection.setSuccessful(false);
			return collection;
		}
		if (collection.getCollectionMaterial().isEmpty()) {
			collection.setMessage("類別未輸入");
			collection.setSuccessful(false);
			return collection;
		}

		if (collection.getCollectionStatus() == null) {
			collection.setMessage("狀態未輸入");
			collection.setSuccessful(false);
			return collection;
		}
		if (dao.insertCol(collection) == false) {
			collection.setMessage("新增發生錯誤!");
			collection.setSuccessful(false);
			return collection;

		}
		collection.setMessage("新增成功");
		collection.setSuccessful(true);
		return collection;
	}

	@Override
	public CollectionVO edit(CollectionVO collection) {
		if (collection.getCollectionTitle().isEmpty()) {
			collection.setMessage("資料更改出現錯誤，名稱不可為空");
			collection.setSuccessful(false);
			return collection;
		}
		if (collection.getCollectionText().isEmpty()) {
			collection.setMessage("資料更改出現錯誤，說明不可為空");
			collection.setSuccessful(false);
			return collection;
		}
		if (collection.getCollectionStatus() == null) {
			collection.setMessage("資料更改出現錯誤，狀態未輸入");
			collection.setSuccessful(false);
			return collection;
		}
		
		if (dao.updateCol(collection) == false) {
			collection.setMessage("新增發生錯誤!");
			collection.setSuccessful(false);
			return collection;

		}
		collection.setMessage("資料更改成功");
		collection.setSuccessful(true);
		return collection;
	}

	@Override
	public CollectionVO findId(CollectionVO collection) {
		try {
			return dao.findByPrimaryKey(collection.getCollectionID());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
//		Query<CollectionVO> query = getsession().createQuery("FROM collection WHERE collectionID = :collectionID", CollectionVO.class);
//		query.setParameter("collectionID", id);
//		CollectionVO collection = query.uniqueResult();
//		return collection;		

	@Override
	public List<CollectionVO> getAll() {
		return dao.getAll();
	}

	@Override
	public List<CollectionVO> findName(CollectionVO collection) {
		try {
			return dao.findByName(collection.getCollectionTitle());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CollectionVO> findMaterial(CollectionVO collection) {
		try {
			return dao.findByMaterial(collection.getCollectionMaterial());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CollectionVO> findEar(CollectionVO collection) {
		try {
			return dao.findByEar(collection.getCollectionEar());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}