package com.ktdsuniversity.proj.dadada.cs.vo;

import org.springframework.web.multipart.MultipartFile;

public class InquiryUpdateRequestVO {
	
    private int inqryId;           // 문의글 ID
    
    private String inqryRply;  // 답변 내용 (inqryRply로 변경)
    
    private String inqryTtl;       // 제목
    
    private String inqryCntnt;     // 내용
    
    private int inqryCtgry;        // 카테고리
    
    private int inqryPrvt;         // 공개 여부
    
    private MultipartFile imageFile;
    
    private String inqryImgPath;   // 이미지 경로 (수정 시 필요)

	public int getInqryId() {
		return inqryId;
	}

	public void setInqryId(int inqryId) {
		this.inqryId = inqryId;
	}
	
	public String getInqryRply() {
		return inqryRply;
	}

	public void setInqryRply(String inqryRply) {
		this.inqryRply = inqryRply;
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

	
	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public String getInqryImgPath() {
		return inqryImgPath;
	}

	public void setInqryImgPath(String inqryImgPath) {
		this.inqryImgPath = inqryImgPath;
	}
    
    
    
    
}