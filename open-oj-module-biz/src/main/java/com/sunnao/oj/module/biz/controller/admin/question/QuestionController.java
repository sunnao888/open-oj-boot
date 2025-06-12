package com.sunnao.oj.module.biz.controller.admin.question;

import com.sunnao.oj.framework.apilog.core.annotation.ApiAccessLog;
import com.sunnao.oj.framework.common.pojo.CommonResult;
import com.sunnao.oj.framework.common.pojo.PageParam;
import com.sunnao.oj.framework.common.pojo.PageResult;
import com.sunnao.oj.framework.common.util.object.BeanUtils;
import com.sunnao.oj.framework.excel.core.util.ExcelUtils;
import com.sunnao.oj.module.biz.controller.admin.question.vo.QuestionPageReqVO;
import com.sunnao.oj.module.biz.controller.admin.question.vo.QuestionRespVO;
import com.sunnao.oj.module.biz.controller.admin.question.vo.QuestionSaveReqVO;
import com.sunnao.oj.module.biz.dal.dataobject.question.QuestionDO;
import com.sunnao.oj.module.biz.service.question.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.sunnao.oj.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static com.sunnao.oj.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 题目")
@RestController
@RequestMapping("/biz/question")
@Validated
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @PostMapping("/create")
    @Operation(summary = "创建题目")
    @PreAuthorize("@ss.hasPermission('biz:question:create')")
    public CommonResult<Long> createQuestion(@Valid @RequestBody QuestionSaveReqVO createReqVO) {
        return success(questionService.createQuestion(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新题目")
    @PreAuthorize("@ss.hasPermission('biz:question:update')")
    public CommonResult<Boolean> updateQuestion(@Valid @RequestBody QuestionSaveReqVO updateReqVO) {
        questionService.updateQuestion(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除题目")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('biz:question:delete')")
    public CommonResult<Boolean> deleteQuestion(@RequestParam("id") Long id) {
        questionService.deleteQuestion(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除题目")
    @PreAuthorize("@ss.hasPermission('biz:question:delete')")
    public CommonResult<Boolean> deleteQuestionList(@RequestParam("ids") List<Long> ids) {
        questionService.deleteQuestionListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得题目")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('biz:question:query')")
    public CommonResult<QuestionRespVO> getQuestion(@RequestParam("id") Long id) {
        QuestionDO question = questionService.getQuestion(id);
        return success(BeanUtils.toBean(question, QuestionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得题目分页")
    @PreAuthorize("@ss.hasPermission('biz:question:query')")
    public CommonResult<PageResult<QuestionRespVO>> getQuestionPage(@Valid QuestionPageReqVO pageReqVO) {
        PageResult<QuestionDO> pageResult = questionService.getQuestionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, QuestionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出题目 Excel")
    @PreAuthorize("@ss.hasPermission('biz:question:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportQuestionExcel(@Valid QuestionPageReqVO pageReqVO,
                                    HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<QuestionDO> list = questionService.getQuestionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "题目.xls", "数据", QuestionRespVO.class,
                BeanUtils.toBean(list, QuestionRespVO.class));
    }

}