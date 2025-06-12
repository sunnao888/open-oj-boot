package com.sunnao.oj.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目的启动类
 *
 * @author sunnao
 */
@SpringBootApplication(scanBasePackages = {"${open-oj.info.base-package}.server", "${open-oj.info.base-package}.module"})
public class OpenOjServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenOjServerApplication.class, args);
    }

}
