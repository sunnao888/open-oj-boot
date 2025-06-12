package com.sunnao.oj.module.system.framework.web.config;

import com.sunnao.oj.framework.swagger.config.OpenOjSwaggerAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * system 模块的 web 组件的 Configuration
 *
 * @author sunnao
 */
@Configuration(proxyBeanMethods = false)
public class SystemWebConfiguration {

    /**
     * system 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi systemGroupedOpenApi() {
        return OpenOjSwaggerAutoConfiguration.buildGroupedOpenApi("system");
    }

}
