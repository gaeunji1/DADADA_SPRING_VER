package com.ktdsuniversity.proj.dadada.member.web;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.proj.dadada.beans.Sha;
import com.ktdsuniversity.proj.dadada.common.EmailService;
import com.ktdsuniversity.proj.dadada.common.VerificationUtil;
import com.ktdsuniversity.proj.dadada.member.service.MemberService;
import com.ktdsuniversity.proj.dadada.member.vo.CheckLoginVO;
import com.ktdsuniversity.proj.dadada.member.vo.LoginResultVO;
import com.ktdsuniversity.proj.dadada.member.vo.MemberVO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private Sha sha;

    @GetMapping("/regist")
    public String registForm() {
        return "member/regist";
    }

    @PostMapping("/regist")
    public String regist(MemberVO memberVO, HttpServletRequest request, HttpSession session, Model model) {
        String inputCode = request.getParameter("emailCode");
        String sessionCode = (String) session.getAttribute("authCode");
        String sessionEmail = (String) session.getAttribute("authEmail");

        if (!memberVO.getMbrEmail().equals(sessionEmail) || !inputCode.equals(sessionCode)) {
            model.addAttribute("error", "이메일 인증에 실패했습니다.");
            return "member/regist";
        }

        if (memberService.isDuplicatedId(memberVO.getMbrInsId())) {
            model.addAttribute("error", "이미 사용 중인 아이디입니다.");
            return "member/regist";
        }

        if (memberVO.getMbrPhNo() == null || !memberVO.getMbrPhNo().matches("^010-\\d{4}-\\d{4}$")) {
            model.addAttribute("error", "휴대전화는 010-XXXX-XXXX 형식으로 입력해주세요.");
            return "member/regist";
        }

        String salt = UUID.randomUUID().toString().substring(0, 6);
        memberVO.setSalt(salt);

        if ("admin".equalsIgnoreCase(memberVO.getMbrInsId())) {
            memberVO.setAdminYn(1);
        } else {
            memberVO.setAdminYn(0);
        }

        memberService.insertMember(memberVO);

        return "redirect:/member/login?registSuccess=true";
    }

    @GetMapping("/login")
    public String loginForm(
            @RequestParam(value = "redirect", required = false) String redirect,
            HttpSession session,
            Model model) {

        if (session.getAttribute("loginUser") != null) {
            return "redirect:/main";
        }
        model.addAttribute("redirect", redirect);
        return "member/login";
    }


    @PostMapping("/login")
    public String login(
            HttpSession session,
            HttpServletResponse response,
            MemberVO memberVO,
            @RequestParam(value = "redirect", required = false) String redirect,
            @RequestParam(value = "autoLogin", required = false) String autoLogin,
            Model model) {

        MemberVO loginUser = memberService.loginMember(memberVO);

        if (loginUser != null) {
            session.setAttribute("loginUser", loginUser);
            session.removeAttribute("pwdCheckSuccess");

            if ("on".equals(autoLogin)) {
                Cookie cookie = new Cookie("autoLoginId", loginUser.getMbrInsId());
                cookie.setMaxAge(60 * 60 * 24 * 7);
                cookie.setPath("/");
                response.addCookie(cookie);
            }

            if (redirect != null && !redirect.isEmpty()) {
                return "redirect:" + redirect;
            }
            return (loginUser.getAdminYn() == 1) 
                    ? "redirect:/admin/main" 
                    : "redirect:/main";
        }

        model.addAttribute("error", "아이디 또는 비밀번호를 확인하세요.");
        model.addAttribute("redirect", redirect);
        return "member/login";
    }
    
    @PostMapping("/ajax-login")
    @ResponseBody
    public LoginResultVO ajaxLogin(
            HttpSession session,
            HttpServletResponse response,
            @RequestBody MemberVO memberVO) {

        MemberVO loginUser = memberService.loginMember(memberVO);

        if (loginUser != null) {
            session.setAttribute("loginUser", loginUser);
            session.removeAttribute("pwdCheckSuccess");

            return new LoginResultVO(true, null);
        }

        return new LoginResultVO(false, "아이디 또는 비밀번호가 틀립니다.");
    }




    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        session.invalidate();
        Cookie cookie = new Cookie("autoLoginId", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/main";
    }

    @GetMapping("/check-id")
    @ResponseBody
    public String checkId(@RequestParam String mbrInsId) {
        return memberService.isDuplicatedId(mbrInsId) ? "exist" : "not exist";
    }

    @PostMapping("/email-auth")
    @ResponseBody
    public String sendEmailCode(@RequestParam String email, HttpSession session) {
        String code = UUID.randomUUID().toString().substring(0, 6);
        emailService.sendVerificationEmail(email, code);
        session.setAttribute("authEmail", email);
        session.setAttribute("authCode", code);
        session.setAttribute("authCodeTime", System.currentTimeMillis());
        return "인증 코드가 전송되었습니다.";
    }

    @PostMapping("/verify-code")
    @ResponseBody
    public String verifyCode(@RequestParam("mbrEmail") String email,
                             @RequestParam("emailCode") String code,
                             HttpSession session) {
        String sessionEmail = (String) session.getAttribute("authEmail");
        String sessionCode = (String) session.getAttribute("authCode");
        Long authTime = (Long) session.getAttribute("authCodeTime");

        if (authTime == null || (System.currentTimeMillis() - authTime) > 180000) {
            return "timeout";
        }

        return (email.equals(sessionEmail) && code.equals(sessionCode)) ? "success" : "fail";
    }

    @GetMapping("/modify")
    public String showModifyForm(HttpSession session, Model model) {
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

        if (loginUser == null) return "redirect:/member/login";

        Boolean pwdCheckSuccess = (Boolean) session.getAttribute("pwdCheckSuccess");
        if (pwdCheckSuccess == null || !pwdCheckSuccess) {
            return "redirect:/member/password-check";
        }

        model.addAttribute("member", memberService.getMemberById(loginUser.getMbrId()));
        return "member/modify";
    }

    @PostMapping("/update")
    public String updateMember(MemberVO memberVO, HttpSession session, HttpServletRequest request, Model model) {
        String sessionEmail = (String) session.getAttribute("authEmail");
        String sessionCode = (String) session.getAttribute("authCode");
        String inputCode = request.getParameter("emailCode");

        if (!memberVO.getMbrEmail().equals(sessionEmail) || !inputCode.equals(sessionCode)) {
            model.addAttribute("error", "이메일 인증이 일치하지 않습니다.");
            model.addAttribute("member", memberVO);
            return "/member/modify";
        }

        String pw = memberVO.getMbrPwd();
        if (pw != null && !pw.isBlank()) {
            String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,20}$";
            if (!pw.matches(pattern)) {
                model.addAttribute("error", "비밀번호는 대소문자, 숫자, 특수문자 포함 8~20자여야 합니다.");
                model.addAttribute("member", memberVO);
                return "/member/modify";
            }

            // ⭐ 여기 수정: 세션 로그인유저에서 salt 가져오기
            MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
            String encrypted = sha.getEncrypt(pw, loginUser.getSalt());
            memberVO.setMbrPwd(encrypted);
            memberVO.setSalt(loginUser.getSalt()); // ⭐ salt도 다시 세팅해줘야 DB update 시 오류 없음
        }

        String phone = memberVO.getMbrPhNo();
        if (phone == null || !phone.matches("^010-\\d{4}-\\d{4}$")) {
            model.addAttribute("error", "휴대전화는 010-XXXX-XXXX 형식으로 입력해주세요.");
            model.addAttribute("member", memberVO);
            return "/member/modify";
        }

        memberService.updateMember(memberVO);

        // 세션 로그인유저 정보 갱신
        session.setAttribute("loginUser", memberService.getMemberById(memberVO.getMbrId()));
        session.removeAttribute("pwdCheckSuccess");

        return "redirect:/main";
    }


    @PostMapping("/delete")
    public String delete(@RequestParam int mbrId, HttpSession session) {
        memberService.deleteMemberById(mbrId);
        session.invalidate();
        return "redirect:/member/login?deleted=true";
    }

    @GetMapping("/check-login")
    @ResponseBody
    public CheckLoginVO checkLoginStatus(HttpSession session) {
        return new CheckLoginVO(session.getAttribute("loginUser") != null);
    }

    @GetMapping("/password-check")
    public String showPasswordCheckForm() {
        return "member/passwordCheck";
    }

    @PostMapping("/password-check")
    public String checkPassword(@RequestParam String mbrPwd,
                                HttpSession session,
                                Model model) {
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        if (loginUser == null) return "redirect:/member/login";

        String encrypted = sha.getEncrypt(mbrPwd, loginUser.getSalt());

        if (encrypted.equals(loginUser.getMbrPwd())) {
            session.setAttribute("pwdCheckSuccess", true);
            return "redirect:/member/modify";
        }

        model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
        return "member/passwordCheck";
    }
}
     
