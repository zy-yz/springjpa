package com.peppers.exam.view.vo.permission;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author peppers
 * @description
 * @since 2020/12/25
 **/
@Data
public class SysPermissionVO implements Serializable {

    private String url;

    private String path;

    private String component;

    private String name;

    private String iconcls;

    private Boolean keepalive;

    private Boolean requireauth;

    private Boolean enabled;

    private List<SysPermissionVO> children;


    private SysPermissionVO parent;


    /**
     *
     * 通过中间表*/
    private List<SysPermissionRoleVO> permissionRolesRelation;
}
