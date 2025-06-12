package com.sunnao.oj.module.system.api.sms;

import com.sunnao.oj.module.system.api.sms.dto.send.SmsSendSingleToUserReqDTO;
import com.sunnao.oj.module.system.service.sms.SmsSendService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 短信发送 API 接口
 *
 * @author sunnao
 */
@Service
@Validated
public class SmsSendApiImpl implements SmsSendApi {

    @Resource
    private SmsSendService smsSendService;

    @Override
    public Long sendSingleSmsToAdmin(SmsSendSingleToUserReqDTO reqDTO) {
        return smsSendService.sendSingleSmsToAdmin(reqDTO.getMobile(), reqDTO.getUserId(),
                reqDTO.getTemplateCode(), reqDTO.getTemplateParams());
    }

    @Override
    public Long sendSingleSmsToMember(SmsSendSingleToUserReqDTO reqDTO) {
        return smsSendService.sendSingleSmsToMember(reqDTO.getMobile(), reqDTO.getUserId(),
                reqDTO.getTemplateCode(), reqDTO.getTemplateParams());
    }

}
