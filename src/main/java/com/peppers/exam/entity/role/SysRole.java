package com.peppers.exam.entity.role;


import com.peppers.exam.entity.baseeneity.BaseEntity;
import com.peppers.exam.entity.menu.SysMenuRole;
import com.peppers.exam.entity.permission.SysPermissionRole;
import com.peppers.exam.entity.roleuser.SysUserRole;
import lombok.Data;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author peppers
 * @description 角色表
 * @since 2020/12/25
 **/
@Data
@Entity
@ToString(exclude = {"roleRelation","userRoleRelation","sysPermissionRoles"})
public class SysRole extends BaseEntity {

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
    @OneToMany(mappedBy = "role",cascade = {CascadeType.ALL},orphanRemoval = true)
    private List<SysMenuRole> roleRelation = new ArrayList<>();

    /**
     *
     * 通过中间表*/
    @OneToMany(mappedBy = "roleUser",cascade = {CascadeType.ALL},orphanRemoval = true)
    private List<SysUserRole> userRoleRelation = new ArrayList<>();

    /**
     *
     * 通过中间表*/
    @OneToMany(mappedBy = "sysRole",cascade = {CascadeType.ALL},orphanRemoval = true)
    private List<SysPermissionRole> sysPermissionRoles = new ArrayList<>();
}
