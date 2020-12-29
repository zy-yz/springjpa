package com.peppers.exam.view.vo.label;


import com.peppers.exam.view.dto.label.TypesLabelDTO;


public class TypesLabelVO extends TypesLabelDTO {

    @Override
    public String toString() {
        return "TypesLabelVO{" +
                "type=" + getType() +
                ", id=" + getId() +
                ", name='" + getName() + '\'' +
                ", enabled=" + getEnabled() +
                ", code='" + getCode() + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        if(getId()==null){
            return Long.valueOf(System.currentTimeMillis()).hashCode();
        }
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
