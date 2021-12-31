package com.yzc.springcloud.mystream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MySource01 {
    String DEFAULT_Msg = "default_msg";

    String TO_MSG = "to_msg";

    @Output(DEFAULT_Msg)
    MessageChannel defaultMsg();

    @Output(TO_MSG)
    MessageChannel toMsg();
}
