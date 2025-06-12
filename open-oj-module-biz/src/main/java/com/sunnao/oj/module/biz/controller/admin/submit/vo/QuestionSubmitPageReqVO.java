package com.sunnao.oj.module.biz.controller.admin.submit.vo;

import com.sunnao.oj.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.sunnao.oj.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 题目提交记录分页 Request VO")
@Data
public class QuestionSubmitPageReqVO extends PageParam {

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "编程语言")
    private String language;

    @Schema(description = "判题状态", example = "1")
    private Integer status;

}