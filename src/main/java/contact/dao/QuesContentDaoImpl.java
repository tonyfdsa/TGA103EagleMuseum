package contact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import contact.vo.Member;
import contact.vo.QuesContent;
import core.util.HibernateUtil;

public class QuesContentDaoImpl implements QuesContentDao {

	private DataSource dataSource;

	public QuesContentDaoImpl() throws NamingException {
		dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/TGA103eagleMuseum");
	}

	// HibernateOK
	@Override
	public Integer insert(QuesContent questionContent) {
		// Hibernate寫法
		getSession().persist(questionContent);
		return 1;
		// JDBC寫法
//		String sql = "insert questionContent(memberId, questionTypeID, questionContent, answerContent, quesTime) "
//				+ "values(?, ?, ?, ?, now())";
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement pstmt = connection.prepareStatement(sql, new String[] { "questionContentID" });) {
//			// 假裝從session取得memberid
////			int memberId = 3;
//			pstmt.setInt(1, questionContent.getMemberId());
//			pstmt.setInt(2, questionContent.getQuestionTypeID());
//			pstmt.setString(3, questionContent.getQuestionContent());
//			pstmt.setString(4, questionContent.getAnswerContent());
//			pstmt.executeUpdate();
//			try (ResultSet rs = pstmt.getGeneratedKeys()) {
//				if (rs.next()) {
//					Integer id = rs.getInt(1);
//					return id;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
	}

	// HibernateOK
	@Override
	public List<QuesContent> selectAll() {
		// HQL寫法
		List<QuesContent> allQs = getSession().createQuery("FROM QuesContent", QuesContent.class).list();
		return allQs;

		// JDBC寫法
//		String sql = "select * from questionContent";
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement pstmt = connection.prepareStatement(sql);
//				ResultSet rs = pstmt.executeQuery();) {
//			List<QuesContent> list = new ArrayList<>();
//			while (rs.next()) {
//				QuesContent quesCont = new QuesContent();
//				quesCont.setQuestionContentID(rs.getInt("questionContentID"));
//				quesCont.setMemberId(rs.getInt("memberId"));
//				quesCont.setQuestionTypeID(rs.getInt("questionTypeID"));
//				quesCont.setQuestionContent(rs.getString("questionContent"));
//				quesCont.setAnswerContent(rs.getString("answerContent"));
//				quesCont.setAnswered(rs.getBoolean("answered"));
//				quesCont.setQuesTime(rs.getTimestamp("quesTime"));
//				quesCont.setAnswerTime(rs.getTimestamp("answerTime"));
//				list.add(quesCont);
//			}
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
	}

	// HibernateOK
	@Override
	public List<QuesContent> findByMemberId(Integer memberId) {
		// HQL參數化寫法
		List<QuesContent> list = getSession().createQuery(
				"SELECT new contact.vo.QuesContent(questionContentID, memberId, questionTypeID, "
						+ "questionContent, answerContent, answered, quesTime, answerTime) FROM QuesContent WHERE memberId = :memberId",
				QuesContent.class)
				.setParameter("memberId", memberId)
				.list();
		return list;

		// JDBC寫法
//		String sql = "select * from questionContent where memberId=? ";
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement pstmt = connection.prepareStatement(sql);) {
//
//			pstmt.setInt(1, memberId);
//			ResultSet ansrs = pstmt.executeQuery();
//			List<QuesContent> list = new ArrayList<QuesContent>();
//			while (ansrs.next()) {
//				QuesContent quesCont = new QuesContent();
//				quesCont.setQuestionContentID(ansrs.getInt("questionContentID"));
//				quesCont.setMemberId(ansrs.getInt("memberId"));
//				quesCont.setQuestionTypeID(ansrs.getInt("questionTypeID"));
//				quesCont.setQuestionContent(ansrs.getString("questionContent"));
//				quesCont.setAnswerContent(ansrs.getString("answerContent"));
//				quesCont.setAnswered(ansrs.getBoolean("answered"));
//				quesCont.setQuesTime(ansrs.getTimestamp("quesTime"));
//				quesCont.setAnswerTime(ansrs.getTimestamp("answerTime"));
//				list.add(quesCont);
//			}
//			return list;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
	}

	// HibernateOK
	@Override
	public List<QuesContent> findByDate(Date startTime, Date endTime) {
		// HQL參數化寫法
		List<QuesContent> list = getSession()
				.createQuery(
						"SELECT new contact.vo.QuesContent(questionContentID, memberId, questionTypeID, "
								+ "questionContent, answerContent, answered, quesTime, answerTime) FROM "
								+ "QuesContent WHERE date(quesTime) BETWEEN " + " :startTime AND :endTime",
						QuesContent.class)
				.setParameter("startTime", startTime)
				.setParameter("endTime", endTime)
				.list();
		return list;

		// JDBC寫法
//		String sql = "select * from questionContent where date(quesTime) between ? and ? ";
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement pstmt = connection.prepareStatement(sql);) {
//
//			Timestamp qTime = new Timestamp(quesTime.getTime());
//			pstmt.setTimestamp(1, qTime);
//			Timestamp aTime = new Timestamp(answerTime.getTime());
//			pstmt.setTimestamp(2, aTime);
//			ResultSet ansrs = pstmt.executeQuery();
//			List<QuesContent> list = new ArrayList<QuesContent>();
//			while (ansrs.next()) {
//				QuesContent quesCont = new QuesContent();
//				quesCont.setQuestionContentID(ansrs.getInt("questionContentID"));
//				quesCont.setMemberId(ansrs.getInt("memberId"));
//				quesCont.setQuestionTypeID(ansrs.getInt("questionTypeID"));
//				quesCont.setQuestionContent(ansrs.getString("questionContent"));
//				quesCont.setAnswerContent(ansrs.getString("answerContent"));
//				quesCont.setAnswered(ansrs.getBoolean("answered"));
//				quesCont.setQuesTime(ansrs.getTimestamp("quesTime"));
//				quesCont.setAnswerTime(ansrs.getTimestamp("answerTime"));
//				list.add(quesCont);
//			}
//			return list;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
	}

