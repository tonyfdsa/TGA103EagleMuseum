package collection.dao.impl;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import collection.dao.intf.CollectionDaointf;
import collection.dao.sql.CollectDaoSQL;
import collection.vo.CollectionVO;

public class CollectionDaoimpl implements CollectionDaointf {

	private static CollectDaoSQL SQL = null;
	private static DataSource ds = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/eagleMuseum_schema");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(CollectionVO collectionVO) {

		int rowCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SQL.INSERT_STMT);
			System.out.println("連線成功");

			pstmt.setString(1, collectionVO.getCollectionTitle());
			pstmt.setString(2, collectionVO.getCollectionEar());
			pstmt.setString(3, collectionVO.getCollectionText());
			pstmt.setString(4, collectionVO.getCollectionMaterial());
			pstmt.setBoolean(5, collectionVO.getCollectionStatus());
			rowCount = pstmt.executeUpdate();
			// Handle any SQL errors
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;

	}

	@Override
	public boolean update(CollectionVO collectionVO) {

		int rowCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SQL.UPDATE);
			System.out.println("連線成功");

			pstmt.setString(1, collectionVO.getCollectionText());
			pstmt.setBoolean(2, collectionVO.getCollectionStatus());
			pstmt.setString(3, collectionVO.getCollectionMaterial());

			rowCount = pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;

	}

	@Override
	public boolean delete(Integer collectionID) {
		int rowCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SQL.DELETE);
			System.out.println("連線成功");

			pstmt.setInt(1, collectionID);
			rowCount = pstmt.executeUpdate();
			// Handle any SQL errors
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;
	}


	@Override
	public List<CollectionVO> getAll() {
		List<CollectionVO> list = new ArrayList<CollectionVO>();

		try (Connection con = ds.getConnection();
			 PreparedStatement pstm = con.prepareStatement(SQL.GET_ALL);) {
			System.out.println("連線成功");

			try (ResultSet rs = pstm.executeQuery()) {
				while(rs.next()) {
					CollectionVO collection = new CollectionVO();
					collection.setCollectionID(rs.getInt("collectionID"));
					collection.setCollectionText(rs.getString("collectionText"));
					collection.setCollectionMaterial(rs.getString("collectionMaterial"));
					collection.setCollectionStatus(rs.getBoolean("collectionStatus"));
					collection.setLastUpdateTime(rs.getTimestamp("LastUpdateTime"));
					list.add(collection);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public CollectionVO findByPrimaryKey(String collectionVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CollectionVO findByName(String collectionVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CollectionVO findByMaterial(String collectionVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CollectionVO findByEar(String collectionVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CollectionVO findByNameMaterial(String collectionVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CollectionVO findByNameEar(String collectionVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CollectionVO findByEarMaterial(String collectionVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CollectionVO findByNEM(String collectionVO) {
		// TODO Auto-generated method stub
		return null;
	}

}