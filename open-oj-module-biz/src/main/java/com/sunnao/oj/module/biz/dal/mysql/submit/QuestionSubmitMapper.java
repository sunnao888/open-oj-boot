package com.sunnao.oj.module.biz.dal.mysql.submit;

import com.sunnao.oj.framework.common.pojo.PageResult;
import com.sunnao.oj.framework.mybatis.core.mapper.BaseMapperX;
import com.sunnao.oj.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.sunnao.oj.module.biz.controller.admin.submit.vo.QuestionSubmitPageReqVO;
import com.sunnao.oj.module.biz.dal.dataobject.submit.QuestionSubmitDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 题目提交记录 Mapper
 *
 * @author sunnao
 */
@Mapper
public interface QuestionSubmitMapper extends BaseMapperX<QuestionSubmitDO> {

    default PageResult<QuestionSubmitDO> selectPage(QuestionSubmitPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<QuestionSubmitDO>()
                .betweenIfPresent(QuestionSubmitDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(QuestionSubmitDO::getLanguage, reqVO.getLanguage())
                .eqIfPresent(QuestionSubmitDO::getStatus, reqVO.getStatus())
                .orderByDesc(QuestionSubmitDO::getId));
    }

}