package com.ymm.basic.project.config.oss;


import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云oss装配
 */
//@Configuration
@Slf4j
public class OSSConfig {


    @Autowired
    private  OSSConstant ossConstant;

    @Bean
    public OSS getOSSClient() {

        // 创建ClientConfiguration实例，按照您的需要修改默认参数。
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        // 关闭CNAME选项。
        conf.setSupportCname(false);

        // 创建OSSClient实例。
        OSS oss = new OSSClientBuilder().build(ossConstant.getEndpoint(), ossConstant.getAccessKeyId(), ossConstant.getAccessKeySecret(), conf);
        //设置为公共读
        oss.setBucketAcl(ossConstant.getBucketName(), CannedAccessControlList.PublicRead);

        log.info("【装配OSS配置到Spring IOC 容器】");
        return oss;

    }
}
