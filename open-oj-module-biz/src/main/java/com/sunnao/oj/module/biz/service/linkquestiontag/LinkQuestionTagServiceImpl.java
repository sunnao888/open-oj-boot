package com.sunnao.oj.module.biz.service.linkquestiontag;

import cn.hutool.core.collection.CollUtil;
import com.sunnao.oj.framework.common.util.object.BeanUtils;
import com.sunnao.oj.module.biz.controller.admin.linkquestiontag.vo.LinkQuestionTagSaveReqVO;
import com.sunnao.oj.module.biz.dal.dataobject.linkquestiontag.LinkQuestionTagDO;
import com.sunnao.oj.module.biz.dal.mysql.linkquestiontag.LinkQuestionTagMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static com.sunnao.oj.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.sunnao.oj.module.biz.enums.ErrorCodeConstants.LINK_QUESTION_TAG_NOT_EXISTS;

/**
 * 题目标签关联 Service 实现类
 *
 * @author sunnao
 */
@Service
@Validated
public class LinkQuestionTagServiceImpl implements LinkQuestionTagService {

    @Resource
    private LinkQuestionTagMapper linkQuestionTagMapper;

    @Override
    public Long createLinkQuestionTag(LinkQuestionTagSaveReqVO createReqVO) {
        // 插入
        LinkQuestionTagDO linkQuestionTag = BeanUtils.toBean(createReqVO, LinkQuestionTagDO.class);
        linkQuestionTagMapper.insert(linkQuestionTag);
        // 返回
        return linkQuestionTag.getQuestionId();
    }

    @Override
    public void updateLinkQuestionTag(LinkQuestionTagSaveReqVO updateReqVO) {
        // 校验存在
        validateLinkQuestionTagExists(updateReqVO.getQuestionId());
        // 更新
        LinkQuestionTagDO updateObj = BeanUtils.toBean(updateReqVO, LinkQuestionTagDO.class);
        linkQuestionTagMapper.updateById(updateObj);
    }

    @Override
    public void deleteLinkQuestionTag(Long id) {
        // 校验存在
        validateLinkQuestionTagExists(id);
        // 删除
        linkQuestionTagMapper.deleteById(id);
    }

    @Override
    public void deleteLinkQuestionTagListByIds(List<Long> ids) {
        // 校验存在
        validateLinkQuestionTagExists(ids);
        // 删除
        linkQuestionTagMapper.deleteByIds(ids);
    }

    private void validateLinkQuestionTagExists(List<Long> ids) {
        List<LinkQuestionTagDO> list = linkQuestionTagMapper.selectByIds(ids);
        if (CollUtil.isEmpty(list) || list.size() != ids.size()) {
            throw exception(LINK_QUESTION_TAG_NOT_EXISTS);
        }
    }

    private void validateLinkQuestionTagExists(Long id) {
        if (linkQuestionTagMapper.selectById(id) == null) {
            throw exception(LINK_QUESTION_TAG_NOT_EXISTS);
        }
    }

    @Override
    public LinkQuestionTagDO getLinkQuestionTag(Long id) {
        return linkQuestionTagMapper.selectById(id);
    }

}