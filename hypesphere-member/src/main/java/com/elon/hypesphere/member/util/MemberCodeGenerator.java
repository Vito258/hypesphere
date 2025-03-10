import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public static void main(String[] args) {
    FastAutoGenerator.create("jdbc:mysql://192.168.56.10:3306/hypesphere_ums?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8", "root", "root")
            .globalConfig(builder -> {
                builder.author("elon") // 设置作者
                        .enableSwagger() // 开启 swagger 模式
                        .outputDir("E://Output//member"); // 指定输出目录
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
                    builder.parent("com.elon.hypesphere.member") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E://Output//member")) // 设置mapperXml生成路径
            )
            .strategyConfig(builder ->
                    builder.addInclude("^ums_.*","^undo_.*") // 设置需要生成的表名
                            .addTablePrefix("ums_","undo_") // 设置过滤表前缀
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
