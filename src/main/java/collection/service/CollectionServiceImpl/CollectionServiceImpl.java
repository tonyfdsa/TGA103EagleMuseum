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
		
		if (collection.getCollectionTitle() == null) {
			collection.setMessage("名稱未輸入");
			collection.setSuccessful(false);
			return collection;
		}
		
		if (collection.getCollectionText() == null) {
			collection.setMessage("說明未輸入");
			collection.setSuccessful(false);
			return collection;
		}
		if (collection.getCollectionEar() == null) {
			collection.setMessage("朝代未輸入");
			collection.setSuccessful(false);
			return collection;
		}
		if (collection.getCollectionMaterial() == null) {
			collection.setMessage("類別未輸入");
			collection.setSuccessful(false);
			return collection;
		}

		if (collection.getCollectionStatus() == null) {
			collection.setMessage("狀態未輸入");
			collection.setSuccessful(false);
			return collection;
		}
		dao.insert(collection);

		collection.setMessage("新增成功");
		collection.setSuccessful(true);
		return collection;
	}

	@Override
	public CollectionVO edit(CollectionVO collection) {
		if (dao.update(collection) == false) {
			collection.setMessage("資料更改出現錯誤，請聯絡管理員!");
			collection.setSuccessful(false);
			return collection;
		}
		collection.setMessage("資料更改成功");
		collection.setSuccessful(true);
		return collection;
	}

	@Override
	public CollectionVO findByPrimaryKey(Integer collectionVO) {
		return null;
	}

	@Override
	public List<CollectionVO> getAll() {
		return dao.getAll();
	}
}