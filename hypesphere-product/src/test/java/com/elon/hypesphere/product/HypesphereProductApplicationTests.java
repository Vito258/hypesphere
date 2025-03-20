package com.elon.hypesphere.product;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Types;
import java.util.Collections;

@SpringBootTest
//@RunWith(SpringRunner.class)  // JUnit 4 需要此注解
public class HypesphereProductApplicationTests {
    @Test
    public void contextLoads() {
    }

    @Test
    public void ProductCodeGenerator() {
        FastAutoGenerator.create("jdbc:mysql://192.168.56.10:3306/hypesphere_pms?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8", "root", "root")
                .globalConfig(builder -> {
                    builder.author("elon") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir("E://Output//product"); // 指定输出目录
                })
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .packageConfig(builder ->
                        builder.parent("com.elon.hypesphere.product") // 设置父包名
                                .pathInfo(Collections.singletonMap(OutputFile.xml, "E://Output//product")) // 设置mapperXml生成路径
                )
                .strategyConfig(builder ->
                        builder.addInclude("^pms_.*", "^undo_.*") // 设置需要生成的表名
                                .addTablePrefix("pms_", "undo_") // 设置过滤表前缀
                                .entityBuilder()
                                .enableLombok() // 启用 Lombok
                                .enableTableFieldAnnotation() // 启用字段注解
                                .controllerBuilder()
                                .enableHyphenStyle() // 驼峰转连字符
                                .enableRestStyle()  // 生成@RestController
                )
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    // Oss云存储 原生使用上传文件
//    @Test
//    public void uploadFileTest() throws Exception {
//        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
//        String endpoint = "oss-cn-qingdao.aliyuncs.com";
//        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
//        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
//        // 填写Bucket名称，例如examplebucket。
//        String bucketName = "hypesphere";
//        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
//        String objectName = "exampledir/liming1.jpg";
//        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
//        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
//        String filePath = "E:\\MyData\\头像\\liming1.jpg";
//        // 填写Bucket所在地域。以华东1（杭州）为例，Region填写为cn-hangzhou。
//        String region = "cn-qingdao";
//
//        // 创建OSSClient实例。
//        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
//        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
//        OSS ossClient = OSSClientBuilder.create()
//                .endpoint(endpoint)
//                .credentialsProvider(credentialsProvider)
//                .clientConfiguration(clientBuilderConfiguration)
//                .region(region)
//                .build();
//
//        try {
//            // 创建PutObjectRequest对象。
//            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(filePath));
//            // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
//            // ObjectMetadata metadata = new ObjectMetadata();
//            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
//            // metadata.setObjectAcl(CannedAccessControlList.Private);
//            // putObjectRequest.setMetadata(metadata);
//
//            // 上传文件。
//            PutObjectResult result = ossClient.putObject(putObjectRequest);
//        } catch (OSSException oe) {
//            System.out.println("Caught an OSSException, which means your request made it to OSS, "
//                    + "but was rejected with an error response for some reason.");
//            System.out.println("Error Message:" + oe.getErrorMessage());
//            System.out.println("Error Code:" + oe.getErrorCode());
//            System.out.println("Request ID:" + oe.getRequestId());
//            System.out.println("Host ID:" + oe.getHostId());
//        } catch (ClientException ce) {
//            System.out.println("Caught an ClientException, which means the client encountered "
//                    + "a serious internal problem while trying to communicate with OSS, "
//                    + "such as not being able to access the network.");
//            System.out.println("Error Message:" + ce.getMessage());
//        } finally {
//            if (ossClient != null) {
//                ossClient.shutdown();
//                System.out.println("上传完成");
//            }
//        }
//    }

    // 使用注入的OssCLient上传文件
//    @Test
//    public void uploadFileTestByOSSClient() throws Exception {
//        // Client 配置
//        String bucketName = "hypesphere";
//        String objectName = "exampledir/liming2.jpg";
//        String filePath = "E:\\MyData\\头像\\liming2.jpg";
//
//        // 创建PutObjectRequest对象。
//        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(filePath));
//        // download file to local
//        oss.putObject(putObjectRequest);
//    }

}
