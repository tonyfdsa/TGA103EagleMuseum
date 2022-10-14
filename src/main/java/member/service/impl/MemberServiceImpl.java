package member.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

import member.dao.MemberDao;
import member.dao.impl.MemberDaoImpl;
import member.service.MemberService;
import member.vo.Member;

public class MemberServiceImpl implements MemberService {
	private MemberDao dao;
	
	public MemberServiceImpl() {
		dao = new MemberDaoImpl();
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

//	管理員登入//
	@Override
	public Member loginManage(Member member) {
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
	
	
//  註冊//
	@Override
	public Member registerMember(Member member) {
		final String memberEmail = member.getMemberEmail();
        //  帳號沒填 和空字串  直接回false,其他檢查也都寫在這,例如格式  交易控制
		if (memberEmail == null || memberEmail.isEmpty()) {
			System.out.println("1");
			member.setMessage("帳號不可空白");
			member.setSuccessful(false);
			return member;
		}
		
		//  信箱核對
		if (dao.selectByMemberEmail(memberEmail) != null) {
			System.out.println("1-1");
			member.setMessage("此信箱已註冊");
			member.setSuccessful(false);
			return member;
		}
		
		String regex = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
		Pattern p = Pattern.compile(regex);
		if (p.matcher(memberEmail).find() == false) {
			System.out.println(p.matcher(memberEmail).find());
			member.setMessage("未符合信箱格式");
			member.setSuccessful(false);
			return member;
		}
		
		final String memberPassword = member.getMemberPassword();
		if (memberPassword == null || memberPassword.isEmpty()) {
			member.setMessage("密碼未輸入");
			member.setSuccessful(false);
			System.out.println(2);
			return member;
		}
		
		final String memberName = member.getMemberName();
		if (memberName == null || memberName.isEmpty()) {
			member.setMessage("姓名未輸入");
			member.setSuccessful(false);
			System.out.println(3);
			return member;
		}
		
		final String memberQA = member.getMemberQA();
		if (memberQA == null || memberName.isEmpty()) {
			member.setMessage("問題未輸入");
			member.setSuccessful(false);
			System.out.println(4);
			return member;
		}
		
		final String memberAns = member.getMemberAns();
		if (memberAns == null || memberAns.isEmpty()) {
			member.setMessage("問題的答案未輸入");
			member.setSuccessful(false);
			System.out.println(5);
			return member;
		}
		
		final String memberAddress = member.getMemberAddress();
		if (memberAddress == null || memberAddress.isEmpty()) {
			member.setMessage("地址未輸入");
			member.setSuccessful(false);
			System.out.println(6);
			return member;
		}
		
		final Integer memberPhone = member.getMemberPhone();
		if (memberPhone == null ) {
			member.setMessage("電話未輸入");
			member.setSuccessful(false);
			System.out.println(7);
			return member;
		}
		
		final Integer memberGender = member.getMemberGender();
		if (memberGender == null ) {
			member.setMessage("性別未選取");
			member.setSuccessful(false);
			System.out.println(8);
			return member;
		}
		
		final LocalDate memberBirthday = member.getMemberBirthday();
		if (memberBirthday == null ) {
			member.setMessage("生日未輸入");
			member.setSuccessful(false);
			System.out.println(9);
			return member;
		}
		

		
//      設定自動生成		
		
		final Integer memberID = dao.insert(member);
		if (memberID == null) {
			System.out.println(11);
			return member;
		}
		
		member.setSuccessful(true);
		return member;
	}
	
//  忘記密碼 ///
	@Override
	public Member forgetpass(Member member) {
		final String email = member.getMemberEmail();
		final String qa = member.getMemberQA();
		final String ans = member.getMemberAns();
		
		if ("".equals(email)) {
			member.setMessage("帳號未輸入");
			member.setSuccessful(false);
			return member;
		}
		if ("".equals(qa)) {
			member.setMessage("問題未輸入");
			member.setSuccessful(false);
			return member;
		}
		if ("".equals(ans)) {
			member.setMessage("答案未輸入");
			member.setSuccessful(false);
			return member;
		}
		
		if (dao.selectForPass(email, ans) == null) {
			member.setSuccessful(false);
			member.setMessage("帳號或信箱錯誤！");
			return member;
		}
		
		
//		// 讓信件可以抓到名字
//		member.setMemberName(dao.selectForPass(email, ans).getMemberName());
//		// JavaMail執行緒
//		JavaMailThread.to = mem.getMemEmail();
//		JavaMailThread.subject = "忘記密碼確認信";
//		JavaMailThread.ch_name = mem.getMemName();
//		VerificationCode code = new VerificationCode();
//		JavaMailThread.passRandom = code.getRandom();
//		mem.setMemVerification(JavaMailThread.passRandom);
//		JavaMailThread.messageText = "Hello! " + JavaMailThread.ch_name + " 您的驗證碼為: " + JavaMailThread.passRandom + "\n" + "(30分鐘後過期)";
//		JavaMailThread jmt = new JavaMailThread();
//		jmt.start();
//		
		member.setSuccessful(true);
		return member;
	}
	
//  刪除	
	@Override
	public boolean removeMember(Integer memberId) {
		return dao.delete(memberId) > 0;
	}

//  修改//
	@Override
	public Member editMember(Member member) {
		
		return null;
	}	
	
//	管理員修改//
	@Override
	public Member manageUpdat(Member member) {
		
		return null;
	}

//  帳號搜尋//
	@Override
	public Member selectByMember(Member member) {
		
		return dao.selectByMemberEmail(member.getMemberEmail());
	}

//	會員查詢
	@Override
	public List<Member> serchAllMember() {
		return dao.serchAllMember();
	}	
	
//  管理員查詢全部
	@Override
	public List<Member> findAllMembers() {
		
		return dao.selectAll();
	}

}
