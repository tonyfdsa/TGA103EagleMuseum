package collection.dao.impl;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.query.Query;

import collection.dao.intf.CollectionDaointf;
import collection.dao.sql.CollectDaoSQL;
import collection.vo.CollectionVO;

public class CollectionDaoimpl implements CollectionDaointf {

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
	public boolean insertCol(CollectionVO collectionVO) {

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
	public boolean updateCol(CollectionVO collectionVO) {

		int rowCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SQL.UPDATE);
			System.out.println("連線成功");
			pstmt.setString(1, collectionVO.getCollectionTitle());
			pstmt.setString(2, collectionVO.getCollectionText());
			pstmt.setString(3, collectionVO.getCollectionEar());
			pstmt.setBoolean(4, collectionVO.getCollectionStatus());
			pstmt.setString(5, collectionVO.getCollectionMaterial());
			pstmt.setInt(6, collectionVO.getCollectionID());

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
			 PreparedStatement pstm = con.prepareStatement(SQL.GET_ALL);)
		{
			System.out.println("連線成功");

			try (ResultSet rs = pstm.executeQuery()) {
				while (rs.next()) {
					CollectionVO collection = new CollectionVO();
					collection.setCollectionID(rs.getInt("collectionID"));
					collection.setCollectionTitle(rs.getString("collectionTitle"));
					collection.setCollectionText(rs.getString("collectionText"));
					collection.setCollectionEar(rs.getString("collectionEar"));
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
	public CollectionVO findByPrimaryKey(Integer id) {
//		return getSession().load(CollectionVO.class, id);

//		Query<CollectionVO> query = getSession().createQuery("FROM collection WHERE collectionID = 1", CollectionVO.class);
//		query.setParameter("collectionID", id);
//		CollectionVO collection = query.uniqueResult();
//		return collection;	
		final String sql = "select * from collection where collectionID = ?";
		try (Connection con = ds.getConnection();
			PreparedStatement pstm = con.prepareStatement(sql);) {
			pstm.setInt(1, id);
			try (ResultSet rs = pstm.executeQuery()) {
				if (rs.next()) {
					CollectionVO collection = new CollectionVO();
					collection.setCollectionID(rs.getInt("collectionID"));
					collection.setCollectionTitle(rs.getString("collectionTitle"));
					collection.setCollectionText(rs.getString("collectionText"));
					collection.setCollectionEar(rs.getString("collectionEar"));
					collection.setCollectionMaterial(rs.getString("collectionMaterial"));
					collection.setCollectionStatus(rs.getBoolean("collectionStatus"));
					collection.setLastUpdateTime(rs.getTimestamp("LastUpdateTime"));
					return collection;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public int insert(CollectionVO pojo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(CollectionVO pojo) {
		return 0;
	}

	@Override
	public CollectionVO selectById(Integer id) {
		return null;
	}

	@Override
	public List<CollectionVO> selectAll() {
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