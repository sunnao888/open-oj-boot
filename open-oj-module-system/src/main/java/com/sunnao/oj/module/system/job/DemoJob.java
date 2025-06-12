package com.sunnao.oj.module.system.job;

import com.sunnao.oj.framework.quartz.core.handler.JobHandler;
import com.sunnao.oj.module.system.dal.dataobject.user.AdminUserDO;
import com.sunnao.oj.module.system.dal.mysql.user.AdminUserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DemoJob implements JobHandler {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public String execute(String param) {
        List<AdminUserDO> users = adminUserMapper.selectList();
        return "用户数量：" + users.size();
    }

}
