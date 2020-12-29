package com.peppers.exam.entity.user;


import com.peppers.exam.entity.baseeneity.BaseEntity;
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
 * @description 用户表
 * @since 2020/12/25
 **/
@Entity
@Data
@ToString(exclude = "userRoleRelation")
public class SysUser extends BaseEntity {

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 住宅电话
     */
    private String telephone;

    /**
     * 联系地址
     */
    private String address;

    private Boolean enabled;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    private String userface;

    private String remark;

    /**
     *
     * 通过中间表*/
    @OneToMany(mappedBy = "userRole",cascade = {CascadeType.ALL},orphanRemoval = true)
    private List<SysUserRole> userRoleRelation = new ArrayList<>();

}
