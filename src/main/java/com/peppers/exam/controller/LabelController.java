package com.peppers.exam.controller;


import com.peppers.exam.entity.label.Label;
import com.peppers.exam.service.label.LabelApplicationService;
import com.peppers.exam.utils.page.ResponseResult;


public class LabelController<S extends LabelApplicationService<T>,T extends Label> {
    protected final S labelService;

    public LabelController(S labelService){
        this.labelService = labelService;
    }

    public ResponseResult<T> create(T label){
        return ResponseResult.success();
    }

    public ResponseResult<T> edit(T label){
        return ResponseResult.success();
    }
}
