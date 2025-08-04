package com.ktdsuniversity.proj.dadada.member.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.proj.dadada.member.dao.MemberDAO;
import com.ktdsuniversity.proj.dadada.member.vo.BenefitCountVO;
import com.ktdsuniversity.proj.dadada.member.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

    // ⚠ XML 매퍼 namespace와 반드시 일치시켜야 함
    private static final String NAMESPACE = "com.ktdsuniversity.proj.dadada.member.dao.MemberDAO.";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void insertMember(MemberVO memberVO) {
        int result = sqlSession.insert(NAMESPACE + "insertMember", memberVO);
        System.out.println("Inserted rows: " + result);
    }

    @Override
    public MemberVO loginMember(MemberVO memberVO) {
        return sqlSession.selectOne(NAMESPACE + "findMemberByMbrInsId", memberVO.getMbrInsId());
    }

    @Override
    public int countById(String mbrInsId) {
        return sqlSession.selectOne(NAMESPACE + "countById", mbrInsId);
    }

    @Override
    public MemberVO findMemberByMbrInsId(String mbrInsId) {
        return sqlSession.selectOne(NAMESPACE + "findMemberByMbrInsId", mbrInsId);
    }

    @Override
    public int decrementBonusCount(int mbrInsId) {
        return sqlSession.update(NAMESPACE + "decrementBonusCount", mbrInsId);
    }

    @Override
    public int getBonusCountBy(int mbrInsId) {
        return sqlSession.selectOne(NAMESPACE + "getBonusCountBy", mbrInsId);
    }

    @Override
    public MemberVO selectMemberById(int mbrId) {
        return sqlSession.selectOne(NAMESPACE + "selectMemberById", mbrId);
    }

    
    @Override
    public int updateMember(MemberVO memberVO) {
        return sqlSession.update(NAMESPACE + "updateMember", memberVO);
    }

    @Override
    public void deleteMemberById(int mbrId) {
        sqlSession.delete(NAMESPACE + "deleteMemberById", mbrId);
    }
    
    @Override
    public MemberVO getMemberByInsId(String mbrInsId) {
        return this.sqlSession.selectOne("getMemberByInsId", mbrInsId);
    }

	@Override
	public void addBenefitCount(BenefitCountVO benefitCountVO) {
		sqlSession.update(NAMESPACE + "addBenefitCount", benefitCountVO);
	}
	
	@Override
	public MemberVO selectMemberForShipping(int mbrId) {
		 return sqlSession.selectOne(NAMESPACE + "selectMemberForShipping", mbrId);
	}
}