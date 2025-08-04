package com.ktdsuniversity.proj.dadada.member.vo;

/**
 * @TableName MBR
 * @TableComment 회원 관리
 */
public class MemberVO {
	
	/**
	 * @ColumnName MBR_ID
	 * @ColumnType NUMBER(36,0)
	 * @ColumnComment PK - 회원 식별 번호
	 */
	private int mbrId;
	
	/**
	 * @ColumnName MBR_INS_ID
	 * @ColumnType VARCHAR2(20)
	 * @ColumnComment 회원 아이디 (입력받는) (중복X)
	 */
	private String mbrInsId;
	
	/**
	 * @ColumnName MBR_EMAIL
	 * @ColumnType VARCHAR2(100)
	 * @ColumnComment 회원 이메일 (인증O) (중복X)
	 */
	private String mbrEmail;
	
	/**
	 * @ColumnName MBR_PWD
	 * @ColumnType VARCHAR2(20)
	 * @ColumnComment 회원 비밀번호
	 */
	private String mbrPwd;
	
	/**
	 * @ColumnName MBR_NCKNM
	 * @ColumnType VARCHAR2(20)
	 * @ColumnComment 회원 닉네임 (중복O)
	 */
	private String mbrNcknm;
	
	/**
	 * @ColumnName MBR_PSTL_CD
	 * @ColumnType VARCHAR2(20)
	 * @ColumnComment 우편 번호
	 */
	private String mbrPstlCd;
	
	/**
	 * @ColumnName MBR_ADDR1
	 * @ColumnType VARCHAR2(150)
	 * @ColumnComment 집 주소 1 (기본)
	 */
	private String mbrAddr1;
	
	/**
	 * @ColumnName MBR_ADDR2
	 * @ColumnType VARCHAR2(100)
	 * @ColumnComment 집 주소 2 (상세)
	 */
	private String mbrAddr2;
	
	/**
	 * @ColumnName ADMIN_YN
	 * @ColumnType CHAR(1)
	 * @ColumnComment 관리자인지 아닌지 여부 (0/1)
	 */
	private int adminYn;
	
	/**
	 * @ColumnName BNFT_CNT
	 * @ColumnType NUMBER(11,0)
	 * @ColumnComment 베네핏을 얻은 횟수
	 */
	private int bnftCnt;
	
	/**
	 * @ColumnName STACK_DSCNT_RT
	 * @ColumnType NUMBER(4,2)
	 * @ColumnComment 할인받은 누적량
	 */
	private Double stackDscntRt;
	
	/**
	 * @ColumnName SALT
	 * @ColumnType VARCHAR2(64)
	 * @ColumnComment 비밀번호 암호화 처리를 위한 고유 난수(SALT)
	 */
	private String salt;
	
	/*
	 * @ColumnName MBR_PH_NO
	 * @ColumnType VARCHAR2(20)
	 * @ColumnComment 회원 휴대전화번호
	 * */
	private String mbrPhNo;


	public int getMbrId() {
		return this.mbrId;
	}

	public void setMbrId(int mbrId) {
		this.mbrId = mbrId;
	}

	public String getMbrInsId() {
		return this.mbrInsId;
	}

	public void setMbrInsId(String mbrInsId) {
		this.mbrInsId = mbrInsId;
	}

	public String getMbrEmail() {
		return this.mbrEmail;
	}

	public void setMbrEmail(String mbrEmail) {
		this.mbrEmail = mbrEmail;
	}

	public String getMbrPwd() {
		return this.mbrPwd;
	}

	public void setMbrPwd(String mbrPwd) {
		this.mbrPwd = mbrPwd;
	}

	public String getMbrNcknm() {
		return this.mbrNcknm;
	}

	public void setMbrNcknm(String mbrNcknm) {
		this.mbrNcknm = mbrNcknm;
	}

	public String getMbrPstlCd() {
		return this.mbrPstlCd;
	}

	public void setMbrPstlCd(String mbrPstlCd) {
		this.mbrPstlCd = mbrPstlCd;
	}

	public String getMbrAddr1() {
		return this.mbrAddr1;
	}

	public void setMbrAddr1(String mbrAddr1) {
		this.mbrAddr1 = mbrAddr1;
	}

	public String getMbrAddr2() {
		return this.mbrAddr2;
	}

	public void setMbrAddr2(String mbrAddr2) {
		this.mbrAddr2 = mbrAddr2;
	}

	public int getAdminYn() {
		return this.adminYn;
	}

	public void setAdminYn(int adminYn) {
		this.adminYn = adminYn;
	}

	public int getBnftCnt() {
		return this.bnftCnt;
	}

	public void setBnftCnt(int bnftCnt) {
		this.bnftCnt = bnftCnt;
	}

	public Double getStackDscntRt() {
		return this.stackDscntRt;
	}

	public void setStackDscntRt(Double stackDscntRt) {
		this.stackDscntRt = stackDscntRt;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getMbrPhNo() {
		return mbrPhNo;
	}

	public void setMbrPhNo(String mbrPhNo) {
		this.mbrPhNo = mbrPhNo;
	}
	

}
