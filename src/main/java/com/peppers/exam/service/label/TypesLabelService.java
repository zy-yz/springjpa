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
@Transactional(rollbackFor = Exception.class)
public class TypesLabelService {

    @Resource
    private TypesLabelJpaRepository typesLabelRepository;



    /***
     * 创建
     * @param command 创建命令
     * @return 分类信息
     */
    public TypesLabel create(TypesLabelDTO command){
        TypesLabel typesLabel = new TypesLabel();
        BeanUtils.copyProperties(command,typesLabel,"id");
        return typesLabelRepository.save(typesLabel);
    }

    /***
     * 编辑
     * @param command 编辑命令
     * @return 分类信息
     */
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
}
