package com.peppers.exam.view.query;

import lombok.Data;

import java.util.Set;

/**
 * @author peppers
 * @description
 * @since 2020/12/28
 **/
@Data
public class TypesLabelQuery {

    private String name;
    private Boolean enabled;
    private String code;

    private Integer type;
    private Set<Integer> types;
    private Boolean isParent;

    private Set<Long> ids;
}
