package com.wqb.website;

import com.wqb.website.supports.property.CloudAccountProperties;
import com.wqb.website.supports.property.SmsProviderProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableCaching
@MapperScan("com.wqb.website.mappers")
@SpringBootApplication(scanBasePackages = {"com.wqb"})
@EnableConfigurationProperties({CloudAccountProperties.class, SmsProviderProperties.class})
@EnableAspectJAutoProxy(exposeProxy = true)
public class WqbWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(WqbWebsiteApplication.class, args);
    }

}

