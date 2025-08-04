package com.ktdsuniversity.proj.dadada.mypage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.proj.dadada.mypage.dao.MyPageDao;
import com.ktdsuniversity.proj.dadada.mypage.service.MyPageService;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageActivityChatVO;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageActivityRouletteVO;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageOrderDetailVO;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageOrderListVO;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageOrderSearchVO;
import com.ktdsuniversity.proj.dadada.roulette.dao.RouletteDAO;
import com.ktdsuniversity.proj.dadada.roulette.vo.RouletteSpinVO;

@Service
public class MyPageServiceImpl implements MyPageService {
	
	@Autowired
	private MyPageDao myPageDao;
	
	@Autowired
	private RouletteDAO rouletteDAO;

	@Transactional(readOnly = true)
	@Override
	public List<MyPageOrderListVO> getMyOrderList(int mbrId) {
		return myPageDao.getMyOrderList(mbrId);
	}

	@Transactional(readOnly = true)
	@Override
	public List<MyPageOrderListVO> getMyOrderListByPaging(MyPageOrderSearchVO myPageOrderSearchVO) {
		return myPageDao.selectMyOrderListByPaging(myPageOrderSearchVO);
	}

	@Transactional(readOnly = true)
	@Override
	public int getMyOrderListCount(MyPageOrderSearchVO myPageOrderSearchVO) {
		return myPageDao.selectMyOrderListCount(myPageOrderSearchVO);
	}

	@Transactional(readOnly = true)
	@Override
	public MyPageOrderDetailVO getMyOrderDetail(int ordId) {
		return myPageDao.getMyOrderDetail(ordId);
	}

	@Transactional(readOnly = true)
	@Override
	public List<RouletteSpinVO> getMyRouletteSpins(int mbrId) {
		return rouletteDAO.selectSpinsByMemberId(mbrId);
	}

	@Transactional(readOnly = true)
	@Override
	public List<MyPageActivityChatVO> getMyActivityChatList(MyPageActivityChatVO myPageActivityChatVO) {
		return myPageDao.getMyActivityChatList(myPageActivityChatVO);
	}

	@Transactional(readOnly = true)
	@Override
	public int getMyActivityChatListCount(MyPageActivityChatVO myPageActivityChatVO) {
		return myPageDao.getMyActivityChatListCount(myPageActivityChatVO);
	}

	@Transactional(readOnly = true)
	@Override
	public List<MyPageActivityRouletteVO> getMyActivityRouletteList(MyPageActivityRouletteVO myPageActivityRouletteVO) {
		return myPageDao.getMyActivityRouletteList(myPageActivityRouletteVO);
	}

	@Transactional(readOnly = true)
	@Override
	public int getMyActivityRouletteListCount(MyPageActivityRouletteVO myPageActivityRouletteVO) {
		return myPageDao.getMyActivityRouletteListCount(myPageActivityRouletteVO);
	}

	@Transactional(readOnly = true)
	@Override
	public List<MyPageOrderDetailVO> getMyOrderDetails(int ordId) {
		return myPageDao.getMyOrderDetails(ordId);
	}
}
