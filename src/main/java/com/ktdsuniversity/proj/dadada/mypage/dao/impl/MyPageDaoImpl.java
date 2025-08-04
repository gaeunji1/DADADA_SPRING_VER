package com.ktdsuniversity.proj.dadada.mypage.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.proj.dadada.mypage.dao.MyPageDao;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageActivityChatVO;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageActivityRouletteVO;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageOrderDetailVO;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageOrderListVO;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageOrderSearchVO;

@Repository
public class MyPageDaoImpl extends SqlSessionDaoSupport implements MyPageDao {
	
	private static final String NAME_SPACE = "com.ktdsuniversity.proj.dadada.mypage.dao.impl.MyPageDaoImpl.";
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public List<MyPageOrderListVO> getMyOrderList(int mbrId) {
		return getSqlSession().selectList(NAME_SPACE + "getMyOrderList", mbrId);
	}

	@Override
	public List<MyPageOrderListVO> selectMyOrderListByPaging(MyPageOrderSearchVO myPageOrderSearchVO) {
		return this.getSqlSession().selectList(NAME_SPACE + "selectMyOrderListByPaging", myPageOrderSearchVO);
	}

	@Override
	public int selectMyOrderListCount(MyPageOrderSearchVO myPageOrderSearchVO) {
		return this.getSqlSession().selectOne(NAME_SPACE + "selectMyOrderListCount", myPageOrderSearchVO);
	}

	@Override
	public MyPageOrderDetailVO getMyOrderDetail(int ordId) {
		return getSqlSession().selectOne(NAME_SPACE + "getMyOrderDetail", ordId);
	}

	@Override
	public List<MyPageActivityChatVO> getMyActivityChatList(MyPageActivityChatVO myPageActivityChatVO) {
		return getSqlSession().selectList(NAME_SPACE + "getMyActivityChatList", myPageActivityChatVO);
	}

	@Override
	public int getMyActivityChatListCount(MyPageActivityChatVO myPageActivityChatVO) {
		return getSqlSession().selectOne(NAME_SPACE + "getMyActivityChatListCount", myPageActivityChatVO);
	}

	@Override
	public List<MyPageActivityRouletteVO> getMyActivityRouletteList(MyPageActivityRouletteVO myPageActivityRouletteVO) {
		return getSqlSession().selectList(NAME_SPACE + "getMyActivityRouletteList", myPageActivityRouletteVO);
	}

	@Override
	public int getMyActivityRouletteListCount(MyPageActivityRouletteVO myPageActivityRouletteVO) {
		return getSqlSession().selectOne(NAME_SPACE + "getMyActivityRouletteListCount", myPageActivityRouletteVO);
	}

	@Override
	public List<MyPageOrderDetailVO> getMyOrderDetails(int ordId) {
		return getSqlSession().selectList(NAME_SPACE + "getMyOrderDetails", ordId);
	}
}
