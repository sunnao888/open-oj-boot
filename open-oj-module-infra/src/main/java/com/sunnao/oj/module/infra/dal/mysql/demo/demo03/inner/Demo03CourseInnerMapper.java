package com.sunnao.oj.module.infra.dal.mysql.demo.demo03.inner;

import com.sunnao.oj.framework.mybatis.core.mapper.BaseMapperX;
import com.sunnao.oj.module.infra.dal.dataobject.demo.demo03.Demo03CourseDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 学生课程 Mapper
 *
 * @author sunnao
 */
@Mapper
public interface Demo03CourseInnerMapper extends BaseMapperX<Demo03CourseDO> {

    default List<Demo03CourseDO> selectListByStudentId(Long studentId) {
        return selectList(Demo03CourseDO::getStudentId, studentId);
    }

    default int deleteByStudentId(Long studentId) {
        return delete(Demo03CourseDO::getStudentId, studentId);
    }

    default int deleteByStudentIds(List<Long> studentIds) {
        return deleteBatch(Demo03CourseDO::getStudentId, studentIds);
    }

}
