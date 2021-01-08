package com.peppers.exam.view.dto.permission;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author peppers
 * @description
 * @since 2020/12/25
 **/
@Data
public class SysPermissionDTO implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String url;

    private String path;

    private String component;

    private String name;

    private String iconcls;

    private Boolean keepalive;

    private Boolean requireauth;

    private Boolean enabled;

    private List<SysPermissionDTO> children;


    private SysPermissionDTO parent;


    /**
     *
     * 通过中间表*/
    private List<SysPermissionRoleDTO> permissionRoleDTOS;
}
