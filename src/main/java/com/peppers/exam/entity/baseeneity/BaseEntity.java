package com.peppers.exam.entity.baseeneity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.peppers.exam.enums.DeletedStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author peppers
 * @description 基本实体
 * @since 2020/12/20
 **/
@MappedSuperclass
@EntityListeners({BaseEntityListener.class})
public abstract class BaseEntity implements Serializable {
    @Id
    @JsonSerialize(
            using = ToStringSerializer.class
    )
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            name = "create_time",
            nullable = false,
            updatable = false
    )
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            name = "modified_time",
            nullable = false,
            updatable = true
    )
    private Date modifiedTime;
    @Column(
            name = "creator_id",
            length = 32
    )
    private String creatorId;
    @Column(
            name = "dept_id",
            length = 32
    )
    private String deptId;
    @Column(
            name = "version",
            length = 20
    )
    /**Spring Data JPA 里面有两个 @Version 注解，
     * 请使用 @javax.persistence.Version，
     * 而不是 @org.springframework.data.annotation.Version*/
    @Version
    @JsonSerialize(
            using = ToStringSerializer.class
    )
    private Long version;
    @Enumerated(EnumType.ORDINAL)
    @Column(
            name = "is_deleted",
            length = 1
    )
    private DeletedStatus isDeleted;

    public BaseEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return this.modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getDeptId() {
        return this.deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public DeletedStatus getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(DeletedStatus isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            if (this.id == null) {
                return false;
            } else {
                BaseEntity that = (BaseEntity)o;
                return this.id.equals(that.id);
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.id == null ? Long.valueOf(System.currentTimeMillis()).hashCode() : this.id.hashCode();
    }
}
