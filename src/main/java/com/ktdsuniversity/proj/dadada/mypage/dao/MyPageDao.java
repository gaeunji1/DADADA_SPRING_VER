package com.ktdsuniversity.proj.dadada.mypage.dao;

import java.util.List;

import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageActivityChatVO;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageActivityRouletteVO;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageOrderDetailVO;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageOrderListVO;
import com.ktdsuniversity.proj.dadada.mypage.vo.MyPageOrderSearchVO;

public interface MyPageDao {
	
	/**
	 * 회원의 주문 내역/배송 조회 목록을 조회
	 * @param mbrId 회원 식별 번호
	 * @return 주문 내역
	*/
	public List<MyPageOrderListVO> getMyOrderList(int mbrId);
	
	/**
	 * 주문 내역 조회
	 * @param myPageOrderSearchVO 검색 조건 (회원 번호, 페이지 번호 등)
	 * @return 주문 내역 목록
	*/
	public List<MyPageOrderListVO> selectMyOrderListByPaging(MyPageOrderSearchVO myPageOrderSearchVO);

	/**
	 * 주문 내역 전체 개수 조회
	 * @param myPageOrderSearchVO 검색 조건 (회원 번호 등)
	 * @return 전체 주문 수
	*/
	public int selectMyOrderListCount(MyPageOrderSearchVO myPageOrderSearchVO);
	
	/**
	 * 주문 상세 조회
	 * @param ordId 주문 ID
	 * @return 주문 상세 정보
	*/
	public MyPageOrderDetailVO getMyOrderDetail(int ordId);
	
	public List<MyPageOrderDetailVO> getMyOrderDetails(int ordId);
	
	/**
	 * 경쟁 참여 내역 조회
	 * @param myPageActivityChatVO 검색 조건 (회원 번호, 페이지 번호 등)
	 * @return 경쟁 참여 내역
	*/
	public List<MyPageActivityChatVO> getMyActivityChatList(MyPageActivityChatVO myPageActivityChatVO);
	
	/**
	 * 경쟁 참여 내역 개수 조회
	 * @param myPageActivityChatVO 검색 조건 (회원 번호, 페이지 번호 등)
	 * @return 경쟁 참여 내역 개수
	*/
	public int getMyActivityChatListCount(MyPageActivityChatVO myPageActivityChatVO);
	
	/**
	 * 룰렛 이용 내역 조회
	 * @param myPageActivityRouletteVO 검색 조건 (회원 번호, 페이지 번호 등)
	 * @return 룰렛 이용 내역
	*/
	public List<MyPageActivityRouletteVO> getMyActivityRouletteList(MyPageActivityRouletteVO myPageActivityRouletteVO);
	
	/**
	 * 룰렛 이용 내역 개수 조회
	 * @param myPageActivityRouletteVO 검색 조건 (회원 번호, 페이지 번호 등)
	 * @return 룰렛 이용 내역 개수
	*/
	public int getMyActivityRouletteListCount(MyPageActivityRouletteVO myPageActivityRouletteVO);
}
