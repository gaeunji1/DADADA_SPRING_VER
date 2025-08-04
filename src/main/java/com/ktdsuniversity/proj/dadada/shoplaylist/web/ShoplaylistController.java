package com.ktdsuniversity.proj.dadada.shoplaylist.web;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ktdsuniversity.proj.dadada.member.vo.MemberVO;
import com.ktdsuniversity.proj.dadada.shoplaylist.service.ShoplaylistService;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.CreatePackageRequestVO;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.DeleteProductRequestVO;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.PackageSearchVO;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.ShoplaylistProductVO;
import com.ktdsuniversity.proj.dadada.shoplaylist.vo.ShoplaylistVO;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ShoplaylistController {
	
	@Autowired
	private ShoplaylistService shoplaylistService;

	public ShoplaylistController(ShoplaylistService shoplaylistService) {
		this.shoplaylistService = shoplaylistService;
	}

	@GetMapping("/shoplaylist/list")
	public String viewPackageList(
			@RequestParam( defaultValue = "0") int userPageNo,
			@RequestParam(defaultValue = "10") int userListSize,
			@RequestParam(defaultValue = "0") int adminPageNo,
			@RequestParam( defaultValue = "10") int adminListSize,
			@RequestParam(required = false) String searchKeyword,
			@RequestParam(defaultValue = "userTab") String activeTab,
			@SessionAttribute(name = "loginUser", required = false) MemberVO loginUser,
			

			Model model) {
		
		// user용 패키지 페이징
		PackageSearchVO userSearchVO = new PackageSearchVO();
		userSearchVO.setPageNo(userPageNo);
		userSearchVO.setListSize(userListSize);
		userSearchVO.setCreatedBy("user");
	    if ("userTab".equals(activeTab)) {
	        userSearchVO.setSearchKeyword(searchKeyword);
	    }
	    int userTotalCount = shoplaylistService.getPackageCount(userSearchVO);
		userSearchVO.setPageCount(userTotalCount);
		List<ShoplaylistVO> userPackages = shoplaylistService.getPackagesBySearch(userSearchVO);
		
		int userTotalPages = (userTotalCount + userListSize - 1) / userListSize;
			model.addAttribute("userTotalPages", userTotalPages);

	
		// admin용 패키지 페이징
		PackageSearchVO adminSearchVO = new PackageSearchVO();
		adminSearchVO.setPageNo(adminPageNo);
		adminSearchVO.setListSize(adminListSize);
		adminSearchVO.setCreatedBy("admin");
		if ("adminTab".equals(activeTab)) {
	    	adminSearchVO.setSearchKeyword(searchKeyword);
	    }
		int adminTotalCount = shoplaylistService.getPackageCount(adminSearchVO);
		adminSearchVO.setPageCount(adminTotalCount);
		List<ShoplaylistVO> adminPackages = shoplaylistService.getPackagesBySearch(adminSearchVO);
		
		List<Integer> recommendedPkgIds = Collections.emptyList();
	    if (loginUser != null) {
	        recommendedPkgIds = shoplaylistService
	            .getRecommendedPackageIdsByMember(loginUser.getMbrId());
	    }
	    // List→Map 으로 변환 (키: PCKG_ID, 값: true)
	    Map<Integer,Boolean> recommendedMap = recommendedPkgIds.stream()
	        .collect(Collectors.toMap(id -> id, id -> Boolean.TRUE));
	    model.addAttribute("recommendedMap", recommendedMap);

	
		model.addAttribute("userPackages", userPackages);
		model.addAttribute("adminPackages", adminPackages);
		model.addAttribute("userPagination", userSearchVO);
		model.addAttribute("adminPagination", adminSearchVO);
		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("activeTab", activeTab);


		return "shoplaylist/packagelist";
	}
	
	// 사용자용 패키지 목록
	@GetMapping("/shoplaylist/list/user")
	public String listUserPackage(Model model) {
		List<ShoplaylistVO> packages = shoplaylistService.getPackagesByCreator("user");
		model.addAttribute("packageList", packages);
		return "shoplaylist/packagelist";
	}
	
	// 관리자용 패키지 목록
	@GetMapping("/shoplaylist/list/admin")
	public String listAdminPackage(Model model) {
		List<ShoplaylistVO> packages = shoplaylistService.getPackagesByCreator("admin");
		model.addAttribute("packageList", packages);
		return "shoplaylist/packagelist";
	}
	
	// 패키지 등록 페이지
	@GetMapping("/shoplaylist/write")
	public String viewPackageWritePage(Model model) {
		model.addAttribute("shoplaylist", new ShoplaylistVO());
		
		List<ShoplaylistProductVO> shoplaylistProductVO = shoplaylistService.getAllProducts();
		model.addAttribute("productList", shoplaylistProductVO);
		
		return "shoplaylist/packagewrite";
	}
	
	@PostMapping("/shoplaylist/write")
	public String doPackageWrite(
			@Valid
			@ModelAttribute
			ShoplaylistVO shoplaylistVO,
			BindingResult bindingResult,
			@RequestParam List<Integer> productIds,
			HttpSession session,
			Model model) 
	{
		
		MemberVO memberVO = (MemberVO) session.getAttribute("loginUser");
		if (memberVO == null) {
			return "redirect:/member/login";
		}
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("productIds", productIds);
			return "shoplaylist/packagewrite";
		}
		
		shoplaylistVO.setMemId(memberVO.getMbrId());
		shoplaylistVO.setCreatorType("user");

		CreatePackageRequestVO requestVO = new CreatePackageRequestVO();
		requestVO.setShopplaylist(shoplaylistVO);
		requestVO.setProductIds(productIds);

		boolean isCreated = shoplaylistService.createPackageWithProducts(requestVO);

		return isCreated ? "redirect:/shoplaylist/list" : "shoplaylist/packagewrite";
	}
	
	@PostMapping("/shoplaylist/api/create")
	@ResponseBody
	public ResponseEntity<?> createPackageAjax(
	        @RequestBody CreatePackageRequestVO vo,
	        HttpSession session) {

	    MemberVO user = (MemberVO) session.getAttribute("loginUser");
	    if (user == null) {
	        return ResponseEntity.status(401).build();
	    }

	    ShoplaylistVO shopVo = new ShoplaylistVO();
	    shopVo.setPackageName(vo.getPackageName());
	    shopVo.setMemId(user.getMbrId());
	    shopVo.setCreatorType("user");

	    CreatePackageRequestVO req = new CreatePackageRequestVO();
	    req.setShopplaylist(shopVo);
	    req.setProductIds(vo.getProductId());

	    boolean created = shoplaylistService.createPackageWithProducts(req);
	    if (created) {
	        return ResponseEntity.ok().build();
	    } else {
	        return ResponseEntity.status(500).body("패키지 생성 실패");
	    }
	}
	
	// 패키지 삭제
	@GetMapping("/shoplaylist/delete/{packageId}")
	public String deletePackage(@PathVariable int packageId, HttpSession session, RedirectAttributes redirectAttributes) {
		
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/member/login";
		}
		
		// 패키지 정보 조회
		ShoplaylistVO shoplaylistVO = shoplaylistService.getPackageDetail(packageId);
		
		// 삭제 권한 체크
		boolean isAuthor = loginUser.getMbrId() == shoplaylistVO.getMemId();
		boolean isAdmin = loginUser.getAdminYn() == 1;
		
		if (!isAuthor && !isAdmin) {
			redirectAttributes.addFlashAttribute("errorMessage", "삭제 권한이 없습니다.");
			return "redirect:/shoplaylist/list";
		}
		
		shoplaylistService.deletePackage(packageId);
		return "redirect:/shoplaylist/list";
	}

	// 패키지 내 상품 개별 삭제
	@GetMapping("/shoplaylist/delete/{packageId}/product/{productId}")
	public String deleteProductFromPackage(
			@PathVariable int packageId,
			@PathVariable int productId,
			HttpSession session,
			RedirectAttributes redirectAttributes) 
	{
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/member/login";
		}

		ShoplaylistVO packageVo = shoplaylistService.getPackageDetail(packageId);
		if (packageVo == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "존재하지 않는 패키지입니다.");
			return "redirect:/shoplaylist/list";
		}
		
		boolean isAuthor = loginUser.getMbrId() == packageVo.getMemId();
		boolean isAdmin = loginUser.getAdminYn() == 1;
		
		if (!isAuthor && !isAdmin) {
			redirectAttributes.addFlashAttribute("errorMessage", "삭제 권한이 없습니다.");
			return "redirect:/shoplaylist/view/" + packageId;
		}
		
		DeleteProductRequestVO vo = new DeleteProductRequestVO();
		vo.setPackageId(packageId);
		vo.setProductId(productId);

		shoplaylistService.deleteProductFromPackage(vo);
		return "redirect:/shoplaylist/view/" + packageId;
	}

	// 패키지 상세 보기
	@GetMapping("/shoplaylist/view/{packageId}")
	public String viewPackageDetail(@PathVariable int packageId, Model model, RedirectAttributes redirectAttributes) {
		ShoplaylistVO shoplaylist = shoplaylistService.getPackageDetail(packageId);
		
		if (shoplaylist == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "존재하지 않는 패키지입니다.");
			return "redirect:/shoplaylist/list";
		}
		
		model.addAttribute("shoplaylist", shoplaylist);
		return "shoplaylist/packageview";
	}
	
	@PostMapping("/shoplaylist/recommend/{packageId}")
	@ResponseBody
	public ResponseEntity<?> recommend(@PathVariable int packageId,
	                                   HttpSession session){

	    MemberVO user = (MemberVO) session.getAttribute("loginUser");
	    if (user == null) return ResponseEntity.status(401).build();

	    int result = shoplaylistService.recommend(packageId, user.getMbrId());

	    switch (result) {
	        case -1:  return ResponseEntity.status(404).body("패키지 없음");
	        case -2:  return ResponseEntity.badRequest().body("자기 패키지는 추천 불가");
	        case -3:  return ResponseEntity.badRequest().body("이미 추천했습니다");
	        default:  return ResponseEntity.ok(result);
	    }
	}
	
}
