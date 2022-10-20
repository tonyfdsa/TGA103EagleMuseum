package prod.dao.impl;

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

import org.hibernate.Session;

import core.util.HibernateUtil;
import prod.dao.intf.ProductDAO_interface;

import prod.dao.sql.ProductSQL;
import prod.vo.CartVO;
import prod.vo.ProdImgVO;
import prod.vo.ProdTypeVO;
import prod.vo.productVO;

public class ProductDAO implements ProductDAO_interface {

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
	
	public Session getSession() {
//		return session;
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	// 查詢商品
	public List<productVO> getAll() throws Exception {
		
		return getSession().createQuery("from productVO")
					.getResultList();
		
		
//		JDBC
//		List<productVO> list = new ArrayList<productVO>();
//
//		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(ProductSQL.GET_ALL);) {
//			
//			try (ResultSet rs = pstmt.executeQuery()) {
//				while (rs.next()) {
//					productVO productVO = new productVO();
//					productVO.setProductID(rs.getInt("productID"));
//					productVO.setProdName(rs.getString("prodName"));
//					productVO.setProdTypeID(rs.getInt("prodTypeID"));
//					productVO.setProdPrice(rs.getInt("prodPrice"));
//					productVO.setDiscountID(rs.getInt("discountID"));
//					productVO.setProdDescription(rs.getString("prodDescription"));
//					productVO.setProdStatus(rs.getInt("prodStatus"));
//					productVO.setCreatTime(rs.getDate("creatTime"));
//					productVO.setLaunchTime(rs.getDate("launchTime"));
//					productVO.setSellQuantity(rs.getInt("sellQuantity"));
//					productVO.setProdInStock(rs.getInt("prodInStock"));
//					productVO.setBestSeller(rs.getInt("bestSeller"));
//					productVO.setLastUpdateTime(rs.getDate("lastUpdateTime"));
//					list.add(productVO); // Store the row in the list
//				}
//			}
//			return list;
//		}
	}

	// 新增商品 
	@Override
	public Integer insert(productVO productVO) throws Exception {
//		Hibernate
		productVO.setBestSeller(0);
		getSession().persist(productVO);
		return null;
//		JDBC
//		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(ProductSQL.Insert);) {
//			
//			pstmt.setString(1, productVO.getProdName());
//			pstmt.setInt(2, productVO.getProdTypeID());
//			pstmt.setInt(3, productVO.getProdPrice());
//			pstmt.setString(4, productVO.getProdDescription());
//			pstmt.setInt(5, productVO.getProdInStock());
//			pstmt.executeUpdate();
//		}
//		return null;
	}
		
	
	// 更新商品資訊
	@Override
	public productVO update(productVO productVO) throws Exception {
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(ProductSQL.Update);) {
			
			try (ResultSet rs = pstmt.executeQuery()) {

				pstmt.setString(1, productVO.getProdName());
				pstmt.setInt(2, productVO.getProdTypeID());
				pstmt.setInt(3, productVO.getDiscountID());
				pstmt.setString(4, productVO.getProdDescription());
				pstmt.setInt(5, productVO.getProdStatus());
				pstmt.setInt(6, productVO.getSellQuantity());
				pstmt.setInt(7, productVO.getProdInStock());
				pstmt.setInt(8, productVO.getBestSeller());

				pstmt.executeUpdate();
			}
		}
		return null;
	}

