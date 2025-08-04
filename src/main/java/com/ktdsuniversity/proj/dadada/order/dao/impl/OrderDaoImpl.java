package com.ktdsuniversity.proj.dadada.order.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.proj.dadada.order.dao.OrderDao;
import com.ktdsuniversity.proj.dadada.order.vo.OrderDetailVO;
import com.ktdsuniversity.proj.dadada.order.vo.OrderVO;

@Repository
public class OrderDaoImpl extends SqlSessionDaoSupport implements OrderDao {
	
	private static final String NAME_SPACE = "com.ktdsuniversity.proj.dadada.order.dao.impl.OrderDaoImpl.";
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

    @Override
    public void insertOrder(OrderVO order) {
        getSqlSession().insert(NAME_SPACE + "insertOrder", order);
    }

    @Override
    public void insertOrderDetail(OrderDetailVO detail) {
        getSqlSession().insert(NAME_SPACE + "insertOrderDetail", detail);
    }
    


}
