package com.spring.chattingpractice.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.chattingpractice.domain.ChatRoom;
import com.spring.chattingpractice.domain.Message;
import com.spring.chattingpractice.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;


    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload: {}", payload);
//        TextMessage textMessage = new TextMessage("WELCOME TO CHAT PRACTICE");
//        session.sendMessage(textMessage);
        Message chatMessage = objectMapper.readValue(payload, Message.class);
        ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
        room.handleActions(session, chatMessage, chatService);
    }

}
