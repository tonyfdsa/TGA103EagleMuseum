package member.service.impl;

import java.sql.Date;
import java.util.List;

import javax.naming.NamingException;

import member.dao.MemberDao;
import member.dao.impl.MemberDaoImpl;
import member.service.MemberService;
import member.vo.Member;

public class MemberServiceImpl implements MemberService {
	private MemberDao dao;
	
	public MemberServiceImpl() throws NamingException {
		dao = new MemberDaoImpl();
	}

	@Override
	public boolean registerMember(Member member) {
		final String memberEmail = member.getMemberEmail();
        //  帳號沒填 和空字串  直接回false,其他檢查也都寫在這,例如格式  交易控制
		if (memberEmail == null || memberEmail.isEmpty()) {
			System.out.println(1);
			return false;
		}
		
		final String memberPassword = member.getMemberPassword();
		if (memberPassword == null || memberPassword.isEmpty()) {
			System.out.println(2);
			return false;
		}
		
		final String memberName = member.getMemberName();
		if (memberName == null || memberName.isEmpty()) {
			System.out.println(3);
			return false;
		}
		
		final String memberQA = member.getMemberQA();
		if (memberQA == null || memberName.isEmpty()) {
			System.out.println(4);
			return false;
		}
		
		final String memberAns = member.getMemberAns();
		if (memberAns == null || memberAns.isEmpty()) {
			System.out.println(5);
			return false;
		}
		
		final String memberAddress = member.getMemberAddress();
		if (memberAddress == null || memberAddress.isEmpty()) {
			System.out.println(6);
			return false;
		}
		
		final Integer memberPhone = member.getMemberPhone();
		if (memberPhone == null ) {
			System.out.println(7);
			return false;
		}
		
		final Integer memberGender = member.getMemberGender();
		if (memberGender == null ) {
			System.out.println(8);
			return false;
		}
		
		final Date memberBirthday = member.getMemberBirthday();
		if (memberBirthday == null ) {
			System.out.println(9);
			return false;
		}
		
		if(dao.selectByMemberEmail(memberEmail) != null) {
			System.out.println(10);
			return false;
		}
		
		final Integer memberID = dao.insert(member);
		if (memberID == null) {
			System.out.println(11);
			return false;
		}
		
		return true;
	}
	

	@Override
	public List<Member> findAllMembers() {
		
		return dao.selectAll();
	}

}
