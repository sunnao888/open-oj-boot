package com.sunnao.oj.module.biz.controller.admin.question.vo;

import com.sunnao.oj.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 题目分页 Request VO")
@Data
public class QuestionPageReqVO extends PageParam {

    @Schema(description = "标题")
    private String title;

}