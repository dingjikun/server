package com.ding.buyaround.controller;

import com.ding.buyaround.model.ChatMessageEntity;
import com.ding.buyaround.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by pc on 2017/6/1.
 */
@RestController
public class TestController {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @GetMapping("/public")
    @CrossOrigin
    public void publicService() {
        Page<ChatMessageEntity> ret = chatMessageRepository.findAllBySenderidOrderByIdDesc( new PageRequest(0,5), 3l);
        int k = 0;
    }

    @GetMapping("/secret")
    @CrossOrigin
    public String secretService() {
        return "A secret message";
    }
}
