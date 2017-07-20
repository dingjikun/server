package com.ding.buyaround.service;

import com.ding.buyaround.model.OssCallback;
import com.ding.buyaround.model.StockEntity;
import com.ding.buyaround.repository.StockRepository;
import com.ding.buyaround.repository.StockRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by djk on 2017/7/9.
 */
@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    private static final String ossPrefix = "stock/";

    public void uploadStock(OssCallback ossinfo) {

        StockEntity stock = new StockEntity();
        stock.setCategoryid(Long.parseLong(ossinfo.getCategoryid()));
        stock.setOwnerid(Long.parseLong(ossinfo.getOwnerid()));
        stock.setOssid(ossinfo.getUuid());
        stock.setLongitude(Double.parseDouble(ossinfo.getJd()));
        stock.setLatitude(Double.parseDouble(ossinfo.getWd()));

        stock.setDescription(Optional.ofNullable(ossinfo.getDescription()).orElse(""));

        stock.setVideos(Optional.ofNullable(ossinfo.getVideo()).orElse(""));

        stock.setImages(Optional.ofNullable(ossinfo.getImage()).orElse(""));

        stockRepository.saveMyStock(stock);
    }

    public StockEntity getStock(Long categoryid, Long userid, String ossid) {
        return stockRepository.findByCategoryidAndOwneridAndOssid(categoryid, userid, ossid);
    }

    public List<String> getStockImages(StockEntity stock) {
        return Arrays.stream(stock.getImages().split(StockRepositoryImpl.spliter))
                .filter(image -> !StockRepositoryImpl.placeholder.equals(image)).collect(Collectors.toList());
    }

    public List<String> getStockVideos(StockEntity stock) {
        return Arrays.stream(stock.getVideos().split(StockRepositoryImpl.spliter))
                .filter(video -> !StockRepositoryImpl.placeholder.equals(video)).collect(Collectors.toList());

    }

    public String generateOssImageKey(Long userid, Long categoryid, String ossid, String filename) {
        return ossPrefix + userid + "/" + categoryid + "/" + ossid + "/images/" + filename;
    }

    public String generateOssVideoKey(Long userid, Long categoryid, String ossid, String filename) {
        return ossPrefix + userid + "/" + categoryid + "/" + ossid + "/videos/" + filename;
    }
}
