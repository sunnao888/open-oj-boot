package com.sunnao.oj.module.infra.service.demo.demo03.inner;

import com.sunnao.oj.framework.common.pojo.PageResult;
import com.sunnao.oj.module.infra.controller.admin.demo.demo03.inner.vo.Demo03StudentInnerPageReqVO;
import com.sunnao.oj.module.infra.controller.admin.demo.demo03.inner.vo.Demo03StudentInnerSaveReqVO;
import com.sunnao.oj.module.infra.dal.dataobject.demo.demo03.Demo03CourseDO;
import com.sunnao.oj.module.infra.dal.dataobject.demo.demo03.Demo03GradeDO;
import com.sunnao.oj.module.infra.dal.dataobject.demo.demo03.Demo03StudentDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 学生 Service 接口
 *
 * @author sunnao
 */
public interface Demo03StudentInnerService {

    /**
     * 创建学生
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDemo03Student(@Valid Demo03StudentInnerSaveReqVO createReqVO);

    /**
     * 更新学生
     *
     * @param updateReqVO 更新信息
     */
    void updateDemo03Student(@Valid Demo03StudentInnerSaveReqVO updateReqVO);

    /**
     * 删除学生
     *
     * @param id 编号
     */
    void deleteDemo03Student(Long id);

    /**
     * 批量删除学生
     *
     * @param ids 编号
     */
    void deleteDemo03StudentListByIds(List<Long> ids);

    /**
     * 获得学生
     *
     * @param id 编号
     * @return 学生
     */
    Demo03StudentDO getDemo03Student(Long id);

    /**
     * 获得学生分页
     *
     * @param pageReqVO 分页查询
     * @return 学生分页
     */
    PageResult<Demo03StudentDO> getDemo03StudentPage(Demo03StudentInnerPageReqVO pageReqVO);

    // ==================== 子表（学生课程） ====================

    /**
     * 获得学生课程列表
     *
     * @param studentId 学生编号
     * @return 学生课程列表
     */
    List<Demo03CourseDO> getDemo03CourseListByStudentId(Long studentId);

    // ==================== 子表（学生班级） ====================

    /**
     * 获得学生班级
     *
     * @param studentId 学生编号
     * @return 学生班级
     */
    Demo03GradeDO getDemo03GradeByStudentId(Long studentId);

}