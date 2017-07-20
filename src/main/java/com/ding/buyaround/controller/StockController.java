package com.ding.buyaround.controller;

import com.ding.buyaround.model.StockEntity;
import com.ding.buyaround.model.StockPOI;
import com.ding.buyaround.repository.StockRepository;
import com.ding.buyaround.repository.StockRepositoryImpl;
import com.ding.buyaround.service.BMapService;
import com.ding.buyaround.service.OssService;
import com.ding.buyaround.service.StockService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by djk on 2017/7/11.
 */
@RestController
@CrossOrigin
public class StockController {
    @Autowired
    private BMapService bMapService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StockService stockService;

    @Autowired
    private OssService ossService;

    @PostMapping("/stock/putPOI")
    public void putPOI(@RequestBody StockPOI stock) {
        bMapService.uploadstock(stock);
    }

    @PostMapping("/stock/getMediaInfo")
    public List<String> getMediaInfo(@RequestBody String stock) throws IOException {
        List<String> ret = new ArrayList<>();
        Map<String, Object> stockMap = objectMapper.readValue(stock, Map.class);

        StockEntity stockEntity = stockService.getStock(Long.parseLong(stockMap.get("categoryID").toString()),
                Long.parseLong(stockMap.get("userID").toString()), stockMap.get("ossID").toString());

        if (stockEntity != null)
        {
            stockService.getStockImages(stockEntity).stream()
                    .forEach(image -> {
                        String key = stockService.generateOssImageKey(stockEntity.getOwnerid(), stockEntity.getCategoryid(), stockEntity.getOssid(), image);
                        ret.add(ossService.getMediaPath(key));
                    });


            stockService.getStockVideos(stockEntity).stream()
                    .forEach(video -> {
                        String key = stockService.generateOssVideoKey(stockEntity.getOwnerid(), stockEntity.getCategoryid(), stockEntity.getOssid(), video);
                        ret.add(ossService.getMediaPath(key));
                    });
        }

        return ret;
    }
}
