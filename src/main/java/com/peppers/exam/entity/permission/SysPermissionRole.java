package com.peppers.exam.entity.permission;


import com.peppers.exam.entity.baseeneity.BaseEntity;
import com.peppers.exam.entity.role.SysRole;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @author peppers
 * @description 权限-角色关联
 * @since 2020/12/25
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"sysRole","sysPermission"})
public class SysPermissionRole extends BaseEntity {



    @ManyToOne
    @JoinColumn(name = "sys_role_id")
    private SysRole sysRole;
    @ManyToOne
    @JoinColumn(name = "sys_permission_id")
    private SysPermission sysPermission;

}
