package com.peppers.exam.view.dto.role;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.peppers.exam.view.dto.menu.SysMenuRoleDTO;
import com.peppers.exam.view.dto.permission.SysPermissionRoleDTO;
import com.peppers.exam.view.dto.user.SysUserRoleDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author peppers
 * @description 角色表
 * @since 2021/1/7
 **/
@Data
public class SysRoleDTO implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 角色英文名称
     */
    private String nameEn;

    /**
     * 角色中文名称
     */
    private String nameCn;

    /**
     * 角色类型
     */
    private Integer groupType;

    /**
     * 角色独立
     */
    private Boolean onAlone;

    /**
     * 角色选择（前端使用）
     */
    private Boolean onChoose;

    /**
     *
     * 通过中间表*/
    private List<SysMenuRoleDTO> menuRoleDTOS;

    /**
     *
     * 通过中间表*/
    private List<SysUserRoleDTO> sysUserRoleDTOS;

    /**
     *
     * 通过中间表*/
    private List<SysPermissionRoleDTO> permissionRoleDTOS;
}
