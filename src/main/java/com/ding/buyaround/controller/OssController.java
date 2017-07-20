package com.ding.buyaround.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.ding.buyaround.config.OssConfig;
import com.ding.buyaround.model.OssCallback;
import com.ding.buyaround.model.OssToken;
import com.ding.buyaround.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by pc on 2017/6/26.
 */
@RestController
@CrossOrigin
public class OssController {

    @Autowired
    private OssConfig ossConfig;

    @Autowired
    private StockService stockService;

    // 目前只有"cn-hangzhou"这个region可用, 不要使用填写其他region的值
    public static final String REGION_CN_HANGZHOU = "cn-hangzhou";
    public static final String STS_API_VERSION = "2015-04-01";

    private AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret, String roleArn,
                                            String roleSessionName, String policy, ProtocolType protocolType, long durationSeconds) throws ClientException
    {
        try {
            // 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
            IClientProfile profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);

            // 创建一个 AssumeRoleRequest 并设置请求参数
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setVersion(STS_API_VERSION);
            request.setMethod(MethodType.POST);
            request.setProtocol(protocolType);

            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy);
            request.setDurationSeconds(durationSeconds);

            // 发起请求，并得到response
            final AssumeRoleResponse response = client.getAcsResponse(request);

            return response;
        } catch (ClientException e) {
            throw e;
        }
    }

    @GetMapping("/oss/getToken")
    public OssToken publicService() {

        // 只有 RAM用户（子账号）才能调用 AssumeRole 接口
        // 阿里云主账号的AccessKeys不能用于发起AssumeRole请求
        // 请首先在RAM控制台创建一个RAM用户，并为这个用户创建AccessKeys
        String accessKeyId = ossConfig.getAccessKeyID();
        String accessKeySecret = ossConfig.getAccessKeySecret();

        // RoleArn 需要在 RAM 控制台上获取
        String roleArn = ossConfig.getRoleArn();
        long durationSeconds = ossConfig.getTokenExpireTime();
        String policy = ossConfig.getPolicy();
        // RoleSessionName 是临时Token的会话名称，自己指定用于标识你的用户，主要用于审计，或者用于区分Token颁发给谁
        // 但是注意RoleSessionName的长度和规则，不要有空格，只能有'-' '_' 字母和数字等字符
        // 具体规则请参考API文档中的格式要求
        String roleSessionName = "ding-001";

        // 此处必须为 HTTPS
        ProtocolType protocolType = ProtocolType.HTTPS;

        OssToken ot = new OssToken();

        try {
            final AssumeRoleResponse stsResponse = assumeRole(accessKeyId, accessKeySecret, roleArn, roleSessionName,
                    policy, protocolType, durationSeconds);

            ot.setCode(200);
            ot.setAccessKeyID(stsResponse.getCredentials().getAccessKeyId());
            ot.setAccessKeySecret(stsResponse.getCredentials().getAccessKeySecret());
            ot.setToken(stsResponse.getCredentials().getSecurityToken());
            ot.setExpiration(stsResponse.getCredentials().getExpiration());

        } catch (ClientException e) {
            ot.setCode(Integer.parseInt(e.getErrCode()));
            ot.setAccessKeyID("");
            ot.setAccessKeySecret("");
            ot.setToken("");
            ot.setExpiration("");
        }

        return ot;
    }

    //注意 osscallback参数中""会置为null
    @PostMapping("/oss/callback")
    public void authorize(@RequestBody OssCallback ossInfo,
                            HttpServletResponse response) {
        stockService.uploadStock(ossInfo);
    }
}
