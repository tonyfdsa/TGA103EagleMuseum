package member.dao;

import java.util.List;
import member.vo.Member;

//建立介面
public interface MemberDao {
	// 加入
	Integer insert(Member member);
	// 
	Integer update(Member member);
	
	Integer delete(Integer memberId);
	
	Member selectForLogin(String email, String password);
	
	Member selectByMemberEmail(String memberEmail);

	List<Member> selectAll();

}