package member.vo;


import java.sql.Date;
import java.sql.Timestamp;
import core.pojo.CoreSimple;

// member繼承coreSimple  建立sql表格項目
public class Member extends CoreSimple {
	private static final long serialVersionUID = 1L;
	private Integer memberID;
	private String memberEmail;
	private String memberPassword; 
	private String memberName;
	private String memberQA;
	private String memberAns;
	private String memberAddress;
	private Integer memberPhone;
	private Integer memberGender;
	private Date memberBirthday;
	private Integer memberPermission;
	private Timestamp modifyTime;
	private Timestamp lastEnterTime ;
	
	public Member() {
		
	}

	//  建立各項目建構子
	public Member(Integer memberID, String memberEmail, String memberPassword, String memberName, String memberQA,
			String memberAns, String memberAddress, Integer memberPhone, Integer memberGender, Date memberBirthday,
			Integer memberPermission, Timestamp modifyTime, Timestamp lastEnterTime) {
		super();
		this.memberID = memberID;
		this.memberEmail = memberEmail;
		this.memberPassword = memberPassword;
		this.memberName = memberName;
		this.memberQA = memberQA;
		this.memberAns = memberAns;
		this.memberAddress = memberAddress;
		this.memberPhone = memberPhone;
		this.memberGender = memberGender;
		this.memberBirthday = memberBirthday;
		this.memberPermission = memberPermission;
		this.modifyTime = modifyTime;
		this.lastEnterTime = lastEnterTime;
	}

	
	// 建立各項目的 讀取 及  存入
	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberQA() {
		return memberQA;
	}

	public void setMemberQA(String memberQA) {
		this.memberQA = memberQA;
	}

	public String getMemberAns() {
		return memberAns;
	}

	public void setMemberAns(String memberAns) {
		this.memberAns = memberAns;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public Integer getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(Integer memberPhone) {
		this.memberPhone = memberPhone;
	}

	public Integer getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(Integer memberGender) {
		this.memberGender = memberGender;
	}

	public Date getMemberBirthday() {
		return memberBirthday;
	}

	public void setMemberBirthday(Date memberBirthday) {
		this.memberBirthday = memberBirthday;
	}

	public Integer getMemberPermission() {
		return memberPermission;
	}

	public void setMemberPermission(Integer memberPermission) {
		this.memberPermission = memberPermission;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Timestamp getLastEnterTime() {
		return lastEnterTime;
	}

	public void setLastEnterTime(Timestamp lastEnterTime) {
		this.lastEnterTime = lastEnterTime;
	}
	
}
