package com.peppers.exam.view.vo.user;

import com.peppers.exam.entity.roleuser.SysUserRole;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author peppers
 * @description 用户VO
 * @since 2021/1/7
 **/
@Data
public class SysUserVO implements Serializable {

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
     *通过中间表 多对多
     * */
    private List<SysUserRole> userRoleRelationVO;
}
