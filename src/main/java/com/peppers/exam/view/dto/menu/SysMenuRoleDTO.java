package com.peppers.exam.view.dto.menu;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.peppers.exam.view.dto.role.SysRoleDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author peppers
 * @description 菜单-角色关联
 * @since 2021/1/7
 **/
@Data
public class SysMenuRoleDTO implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime,updateTime;


    private SysMenuDTO menuDTO;

    private SysRoleDTO roleDTO;
}
