package com.peppers.exam.view.dto.permission;


import com.peppers.exam.view.dto.role.SysRoleDTO;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author peppers
 * @description 权限-角色关联
 * @since 2020/12/25
 **/
@Data
public class SysPermissionRoleDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createTime,updateTime;


    private SysRoleDTO sysRoleDTO;

    private SysPermissionDTO permissionDTO;

}
