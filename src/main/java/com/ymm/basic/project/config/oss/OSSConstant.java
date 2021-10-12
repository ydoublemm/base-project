package com.ymm.basic.project.config.oss;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "file.oss")
@Data
public class OSSConstant {

    /**节点*/

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    /**
     * 面向公网的地址头,还需加上你文件地址
     */
    private String accessPath;

}
