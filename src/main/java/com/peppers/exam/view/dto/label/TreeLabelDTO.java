package com.peppers.exam.view.dto.label;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.peppers.exam.enums.LabelTypeEnum;


public class TreeLabelDTO<T> extends LabelDTO {
    private LabelTypeEnum type;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    /**
     * 备注
     */
    private String remark;

    public LabelTypeEnum getType() {
        return type;
    }

    public void setType(LabelTypeEnum type) {
        this.type = type;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "TreeLabelDTO{" +
                "type=" + type +
                ", parentId=" + parentId +
                ", remark='" + remark + '\'' +
                '}';
    }
}
