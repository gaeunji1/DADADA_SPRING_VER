package com.ktdsuniversity.proj.dadada.mypage.service;

import java.util.List;

import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageActivityChatVO;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageActivityRouletteVO;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageOrderDetailVO;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageOrderListVO;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageOrderSearchVO;
import com.ktdsuniversity.proj.dadada.roulette.vo.RouletteSpinVO;

public interface MyPageService {
	
	public List<MyPageOrderListVO> getMyOrderList(int mbrId);
	
	public List<MyPageOrderListVO> getMyOrderListByPaging(MyPageOrderSearchVO myPageOrderSearchVO);
	
	public int getMyOrderListCount(MyPageOrderSearchVO myPageOrderSearchVO);
	
	public MyPageOrderDetailVO getMyOrderDetail(int ordId);
	
	public List<MyPageOrderDetailVO> getMyOrderDetails(int ordId);
	
	public List<RouletteSpinVO> getMyRouletteSpins(int mbrId);
	
	public List<MyPageActivityChatVO> getMyActivityChatList(MyPageActivityChatVO myPageActivityChatVO);
	
	public int getMyActivityChatListCount(MyPageActivityChatVO myPageActivityChatVO);
	
	public List<MyPageActivityRouletteVO> getMyActivityRouletteList(MyPageActivityRouletteVO myPageActivityRouletteVO);
	
	public int getMyActivityRouletteListCount(MyPageActivityRouletteVO myPageActivityRouletteVO);
}
