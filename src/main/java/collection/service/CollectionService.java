package collection.service;

import java.util.*;
import collection.vo.CollectionVO;
import core.service.CoreService;

public interface CollectionService extends CoreService {
	CollectionVO add(CollectionVO collection);
	CollectionVO edit(CollectionVO collection);
	CollectionVO findId(CollectionVO collection);
    List<CollectionVO> findName(CollectionVO collection);
    List<CollectionVO> findMaterial(CollectionVO collection);
    List<CollectionVO> findEar(CollectionVO collection);
    
	public List<CollectionVO> getAll();
}
