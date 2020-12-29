package com.peppers.exam.repository.base;

import com.peppers.exam.entity.baseeneity.BaseEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;


@Transactional(readOnly = true)
public class BaseRepository<T extends BaseEntity,ID> extends SimpleJpaRepository<T,ID>  {
    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager em;
    public BaseRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.em = entityManager;
    }

    public BaseRepository(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        entityInformation = null;
        this.em = em;
    }

}

