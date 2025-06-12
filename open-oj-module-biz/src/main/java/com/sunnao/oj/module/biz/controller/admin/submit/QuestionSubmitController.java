package com.sunnao.oj.module.biz.controller.admin.submit;

import com.sunnao.oj.framework.apilog.core.annotation.ApiAccessLog;
import com.sunnao.oj.framework.common.pojo.CommonResult;
import com.sunnao.oj.framework.common.pojo.PageParam;
import com.sunnao.oj.framework.common.pojo.PageResult;
import com.sunnao.oj.framework.common.util.object.BeanUtils;
import com.sunnao.oj.framework.excel.core.util.ExcelUtils;
import com.sunnao.oj.module.biz.controller.admin.submit.vo.QuestionSubmitPageReqVO;
import com.sunnao.oj.module.biz.controller.admin.submit.vo.QuestionSubmitRespVO;
import com.sunnao.oj.module.biz.controller.admin.submit.vo.QuestionSubmitSaveReqVO;
import com.sunnao.oj.module.biz.dal.dataobject.submit.QuestionSubmitDO;
import com.sunnao.oj.module.biz.service.submit.QuestionSubmitService;
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

@Tag(name = "管理后台 - 题目提交记录")
@RestController
@RequestMapping("/biz/submit")
@Validated
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @PostMapping("/create")
    @Operation(summary = "创建题目提交记录")
    @PreAuthorize("@ss.hasPermission('biz:submit:create')")
    public CommonResult<Long> createQuestionSubmit(@Valid @RequestBody QuestionSubmitSaveReqVO createReqVO) {
        return success(questionSubmitService.createQuestionSubmit(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新题目提交记录")
    @PreAuthorize("@ss.hasPermission('biz:submit:update')")
    public CommonResult<Boolean> updateQuestionSubmit(@Valid @RequestBody QuestionSubmitSaveReqVO updateReqVO) {
        questionSubmitService.updateQuestionSubmit(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除题目提交记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('biz:submit:delete')")
    public CommonResult<Boolean> deleteQuestionSubmit(@RequestParam("id") Long id) {
        questionSubmitService.deleteQuestionSubmit(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除题目提交记录")
                @PreAuthorize("@ss.hasPermission('biz:submit:delete')")
    public CommonResult<Boolean> deleteQuestionSubmitList(@RequestParam("ids") List<Long> ids) {
        questionSubmitService.deleteQuestionSubmitListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得题目提交记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('biz:submit:query')")
    public CommonResult<QuestionSubmitRespVO> getQuestionSubmit(@RequestParam("id") Long id) {
        QuestionSubmitDO questionSubmit = questionSubmitService.getQuestionSubmit(id);
        return success(BeanUtils.toBean(questionSubmit, QuestionSubmitRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得题目提交记录分页")
    @PreAuthorize("@ss.hasPermission('biz:submit:query')")
    public CommonResult<PageResult<QuestionSubmitRespVO>> getQuestionSubmitPage(@Valid QuestionSubmitPageReqVO pageReqVO) {
        PageResult<QuestionSubmitDO> pageResult = questionSubmitService.getQuestionSubmitPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, QuestionSubmitRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出题目提交记录 Excel")
    @PreAuthorize("@ss.hasPermission('biz:submit:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportQuestionSubmitExcel(@Valid QuestionSubmitPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<QuestionSubmitDO> list = questionSubmitService.getQuestionSubmitPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "题目提交记录.xls", "数据", QuestionSubmitRespVO.class,
                        BeanUtils.toBean(list, QuestionSubmitRespVO.class));
    }

}