	// HibernateOK
	@Override
	public List<QuesContent> findByIdAndDate(Integer memberId, Date startTime, Date endTime) {

		// HQL參數化寫法
		List<QuesContent> list = getSession()
				.createQuery("SELECT new contact.vo.QuesContent(questionContentID, memberId, questionTypeID, "
						+ "questionContent, answerContent, answered, quesTime, answerTime) FROM "
						+ "QuesContent WHERE memberId = :memberId AND date(quesTime) BETWEEN "
						+ " :startTime AND :endTime", QuesContent.class)
				.setParameter("memberId", memberId)
				.setParameter("startTime", startTime)
				.setParameter("endTime", endTime)
				.list();
		return list;

		// JDBC寫法
//		String sql = "select * from questionContent " + "where memberId = ? and date(quesTime) between ? and ? ";
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement pstmt = connection.prepareStatement(sql);) {
//			pstmt.setInt(1, memberId);
//			Timestamp qTime = new Timestamp(quesTime.getTime());
//			pstmt.setTimestamp(2, qTime);
//			Timestamp aTime = new Timestamp(answerTime.getTime());
//			pstmt.setTimestamp(3, aTime);
//			List<QuesContent> list = new ArrayList<QuesContent>();
//			try (ResultSet ansrs = pstmt.executeQuery()) {
//				while (ansrs.next()) {
//					QuesContent quesCont = new QuesContent();
//					quesCont.setQuestionContentID(ansrs.getInt("questionContentID"));
//					quesCont.setMemberId(ansrs.getInt("memberId"));
//					quesCont.setQuestionTypeID(ansrs.getInt("questionTypeID"));
//					quesCont.setQuestionContent(ansrs.getString("questionContent"));
//					quesCont.setAnswerContent(ansrs.getString("answerContent"));
//					quesCont.setAnswered(ansrs.getBoolean("answered"));
//					quesCont.setQuesTime(ansrs.getTimestamp("quesTime"));
//					quesCont.setAnswerTime(ansrs.getTimestamp("answerTime"));
//					list.add(quesCont);
//				}
//				return list;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
	}

	// Join的SQL語法，不必用Hibernate
	@Override
	public String getMemNameAndMailAndQues(Integer questionContentID) {
		// JDBC寫法
		String sql = "SELECT memberEmail, memberName, questionContent, answerContent FROM "
				+ "	member m join questionContent q on m.memberID = q.memberId " + "	where q.questionContentID = ?;";
		String returnString = "";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setInt(1, questionContentID);
			ResultSet ansrs = pstmt.executeQuery();
			while (ansrs.next()) {
				returnString += ansrs.getString(1);
				returnString += ",";
				returnString += ansrs.getString(2);
				returnString += ",";
				returnString += ansrs.getString(3);
				returnString += ",";
				returnString += ansrs.getString(4);
			}
			return returnString;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnString;
	}

	// HibernateOK
	@Override
	public Member confirmQues(Integer memberId) {
		// Hibernate寫法
		Member member = getSession().load(Member.class, memberId);
		return member;

		// JDBC寫法
//		String sql = "SELECT memberEmail FROM " + "member where memberID = ?;";
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement pstmt = connection.prepareStatement(sql);) {
//			pstmt.setInt(1, memberId);
//			ResultSet rs = pstmt.executeQuery();
//			if (rs.next()) {
//				Member member = new Member();
//				member.setMemberEmail(rs.getString("memberEmail"));
//				return member.getMemberEmail();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
	}

	// HibernateOK
	@Override
	public QuesContent getQAContent(Integer questionContentID) {
		// Hibernate寫法
		QuesContent quesContent = getSession().load(QuesContent.class, questionContentID);
		return quesContent;

		// JDBC寫法
//		String sql = "select questionContent from questionContent where questionContentID = ?;";
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement pstmt = connection.prepareStatement(sql);) {
//			pstmt.setInt(1, questionContentID);
//			ResultSet rs = pstmt.executeQuery();
//			if (rs.next()) {
//				QuesContent quesContent = new QuesContent();
//				quesContent.setQuestionContent(rs.getString("questionContent"));
//				return quesContent.getQuestionContent();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
	}
	
	//HibernateOK
	@Override
	public void updateAns(String answerContent, Integer questionContentID) {
		
		//Hibernate參數化寫法
		getSession().createQuery("UPDATE QuesContent "
		+ "SET answerContent = :answerContent, answered = true, answerTime=now() "
		+ "WHERE questionContentID = :questionContentID")
		.setParameter("answerContent", answerContent)
		.setParameter("questionContentID", questionContentID)
		.executeUpdate();
		
		// JDBC寫法
//		String sql = "update questionContent set answerContent = ?, "
//				+ "answered = true, answerTime=now() where questionContentID = ?";
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement pstmt = connection.prepareStatement(sql);) {
//			pstmt.setString(1, answerContent);
//			pstmt.setInt(2, questionContentID);
//			pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

}// class
