package com.sunnao.oj.module.biz.dal.dataobject.linkquestiontag;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sunnao.oj.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

/**
 * 题目标签关联 DO
 *
 * @author sunnao
 */
@TableName("biz_link_question_tag")
@KeySequence("biz_link_question_tag_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkQuestionTagDO extends BaseDO {

    /**
     * 题目id
     */
    @TableId
    private Long questionId;
    /**
     * 标签id
     */
    private Long tagId;

}