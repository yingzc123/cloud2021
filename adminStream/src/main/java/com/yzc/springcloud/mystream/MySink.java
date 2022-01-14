package com.yzc.springcloud.mystream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {
    java.lang.String TO_MSG = "to_msg";

    @Input(TO_MSG)
    SubscribableChannel toMsg();
}
