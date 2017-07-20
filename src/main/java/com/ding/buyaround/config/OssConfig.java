package com.ding.buyaround.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by pc on 2017/6/26.
 */
@Component
@ConfigurationProperties(prefix = "oss")
public class OssConfig {
    private String accessKeyID;
    private String accessKeySecret;
    private String roleArn;
    private long tokenExpireTime;
    private String policy;

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public void setAccessKeyID(String accessKeyID) {
        this.accessKeyID = accessKeyID;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public void setRoleArn(String roleArn) {
        this.roleArn = roleArn;
    }

    public void setTokenExpireTime(long tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }

    public String getAccessKeyID() {
        return accessKeyID;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public String getRoleArn() {
        return roleArn;
    }

    public long getTokenExpireTime() {
        return tokenExpireTime;
    }
}
