package com.ding.buyaround.controller;

import com.ding.buyaround.model.ChatMessageEntity;
import com.ding.buyaround.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by djk on 2017/7/23.
 */
@RestController
@CrossOrigin
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ChatService chatService;


    @PostMapping("/chat/putMessage")
    public ChatMessageEntity putMessage(@RequestBody String message) throws IOException {
        Map<String, Object> messageMap = objectMapper.readValue(message, Map.class);

        ChatMessageEntity chatMessageEntity = new ChatMessageEntity();
        chatMessageEntity.setSenderid(Long.parseLong(messageMap.get("senderid").toString()));
        chatMessageEntity.setContent(messageMap.get("content").toString());

        ChatMessageEntity ret = chatService.saveMessage(chatMessageEntity);

        simpMessagingTemplate.convertAndSend("/queue/" + messageMap.get("destuserid").toString() + "/exchange/message", ret);

        return ret;
    }

    @PostMapping("/chat/getMessages")
    public List<ChatMessageEntity> getMessages(@RequestBody String param) throws IOException {
        Map<String, Object> messageMap = objectMapper.readValue(param, Map.class);

        Long senderid = Long.parseLong(messageMap.get("senderid").toString());
        int page = Integer.parseInt(messageMap.get("page").toString());
        int size = Integer.parseInt(messageMap.get("size").toString());

        return chatService.getMessages(senderid, page, size);
    }

}
