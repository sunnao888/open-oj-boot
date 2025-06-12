package com.sunnao.oj.module.biz.controller.admin.tag.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 标签 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TagRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "8896")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "标签名", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("标签名")
    private String name;

}