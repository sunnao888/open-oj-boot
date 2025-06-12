package com.sunnao.oj.module.infra.framework.file.config;

import com.sunnao.oj.module.infra.framework.file.core.client.FileClientFactory;
import com.sunnao.oj.module.infra.framework.file.core.client.FileClientFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件配置类
 *
 * @author sunnao
 */
@Configuration(proxyBeanMethods = false)
public class OpenOjFileAutoConfiguration {

    @Bean
    public FileClientFactory fileClientFactory() {
        return new FileClientFactoryImpl();
    }

}
