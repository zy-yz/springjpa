package com.peppers.exam.controller;

import com.peppers.exam.entity.label.TypesLabel;
import com.peppers.exam.service.label.TypesLabelService;
import com.peppers.exam.utils.page.PageDataRequest;
import com.peppers.exam.utils.page.ResponseResult;
import com.peppers.exam.view.converter.label.LabelConverter;
import com.peppers.exam.view.dto.label.TypesLabelDTO;
import com.peppers.exam.view.query.TypesLabelQuery;
import com.peppers.exam.view.vo.label.TypesLabelVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "标签服务API")
@RequestMapping("/label-types")
public class TypesLabelController  {

    @Resource
    private TypesLabelService labelService;




    @ApiOperation("创建")
    @PostMapping
    public ResponseResult<TypesLabelVO> create(@RequestBody TypesLabelDTO command){

        return ResponseResult.success(LabelConverter.toVO(labelService.create(command)));
    }

    @ApiOperation("编辑")
    @PutMapping
    public ResponseResult<TypesLabelVO> edit(@RequestBody TypesLabelDTO command){


        return ResponseResult.success(labelService.edit(command));
    }

    @ApiOperation("详情")
    @GetMapping("/{id}")
    public ResponseResult<TypesLabelVO> detail(@PathVariable("id") Long id){
        return ResponseResult.success(labelService.findById(id));
    }

    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public ResponseResult<TypesLabelVO> delete(@PathVariable("id") Long id){
        labelService.delete(id);
        return ResponseResult.success();
    }

    @ApiOperation("获取类型标签列表数据")
    @PostMapping("/query")
    public ResponseResult<List<TypesLabelVO>> query(@RequestBody PageDataRequest<TypesLabelQuery> params){
        Pageable pageable = new PageRequest(params.getPageNo(), params.getPageSize());
        List<TypesLabel> query = labelService.query(params.getQuery(), pageable);

        return ResponseResult.success(LabelConverter.toTypeVOS(query));

    }



}
