package com.peppers.exam.entity.baseeneity;

import com.peppers.exam.enums.DeletedStatus;
import com.peppers.exam.utils.SnowflakeIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author peppers
 * @description
 *   回调方法：
 *   1回调函数都是和 EntityManager.flush 或 EntityManager.commit 在同一个线程里面执行的，只不过调用方法有先后之分，
 * 都是同步调用，所以当任何一个回调方法里面发生异常，都会触发事务进行回滚，而不会触发事务提交。
 *    2.使回调方法注解生效的回调方法可以是 public、private、protected、friendly 类型的，但是不能是 static 和 finnal 类型的方法
 * @since 2020/12/20
 **/
@Transactional(
        rollbackFor = {Exception.class}
)
public class BaseEntityListener {
    private static final Logger log = LoggerFactory.getLogger(BaseEntityListener.class);

    public BaseEntityListener() {
    }

    /**
     * 方法调用之前的回调注解
     * 可以理解为新增之前的回调方法
     * */
    @PrePersist
    public void prePersist(BaseEntity baseEntity) {
//        if (this.idGenerator == null) {
//            this.idGenerator = (IDGenerator) SpringContextUtils.getApplicationContext().getBean(IDGenerator.class);
//        }

        if (baseEntity.getId() == null) {
            baseEntity.setId((Long) SnowflakeIDGenerator.generateId());
        }

        baseEntity.setCreateTime(new Date());
        baseEntity.setModifiedTime(new Date());
//        log.error("当前线程:{},当前登录人ID：{}", Thread.currentThread(), CurrentPrincipalHolder.getUid());
//        baseEntity.setCreatorId(CurrentPrincipalHolder.getUid());
//        baseEntity.setVersion(1L);
//        if (baseEntity.getDeptId() == null) {
//            baseEntity.setDeptId((String)CurrentPrincipalHolder.getAttribute("orgId"));
//        }

        baseEntity.setIsDeleted(DeletedStatus.FALSE);
        log.info("对实体{}： {}。当前操作时间：{}", new Object[]{baseEntity, "保存之前，写入相关参数成功", (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date())});
    }


    /**
     * 在操作EntityManager.persist方法之后的回调注解，EntityManager.flush或者EntityManager.commit方法之后调用的方法
     * 可以理解为保存到数据库之后进行调用
     * */
    @PostPersist
    public void postPersist(BaseEntity baseEntity) {
        log.info("对实体{}： {}", baseEntity, "保存信息成功");
    }

    @PreUpdate
    public void preUpdate(BaseEntity baseEntity) {
        baseEntity.setModifiedTime(new Date());
        Long version = baseEntity.getVersion();
        if (version == null) {
            version = 0L;
        }

        baseEntity.setVersion(version + 1L);
        log.info("对实体{}：{}。当前操作时间：{}", new Object[]{baseEntity, "修改操作，写入相关参数成功", (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date())});
    }

    @PostUpdate
    public void postUpdate(BaseEntity baseEntity) {
        log.info("对实体{}： {}", baseEntity, "修改操作，修改成功");
    }

    /**
     * 删除方法之前调用
     * */
    @PreRemove
    public void preRemove(BaseEntity baseEntity) {
        log.info("对实体{}： {}", baseEntity, "删除操作，删除之前");
    }

    /***
     * 删除方法操作之后调用
     * */
    @PostRemove
    public void postRemove(BaseEntity baseEntity) {
        log.info("对实体{}： {}", baseEntity, "删除操作，删除成功");
    }
}