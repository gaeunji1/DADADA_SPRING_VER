package com.ktdsuniversity.proj.dadada.cs.web;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ktdsuniversity.proj.dadada.cs.service.InquiryService;
import com.ktdsuniversity.proj.dadada.cs.vo.InquiryListVO;
import com.ktdsuniversity.proj.dadada.cs.vo.InquiryUpdateRequestVO;
import com.ktdsuniversity.proj.dadada.cs.vo.InquiryVO;
import com.ktdsuniversity.proj.dadada.cs.vo.InquiryWriteRequestVO;
import com.ktdsuniversity.proj.dadada.member.vo.MemberVO;
import com.ktdsuniversity.proj.dadada.mypage.service.MyPageService;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageOrderDetailVO;
import com.ktdsuniversity.proj.dadada.payment.service.PaymentService;
import com.ktdsuniversity.proj.dadada.payment.vo.PaymentVO;

@Controller
@RequestMapping("/cs")
public class CsController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CsController.class);
    
    @Autowired
    private InquiryService inquiryService;
    
    @Autowired
    private MyPageService myPageService;
    
    @Autowired
    private PaymentService paymentService;
    
    @GetMapping("/inquiry")
    public String viewInquiryListPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer category,
            @RequestParam(required = false, defaultValue = "newest") String sort,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String searchType,
            @RequestParam(required = false) Boolean myInquiries,
            @SessionAttribute(value = "loginUser", required = false) MemberVO loginUser,
            Model model) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("category",   category);
        params.put("sort",       sort);
        params.put("keyword",    keyword);
        params.put("searchType", searchType);
        
        if (loginUser != null) {
            params.put("currentUserId", loginUser.getMbrId());
            params.put("onlyPublic",    false);
            if (Boolean.TRUE.equals(myInquiries)) {
                params.put("filterByUserId", loginUser.getMbrId());
            }
        } else {
            params.put("onlyPublic", true);
            myInquiries = false;
        }
        
        InquiryListVO list = inquiryService.getInquiryList(page, size, params);
        model.addAttribute("inquiryList", list);
        model.addAttribute("category",     category);
        model.addAttribute("sort",         sort);
        model.addAttribute("keyword",      keyword);
        model.addAttribute("searchType",   searchType);
        model.addAttribute("myInquiries",  myInquiries);
        
        return "cs/inquiry-list";
    }
    
    @GetMapping("/inquiry/{inquiryId}")
    public String viewInquiryDetailPage(
            @PathVariable int inquiryId,
            @SessionAttribute(value = "loginUser", required = false) MemberVO loginUser,
            Model model,
            RedirectAttributes redirectAttributes) {

        InquiryVO inquiry = inquiryService.selectOneInquiry(inquiryId);
        
        if (inquiry.getInqryPrvt() == 1) {
            boolean isWriter = loginUser != null && loginUser.getMbrId() == inquiry.getMbrId();
            boolean isAdmin = loginUser != null && loginUser.getAdminYn() == 1;
            
            if (!isWriter && !isAdmin) {
                redirectAttributes.addFlashAttribute("errorMessage", "비공개 글은 작성자와 관리자만 열람할 수 있습니다.");
                return "redirect:/cs/inquiry";
            }
        }
        
        model.addAttribute("inquiry", inquiry);

        LOGGER.debug("inquiryId={} 조회 → ordId={}, inqryCtgry={}", 
                     inquiryId, inquiry.getOrdId(), inquiry.getInqryCtgry());

        if ( inquiry.getInqryCtgry() == 2 && inquiry.getOrdId() != 0 ) {
        	PaymentVO paymentInfo = paymentService.getPaymentByOrderId(inquiry.getOrdId());
            model.addAttribute("paymentInfo", paymentInfo);
        }

        return "cs/inquiry-detail";
    }
    
    @GetMapping("/inquiry/write")
    public String viewInquiryWritePage(
            @RequestParam(value = "ordId", required = false, defaultValue="0") int ordId,
            Model model) {
        
        boolean canRefund = true;
        if (ordId != 0) {
            MyPageOrderDetailVO order = myPageService.getMyOrderDetail(ordId);
            model.addAttribute("order", order);
            canRefund = !"competition".equals(order.getOrdDvsn());
        }
        model.addAttribute("canRefund", canRefund);
        model.addAttribute("inquiry", null);
        return "cs/inquiry-form";
    }

    
    @PostMapping("/inquiry/write")
    public String processInquiryWrite(
            @ModelAttribute InquiryWriteRequestVO vo,
            @SessionAttribute("loginUser") MemberVO loginUser,
            RedirectAttributes redirectAttributes) {
        
        LOGGER.info("Processing inquiry write. Title: {}", vo.getInqryTtl());
        vo.setMbrId(loginUser.getMbrId());

        int ordId = vo.getOrdId();
        if (ordId != 0 && vo.getInqryCtgry() == 2) {
            MyPageOrderDetailVO order = myPageService.getMyOrderDetail(ordId);
            if ("competition".equals(order.getOrdDvsn())) {
                redirectAttributes.addFlashAttribute("errorMessage",
                    "경쟁 룸 구매 상품은 환불 문의가 불가합니다.");
                return "redirect:/cs/inquiry/write?ordId=" + ordId;
            }
        }
        
        try {
            boolean ok = inquiryService.insertNewInquiry(vo);
            if (ok) {
                redirectAttributes.addFlashAttribute("successMessage", "문의글이 등록되었습니다.");
                return "redirect:/cs/inquiry";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "문의글 등록에 실패했습니다.");
                return "redirect:/cs/inquiry/write" +
                       (ordId != 0 ? "?ordId=" + ordId : "");
            }
        } catch (IllegalArgumentException e) {
            LOGGER.warn("파일 검증 오류: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/cs/inquiry/write" +
                   (ordId != 0 ? "?ordId=" + ordId : "");
        } catch (Exception e) {
            LOGGER.error("문의글 등록 중 오류 발생: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage",
                "문의글 등록 중 오류가 발생했습니다.");
            return "redirect:/cs/inquiry/write" +
                   (ordId != 0 ? "?ordId=" + ordId : "");
        }
    }
    
    /** 5) 문의 수정 화면 */
    @GetMapping("/inquiry/{inquiryId}/edit")
    public String viewInquiryEditPage(
            @PathVariable int inquiryId,
            @SessionAttribute("loginUser") MemberVO loginUser,
            Model model,
            RedirectAttributes redirectAttributes) {
        
        InquiryVO inquiry = inquiryService.selectOneInquiry(inquiryId);
        if (loginUser.getMbrId() != inquiry.getMbrId()) {
            redirectAttributes.addFlashAttribute("errorMessage", "수정 권한이 없습니다.");
            return "redirect:/cs/inquiry/" + inquiryId;
        }
        model.addAttribute("inquiry", inquiry);
        return "cs/inquiry-form";
    }
    
    @PostMapping("/inquiry/{inquiryId}/edit")
    public String processInquiryEdit(
            @PathVariable int inquiryId,
            @ModelAttribute InquiryUpdateRequestVO vo,
            @SessionAttribute("loginUser") MemberVO loginUser,
            RedirectAttributes redirectAttributes) {
        
        vo.setInqryId(inquiryId);
        try {
            boolean ok = inquiryService.updateOneInquiry(vo);
            if (ok) {
                redirectAttributes.addFlashAttribute("successMessage", "문의글이 수정되었습니다.");
                return "redirect:/cs/inquiry/" + inquiryId;
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "문의글 수정에 실패했습니다.");
                return "redirect:/cs/inquiry/" + inquiryId + "/edit";
            }
        } catch (Exception e) {
            LOGGER.error("문의글 수정 중 오류: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", "문의글 수정 중 오류가 발생했습니다.");
            return "redirect:/cs/inquiry/" + inquiryId + "/edit";
        }
    }

    @PostMapping("/inquiry/{inquiryId}/delete")
    public String processInquiryDelete(
            @PathVariable int inquiryId,
            @SessionAttribute("loginUser") MemberVO loginUser,
            RedirectAttributes redirectAttributes) {
        
        InquiryVO inquiryVO = inquiryService.selectOneInquiry(inquiryId);
        if (loginUser.getMbrId() != inquiryVO.getMbrId() && loginUser.getAdminYn() != 1) {
            redirectAttributes.addFlashAttribute("errorMessage", "문의글 삭제 권한이 없습니다.");
            return "redirect:/cs/inquiry/" + inquiryId;
        }
        boolean isSuccess = inquiryService.deleteOneInquiry(inquiryId);
        if (isSuccess) {
            redirectAttributes.addFlashAttribute("successMessage", "문의글이 삭제되었습니다.");
            return "redirect:/cs/inquiry";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "문의글 삭제에 실패했습니다.");
            return "redirect:/cs/inquiry/" + inquiryId;
        }
    }
    
    @PostMapping("/inquiry/{inquiryId}/reply")
    @ResponseBody
    public Map<String, Object> processInquiryReply(
            @PathVariable int inquiryId,
            @RequestParam(required = false) String inqryRply,
            @SessionAttribute("loginUser") MemberVO loginUser) {
        
        Map<String, Object> response = new HashMap<>();
        if (loginUser.getAdminYn() != 1) {
            response.put("success", false);
            response.put("message", "답변 등록은 관리자만 가능합니다.");
            return response;
        }
        if (inqryRply == null || inqryRply.trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "답변 내용을 입력해주세요.");
            return response;
        }
        InquiryUpdateRequestVO requestVO = new InquiryUpdateRequestVO();
        requestVO.setInqryId(inquiryId);
        requestVO.setInqryRply(inqryRply);
        boolean isSuccess = inquiryService.updateInquiryReply(requestVO);
        response.put("success", isSuccess);
        if (!isSuccess) {
            response.put("message", "답변 등록에 실패했습니다.");
        }
        return response;
    }

    @PostMapping("/inquiry/{inquiryId}/status")
    @ResponseBody
    public Map<String, Object> processInquiryStatusChange(
            @PathVariable int inquiryId,
            @RequestParam int status,
            @SessionAttribute("loginUser") MemberVO loginUser) {
        
        Map<String, Object> response = new HashMap<>();
        if (loginUser.getAdminYn() != 1) {
            response.put("success", false);
            response.put("message", "상태 변경은 관리자만 가능합니다.");
            return response;
        }
        boolean isSuccess = inquiryService.updateInquiryStatus(inquiryId, status);
        response.put("success", isSuccess);
        if (!isSuccess) {
            response.put("message", "상태 변경에 실패했습니다.");
        }
        return response;
    }
    
    @GetMapping("/inquiry/image/{fileName:.+}")
    public ResponseEntity<Resource> serveInquiryImage(@PathVariable String fileName) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            String baseDir;
            if (os.startsWith("windows")) {
                baseDir = "C:\\upload-files";
            } else if (os.startsWith("mac")) {
                baseDir = System.getProperty("user.home") + "/document/upload-files";
            } else {
                baseDir = "/var/local/src/upload-files";
            }
            Path filePath = Paths.get(baseDir).resolve(fileName);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);
                if (contentType == null) contentType = "application/octet-stream";
                return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=\"" + fileName + "\"")
                    .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            LOGGER.error("이미지 서빙 오류", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/faq")
    public String viewFaqPage(@RequestParam(required = false) Integer category,
                              Model model) {
        model.addAttribute("category", category);
        return "cs/faq";
    }
}