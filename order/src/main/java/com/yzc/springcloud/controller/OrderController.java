package com.yzc.springcloud.controller;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzc.springcloud.entity.Order;
import com.yzc.springcloud.entity.ResultObject;
import com.yzc.springcloud.entity.dto.OrderDto;
import com.yzc.springcloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzc
 * @since 2021-08-07
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Value("${server.port}")
    private String port;
    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private OrderService orderService;


    @GetMapping("/getPort")
    public String getPort()
    {
        return "order:"+port;
    }

    @GetMapping("/getService")
    public Object get(){
        List<String> services = discoveryClient.getServices();
        services.forEach(i-> System.out.println(i));
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-ORDER-SERVICE");
        instances.forEach(i-> System.out.println("\t:"+i.getServiceId()+"\t:"+i.getHost()+"\t:"+i.getPort()+"\t:"+i.getUri()));
        return this.discoveryClient;
    }

    @GetMapping("/getOrderNo")
    public Order getOrderByOrderNo(String orderNo)
    {
        return  orderService.getOne(new QueryWrapper<Order>().eq("order_no", orderNo));
    }

    /**
     * 支付
     */
    @PostMapping ("/userPay")
    public ResultObject userPay(@RequestBody  @Valid OrderDto.PayOrderUser dto) {
        log.info(JSONUtil.toJsonStr(dto));
        try {
            orderService.payOrder(dto);
            return new ResultObject(200,"支付成功",dto.getOrderNo());
        }catch (Exception e) {
            return new ResultObject(500,e.getMessage());
        }
    }

    /**
     * 生成订单
     */
    @PostMapping ("/createOrder")
    public ResultObject createOrder(@RequestBody  @Valid OrderDto.CreateOrderUser dto) throws Exception{
        try {
            Order order = orderService.userCreateOrder(dto);
            return new ResultObject(200,"下单成功",order);
        }catch (Exception e) {
            return new ResultObject(500,e.getMessage());
        }
    }

}
