package com.sunnao.oj.framework.tracer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * BizTracer配置类
 *
 * @author 麻薯
 */
@ConfigurationProperties("open-oj.tracer")
@Data
public class TracerProperties {
}
