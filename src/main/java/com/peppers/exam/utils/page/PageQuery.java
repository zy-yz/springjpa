package com.peppers.exam.utils.page;

import java.io.Serializable;

/**
 * @author peppers
 * @description
 * @since 2020/12/20
 **/
public class PageQuery implements Serializable {
    private int pageNo = 1;
    private int pageSize = 15;

    public PageQuery() {
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageNumber() {
        return this.getPageNo();
    }

    public int getPageNo() {
        return this.pageNo <= 0 ? 1 : this.pageNo;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public String toString() {
        return "PageQuery{pageNo=" + this.pageNo + ", pageSize=" + this.pageSize + '}';
    }
}
