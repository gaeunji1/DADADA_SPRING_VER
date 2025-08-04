package com.ktdsuniversity.proj.dadada.roulette.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.proj.dadada.member.dao.MemberDAO;
import com.ktdsuniversity.proj.dadada.member.vo.MemberVO;
import com.ktdsuniversity.proj.dadada.roulette.service.RouletteService;
import com.ktdsuniversity.proj.dadada.roulette.vo.RouletteBenefitVO;
import com.ktdsuniversity.proj.dadada.roulette.vo.RouletteSpinVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/roulette")
public class RouletteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouletteController.class);
    
    private final RouletteService rouletteService;
    
    @Autowired
    private MemberDAO memberDao;

    public RouletteController(RouletteService rouletteService) {
        this.rouletteService = rouletteService;
    }
    
    @GetMapping("/spin")
    public String displaySpinPage(HttpSession session, Model model) {
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/member/login";
        }
        int memId = loginUser.getMbrId();
        int currentBnftCnt = memberDao.getBonusCountBy(memId);
        loginUser.setBnftCnt(currentBnftCnt);
        session.setAttribute("loginUser", loginUser);
        model.addAttribute("loginUser", loginUser);

        List<RouletteBenefitVO> benefitList = rouletteService.getAllBenefits();
        model.addAttribute("benefitList", benefitList);

        return "roulette/rouletteSpin";
    }
    
    @PostMapping(value = "/spin", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> doSpin(HttpSession session) {
        Map<String, Object> resultMap = new HashMap<>();
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        if (loginUser == null) {
            resultMap.put("status", "error");
            resultMap.put("message", "로그인이 필요합니다.");
            return resultMap;
        }
        int memId = loginUser.getMbrId();
        LOGGER.info("룰렛 스핀 요청 memId: {}", memId);

        int currentBnftCnt = memberDao.getBonusCountBy(memId);
        if (currentBnftCnt <= 0) {
            resultMap.put("status", "error");
            resultMap.put("message", "보너스 횟수가 부족하여 룰렛을 돌릴 수 없습니다.");
            return resultMap;
        }

        // 1) 전체 혜택 리스트 조회
        List<RouletteBenefitVO> benefitList = rouletteService.getAllBenefits();

        // 2) 실제 스핀 및 DB 저장
        RouletteSpinVO spinResult = rouletteService.spinRoulette(memId);

        // 3) 서버에서 뽑힌 혜택의 인덱스 계산
        int selectedIndex = 0;
        for (int i = 0; i < benefitList.size(); i++) {
            if (benefitList.get(i).getBenefitId() == spinResult.getBenefitId()) {
                selectedIndex = i;
                break;
            }
        }

        // 4) 보너스 차감 및 갱신
        memberDao.decrementBonusCount(memId);
        int newBnftCnt = memberDao.getBonusCountBy(memId);
        loginUser.setBnftCnt(newBnftCnt);
        session.setAttribute("loginUser", loginUser);

        // 5) spinResult로 모달 HTML 구성
        String spinHtml = "<h2>당첨 결과</h2>";
        if (spinResult.getBenefitName() != null) {
            spinHtml += "<p>혜택: " + spinResult.getBenefitName() + "</p>";
        } else {
            spinHtml += "<p>당첨된 혜택이 없습니다.</p>";
        }

        // 6) 결과 맵에 넣기
        resultMap.put("status", "success");
        resultMap.put("spinHtml", spinHtml);
        resultMap.put("bnftCnt", newBnftCnt);
        resultMap.put("selectedBenefitId", spinResult.getBenefitId());

        return resultMap;
    }
    
    @GetMapping("/history")
    public String showSpinHistory(HttpSession session, Model model) {
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/member/login";
        }
        int memId = loginUser.getMbrId();
        model.addAttribute("history", rouletteService.getSpinsByMember(memId));
        return "rouletteHistory";
    }
    
    @PostMapping("/addChance")
    @ResponseBody
    public Map<String, Object> addChance(HttpSession session) {
    	MemberVO user = (MemberVO) session.getAttribute("loginUser");
    	Map<String, Object> result = new HashMap<>();
    	
    	if (user == null) {
    		result.put("status", "fail");
    		result.put("message", "로그인이 필요합니다.");
    		return result;
    	}
    	
    	rouletteService.increaseBonusCount(user.getMbrId());
    	
    	int updateCount = memberDao.getBonusCountBy(user.getMbrId());
    	
    	user.setBnftCnt(updateCount);
    	session.setAttribute("loginUser", user);
    	
    	result.put("status", "success");
    	result.put("message", "보너스 찬스가 1개 증가했습니다.");
    	result.put("bnftCnt", updateCount);
    	return result;
    }
}
