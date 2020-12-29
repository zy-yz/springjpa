package com.peppers.exam.enums;


public enum AuditStatusEnum {
    /***
     * 草稿，未提交
     * 0
     */
    DRAFT,
    /***
     * 已提交，待审核，审核中
     * 1
     */
    SUBMITTED,
    /***
     * 通过
     * 2
     */
    PAST,
    /***
     * 驳回
     * 3
     */
    REJECTED,
    /**
     * 会签中
     * 4
     * */
    SIGNSTATUS,
}
