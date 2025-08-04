package com.ktdsuniversity.proj.dadada.mypage.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.proj.dadada.member.vo.MemberVO;
import com.ktdsuniversity.proj.dadada.mypage.service.MyPageService;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageActivityChatVO;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageActivityRouletteVO;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageOrderDetailVO;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageOrderListVO;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageOrderSearchVO;
import com.ktdsuniversity.proj.dadada.roulette.vo.RouletteSpinVO;
import com.ktdsuniversity.proj.dadada.shipping.ShippingPolicy;
import com.ktdsuniversity.proj.dadada.shoplaylist.service.ShoplaylistService;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.ShoplaylistVO;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/mypage")
public class MyPageController {
	
	@Autowired
	private MyPageService myPageService;
	
	@Autowired
	private ShoplaylistService shoplaylistService;
	
	@Autowired
	private ShippingPolicy shippingPolicy;
	
	// 마이페이지 클릭 시 주문 내역 페이지로 리다이렉트
	@GetMapping("")
	public String redirectToOrderListPage() {
		return "redirect:/mypage/order-list";
	}
	
	// 주문 내역/배송 조회 페이지
	@GetMapping("/order-list")
	public String viewOrderListPage(@SessionAttribute("loginUser") MemberVO memberVO,
									@RequestParam(defaultValue = "0") int pageNo,
									@RequestParam(defaultValue = "10") int listSize,
									Model model) {
		int mbrId = memberVO.getMbrId();
		
		MyPageOrderSearchVO orderSearch = new MyPageOrderSearchVO();
		orderSearch.setMbrId(mbrId);
		orderSearch.setPageNo(pageNo);
		orderSearch.setListSize(listSize);
		
		int totalCount = myPageService.getMyOrderListCount(orderSearch);
		orderSearch.setPageCount(totalCount);
		
		List<MyPageOrderListVO> orderList = myPageService.getMyOrderListByPaging(orderSearch);
		
		for (MyPageOrderListVO order : orderList) {
			List<MyPageOrderDetailVO> details = myPageService.getMyOrderDetails(order.getOrdId());
		    if (!details.isEmpty()) {
		    	MyPageOrderDetailVO first = details.get(0);
		    	order.setSamplePrdId(first.getPrdId());
		    	order.setSamplePrdNm(first.getPrdNm());
		    	order.setSamplePrdImg(first.getPrdImg());
		    	order.setAdditionalProductCount(details.size() - 1);
		    }
			double totalPrice = order.getPrdPrcFinal();
			int shippingFee = shippingPolicy.calcShippingFee(totalPrice);
			order.setShippingFee(shippingFee);
		}
		
		model.addAttribute("pageMenu", "order-list");
		model.addAttribute("orderList", orderList);
		model.addAttribute("pagination", orderSearch);
		
		return "mypage/mypage_order_list";
	}
	
	// 주문 상세 페이지
	@GetMapping("/order-detail/{ordId}")
	public String viewOrderDetailPage(@SessionAttribute("loginUser") MemberVO memberVO,
			@PathVariable("ordId") int ordId, Model model) {

		List<MyPageOrderDetailVO> orderDetails = myPageService.getMyOrderDetails(ordId);
		
		double totalProductAmount = orderDetails.stream()
												.mapToDouble(MyPageOrderDetailVO::getPrdPrcFinal)
												.sum();
		
		double paidAmount = orderDetails.get(0).getPaidAmount();	
		
		double totalDiscountAmount = totalProductAmount - paidAmount;
		totalDiscountAmount = totalDiscountAmount > 0 ? totalDiscountAmount : 0;
		
		int shippingFee =  totalProductAmount >= 50000 ? 0 : shippingPolicy.calcShippingFee(totalProductAmount);
		
		double grandTotal = paidAmount;
		
		model.addAttribute("orderDetails", orderDetails);
		
//		MyPageOrderDetailVO orderDetail = myPageService.getMyOrderDetail(ordId);
		 
//		double totalPrice = orderDetail.getPrdPrcFinal();
//		  
//		int shippingFee = shippingPolicy.calcShippingFee(totalPrice);
		
		model.addAttribute("totalProductAmount", totalProductAmount);
		model.addAttribute("totalDiscountAmount", totalDiscountAmount);
		model.addAttribute("shippingFee", shippingFee);
		model.addAttribute("grandTotal", grandTotal);
		model.addAttribute("paymentMethod", orderDetails.get(0).getMtdMthd());
		model.addAttribute("paidAmount", orderDetails.get(0).getPaidAmount());
		
		model.addAttribute("pageMenu", "order-list");
//		model.addAttribute("orderDetail", orderDetail);
	
		return "mypage/mypage_order_detail";
	}
	
