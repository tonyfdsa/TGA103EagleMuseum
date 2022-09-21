package collectionImage.service;

import collectionImage.vo.ColImgVO;

public interface ColImgService {
	ColImgVO add(ColImgVO ColImg);
	ColImgVO edit(ColImgVO ColImg);
	ColImgVO remove(ColImgVO ColImg);
}
