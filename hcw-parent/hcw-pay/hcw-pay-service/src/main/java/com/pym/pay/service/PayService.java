package com.pym.pay.service;

import com.pym.base.BaseService;
import com.pym.pay.entity.PayInfo;

import java.util.Map;

public interface PayService extends BaseService<PayInfo> {
    String createToken(PayInfo payInfo);
    PayInfo findByToke(String token);
    PayInfo findByOrderId(Long orderId);
    boolean dealAlipayNotify(Map<String,String> params);
}
