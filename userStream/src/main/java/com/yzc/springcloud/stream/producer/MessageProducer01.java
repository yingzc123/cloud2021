package com.yzc.springcloud.stream.producer;


import com.yzc.springcloud.mystream.MySource01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(MySource01.class)
public class MessageProducer01 {
    @Autowired
    private MySource01 mySource01;

    public void send(String msg){
        mySource01.defaultMsg().send(MessageBuilder.withPayload(msg).build());
        mySource01.toMsg().send(MessageBuilder.withPayload(msg).build());
    };


}
