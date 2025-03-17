package com.elon.hypesphere.thirdparty.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.sts20150401.models.AssumeRoleResponseBody;
import com.aliyun.tea.TeaException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.elon.hypesphere.common.utils.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/oss")
public class OssController {
    @Autowired
    private OSS ossClient;

    @Value("${alibaba.cloud.access-key}")
    private String accessId;

    @Value("${alibaba.cloud.secret-key}")
    private String accessSecret;

    @Value("${alibaba.cloud.oss.endpoint}")
    private String endpoint;

    /**
     *  获取临时访问凭证
     */
    @RequestMapping("/policy")
    public R policy() {
        // STS服务接入点，例如sts.cn-hangzhou.aliyuncs.com。您可以通过公网或者VPC接入STS服务。
        String endpoint = "sts.cn-qingdao.aliyuncs.com";
        // 从环境变量中获取步骤1.1生成的RAM用户的访问密钥（AccessKey ID和AccessKey Secret）。
        String accessKeyId = accessId;
        String accessKeySecret = accessSecret;
        // 从环境变量中获取步骤1.3生成的RAM角色的RamRoleArn。
        String roleArn = System.getenv("OSS_STS_ROLE_ARN");
        // 自定义角色会话名称，用来区分不同的令牌，例如可填写为SessionTest。
        String roleSessionName = "SessionTest";
        // 临时访问凭证将获得角色拥有的所有权限。
        String policy = null;
        // 临时访问凭证的有效时间，单位为秒。最小值为900，最大值以当前角色设定的最大会话时间为准。当前角色最大会话时间取值范围为3600秒~43200秒，默认值为3600秒。
        // 在上传大文件或者其他较耗时的使用场景中，建议合理设置临时访问凭证的有效时间，确保在完成目标任务前无需反复调用STS服务以获取临时访问凭证。
        Long durationSeconds = 3600L;
        try {
            // 获取当前时间
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = LocalDate.now().format(formatter);
            // 上传的文件目录
            String dir = date + "/";
            // 填写host
            String host = "https://" + "hypesphere" + "." + endpoint;
            // 发起STS请求所在的地域。建议保留默认值，默认值为空字符串（""）。
            String regionId = "";
            // 添加endpoint。适用于Java SDK 3.12.0及以上版本。
            DefaultProfile.addEndpoint(regionId, "Sts", endpoint);
            // 添加endpoint。适用于Java SDK 3.12.0以下版本。
            // DefaultProfile.addEndpoint("",regionId, "Sts", endpoint);
            // 构造default profile。
            IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
            // 构造client。
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            // 适用于Java SDK 3.12.0及以上版本。
            request.setSysMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy);
            request.setDurationSeconds(durationSeconds);
            final AssumeRoleResponse response = client.getAcsResponse(request);
            Map respMap = new LinkedHashMap<String, String>();
            respMap.put("expiration", response.getCredentials().getExpiration());
            respMap.put("securityToken", response.getCredentials().getSecurityToken());
            respMap.put("requestId", response.getRequestId());
            respMap.put("accessKeyId", response.getCredentials().getAccessKeyId());
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("accessKeySecret", response.getCredentials().getAccessKeySecret());
            respMap.put("region", "oss-cn-qingdao");
            respMap.put("bucket", "hypesphere");
            respMap.put("policy", policy);
            return R.ok().put("data", respMap);
        } catch (ClientException e) {
            //TODO 打印日志
        }
        return R.error("获取临时访问凭证失败");
    }

    @RequestMapping("/upload")
    public void uploadTest() {
        // OSS访问域名。以华东1（杭州）地域为例，填写为https://oss-cn-hangzhou.aliyuncs.com。其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-qingdao.aliyuncs.com";
        // 从环境变量中获取步骤1.5生成的临时访问密钥AccessKey ID和AccessKey Secret，非阿里云账号AccessKey ID和AccessKey Secret。
        String accessKeyId = System.getenv("ACCESS_KEY_ID");
        String accessKeySecret = System.getenv("ACCESS_KEY_SECRET");
        // 从环境变量中获取步骤1.5生成的安全令牌SecurityToken。
        String securityToken = System.getenv("STS_Token");

        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, securityToken);
        // 将本地文件exampletest.txt上传至examplebucket。
        PutObjectRequest putObjectRequest = new PutObjectRequest("examplebucket", "exampletest.txt", new File("D:\\localpath\\exampletest.txt"));

        // ObjectMetadata metadata = new ObjectMetadata();
        // 上传文件时设置存储类型。
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // 上传文件时设置读写权限ACL。
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        // putObjectRequest.setMetadata(metadata);

        try {
            // 上传文件。
            ossClient.putObject(putObjectRequest);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

}
