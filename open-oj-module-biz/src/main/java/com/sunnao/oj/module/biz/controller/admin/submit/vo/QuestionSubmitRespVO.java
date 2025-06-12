package com.sunnao.oj.module.biz.controller.admin.submit.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.sunnao.oj.framework.excel.core.annotations.DictFormat;
import com.sunnao.oj.framework.excel.core.convert.DictConvert;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 题目提交记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class QuestionSubmitRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "30941")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "题目id", requiredMode = Schema.RequiredMode.REQUIRED, example = "5660")
    @ExcelProperty("题目id")
    private Long questionId;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "编程语言", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("编程语言")
    private String language;

    @Schema(description = "用户代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("用户代码")
    private String code;

    @Schema(description = "判题信息")
    @ExcelProperty("判题信息")
    private String judgeInfo;

    @Schema(description = "判题状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty(value = "判题状态", converter = DictConvert.class)
    @DictFormat("biz_judge_status") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private Integer status;

}