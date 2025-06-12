package com.sunnao.oj.module.biz.dal.mysql.tag;

import com.sunnao.oj.framework.common.pojo.PageResult;
import com.sunnao.oj.framework.mybatis.core.mapper.BaseMapperX;
import com.sunnao.oj.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.sunnao.oj.module.biz.controller.admin.tag.vo.TagPageReqVO;
import com.sunnao.oj.module.biz.dal.dataobject.tag.TagDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标签 Mapper
 *
 * @author sunnao
 */
@Mapper
public interface TagMapper extends BaseMapperX<TagDO> {

    default PageResult<TagDO> selectPage(TagPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TagDO>()
                .betweenIfPresent(TagDO::getCreateTime, reqVO.getCreateTime())
                .likeIfPresent(TagDO::getName, reqVO.getName())
                .orderByDesc(TagDO::getId));
    }

}