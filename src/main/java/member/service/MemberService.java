package member.service;

import java.util.List;

import member.vo.Member;

public interface MemberService {
	
//	會員登入
	Member loginMember (Member member);
	
//	管理員登入
	Member  loginManage (Member member);
	
//  註冊
	Member registerMember(Member member);
	
//	忘記密碼
	Member forgetpass (Member member);
	
//  移除	(後台)
	boolean removeMember(Integer memberId);
	
//  會員編輯修改	
	Member editMember(Member member);
	
//	管理員編輯修改
	Member manageUpdat(Member member);
	
//	帳號查詢  
	Member selectByMember(Member member);
	
//	會員查詢全部
	List<Member> serchAllMember(); 
	
//  管理員查詢全部	
	List<Member> findAllMembers();
	
}



