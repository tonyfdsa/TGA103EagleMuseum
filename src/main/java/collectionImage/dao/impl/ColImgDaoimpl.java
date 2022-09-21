package collectionImage.dao.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import collection.dao.sql.CollectDaoSQL;
import collectionImage.dao.intf.ColImgDaointf;
import collectionImage.vo.ColImgVO;

public class ColImgDaoimpl implements ColImgDaointf {
	private static CollectDaoSQL SQL = null;
	private static DataSource ds = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TGA103eagleMuseum");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(ColImgVO ColImgVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ColImgVO ColImgVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer collectionID) {
		// TODO Auto-generated method stub
		return false;
	}
}
