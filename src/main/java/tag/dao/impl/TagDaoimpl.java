package tag.dao.impl;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import tag.dao.intf.TagDaointf;
import tag.dao.sql.TagDaoSQL;
import tag.vo.TagVO;

public class TagDaoimpl implements TagDaointf {

	private static TagDaoSQL SQL = null;
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
	public boolean insert(TagVO tagVO) {

		int rowCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SQL.INSERT_STMT);
			System.out.println("連線成功");

			pstmt.setString(1, tagVO.getTag());
			rowCount = pstmt.executeUpdate();
			// Handle any SQL errors
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;

	}

	@Override
	public boolean update(TagVO tagVO) {

		int rowCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SQL.UPDATE);
			System.out.println("連線成功");

			pstmt.setString(1, tagVO.getTag());

			rowCount = pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount != 0;

	}

	@Override
	public TagVO findByPrimaryKey(Integer tagVO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public TagVO findByNane(String TagVO) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<TagVO> getAll() {
		List<TagVO> list = new ArrayList<TagVO>();

		try (Connection con = ds.getConnection();
			 PreparedStatement pstm = con.prepareStatement(SQL.GET_ALL);) {
			System.out.println("連線成功");

			try (ResultSet rs = pstm.executeQuery()) {
				while(rs.next()) {
					TagVO tag = new TagVO();
					tag.setTagID(rs.getInt("tagID"));
					tag.setTag(rs.getString("tag"));
					tag.setLastUpdateTime(rs.getTimestamp("lastUpdateTime"));
					list.add(tag);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


}