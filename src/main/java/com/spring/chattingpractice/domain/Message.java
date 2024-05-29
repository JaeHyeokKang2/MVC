package com.spring.chattingpractice.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class Message {

    public enum MessageType {
        ENTER, TALK, LEAVE
    }

    private MessageType Type;
    private String roomId;
    private String sender;
    private String message;

}
