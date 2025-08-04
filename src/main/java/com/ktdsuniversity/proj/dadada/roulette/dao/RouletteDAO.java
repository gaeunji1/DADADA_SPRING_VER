package com.ktdsuniversity.proj.dadada.roulette.dao;

import java.util.List;
import com.ktdsuniversity.proj.dadada.roulette.vo.RouletteBenefitVO;
import com.ktdsuniversity.proj.dadada.roulette.vo.RouletteSpinVO;

public interface RouletteDAO {

    // 모든 룰렛 혜택 리스트 조회
    List<RouletteBenefitVO> selectAllBenefits();

    // 특정 benefitId로 혜택 조회
    RouletteBenefitVO selectBenefitById(int benefitId);

    // 룰렛 스핀 결과 등록
    int insertRouletteSpin(RouletteSpinVO spinVO);

    // 회원별 룰렛 스핀 결과 조회 
    List<RouletteSpinVO> selectSpinsByMemberId(int memId);
    
    List<String> selectAllBenefitNames();
    
    // 혜택 삭제
   // public int deleteUsedBenefit(int spinId);
}