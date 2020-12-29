package com.peppers.exam.utils.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author peppers
 * @description
 * @since 2020/12/20
 **/
public class PageData<T> implements Serializable {
    private final List<T> data;
    private Integer pageSize;
    private Integer pageNumber;
    private final Long totalElements;

    public PageData(List<T> data, Long totalElements) {
        this.data = data;
        this.totalElements = totalElements;
    }

    public PageData(List<T> data, long totalElements) {
        this.data = data;
        this.totalElements = totalElements;
    }

    public PageData(List<T> data) {
        if (data == null) {
            data = new ArrayList();
        }

        this.data = (List)data;
        this.totalElements = (long)((List)data).size();
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public PageData<T> pageSize(Integer pageSize) {
        this.setPageSize(pageSize);
        return this;
    }

    public PageData<T> pageSize(int pageSize) {
        this.setPageSize(pageSize);
        return this;
    }

    public PageData<T> pageNumber(Integer pageNumber) {
        this.setPageNumber(pageNumber);
        return this;
    }

    public PageData<T> pageNumber(int pageNumber) {
        this.setPageNumber(pageNumber);
        return this;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageNumber() {
        return this.pageNumber;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Integer getTotalPages() {
        return this.totalElements != null && this.pageSize != null ? this.data != null && !this.data.isEmpty() ? (int)Math.ceil((double)this.totalElements / (double)this.pageSize) : 1 : null;
    }

    public Long getTotalElements() {
        return this.totalElements;
    }

    public List<T> getData() {
        return this.data;
    }
}

