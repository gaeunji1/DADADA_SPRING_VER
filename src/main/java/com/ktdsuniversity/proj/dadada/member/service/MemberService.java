package com.ktdsuniversity.proj.dadada.member.service;

import com.ktdsuniversity.proj.dadada.member.vo.BenefitCountVO;
import com.ktdsuniversity.proj.dadada.member.vo.MemberVO;

public interface MemberService {
    public void insertMember(MemberVO memberVO);
    public MemberVO loginMember(MemberVO memberVO);
    public boolean isDuplicatedId(String mbrInsId);
    
    public MemberVO getMemberById(int mbrId);
    public void updateMember(MemberVO memberVO);
    
    public void deleteMemberById(int mbrId);
	public MemberVO getMemberByInsId(String mbrInsId);
	
	public void addBenefitCount(BenefitCountVO benefitCountVO);
    
}
