package com.ktdsuniversity.proj.dadada.roulette.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.proj.dadada.roulette.dao.RouletteDAO;
import com.ktdsuniversity.proj.dadada.roulette.vo.RouletteBenefitVO;
import com.ktdsuniversity.proj.dadada.roulette.vo.RouletteSpinVO;

@Repository
public class RouletteDAOImpl extends SqlSessionDaoSupport implements RouletteDAO {

    private static final String NAME_SPACE = "com.ktdsuniversity.proj.dadada.roulette.dao.impl.RouletteDAOImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @Override
    public List<RouletteBenefitVO> selectAllBenefits() {
        return this.getSqlSession().selectList(NAME_SPACE + "selectAllBenefits");
    }

    @Override
    public int insertRouletteSpin(RouletteSpinVO spinVO) {
        return this.getSqlSession().insert(NAME_SPACE + "insertRouletteSpin", spinVO);
    }

    @Override
    public List<RouletteSpinVO> selectSpinsByMemberId(int memId) {
        return this.getSqlSession().selectList(NAME_SPACE + "selectSpinsByMemberId", memId);
    }

    @Override
    public RouletteBenefitVO selectBenefitById(int benefitId) {
        return this.getSqlSession().selectOne(NAME_SPACE + "selectBenefitById", benefitId);
    }

    @Override
    public List<String> selectAllBenefitNames() {
        return this.getSqlSession().selectList(NAME_SPACE + "selectAllBenefitNames");
    }

    // @Override
    // public int deleteUsedBenefit(int spinId) {
    //     return this.getSqlSession().delete(NAME_SPACE + "deleteUsedBenefit", spinId);
    // }
}