package com.peppers.exam.entity.roleuser;


import com.peppers.exam.entity.baseeneity.BaseEntity;
import com.peppers.exam.entity.role.SysRole;
import com.peppers.exam.entity.user.SysUser;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author peppers
 * @description
 * @since 2020/12/25
 **/
@Data
@Entity
@ToString(exclude = {"userRole","roleUser"})
public class SysUserRole extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createTime,updateTime;

    @ManyToOne
    @JoinColumn(name = "user_role_id")
    private SysUser userRole;
    @ManyToOne
    @JoinColumn(name = "role_user_id")
    private SysRole roleUser;


}
