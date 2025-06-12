package com.sunnao.oj.module.biz.dal.dataobject.tag;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sunnao.oj.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

/**
 * 标签 DO
 *
 * @author sunnao
 */
@TableName("biz_tag")
@KeySequence("biz_tag_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 标签名
     */
    private String name;


}