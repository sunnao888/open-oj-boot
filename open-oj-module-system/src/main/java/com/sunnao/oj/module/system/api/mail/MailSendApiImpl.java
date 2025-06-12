package com.sunnao.oj.module.system.api.mail;

import com.sunnao.oj.module.system.api.mail.dto.MailSendSingleToUserReqDTO;
import com.sunnao.oj.module.system.service.mail.MailSendService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 邮件发送 API 实现类
 *
 * @author wangjingyi
 */
@Service
@Validated
public class MailSendApiImpl implements MailSendApi {

    @Resource
    private MailSendService mailSendService;

    @Override
    public Long sendSingleMailToAdmin(MailSendSingleToUserReqDTO reqDTO) {
        return mailSendService.sendSingleMailToAdmin(reqDTO.getMail(), reqDTO.getUserId(),
                reqDTO.getTemplateCode(), reqDTO.getTemplateParams());
    }

    @Override
    public Long sendSingleMailToMember(MailSendSingleToUserReqDTO reqDTO) {
        return mailSendService.sendSingleMailToMember(reqDTO.getMail(), reqDTO.getUserId(),
                reqDTO.getTemplateCode(), reqDTO.getTemplateParams());
    }

}
