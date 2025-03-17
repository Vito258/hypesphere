package com.elon.hypesphere.thirdparty;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HypesphereThirdPartyApplicationTests {
	@Value("${alibaba.cloud.access-key}")
	private String ossAccessKeyId;

	@Autowired
	private OSSClient oss;

	@Test
    public void contextLoads() {
		System.out.println(ossAccessKeyId);
	}

	@Test
	public void uploadFileTestByOSSClient() throws Exception {
		// Client 配置
		String bucketName = "hypesphere";
		String objectName = "exampledir/liming2.jpg";
		String filePath = "E:\\MyData\\头像\\liming2.jpg";

		// 创建PutObjectRequest对象。
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(filePath));
		// download file to local
		oss.putObject(putObjectRequest);
	}

}
