package com.peppers.exam.view.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.peppers.exam.entity.role.SysRole;
import com.peppers.exam.entity.user.SysUser;
import com.peppers.exam.view.vo.role.SysRoleVO;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

/**
 * @author peppers
 * @description
 * @since 2021/1/7
 **/
@Data
public class SysUserRoleVO implements Serializable {

    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime,updateTime;


    /**
     *  用户表
     * */
    private SysUserVO userRoleVO;

    /**
     * 角色表
     * */
    private SysRoleVO roleUserVO;
}
