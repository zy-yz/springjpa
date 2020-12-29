package com.peppers.exam.utils.page;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * @author peppers
 * @description
 * @since 2020/12/20
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> implements Serializable {
    public static final int SUCCESS = 200;
    public static final int ERROR = 500;
    private String message;
    private int code;
    private T data;
    private Integer pageSize;
    private Integer currentPage;
    private Integer totalPage;
    private Long totalSize;

    private ResponseResult() {
    }

    public boolean isSuccess() {
        return this.getCode() == 200;
    }

    public static <T> ResponseResult<T> error(int code, String msg) {
        ResponseResult<T> responseResult = new ResponseResult();
        responseResult.code = code;
        responseResult.message = msg;
        return responseResult;
    }

    public static <T> ResponseResult<T> error(String msg) {
        return error(500, msg);
    }

    public static <T> ResponseResult<T> success() {
        return success("success");
    }

    public static <T> ResponseResult<T> success(String msg) {
        ResponseResult<T> responseResult = new ResponseResult();
        responseResult.message = msg;
        responseResult.code = 200;
        return responseResult;
    }

    public static <T> ResponseResult<T> success(String msg, T data) {
        ResponseResult<T> responseResult = success(msg);
        responseResult.data = data;
        return responseResult;
    }

    public static <T> ResponseResult<T> success(T data) {
        return success("success", data);
    }

    public static <T> ResponseResult<List<T>> pageResult(PageData<T> page) {
        ResponseResult<List<T>> responseResult = success(page.getData());
        responseResult.currentPage = page.getPageNumber();
        responseResult.pageSize = page.getPageSize();
        responseResult.totalPage = page.getTotalPages();
        responseResult.totalSize = page.getTotalElements();
        return responseResult;
    }

    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Integer getCurrentPage() {
        return this.currentPage;
    }

    public Integer getTotalPage() {
        return this.totalPage;
    }

    public Long getTotalSize() {
        return this.totalSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }
}

