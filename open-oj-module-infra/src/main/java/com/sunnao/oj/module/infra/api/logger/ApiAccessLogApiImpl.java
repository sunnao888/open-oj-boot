package com.sunnao.oj.module.infra.api.logger;

import com.sunnao.oj.framework.common.biz.infra.logger.ApiAccessLogCommonApi;
import com.sunnao.oj.framework.common.biz.infra.logger.dto.ApiAccessLogCreateReqDTO;
import com.sunnao.oj.module.infra.service.logger.ApiAccessLogService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * API 访问日志的 API 实现类
 *
 * @author sunnao
 */
@Service
@Validated
public class ApiAccessLogApiImpl implements ApiAccessLogCommonApi {

    @Resource
    private ApiAccessLogService apiAccessLogService;

    @Override
    public void createApiAccessLog(ApiAccessLogCreateReqDTO createDTO) {
        apiAccessLogService.createApiAccessLog(createDTO);
    }

}
