package ticket.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import core.util.HibernateUtil;
import ticket.common.Result;
import ticket.dao.intf.TicketDAOIn;
import ticket.dao.sql.TicketSQL;
import ticket.service.inft.TicketServiceIn;
import ticket.vo.TicketVO;
import ticket.vo.TicketVOo;

public class TicketDAOIm implements TicketDAOIn {
	
	private DataSource ds;

	public TicketDAOIm() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TGA103eagleMuseum");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<TicketVOo> getAll() throws Exception {
		List<TicketVOo> list = new ArrayList<TicketVOo>();
		// try with resources
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(TicketSQL.GET_ALL);) {
			System.out.println("連線成功");
			try (ResultSet rs = pstmt.executeQuery()) {
				TicketVOo vo;
				while (rs.next()) {

					vo = new TicketVOo();
					vo.setTicketID(rs.getInt("ticketID"));
					vo.setMemberID(1);
					vo.setExhibitionID(rs.getInt("exhibitionID"));
					vo.setTicketDate("2022-10-23");
					vo.setAldultTicket(rs.getInt("aldultTicket"));
					vo.setStuTicket(rs.getInt("stuTicket"));
					vo.setOldTicket(rs.getInt("oldTicket"));
					vo.setPhyTicket(rs.getInt("phyTicket"));
					vo.setLastUpdateTime(rs.getString("lastUpdateTime"));
					vo.setTicketStatus(rs.getInt("ticketStatus"));
					vo.setTicketTotal(rs.getInt("ticketTotal"));
					vo.setBuyTime(rs.getString("buyTime"));
					list.add(vo);
				}
			}
		}
		return list;
	}

	@Override
	public List<TicketVOo> getById(Integer ticketID) throws Exception {
		List<TicketVOo> list = new ArrayList<TicketVOo>();
		// try with resources
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(TicketSQL.GET_BY_ID);) {
			System.out.println("連線成功");
			try (ResultSet rs = pstmt.executeQuery()) {
				TicketVOo vo;
				while (rs.next()) {

					vo = new TicketVOo();
					vo.setTicketID(rs.getInt("ticketID"));
					vo.setMemberID(rs.getInt("memberId"));
					vo.setExhibitionID(rs.getInt("exhibitionID"));
					vo.setTicketDate("2022-10-23");
					vo.setAldultTicket(rs.getInt("aldultTicket"));
					vo.setStuTicket(rs.getInt("stuTicket"));
					vo.setOldTicket(rs.getInt("oldTicket"));
					vo.setPhyTicket(rs.getInt("phyTicket"));
					vo.setLastUpdateTime(rs.getString("lastUpdateTime"));
					vo.setTicketStatus(rs.getInt("ticketStatus"));
					vo.setTicketTotal(rs.getInt("ticketTotal"));
					vo.setBuyTime(rs.getString("buyTime"));
					list.add(vo);
				}
			}
		}
		return list;
	}


	@Override
	public Integer insert(TicketVO vo) {
		Transaction tx = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			System.out.println("連線成功11");
			tx = session.beginTransaction();
			final String SQL = "INSERT into ticket(memberId, exhibitionID, ticketDate, aldultTicket, stuTicket, oldTicket, phyTicket, ticketTotal, buyTime, lastUpdateTime) values (?, ?, '2022-10-23',?, ?, ?, ?, ?, NOW(), NOW()) "; 		
			session.createNativeQuery(SQL)
			       .setParameter(1, vo.getMemberId())
			       .setParameter(2, vo.getExhibitionID())
			       .setParameter(3, vo.getAldultTicket())
			       .setParameter(4, vo.getStuTicket())
			       .setParameter(5, vo.getOldTicket())
			       .setParameter(6, vo.getPhyTicket())
			       .setParameter(7, vo.getTicketTotal()).executeUpdate();
			
			tx.commit();
			System.out.println(vo.getTicketID());
			return vo.getTicketID();
		}catch(Exception e) {
			System.out.println("失敗啦幹");
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();			
		}
		return null;
	}

}
