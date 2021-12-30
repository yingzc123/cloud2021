package com.yzc.springcloud.service.imp;

import com.alipay.api.request.AlipayTradePagePayRequest;
import com.yzc.springcloud.entity.Payment;
import com.yzc.springcloud.dao.PaymentMapper;
import com.yzc.springcloud.entity.feignVo.Order;
import com.yzc.springcloud.feign.OrderFeign;
import com.yzc.springcloud.service.PaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzc
 * @since 2021-08-07
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {
 @Autowired
 private OrderFeign orderFeign;

     @Override
    public AlipayTradePagePayRequest aliPayApply(String orderNo, String subject) throws Exception{
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        Order order = orderFeign.getOrderByOrderNo(orderNo);
        if(null == order) {
            throw new Exception("系统中找不到订单号");
        }
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + orderNo + "\","
                + "\"total_amount\":\"" + order.getPrice() + "\","
                + "\"subject\":\"" + order.getGoodsName() + "\","
                + "\"body\":\"" + order.getGoodsName() + "\","  //商品描述，可空
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        return alipayRequest;
    }
}
