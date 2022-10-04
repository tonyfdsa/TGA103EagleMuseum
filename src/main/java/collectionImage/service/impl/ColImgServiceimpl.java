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
	public ColImgVO add(ColImgVO ColImg) {
		try {
			if (ColImg.getImageName() == null) {
				ColImg.setMessage("圖片名稱未輸入");
				ColImg.setSuccessful(false);
				return ColImg;
			}
			if (ColImg.getCollectionimgStr() == null) {
				ColImg.setImageName(null);
			} else if (!ColImg.getCollectionimgStr().equals("")) {
				ColImg.setImageName(base64.Decoder(ColImg.getCollectionimgStr()));
			}
			
			ColImg.setMessage("新增成功");
			ColImg.setSuccessful(true);
			return ColImg;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ColImgVO edit(ColImgVO ColImg) {
		return null;
	}

	@Override
	public ColImgVO remove(ColImgVO ColImg) {
		return null;
	}

	@Override
	public List<ColImgVO> getAll() {
		try {
			return (List<ColImgVO>) dao.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
