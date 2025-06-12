package com.sunnao.oj.module.biz.controller.admin.question.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 题目 Response VO")
@Data
@ExcelIgnoreUnannotated
public class QuestionRespVO {

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("标题")
    private String title;

    @Schema(description = "题目提交数")
    @ExcelProperty("题目提交数")
    private Integer submitNum;

    @Schema(description = "题目通过数")
    @ExcelProperty("题目通过数")
    private Integer acceptNum;

    @Schema(description = "点赞数")
    @ExcelProperty("点赞数")
    private Integer thumbNum;

    @Schema(description = "收藏数")
    @ExcelProperty("收藏数")
    private Integer favourNum;

}