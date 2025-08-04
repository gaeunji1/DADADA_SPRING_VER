package com.ktdsuniversity.proj.dadada.admin.service;

import java.util.List;

import com.ktdsuniversity.proj.dadada.admin.vo.AdminVO;

public interface AdminService {

    // 전체 주문 목록 조회
    public List<AdminVO> getAllOrders();

}
