package com.peppers.exam.repositoryimpl;

import com.peppers.exam.entity.label.Label;
import com.peppers.exam.entity.label.TypesLabel;
import com.peppers.exam.repository.label.TypesLabelJpaRepository;
import com.peppers.exam.repository.label.TypesLabelRepository;
import com.peppers.exam.utils.page.PageData;
import com.peppers.exam.utils.page.PageDataRequest;
import com.peppers.exam.view.query.TypesLabelQuery;
import com.peppers.exam.view.vo.label.TypesLabelVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author peppers
 * @description
 * @since 2020/12/29
 **/
@Repository
public class TypesLabelRepositoryImpl implements TypesLabelRepository<TypesLabel> {

    EntityManager entityManager;

    public TypesLabelRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    TypesLabelJpaRepository typesLabelRepository;



//    @Override
//    public void findList(TypesLabelQuery params, Pageable pageAble) {
//        Page<TypesLabel> page = typesLabelRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
//            List<Predicate> predicatesList = new ArrayList<>();
//            //name模糊查询 ,like语句
//            return criteriaBuilder.and(
//                    predicatesList.toArray(new Predicate[predicatesList.size()]));
//        }, pageAble);
//       // return page;
//    }
}
