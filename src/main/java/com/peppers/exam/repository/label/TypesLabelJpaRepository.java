package com.peppers.exam.repository.label;

import com.peppers.exam.entity.label.TypesLabel;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

/**
 * @author peppers
 * @description
 * @since 2020/12/28
 **/
public interface TypesLabelJpaRepository extends JpaRepository<TypesLabel,Long>, JpaSpecificationExecutor<TypesLabel>, TypesLabelRepository {

    Optional<TypesLabel> findByCode(String id);

    /**
     * 加悲观锁
     *生产环境中要慎用悲观锁，因为它是阻塞的，一旦发生服务异常，可能会造成死锁的现象
     * */
//    @Override
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    Optional<TypesLabel> findById(Long id);



}
