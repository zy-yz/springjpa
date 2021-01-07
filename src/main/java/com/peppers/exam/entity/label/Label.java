package com.peppers.exam.entity.label;

import com.peppers.exam.entity.baseeneity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;


@Getter
@Setter
@MappedSuperclass
public class Label extends BaseEntity {
    private String name;
    /***
     * 是否可用
     */
    private Boolean enabled;
    private String code;
}