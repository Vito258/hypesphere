package com.elon.hypesphere.thirdparty.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云OSS配置,显式定义OssClient Bean
 *
 * @author elon
 * @since 2021/10/26
 */
@Configuration
public class OssConfig {
    @Value("${alibaba.cloud.oss.endpoint}")
    private String endpoint;

    @Value("${alibaba.cloud.access-key}")
    private String accessKeyId;

    @Value("${alibaba.cloud.secret-key}")
    private String accessKeySecret;

    @Bean
    public OSS ossClient() {
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }
}
