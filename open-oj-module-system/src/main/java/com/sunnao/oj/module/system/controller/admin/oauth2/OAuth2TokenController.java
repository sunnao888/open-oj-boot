package com.sunnao.oj.module.system.controller.admin.oauth2;

import com.sunnao.oj.framework.common.pojo.CommonResult;
import com.sunnao.oj.framework.common.pojo.PageResult;
import com.sunnao.oj.framework.common.util.object.BeanUtils;
import com.sunnao.oj.module.system.controller.admin.oauth2.vo.token.OAuth2AccessTokenPageReqVO;
import com.sunnao.oj.module.system.controller.admin.oauth2.vo.token.OAuth2AccessTokenRespVO;
import com.sunnao.oj.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import com.sunnao.oj.module.system.enums.logger.LoginLogTypeEnum;
import com.sunnao.oj.module.system.service.auth.AdminAuthService;
import com.sunnao.oj.module.system.service.oauth2.OAuth2TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.sunnao.oj.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - OAuth2.0 令牌")
@RestController
@RequestMapping("/system/oauth2-token")
public class OAuth2TokenController {

    @Resource
    private OAuth2TokenService oauth2TokenService;
    @Resource
    private AdminAuthService authService;

    @GetMapping("/page")
    @Operation(summary = "获得访问令牌分页", description = "只返回有效期内的")
    @PreAuthorize("@ss.hasPermission('system:oauth2-token:page')")
    public CommonResult<PageResult<OAuth2AccessTokenRespVO>> getAccessTokenPage(@Valid OAuth2AccessTokenPageReqVO reqVO) {
        PageResult<OAuth2AccessTokenDO> pageResult = oauth2TokenService.getAccessTokenPage(reqVO);
        return success(BeanUtils.toBean(pageResult, OAuth2AccessTokenRespVO.class));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除访问令牌")
    @Parameter(name = "accessToken", description = "访问令牌", required = true, example = "tudou")
    @PreAuthorize("@ss.hasPermission('system:oauth2-token:delete')")
    public CommonResult<Boolean> deleteAccessToken(@RequestParam("accessToken") String accessToken) {
        authService.logout(accessToken, LoginLogTypeEnum.LOGOUT_DELETE.getType());
        return success(true);
    }

}
