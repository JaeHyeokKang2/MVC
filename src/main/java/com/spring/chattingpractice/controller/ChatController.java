package com.spring.chattingpractice.controller;

import com.spring.chattingpractice.domain.ChatRoom;
import com.spring.chattingpractice.service.ChatService;
import com.sun.source.tree.BreakTree;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ChatRoom createChatRoom(@RequestBody String name) {
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoom> getChatRooms() {
        return chatService.findAllRoom();
    }

}
