package com.ktdsuniversity.proj.dadada.cs.vo;

public class InquiryVO {
	
    private int inqryId;          // 문의글 ID
    private int mbrId;            // 회원 ID (long에서 int로 변경)
    private int ordId;
    private String mbrNcknm;      // 회원 닉네임
    private String inqryTtl;      // 제목
    private String inqryCntnt;    // 내용
    private int inqryCtgry;       // 카테고리 (0=일반, 1=교환, 2=환불)
    private int inqryPrvt;        // 공개 여부 (0=공개, 1=비공개)
    private int inqryStat;        // 처리 상태 (0=처리전, 1=처리중, 2=처리완료)
    private String inqryCrtDt;    // 작성일 (Date에서 String으로 변경)
    private String inqryUpdtDt;   // 수정일 (Date에서 String으로 변경)
    private String inqryImgPath;  // 이미지 경로
    private String inqryRply;     // 답변 내용
    private String inqryRplyDt;   // 답변일 (Date에서 String으로 변경)

    // Getter & Setter
    public int getInqryId() {
        return inqryId;
    }
    public void setInqryId(int inqryId) {
        this.inqryId = inqryId;
    }

    public int getMbrId() {
        return mbrId;
    }
    public void setMbrId(int mbrId) {
        this.mbrId = mbrId;
    }

    public int getOrdId() {
		return ordId;
	}
	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}
	public String getMbrNcknm() {
        return mbrNcknm;
    }
    public void setMbrNcknm(String mbrNcknm) {
        this.mbrNcknm = mbrNcknm;
    }
    
    public String getInqryTtl() {
        return inqryTtl;
    }
    public void setInqryTtl(String inqryTtl) {
        this.inqryTtl = inqryTtl;
    }

    public String getInqryCntnt() {
        return inqryCntnt;
    }
    public void setInqryCntnt(String inqryCntnt) {
        this.inqryCntnt = inqryCntnt;
    }

    public int getInqryCtgry() {
        return inqryCtgry;
    }
    public void setInqryCtgry(int inqryCtgry) {
        this.inqryCtgry = inqryCtgry;
    }

    public int getInqryPrvt() {
        return inqryPrvt;
    }
    public void setInqryPrvt(int inqryPrvt) {
        this.inqryPrvt = inqryPrvt;
    }

    public int getInqryStat() {
        return inqryStat;
    }
    public void setInqryStat(int inqryStat) {
        this.inqryStat = inqryStat;
    }

    public String getInqryCrtDt() {
        return inqryCrtDt;
    }
    public void setInqryCrtDt(String inqryCrtDt) {
        this.inqryCrtDt = inqryCrtDt;
    }

    public String getInqryUpdtDt() {
        return inqryUpdtDt;
    }
    public void setInqryUpdtDt(String inqryUpdtDt) {
        this.inqryUpdtDt = inqryUpdtDt;
    }

    public String getInqryImgPath() {
        return inqryImgPath;
    }
    public void setInqryImgPath(String inqryImgPath) {
        this.inqryImgPath = inqryImgPath;
    }

    public String getInqryRply() {
        return inqryRply;
    }
    public void setInqryRply(String inqryRply) {
        this.inqryRply = inqryRply;
    }

    public String getInqryRplyDt() {
        return inqryRplyDt;
    }
    public void setInqryRplyDt(String inqryRplyDt) {
        this.inqryRplyDt = inqryRplyDt;
    }
}