	// 根據商品名稱查詢
	@Override
	public List<productVO> getByName(String productName) throws SQLException {
		List<productVO> list = new ArrayList<productVO>();
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(ProductSQL.GET_BY_Name);) {
			pstmt.setString(1, productName);
			
			System.out.println(productName);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					productVO productVO = new productVO();
					productVO.setProductID(rs.getInt("productID"));
					productVO.setProdName(rs.getString("prodName"));
					productVO.setProdTypeID(rs.getInt("prodTypeID"));
					productVO.setProdPrice(rs.getInt("prodPrice"));
					productVO.setDiscountID(rs.getInt("discountID"));
					productVO.setProdDescription(rs.getString("prodDescription"));
					productVO.setProdStatus(rs.getInt("prodStatus"));
					productVO.setSellQuantity(rs.getInt("sellQuantity"));
					productVO.setProdInStock(rs.getInt("prodInStock"));
					productVO.setBestSeller(rs.getInt("bestSeller"));
					productVO.setLastUpdateTime(rs.getTimestamp("lastUpdateTime"));
					list.add(productVO); // Store the row in the list
				}
			}
			return list;
		}
	}


	// 更新商品狀愛
	@Override
	public Integer updateStatus(productVO productVO) throws Exception {
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(ProductSQL.UpdateStatus);) {
			
//		"UPDATE productlist set prodStatus=? where prodTypeID=?";
				pstmt.setInt(1, productVO.getProdStatus());
				pstmt.setInt(2, productVO.getProductID());
				pstmt.executeUpdate();
			}
		
		return null;
	}
	//Insert Tag
	@Override
	public Integer insertTag(String prodType) throws SQLException {
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(ProductSQL.InsertTag);) {
			
//			"INSERT INTO producttype(prodType) VALUES(?)";
			pstmt.setString(1, prodType);
			pstmt.executeUpdate();
			return 1;
		}
	}

	@Override
	public List<ProdTypeVO> prodTagGetAll() throws Exception {
		List<ProdTypeVO> list = new ArrayList<ProdTypeVO>();
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(ProductSQL.TagGET_ALL);) {
			
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					ProdTypeVO prodTypeVO = new ProdTypeVO();
					prodTypeVO.setProdTypeId(rs.getInt("prodTypeID"));
					prodTypeVO.setProdType(rs.getString("prodType"));
					prodTypeVO.setLastUpdateTime(rs.getDate("lastUpdateTime"));
					
					list.add(prodTypeVO); // Store the row in the list
				}
			}
			return list;
		}
	}

	@Override
	public List<productVO> prodGetByID(Integer productID) throws Exception {
		List<productVO> list = new ArrayList<productVO>();
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(ProductSQL.GET_BY_ID);) {
			
			pstmt.setInt(1, productID);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					productVO productVO = new productVO();
					productVO.setProductID(rs.getInt("productID"));
					productVO.setProdName(rs.getString("prodName"));
					productVO.setProdTypeID(rs.getInt("prodTypeID"));
					productVO.setProdPrice(rs.getInt("prodPrice"));
					productVO.setDiscountID(rs.getInt("discountID"));
					productVO.setProdDescription(rs.getString("prodDescription"));
					productVO.setProdStatus(rs.getInt("prodStatus"));
					productVO.setSellQuantity(rs.getInt("sellQuantity"));
					productVO.setProdInStock(rs.getInt("prodInStock"));
					productVO.setBestSeller(rs.getInt("bestSeller"));
					productVO.setLastUpdateTime(rs.getTimestamp("lastUpdateTime"));
					list.add(productVO); // Store the row in the list
				}
			}
			return list;
		}
	}

	@Override
	public Integer insertProdImg(byte[] img, Integer id) throws Exception {
		List<ProdImgVO> list = new ArrayList<ProdImgVO>();
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(ProductSQL.InsertProdImg);) {
			pstmt.setInt(1, id);
			pstmt.setBytes(2, img);

			
			
			pstmt.executeUpdate();
			return 1;
		}
	}

	@Override
	public List<ProdImgVO> prodGetImg(Integer prodID) throws Exception {
		List<ProdImgVO> list = new ArrayList<ProdImgVO>();
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(ProductSQL.GetImgByID);) {
			pstmt.setInt(1, prodID);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					ProdImgVO VO = new ProdImgVO();
					VO.setProductgetimg(rs.getBytes("productimg"));

					
					list.add(VO); // Store the row in the list
				}
			}
			return list;
		}
	}

	@Override
	public int prodUpdate(productVO productVO) throws Exception {
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(ProductSQL.UpdateProd);) {
			
//			UpdateProd = "update productlist set prodName=? , 
//			prodTypeID=?, prodPrice=?, prodDescription=?, 
//			prodStatus=?, prodInStock=? lastUpdateTime=now()where productID=?";
			pstmt.setString(1, productVO.getProdName());
			pstmt.setInt(2, productVO.getProdTypeID());
			pstmt.setInt(3, productVO.getProdPrice());
			pstmt.setString(4, productVO.getProdDescription());
			pstmt.setInt(5, productVO.getProdStatus());
			pstmt.setInt(6, productVO.getProdInStock());
			pstmt.setInt(7, productVO.getProductID());
			pstmt.executeUpdate();
			return 1;
		
		}
	}

	@Override
	public int prodDeImg(Integer prodID) throws Exception {
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(ProductSQL.ProdDeImg);) {
//			ProdDeImg = "delete from productimg where productID=?"
			System.out.println(prodID);
			pstmt.setInt(1, prodID);
			pstmt.executeUpdate();
			return 1;
			}
	}

	@Override
	public List<ProdImgVO> prodImgGetAll() throws Exception {
		List<ProdImgVO> list = new ArrayList<ProdImgVO>();
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(ProductSQL.GetAllImg);) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					ProdImgVO VO = new ProdImgVO();
					VO.setProductgetimg(rs.getBytes("productimg"));
					VO.setProductID(rs.getInt("productID"));

					
					list.add(VO); // Store the row in the list
				}
			}
			return list;
		}
	}


	//購物車
	@Override
	public CartVO cartgetProd(Integer prodID) throws Exception {
		CartVO VO = new CartVO();
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(ProductSQL.GET_BY_ID);) {
//			GET_BY_ID = "SELECT * FROM productlist where productID = ?
			pstmt.setInt(1, prodID);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					VO.setProductID(rs.getInt("productID"));
					VO.setProdName(rs.getString("prodName"));
					VO.setProdPrice(rs.getInt("prodPrice"));
				}
			}
			return VO;
		}
	}

	@Override
	public List<productVO> prodListed() throws Exception {
		List<productVO> list = new ArrayList<productVO>();
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(ProductSQL.GET_Listed);) {
			
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					productVO productVO = new productVO();
					productVO.setProductID(rs.getInt("productID"));
					productVO.setProdName(rs.getString("prodName"));
					productVO.setProdTypeID(rs.getInt("prodTypeID"));
					productVO.setProdPrice(rs.getInt("prodPrice"));
					productVO.setDiscountID(rs.getInt("discountID"));
					productVO.setProdDescription(rs.getString("prodDescription"));
					productVO.setProdStatus(rs.getInt("prodStatus"));
					productVO.setSellQuantity(rs.getInt("sellQuantity"));
					productVO.setProdInStock(rs.getInt("prodInStock"));
					list.add(productVO); // Store the row in the list
				}
			}
			return list;
		}
	}
	
	





}
