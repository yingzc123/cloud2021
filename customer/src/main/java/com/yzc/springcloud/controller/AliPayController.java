package com.yzc.springcloud.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzc.springcloud.entity.AlipayConfig;
import com.yzc.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.*;


@Slf4j
@RestController
public class AliPayController {
    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired

    private  PaymentService paymentService;

    //支付发起方法
    @PostMapping("/ali_pay")
    public String aliPay( HttpServletRequest request) throws Exception {
        AlipayConfig alipayConfig = new AlipayConfig();//aliPayService.getAliPayConfig();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, alipayConfig.getAppId(),
                alipayConfig.getMerchantPrivateKey(), "json", AlipayConfig.charset,
                alipayConfig.getAlipayPublicKey(), AlipayConfig.signType);
        log.info("配置信息:{}", AlipayConfig.gatewayUrl);
        String host ="";
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(request.getParameter("order_no").getBytes("ISO-8859-1"), "UTF-8");
        //订单名称，必填
        String subject = new String(request.getParameter("subject").getBytes("ISO-8859-1"), "UTF-8");
        //设置请求参数
//        alipayRequest.setReturnUrl(host + "/api/" + applicationName + "/return_url");
        AlipayTradePagePayRequest alipayRequest = paymentService.aliPayApply(out_trade_no, subject);
        alipayRequest.setNotifyUrl(host + "/api/" + applicationName + "/ali_pay_notify");
        // 调用SDK生成表单
        return alipayClient.pageExecute(alipayRequest).getBody();
    }

    //异步回调处理方法
    @PostMapping("/ali_pay_notify")
    public String notifyUrl(HttpServletRequest request) throws Exception {
        log.info("支付宝异步异步调用");
        AlipayConfig alipayConfig = new AlipayConfig();//aliPayService.getAliPayConfig();
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params,
                alipayConfig.getAlipayPublicKey(),
                AlipayConfig.charset,
                AlipayConfig.signType); //调用SDK验证签名

        if (signVerified) {//验证成功
            log.info("异步验证成功");
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

            if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
               // orderService.thirdOauthPaySucceeded(out_trade_no, trade_no);
                return "success";
            } else {
                return "fail";
            }
        } else {//验证失败
            log.info("异步验证失败");
            return "fail";
            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }
    }


}
