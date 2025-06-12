package com.sunnao.oj.module.system.api.permission;

import com.sunnao.oj.module.system.service.permission.RoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * 角色 API 实现类
 *
 * @author sunnao
 */
@Service
public class RoleApiImpl implements RoleApi {

    @Resource
    private RoleService roleService;

    @Override
    public void validRoleList(Collection<Long> ids) {
        roleService.validateRoleList(ids);
    }
}
