package com.sunnao.oj.module.biz.enums;

import com.sunnao.oj.framework.common.exception.ErrorCode;

/**
 * biz 错误码枚举类
 * <p>
 * biz 系统，使用 1-009-000-000 段
 */
public interface ErrorCodeConstants {

    ErrorCode QUESTION_NOT_EXISTS = new ErrorCode(1_009_000_001, "题目不存在");

    ErrorCode TAG_NOT_EXISTS = new ErrorCode(1_009_000_002, "标签不存在");

    ErrorCode LINK_QUESTION_TAG_NOT_EXISTS = new ErrorCode(1_009_000_003, "题目标签关联不存在");

    ErrorCode QUESTION_SUBMIT_NOT_EXISTS = new ErrorCode(1_009_000_004, "题目提交记录不存在");

}
