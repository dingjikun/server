package com.ding.buyaround.controller;

import com.ding.buyaround.model.ChatMessage;
import com.ding.buyaround.model.ChatMessageEntity;
import com.ding.buyaround.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
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


    //注意:WebSocket的输入必须为只有一个string的类，且必须具有赋值构造函数
    @MessageMapping("/say.{sourceuserid}to{destuserid}")
    public void say(String message, @DestinationVariable("sourceuserid") String sourceuserid,
                    @DestinationVariable("destuserid") String destuserid) throws IOException {
        //注册模版/queue/3/exchange/message
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMsg(message);
        chatMessage.setSourceuserid(sourceuserid);
        simpMessagingTemplate.convertAndSend("/queue/" + destuserid + "/exchange/message", chatMessage);
    }

    @PostMapping("/chat/putMessage")
    public void putMessage(@RequestBody String message) throws IOException {
        Map<String, Object> messageMap = objectMapper.readValue(message, Map.class);

        ChatMessageEntity chatMessageEntity = new ChatMessageEntity();
        chatMessageEntity.setSenderid(Long.parseLong(messageMap.get("senderid").toString()));
        chatMessageEntity.setContent(messageMap.get("content").toString());

        chatService.saveMessage(chatMessageEntity);
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
