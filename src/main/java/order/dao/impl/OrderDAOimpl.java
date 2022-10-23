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
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.sql.Select;

import core.util.HibernateUtil;
import order.dao.intf.OrderDAOinf;
import order.dao.sql.OrderSQL;
import order.vo.OrderDetailVO;
import order.vo.OrderTagVO;
import order.vo.OrderVO;
import prod.vo.CartVO;
import prod.vo.productVO;


public class OrderDAOimpl implements OrderDAOinf{
	@PersistenceContext
	private Session session = null;
	public Session getSession() {
//		return session;
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
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
		final String sql = "select * from  orderlist where orderStatus =:orderstatus";
		return getSession().createNativeQuery(sql,OrderVO.class)
				.setParameter("orderstatus", orderStatus)
				.getResultList();
//		

		
		
		//JDBC
//		List<OrderVO> list = new ArrayList<OrderVO>();
//		try (Connection con = ds.getConnection();
//				PreparedStatement pstmt = con.prepareStatement(OrderSQL.orderGetByStatus);) {
//			pstmt.setInt(1, orderStatus);
//			try (ResultSet rs = pstmt.executeQuery()) {
//				
//				while (rs.next()) {
//					OrderVO VO = new OrderVO();
//					VO.setOrderID(rs.getInt("orderID"));
//					VO.setMemberId(rs.getInt("memberId"));
//					VO.setOrderAmount(rs.getInt("orderAmount"));
//					VO.setOrderStatus(rs.getInt("orderStatus"));
//					VO.setDeliveryAddress(rs.getString("deliveryAddress"));
//					VO.setFreight(rs.getInt("freight"));
//					VO.setMemo(rs.getString("memo"));
//					VO.setOrderAmount(rs.getInt("orderAmount"));
//					VO.setCreateTime(rs.getDate("createTime"));
//				
//					list.add(VO); // Store the row in the list
//				}
//			}
//			return list;
//		}
	}
	@Override
	public Integer statupdate(Integer orderID, Integer status) throws Exception {
//		Hibernare 
		
		final String hql = "UPDATE OrderVO SET orderStatus = :orderStatus where orderID = :orderID";
		getSession().createQuery(hql)
			.setParameter("orderStatus", status)
			.setParameter("orderID", orderID )
			.executeUpdate();
		return 1;
		
		
		
//		JDBC
//		try (Connection con = ds.getConnection();
//				PreparedStatement pstmt = con.prepareStatement(OrderSQL.updateStatus);) {
//			System.out.println(status);
//			pstmt.setInt(1, status);
//			pstmt.setInt(2, orderID);
//			pstmt.executeUpdate();
//			return 1;
//			}
	}
	@Override
	public Integer insertOrder(Integer mem, String address,Integer amount) throws Exception {
		
//		Hibernate
//		final String hql ="insert into OrderVO values(:memberID, :orderAmount, :orderStatus, :deliveryAddress, :freight, :memo, :createTime)";
//		getSession().createQuery(hql)
//					.setParameter("memberID", mem)
//					.setParameter("orderAmount", amount)
//					.setParameter("orderStatus", 1)
//					.setParameter("deliveryAddress", address)
//					.setParameter("freight", 60)
//					.setParameter("memo", "無")
//					.setParameter("createTime", "Now()")
//					.executeUpdate();

		
		
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
		
//		Hibernate
//		final String hql = "insert into OrderDetailVO values(:productID, :orderID, :prodQuantity, :prodName, :prodPrice)";
//		getSession().createQuery(hql)
//					.setParameter("productID", vo.getProductID())
//					.setParameter("orderID", ID)
//					.setParameter("prodQuantity", vo.getProdCount())
//					.setParameter("prodName", vo.getProdName())
//					.setParameter("prodPrice", vo.getProdPrice())
//					.executeUpdate();
//		return 1;
		

		
//		JDBC
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
		
//		Hibernate
		return getSession().get(productVO.class, ID);
		
		
		
		
//		JDBC
//		productVO vo = new productVO();
//		try(Connection con = ds.getConnection();
//				PreparedStatement pstmt = con.prepareStatement(OrderSQL.prodStGetByID)){
//			pstmt.setInt(1, ID);
//			ResultSet rs = pstmt.executeQuery();
//			if(rs.next()) {
//
//				vo.setProdInStock(rs.getInt(1));
//				vo.setSellQuantity(rs.getInt(2));
//			 }
//		}
//		return vo;
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
		final String sql = "select * from orderlist";
		List<OrderVO> list = getSession().createNativeQuery(sql, OrderVO.class).list();
		return list;
		
		//JDBC
//		List<OrderVO> list = new ArrayList<OrderVO>();
//		try(Connection con = ds.getConnection();
//				PreparedStatement pstmt =  con.prepareStatement(OrderSQL.orderGetAll)){
//				try (ResultSet rs = pstmt.executeQuery()) {
//				
//				while (rs.next()) {
//					OrderVO VO = new OrderVO();
//					VO.setOrderID(rs.getInt("orderID"));
//					VO.setMemberId(rs.getInt("memberId"));
//					VO.setOrderAmount(rs.getInt("orderAmount"));
//					VO.setOrderStatus(rs.getInt("orderStatus"));
//					VO.setDeliveryAddress(rs.getString("deliveryAddress"));
//					VO.setFreight(rs.getInt("freight"));
//					VO.setMemo(rs.getString("memo"));
//					VO.setOrderAmount(rs.getInt("orderAmount"));
//					VO.setCreateTime(rs.getDate("createTime"));
//				
//					list.add(VO); // Store the row in the list
//					}
//				}
//		return list;
//		}

	}
	@Override
	public List<OrderTagVO> orderTagGetAll() throws Exception {
		final String sql = "select * from orderstatus"; 
		List<OrderTagVO> list =  getSession().createNativeQuery(sql,OrderTagVO.class).list();
		return list;
		//JDBC
//		List<OrderTagVO> list = new ArrayList<OrderTagVO>();
//		try(Connection con = ds.getConnection();
//				PreparedStatement pstmt = con.prepareStatement(OrderSQL.orderTagGetAll)){
//			try (ResultSet rs = pstmt.executeQuery()){
//				while (rs.next()) {
//					OrderTagVO vo = new OrderTagVO();
//					vo.setOrderStatusID(rs.getInt("orderStatusID"));
//					vo.setOrderStatus(rs.getString("orderStatus"));
//					list.add(vo);
//				}
//			}
//			return list;
//		}

	}
	@Override
	public List<OrderDetailVO> getOrderDetailByID(Integer orderID) throws Exception {
//		Hibernate
		return getSession().createQuery("from OrderDetailVO where orderID = :orderID")
					.setParameter("orderID", orderID)
					.getResultList();
		
//		JDBC
//		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
//		try(Connection con = ds.getConnection();
//				PreparedStatement pstmt = con.prepareStatement(OrderSQL.getOrderDetailByID)){
//			pstmt.setInt(1, orderID);
//			try (ResultSet rs = pstmt.executeQuery()){
//				while (rs.next()) {
//					OrderDetailVO vo = new OrderDetailVO();
//					vo.setProductID(rs.getInt("productID"));
//					vo.setProdQuantity(rs.getInt("prodQuantity"));
//					vo.setProdPrice(rs.getInt("prodPrice"));
//					vo.setProdName(rs.getString("prodName"));
//					list.add(vo);
//				}
//			}
//			return list;
//		}
		
	}
	@Override
	public List<OrderVO> getByMem(Integer memID) throws Exception {
		
//		Hibernate
		return getSession().createQuery("from OrderVO where memberId = :memberId")
					.setParameter("memberId", memID)
					.getResultList();
		
		
//		JDBC
//		List<OrderVO> list = new ArrayList<OrderVO>();
//		try(Connection con = ds.getConnection();
//				PreparedStatement pstmt = con.prepareStatement(OrderSQL.getByMem)){
//			pstmt.setInt(1, memID);
//			try(ResultSet rs = pstmt.executeQuery()){
//				while (rs.next()) {
//					OrderVO VO = new OrderVO();
//					VO.setOrderID(rs.getInt("orderID"));
//					VO.setMemberId(rs.getInt("memberId"));
//					VO.setOrderAmount(rs.getInt("orderAmount"));
//					VO.setOrderStatus(rs.getInt("orderStatus"));
//					VO.setDeliveryAddress(rs.getString("deliveryAddress"));
//					VO.setFreight(rs.getInt("freight"));
//					VO.setMemo(rs.getString("memo"));
//					VO.setOrderAmount(rs.getInt("orderAmount"));
//					VO.setCreateTime(rs.getDate("createTime"));
//				
//					list.add(VO); // Store the row in the list
//					}
//			}
//			return list;
//		}
	}
}
