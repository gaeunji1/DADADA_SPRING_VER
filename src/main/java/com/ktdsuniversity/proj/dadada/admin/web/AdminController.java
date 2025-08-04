package com.ktdsuniversity.proj.dadada.admin.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ktdsuniversity.proj.dadada.admin.service.AdminService;
import com.ktdsuniversity.proj.dadada.admin.vo.AdminVO;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/main")
	public String adminMainPage() {
		return "admin/main";
	}

	@GetMapping("/orders")
	public String viewOrderListPage(Model model) {
		List<AdminVO> orderList = adminService.getAllOrders();
		model.addAttribute("orderList", orderList);
		return "admin/admin_orderlist";
	}
}