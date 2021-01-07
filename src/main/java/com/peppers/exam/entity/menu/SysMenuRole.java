package com.peppers.exam.entity.menu;

import com.peppers.exam.entity.baseeneity.BaseEntity;
import com.peppers.exam.entity.role.SysRole;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @author peppers
 * @description 菜单-角色关联
 * @since 2020/12/25
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"menu","role"})
public class SysMenuRole extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "menu_id")
    private SysMenu menu;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private SysRole role;
}
