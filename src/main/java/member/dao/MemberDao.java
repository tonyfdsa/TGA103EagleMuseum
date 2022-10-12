package member.dao;

import java.util.List;
import member.vo.Member;

//建立介面
public interface MemberDao {
	// 登入
	Member selectForLogin(String email, String password);
	// 管理員登入
//	Member selectLoginManage(String email, String password);
	// 註冊
	Integer insert(Member member);
	// 忘記密碼
	Member selectForPass(String memberEmail, String memberAns);
	// 管理員 刪除
	Integer delete(Integer memberId);
	// 修改
	Integer update(Member member);
	// 管理員修改
	Integer updateManage(Member member);
	// 帳號查詢
	Member selectByMemberEmail(String memberEmail);
	// 管理員簡易全員查詢
	List<Member> serchAllMember();
	// 管理員 查詢全部
	List<Member> selectAll();

}