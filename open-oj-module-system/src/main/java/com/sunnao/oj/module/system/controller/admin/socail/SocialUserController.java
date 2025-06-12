package com.sunnao.oj.module.system.controller.admin.socail;

import com.sunnao.oj.framework.common.enums.UserTypeEnum;
import com.sunnao.oj.framework.common.pojo.CommonResult;
import com.sunnao.oj.framework.common.pojo.PageResult;
import com.sunnao.oj.framework.common.util.object.BeanUtils;
import com.sunnao.oj.module.system.api.social.dto.SocialUserBindReqDTO;
import com.sunnao.oj.module.system.controller.admin.socail.vo.user.SocialUserBindReqVO;
import com.sunnao.oj.module.system.controller.admin.socail.vo.user.SocialUserPageReqVO;
import com.sunnao.oj.module.system.controller.admin.socail.vo.user.SocialUserRespVO;
import com.sunnao.oj.module.system.controller.admin.socail.vo.user.SocialUserUnbindReqVO;
import com.sunnao.oj.module.system.dal.dataobject.social.SocialUserDO;
import com.sunnao.oj.module.system.service.social.SocialUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sunnao.oj.framework.common.pojo.CommonResult.success;
import static com.sunnao.oj.framework.common.util.collection.CollectionUtils.convertList;
import static com.sunnao.oj.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "管理后台 - 社交用户")
@RestController
@RequestMapping("/system/social-user")
@Validated
public class SocialUserController {

    @Resource
    private SocialUserService socialUserService;

    @PostMapping("/bind")
    @Operation(summary = "社交绑定，使用 code 授权码")
    public CommonResult<Boolean> socialBind(@RequestBody @Valid SocialUserBindReqVO reqVO) {
        socialUserService.bindSocialUser(BeanUtils.toBean(reqVO, SocialUserBindReqDTO.class)
                        .setUserId(getLoginUserId()).setUserType(UserTypeEnum.ADMIN.getValue()));
        return CommonResult.success(true);
    }

    @DeleteMapping("/unbind")
    @Operation(summary = "取消社交绑定")
    public CommonResult<Boolean> socialUnbind(@RequestBody SocialUserUnbindReqVO reqVO) {
        socialUserService.unbindSocialUser(getLoginUserId(), UserTypeEnum.ADMIN.getValue(), reqVO.getType(), reqVO.getOpenid());
        return CommonResult.success(true);
    }

    @GetMapping("/get-bind-list")
    @Operation(summary = "获得绑定社交用户列表")
    public CommonResult<List<SocialUserRespVO>> getBindSocialUserList() {
        List<SocialUserDO> list = socialUserService.getSocialUserList(getLoginUserId(), UserTypeEnum.ADMIN.getValue());
        return success(convertList(list, socialUser -> new SocialUserRespVO() // 返回精简信息
                .setId(socialUser.getId()).setType(socialUser.getType()).setOpenid(socialUser.getOpenid())
                .setNickname(socialUser.getNickname()).setAvatar(socialUser.getNickname())));
    }

    // ==================== 社交用户 CRUD ====================

    @GetMapping("/get")
    @Operation(summary = "获得社交用户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:social-user:query')")
    public CommonResult<SocialUserRespVO> getSocialUser(@RequestParam("id") Long id) {
        SocialUserDO socialUser = socialUserService.getSocialUser(id);
        return success(BeanUtils.toBean(socialUser, SocialUserRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得社交用户分页")
    @PreAuthorize("@ss.hasPermission('system:social-user:query')")
    public CommonResult<PageResult<SocialUserRespVO>> getSocialUserPage(@Valid SocialUserPageReqVO pageVO) {
        PageResult<SocialUserDO> pageResult = socialUserService.getSocialUserPage(pageVO);
        return success(BeanUtils.toBean(pageResult, SocialUserRespVO.class));
    }

}
