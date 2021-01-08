package com.peppers.exam.view.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.peppers.exam.view.dto.role.SysRoleDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author peppers
 * @description
 * @since 2021/1/7
 **/
@Data
public class SysUserRoleDTO implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime,updateTime;


    /**
     *  用户表
     * */
    private SysUserDTO sysUserDTO;

    /**
     * 角色表
     * */
    private SysRoleDTO roleDTO;
}
