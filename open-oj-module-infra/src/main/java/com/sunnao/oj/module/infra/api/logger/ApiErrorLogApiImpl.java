package com.sunnao.oj.module.infra.api.logger;

import com.sunnao.oj.framework.common.biz.infra.logger.ApiErrorLogCommonApi;
import com.sunnao.oj.framework.common.biz.infra.logger.dto.ApiErrorLogCreateReqDTO;
import com.sunnao.oj.module.infra.service.logger.ApiErrorLogService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * API 访问日志的 API 接口
 *
 * @author sunnao
 */
@Service
@Validated
public class ApiErrorLogApiImpl implements ApiErrorLogCommonApi {

    @Resource
    private ApiErrorLogService apiErrorLogService;

    @Override
    public void createApiErrorLog(ApiErrorLogCreateReqDTO createDTO) {
        apiErrorLogService.createApiErrorLog(createDTO);
    }

}
