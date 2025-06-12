package com.sunnao.oj.module.biz.dal.dataobject.question;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sunnao.oj.framework.mybatis.core.dataobject.BaseDO;
import com.sunnao.oj.module.biz.dal.dataobject.tag.TagDO;
import lombok.*;

import javax.swing.text.html.HTML;
import java.util.List;

/**
 * 题目 DO
 *
 * @author sunnao
 */
@TableName("biz_question")
@KeySequence("biz_question_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 答案
     */
    private String answer;
    /**
     * 题目提交数
     */
    private Integer submitNum;
    /**
     * 题目通过数
     */
    private Integer acceptNum;
    /**
     * 判题用例(json数组)
     */
    private String judgeCase;
    /**
     * 判题配置(json对象)
     */
    private String judgeConfig;
    /**
     * 点赞数
     */
    private Integer thumbNum;
    /**
     * 收藏数
     */
    private Integer favourNum;

    /**
     * 标签列表
     */
    private List<TagDO> tagList;

}