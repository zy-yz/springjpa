package com.peppers.exam.repository.repositoryimpl;

import com.peppers.exam.entity.label.TypesLabel;
import com.peppers.exam.repository.label.TypesLabelJpaRepository;
import com.peppers.exam.repository.label.TypesLabelRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

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
