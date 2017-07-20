package com.ding.buyaround.controller;

import com.ding.buyaround.model.StockEntity;
import com.ding.buyaround.model.StockPOI;
import com.ding.buyaround.repository.StockRepository;
import com.ding.buyaround.service.BMapService;
import com.ding.buyaround.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by pc on 2017/6/1.
 */
@RestController
public class TestController {

    @GetMapping("/public")
    @CrossOrigin
    public void publicService() {
    }

    @GetMapping("/secret")
    @CrossOrigin
    public String secretService() {
        return "A secret message";
    }
}
