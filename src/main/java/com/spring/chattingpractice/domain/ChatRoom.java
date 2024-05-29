package com.spring.chattingpractice.domain;

import com.spring.chattingpractice.service.ChatService;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ChatRoom {

    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handleActions(WebSocketSession session, Message message, ChatService chatService) {
        if (message.getType().equals(Message.MessageType.ENTER)){
            sessions.add(session);
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }
        sendMessage(message, chatService);
    }

    public <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream().forEach(session -> {chatService.sendMessage(session,message);});
    }
}
