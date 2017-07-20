package com.ding.buyaround.service;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;

/**
 * Created by djk on 2017/7/13.
 */
@Service
public class OssService {
    @Value("${oss.system.endpoint}")
    private String endPoint;

    @Value("${oss.system.keyid}")
    private String accessKeyId;

    @Value("${oss.system.keysecret}")
    private String accessKeySecret;

    @Value("${oss.system.bucket}")
    private String bucket;

    @Value("${oss.system.expiration}")
    private int expiration;

    private OSSClient ossClient;



    @PostConstruct
    public void init() {
        ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
    }

    public String getMediaPath(String key) {
        Date expirationTime = new Date(new Date().getTime() + expiration * 1000);

        return ossClient.generatePresignedUrl(bucket, key, expirationTime).toString();
    }

    @PreDestroy
    public void dispose() {
        ossClient.shutdown();
    }
}
