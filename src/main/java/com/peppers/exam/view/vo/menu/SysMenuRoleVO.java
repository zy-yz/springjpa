package com.peppers.exam.view.vo.menu;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.peppers.exam.entity.menu.SysMenu;
import com.peppers.exam.entity.role.SysRole;
import com.peppers.exam.view.vo.role.SysRoleVO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author peppers
 * @description 菜单-角色关联
 * @since 2021/1/7
 **/
@Data
public class SysMenuRoleVO implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime,updateTime;


    private SysMenuVO menuVO;

    private SysRoleVO roleVO;
}