	// 보유 룰렛 경품 내역 페이지
	@GetMapping("/reward")
	public String viewRewardList(HttpSession session, Model model) {
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/member/login?redirectUrl=/mypage/reward";
		}
		
		// 전체 룰렛 기록 가져오기
		List<RouletteSpinVO> fullList = myPageService.getMyRouletteSpins(loginUser.getMbrId());
		
		// 꽝 제외된 리스트
		List<RouletteSpinVO> rewardList = fullList.stream()
												  .filter(spin -> !"꽝".equals(spin.getBenefitName()))
												  .toList();
		
		// 포인트 혜택만 합산
		int totalPoint = fullList.stream()
								 .filter(spin -> spin.getBenefitType() == 1 && spin.getIsClaimed() == 1)
								 .mapToInt(RouletteSpinVO::getBenefitValue)
								 .sum();
		
		model.addAttribute("pageMenu", "reward");
		model.addAttribute("rewardList", rewardList);
		model.addAttribute("totalPoint", totalPoint);
		
		return "mypage/mypage_reward";
	}
	
	// 활동 내역 페이지
	@GetMapping("/activity")
	public String viewActivityPage(@SessionAttribute("loginUser") MemberVO memberVO,
								   @RequestParam(defaultValue = "0") int chatPageNo,
								   @RequestParam(defaultValue = "10") int chatListSize,
								   @RequestParam(defaultValue = "0") int roulettePageNo,
								   @RequestParam(defaultValue = "10") int rouletteListSize,
								   Model model) {
		int mbrId = memberVO.getMbrId();
		
		// 경쟁 참여 내역 조회용
		MyPageActivityChatVO chatSearch = new MyPageActivityChatVO();
		chatSearch.setMbrId(mbrId);
		chatSearch.setPageNo(chatPageNo);
		chatSearch.setListSize(chatListSize);
		int chatCount = myPageService.getMyActivityChatListCount(chatSearch);
		chatSearch.setPageCount(chatCount);
		List<MyPageActivityChatVO> chatList = myPageService.getMyActivityChatList(chatSearch);
		
		// 룰렛 이용 내역 조회용
		MyPageActivityRouletteVO rouletteSearch = new MyPageActivityRouletteVO();
		rouletteSearch.setMbrId(mbrId);
		rouletteSearch.setPageNo(roulettePageNo);
		rouletteSearch.setListSize(rouletteListSize);
		int rouletteCount = myPageService.getMyActivityRouletteListCount(rouletteSearch);
		rouletteSearch.setPageCount(rouletteCount);
		List<MyPageActivityRouletteVO> rouletteList = myPageService.getMyActivityRouletteList(rouletteSearch);
		
		model.addAttribute("pageMenu", "activity");
		model.addAttribute("chatList", chatList);
		model.addAttribute("chatSearch", chatSearch);
		model.addAttribute("chatCount", chatCount);
		model.addAttribute("rouletteList", rouletteList);
		model.addAttribute("rouletteSearch", rouletteSearch);
		model.addAttribute("rouletteCount", rouletteCount);
		
		return "mypage/mypage_activity";
	}
	
	// 패키지 관리 페이지
	@GetMapping("/shoplaylist")
	public String viewMyPackageList(@SessionAttribute("loginUser") MemberVO loginUser,
									Model model) {
		List<ShoplaylistVO> myPackages = shoplaylistService.getPackagesByMemberId(loginUser.getMbrId());
		
		model.addAttribute("pageMenu", "shoplaylist");
		model.addAttribute("myPackages", myPackages);
		
		return "mypage/mypage_shoplaylist";
	}
}
