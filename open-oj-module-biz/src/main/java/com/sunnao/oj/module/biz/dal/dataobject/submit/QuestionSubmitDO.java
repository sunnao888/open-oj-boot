package com.sunnao.oj.module.biz.dal.dataobject.submit;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sunnao.oj.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

/**
 * 题目提交记录 DO
 *
 * @author sunnao
 */
@TableName("biz_question_submit")
@KeySequence("biz_question_submit_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionSubmitDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 题目id
     */
    private Long questionId;
    /**
     * 编程语言
     */
    private String language;
    /**
     * 用户代码
     */
    private String code;
    /**
     * 判题信息
     */
    private String judgeInfo;
    /**
     * 判题状态
     *
     * 枚举 {@link TODO biz_judge_status 对应的类}
     */
    private Integer status;


}