package com.sunnao.oj.module.biz.service.linkquestiontag;

import com.sunnao.oj.module.biz.controller.admin.linkquestiontag.vo.LinkQuestionTagSaveReqVO;
import com.sunnao.oj.module.biz.dal.dataobject.linkquestiontag.LinkQuestionTagDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 题目标签关联 Service 接口
 *
 * @author sunnao
 */
public interface LinkQuestionTagService {

    /**
     * 创建题目标签关联
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createLinkQuestionTag(@Valid LinkQuestionTagSaveReqVO createReqVO);

    /**
     * 更新题目标签关联
     *
     * @param updateReqVO 更新信息
     */
    void updateLinkQuestionTag(@Valid LinkQuestionTagSaveReqVO updateReqVO);

    /**
     * 删除题目标签关联
     *
     * @param id 编号
     */
    void deleteLinkQuestionTag(Long id);

    /**
     * 批量删除题目标签关联
     *
     * @param ids 编号
     */
    void deleteLinkQuestionTagListByIds(List<Long> ids);

    /**
     * 获得题目标签关联
     *
     * @param id 编号
     * @return 题目标签关联
     */
    LinkQuestionTagDO getLinkQuestionTag(Long id);

}