package com.sunnao.oj.framework.tracer.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * Metrics 配置类
 *
 * @author sunnao
 */
@AutoConfiguration
@ConditionalOnClass({MeterRegistryCustomizer.class})
@ConditionalOnProperty(prefix = "open-oj.metrics", value = "enable", matchIfMissing = true) // 允许使用 open-oj.metrics.enable=false 禁用 Metrics
public class OpenOjMetricsAutoConfiguration {

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags(
            @Value("${spring.application.name}") String applicationName) {
        return registry -> registry.config().commonTags("application", applicationName);
    }

}
