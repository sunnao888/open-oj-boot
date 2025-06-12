package com.sunnao.oj.module.biz.controller.admin.linkquestiontag.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 题目标签关联新增/修改 Request VO")
@Data
public class LinkQuestionTagSaveReqVO {

    @Schema(description = "题目编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "题目编号不能为空")
    private Long questionId;

    @Schema(description = "标签编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "标签编号不能为空")
    private Long tagId;

}