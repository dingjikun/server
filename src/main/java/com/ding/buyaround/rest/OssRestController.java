package com.ding.buyaround.rest;

import com.aliyun.oss.OSSClient;
import model.OssToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

/**
 * Created by pc on 2017/5/28.
 */
@RestController
@RequestMapping("/api")
public class OssRestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String accessId = "LTAIWNORFHS2TfeY";
    private final String accessKey = "rOLYD9C0IrRiRbn7e1jPhMd1DEPG1W";
    private final String bucket = "testbukk";
    private final String endPoint = "http://oss-cn-shanghai.aliyuncs.com";

    public OssRestController() {
    }

    @RequestMapping("/test")
    public OssToken test() {
        //OSSClient client = new OSSClient(endPoint, accessId, accessKey);
        //byte[] content = "Hello OSS".getBytes();
        //client.putObject(bucket, "mytestkey", new ByteArrayInputStream(content));
        return new OssToken("test");
    }
}
