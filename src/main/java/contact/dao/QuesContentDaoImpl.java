package contact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import contact.vo.Member;
import contact.vo.QuesContent;

public class QuesContentDaoImpl implements QuesContentDao {

	private DataSource dataSource;

	public QuesContentDaoImpl() throws NamingException {
		dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/TGA103eagleMuseum");
	}
	@Override
	public Integer insert(QuesContent questionContent) {
		String sql = "insert questionContent(memberId, questionTypeID, questionContent, answerContent, quesTime) "
				+ "values(?, ?, ?, ?, now())";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql, new String[] { "questionContentID" });) {
			// 假裝從session取得memberid
//			int memberId = 3;
			pstmt.setInt(1, questionContent.getMemberId());
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
				quesCont.setQuesTime(rs.getTimestamp("quesTime"));
				quesCont.setAnswerTime(rs.getTimestamp("answerTime"));			
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
				quesCont.setQuesTime(ansrs.getTimestamp("quesTime"));
				quesCont.setAnswerTime(ansrs.getTimestamp("answerTime"));
				list.add(quesCont);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<QuesContent> findByDate(Timestamp quesTime, Timestamp answerTime) {
		String sql = "select * from questionContent where date(quesTime) between ? and ? ";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setTimestamp(1, quesTime);
			pstmt.setTimestamp(2, answerTime);
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
				quesCont.setQuesTime(ansrs.getTimestamp("quesTime"));
				quesCont.setAnswerTime(ansrs.getTimestamp("answerTime"));
				list.add(quesCont);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<QuesContent> findByIdAndDate(Integer memberId, Timestamp quesTime, Timestamp answerTime) {
		String sql = "select * from questionContent " + "where memberId = ? and date(quesTime) between ? and ? ";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);) {
			pstmt.setInt(1, memberId);
			pstmt.setTimestamp(2, quesTime);
			pstmt.setTimestamp(3, answerTime);
			List<QuesContent> list = new ArrayList<QuesContent>();
			try(ResultSet ansrs = pstmt.executeQuery()){
				while (ansrs.next()) {
					QuesContent quesCont = new QuesContent();
					quesCont.setQuestionContentID(ansrs.getInt("questionContentID"));
					quesCont.setMemberId(ansrs.getInt("memberId"));
					quesCont.setQuestionTypeID(ansrs.getInt("questionTypeID"));
					quesCont.setQuestionContent(ansrs.getString("questionContent"));
					quesCont.setAnswerContent(ansrs.getString("answerContent"));
					quesCont.setAnswered(ansrs.getBoolean("answered"));
					quesCont.setQuesTime(ansrs.getTimestamp("quesTime"));
					quesCont.setAnswerTime(ansrs.getTimestamp("answerTime"));
					list.add(quesCont);
				}					
				return list;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateAns(String answerContent, Integer questionContentID) {
		String sql = "update questionContent set answerContent = ?, answered = true, answerTime=now() where questionContentID = ?";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);){
			pstmt.setString(1, answerContent);
			pstmt.setInt(2, questionContentID);
			pstmt.executeUpdate();			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String getMemNameAndMailAndQues(Integer questionContentID) {
		String sql = "SELECT memberEmail, memberName, questionContent, answerContent FROM "
				+ "	member m join questionContent q on m.memberID = q.memberId "
				+ "	where q.questionContentID = ?;";
		String retrunString = "";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);){
			pstmt.setInt(1, questionContentID);
			ResultSet ansrs = pstmt.executeQuery();
			while (ansrs.next()) {
				retrunString += ansrs.getString(1);
				retrunString += ",";
				retrunString += ansrs.getString(2);
				retrunString += ",";
				retrunString += ansrs.getString(3);
				retrunString += ",";
				retrunString += ansrs.getString(4);
			}
			return retrunString;			
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return retrunString;
	}

	@Override
	public String confirmQues(Integer memberId) {
		String sql = "SELECT memberEmail FROM "
				+ "member where memberID = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);){
			pstmt.setInt(1, memberId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				Member member = new Member();
				member.setMemberEmail(rs.getString("memberEmail"));
				return member.getMemberEmail();	
			}				
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;				
	}

	@Override
	public String getQContent(Integer questionContentID) {
		String sql = "select questionContent from questionContent where questionContentID = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);){
			pstmt.setInt(1, questionContentID);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				QuesContent quesContent = new QuesContent();
				quesContent.setQuestionContent(rs.getString("questionContent"));
				return quesContent.getQuestionContent();	
			}				
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAContent(Integer questionContentID) {
		String sql = "SELECT answerContent FROM questionContent where questionContentID = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);){
			pstmt.setInt(1, questionContentID);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				QuesContent quesContent = new QuesContent();
				quesContent.setAnswerContent(rs.getString("answerContent"));
				return quesContent.getAnswerContent();	
			}				
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}// class
