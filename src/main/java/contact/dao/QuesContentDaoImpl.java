package contact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import contact.vo.QuesContent;

public class QuesContentDaoImpl implements QuesContentDao {

	private DataSource dataSource;

	public QuesContentDaoImpl() throws NamingException {
		dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/TGA103eagleMuseum");
	}

	@Override
	public Integer insert(QuesContent questionContent) {
		String sql = "insert questionContent(memberId, questionTypeID, questionContent, answerContent) "
				+ "values(?, ?, ?, ?)";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql, new String[] { "questionContentID" });) {
			// 假裝從session取得memberid
			int memberId = 1;
//			pstmt.setInt(1, questionContent.getMemberId());
			pstmt.setInt(1, memberId);
			pstmt.setInt(2, questionContent.getQuestionTypeID());
			pstmt.setString(3, questionContent.getQuestionContent());
			pstmt.setString(4, questionContent.getAnswerContent());
			pstmt.executeUpdate();
			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				if (rs.next()) {
					Integer id = rs.getInt(1);
					return id;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<QuesContent> selectAll() {
		String sql = "select * from questionContent";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			List<QuesContent> list = new ArrayList<>();
			while (rs.next()) {
				QuesContent quesCont = new QuesContent();
				quesCont.setQuestionContentID(rs.getInt("questionContentID"));
				quesCont.setMemberId(rs.getInt("memberId"));
				quesCont.setQuestionTypeID(rs.getInt("questionTypeID"));
				quesCont.setQuestionContent(rs.getString("questionContent"));
				quesCont.setAnswerContent(rs.getString("answerContent"));
				quesCont.setAnswered(rs.getBoolean("answered"));
				quesCont.setLastUpdateTime(rs.getTimestamp("lastUpdateTime"));
				list.add(quesCont);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<QuesContent> findByMemberId(Integer memberId) {
		String sql = "select * from questionContent where memberId=? ";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);) {

			pstmt.setInt(1, memberId);
			ResultSet ansrs = pstmt.executeQuery();
			List<QuesContent> list = new ArrayList<QuesContent>();
			while (ansrs.next()) {
				QuesContent quesCont = new QuesContent();
				quesCont.setQuestionContentID(ansrs.getInt("questionContentID"));
				quesCont.setMemberId(ansrs.getInt("memberId"));
				quesCont.setQuestionTypeID(ansrs.getInt("questionTypeID"));
				quesCont.setQuestionContent(ansrs.getString("questionContent"));
				quesCont.setAnswerContent(ansrs.getString("answerContent"));
				quesCont.setAnswered(ansrs.getBoolean("answered"));
				quesCont.setLastUpdateTime(ansrs.getTimestamp("lastUpdateTime"));
				list.add(quesCont);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<QuesContent> findByDate(String lastUpdateDate1, String lastUpdateDate2) {
		String sql = "select * from questionContent where date(lastUpdateTime) between ? and ? ";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setString(1, lastUpdateDate1);
			pstmt.setString(2, lastUpdateDate2);
			ResultSet ansrs = pstmt.executeQuery();
			List<QuesContent> list = new ArrayList<QuesContent>();
			while (ansrs.next()) {
				QuesContent quesCont = new QuesContent();
				quesCont.setQuestionContentID(ansrs.getInt("questionContentID"));
				quesCont.setMemberId(ansrs.getInt("memberId"));
				quesCont.setQuestionTypeID(ansrs.getInt("questionTypeID"));
				quesCont.setQuestionContent(ansrs.getString("questionContent"));
				quesCont.setAnswerContent(ansrs.getString("answerContent"));
				quesCont.setAnswered(ansrs.getBoolean("answered"));
				quesCont.setLastUpdateTime(ansrs.getTimestamp("lastUpdateTime"));
				list.add(quesCont);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<QuesContent> findByIdAndDate(Integer memberId, String lastUpdateDate1, String lastUpdateDate2) {
		String sql = "select * from questionContent " + "where memberId = ? and date(lastUpdateTime) between ? and ? ";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setInt(1, memberId);
			pstmt.setString(2, lastUpdateDate1);
			pstmt.setString(3, lastUpdateDate2);
			ResultSet ansrs = pstmt.executeQuery();
			List<QuesContent> list = new ArrayList<QuesContent>();
			while (ansrs.next()) {
				QuesContent quesCont = new QuesContent();
				quesCont.setQuestionContentID(ansrs.getInt("questionContentID"));
				quesCont.setMemberId(ansrs.getInt("memberId"));
				quesCont.setQuestionTypeID(ansrs.getInt("questionTypeID"));
				quesCont.setQuestionContent(ansrs.getString("questionContent"));
				quesCont.setAnswerContent(ansrs.getString("answerContent"));
				quesCont.setAnswered(ansrs.getBoolean("answered"));
				quesCont.setLastUpdateTime(ansrs.getTimestamp("lastUpdateTime"));
				list.add(quesCont);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void insertAns(QuesContent ansContent) {
		String sql = "insert questionContent(memberId, answerContent, answered) " + "values(?, ?, ?)";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);) {

			// 假裝從session取得memberid
			int memberId = 4;
//			pstmt.setInt(1, questionContent.getMemberId());
			pstmt.setInt(1, memberId);
			pstmt.setString(2, ansContent.getAnswerContent());
			pstmt.setBoolean(3, ansContent.getAnswered());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
