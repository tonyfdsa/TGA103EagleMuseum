package index_prod.service.impl;

import java.util.List;

import index_exhibition.vo.Index_productImgVo;
import index_exhibition.vo.Index_productListVo;
import prod.common.Result;

public interface Index_prodServie_interface {

	Result getAll();

	Result getByName(String prodName);

	Result updateStatus(Index_productListVo Index_productListVo);

	Result prodGetByID(int prodID);

	Result insertProdImg(String img, Integer id);

	Result prodGetImg(Integer id);

	Result prodDeImg(Integer id);

	Result prodGetAllImg();

	//工具
	List<Index_productImgVo> getBase64(List<Index_productImgVo> list);

	Result insertProdWord(String description);

}