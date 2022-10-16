package member.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import member.dao.MemberDao;
import member.vo.Member;

public class MemberDaoImpl implements MemberDao {
	private DataSource dataSource;

	public MemberDaoImpl() {
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/TGA103eagleMuseum");
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public Member selectForLogin(String email, String password) {
		final String sql = "select * from member where memberEmail = ? and memberPassword = ? " ;
		Member member = null;
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					
					member = new Member();
					member.setMemberID(rs.getInt("memberID"));
					member.setMemberEmail(rs.getString("memberEmail"));
					member.setMemberPassword(rs.getString("memberPassword"));
					member.setMemberName(rs.getString("memberName"));
					member.setMemberQA(rs.getString("memberQA"));
					member.setMemberAns(rs.getString("memberAns"));
					member.setMemberAddress(rs.getString("memberAddress"));
					member.setMemberPhone(rs.getInt("memberPhone"));
					member.setMemberGender(rs.getInt("memberGender"));
					member.setMemberBirthday(rs.getObject("memberBirthday", LocalDate.class));
					member.setMemberPermission(rs.getInt("memberPermission"));
					member.setModifyTime(rs.getObject("modifyTime", LocalDateTime.class));
					member.setLastEnterTime(rs.getObject("lastEnterTime", LocalDateTime.class));
				}		
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return member;
	}
	
//	@Override
//	public Member selectLoginManage(String email, String password) {
//		final String sql = "select * from member where memberEmail = ? and memberPassword = ? " ;
//		Member member = null;
//		try (
//				Connection conn = dataSource.getConnection();
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				) {
//			pstmt.setString(1, email);
//			pstmt.setString(2, password);
//			try(ResultSet rs = pstmt.executeQuery()){
//				if (rs.next()) {
//					
//					member = new Member();
//					member.setMemberID(rs.getInt("memberID"));
//					member.setMemberEmail(rs.getString("memberEmail"));
//					member.setMemberPassword(rs.getString("memberPassword"));
//					member.setMemberName(rs.getString("memberName"));
//					member.setMemberQA(rs.getString("memberQA"));
//					member.setMemberAns(rs.getString("memberAns"));
//					member.setMemberAddress(rs.getString("memberAddress"));
//					member.setMemberPhone(rs.getInt("memberPhone"));
//					member.setMemberGender(rs.getInt("memberGender"));
//					member.setMemberBirthday(rs.getDate("memberBirthday"));
//					member.setMemberPermission(rs.getInt("memberPermission"));
//					member.setModifyTime(rs.getTimestamp("modifyTime"));
//					member.setLastEnterTime(rs.getTimestamp("lastEnterTime"));
//				}		
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		
//		return member;
//	}
	
	@Override
	public Integer insert(Member member) {
		final String sql = "insert into member(memberEmail,memberPassword,memberName,memberQA,memberAns,memberAddress,memberPhone,memberGender,memberBirthday,memberPermission,modifyTime,lastEnterTime) VALUES (?,?,?,?,?,?,?,?,?,1,now(),now())";
		try (
			Connection conn = dataSource.getConnection();
				
			PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			pstmt.setString(1, member.getMemberEmail());
			pstmt.setString(2, member.getMemberPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberQA());
			pstmt.setString(5, member.getMemberAns());
			pstmt.setString(6, member.getMemberAddress());
			pstmt.setInt(7, member.getMemberPhone());
			pstmt.setInt(8, member.getMemberGender());
			pstmt.setObject(9, member.getMemberBirthday());
			pstmt.executeUpdate();
			
			return 1;	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	// 忘記密碼
	@Override
	public Member selectForPass(String memberEmail, String memberAns) {
		final String sql = "select * from member where memberEmail = ? and memberAns = ? " ;
		Member member = null;
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
			pstmt.setString(1, memberEmail);
			pstmt.setString(2, memberAns);
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					
					member = new Member();
					member.setMemberID(rs.getInt("memberID"));
					member.setMemberEmail(rs.getString("memberEmail"));
					member.setMemberPassword(rs.getString("memberPassword"));
					member.setMemberName(rs.getString("memberName"));
					member.setMemberQA(rs.getString("memberQA"));
					member.setMemberAns(rs.getString("memberAns"));
					member.setMemberAddress(rs.getString("memberAddress"));
					member.setMemberPhone(rs.getInt("memberPhone"));
					member.setMemberGender(rs.getInt("memberGender"));
					member.setMemberBirthday(rs.getObject("memberBirthday", LocalDate.class));
					member.setMemberPermission(rs.getInt("memberPermission"));
					member.setModifyTime(rs.getObject("modifyTime", LocalDateTime.class));
					member.setLastEnterTime(rs.getObject("lastEnterTime", LocalDateTime.class));
				}		
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return member;
	}
	
	@Override
	public Integer delete(Integer memberId) {
		final String sql = "DELETE FROM member where memberID = ?";
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			){
			pstmt.setInt(1, memberId);
			pstmt.executeUpdate();
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean update(Member member) {
		final String sql = "update member set memberEmail = ?,memberPassword = ?,memberName = ?,memberQA = ?,memberAns = ?,memberAddress = ?,memberPhone = ?,memberGender = ?,memberBirthday = ? where memberEmail=?;";
		int rowCount = 0;
		try (
			Connection conn = dataSource.getConnection();
				
			PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			pstmt.setString(1, member.getMemberEmail());
			pstmt.setString(2, member.getMemberPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberQA());
			pstmt.setString(5, member.getMemberAns());
			pstmt.setString(6, member.getMemberAddress());
			pstmt.setInt(7, member.getMemberPhone());
			pstmt.setInt(8, member.getMemberGender());
			pstmt.setObject(9, member.getMemberBirthday());
//			pstmt.setObject(10, member.getModifyTime());
//			pstmt.setObject(11, member.getLastEnterTime());
			pstmt.setString(10, member.getMemberEmail());
			rowCount =pstmt.executeUpdate();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount != 0 ;
	}
	
	@Override
	public Integer updateManage(Member member) {
		final String sql = "update member set memberEmail = ?,memberPassword = ?,memberName = ?,memberQA = ?,memberAns = ?,memberAddress = ?,memberPhone = ?,memberGender = ?,memberBirthday = ?,memberPermission = ?,modifyTime = now(),lastEnterTime =? where memberEmail=?;";
		try (
			Connection conn = dataSource.getConnection();
				
			PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			pstmt.setString(1, member.getMemberEmail());
			pstmt.setString(2, member.getMemberPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberQA());
			pstmt.setString(5, member.getMemberAns());
			pstmt.setString(6, member.getMemberAddress());
			pstmt.setInt(7, member.getMemberPhone());
			pstmt.setInt(8, member.getMemberGender());
			pstmt.setObject(9, member.getMemberBirthday());
			pstmt.setInt(10, member.getMemberPermission());
//			pstmt.setTimestamp(11, member.getModifyTime());
			pstmt.setObject(11, member.getLastEnterTime());
			pstmt.executeUpdate();
			
			return 1;	
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
	}
	
	
	@Override
	public Member selectByMemberEmail(String memberEmail) {
		final String sql = "select * from member where memberEmail = ?";
		Member member = null;
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			pstmt.setString(1, memberEmail);
			try(ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					
					member = new Member();
					member.setMemberID(rs.getInt("memberID"));
					member.setMemberEmail(rs.getString("memberEmail"));
					member.setMemberPassword(rs.getString("memberPassword"));
					member.setMemberName(rs.getString("memberName"));
					member.setMemberQA(rs.getString("memberQA"));
					member.setMemberAns(rs.getString("memberAns"));
					member.setMemberAddress(rs.getString("memberAddress"));
					member.setMemberPhone(rs.getInt("memberPhone"));
					member.setMemberGender(rs.getInt("memberGender"));
					member.setMemberBirthday(rs.getObject("memberBirthday", LocalDate.class));
					member.setMemberPermission(rs.getInt("memberPermission"));
					member.setModifyTime(rs.getObject("modifyTime", LocalDateTime.class));
					member.setLastEnterTime(rs.getObject("lastEnterTime", LocalDateTime.class));
				}		
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return member;
	}
	
	@Override
	public List<Member> serchAllMember() {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select memberID,memberEmail,memberPassword,memberName,memberPhone,memberGender from member ;");
				ResultSet rs = pstmt.executeQuery()) {
			List<Member> list = new ArrayList<>();
			while (rs.next()) {
				Member member = new Member();
				member.setMemberID(rs.getInt("memberID"));
				member.setMemberEmail(rs.getString("memberEmail"));
				member.setMemberPassword(rs.getString("memberPassword"));
				member.setMemberName(rs.getString("memberName"));
				member.setMemberPhone(rs.getInt("memberPhone"));
				list.add(member);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Member> selectAll() {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select * from member order by memberID;");
				ResultSet rs = pstmt.executeQuery()) {
			List<Member> list = new ArrayList<>();
			while (rs.next()) {
				Member member = new Member();
				member.setMemberID(rs.getInt("memberID"));
				member.setMemberEmail(rs.getString("memberEmail"));
				member.setMemberPassword(rs.getString("memberPassword"));
				member.setMemberName(rs.getString("memberName"));
				member.setMemberQA(rs.getString("memberQA"));
				member.setMemberAns(rs.getString("memberAns"));
				member.setMemberAddress(rs.getString("memberAddress"));
				member.setMemberPhone(rs.getInt("memberPhone"));
				member.setMemberGender(rs.getInt("memberGender"));
				member.setMemberBirthday(rs.getObject("memberBirthday", LocalDate.class));
				member.setMemberPermission(rs.getInt("memberPermission"));
				member.setModifyTime(rs.getObject("modifyTime", LocalDateTime.class));
				member.setLastEnterTime(rs.getObject("lastEnterTime", LocalDateTime.class));
				list.add(member);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}



}
