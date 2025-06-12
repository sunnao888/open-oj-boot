package com.sunnao.oj.module.biz.controller.admin.submit.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 题目提交记录新增/修改 Request VO")
@Data
public class QuestionSubmitSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "30941")
    private Long id;

    @Schema(description = "题目id", requiredMode = Schema.RequiredMode.REQUIRED, example = "5660")
    @NotNull(message = "题目id不能为空")
    private Long questionId;

}