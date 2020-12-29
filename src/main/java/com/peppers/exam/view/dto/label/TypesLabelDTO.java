package com.peppers.exam.view.dto.label;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.peppers.exam.enums.LabelTypeEnum;


@JsonIgnoreProperties(ignoreUnknown = true)
public class TypesLabelDTO extends LabelDTO {
    @JsonIgnoreProperties(ignoreUnknown = true)
    private LabelTypeEnum type;

    public LabelTypeEnum getType() {
        return type;
    }

    public void setType(LabelTypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TypesLabelDTO{" +
                "type=" + type +
                '}';
    }
}
