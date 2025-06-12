package com.sunnao.oj.module.biz.service.question;

import cn.hutool.core.collection.CollUtil;
import com.sunnao.oj.framework.common.pojo.PageResult;
import com.sunnao.oj.framework.common.util.object.BeanUtils;
import com.sunnao.oj.module.biz.controller.admin.linkquestiontag.vo.LinkQuestionTagSaveReqVO;
import com.sunnao.oj.module.biz.controller.admin.question.vo.QuestionPageReqVO;
import com.sunnao.oj.module.biz.controller.admin.question.vo.QuestionSaveReqVO;
import com.sunnao.oj.module.biz.dal.dataobject.linkquestiontag.LinkQuestionTagDO;
import com.sunnao.oj.module.biz.dal.dataobject.question.QuestionDO;
import com.sunnao.oj.module.biz.dal.dataobject.tag.TagDO;
import com.sunnao.oj.module.biz.dal.mysql.question.QuestionMapper;
import com.sunnao.oj.module.biz.service.linkquestiontag.LinkQuestionTagService;
import com.sunnao.oj.module.biz.service.tag.TagService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
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

    @Resource
    private LinkQuestionTagService linkQuestionTagService;

    @Resource
    private TagService tagService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createQuestion(QuestionSaveReqVO createReqVO) {
        // 插入
        QuestionDO question = BeanUtils.toBean(createReqVO, QuestionDO.class);
        questionMapper.insert(question);

        // 处理标签
        List<Long> tagIds = createReqVO.getTagIds();
        if (CollUtil.isNotEmpty(tagIds)) {
            ArrayList<LinkQuestionTagSaveReqVO> linkQuestionTagDOS = new ArrayList<>();
            for (Long tagId : tagIds) {
                LinkQuestionTagSaveReqVO linkQuestionTagDO = new LinkQuestionTagSaveReqVO();
                linkQuestionTagDO.setQuestionId(question.getId());
                linkQuestionTagDO.setTagId(tagId);
                linkQuestionTagDOS.add(linkQuestionTagDO);
            }
            linkQuestionTagService.createBatchLinkQuestionTag(linkQuestionTagDOS);
        }

        // 返回
        return question.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateQuestion(QuestionSaveReqVO updateReqVO) {
        // 校验存在
        validateQuestionExists(updateReqVO.getId());
        // 更新
        QuestionDO updateObj = BeanUtils.toBean(updateReqVO, QuestionDO.class);

        // 根据问题id删除所有标签
        linkQuestionTagService.deleteLinkQuestionTag(updateObj.getId());
        // 重新添加标签
        List<Long> tagIds = updateReqVO.getTagIds();
        if (CollUtil.isNotEmpty(tagIds)) {
            ArrayList<LinkQuestionTagSaveReqVO> linkQuestionTagDOS = new ArrayList<>();
            for (Long tagId : tagIds) {
                LinkQuestionTagSaveReqVO linkQuestionTagDO = new LinkQuestionTagSaveReqVO();
                linkQuestionTagDO.setQuestionId(updateObj.getId());
                linkQuestionTagDO.setTagId(tagId);
                linkQuestionTagDOS.add(linkQuestionTagDO);
            }
            linkQuestionTagService.createBatchLinkQuestionTag(linkQuestionTagDOS);
        }
        questionMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteQuestion(Long id) {
        // 校验存在
        validateQuestionExists(id);
        // 删除
        questionMapper.deleteById(id);
        // 删除标签
        linkQuestionTagService.deleteLinkQuestionTag(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteQuestionListByIds(List<Long> ids) {
        // 校验存在
        validateQuestionExists(ids);
        // 删除
        questionMapper.deleteByIds(ids);
        // 删除标签
        linkQuestionTagService.deleteLinkQuestionTagListByIds(ids);
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
        QuestionDO questionDO = questionMapper.selectById(id);
        // 补充标签
        List<TagDO> tags = tagService.getTagListByQuestionId(id);
        questionDO.setTagList(tags);
        return questionDO;
    }

    @Override
    public PageResult<QuestionDO> getQuestionPage(QuestionPageReqVO pageReqVO) {
        PageResult<QuestionDO> page = questionMapper.selectPage(pageReqVO);
        // 补充标签
        for (QuestionDO questionDO : page.getList()) {
            List<TagDO> tags = tagService.getTagListByQuestionId(questionDO.getId());
            questionDO.setTagList(tags);
        }
        return page;
    }

}