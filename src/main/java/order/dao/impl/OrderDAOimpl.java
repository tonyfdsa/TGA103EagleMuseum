package order.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import order.dao.intf.OrderDAOinf;
import order.dao.sql.OrderSQL;
import order.vo.OrderDetailVO;
import order.vo.OrderTagVO;
import order.vo.OrderVO;
import prod.vo.CartVO;
import prod.vo.productVO;


public class OrderDAOimpl implements OrderDAOinf{
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
	@Override
	public List<OrderVO> statusget(Integer orderStatus) throws Exception {
		List<OrderVO> list = new ArrayList<OrderVO>();
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(OrderSQL.orderGetByStatus);) {
			pstmt.setInt(1, orderStatus);
			try (ResultSet rs = pstmt.executeQuery()) {
				
				while (rs.next()) {
					OrderVO VO = new OrderVO();
					VO.setOrderID(rs.getInt("orderID"));
					VO.setMemberId(rs.getInt("memberId"));
					VO.setOrderAmount(rs.getInt("orderAmount"));
					VO.setOrderStatus(rs.getInt("orderStatus"));
					VO.setDeliveryAddress(rs.getString("deliveryAddress"));
					VO.setFreight(rs.getInt("freight"));
					VO.setMemo(rs.getString("memo"));
					VO.setOrderAmount(rs.getInt("orderAmount"));
					VO.setCreateTime(rs.getDate("createTime"));
				
					list.add(VO); // Store the row in the list
				}
			}
			return list;
		}
	}
	@Override
	public Integer statupdate(Integer orderID, Integer status) throws Exception {
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(OrderSQL.updateStatus);) {
			System.out.println(status);
			pstmt.setInt(1, status);
			pstmt.setInt(2, orderID);
			pstmt.executeUpdate();
			return 1;
			}
	}
	@Override
	public Integer insertOrder(Integer mem, String address,Integer amount) throws Exception {
		Integer orderID =0;
		try (Connection con = ds.getConnection();
				 PreparedStatement pstmt = con.prepareStatement(OrderSQL.insertOrder,Statement.RETURN_GENERATED_KEYS);){
			 		 
					 pstmt.setInt(1, mem);
					 pstmt.setInt(2, amount);
					 pstmt.setString(3, address);
					 pstmt.executeUpdate(); 
					 ResultSet rs = pstmt.getGeneratedKeys(); 
					 if(rs.next()) {
						orderID = rs.getInt(1);
					 }
					 
					 return orderID;	    
		 }
	}
	@Override
	public Integer insertOrderDetail(CartVO vo, Integer ID) throws Exception {
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(OrderSQL.insertOrderDetail);){
					pstmt.setInt(1, vo.getProductID());
					pstmt.setInt(2, ID);
					pstmt.setInt(3, vo.getProdCount());
					pstmt.setString(4, vo.getProdName());
					pstmt.setInt(5,vo.getProdPrice());
					pstmt.executeUpdate();
					return 1;
		}
		
	}
	@Override
	public productVO prodStGetbyID(Integer ID) throws Exception {
		productVO vo = new productVO();
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(OrderSQL.prodStGetByID)){
			pstmt.setInt(1, ID);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {

				vo.setProdInStock(rs.getInt(1));
				vo.setSellQuantity(rs.getInt(2));
			 }
		}
		return vo;
	}
	@Override
	public Integer prodStUpdate(productVO vo, Integer buy, Integer ID) throws Exception{
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(OrderSQL.prodStUpdate)){
			pstmt.setInt(1, (vo.getProdInStock() - buy));
			pstmt.setInt(2, (vo.getSellQuantity() + buy));
			pstmt.setInt(3, ID);
			pstmt.executeUpdate();
			return 1;
		}
	}
	@Override
	public List<OrderVO> orderGetAll() throws Exception {
		List<OrderVO> list = new ArrayList<OrderVO>();
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt =  con.prepareStatement(OrderSQL.orderGetAll)){
				try (ResultSet rs = pstmt.executeQuery()) {
				
				while (rs.next()) {
					OrderVO VO = new OrderVO();
					VO.setOrderID(rs.getInt("orderID"));
					VO.setMemberId(rs.getInt("memberId"));
					VO.setOrderAmount(rs.getInt("orderAmount"));
					VO.setOrderStatus(rs.getInt("orderStatus"));
					VO.setDeliveryAddress(rs.getString("deliveryAddress"));
					VO.setFreight(rs.getInt("freight"));
					VO.setMemo(rs.getString("memo"));
					VO.setOrderAmount(rs.getInt("orderAmount"));
					VO.setCreateTime(rs.getDate("createTime"));
				
					list.add(VO); // Store the row in the list
					}
				}
		return list;
		}

	}
	@Override
	public List<OrderTagVO> orderTagGetAll() throws Exception {
		List<OrderTagVO> list = new ArrayList<OrderTagVO>();
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(OrderSQL.orderTagGetAll)){
			try (ResultSet rs = pstmt.executeQuery()){
				while (rs.next()) {
					OrderTagVO vo = new OrderTagVO();
					vo.setOrderStatusID(rs.getInt("orderStatusID"));
					vo.setOrderStatus(rs.getString("orderStatus"));
					list.add(vo);
				}
			}
			return list;
		}

	}
	@Override
	public List<OrderDetailVO> getOrderDetailByID(Integer orderID) throws Exception {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(OrderSQL.getOrderDetailByID)){
			pstmt.setInt(1, orderID);
			try (ResultSet rs = pstmt.executeQuery()){
				while (rs.next()) {
					OrderDetailVO vo = new OrderDetailVO();
					vo.setProductID(rs.getInt("productID"));
					vo.setProdQuantity(rs.getInt("prodQuantity"));
					vo.setProdPrice(rs.getInt("prodPrice"));
					vo.setProdName(rs.getString("prodName"));
					list.add(vo);
				}
			}
			return list;
		}
		
	}
	@Override
	public List<OrderVO> getByMem(Integer memID) throws Exception {
		List<OrderVO> list = new ArrayList<OrderVO>();
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(OrderSQL.getByMem)){
			pstmt.setInt(1, memID);
			try(ResultSet rs = pstmt.executeQuery()){
				while (rs.next()) {
					OrderVO VO = new OrderVO();
					VO.setOrderID(rs.getInt("orderID"));
					VO.setMemberId(rs.getInt("memberId"));
					VO.setOrderAmount(rs.getInt("orderAmount"));
					VO.setOrderStatus(rs.getInt("orderStatus"));
					VO.setDeliveryAddress(rs.getString("deliveryAddress"));
					VO.setFreight(rs.getInt("freight"));
					VO.setMemo(rs.getString("memo"));
					VO.setOrderAmount(rs.getInt("orderAmount"));
					VO.setCreateTime(rs.getDate("createTime"));
				
					list.add(VO); // Store the row in the list
					}
			}
			return list;
		}
	}
}
