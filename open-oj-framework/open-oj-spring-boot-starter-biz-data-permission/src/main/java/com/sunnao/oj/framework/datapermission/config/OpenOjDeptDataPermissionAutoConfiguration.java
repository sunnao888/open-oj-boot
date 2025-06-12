package com.sunnao.oj.framework.datapermission.config;

import com.sunnao.oj.framework.common.biz.system.permission.PermissionCommonApi;
import com.sunnao.oj.framework.datapermission.core.rule.dept.DeptDataPermissionRule;
import com.sunnao.oj.framework.datapermission.core.rule.dept.DeptDataPermissionRuleCustomizer;
import com.sunnao.oj.framework.security.core.LoginUser;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * 基于部门的数据权限 AutoConfiguration
 *
 * @author sunnao
 */
@AutoConfiguration
@ConditionalOnClass(LoginUser.class)
@ConditionalOnBean(value = {PermissionCommonApi.class, DeptDataPermissionRuleCustomizer.class})
public class OpenOjDeptDataPermissionAutoConfiguration {

    @Bean
    public DeptDataPermissionRule deptDataPermissionRule(PermissionCommonApi permissionApi,
                                                         List<DeptDataPermissionRuleCustomizer> customizers) {
        // 创建 DeptDataPermissionRule 对象
        DeptDataPermissionRule rule = new DeptDataPermissionRule(permissionApi);
        // 补全表配置
        customizers.forEach(customizer -> customizer.customize(rule));
        return rule;
    }

}
