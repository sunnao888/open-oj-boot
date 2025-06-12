package com.sunnao.oj.module.infra.controller.admin.demo.demo03.inner;

import com.sunnao.oj.framework.apilog.core.annotation.ApiAccessLog;
import com.sunnao.oj.framework.common.pojo.CommonResult;
import com.sunnao.oj.framework.common.pojo.PageParam;
import com.sunnao.oj.framework.common.pojo.PageResult;
import com.sunnao.oj.framework.common.util.object.BeanUtils;
import com.sunnao.oj.framework.excel.core.util.ExcelUtils;
import com.sunnao.oj.module.infra.controller.admin.demo.demo03.inner.vo.Demo03StudentInnerPageReqVO;
import com.sunnao.oj.module.infra.controller.admin.demo.demo03.inner.vo.Demo03StudentInnerRespVO;
import com.sunnao.oj.module.infra.controller.admin.demo.demo03.inner.vo.Demo03StudentInnerSaveReqVO;
import com.sunnao.oj.module.infra.dal.dataobject.demo.demo03.Demo03CourseDO;
import com.sunnao.oj.module.infra.dal.dataobject.demo.demo03.Demo03GradeDO;
import com.sunnao.oj.module.infra.dal.dataobject.demo.demo03.Demo03StudentDO;
import com.sunnao.oj.module.infra.service.demo.demo03.inner.Demo03StudentInnerService;
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

@Tag(name = "管理后台 - 学生")
@RestController
@RequestMapping("/infra/demo03-student-inner")
@Validated
public class Demo03StudentInnerController {

    @Resource
    private Demo03StudentInnerService demo03StudentInnerService;

    @PostMapping("/create")
    @Operation(summary = "创建学生")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:create')")
    public CommonResult<Long> createDemo03Student(@Valid @RequestBody Demo03StudentInnerSaveReqVO createReqVO) {
        return success(demo03StudentInnerService.createDemo03Student(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新学生")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:update')")
    public CommonResult<Boolean> updateDemo03Student(@Valid @RequestBody Demo03StudentInnerSaveReqVO updateReqVO) {
        demo03StudentInnerService.updateDemo03Student(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除学生")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:delete')")
    public CommonResult<Boolean> deleteDemo03Student(@RequestParam("id") Long id) {
        demo03StudentInnerService.deleteDemo03Student(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除学生")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:delete')")
    public CommonResult<Boolean> deleteDemo03StudentList(@RequestParam("ids") List<Long> ids) {
        demo03StudentInnerService.deleteDemo03StudentListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得学生")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:query')")
    public CommonResult<Demo03StudentInnerRespVO> getDemo03Student(@RequestParam("id") Long id) {
        Demo03StudentDO demo03Student = demo03StudentInnerService.getDemo03Student(id);
        return success(BeanUtils.toBean(demo03Student, Demo03StudentInnerRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得学生分页")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:query')")
    public CommonResult<PageResult<Demo03StudentInnerRespVO>> getDemo03StudentPage(@Valid Demo03StudentInnerPageReqVO pageReqVO) {
        PageResult<Demo03StudentDO> pageResult = demo03StudentInnerService.getDemo03StudentPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, Demo03StudentInnerRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出学生 Excel")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDemo03StudentExcel(@Valid Demo03StudentInnerPageReqVO pageReqVO,
                                         HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<Demo03StudentDO> list = demo03StudentInnerService.getDemo03StudentPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "学生.xls", "数据", Demo03StudentInnerRespVO.class,
                BeanUtils.toBean(list, Demo03StudentInnerRespVO.class));
    }

    // ==================== 子表（学生课程） ====================

    @GetMapping("/demo03-course/list-by-student-id")
    @Operation(summary = "获得学生课程列表")
    @Parameter(name = "studentId", description = "学生编号")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:query')")
    public CommonResult<List<Demo03CourseDO>> getDemo03CourseListByStudentId(@RequestParam("studentId") Long studentId) {
        return success(demo03StudentInnerService.getDemo03CourseListByStudentId(studentId));
    }

    // ==================== 子表（学生班级） ====================

    @GetMapping("/demo03-grade/get-by-student-id")
    @Operation(summary = "获得学生班级")
    @Parameter(name = "studentId", description = "学生编号")
    @PreAuthorize("@ss.hasPermission('infra:demo03-student:query')")
    public CommonResult<Demo03GradeDO> getDemo03GradeByStudentId(@RequestParam("studentId") Long studentId) {
        return success(demo03StudentInnerService.getDemo03GradeByStudentId(studentId));
    }

}