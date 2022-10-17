package index_prod.dao.impl;

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

import index_exhibition.vo.Index_productImgVo;
import index_exhibition.vo.Index_productListVo;
import index_prod.dao.sql.Index_productSQL;

public class Index_prodDao implements Index_proDaoIntf  {

	// 獲取DS使用連線池
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TGA103eagleMuseum");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// 查詢商品
	
	@Override
	public List<Index_productListVo> getAll() throws Exception {
		List<Index_productListVo> list = new ArrayList<Index_productListVo>();

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(Index_productSQL.GET_ALL);) {

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Index_productListVo Index_productListVo = new Index_productListVo();
					Index_productListVo.setProductID(rs.getInt("productID"));
					Index_productListVo.setProdName(rs.getString("prodName"));
					Index_productListVo.setProdDescription(rs.getString("prodDescription"));
					Index_productListVo.setProdStatus(rs.getInt("prodStatus"));
					Index_productListVo.setLaunchTime(rs.getDate("launchTime"));
					Index_productListVo.setBestSeller(rs.getInt("bestSeller"));
					Index_productListVo.setLastUpdateTime(rs.getDate("lastUpdateTime"));
					list.add(Index_productListVo); // Store the row in the list
				}
			}
			return list;
		}
	}

	// 根據商品名稱查詢
	
	@Override
	public List<Index_productListVo> getByName(String productName) throws SQLException {
		List<Index_productListVo> list = new ArrayList<Index_productListVo>();
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(Index_productSQL.GET_BY_Name);) {
			pstmt.setString(1, productName);

			System.out.println(productName);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Index_productListVo Index_productListVo = new Index_productListVo();
					Index_productListVo.setProductID(rs.getInt("productID"));
					Index_productListVo.setProdName(rs.getString("prodName"));
					Index_productListVo.setProdDescription(rs.getString("prodDescription"));
					Index_productListVo.setProdStatus(rs.getInt("prodStatus"));
					Index_productListVo.setLaunchTime(rs.getDate("launchTime"));
					Index_productListVo.setBestSeller(rs.getInt("bestSeller"));
					Index_productListVo.setLastUpdateTime(rs.getDate("lastUpdateTime"));
					list.add(Index_productListVo); // Store the row in the list
				}
			}
			return list;
		}
	}

	// 更新商品狀態
	
	@Override
	public Integer updateStatus(Index_productListVo Index_productListVo) throws Exception {
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(Index_productSQL.UpdateStatus);) {

//			"UPDATE index_productlist set prodStatus=? where prodTypeID=?";
			pstmt.setInt(1, Index_productListVo.getProdStatus());
			pstmt.setInt(2, Index_productListVo.getProductID());
			pstmt.executeUpdate();
		}

		return null;
	}

	
	@Override
	public List<Index_productListVo> prodGetByID(Integer productID) throws Exception {
		List<Index_productListVo> list = new ArrayList<Index_productListVo>();
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(Index_productSQL.GET_BY_ID);) {

			pstmt.setInt(1, productID);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Index_productListVo Index_productListVo = new Index_productListVo();
					Index_productListVo.setProductID(rs.getInt("productID"));
					Index_productListVo.setProdName(rs.getString("prodName"));
					Index_productListVo.setProdDescription(rs.getString("prodDescription"));
					Index_productListVo.setProdStatus(rs.getInt("prodStatus"));
					Index_productListVo.setLaunchTime(rs.getDate("launchTime"));
					Index_productListVo.setBestSeller(rs.getInt("bestSeller"));
					Index_productListVo.setLastUpdateTime(rs.getDate("lastUpdateTime"));
					list.add(Index_productListVo); // Store the row in the list
				}
			}
			return list;
		}
	}

	
	@Override
	public Integer insertProdImg(byte[] img, Integer id) throws Exception {
		List<Index_productImgVo> list = new ArrayList<Index_productImgVo>();
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(Index_productSQL.InsertProdImg);) {
			pstmt.setInt(1, id);
			pstmt.setBytes(2, img);

			pstmt.executeUpdate();
			return 1;
		}
	}

	
	@Override
	public List<Index_productImgVo> prodGetImg(Integer prodID) throws Exception {
		List<Index_productImgVo> list = new ArrayList<Index_productImgVo>();
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(Index_productSQL.GetImgByID);) {
			pstmt.setInt(1, prodID);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Index_productImgVo VO = new Index_productImgVo();
					VO.setProductgetimg(rs.getBytes("productimg"));

					list.add(VO); // Store the row in the list
				}
			}
			return list;
		}
	}

	
	@Override
	public int prodDeImg(Integer prodID) throws Exception {
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(Index_productSQL.ProdDeImg);) {
			System.out.println(prodID);
			pstmt.setInt(1, prodID);
			pstmt.executeUpdate();
			return 1;
		}
	}

	
	@Override
	public List<Index_productImgVo> prodImgGetAll() throws Exception {
		List<Index_productImgVo> list = new ArrayList<Index_productImgVo>();
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(Index_productSQL.GetAllImg);) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Index_productImgVo VO = new Index_productImgVo();
					VO.setProductgetimg(rs.getBytes("productimg"));
					VO.setProductID(rs.getInt("productID"));

					list.add(VO);
				}
			}
			return list;
		}
	}

	@Override
	public List<Index_productListVo> insertProdDescription(String Description) throws Exception {
		List<Index_productListVo> list = new ArrayList<Index_productListVo>();
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(Index_productSQL.InsertWord);) {
			pstmt.setString(1, Description);

			System.out.println(Description);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Index_productListVo Index_productListVo = new Index_productListVo();
					Index_productListVo.setProdDescription(rs.getString("prodDescription"));
					list.add(Index_productListVo); 
				}
			}
			return list;
		}
	}

	

}
