package com.sunnao.oj.module.biz.service.submit;

import cn.hutool.core.collection.CollUtil;
import com.sunnao.oj.framework.common.pojo.PageResult;
import com.sunnao.oj.framework.common.util.object.BeanUtils;
import com.sunnao.oj.module.biz.controller.admin.submit.vo.QuestionSubmitPageReqVO;
import com.sunnao.oj.module.biz.controller.admin.submit.vo.QuestionSubmitSaveReqVO;
import com.sunnao.oj.module.biz.dal.dataobject.submit.QuestionSubmitDO;
import com.sunnao.oj.module.biz.dal.mysql.submit.QuestionSubmitMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static com.sunnao.oj.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.sunnao.oj.module.biz.enums.ErrorCodeConstants.QUESTION_SUBMIT_NOT_EXISTS;

/**
 * 题目提交记录 Service 实现类
 *
 * @author sunnao
 */
@Service
@Validated
public class QuestionSubmitServiceImpl implements QuestionSubmitService {

    @Resource
    private QuestionSubmitMapper questionSubmitMapper;

    @Override
    public Long createQuestionSubmit(QuestionSubmitSaveReqVO createReqVO) {
        // 插入
        QuestionSubmitDO questionSubmit = BeanUtils.toBean(createReqVO, QuestionSubmitDO.class);
        questionSubmitMapper.insert(questionSubmit);
        // 返回
        return questionSubmit.getId();
    }

    @Override
    public void updateQuestionSubmit(QuestionSubmitSaveReqVO updateReqVO) {
        // 校验存在
        validateQuestionSubmitExists(updateReqVO.getId());
        // 更新
        QuestionSubmitDO updateObj = BeanUtils.toBean(updateReqVO, QuestionSubmitDO.class);
        questionSubmitMapper.updateById(updateObj);
    }

    @Override
    public void deleteQuestionSubmit(Long id) {
        // 校验存在
        validateQuestionSubmitExists(id);
        // 删除
        questionSubmitMapper.deleteById(id);
    }

    @Override
        public void deleteQuestionSubmitListByIds(List<Long> ids) {
        // 校验存在
        validateQuestionSubmitExists(ids);
        // 删除
        questionSubmitMapper.deleteByIds(ids);
        }

    private void validateQuestionSubmitExists(List<Long> ids) {
        List<QuestionSubmitDO> list = questionSubmitMapper.selectByIds(ids);
        if (CollUtil.isEmpty(list) || list.size() != ids.size()) {
            throw exception(QUESTION_SUBMIT_NOT_EXISTS);
        }
    }

    private void validateQuestionSubmitExists(Long id) {
        if (questionSubmitMapper.selectById(id) == null) {
            throw exception(QUESTION_SUBMIT_NOT_EXISTS);
        }
    }

    @Override
    public QuestionSubmitDO getQuestionSubmit(Long id) {
        return questionSubmitMapper.selectById(id);
    }

    @Override
    public PageResult<QuestionSubmitDO> getQuestionSubmitPage(QuestionSubmitPageReqVO pageReqVO) {
        return questionSubmitMapper.selectPage(pageReqVO);
    }

}