package com.yzc.springcloud.service;

import com.alipay.api.request.AlipayTradePagePayRequest;
import com.yzc.springcloud.entity.Payment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzc
 * @since 2021-08-07
 */
public interface PaymentService extends IService<Payment> {
  AlipayTradePagePayRequest aliPayApply(String orderNo, String subject) throws Exception;

}
