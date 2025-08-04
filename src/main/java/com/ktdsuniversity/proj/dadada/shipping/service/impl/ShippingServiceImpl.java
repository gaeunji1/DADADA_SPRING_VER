package com.ktdsuniversity.proj.dadada.shipping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.proj.dadada.shipping.dao.ShippingDao;
import com.ktdsuniversity.proj.dadada.shipping.service.ShippingService;
import com.ktdsuniversity.proj.dadada.shipping.vo.ShippingVO;

@Service
public class ShippingServiceImpl implements ShippingService{

	@Autowired
    private ShippingDao shippingDao;

    @Override
    public int insertShipping(ShippingVO shippingVO) {
        return shippingDao.insertShipping(shippingVO);
    }
    
    @Override
    @Transactional
    public void updateShippingStatus(int ordId, String shpStat) {
        ShippingVO vo = new ShippingVO();
        vo.setOrdId(ordId);
        vo.setShpStat(shpStat);
        shippingDao.updateShippingStatus(vo);
    }
}
