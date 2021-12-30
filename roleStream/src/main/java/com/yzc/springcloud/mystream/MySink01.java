package com.yzc.springcloud.mystream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySink01 {
    java.lang.String DEFAULT_Msg = "default_msg";

    @Input(DEFAULT_Msg)
    SubscribableChannel defaultMsg();
}
