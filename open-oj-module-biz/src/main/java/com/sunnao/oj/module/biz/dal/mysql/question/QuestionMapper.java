package com.sunnao.oj.module.biz.dal.mysql.question;

import com.sunnao.oj.framework.common.pojo.PageResult;
import com.sunnao.oj.framework.mybatis.core.mapper.BaseMapperX;
import com.sunnao.oj.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.sunnao.oj.module.biz.controller.admin.question.vo.QuestionPageReqVO;
import com.sunnao.oj.module.biz.dal.dataobject.question.QuestionDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 题目 Mapper
 *
 * @author sunnao
 */
@Mapper
public interface QuestionMapper extends BaseMapperX<QuestionDO> {

    default PageResult<QuestionDO> selectPage(QuestionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<QuestionDO>()
                .likeIfPresent(QuestionDO::getTitle, reqVO.getTitle())
                .orderByDesc(QuestionDO::getId));
    }

}