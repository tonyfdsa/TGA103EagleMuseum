package member.service;

import java.util.List;

import member.vo.Member;

public interface MemberService {
	
//  註冊
	Member registerMember(Member member);
	
//  移除	(後台)
	Member removeMember(Member member);
	
//  編輯修改	
	Member editMember(Member member);
	
//	登入
	Member loginMember (Member member);
	
//	帳號查詢  
	Member selectByMember(String memberEmail);
	
//	
	
//  查詢全部	
	List<Member> findAllMembers();
	
}



