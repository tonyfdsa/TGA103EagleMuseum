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
	
	public MemberServiceImpl() {
		dao = new MemberDaoImpl();
	}

//  註冊
	@Override
	public boolean registerMember(Member member) {
		final String memberEmail = member.getMemberEmail();
        //  帳號沒填 和空字串  直接回false,其他檢查也都寫在這,例如格式  交易控制
		if (memberEmail == null || memberEmail.isEmpty()) {
			System.out.println("帳號不可空白");
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
		
//      設定自動生成		
		
		final Integer memberID = dao.insert(member);
		if (memberID == null) {
			System.out.println(11);
			return false;
		}
		
		return true;
	}
	
//  查詢全部
	@Override
	public List<Member> findAllMembers() {
		
		return dao.selectAll();
	}

//  修改
	@Override
	public Member editMember(Member member) {
		
		return null;
	}

//  登入	
	@Override
	public Member loginMember(Member member) {
		final String memberEmail = member.getMemberEmail();
		final String memberPassword = member.getMemberPassword();
		
		if (memberEmail == null) {
			member.setMessage("帳號未輸入");
			member.setSuccessful(false);
			return member;
		}
		
		if (memberPassword == null) {
			member.setMessage("密碼未輸入");
			member.setSuccessful(false);
			return member;
		}
		
		member = dao.selectForLogin(memberEmail, memberPassword);
		if (member == null) {
			member = new Member();
			member.setMessage("帳號或密碼錯誤");
			member.setSuccessful(false);
			return member;
		}
		
		member.setMessage("登入成功");
		member.setSuccessful(true);
		return member;
	}
		

//  帳號搜尋
	@Override
	public Member selectByMember(String memberEmail) {
		
		return null;
	}

//  刪除	
	@Override
	public Member removeMember(Member member) {
		return null;
	}

}
