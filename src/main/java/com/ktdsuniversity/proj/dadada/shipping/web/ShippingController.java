package com.ktdsuniversity.proj.dadada.shipping.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.proj.dadada.shipping.service.ShippingService;
import com.ktdsuniversity.proj.dadada.shipping.vo.ShippingVO;

@Controller
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    @PostMapping("/shipping/save")
    @ResponseBody
    public ResponseEntity<String> saveShipping(@RequestBody ShippingVO shippingVO) {
        shippingService.insertShipping(shippingVO);
        return ResponseEntity.ok("배송지 저장 완료");
    }

}
