package com.sunnao.oj.module.biz.service.submit;

import com.sunnao.oj.framework.common.pojo.PageResult;
import com.sunnao.oj.module.biz.controller.admin.submit.vo.QuestionSubmitPageReqVO;
import com.sunnao.oj.module.biz.controller.admin.submit.vo.QuestionSubmitSaveReqVO;
import com.sunnao.oj.module.biz.dal.dataobject.submit.QuestionSubmitDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 题目提交记录 Service 接口
 *
 * @author sunnao
 */
public interface QuestionSubmitService {

    /**
     * 创建题目提交记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createQuestionSubmit(@Valid QuestionSubmitSaveReqVO createReqVO);

    /**
     * 更新题目提交记录
     *
     * @param updateReqVO 更新信息
     */
    void updateQuestionSubmit(@Valid QuestionSubmitSaveReqVO updateReqVO);

    /**
     * 删除题目提交记录
     *
     * @param id 编号
     */
    void deleteQuestionSubmit(Long id);

    /**
    * 批量删除题目提交记录
    *
    * @param ids 编号
    */
    void deleteQuestionSubmitListByIds(List<Long> ids);

    /**
     * 获得题目提交记录
     *
     * @param id 编号
     * @return 题目提交记录
     */
    QuestionSubmitDO getQuestionSubmit(Long id);

    /**
     * 获得题目提交记录分页
     *
     * @param pageReqVO 分页查询
     * @return 题目提交记录分页
     */
    PageResult<QuestionSubmitDO> getQuestionSubmitPage(QuestionSubmitPageReqVO pageReqVO);

}