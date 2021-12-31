package com.yzc.springcloud.mystream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySink01 {
    java.lang.String DEFAULT_Msg = "default_msg";

    java.lang.String TO_MSG = "to_msg";

    @Input(DEFAULT_Msg)
    SubscribableChannel defaultMsg();

    @Input(TO_MSG)
    SubscribableChannel toMsg();
}
