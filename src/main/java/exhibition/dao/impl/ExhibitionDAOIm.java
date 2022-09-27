package exhibition.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import exhibition.dao.intf.ExhibitionDAOIn;
import exhibition.dao.sql.ExhibitionSQL;
import exhibition.vo.ExhibitionVO;


public class ExhibitionDAOIm implements ExhibitionDAOIn {

	private DataSource ds;
	
	public ExhibitionDAOIm() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TGA103eagleMuseum");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ExhibitionVO> getAll() throws Exception {
		List<ExhibitionVO> list = new ArrayList<ExhibitionVO>();
//		try with resources
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(ExhibitionSQL.GET_ALL);) {
			System.out.println("連線成功");
			try (ResultSet rs = pstmt.executeQuery()) {
				ExhibitionVO vo;
				while (rs.next()) {
//					EXHIBITION_ID, EXHIBITION_TYPE, EXHIBITION_NAME, EXHIBITION_START_DATE, 
//					EXHIBITION_END_DATE, EXHIBITION_ARTICLE, EXHIBITION_STATUS, LAST_UPDATE_TIME,
					vo = new ExhibitionVO();
					vo.setExhibitionId(rs.getInt("exhibitionID"));
					vo.setExhibitionType(rs.getInt("exhibitionType"));
					vo.setExhibitionName(rs.getString("exhibitionName"));
					vo.setExhibitionStartDate(rs.getString("exhibitionStartDate"));
					vo.setExhibitionEndDate(rs.getString("exhibitionEndDate"));;
					vo.setExhibitionArticle(rs.getString("exhibitionArticle"));
					vo.setExhibitionStatus(rs.getInt("exhibitionStatus"));
					vo.setLastUpdateTime(rs.getString("lastUpdateTime"));
					list.add(vo);
				}
			}
			return list;
		}
	}

	@Override
	public List<ExhibitionVO> getById(Integer id) {
		return null;
	}
	
	@Override
	public List<ExhibitionVO> getByName(String exhibitionName) throws Exception {
		List<ExhibitionVO> list = new ArrayList<ExhibitionVO>();
//		try with resources
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(ExhibitionSQL.GET_BY_NAME);) {
			System.out.println("連線成功");
			pstmt.setString(1, exhibitionName);
			try (ResultSet rs = pstmt.executeQuery()) {
				ExhibitionVO vo;
				while (rs.next()) {
//					EXHIBITION_ID, EXHIBITION_TYPE, EXHIBITION_NAME, EXHIBITION_START_DATE, 
//					EXHIBITION_END_DATE, EXHIBITION_ARTICLE, EXHIBITION_STATUS, LAST_UPDATE_TIME,
					vo = new ExhibitionVO();
					vo.setExhibitionId(rs.getInt("exhibitionID"));
					vo.setExhibitionType(rs.getInt("exhibitionType"));
					vo.setExhibitionName(rs.getString("exhibitionName"));
					vo.setExhibitionStartDate(rs.getString("exhibitionStartDate"));
					vo.setExhibitionEndDate(rs.getString("exhibitionEndDate"));;
					vo.setExhibitionArticle(rs.getString("exhibitionArticle"));
					vo.setExhibitionStatus(rs.getInt("exhibitionStatus"));
					vo.setLastUpdateTime(rs.getString("lastUpdateTime"));
					list.add(vo);
				}
			}
			return list;
		}
	}

	
	
	
	
	
}