package com.peppers.exam.entity.label;


import com.peppers.exam.enums.LabelTypeEnum;

import javax.persistence.*;


@Entity
@Table(name = "types_label")
@Inheritance(strategy = InheritanceType.JOINED)
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
