package collectionImage.service.impl;

import java.util.List;

import collectionImage.dao.impl.ColImgDaoimpl;
import collectionImage.dao.intf.ColImgDaointf;
import collectionImage.service.ColImgService;
import collectionImage.vo.ColImgVO;
import core.util.Global;

public class ColImgServiceimpl implements ColImgService {
	private ColImgDaointf dao;
	private Global base64;

	public ColImgServiceimpl() {
		dao = new ColImgDaoimpl();
		base64 = new Global();

	}

	@Override
	public int add(ColImgVO colImgVO) {		
		try {	
			if (colImgVO.getCollectionimgStr() == null) {
				colImgVO.setImageName(null);
			} else if (!colImgVO.getCollectionimgStr().equals("")) {
				colImgVO.setImageName(base64.Decoder(colImgVO.getCollectionimgStr()));
			}
			return dao.insert(colImgVO);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


	@Override
	public ColImgVO remove(ColImgVO ColImg) {
		return null;
	}

	@Override
	public List<ColImgVO> getAll() {
		try {
			return dao.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ColImgVO> getImgName(ColImgVO colImgVO) {	
//		try {
//			return dao.selectByName(colImgVO);
//		} 
		
		try {
			List<ColImgVO> list = dao.selectByName(colImgVO);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setCollectionimgStr(base64.Encoder(list.get(i).getImageName()));
			}
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
