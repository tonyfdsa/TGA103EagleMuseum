package order.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import order.dao.intf.OrderDAOinf;
import order.dao.sql.OrderSQL;
import order.vo.orderVO;
import prod.dao.sql.ProductSQL;
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
	public List<orderVO> statusget(Integer orderStatus) throws Exception {
		List<orderVO> list = new ArrayList<orderVO>();
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(OrderSQL.orderGetByStatus);) {
			pstmt.setInt(1, orderStatus);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					orderVO VO = new orderVO();
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
			pstmt.setInt(1, status);
			pstmt.setInt(2, orderID);
			pstmt.executeUpdate();
			return 1;
			}
	}

}
