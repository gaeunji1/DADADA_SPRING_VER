package com.ktdsuniversity.proj.dadada.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.proj.dadada.beans.Sha;  // SHA 컴포넌트 경로에 맞게 조정
import com.ktdsuniversity.proj.dadada.member.dao.MemberDAO;
import com.ktdsuniversity.proj.dadada.member.service.MemberService;
import com.ktdsuniversity.proj.dadada.member.vo.BenefitCountVO;
import com.ktdsuniversity.proj.dadada.member.vo.MemberVO;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;
    
    @Autowired
    private Sha sha; // Base64 방식으로 salt 생성 및 해시 처리 사용
    


    
    public void insertMember(MemberVO memberVO) {
        System.out.println("Service.insertMember 호출됨");
        System.out.println("memberDAO 객체: " + memberDAO); // null 여부 확인
        String rawPassword = memberVO.getMbrPwd();
        String salt = sha.generateSalt();  
        String hashedPwd = sha.getEncrypt(rawPassword, salt);
        memberVO.setMbrPwd(hashedPwd);
        memberVO.setSalt(salt);
        memberDAO.insertMember(memberVO);
    }

    @Override
    public MemberVO loginMember(MemberVO memberVO) {
        MemberVO dbMember = memberDAO.findMemberByMbrInsId(memberVO.getMbrInsId());
        if (dbMember != null) {
            String storedSalt = dbMember.getSalt();  
            if (storedSalt == null) {
                throw new IllegalStateException("No salt stored for user: " + dbMember.getMbrInsId());
            }
            String hashedInputPwd = sha.getEncrypt(memberVO.getMbrPwd(), storedSalt);
            
            if (hashedInputPwd.equals(dbMember.getMbrPwd())) {
                return dbMember;
            }
        }
        return null;
    }
    
    
    @Override
    public boolean isDuplicatedId(String mbrInsId) {
        return memberDAO.countById(mbrInsId) > 0;
    }
    
    @Override
    public MemberVO getMemberById(int mbrId) {
        return memberDAO.selectMemberById(mbrId);
    }

    @Override
    public void updateMember(MemberVO memberVO) {
        memberDAO.updateMember(memberVO);
    }
    
    @Override
    public void deleteMemberById(int mbrId) {
        memberDAO.deleteMemberById(mbrId);
    }
    @Override
    public MemberVO getMemberByInsId(String mbrInsId) {
    	return memberDAO.getMemberByInsId(mbrInsId);
    }

	@Override
	public void addBenefitCount(BenefitCountVO benefitCountVO) {
		memberDAO.addBenefitCount(benefitCountVO);
	}
    
}