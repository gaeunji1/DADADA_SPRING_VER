package com.ktdsuniversity.proj.dadada.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.proj.dadada.member.dao.MemberDAO;
import com.ktdsuniversity.proj.dadada.member.vo.MemberVO;
import com.ktdsuniversity.proj.dadada.order.dao.OrderDao;
import com.ktdsuniversity.proj.dadada.order.service.OrderService;
import com.ktdsuniversity.proj.dadada.order.vo.OrderDetailVO;
import com.ktdsuniversity.proj.dadada.order.vo.OrderVO;
import com.ktdsuniversity.proj.dadada.payment.dao.PaymentDAO;
import com.ktdsuniversity.proj.dadada.payment.vo.PaymentVO;
import com.ktdsuniversity.proj.dadada.product.dao.ProductDao;
import com.ktdsuniversity.proj.dadada.product.vo.ProductQtyUpdateVO;
import com.ktdsuniversity.proj.dadada.shipping.dao.ShippingDao;
import com.ktdsuniversity.proj.dadada.shipping.vo.ShippingVO;

@Service
public class OrderServiceImple implements OrderService {
	
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private PaymentDAO paymentDao;
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private ShippingDao shippingDao;
	
	@Override
	@Transactional
	public void saveOrder(int mbrId, PaymentVO vo) {
		
		int totalCnt = vo.getItems().stream()
									.mapToInt(PaymentVO.Item::getQty)
									.sum();
		double totalPrice = vo.getItems().stream()
										.mapToDouble(item -> vo.getPaidAmount())
										.sum();
		
		OrderVO order = new OrderVO();
		order.setMbrId(mbrId);
		
//		PaymentVO.Item item = vo.getItems().get(0);
		
//		order.setPrdId(item.getPrdId());
//		order.setOrdCnt(item.getQty());
		order.setOrdCnt(totalCnt);
		order.setPrdPrc(totalPrice);
		order.setPrdPrc(vo.getPaidAmount());
		order.setDscntRt(0);
		order.setPrdDscntPrc(0);
		order.setPrdPrcFinal(vo.getPaidAmount());
		String ordDvsn = vo.getOrdDvsn();
		order.setOrdDvsn(ordDvsn == null ? "regular" : ordDvsn);
		order.setDlvStat("결제 완료");
		order.setCrtPckgYn("N");
		
		orderDao.insertOrder(order); 
		
//		OrderDetailVO detail = new OrderDetailVO();
//		detail.setOrdId(order.getOrdId());
//		detail.setPrdId(item.getPrdId());
//		detail.setPrdQty(item.getQty());
//		orderDao.insertOrderDetail(detail);
//		
//		ProductQtyUpdateVO stock = new ProductQtyUpdateVO(item.getPrdId(), item.getQty());
//		productDao.decreaseProductQty(stock);
//		
		for (PaymentVO.Item item : vo.getItems()) {
	        OrderDetailVO detail = new OrderDetailVO();
	        detail.setOrdId(order.getOrdId());
	        detail.setPrdId(item.getPrdId());
	        detail.setPrdQty(item.getQty());
	        
	        double unitPrice = productDao.getProductPrice(item.getPrdId());
	        detail.setPrdPrc(unitPrice);
	        detail.setDscntRt(0);
	        detail.setPrdDscntPrc(0);
	        detail.setPrdPrcFinal(unitPrice * item.getQty());
	        orderDao.insertOrderDetail(detail);

	        // 재고 차감
	        ProductQtyUpdateVO stock = new ProductQtyUpdateVO(item.getPrdId(), item.getQty());
	        productDao.decreaseProductQty(stock);
	    }

		vo.setMerchantUid(String.valueOf(order.getOrdId()));
		paymentDao.insertPayment(vo);
		
		MemberVO mem = memberDAO.selectMemberForShipping(mbrId);
		
		String fullAddr = mem.getMbrAddr1() + " " + mem.getMbrAddr2()
			+ " (" + mem.getMbrPstlCd() + ")";
		
		ShippingVO shp = new ShippingVO();
		shp.setOrdId(order.getOrdId());
		shp.setShpNm(mem.getMbrNcknm());
		shp.setShpPhNo(mem.getMbrPhNo());
		shp.setShpAddr(fullAddr);
		shp.setShpReqCn("");
		
		shippingDao.insertShipping(shp);
	}
}
