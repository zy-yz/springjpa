package com.peppers.exam.service.label;


import com.peppers.exam.entity.label.TypesLabel;
import com.peppers.exam.repository.label.TypesLabelJpaRepository;
import com.peppers.exam.utils.SupportBeanUtils;
import com.peppers.exam.utils.page.PageData;
import com.peppers.exam.utils.page.PageDataRequest;
import com.peppers.exam.view.converter.label.LabelConverter;
import com.peppers.exam.view.dto.label.TypesLabelDTO;
import com.peppers.exam.view.query.TypesLabelQuery;
import com.peppers.exam.view.vo.label.TypesLabelVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author peppers
 * @description
 * @since 2020/12/28
 **/
@Service
@Slf4j
public class TypesLabelService {

    @Resource
    private TypesLabelJpaRepository typesLabelRepository;



    /***
     * 创建
     * @param command 创建命令
     * @return 分类信息
     */
    @Transactional(rollbackFor = Exception.class)
    public TypesLabel create(TypesLabelDTO command){
        TypesLabel typesLabel = new TypesLabel();
        BeanUtils.copyProperties(command,typesLabel,"id");
        return typesLabelRepository.save(typesLabel);
    }

    /***
     * 编辑
     * @param command 编辑命令
     * @return 分类信息
     *
     * 这里测试一下重试机制
     */
    @Transactional(rollbackFor = Exception.class)
    @Retryable
    public TypesLabelVO edit(TypesLabelDTO command){
        Optional<TypesLabel> typesLabel = typesLabelRepository.findById(command.getId());

        SupportBeanUtils.copyProperties(command,typesLabel);
        return LabelConverter.toVO(typesLabel.get());
    }

    public TypesLabelVO findById(Long id) {
        Optional<TypesLabel> typesLabel = typesLabelRepository.findById(id);
        return LabelConverter.toVO(typesLabel.get());
    }

    public TypesLabelVO findByCode(String  id) {
        Optional<TypesLabel> typesLabel = typesLabelRepository.findByCode(id);
        return LabelConverter.toVO(typesLabel.get());
    }


    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Optional<TypesLabel> typesLabel = typesLabelRepository.findById(id);
        typesLabelRepository.delete(typesLabel.get());
    }

    public List<TypesLabel> query(TypesLabelQuery params, Pageable page) {


        //Page<TypesLabel> list = typesLabelRepository.findList(params, page);

        List<TypesLabel> all = typesLabelRepository.findAll();

        return all;
        //return list;
    }

    /**
     * 测试乐观锁的
     * */
    @org.springframework.transaction.annotation.Transactional
    public TypesLabel calculate(Long userId) {
        TypesLabel userInfo = typesLabelRepository.getOne(userId);
        try {
            //模拟复杂的业务计算逻辑耗时操作；
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userInfo.setName(userInfo.getName()+1);
        return typesLabelRepository.saveAndFlush(userInfo);
    }
}
