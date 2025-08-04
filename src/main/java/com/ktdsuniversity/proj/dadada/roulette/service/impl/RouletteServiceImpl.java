package com.ktdsuniversity.proj.dadada.roulette.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.proj.dadada.member.dao.MemberDAO;
import com.ktdsuniversity.proj.dadada.member.vo.BenefitCountVO;

import com.ktdsuniversity.proj.dadada.roulette.dao.RouletteDAO;
import com.ktdsuniversity.proj.dadada.roulette.service.RouletteService;
import com.ktdsuniversity.proj.dadada.roulette.vo.RouletteBenefitVO;
import com.ktdsuniversity.proj.dadada.roulette.vo.RouletteSpinVO;

@Service
public class RouletteServiceImpl implements RouletteService {


    private static final Logger LOGGER = LoggerFactory.getLogger(RouletteServiceImpl.class);

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired

    private final RouletteDAO rouletteDAO;
    
    public RouletteServiceImpl(RouletteDAO rouletteDAO) {
        this.rouletteDAO = rouletteDAO;
    }

    @Override
    public List<RouletteBenefitVO> getAllBenefits() {
        return rouletteDAO.selectAllBenefits();
    }

    @Override
    public RouletteSpinVO spinRoulette(int memId) {
        List<RouletteBenefitVO> benefitList = rouletteDAO.selectAllBenefits();
        if (benefitList == null || benefitList.isEmpty()) {
            throw new IllegalStateException("혜택 목록이 비어 있습니다.");
        }

        RouletteBenefitVO selectedBenefit = selectBenefitByProbability(benefitList);

        RouletteSpinVO spinVO = new RouletteSpinVO();
        spinVO.setSpinId((int) System.currentTimeMillis());
        spinVO.setMemId(memId);
        spinVO.setBenefitId(selectedBenefit.getBenefitId());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        spinVO.setSpinDatetime(sdf.format(new Date()));

        spinVO.setIsClaimed(1); // 자동 수령 처리
        spinVO.setCouponCode("");

        // 복합 객체 설정
        spinVO.setBenefit(selectedBenefit);

        // 직접 접근용 필드도 세팅
        spinVO.setBenefitName(selectedBenefit.getBenefitName());
        spinVO.setBenefitType(selectedBenefit.getBenefitType());
        spinVO.setBenefitValue(selectedBenefit.getBenefitValue().intValue());


        LOGGER.info("[SPIN] benefitName={}, benefitType={}, benefitValue={}", 
                    selectedBenefit.getBenefitName(), 
                    selectedBenefit.getBenefitType(), 
                    selectedBenefit.getBenefitValue());

        rouletteDAO.insertRouletteSpin(spinVO);
        return spinVO;
    }

    private RouletteBenefitVO selectBenefitByProbability(List<RouletteBenefitVO> benefits) {
        BigDecimal total = BigDecimal.ZERO;
        for (RouletteBenefitVO b : benefits) {
            total = total.add(b.getBenefitProbability());
        }
        Random random = new Random();
        BigDecimal randomPoint = new BigDecimal(random.nextDouble()).multiply(total);
        BigDecimal cumulative = BigDecimal.ZERO;
        for (RouletteBenefitVO b : benefits) {
            cumulative = cumulative.add(b.getBenefitProbability());
            if (randomPoint.compareTo(cumulative) <= 0) {
                return b;
            }
        }
        return benefits.get(benefits.size() - 1);
    }

    @Override
    public List<RouletteSpinVO> getSpinsByMemberId(int memberId) {
        return rouletteDAO.selectSpinsByMemberId(memberId);
    }

    
    @Override
    public List<String> getAllBenefitNames() {
      return rouletteDAO.selectAllBenefitNames(); 
    }
    
    @Override
    public List<RouletteSpinVO> getSpinsByMember(int memId) {
        return rouletteDAO.selectSpinsByMemberId(memId);
    }
    


	@Override
	public void increaseBonusCount(int memId) {
		BenefitCountVO benefitCountVO = new BenefitCountVO();
		benefitCountVO.setMbrId(memId);
		benefitCountVO.setCount(1);
		
		memberDAO.addBenefitCount(benefitCountVO);
	}
}

