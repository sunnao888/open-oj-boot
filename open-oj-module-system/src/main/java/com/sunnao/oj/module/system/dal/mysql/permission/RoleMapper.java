package com.sunnao.oj.module.system.dal.mysql.permission;

import com.sunnao.oj.framework.common.pojo.PageResult;
import com.sunnao.oj.framework.mybatis.core.dataobject.BaseDO;
import com.sunnao.oj.framework.mybatis.core.mapper.BaseMapperX;
import com.sunnao.oj.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.sunnao.oj.module.system.controller.admin.permission.vo.role.RolePageReqVO;
import com.sunnao.oj.module.system.dal.dataobject.permission.RoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapperX<RoleDO> {

    default PageResult<RoleDO> selectPage(RolePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoleDO>()
                .likeIfPresent(RoleDO::getName, reqVO.getName())
                .likeIfPresent(RoleDO::getCode, reqVO.getCode())
                .eqIfPresent(RoleDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(BaseDO::getCreateTime, reqVO.getCreateTime())
                .orderByAsc(RoleDO::getSort));
    }

    default RoleDO selectByName(String name) {
        return selectOne(RoleDO::getName, name);
    }

    default RoleDO selectByCode(String code) {
        return selectOne(RoleDO::getCode, code);
    }

    default List<RoleDO> selectListByStatus(@Nullable Collection<Integer> statuses) {
        return selectList(RoleDO::getStatus, statuses);
    }

}
