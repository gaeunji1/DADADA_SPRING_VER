package com.ktdsuniversity.proj.dadada.admin.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.proj.dadada.admin.dao.AdminDAO;
import com.ktdsuniversity.proj.dadada.admin.vo.AdminVO;

@Repository
public class AdminDAOImpl implements AdminDAO {

    @Autowired
    private SqlSessionTemplate sqlSession;

    private final String NS = "com.ktdsuniversity.proj.dadada.admin.dao.AdminDAO";

    @Override
    public List<AdminVO> selectAllOrders() {
        return sqlSession.selectList(NS + ".selectAllOrders");
    }
}