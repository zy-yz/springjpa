package com.peppers.exam.entity.label;


import com.peppers.exam.enums.LabelTypeEnum;
import lombok.Builder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Entity
@Table(name = "types_label")
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicInsert /**注解表示 insert 的时候，会动态生产 insert SQL 语句，其生成 SQL 的规则是：只有非空的字段才能生成 SQL*/
@DynamicUpdate
public class TypesLabel extends Label {
    @Enumerated(EnumType.ORDINAL)
    @Column(name="type",length = 3)
    private LabelTypeEnum type;

    public LabelTypeEnum getType() {
        return type;
    }

    public void setType(LabelTypeEnum type) {
        this.type = type;
    }
    @Override
    public boolean equals(Object o) {
        if (getId() == null) {
            return false;
        }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        if(getId()==null){
            return Long.valueOf(System.currentTimeMillis()).hashCode();
        }
        return super.hashCode();
    }
}
