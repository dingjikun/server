package com.ding.buyaround.service;

import com.ding.buyaround.model.StockPOI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.concurrent.Future;

/**
 * Created by djk on 2017/7/9.
 */
@Service
public class BMapService {
    private static final String bmapBase = "http://api.map.baidu.com/geodata/v3/";

    //Value配置不能初始化static类型
    @Value("${bmap.secret}")
    private String ak;

    @Value("${bmap.stock.geotable_id}")
    private String geotable_id;

    @Value("${bmap.stock.coord_type}")
    private String coord_type;

    public void uploadstock(StockPOI stock) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("ak", ak);
        map.add("geotable_id", geotable_id);
        map.add("coord_type", coord_type);
        map.add("longitude", stock.getJd());
        map.add("latitude", stock.getWd());
        map.add("oss_id", stock.getOssID());
        map.add("category_id", stock.getCategoryID());
        map.add("tags", stock.getUserID());


        AsyncRestTemplate restTemplate = new AsyncRestTemplate();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        Future<ResponseEntity<String>> response = restTemplate.postForEntity( bmapBase + "poi/create", request , String.class );

    }
}
