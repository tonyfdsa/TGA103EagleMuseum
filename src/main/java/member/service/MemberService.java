package member.service;

import java.util.List;

import member.vo.Member;

public interface MemberService {
	
	boolean registerMember(Member member);

	List<Member> findAllMembers();
	
}
