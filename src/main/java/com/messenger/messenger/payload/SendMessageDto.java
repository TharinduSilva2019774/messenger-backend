package com.messenger.messenger.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMessageDto {

    private Long userId;
    private String message;

}
