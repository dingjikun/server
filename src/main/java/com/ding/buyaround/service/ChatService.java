package com.ding.buyaround.service;

import com.ding.buyaround.model.ChatMessageEntity;
import com.ding.buyaround.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by djk on 2017/7/24.
 */
@Service
public class ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public void saveMessage(ChatMessageEntity chatMessageEntity)
    {
        chatMessageRepository.save(chatMessageEntity);
    }

    public List<ChatMessageEntity> getMessages(Long senderid, int page, int size)
    {
        return chatMessageRepository.findAllBySenderidOrderByIdDesc(new PageRequest(page, size), senderid).getContent();
    }
}
