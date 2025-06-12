package com.sunnao.oj.module.biz.service.question;

import cn.hutool.core.collection.CollUtil;
import com.sunnao.oj.framework.common.pojo.PageResult;
import com.sunnao.oj.framework.common.util.object.BeanUtils;
import com.sunnao.oj.module.biz.controller.admin.question.vo.QuestionPageReqVO;
import com.sunnao.oj.module.biz.controller.admin.question.vo.QuestionSaveReqVO;
import com.sunnao.oj.module.biz.dal.dataobject.question.QuestionDO;
import com.sunnao.oj.module.biz.dal.mysql.question.QuestionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static com.sunnao.oj.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.sunnao.oj.module.biz.enums.ErrorCodeConstants.QUESTION_NOT_EXISTS;

/**
 * 题目 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public Long createQuestion(QuestionSaveReqVO createReqVO) {
        // 插入
        QuestionDO question = BeanUtils.toBean(createReqVO, QuestionDO.class);
        questionMapper.insert(question);
        // 返回
        return question.getId();
    }

    @Override
    public void updateQuestion(QuestionSaveReqVO updateReqVO) {
        // 校验存在
        validateQuestionExists(updateReqVO.getId());
        // 更新
        QuestionDO updateObj = BeanUtils.toBean(updateReqVO, QuestionDO.class);
        questionMapper.updateById(updateObj);
    }

    @Override
    public void deleteQuestion(Long id) {
        // 校验存在
        validateQuestionExists(id);
        // 删除
        questionMapper.deleteById(id);
    }

    @Override
        public void deleteQuestionListByIds(List<Long> ids) {
        // 校验存在
        validateQuestionExists(ids);
        // 删除
        questionMapper.deleteByIds(ids);
        }

    private void validateQuestionExists(List<Long> ids) {
        List<QuestionDO> list = questionMapper.selectByIds(ids);
        if (CollUtil.isEmpty(list) || list.size() != ids.size()) {
            throw exception(QUESTION_NOT_EXISTS);
        }
    }

    private void validateQuestionExists(Long id) {
        if (questionMapper.selectById(id) == null) {
            throw exception(QUESTION_NOT_EXISTS);
        }
    }

    @Override
    public QuestionDO getQuestion(Long id) {
        return questionMapper.selectById(id);
    }

    @Override
    public PageResult<QuestionDO> getQuestionPage(QuestionPageReqVO pageReqVO) {
        return questionMapper.selectPage(pageReqVO);
    }

}