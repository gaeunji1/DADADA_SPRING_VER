package com.ktdsuniversity.proj.dadada.member.dao;

import com.ktdsuniversity.proj.dadada.member.vo.BenefitCountVO;
import com.ktdsuniversity.proj.dadada.member.vo.MemberVO;

public interface MemberDAO {
    public void insertMember(MemberVO memberVO);
    public MemberVO loginMember(MemberVO memberVO);
    public MemberVO findMemberByMbrInsId(String mbrInsId);
    public int countById(String mbrInsId);
    public static int getBnftCntBy(int mbrInsId) {
		return 0;
	}
    int decrementBonusCount(int mbrInsId);
    int getBonusCountBy(int mbrInsId);
    public MemberVO selectMemberById(int mbrId);
    public int updateMember(MemberVO memberVO);
    public void deleteMemberById(int mbrId);
    public MemberVO getMemberByInsId(String mbrInsId);
    public void addBenefitCount(BenefitCountVO benefitCountVO);
    public MemberVO selectMemberForShipping(int mbrId);
    
}