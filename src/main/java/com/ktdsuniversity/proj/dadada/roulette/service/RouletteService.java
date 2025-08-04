package com.ktdsuniversity.proj.dadada.roulette.service;

import java.util.List;

import com.ktdsuniversity.proj.dadada.roulette.vo.RouletteBenefitVO;
import com.ktdsuniversity.proj.dadada.roulette.vo.RouletteSpinVO;

public interface RouletteService {

	List<RouletteSpinVO> getSpinsByMemberId(int memberId);
    
	RouletteSpinVO spinRoulette(int memId);
    
    List<RouletteSpinVO> getSpinsByMember(int memId);

	List<RouletteBenefitVO> getAllBenefits();
	
	

	List<String> getAllBenefitNames();

	void increaseBonusCount(int memId);
	
}