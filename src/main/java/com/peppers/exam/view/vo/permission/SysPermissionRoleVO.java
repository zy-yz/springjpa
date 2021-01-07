package com.peppers.exam.view.vo.permission;


import com.peppers.exam.view.vo.role.SysRoleVO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author peppers
 * @description 权限-角色关联
 * @since 2020/12/25
 **/
@Data
public class SysPermissionRoleVO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createTime,updateTime;


    private SysRoleVO sysRole;

    private SysPermissionVO sysPermission;

}
