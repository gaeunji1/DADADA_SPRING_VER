package com.ktdsuniversity.proj.dadada.admin.dao;

import java.util.List;

import com.ktdsuniversity.proj.dadada.admin.vo.AdminVO;

public interface AdminDAO {
    public List<AdminVO> selectAllOrders();
}