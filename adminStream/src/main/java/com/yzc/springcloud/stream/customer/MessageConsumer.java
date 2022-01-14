package com.yzc.springcloud.stream.customer;



import com.yzc.springcloud.mystream.MySink;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(MySink.class)
public class MessageConsumer {


    /**
     * 接收消息
     */
    @StreamListener(MySink.TO_MSG)
    public void toMsg(String msg) {
        System.out.println("TO_MSG:"+msg);
    }
}
