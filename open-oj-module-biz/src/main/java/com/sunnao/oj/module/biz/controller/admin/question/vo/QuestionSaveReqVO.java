package com.sunnao.oj.module.biz.controller.admin.question.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 题目新增/修改 Request VO")
@Data
public class QuestionSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "标题不能为空")
    private String title;

    @Schema(description = "内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "内容不能为空")
    private String content;

    @Schema(description = "答案", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "答案不能为空")
    private String answer;

    @Schema(description = "判题用例(json数组)")
    private String judgeCase;

    @Schema(description = "判题配置(json对象)")
    private String judgeConfig;

    @Schema(description = "标签列表")
    private List<Long> tagIds;

}