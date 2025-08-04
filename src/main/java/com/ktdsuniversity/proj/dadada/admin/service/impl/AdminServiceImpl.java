package com.ktdsuniversity.proj.dadada.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.proj.dadada.admin.dao.AdminDAO;
import com.ktdsuniversity.proj.dadada.admin.service.AdminService;
import com.ktdsuniversity.proj.dadada.admin.vo.AdminVO;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Override
    public List<AdminVO> getAllOrders() {
        return adminDAO.selectAllOrders();
    }
}