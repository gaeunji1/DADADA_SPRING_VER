package com.ktdsuniversity.proj.dadada.cs.vo;

import org.springframework.web.multipart.MultipartFile;

public class InquiryWriteRequestVO {
	private int inqryId;
    private int mbrId;
    private int ordId;
    private String inqryTtl;
    private String inqryCntnt;
    private int inqryCtgry;
    private int inqryPrvt;
    private MultipartFile imageFile;
    private String inqryImgPath;
    
    
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
